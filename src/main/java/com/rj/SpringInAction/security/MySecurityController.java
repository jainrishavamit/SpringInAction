package com.rj.SpringInAction.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MySecurityController {

    @Bean
    UserDetailsService manageUsers() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(
                User.withUsername("con")
                        .password("123")
                        .roles("CONSUMER")
                        .passwordEncoder(pass -> new BCryptPasswordEncoder().encode(pass))
                        .build());

        manager.createUser(
            User.withUsername("sel")
                .password("123")
                .roles("SELLER")
                .passwordEncoder(pass -> new BCryptPasswordEncoder().encode(pass))
                .build());
        return manager;
    }

    @Bean
    SecurityFilterChain mySecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(httpRequest -> {
            httpRequest.requestMatchers("/api/public/**").permitAll();
            httpRequest.requestMatchers("/h2-console/**").permitAll();
            httpRequest.requestMatchers("/api/auth/consumer/**").hasRole("CONSUMER");
            httpRequest.requestMatchers("/api/auth/seller/**").hasRole("SELLER");
            httpRequest.anyRequest().authenticated();
        });
        // httpSecurity.formLogin(Customizer.withDefaults()); // this enables the form
        // based login
        httpSecurity.cors(cors -> cors.disable());
        httpSecurity.csrf(csrf -> csrf.disable());
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder myPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
