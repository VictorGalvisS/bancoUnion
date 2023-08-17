package com.pruebaTecnica.bancoUnion.config.validation.dto;

public final class ValidationErrorDto {

	private final String id;
	private final String campo;
	private final String mensaje;
	private final String valor;

	public String getId() {
		return id;
	}
	public String getCampo() {
		return campo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public String getValor() {
		return valor;
	}

	private ValidationErrorDto(String id, String campo, String mensaje, String valor) {
		this.id = id;
		this.campo = campo;
		this.mensaje = mensaje;
		this.valor = valor;
	}

	public static ValidationErrorDto newInstanceValidationErrorDto(String id, String campo, String mensaje, String valor) {
		return new ValidationErrorDto(
				id,
				campo,
				mensaje,
				valor);
	}
}
