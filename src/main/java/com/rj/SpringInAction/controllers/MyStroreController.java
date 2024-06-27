package com.rj.SpringInAction.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.rj.SpringInAction.models.Cart;
import com.rj.SpringInAction.models.Product;
import com.rj.SpringInAction.service.CartService;
import com.rj.SpringInAction.service.ProductService;

import jakarta.websocket.server.PathParam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MyStroreController {
    
    @Autowired
    ProductService productService;

    @Autowired
    CartService cartService;

    @GetMapping("/api/public/product/search")
    public ResponseEntity<List<Product>> getProductyByKeyword(@RequestParam(required = false) String keyword) {
        return productService.searchProductByKeyword(keyword);
    }

    @GetMapping("/api/auth/consumer/cart")
    public ResponseEntity<List<Cart>> getCartOfConsumer(@RequestParam(defaultValue = "jack") String username) {
        return cartService.getConsumerCart(username);
    }

    @GetMapping({"api/auth/seller/product/{id}","api/auth/seller/product","api/auth/seller/product/"})
    public ResponseEntity<List<Product>> getMethodName(@RequestParam(defaultValue = "apple") String username, @PathVariable(required = false) Integer id) {
        return  productService.findProductByUsername(username,id);
    }
    
    
    
}
