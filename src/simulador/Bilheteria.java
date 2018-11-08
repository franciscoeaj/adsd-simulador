package simulador;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_stat;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_normal_obj;
import eduni.simjava.distributions.Sim_random_obj;

public class Bilheteria extends Sim_entity {
	
	private Sim_port entrada, geral, cadeiras, areaConveniencia;
	private Sim_normal_obj delay;
	private Sim_random_obj prob;
	
	private Sim_stat stat;
	
	Bilheteria (String nome, double media, double variancia) {
		
		super(nome);
		
		entrada = new Sim_port("Entrada");
		geral = new Sim_port("Arquibancada Geral");
		cadeiras = new Sim_port("Arquibancada Cadeiras");
		areaConveniencia = new Sim_port("Area de Conveniencia");
		
		add_port(entrada);
		add_port(geral);
		add_port(cadeiras);
		add_port(areaConveniencia);
		
		delay = new Sim_normal_obj("Delay", media, variancia);
		prob = new Sim_random_obj("Probability");
		
		add_generator(delay);
		add_generator(prob);
		
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
			sim_trace(1, "Pessoa compra ingresso");
			
			double p = prob.sample();
			
			if (p <= 0.10) {
				//10% vai direto para o evento
				sim_schedule(areaConveniencia, 0.0, 1);
			} else if (p <= 0.30) {
				//90% vai para area de conveniencia antes do evento
				sim_schedule(cadeiras, 0.0, 1);
			} else {
				sim_schedule(geral, 0.0, 1);
			}
		}
	}

}
