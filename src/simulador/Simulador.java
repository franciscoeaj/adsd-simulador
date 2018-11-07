package simulador;

import eduni.simjava.Sim_system;

public class Simulador {

    public static void main(String[] args) {

        Sim_system.initialise();
        
        Entrada entrada = new Entrada(Entidades.ENTRADA, 8D);
        Bilheteria bi_geral = new Bilheteria(Entidades.BILHETERIA_GERAL, 4, 1);
        Bilheteria bi_preferencial = new Bilheteria(Entidades.BILHETERIA_PREFERENCIAL, 6, 1);
        Arquibancada arq_geral = new Arquibancada(Entidades.ARQUIBANCADA_GERAL, 6);
        Arquibancada arq_cadeiras = new Arquibancada(Entidades.ARQUIBANCADA_CADEIRAS, 5);
        AreaConveniencia areaConveniencia = new AreaConveniencia(Entidades.AREA_CONVENIENCIA, 10, 3);
        Banheiro banheiro_M = new Banheiro(Entidades.BANHEIRO_MASCULINO, 5, 2);
        Banheiro banheiro_F = new Banheiro(Entidades.BANHEIRO_FEMININO, 2, 1);
        Lanchonete lanchonete = new Lanchonete(Entidades.LANCHONETE, 3, 2);
        Saida saida = new Saida(Entidades.SAIDA, 8);
        
        Sim_system.link_ports(Entidades.ENTRADA.getNome(), Entidades.BILHETERIA_GERAL.getNome(), Entidades.BILHETERIA_GERAL.getNome(), Entidades.ENTRADA.getNome());
        Sim_system.link_ports(Entidades.ENTRADA.getNome(), Entidades.BILHETERIA_PREFERENCIAL.getNome(), Entidades.BILHETERIA_PREFERENCIAL.getNome(), Entidades.ENTRADA.getNome());
        
        Sim_system.link_ports(Entidades.BILHETERIA_GERAL.getNome(), Entidades.AREA_CONVENIENCIA.getNome(), Entidades.AREA_CONVENIENCIA.getNome(), Entidades.ENTRADA.getNome());
        Sim_system.link_ports(Entidades.BILHETERIA_PREFERENCIAL.getNome(), Entidades.AREA_CONVENIENCIA.getNome(), Entidades.AREA_CONVENIENCIA.getNome(), Entidades.ENTRADA.getNome());
        	
        Sim_system.link_ports(Entidades.ARQUIBANCADA_GERAL.getNome(), Entidades.SAIDA.getNome(), Entidades.SAIDA.getNome(), Entidades.ENTRADA.getNome());
        Sim_system.link_ports(Entidades.ARQUIBANCADA_CADEIRAS.getNome(), Entidades.SAIDA.getNome(), Entidades.SAIDA.getNome(), Entidades.ENTRADA.getNome());
        
        Sim_system.link_ports(Entidades.AREA_CONVENIENCIA.getNome(), Entidades.LANCHONETE.getNome(), Entidades.LANCHONETE.getNome(), Entidades.ENTRADA.getNome());
        Sim_system.link_ports(Entidades.AREA_CONVENIENCIA.getNome(), Entidades.BANHEIRO_MASCULINO.getNome(), Entidades.BANHEIRO_MASCULINO.getNome(), Entidades.ENTRADA.getNome());
        Sim_system.link_ports(Entidades.AREA_CONVENIENCIA.getNome(), Entidades.BANHEIRO_FEMININO.getNome(), Entidades.BANHEIRO_FEMININO.getNome(), Entidades.ENTRADA.getNome());
        
        Sim_system.link_ports(Entidades.BANHEIRO_MASCULINO.getNome(), Entidades.ARQUIBANCADA_GERAL.getNome(), Entidades.ARQUIBANCADA_GERAL.getNome(), Entidades.ENTRADA.getNome());
        Sim_system.link_ports(Entidades.BANHEIRO_MASCULINO.getNome(), Entidades.ARQUIBANCADA_CADEIRAS.getNome(), Entidades.ARQUIBANCADA_CADEIRAS.getNome(), Entidades.ENTRADA.getNome());
        
        Sim_system.link_ports(Entidades.BANHEIRO_FEMININO.getNome(), Entidades.ARQUIBANCADA_GERAL.getNome(), Entidades.ARQUIBANCADA_GERAL.getNome(), Entidades.ENTRADA.getNome());
        Sim_system.link_ports(Entidades.BANHEIRO_FEMININO.getNome(), Entidades.ARQUIBANCADA_CADEIRAS.getNome(), Entidades.ARQUIBANCADA_CADEIRAS.getNome(), Entidades.ENTRADA.getNome());
        
        Sim_system.link_ports(Entidades.LANCHONETE.getNome(), Entidades.ARQUIBANCADA_GERAL.getNome(), Entidades.ARQUIBANCADA_GERAL.getNome(), Entidades.ENTRADA.getNome());
        Sim_system.link_ports(Entidades.LANCHONETE.getNome(), Entidades.ARQUIBANCADA_CADEIRAS.getNome(), Entidades.ARQUIBANCADA_CADEIRAS.getNome(), Entidades.ENTRADA.getNome());
        
        Sim_system.set_trace_detail(false, true, false);
        
        Sim_system.run();
    }
}
