package simulador;

public enum Entidades {
	
	AREA_CONVENIENCIA("AreaConveniencia"),
	ARQUIBANCADA_GERAL("ArquibancadaGeral"),
	ARQUIBANCADA_CADEIRAS("ArquibancadaCadeirasCativas"),
	BANHEIRO_FEMININO("BanheiroFeminino"),
	BANHEIRO_MASCULINO("BanheiroMasculino"),
	BILHETERIA_GERAL("BilheteriaGeral"),
	BILHETERIA_PREFERENCIAL("BilheteriaPreferencial"),
	ENTRADA("Entrada"),
	LANCHONETE("Lanchonete"),
	SAIDA("Saida");
	
	private String nome;
	
	private Entidades(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
}