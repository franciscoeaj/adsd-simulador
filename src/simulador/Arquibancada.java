package simulador;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_stat;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_negexp_obj;

public class Arquibancada extends Sim_entity{
	
	private Sim_port entrada, saida;
	private Sim_negexp_obj delay;
	private Sim_stat stat;
	
	public Arquibancada(String nome, double media) {
		super(nome);
		
		entrada = new Sim_port("Entrada");
		add_port(entrada);
		
		saida = new Sim_port("Saida");
		add_port(saida);
		
		delay = new Sim_negexp_obj("Delay", media);
		add_generator(delay);
		
		stat = new Sim_stat();
		stat.add_measure(Sim_stat.ARRIVAL_RATE); //Taxa de chegada
		stat.add_measure(Sim_stat.QUEUE_LENGTH); //Tamanho da fila
		stat.add_measure(Sim_stat.WAITING_TIME); //Tempo de espera
		stat.add_measure(Sim_stat.UTILISATION);  //Utiliza��o
		stat.add_measure(Sim_stat.RESIDENCE_TIME); //Tempo de resposta

		set_stat(stat);
	}
	
	public void body() {
		while (Sim_system.running()) {
			Sim_event e = new Sim_event();
			sim_get_next(e);
			sim_process(delay.sample());
			sim_completed(e);
			
			sim_schedule(saida, 0.0, 1);
		}
	}
}
