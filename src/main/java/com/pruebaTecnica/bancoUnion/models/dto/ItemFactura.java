package com.pruebaTecnica.bancoUnion.models.dto;

import com.pruebaTecnica.bancoUnion.models.entities.ItemFacturaEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    public static List<ItemFactura> fromListToDomainModel(List<ItemFacturaEntity> listItemFacturas) {
        return new ArrayList<ItemFactura>() {{
            for (ItemFacturaEntity fc : listItemFacturas) {
                if(fc != null) {
                    this.add(fc.toDomainModel());
                }
            }
        }};
    }
}