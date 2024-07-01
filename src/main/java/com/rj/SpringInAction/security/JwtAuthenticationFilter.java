package com.rj.SpringInAction.security;

import java.io.IOException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rj.SpringInAction.models.User;
import com.rj.SpringInAction.repository.UserRepository;
import com.rj.SpringInAction.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                String token=request.getHeader("Authorization");
                if(!Objects.isNull(token) && token.startsWith("Bearer ") && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                    token = token.substring(7);
                    //missing check if username is present in the token or not
                    UserDetails ud = userDetailsService.loadUserByUsername(jwtUtils.extractUsername(token));
                    if(jwtUtils.isTokenValid(token, ud)) {
                        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(ud, null, ud.getAuthorities());
                        upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(upat);
                    }
                }
                filterChain.doFilter(request, response);
    }
}
