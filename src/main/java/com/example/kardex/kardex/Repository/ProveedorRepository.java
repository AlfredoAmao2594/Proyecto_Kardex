package com.example.kardex.kardex.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.kardex.kardex.model.Proveedor;


public interface ProveedorRepository extends CrudRepository<Proveedor,Integer> {
    
    List<Proveedor> findAll();

    @Query(value ="""
            Select * from proveedor p where concat(p.id_Tipo_Documento,p.numero_Documento,
            p.razon_Social,p.pais,p.ciudad,p.direccion) LIKE %?1%
            """,nativeQuery = true)
    public List<Proveedor> findAll(String palabraClave);

}
