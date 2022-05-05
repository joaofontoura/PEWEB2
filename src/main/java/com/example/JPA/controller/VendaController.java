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
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String form (Item i, ModelMap model){
        model.addAttribute("clientesPF", cpfrepository.clientespf());
        return "/vendas/form";
    }
    
    @GetMapping("/list")
    public ModelAndView listar(ModelMap model){
        model.addAttribute("vendas", repository.vendas());
        return new ModelAndView("vendas/list");
    }
    
    @PostMapping("/add")
    public ModelAndView additem(@Valid Item i, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("Erro", "Quantidade não pode ser 0");
            return new ModelAndView("redirect:/produtos/list");
        }
        
        Produto produto = prepository.produto(i.getProduto().getId());
        i.setProduto(produto);
        i.setVenda(venda);
        venda.getItens().add(i);
        return new ModelAndView("redirect:/vendas/form");
    }
    
    @GetMapping("/save")
    public ModelAndView save(@RequestParam("id_cliente") Long id_cliente, RedirectAttributes attributes){ 
        if(id_cliente == 0){
            attributes.addFlashAttribute("Erro", "Selecione um cliente");
            return new ModelAndView("redirect:/vendas/form");
        }
        
        ClientePF clientePF = cpfrepository.clientepf(id_cliente); 
        
        venda.setId(null);
        venda.setData(LocalDate.now());
        venda.setClientePF(clientePF);
        repository.save(venda);
        venda.getItens().clear();
        return new ModelAndView("redirect:/vendas/list");    
    }
}
