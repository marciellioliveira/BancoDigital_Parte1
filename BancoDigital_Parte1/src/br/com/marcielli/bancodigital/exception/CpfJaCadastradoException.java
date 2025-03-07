package br.com.marcielli.bancodigital.exception;

public class CpfJaCadastradoException extends Exception {

	/**
	 * Para saber se o CPF que o usuário está tentando cadastrar já existe no sistema
	 */
	private static final long serialVersionUID = 1L;
	
	public CpfJaCadastradoException(String msg) {
		super(msg);
	}

}
