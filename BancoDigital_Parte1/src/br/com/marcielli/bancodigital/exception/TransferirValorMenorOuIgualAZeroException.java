package br.com.marcielli.bancodigital.exception;

public class TransferirValorMenorOuIgualAZeroException extends Exception {

	private static final long serialVersionUID = 1L;

	public TransferirValorMenorOuIgualAZeroException(String msg) {
		super(msg);
	}
}
