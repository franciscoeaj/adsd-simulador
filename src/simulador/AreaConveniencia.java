package simulador;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_stat;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_normal_obj;
import eduni.simjava.distributions.Sim_random_obj;

public class AreaConveniencia extends Sim_entity{

	private Sim_port entrada, lanchonete, banheiroM, banheiroF;
	private Sim_normal_obj delay;
	private Sim_random_obj prob;
	private Sim_stat stat;
	
	public AreaConveniencia(String nome, double media, double variancia) {
		super(nome);
		
		entrada = new Sim_port("Entrada");
		add_port(entrada);
		
		lanchonete = new Sim_port("Lanchonete");
		add_port(lanchonete);
		
		banheiroM = new Sim_port("Banheiro masculino");
		add_port(banheiroM);
		
		banheiroF= new Sim_port("Banheiro feminino");
		add_port(banheiroF);
		
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
			
			if (p <= 10) {
				sim_trace(1, "Pessoa foi ao banheiro feminino");
				sim_schedule(banheiroF, 0.0, 1);
				
			} else if (p <= 35) {
				sim_trace(1, "Pessoa foi ao banheiro masculino");
				sim_schedule(banheiroM, 0.0, 1);
				
			} else {
				sim_trace(1, "Pessoa foi a lanchonete");
				sim_schedule(lanchonete, 0.0, 1);
			}
		}
	}
}
