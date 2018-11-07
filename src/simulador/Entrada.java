package simulador;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_port;
import eduni.simjava.distributions.Sim_negexp_obj;
import eduni.simjava.distributions.Sim_random_obj;


public class Entrada extends Sim_entity {
	
	private Sim_port bilheteriaGeral;
	private Sim_port bilheteriaPreferencial;
	private Sim_negexp_obj delay;
	private Sim_random_obj prob;
	
	Entrada (String nome, double media) {
		super(nome); 
		
		bilheteriaGeral = new Sim_port("Bilheteria geral");
		add_port(bilheteriaGeral);
		
		bilheteriaPreferencial = new Sim_port("Bilheteria preferencial");
		add_port(bilheteriaPreferencial);
		
		delay = new Sim_negexp_obj("Delay", media);
		add_generator(delay);
		
		prob = new Sim_random_obj("Probabilidade");
		add_generator(prob);
	}
	
	public void body() {
		
		for (int i = 0; i < 100; i++) {	
			
			double p = prob.sample();
		
			if (p <= 0.15) { //15% ds pessoas vao pra fila preferencial
				sim_schedule(bilheteriaPreferencial, 0.0, 1);
				sim_pause(delay.sample());
				
			} else { //o resto vai para a fila geral
				sim_schedule(bilheteriaGeral, 0.0, 1);
				sim_pause(delay.sample());
			}
		}		
	}
}
