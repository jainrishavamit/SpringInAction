package com.rj.SpringInAction.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rj.SpringInAction.models.Cart;
import com.rj.SpringInAction.models.User;
import com.rj.SpringInAction.service.CartService;

@RestController
@RequestMapping("/api/auth/consumer")
public class MyStoreConsumerController {

    @Autowired
    CartService cartService;

    @GetMapping("/cart")
    public ResponseEntity<List<Cart>> getCartOfConsumer() {
        String username = ((User)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername();
        return cartService.getConsumerCart(username);
    }
}
