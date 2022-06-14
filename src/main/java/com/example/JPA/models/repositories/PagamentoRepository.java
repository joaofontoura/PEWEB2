/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JPA.models.repositories;

import com.example.JPA.models.entity.Pagamento;
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
public class PagamentoRepository {
    
    @PersistenceContext
    private EntityManager em;
     
    public void save(Pagamento pagamento){
        em.persist(pagamento);
    }
    
    public Pagamento pagamento(Long id){
        return em.find(Pagamento.class,id);
    }
    
    public List<Pagamento> pagamentos(){
        Query query = em.createQuery("from Pagamento");
        return query.getResultList();
    }
    
    public void remove(Long id){
        Pagamento p = em.find(Pagamento.class,id);
        em.remove(p);
    }
    
    public void update(Pagamento pagamento){
        em.merge(pagamento);
    }     
}
