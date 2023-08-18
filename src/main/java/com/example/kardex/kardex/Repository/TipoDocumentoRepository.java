package com.example.kardex.kardex.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.kardex.kardex.model.TipoDocumento;


public interface TipoDocumentoRepository extends CrudRepository<TipoDocumento,Integer>{
    
    List<TipoDocumento> findAll();
}
