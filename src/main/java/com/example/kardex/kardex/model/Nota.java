package com.example.kardex.kardex.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fecha;
    @Column(name = "tipo_Nota")
    private String tipoNota;
    @Column(name = "id_Proveedor")
    private Integer idProveedor;
    @Column(name = "id_Tipo_Comprobante")
    private Integer idTipoComprobante;
    @Column(name = "id_Cliente")
    private Integer idCliente;
    @Column(name = "id_Producto")
    private Integer idProducto;
    private Integer cantidad;
    @Column(name = "precio_Unitario")
    private double precio;
    @Column(name= "importe_Total")
    private double impTotal;
    
    public Nota(){}

    public Nota(String fecha, String tipoNota, Integer idProveedor, Integer idTipoComprobante,
            Integer idCliente, Integer idProducto, Integer cantidad, double precio, double impTotal) {
        this.fecha = fecha;
        this.tipoNota = tipoNota;
        this.idProveedor = idProveedor;
        this.idTipoComprobante = idTipoComprobante;
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.impTotal = impTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipoNota() {
        return tipoNota;
    }

    public void setTipoNota(String tipoNota) {
        this.tipoNota = tipoNota;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(Integer idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getImpTotal() {
        return impTotal;
    }

    public void setImpTotal(double impTotal) {
        this.impTotal = impTotal;
    }

    
    
}
