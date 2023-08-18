package com.example.kardex.kardex.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.kardex.kardex.model.Cliente;


public interface ClienteRepository extends CrudRepository<Cliente,Integer>{
    
    List<Cliente> findAll();

    @Query(value ="""
            Select * from cliente c where concat(c.id_Tipo_Documento,c.numero_Documento,
            c.razon_Social,c.pais,c.ciudad,c.direccion) LIKE %?1%
            """,nativeQuery = true)
    public List<Cliente> findAll(String palabraClave);
}
