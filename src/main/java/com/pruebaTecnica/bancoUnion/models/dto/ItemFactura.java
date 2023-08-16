package com.pruebaTecnica.bancoUnion.models.dto;

import java.io.Serializable;

public class ItemFactura implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Long id;
    private final Integer cantidad;
    private final Producto producto;

    public ItemFactura(Long id, Integer cantidad, Producto producto) {
        this.id = id;
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    @Override
    public String toString() {
        return "ItemFactura{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", producto=" + producto +
                '}';
    }
}