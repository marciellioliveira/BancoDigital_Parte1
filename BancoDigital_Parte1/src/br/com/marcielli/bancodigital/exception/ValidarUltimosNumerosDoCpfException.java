package br.com.marcielli.bancodigital.exception;

public class ValidarUltimosNumerosDoCpfException extends Exception {
	
	/**
	 * Retorna uma mensagem customizada exibindo o erro para o usuário.
	 */
	
	private static final long serialVersionUID = 1L;

	public ValidarUltimosNumerosDoCpfException(String msg) {
		super(msg);
	}

}


