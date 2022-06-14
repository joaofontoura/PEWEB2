/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JPA.controller;

import com.example.JPA.models.entity.Cartao;
import com.example.JPA.models.repositories.CartaoRepository;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author 01719899100
 */
@Transactional
@Controller
@RequestMapping("cartoes")
public class CartaoController {
    
    @Autowired
    CartaoRepository repository;
    
    @GetMapping("/form")
    public String form(Cartao cartao){
        return "/cartoes/form";
    }
    
    @GetMapping("/list")
    public ModelAndView listar(ModelMap model){
        model.addAttribute("cartoes", repository.cartoes());
        return new ModelAndView("/home", model);
    }
    
    @PostMapping("/save")
    public ModelAndView save(@Valid Cartao cartao){
        repository.save(cartao);
        return new ModelAndView("redirect:/home");
    }
    
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        repository.remove(id);
        return new ModelAndView("redirect:/home");
    }
    
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("cartao", repository.cartao(id));
        return new ModelAndView("/cartoes/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Cartao cartao) {
        repository.update(cartao);
        return new ModelAndView("redirect:/home");
    }      
}
