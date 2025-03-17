package br.com.marcielli.bancodigital.helpers;

public enum OperacaoBancaria {

	PIX(1, "PIX", "Funciona 24 horas por dia e sete dias por semana, inclusive em feriados. Não há cobrança de taxa por transação para pessoas físicas e Microempreendedores Individuais (MEIs)."),
	TED(2, "TED", "Transferência Eletrônica Disponível, que permite transferências entre contas de diferentes bancos. Se realizada até às 17h, o dinheiro entra na conta no mesmo dia, caso contrário, o dinheiro só entra no próximo dia útil."),
	DOC(3, "DOC", "Permite a movimentação de valores diferentes, em horários distintos. O dinheiro cai na conta no dia útil seguinte ou em até 2 dias úteis em caso de finais de semana, feriados ou realizado após às 22h.");
	
	
	private final int codigoOperacaoBancaria;
	private final String tipoDeOperacaoBancaria;
	private final String sobreOperacaoBancaria;
	
	private OperacaoBancaria(int codigoOperacaoBancaria, String tipoDeOperacaoBancaria, String sobreOperacaoBancaria) {
		this.codigoOperacaoBancaria = codigoOperacaoBancaria;
		this.tipoDeOperacaoBancaria = tipoDeOperacaoBancaria;
		this.sobreOperacaoBancaria = sobreOperacaoBancaria;
	}

	public int getCodigoOperacaoBancaria() {
		return codigoOperacaoBancaria;
	}

	public String getTipoDeOperacaoBancaria() {
		return tipoDeOperacaoBancaria;
	}

	public String getSobreOperacaoBancaria() {
		return sobreOperacaoBancaria;
	}	
}
