package com.pruebaTecnica.bancoUnion.web.validation.excepcion;

public class ValidationDataException extends Exception {

	private static final long serialVersionUID = 1L;

	  public ValidationDataException() {
	    super();
	  }

	  public ValidationDataException(String message) {
	    super(message);
	  }

	  public ValidationDataException(String message, Throwable cause) {
	    super(message, cause);
	  }

	  public ValidationDataException(Throwable cause) {
	    super(cause);
	  }

}


