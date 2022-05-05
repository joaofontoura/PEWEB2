/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.JPA.controller;

import com.example.JPA.models.entity.ClientePF;
import com.example.JPA.models.entity.Item;
import com.example.JPA.models.entity.Produto;
import com.example.JPA.models.entity.Venda;
import com.example.JPA.models.repositories.ClientePFRepository;
import com.example.JPA.models.repositories.ProdutoRepository;
import com.example.JPA.models.repositories.VendaRepository;
import java.time.LocalDate;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author joaoc
 */
@Scope("request")
@Transactional
@Controller
@RequestMapping("vendas")
public class VendaController {
    
    @Autowired
    VendaRepository repository;
    
    @Autowired
    ProdutoRepository prepository;
    
    @Autowired
    ClientePFRepository cpfrepository;
    
    @Autowired
    Venda venda;
    
    @GetMapping("/form")
    public String form (Venda venda, Item i){
        return "/vendas/form";
    }
    
    @GetMapping("/list")
    public ModelAndView listar(ModelMap model){
        model.addAttribute("vendas", repository.vendas());
        return new ModelAndView("/vendas/list", model);
    }
    
    @PostMapping("/add")
    public ModelAndView additem(Item i,ModelMap model){
        Produto produto = prepository.produto(i.getProduto().getId());
        i.setProduto(produto);
        i.setVenda(venda);
        venda.getItens().add(i);
        return new ModelAndView("redirect:/vendas/form");
    }
    
    @PostMapping("/save")
    public ModelAndView save(Venda venda){
        ClientePF clientePF = cpfrepository.clientepf(venda.getClientePF().getId()); 
        
        this.venda.setId(null);
        this.venda.setData(LocalDate.now());
        this.venda.setClientePF(clientePF);
        repository.save(this.venda);
        this.venda.getItens().clear();
        return new ModelAndView("redirect:/vendas/list");    
    }
}
