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
    
    @OneToMany(mappedBy = "venda", cascade = CascadeType.PERSIST)
    private List<Item> itens = new ArrayList();   
        
    public double total(){
        double resultado = 0;
        for (Item i:itens){
            resultado+= i.total();
        }
        return resultado;
    }
    
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
