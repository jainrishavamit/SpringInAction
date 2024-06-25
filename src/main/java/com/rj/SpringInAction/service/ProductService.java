package com.rj.SpringInAction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rj.SpringInAction.models.Product;
import com.rj.SpringInAction.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ResponseEntity<List<Product>> searchProductByKeyword(String keyword) {
        List<Product> products= productRepository.findAll();
        products = products.stream().filter( 
            product -> product.getPName().contains(keyword) || product.getCategory().getCatName().contains(keyword)
        ).toList();

        return ResponseEntity.ok().body(products);
    }

}
