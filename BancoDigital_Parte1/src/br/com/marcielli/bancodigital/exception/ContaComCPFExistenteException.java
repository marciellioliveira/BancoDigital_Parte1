package br.com.marcielli.bancodigital.exception;

public class ContaComCPFExistenteException extends Exception {

	/**
	 * Para saber se já existe uma conta com esse CPF
	 */
	private static final long serialVersionUID = 1L;
	
	public ContaComCPFExistenteException(String msg) {
		super(msg);
	}

}
