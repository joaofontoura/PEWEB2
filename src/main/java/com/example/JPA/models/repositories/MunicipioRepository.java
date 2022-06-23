/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JPA.models.repositories;

import com.example.JPA.models.entity.ClientePF;
import com.example.JPA.models.entity.Municipio;
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
public class MunicipioRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    public Municipio municipio(Long id){
        return em.find(Municipio.class, id);
    }
    
    public List<Municipio> municipios(){
        Query query = em.createQuery("from Municipio");
        return query.getResultList();
    }
    
}
