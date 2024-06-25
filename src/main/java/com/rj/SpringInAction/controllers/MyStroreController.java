package com.rj.SpringInAction.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.rj.SpringInAction.models.Product;
import com.rj.SpringInAction.service.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MyStroreController {
    
    @Autowired
    ProductService productService;

    @GetMapping("/api/public/product/search")
    public ResponseEntity<List<Product>> getMethodName(@RequestParam(required = false) String keyword) {
        return productService.searchProductByKeyword(keyword);
    }
    
}
