/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JPA.models.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 *
 * @author labbit-03
 */

@Table(name = "tb_cliente")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
@Entity
public abstract class Cliente implements Serializable{   
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;    
        
    @OneToOne
    private Usuario usuario;
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }      
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }     
}
