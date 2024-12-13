package com.example.skillwillproject25.Config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
public class Configur {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, @Qualifier("MyCustomFilterForCheckAuthenticationAndAuthorization")OncePerRequestFilter myCustomFilter) throws Exception {
        return http
                .addFilterBefore(myCustomFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(customizatoin -> customizatoin.disable())
                .authorizeHttpRequests(customization -> customization.requestMatchers
                        ("/login").permitAll().requestMatchers("/Admin").hasRole("Admin")
                        .requestMatchers("/User").hasRole("User")
                        .anyRequest().authenticated())
                .build();
    }

}
