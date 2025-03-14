package br.com.marcielli.bancodigital.helpers;

public enum TiposDeConta {
	
	CONTA_CORRENTE(1,"Conta Corrente"),
	CONTA_POUPANCA(2, "Conta Poupança"),
	CONTA_CADASTRO(3, "Conta Cadastro");
	
	private final int codigoTipoDeConta;
	private final String descricaoDaConta;
	
	TiposDeConta(int codigoTipoDeConta, String descricaoDaConta) {
		this.codigoTipoDeConta = codigoTipoDeConta;
		this.descricaoDaConta = descricaoDaConta;
	}

	public int getCodigoTipoDeConta() {
		return codigoTipoDeConta;
	}

	public String getDescricaoDaConta() {
		return descricaoDaConta;
	}
}
