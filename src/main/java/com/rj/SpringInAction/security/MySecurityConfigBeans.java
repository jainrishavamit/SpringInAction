package com.rj.SpringInAction.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.rj.SpringInAction.repository.UserRepository;

@Component
public class MySecurityConfigBeans {

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
    PasswordEncoder myPasswordEncoder(){
        // return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();  //shifted to NoOp because the saved passwords are not encoded
    }

    @Bean
    AuthenticationManager getAuthenticationManager(AuthenticationConfiguration configProvider) throws Exception {
        return configProvider.getAuthenticationManager();
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
