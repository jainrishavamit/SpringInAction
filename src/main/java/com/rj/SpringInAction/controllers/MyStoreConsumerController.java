package com.rj.SpringInAction.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rj.SpringInAction.models.Cart;
import com.rj.SpringInAction.models.CartProducts;
import com.rj.SpringInAction.models.Product;
import com.rj.SpringInAction.models.User;
import com.rj.SpringInAction.service.CartService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



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

    @PostMapping("/cart")
    public ResponseEntity<Object> postMethodName(@RequestBody Product product) {
        
        String username = ((User)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername();
        return cartService.addProduct(username, product);
    }

    @PutMapping("/cart")
    public void putMethodName(@RequestBody CartProducts cp) {
        String username = ((User)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername();
        cartService.updateProduct(username,cp);
    }
    
}
