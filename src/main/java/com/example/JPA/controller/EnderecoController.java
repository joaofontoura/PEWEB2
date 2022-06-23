/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.JPA.controller;

import com.example.JPA.models.entity.ClientePF;
import com.example.JPA.models.entity.Endereco;
import com.example.JPA.models.entity.Municipio;
import com.example.JPA.models.entity.Usuario;
import com.example.JPA.models.repositories.ClientePFRepository;
import com.example.JPA.models.repositories.EnderecoRepository;
import com.example.JPA.models.repositories.MunicipioRepository;
import com.example.JPA.models.repositories.UsuarioRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author joaoc
 */
@Transactional
@Controller
@RequestMapping("enderecos")
public class EnderecoController {
    
    @Autowired
    EnderecoRepository repository;
    
    @Autowired
    MunicipioRepository mRepository;
    
    @Autowired
    ClientePFRepository cpfrepository;
    
    @Autowired
    UsuarioRepository uRepository; 
    
    @GetMapping("/form")
    public String form(Endereco endereco, ModelMap model){
        model.addAttribute("municipios", mRepository.municipios());
        return "/enderecos/form";
    }
    
    @GetMapping("/list")
    public ModelAndView listar (ModelMap model){
        model.addAttribute("endereco", repository.enderecos());
        return new ModelAndView("/enderecos/list", model);
    }
    
    @GetMapping("/save")
    public ModelAndView save(@RequestParam("id_municipio") Long id_municipio, Endereco endereco){  
        
        ClientePF clientePF;         
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario u = uRepository.usuario(auth.getName());
        clientePF = cpfrepository.clienteByUsuario(u.getId());
        
        Municipio municipio = mRepository.municipio(id_municipio); 
        endereco.setClientePF(clientePF);
        endereco.setMunicipio(municipio);
        repository.save(endereco);
        
        return new ModelAndView("redirect:/vendas/form");
    }
    
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        repository.remove(id);
        return new ModelAndView("redirect:/enderecos/list");
    }
    
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("endereco", repository.endereco(id));
        return new ModelAndView("/enderecos/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Endereco endereco) {
        repository.update(endereco);
        return new ModelAndView("redirect:/enderecos/list");
    }    
}
