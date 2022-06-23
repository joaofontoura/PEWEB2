/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.JPA.models.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author joaoc
 */
@Scope("session")
@Component
@Entity
@Table(name = "tb_venda")
public class Venda implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private LocalDate data;
    
    @ManyToOne
    @JoinColumn(name = "id_pagamento")
    private Pagamento pagamento;

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
    
    @OneToMany(mappedBy = "venda", cascade = CascadeType.PERSIST)
    private List<Item> itens = new ArrayList();   
    
    @OneToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;
        
    public double total(){
        double resultado = 0;
        for (Item i:itens){
            resultado+= i.total();
        }
        return resultado;
    }
    
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClientePF clientePF;

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }  

    public ClientePF getClientePF() {
        return clientePF;
    }

    public void setClientePF(ClientePF clientePF) {
        this.clientePF = clientePF;
    }   
}