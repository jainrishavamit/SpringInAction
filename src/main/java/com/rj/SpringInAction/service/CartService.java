package com.rj.SpringInAction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rj.SpringInAction.models.Cart;
import com.rj.SpringInAction.repository.CartRepository;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;
    
    public ResponseEntity<List<Cart>> getConsumerCart(String username) {
        List<Cart> cart= cartRepository.findAllByUserUsername(username);
        return ResponseEntity.ok().body(cart);
    }
}
