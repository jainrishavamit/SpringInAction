package com.rj.SpringInAction.service;

import java.util.List;
import java.util.Objects;

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
        /**
         * Using the custom code
         */
        /*
            List<Product> products= productRepository.findAll();
            products = products.stream().filter( 
                product -> product.getPName().contains(keyword) || product.getCategory().getCatName().contains(keyword)
            ).toList();
        */

        // Using the repo methods : query 
        // return ResponseEntity.ok().body(productRepository.searchByKeyword(keyword));

        //using JPA provided method
        return ResponseEntity.ok().body(productRepository.findAllBypNameContainingIgnoringCaseOrCategoryCatNameContainingIgnoringCase(keyword,keyword));
    }

    public ResponseEntity<List<Product>> findProductByUsername(String username, Integer id) {
        if(Objects.isNull(id))
            return ResponseEntity.ok().body(productRepository.findAllBySellerUsername(username));
        return ResponseEntity.ok().body(productRepository.findAllBypIdAndSellerUsername(id, username));
    }

}
