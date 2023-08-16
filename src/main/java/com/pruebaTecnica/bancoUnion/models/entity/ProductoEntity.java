package com.pruebaTecnica.bancoUnion.models.entity;

import com.pruebaTecnica.bancoUnion.models.dto.Cliente;
import com.pruebaTecnica.bancoUnion.models.dto.Factura;
import com.pruebaTecnica.bancoUnion.models.dto.Producto;
import jakarta.persistence.*;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "productos")
public class ProductoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Double precio;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    public ProductoEntity() {
    }

    @PrePersist
    public void prePersist() {
        this.fechaRegistro = new Date();
    }


    public ProductoEntity(Long id, String nombre, Double precio, Date fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaRegistro = fechaRegistro;
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public static ProductoEntity fromDomainModel(Producto producto) {
        if (producto == null) {
            return null;
        }
        return new ProductoEntity(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getFechaRegistro()
                );
    }

    public Producto toDomainModel() {
        if (id == null || id <= 0L) {
            return null;
        }
        return new Producto(id, nombre, precio, fechaRegistro);
    }
}
