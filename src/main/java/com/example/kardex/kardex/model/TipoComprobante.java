package com.example.kardex.kardex.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TipoComprobante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Integer id;
    String descripcion;

    TipoComprobante(){}

    TipoComprobante(Integer id, String descripcion){
        this.id = id;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
