package com.example.kardex.kardex.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Proveedor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_Tipo_Documento")
    private Integer idTipoDocumento;

    @Column(name = "numero_Documento")
    private String numeroDocumento;

    @Column(name = "razon_Social")
    private String razonSocial;
    private String pais;
    private String ciudad;
    private String direccion;

    Proveedor(){}

    public Proveedor(Integer idTipoDocumento,String numeroDocumento,String razonSocial,
    String pais, String ciudad, String direccion){
        this.idTipoDocumento = idTipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.razonSocial = razonSocial;
        this.pais = pais;
        this.ciudad = ciudad;
        this.direccion = direccion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
}
