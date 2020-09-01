package com.example.demo;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() // Nuestros request requieren autenticacion
            .anyRequest() // Cuales? : Todos
            .authenticated() // Forsozamente un usuario autenticado en nuestro caso con un token de autorizacion
            .and() // Y ademas
            .oauth2Login() // Vamos a habilitar un login con okta en nuestro caso
            .and() // Y ademas
            .oauth2ResourceServer() // Estamos habilitados como un servidor de reucrsos
            .jwt(); // MEdiante jwt tokens
    }
}