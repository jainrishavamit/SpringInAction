package com.rj.SpringInAction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rj.SpringInAction.models.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer>{
    List<Cart> findAllByUserUsername(String username);
    Cart findByUserUsername(String username);

}
