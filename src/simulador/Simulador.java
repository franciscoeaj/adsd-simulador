package simulador;

import eduni.simjava.Sim_system;

public class Simulador {

    public static void main(String[] args) {

        Sim_system.initialise();
        
        Entrada entrada = new Entrada("Entrada", 8);
        Bilheteria bi_geral = new Bilheteria("Bilheteria geral", 4, 1);
        Bilheteria bi_preferencial = new Bilheteria("Bilheteria preferencial", 6, 1);
        Arquibancada arq_geral = new Arquibancada("Arquibancada geral", 6);
        Arquibancada arq_cadeiras = new Arquibancada("Cadeiras cativas", 5);
        AreaConveniencia areaConveniencia = new AreaConveniencia("Area de conveniencia", 10, 3);
        Banheiro banheiro_M = new Banheiro("Banheiro masculino", 5, 2);
        Banheiro banheiro_F = new Banheiro("Banheiro feminino", 2, 1);
        Lanchonete lanchonete = new Lanchonete("Lanchonete", 3, 2);
        Saida saida = new Saida("Saida", 8);
        
        Sim_system.link_ports("Entrada", "Bilheteria geral", "Bilheteria geral", "Entrada");
        Sim_system.link_ports("Entrada", "Bilheteria preferencial", "Bilheteria preferencial", "Entrada");
        
        Sim_system.link_ports("Bilheteria geral", "Area de conveniencia", "Area de conveniencia", "Entrada");
        Sim_system.link_ports("Bilheteria preferencial", "Area de conveniencia", "Area de conveniencia", "Entrada");
        	
        Sim_system.link_ports("Arquibancada geral", "Saida", "Saida", "Entrada");
        Sim_system.link_ports("Arquibancada preferencial", "Saida", "Saida", "Entrada");
        
        Sim_system.link_ports("Area de conveniencia", "Lanchonete", "Lanchonete", "Entrada");
        Sim_system.link_ports("Area de conveniencia", "Banheiro masculino", "Banheiro masculino", "Entrada");
        Sim_system.link_ports("Area de conveniencia", "Banheiro femininino", "Banheiro feminino", "Entrada");
        
        Sim_system.link_ports("Banheiro masculino", "Geral", "Arquibancada geral", "Entrada");
        Sim_system.link_ports("Banheiro masculino", "Cadeiras", "Cadeiras cativas", "Entrada");
        
        Sim_system.link_ports("Banheiro feminino", "Geral", "Arquibancada geral", "Entrada");
        Sim_system.link_ports("Banheiro feminino", "Cadeiras", "Cadeiras cativas", "Entrada");
        
        Sim_system.link_ports("Lanchonete", "Geral", "Arquibancada geral", "Entrada");
        Sim_system.link_ports("Lanchonete", "Cadeiras", "Cadeiras cativas", "Entrada");
        
        Sim_system.set_trace_detail(false, true, false);
        
        Sim_system.run();
    }
}
