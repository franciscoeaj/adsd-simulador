package simulador;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_stat;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_normal_obj;
import eduni.simjava.distributions.Sim_random_obj;

public class Lanchonete extends Sim_entity {

	private Sim_port entrada, geral, cadeiras;
	
	private Sim_normal_obj delay;
	private Sim_random_obj prob;
	
	private Sim_stat stat;
	
	Lanchonete (String nome, double media, double variancia) {
		
		super(nome);
		
		entrada = new Sim_port("Entrada");
		geral = new Sim_port("Arquibancada Geral");
		cadeiras = new Sim_port("Arquibancada Cadeiras");
		
		add_port(entrada);
		add_port(geral);
		add_port(cadeiras);
		
		delay = new Sim_normal_obj("Delay", media, variancia);
		prob = new Sim_random_obj("Probability");
		
		add_generator(delay);
		add_generator(prob);
		
		//Medidas de estatistica
		stat = new Sim_stat();
		
		stat.add_measure(Sim_stat.ARRIVAL_RATE); //Taxa de chegada
		stat.add_measure(Sim_stat.QUEUE_LENGTH); //Tamanho da fila
		stat.add_measure(Sim_stat.WAITING_TIME); //Tempo de espera
		stat.add_measure(Sim_stat.UTILISATION);  //Utilização
		stat.add_measure(Sim_stat.RESIDENCE_TIME); //Tempo de resposta
		
		set_stat(stat);
		
	}
	
	public void body() {
		
		while (Sim_system.running()) {
			
			Sim_event e = new Sim_event();
			
			sim_get_next(e);
			
			sim_process(delay.sample());
			
			sim_completed(e);
			
			double p = prob.sample();
			
			if (p <= 0.20) {
				//1% das pessoas vão pra casa antes de ir pro evento
				sim_trace(1, "Pessoa vai pras cadeiras");
				sim_schedule(cadeiras, 0.0, 1);
				
			} else {
				//99% sai da lanchonete e vai para o evento
				sim_trace(1, "Pessoa vai para a geral");
				sim_schedule(geral, 0.0, 1);
			}
		}
		
	}
}
