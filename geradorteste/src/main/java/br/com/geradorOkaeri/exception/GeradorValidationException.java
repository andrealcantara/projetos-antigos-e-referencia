package br.com.geradorOkaeri.exception;

/**
 * Classe que representa a {@link Exception} para validacao do gerador.
 * @author andre
 *
 */
public class GeradorValidationException extends Exception {
	private static final long serialVersionUID = 7438736311097522435L;

	public GeradorValidationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public GeradorValidationException(String arg0) {
		super(arg0);
	}

	public GeradorValidationException(Throwable arg0) {
		super(arg0);
	}

	public GeradorValidationException() {
		super();
	}
	
	
}
