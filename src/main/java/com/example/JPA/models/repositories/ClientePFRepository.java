/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JPA.models.repositories;

import com.example.JPA.models.entity.Cliente;
import com.example.JPA.models.entity.ClientePF;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author labbit-03
 */
@Repository
public class ClientePFRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    public void save(ClientePF clientepf){
        em.persist(clientepf);
    }

    public ClientePF clientepf(Long id){
        return em.find(ClientePF.class, id);
    }

    public List<ClientePF> clientespf(){
        Query query = em.createQuery("from ClientePF");
        return query.getResultList();
    }
    
    public ClientePF clienteByUsuario(Long usuario_id){
        String hql = "from ClientePF where usuario_id=:usuario_id";
        Query query = em.createQuery(hql, Cliente.class);
        query.setParameter("usuario_id", usuario_id);
        return (ClientePF) query.getSingleResult();
    } 

    public void remove(Long id){
        ClientePF pf = em.find(ClientePF.class, id);
        em.remove(pf);
    }

    public void update(ClientePF clientepf){
        em.merge(clientepf);
    }    
}
