package com.example.kardex.kardex.DTO;


public class NotaDTO {
    
    String fecha;
    String tipoNota;
    Integer idProveedor;
    Integer idTipoComprobante;
    Integer idCliente;
    Integer idProducto;
    Integer cantidad;
    double precio;
    double impTotal;


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
