/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.JPA.controller;

import com.example.JPA.models.entity.Item;
import com.example.JPA.models.entity.Produto;
import com.example.JPA.models.repositories.ProdutoRepository;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author joaoc
 */
@Transactional
@Controller
@RequestMapping("produtos")
public class ProdutoController {
    
    @Autowired
    ProdutoRepository repository;
    
    @GetMapping("/form")
    public String form(Produto produto){
        return "/produtos/form";
    }
    
    @GetMapping("/list")
    public ModelAndView listar(ModelMap model, Item itemvenda){
        model.addAttribute("produtos", repository.produtos());
        return new ModelAndView("/produtos/list", model);
    }
    
    @PostMapping("/save")
    public ModelAndView save(@Valid Produto produto, BindingResult result){
        
        if(result.hasErrors())
            return new ModelAndView("/produtos/form");

        
        repository.save(produto);
        return new ModelAndView("redirect:/produtos/list");
    }
    
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        repository.remove(id);
        return new ModelAndView("redirect:/produtos/list");
    }
    
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("produto", repository.produto(id));
        return new ModelAndView("/produtos/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Produto produto) {
        repository.update(produto);
        return new ModelAndView("redirect:/produtos/list");
    }    
}
