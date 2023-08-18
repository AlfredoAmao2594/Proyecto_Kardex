package com.example.kardex.kardex.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kardex.kardex.Repository.ProveedorRepository;
import com.example.kardex.kardex.model.Proveedor;

@Service
public class ProveedorServices {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> listAll(String palabraClave){
        if(palabraClave != null){
            return proveedorRepository.findAll(palabraClave);
        }
        return proveedorRepository.findAll();
    }
    
}
