package com.example.kardex.kardex.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.example.kardex.kardex.DTO.ReporteDTO;
import com.example.kardex.kardex.model.Nota;


public interface NotaRepository extends CrudRepository<Nota,Integer> {
    
    List<Nota> findAll();

    @Query(value = """
      select n.id_producto as idProducto, 
      p.detalle as detalleProducto,
      sum(n.cantidad) as cantidad,
      sum(n.importe_total) as importeTotal,
      sum(n.importe_total)/ sum(n.cantidad) as precio
      from nota n inner join producto p on n.id_producto = p.id
      group by n.id_Producto , p.detalle
      """    
        , nativeQuery = true)
    List<ReporteDTO> obtenerResumenNotas();

    @Query(value ="""
      select n.id_producto as idProducto, 
      p.detalle as detalleProducto,
      sum(n.cantidad) as cantidad,
      sum(n.importe_total) as importeTotal,
      sum(n.importe_total)/ sum(n.cantidad) as precio
      from nota n inner join producto p on n.id_producto = p.id where p.detalle LIKE %?1%
      
        """,nativeQuery = true)
        public List<ReporteDTO> findAll(String palabraClave);


    

    /*@Query(value="""
      SELECT n.id, n.fecha, n.tipo_Nota, p.razon_Social, t.descripcion, c.razon_Social, pr.detalle, n.cantidad, n.precio_Unitario, n.importe_Total
      FROM nota n
      LEFT JOIN proveedor p ON n.id_Proveedor = p.id
      LEFT JOIN tipo_comprobante t ON n.id_Tipo_Comprobante = t.id
      LEFT JOIN cliente c ON n.id_Cliente = c.id
      LEFT JOIN producto pr ON n.id_Producto = pr.id;   
      """
    ,nativeQuery =true )
    List<NotaReporteDTO> reporteNotas();*/
}
