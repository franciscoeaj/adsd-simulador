package simulador;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_stat;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_normal_obj;
import eduni.simjava.distributions.Sim_random_obj;

public class Banheiro extends Sim_entity {
	
	private Sim_port entrada, arqGeral, arqCadeiras;
	private Sim_normal_obj delay;
	private Sim_random_obj prob;
	private Sim_stat stat;
	
	public Banheiro(Entidades banheiroMasculino, double media, double variancia) {
		super(banheiroMasculino.getNome());
		
		entrada = new Sim_port(Entidades.ENTRADA.getNome());
		add_port(entrada);
		
		arqGeral = new Sim_port(Entidades.ARQUIBANCADA_GERAL.getNome());
		add_port(arqGeral);
		
		arqCadeiras = new Sim_port(Entidades.ARQUIBANCADA_CADEIRAS.getNome());
		add_port(arqCadeiras);
		
		delay = new Sim_normal_obj("Delay", media, variancia);
		add_generator(delay);
		
		prob = new Sim_random_obj("Probabilidade");
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
			
			double p = prob.sample();
			
			if (p <= 0.3) {
				sim_trace(1, "Pessoa sai do banheiro e vai para as cadeiras");
				sim_schedule(arqCadeiras, 0.0, 1);
			} else {
				sim_trace(1, "Pessoa sai do banheiro e vai para a torcida geral");
				sim_schedule(arqGeral, 0.0, 1);
			}
		}
	}
	

}
