package com.example.kardex.kardex.DTO;

import java.math.BigDecimal;

public interface ReporteDTO {
    
    public Integer getIdProducto(); 
    public String getDetalleProducto();
    public BigDecimal getCantidad();
    public BigDecimal getImporteTotal();
    public BigDecimal getPrecio();

}
