package com.tchemso.exception;

/**
 *
 * @author Galogbe Djessa Fernandaize
 */
public class GalogbeExceptions extends RuntimeException {

	public GalogbeExceptions(String message) {
		super(message);
		// getMessage(null, message, "Informations", JOptionPane.PLAIN_MESSAGE);
	}

	public GalogbeExceptions(Throwable cause) {
		super(cause);
	}

	public GalogbeExceptions(String message, Throwable cause) {
		super(message, cause);
	}

}
