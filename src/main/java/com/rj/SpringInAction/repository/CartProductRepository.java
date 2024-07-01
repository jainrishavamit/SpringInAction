package com.rj.SpringInAction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rj.SpringInAction.models.CartProducts;
import java.util.Optional;

import com.rj.SpringInAction.models.Cart;
import com.rj.SpringInAction.models.Product;


@Repository
public interface CartProductRepository extends JpaRepository<CartProducts,Integer>{
    Optional<CartProducts> findByCartAndProduct(Cart cart, Product product);
}
