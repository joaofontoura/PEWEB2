/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JPA.models.repositories;

import com.example.JPA.models.entity.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author labbit-03
 */
@Repository
public class RoleRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    public Role role(Long id){
        return em.find(Role.class, id);
    }    
}
