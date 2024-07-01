package com.rj.SpringInAction.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rj.SpringInAction.models.Product;
import com.rj.SpringInAction.models.User;
import com.rj.SpringInAction.service.ProductService;

@RestController
@RequestMapping("/api/auth/seller/")
public class MyStoreSellerContrller {

    @Autowired
    ProductService productService;

    @GetMapping({"product/{id}","product","product/"})
    public ResponseEntity<List<Product>> getMethodName(@PathVariable(required = false) Integer id) {
        String username = ((User)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername();
        return  productService.findProductByUsername(username,id);
    }
}
