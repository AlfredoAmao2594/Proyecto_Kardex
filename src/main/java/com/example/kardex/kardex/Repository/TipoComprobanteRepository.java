package com.example.kardex.kardex.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.kardex.kardex.model.TipoComprobante;

public interface TipoComprobanteRepository extends CrudRepository<TipoComprobante,Integer>{
    
    List<TipoComprobante> findAll();
}
