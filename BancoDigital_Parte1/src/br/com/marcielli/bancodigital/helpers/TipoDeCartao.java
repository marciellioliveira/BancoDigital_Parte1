package br.com.marcielli.bancodigital.helpers;

public enum TipoDeCartao {

	CARTAO_DE_CREDITO(1, "Cartão de Crédito"),
	CARTAO_DE_DEBITO(2, "Cartão de Débito");
	
	private final int codigoDoTipoDeCartao;
	private final String descricaoDoTipoDeCartao;
	
	private TipoDeCartao(int codigoDoTipoDeCartao, String descricaoDoTipoDeCartao) {
		this.codigoDoTipoDeCartao = codigoDoTipoDeCartao;
		this.descricaoDoTipoDeCartao = descricaoDoTipoDeCartao;
	}

	public int getCodigoDoTipoDeCartao() {
		return codigoDoTipoDeCartao;
	}

	public String getDescricaoDoTipoDeCartao() {
		return descricaoDoTipoDeCartao;
	}	
}
