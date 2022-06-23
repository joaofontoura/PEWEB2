/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JPA.security;

import com.example.JPA.models.entity.Usuario;
import com.example.JPA.models.repositories.UsuarioRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author labbit-03
 */
@Transactional
@Repository
public class UsuarioDetailsConfig implements UserDetailsService{
    
    @Autowired
    UsuarioRepository repository;
    
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = repository.usuario(login);
        
        if(usuario == null){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }        
        return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
    }    
}
