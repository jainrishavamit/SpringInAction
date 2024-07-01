package com.rj.SpringInAction.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.rj.SpringInAction.models.Product;
import com.rj.SpringInAction.models.dto.LoginRequestDto;
import com.rj.SpringInAction.models.dto.LoginResponseDto;
import com.rj.SpringInAction.service.CartService;
import com.rj.SpringInAction.service.LoginService;
import com.rj.SpringInAction.service.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/public")
public class MyStrorePublicController {
    
    @Autowired
    ProductService productService;

    @Autowired
    CartService cartService;

    @Autowired
    LoginService loginService;

    /*
     * Remove requried=false from RequestParam, if need to make it required
     * if done, 
     */
    @GetMapping("/product/search")
    public ResponseEntity<List<Product>> getProductyByKeyword(@RequestParam(required = false) String keyword) {
        return productService.searchProductByKeyword(keyword);
    }

    @GetMapping("/getToken")
    public ResponseEntity<LoginResponseDto> getMethodName(@RequestBody LoginRequestDto loginDetails) {
        return ResponseEntity.ok().body(loginService.login(loginDetails));
    }

}
