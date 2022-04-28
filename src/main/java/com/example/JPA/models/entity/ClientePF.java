/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JPA.models.entity;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author labbit-03
 */
@Entity
@DiscriminatorValue("F")
public class ClientePF extends Cliente implements Serializable{
    
    @NotBlank(message = "não pode estar vazio")
    private String nome;
    
    @CPF(message = "inválido")
    private String cpf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }   
}
