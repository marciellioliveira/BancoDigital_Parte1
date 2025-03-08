package br.com.marcielli.bancodigital.helpers;

public enum CategoriasDeConta {

	COMUM(1, "Categoria Comum"),
	SUPER(2, "Categoria Super"),
	PREMIUM(3, "Categoria Premium");
	
	private final int codigoDaCategoria;
	private final String tipoDaCategoria;
	
	private CategoriasDeConta(int codigoDaCategoria, String tipoDaCategoria) {
		this.codigoDaCategoria = codigoDaCategoria;
		this.tipoDaCategoria = tipoDaCategoria;
	}

	public int getCodigoDaCategoria() {
		return codigoDaCategoria;
	}

	public String getTipoDaCategoria() {
		return tipoDaCategoria;
	}
}
