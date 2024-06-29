package com.rj.SpringInAction.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.rj.SpringInAction.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class MySecurityController {
    
    @Autowired
    UserRepository userRepository;

    /**
     * Creating the UserDetailsService bean which will manage users
     *
     * Creating a anonymous class for the UserDetailService interface which implements following method, using lamda
     * UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
     *      
     * @return an anonymous instance of the UserDetailsService interface
     */
    @Bean
    UserDetailsService userDetailsService(){
        return username -> {
            List<com.rj.SpringInAction.models.User> users = userRepository.findByUsername(username);
            if(users.isEmpty())
                throw new UsernameNotFoundException("user not found : "+username);
            return users.get(0);
        };
    }

    // Not sure about the purpose yet
    // @Bean
    // AuthenticationProvider authenticationProvider(){
    //     DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
    //     dap.setPasswordEncoder(myPasswordEncoder());
    //     dap.setUserDetailsService(userDetailsService());
    //     return dap;
    // }
    
    @Bean
    SecurityFilterChain mySecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(httpRequest -> {
            httpRequest.requestMatchers("/api/public/**").permitAll();
            httpRequest.requestMatchers("/h2-console/**").permitAll();
            httpRequest.requestMatchers("/api/auth/consumer/**").hasRole("CONSUMER");
            httpRequest.requestMatchers("/api/auth/seller/**").hasRole("SELLER");
            httpRequest.anyRequest().authenticated();
        });
        // httpSecurity.formLogin(Customizer.withDefaults()); // this enables the form based login
        httpSecurity.cors(cors -> cors.disable());
        httpSecurity.csrf(csrf -> csrf.disable());
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder myPasswordEncoder(){
        // return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();  //shifted to NoOp because the saved passwords are not encoded
    }

    /**
     * This is used to create the inmemory users ->  we are storing the users in DB
     */
    /*
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
    */
}
