package simulador;

import eduni.simjava.Sim_system;

public class Simulacao {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		Sim_system.initialise();
		
		Entrada entrada = new Entrada("Entrada", 8);	
		Bilheteria bilheteria = new Bilheteria("Bilheteria", 3, 1);		
		ArquibancadaCadeiras cadeiras = new ArquibancadaCadeiras("ArquibancadaCadeiras", 2);		
		ArquibancadaGeral geral = new ArquibancadaGeral("ArquibancadaGeral", 6);		
		AreaConveniencia areaConveniencia = new AreaConveniencia("AreaDeConveniencia", 3, 1);		
		Lanchonete lanchonete = new Lanchonete ("Lanchonete", 10, 5);		
		Banheiro banheiro1 = new Banheiro("BanheiroMasculino", 3, 1);
		Banheiro banheiro2 = new Banheiro("BanheiroFeminino", 2, 1);		
		Saida saida = new Saida("Saida", 8);
		
		Sim_system.link_ports("Entrada", "Saida", "Bilheteria", "Entrada");
		
		Sim_system.link_ports("Bilheteria", "Arquibancada Cadeiras", "ArquibancadaCadeiras", "Entrada");
		Sim_system.link_ports("Bilheteria", "Arquibancada Geral", "ArquibancadaGeral", "Entrada");
		
		Sim_system.link_ports("ArquibancadaCadeiras", "Saida", "Saida", "In"); 
		Sim_system.link_ports("ArquibancadaGeral", "Saida", "Saida", "In"); 
		
		Sim_system.link_ports("Bilheteria", "Area de Conveniencia", "AreaDeConveniencia", "Entrada");

		Sim_system.link_ports("AreaDeConveniencia", "Lanchonete", "Lanchonete", "Entrada"); 

		Sim_system.link_ports("AreaDeConveniencia", "Banheiro 1", "BanheiroMasculino", "Entrada"); 
		Sim_system.link_ports("AreaDeConveniencia", "Banheiro 2", "BanheiroFeminino", "Entrada"); 
		
		Sim_system.link_ports("Lanchonete", "Arquibancada Geral", "ArquibancadaGeral", "Entrada");
		Sim_system.link_ports("Lanchonete", "Arquibancada Cadeiras", "ArquibancadaCadeiras", "Entrada");
		
		Sim_system.link_ports("BanheiroMasculino", "Arquibancada Geral", "ArquibancadaGeral", "Entrada");
		Sim_system.link_ports("BanheiroMasculino", "Arquibancada Cadeiras", "ArquibancadaCadeiras", "Entrada");
		
		Sim_system.link_ports("BanheiroFeminino", "Arquibancada Geral", "ArquibancadaGeral", "Entrada");
		Sim_system.link_ports("BanheiroFeminino", "Arquibancada Cadeiras", "ArquibancadaCadeiras", "Entrada");
		
		//Configura o rastreio para o simulador (default, entity, event)
		Sim_system.set_trace_detail(false, true, false);
				
		Sim_system.run();		
	}
}
