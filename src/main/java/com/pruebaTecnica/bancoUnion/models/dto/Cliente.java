package com.pruebaTecnica.bancoUnion.models.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;


	private final Long id;

	@NotEmpty(message ="no puede estar vacio")
	@Size(min=4, max=12, message="el tamaño tiene que estar entre 4 y 12")
	private final String nombre;

	@NotEmpty(message ="no puede estar vacio")
	private final String apellido;


	@NotEmpty(message ="no puede estar vacio")
	private final String email;

	@Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$", message = "El formato de fecha debe ser dd/MM/yyyy")
	private final Date fechaRegistro;

	@Size(min = 1, message = "Debe ingresar una o mas edades")
	private final List<Factura> facturas;

	public Cliente(Long id, String nombre, String apellido, String email, Date fechaRegistro, List<Factura> facturas) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaRegistro = fechaRegistro;
		this.facturas = facturas;
	}
	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getEmail() {
		return email;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	@Override
	public String toString() {
		return "Cliente{" +
				"id=" + id +
				", nombre='" + nombre + '\'' +
				", apellido='" + apellido + '\'' +
				", email='" + email + '\'' +
				", createAt=" + fechaRegistro +
				", facturas=" + facturas +
				'}';
	}

	public static Cliente newInstanceClienteUpdate(Cliente clienteActual, Cliente cliente) {
		if (cliente == null ) {
			return null;
		}
		return new Cliente(
				clienteActual.getId(),
				cliente.getNombre(),
				cliente.getApellido(),
				cliente.getEmail(),
				cliente.getFechaRegistro(),
				clienteActual.getFacturas()
				);
	}
}
