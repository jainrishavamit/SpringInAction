package com.rj.SpringInAction.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int pId;
    String pName;
    double price;

    
    //uniderctional mapping from Product to user
    //this is not created in user and so no need to use mapped by
    @ManyToOne
    @JoinColumn(name="sellerId",referencedColumnName = "userId",updatable = false)
    @JsonIgnore
    User seller;

    
    //uniderctional mapping from Product to user
    //this is not created in user and so no need to use mapped by
    @ManyToOne
    @JoinColumn(name="categoryId",referencedColumnName = "catId")
    Category category;
}
