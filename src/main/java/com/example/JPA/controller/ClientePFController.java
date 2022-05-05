/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JPA.controller;

import com.example.JPA.models.entity.ClientePF;
import com.example.JPA.models.repositories.ClientePFRepository;
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
 * @author labbit-03
 */

@Transactional
@Controller
@RequestMapping("clientespf")
public class ClientePFController {
    
    @Autowired
    ClientePFRepository repository; 
    
    @GetMapping("/formf")
    public String form(ClientePF clientepf){
        return "/clientespf/formf";
    }
    
    @GetMapping("/listf")
    public ModelAndView listar(ModelMap model){
        model.addAttribute("clientespf", repository.clientespf());
        return new ModelAndView("/clientespf/listf", model);
    }
    
    @PostMapping("/save")
    public ModelAndView save(@Valid ClientePF clientepf, BindingResult result){
        
        if(result.hasErrors())
            return new ModelAndView("/clientespf/formf");
        
        repository.save(clientepf);
        return new ModelAndView("redirect:/clientespf/listf");
    }
    
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        repository.remove(id);
        return new ModelAndView("redirect:/clientespf/listf");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("clientePF", repository.clientepf(id));
        return new ModelAndView("/clientespf/formf", model);
    }
    
    @PostMapping("/update")
    public ModelAndView update(ClientePF clientepf) {
        repository.update(clientepf);
        return new ModelAndView("redirect:/clientespf/listf");
    }
}
