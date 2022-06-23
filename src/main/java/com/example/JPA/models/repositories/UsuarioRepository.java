/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JPA.models.repositories;

import com.example.JPA.models.entity.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author labbit-03
 */
@Repository
public class UsuarioRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    public Usuario usuario(String login){
        try{
            String jpql = "from Usuario u where u.login = :login";
            TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
            query.setParameter("login", login);
            return query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }
    }
    
    public void save(Usuario usuario){
        em.persist(usuario);
    }
}