package com.example.kardex.kardex.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.kardex.kardex.model.Producto;

public interface ProductoRepository extends CrudRepository<Producto,Integer> {
    
    List<Producto> findAll();
    
    @Query(value ="""
            Select * from producto p where concat(p.detalle,p.marca) LIKE %?1%
            """,nativeQuery = true)
    public List<Producto> findAll(String palabraClave);

    
    
    
}
