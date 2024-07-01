package com.rj.SpringInAction.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.rj.SpringInAction.models.Cart;
import com.rj.SpringInAction.models.LoginRequestDto;
import com.rj.SpringInAction.models.LoginResponseDto;
import com.rj.SpringInAction.models.Product;
import com.rj.SpringInAction.models.User;
import com.rj.SpringInAction.service.CartService;
import com.rj.SpringInAction.service.LoginService;
import com.rj.SpringInAction.service.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MyStroreController {
    
    @Autowired
    ProductService productService;

    @Autowired
    CartService cartService;

    @Autowired
    LoginService loginService;

    @GetMapping("/api/public/product/search")
    public ResponseEntity<List<Product>> getProductyByKeyword(@RequestParam(required = false) String keyword) {
        return productService.searchProductByKeyword(keyword);
    }

    @GetMapping("/api/public/getToken")
    public ResponseEntity<LoginResponseDto> getMethodName(@RequestBody LoginRequestDto loginDetails) {
        return ResponseEntity.ok().body(loginService.login(loginDetails));
    }
    

    @GetMapping("/api/auth/consumer/cart")
    public ResponseEntity<List<Cart>> getCartOfConsumer() {
        String username = ((User)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername();
        return cartService.getConsumerCart(username);
    }

    @GetMapping({"api/auth/seller/product/{id}","api/auth/seller/product","api/auth/seller/product/"})
    public ResponseEntity<List<Product>> getMethodName(@PathVariable(required = false) Integer id) {
        String username = ((User)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername();
        return  productService.findProductByUsername(username,id);
    }
    
    
    
}
