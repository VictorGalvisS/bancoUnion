package com.pruebaTecnica.bancoUnion.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pruebaTecnica.bancoUnion.models.dto.Factura;
import jakarta.persistence.*;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "facturas")
public class FacturaEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    private String observacion;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    @JsonIgnoreProperties(value = {"facturas", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private ClienteEntity cliente;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "factura_id")
    private List<ItemFacturaEntity> items;

    public FacturaEntity(Long id, String descripcion, String observacion, Date fechaRegistro,
                         ClienteEntity cliente, List<ItemFacturaEntity> items) {
        this.id = id;
        this.descripcion = descripcion;
        this.observacion = observacion;
        this.fechaRegistro = fechaRegistro;
        this.cliente = cliente;
        this.items = items;
    }

    public FacturaEntity() {
        items = new ArrayList<>();
    }

    @PrePersist
    public void prePersist() {
        this.fechaRegistro = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public List<ItemFacturaEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemFacturaEntity> items) {
        this.items = items;
    }

    public Double getTotal() {
        Double total = 0.00;
        for (ItemFacturaEntity item : items) {
            total += item.getImporte();
        }
        return total;
    }

    public static List<FacturaEntity> fromListDomainModel(List<Factura> facturas) {
        return new ArrayList<FacturaEntity>() {{
            for (Factura fc : facturas) {
                if(fc != null) {
                    this.add(FacturaEntity.fromDomainModel(fc));
                }
            }
        }};
    }

    public static FacturaEntity fromDomainModel(Factura factura) {
        if (factura == null) {
            return null;
        }
        return new FacturaEntity(
                factura.getId(),
                factura.getDescripcion(),
                factura.getObservacion(),
                factura.getFechaRegistro(),
                ClienteEntity.fromDomainModelID(factura.getCliente()),
                ItemFacturaEntity.fromListDomainModel(factura.getItems())
                );
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

    public Factura toDomainModel() {
        if (id == null || id <= 0L) {
            return null;
        }
        return new Factura(id, descripcion, observacion, fechaRegistro,
                cliente.toDomainModelID(), ItemFacturaEntity.fromListToDomainModel(items));
    }


}