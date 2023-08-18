package com.example.kardex.kardex.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kardex.kardex.Repository.ProductoRepository;
import com.example.kardex.kardex.model.Producto;

@Service
public class ProductoServices {
    
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listAll(String palabraClave){
        if(palabraClave != null){
            return productoRepository.findAll(palabraClave);
        }
        return productoRepository.findAll();
    }
}
