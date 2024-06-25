package com.rj.SpringInAction.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int cId;
    double tAmount;

    //uniderctional mapping from cart to user
    //this is not created in user and so no need to use mapped by
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "cartOwner", referencedColumnName = "userId")
    User user;

    //bidirectional mapping between cart and cart products
    //foreing key is expected to be in CartProducts, so mapping the same here
    // this will avoid creating the sepearte table for the same
    //mapped by uses variable name from other class
    @OneToMany (mappedBy = "cart", fetch = FetchType.EAGER)
    //@JoinColumn(name="abc") | can not be used when mappedBy is used
    List<CartProducts> cartProducts;
}
