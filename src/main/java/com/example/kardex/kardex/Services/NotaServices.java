package com.example.kardex.kardex.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kardex.kardex.DTO.ReporteDTO;
import com.example.kardex.kardex.Repository.NotaRepository;


@Service
public class NotaServices {
   
    @Autowired
    private NotaRepository notaRepository;

    public List<ReporteDTO> listAll(String palabraClave){
        if(palabraClave != null){
            return notaRepository.findAll(palabraClave);
        }
        return notaRepository.obtenerResumenNotas();
    }
}
