package com.example.kardex.kardex.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kardex.kardex.Repository.ClienteRepository;
import com.example.kardex.kardex.model.Cliente;

@Service
public class ClienteServices {
 
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listAll(String palabraClave){
        if(palabraClave != null){
            return clienteRepository.findAll(palabraClave);
        }
        return clienteRepository.findAll();
    }
}
