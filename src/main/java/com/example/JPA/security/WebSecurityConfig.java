/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JPA.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author labbit-03
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    UsuarioDetailsConfig usuarioDetailsConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/clientespf/formf").permitAll()
                .antMatchers("/clientespf/save").permitAll()
                .antMatchers("/enderecos/form").permitAll()
                .antMatchers("/vendas/listAllVendas").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll();
    }
    
    @Autowired
    public void configureUserDeatils(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(usuarioDetailsConfig)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
    /**
     * Com o método, instanciamos uma instância do encoder BCrypt e deixando o 
     * controle dessa instância como responsabilidade do Spring. Agora, sempre 
     * que o Spring Security necessitar disso, ele já terá o que precisa confi
     * gurado. @return 
     */    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
