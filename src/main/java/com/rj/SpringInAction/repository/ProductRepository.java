package com.rj.SpringInAction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rj.SpringInAction.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    //using @Query
    /* 
        @Query(value = "from Product where pName ilike %?1% or category.catName ilike %?1%")
        List<Product> searchByKeyword(String keyword);
    */

    List<Product> findAllBypNameContainingIgnoringCaseOrCategoryCatNameContainingIgnoringCase(String productKeyword,String catKeyword);

    List<Product> findAllBySellerUsername(String username);

    List<Product> findAllBypIdAndSellerUsername(int id, String username);
}
