package com.pruebaTecnica.bancoUnion.web.validation;

public final class ValidationErrorDto {
	private final String campo;
	private final String mensaje;
	private final String valor;

	public String getCampo() {
		return campo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public String getValor() {
		return valor;
	}

	private ValidationErrorDto(String campo, String mensaje, String valor) {
		this.campo = campo;
		this.mensaje = mensaje;
		this.valor = valor;
	}

	public static ValidationErrorDto newInstanceValidationErrorDto(String campo, String mensaje, String valor) {
		return new ValidationErrorDto(
				campo,
				mensaje,
				valor);
	}
}
