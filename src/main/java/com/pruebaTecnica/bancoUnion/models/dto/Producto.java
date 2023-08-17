package com.pruebaTecnica.bancoUnion.models.dto;

import java.io.Serializable;
import java.util.Date;

public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long id;
    private final String nombre;
    private final Double precio;
    private final Date fechaRegistro;

    public Producto(Long id, String nombre, Double precio, Date fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }
}
