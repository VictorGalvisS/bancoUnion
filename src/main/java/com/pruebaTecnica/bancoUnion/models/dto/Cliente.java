package com.pruebaTecnica.bancoUnion.models.dto;

import com.pruebaTecnica.bancoUnion.models.entities.ClienteEntity;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;


	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	@Parameter(required=true)
	private final Long id;

	@NotEmpty(message ="no puede estar vacio")
	@Size(min=4, max=12, message="el tamaño tiene que estar entre 4 y 12")
	@Parameter(required=true)
	private final String nombre;

	@NotEmpty(message ="no puede estar vacio")
	@Parameter(required=true)
	private final String apellido;

	@NotEmpty(message ="no puede estar vacio")
	@Parameter(required=true)
	private final String email;

	@Parameter(required=false)
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private final Date fechaRegistro;


	@Parameter(required=false)
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
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

	public static List<Cliente> fromListToDomainModel(List<ClienteEntity> listClientesEntity) {
		if(listClientesEntity == null || listClientesEntity.isEmpty()) {
			return new ArrayList<>();
		}
		return new ArrayList<Cliente>() {{
			for (ClienteEntity fc : listClientesEntity) {
				if(fc != null) {
					this.add(fc.toDomainModel());
				}
			}
		}};
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
