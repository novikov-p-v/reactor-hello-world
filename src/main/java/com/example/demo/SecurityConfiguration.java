package com.example.demo;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableReactiveMethodSecurity
public class SecurityConfiguration {
    @Bean // (2)
    public SecurityWebFilterChain securityWebFilterChain( //
                                                          ServerHttpSecurity http // (2.1)
    ) { //
        return http // (2.2)
                .formLogin() //
                .and() //
                .authorizeExchange() //
                .anyExchange().authenticated() //
                .and() //
                .build(); // (2.3)
    } //
    @Bean // (3)
    public ReactiveUserDetailsService userDetailsService() { //
        UserDetails user = //
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                .roles("USER", "ADMIN") //
                .build(); //
        return new MapReactiveUserDetailsService(user); // (3.2)
    } //
}
