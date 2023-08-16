package com.pruebaTecnica.bancoUnion.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pruebaTecnica.bancoUnion.models.dto.Cliente;
import com.pruebaTecnica.bancoUnion.models.dto.Factura;
import com.pruebaTecnica.bancoUnion.models.dto.ItemFactura;
import jakarta.persistence.*;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "facturas_items")
public class ItemFacturaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private ProductoEntity producto;

    public ItemFacturaEntity() {
    }

    public ItemFacturaEntity(Long id, Integer cantidad, ProductoEntity producto) {
        this.id = id;
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getImporte() {
        return cantidad.doubleValue() * producto.getPrecio();
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public static List<ItemFacturaEntity> fromListDomainModel(List<ItemFactura> listItemFacturas) {
        return new ArrayList<ItemFacturaEntity>() {{
            for (ItemFactura fc : listItemFacturas) {
                if(fc != null) {
                    this.add(ItemFacturaEntity.fromDomainModel(fc));
                }
            }
        }};
    }

    public static ItemFacturaEntity fromDomainModel(ItemFactura itemFactura) {
        if (itemFactura == null) {
            return null;
        }
        return new ItemFacturaEntity(
                itemFactura.getId(),
                itemFactura.getCantidad(),
                ProductoEntity.fromDomainModel(itemFactura.getProducto())
                );
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

    public ItemFactura toDomainModel() {
        if (id == null || id <= 0L) {
            return null;
        }
        return new ItemFactura(id, cantidad, producto.toDomainModel());
    }
}