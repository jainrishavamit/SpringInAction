package com.rj.SpringInAction.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class CartProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int cpId;
    
    //Bidirectional mapping from cart to cart products 
    //foreign key will be created here
    @ManyToOne
    @JoinColumn(name = "cartId",referencedColumnName = "cId")
    @JsonIgnore
    Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId", referencedColumnName = "pId")
    Product product;

    int quantity;
}
