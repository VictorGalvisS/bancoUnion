package com.pruebaTecnica.bancoUnion.models.dto;
import com.pruebaTecnica.bancoUnion.models.entities.FacturaEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Factura implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Long id;
    private final String descripcion;
    private final String observacion;
    private final Date fechaRegistro;
    private final Cliente cliente;
    private final List<ItemFactura> items;

    public Factura(Long id, String descripcion, String observacion, Date fechaRegistro, Cliente cliente,
                   List<ItemFactura> items) {
        this.id = id;
        this.descripcion = descripcion;
        this.observacion = observacion;
        this.fechaRegistro = fechaRegistro;
        this.cliente = cliente;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemFactura> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", observacion='" + observacion + '\'' +
                ", createAt=" + fechaRegistro +
                ", cliente=" + cliente +
                ", items=" + items +
                '}';
    }

    public static List<Factura> fromListToDomainModel(List<FacturaEntity> listFacturas) {
        return new ArrayList<Factura>() {{
            for (FacturaEntity fc : listFacturas) {
                if(fc != null) {
                    this.add(fc.toDomainModel());
                }
            }
        }};
    }
}