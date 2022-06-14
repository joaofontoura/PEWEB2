/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.JPA.controller;

import com.example.JPA.models.entity.ClientePF;
import com.example.JPA.models.entity.Endereco;
import com.example.JPA.models.entity.Item;
import com.example.JPA.models.entity.Pagamento;
import com.example.JPA.models.entity.Produto;
import com.example.JPA.models.entity.Usuario;
import com.example.JPA.models.entity.Venda;
import com.example.JPA.models.repositories.ClientePFRepository;
import com.example.JPA.models.repositories.EnderecoRepository;
import com.example.JPA.models.repositories.PagamentoRepository;
import com.example.JPA.models.repositories.ProdutoRepository;
import com.example.JPA.models.repositories.UsuarioRepository;
import com.example.JPA.models.repositories.VendaRepository;
import java.time.LocalDate;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    UsuarioRepository uRepository;    
    
    @Autowired
    EnderecoRepository eRepository;
    
    @Autowired
    Venda venda;
    
    @Autowired
    PagamentoRepository pagamentoRepository;
    
    @GetMapping("/form")
    public String form (Item i, ModelMap model){
        ClientePF clientePF;         
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario u = uRepository.usuario(auth.getName());
        clientePF = cpfrepository.clienteByUsuario(u.getId());
        
        model.addAttribute("enderecos", eRepository.enderecoByCliente(clientePF.getId()));
        model.addAttribute("pagamentos", pagamentoRepository.pagamentos());
        return "/vendas/form";
    }
    
    @GetMapping("/list")
    public ModelAndView listar(ModelMap model){    
        ClientePF clientePF;         
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario u = uRepository.usuario(auth.getName());
        clientePF = cpfrepository.clienteByUsuario(u.getId());
        model.addAttribute("vendas", repository.listarPorCliente(clientePF.getId()));
        return new ModelAndView("vendas/list");
    }
    
    @GetMapping("/listAllVendas")
    public ModelAndView listarPorCliente(ModelMap model){
        model.addAttribute("vendas", repository.vendas());
        return new ModelAndView("/vendas/list", model);
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
    public ModelAndView save(@RequestParam("id_endereco") Long id_endereco, @RequestParam("id_pagamento") Long id_pagamento){        
        ClientePF clientePF;         
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario u = uRepository.usuario(auth.getName());
        clientePF = cpfrepository.clienteByUsuario(u.getId());
        
        Endereco endereco;
        endereco = eRepository.endereco(id_endereco);
        clientePF.getEnderecos().add(endereco);
        
        Pagamento pagamento;
        pagamento = pagamentoRepository.pagamento(id_pagamento);
        
        venda.setPagamento(pagamento);
        venda.setEndereco(endereco);
        venda.setId(null);
        venda.setData(LocalDate.now());
        venda.setClientePF(clientePF);
        repository.save(venda);
        venda.getItens().clear();
        return new ModelAndView("redirect:/vendas/list");    
    }
    
    @GetMapping("/detalhes/{id}")
    public ModelAndView detalhes(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("venda", repository.venda(id));
        return new ModelAndView("/vendas/detalhes", model);
    }
}
