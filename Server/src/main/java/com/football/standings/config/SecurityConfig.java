package com.football.standings.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .requestMatchers("/public").permitAll()
                .anyRequest().authenticated()
                .and()
            
        ;
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        
        return web -> web.ignoring().requestMatchers("/static/**");
    }
}
