package com.pruebaTecnica.bancoUnion.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pruebaTecnica.bancoUnion.models.dto.Cliente;
import com.pruebaTecnica.bancoUnion.models.dto.Factura;
import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="clientes")
public class ClienteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=false)
	private String nombre;

	@Column(nullable=false)
	private String apellido;

	@Column(nullable=false, unique=true)
	private String email;

	@Column(name = "fecha_registro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro;
	@JsonIgnoreProperties({"cliente", "hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<FacturaEntity> facturas;

	public ClienteEntity(Long id, String nombre, String apellido, String email, Date fechaRegistro,
						 List<FacturaEntity> facturas) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaRegistro = fechaRegistro;
		this.facturas = facturas;
	}

	@PrePersist
	public void prePersist() {
		this.fechaRegistro = new Date();
	}

	public ClienteEntity(Long id) {
		this.id = id;
	}

	public ClienteEntity() {
		this.facturas = new ArrayList<>();
	}

	public List<FacturaEntity> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<FacturaEntity> facturas) {
		this.facturas = facturas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public static ClienteEntity fromDomainModelID(Cliente cliente) {
		if (cliente == null) {
			return null;
		}
		return new ClienteEntity(cliente.getId());
	}

	public static ClienteEntity fromDomainModel(Cliente cliente) {
		if (cliente == null) {
			return null;
		}
		List<FacturaEntity> listFacturas = new ArrayList<>();
		if(cliente.getFacturas() != null && !cliente.getFacturas().isEmpty()) {
			listFacturas = FacturaEntity.fromListDomainModel(cliente.getFacturas());
		}

		return new ClienteEntity(
				cliente.getId(),
				cliente.getNombre(),
				cliente.getApellido(),
				cliente.getEmail(),
				cliente.getFechaRegistro(),
				listFacturas
		);
	}


	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
		Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
		if (thisEffectiveClass != oEffectiveClass) return false;
		ClienteEntity that = (ClienteEntity) o;
		return getId() != null && Objects.equals(getId(), that.getId());
	}

	@Override
	public final int hashCode() {
		return getClass().hashCode();
	}

	public Cliente toDomainModelID() {
		if (id == null || id <= 0L) {
			return null;
		}
		return new Cliente(id, nombre, apellido, email, fechaRegistro, null);
	}

	public Cliente toDomainModel() {
		if (id == null || id <= 0L) {
			return null;
		}
		List<Factura> listFacturas = Factura.fromListToDomainModel(facturas);
		return new Cliente(id, nombre, apellido, email, fechaRegistro, listFacturas);
	}

}
