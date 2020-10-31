package com.example.demo;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {
    
    // @Override
    // public void configure(WebSecurity web) throws Exception {
    //     web
    //       .ignoring()
    //       .antMatchers("/**");
    //   }
      
    @Override  
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/author/*/image/*")
        .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .oauth2Login()
            .and()
            .oauth2ResourceServer()
            .jwt();
    }
}
