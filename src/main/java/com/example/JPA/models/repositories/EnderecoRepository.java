/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.JPA.models.repositories;

import com.example.JPA.models.entity.Cliente;
import com.example.JPA.models.entity.ClientePF;
import com.example.JPA.models.entity.Endereco;
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
public class EnderecoRepository {
      
    @PersistenceContext
    private EntityManager em;
    
    public void save(Endereco endereco){
        em.persist(endereco);
    }
    
    public Endereco endereco(Long id){
        return em.find(Endereco.class, id);
    }
    
    public List<Endereco> enderecoByCliente(Long id_cliente){
        String hql = "from Endereco where id_cliente=:id_cliente";
        Query query = em.createQuery(hql, Endereco.class);
        query.setParameter("id_cliente", id_cliente);
        return query.getResultList();
    }
            
    
    public List<Endereco> enderecos(){
        Query query = em.createQuery("from Endereco");
        return query.getResultList();
    }
    
    public void remove(Long id){
        Endereco e = em.find(Endereco.class, id);
        em.remove(e);
    }
    
    public void update(Endereco endereco){
        em.merge(endereco);
    }  
}
