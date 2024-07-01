package com.rj.SpringInAction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rj.SpringInAction.models.dto.LoginRequestDto;
import com.rj.SpringInAction.models.dto.LoginResponseDto;
import com.rj.SpringInAction.utils.JwtUtils;

@Service
public class LoginService {

    @Autowired
    AuthenticationManager manager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserDetailsService uds;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
            UserDetails ud = uds.loadUserByUsername(loginRequestDto.getUsername());
            return new LoginResponseDto(jwtUtils.generateToken(ud));
        } catch (Exception ex) {
            throw new UsernameNotFoundException("Inavlid user details");
        }
    }
}
