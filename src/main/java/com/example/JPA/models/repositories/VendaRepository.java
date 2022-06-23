/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.JPA.models.repositories;

import com.example.JPA.models.entity.Venda;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joaoc
 */
@Repository
public class VendaRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Venda> listarPorCliente(Long id_cliente){
        String hql = "from Venda where id_cliente=:id_cliente";
        Query query = em.createQuery(hql, Venda.class);
        query.setParameter("id_cliente", id_cliente);
        return query.getResultList();
    }
    
    public List<Venda> vendas(){
        Query query = em.createQuery("from Venda");
        return query.getResultList();
    }
    
    public Venda venda(Long id){
        return em.find(Venda.class, id);
    }
    
    public void save(Venda venda){
        em.persist(venda);
    }
}
