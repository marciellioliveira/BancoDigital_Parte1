package br.com.marcielli.bancodigital.exception;

public class TamanhoDoCpfException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private int tamanhoCpfSemPontuacao;
	
	public TamanhoDoCpfException(String msg) {
		super(msg);
		this.tamanhoCpfSemPontuacao = 11;	
	}	
	
	public int getTamanhoCpfSemPontuacao() {
		return tamanhoCpfSemPontuacao;
	}	
}
