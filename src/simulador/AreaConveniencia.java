package simulador;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_stat;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_normal_obj;
import eduni.simjava.distributions.Sim_random_obj;

public class AreaConveniencia extends Sim_entity {

	private Sim_port entrada, loja, lanchonete, banheiro1, banheiro2;
	private Sim_normal_obj delay;
	private Sim_random_obj prob;
	
	//Objeto para as medidas de estatisticas
	private Sim_stat stat;
	
	AreaConveniencia (String nome, double media, double variancia) {
		
		super(nome);
		
		//Criando porta de entrada
		entrada = new Sim_port("Entrada");
		
		//Cria demais portas
//		loja = new Sim_port("Loja");
		lanchonete = new Sim_port("Lanchonete");
		banheiro1 = new Sim_port("Banheiro 1");
		banheiro2 = new Sim_port("Banheiro 2");
		
		//Adicionando portas para Evento
		add_port(entrada);
//		add_port(loja);
		add_port(lanchonete);
//		add_port(bar);
		add_port(banheiro1);
		add_port(banheiro2);
		
		//Gerando distribuilcao de probabilidade
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
			
			//Cria o evento
			Sim_event e = new Sim_event();
			
			//Pega o proximo evento
			sim_get_next(e);
			
			//Processa o evento com a amostra da distribuicao
			sim_process(delay.sample());
			
			//Completa a execução do evento
			sim_completed(e);
			
			double p = prob.sample();
			
			if (p <= 0.30) {
				sim_trace(1, "Pessoa vai para a lanchonete");
				sim_schedule(lanchonete, 0.0, 1);
				
			} else if (p <= 0.85){
				sim_trace(1, "Pessoa vai para o banheiro masculino");
				sim_schedule(banheiro1, 0.0, 1);
			
			} else {
				sim_trace(1, "Pessoa vai para o banheiro feminino");
				sim_schedule(banheiro2, 0.0, 1);
			}
			
		}
	}

}
