package com.rj.SpringInAction.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;


/**
 *  User class implements the UserDetails class of spring security to hold the details of user as per security context
 */
@Entity
@Data
@Table(name = "CustomUser")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int userId;
    String username;
    String password;

    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UserRole")
    List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Added ROLE_ prefix to the roles fetched from the DB as 
        // we are using hasRole() and so spring requires roles to be prefrefixed with ROLE_
        // alternative is to use hasAuthority() for the aurhorization
        return this.roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.getRole())).toList();
    }
}
