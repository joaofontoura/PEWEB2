/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JPA.models.repositories;

import com.example.JPA.models.entity.Cartao;
import com.example.JPA.models.entity.Produto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 01719899100
 */
@Transactional
@Repository
public class CartaoRepository {
    
    @PersistenceContext
    private EntityManager em;
     
    public void save(Cartao cartao){
        em.persist(cartao);
    }
    
    public Cartao cartao(Long id){
        return em.find(Cartao.class,id);
    }
    
    public List<Cartao> cartoes(){
        Query query = em.createQuery("from Cartao");
        return query.getResultList();
    }
    
    public void remove(Long id){
        Cartao c = em.find(Cartao.class,id);
        em.remove(c);
    }
    
    public void update(Cartao cartao){
        em.merge(cartao);
    }    
}
