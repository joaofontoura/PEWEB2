/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.JPA.models.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author joaoc
 */
@Entity
@Table(name = "tb_endereco")
public class Endereco implements Serializable{
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String bairro;
    private String logradouro;
    private String numero;
    private String cep;
    
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClientePF clientePF;
    
    @ManyToOne
    @JoinColumn(name = "id_municipio")
    private Municipio municipio;

    public ClientePF getClientePF() {
        return clientePF;
    }

    public void setClientePF(ClientePF clientePF) {
        this.clientePF = clientePF;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
