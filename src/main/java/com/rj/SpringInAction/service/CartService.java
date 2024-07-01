package com.rj.SpringInAction.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rj.SpringInAction.models.Cart;
import com.rj.SpringInAction.models.CartProducts;
import com.rj.SpringInAction.models.Product;
import com.rj.SpringInAction.repository.CartProductRepository;
import com.rj.SpringInAction.repository.CartRepository;
import com.rj.SpringInAction.repository.ProductRepository;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;
    
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartProductRepository cartProductRepository;

    public ResponseEntity<List<Cart>> getConsumerCart(String username) {
        List<Cart> cart= cartRepository.findAllByUserUsername(username);
        return ResponseEntity.ok().body(cart);
    }

    public ResponseEntity<Object> addProduct(String username, Product product) {
        Cart cart= cartRepository.findByUserUsername(username);
        Optional<Product> pti = productRepository.findById(product.getPId());

        if (cart.getCartProducts().stream().anyMatch(cp -> cp.getProduct().getPId() == pti.get().getPId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        cart.setTAmount(cart.getTAmount()+product.getPrice());

        CartProducts cp = new CartProducts();
        cp.setCart(cart);
        cp.setProduct(product);
        cp.setQuantity(1);
        cartProductRepository.save(cp);
        return ResponseEntity.ok().body(null);
    }

    public void updateProduct(String username, CartProducts cpn) {
        Cart cart= cartRepository.findByUserUsername(username);
        Optional<Product> pti = productRepository.findById(cpn.getProduct().getPId());
        Optional<CartProducts> cpo = cartProductRepository.findByCartAndProduct(cart, pti.get());

        cart.setTAmount(cart.getTAmount()+((cpn.getQuantity()-cpo.get().getQuantity())*pti.get().getPrice()));
        cart.getCartProducts().remove(cpo.get());
        cpn.setCart(cart);
        cartProductRepository.delete(cpo.get());
        if(!(cpn.getQuantity() == 0)) 
            cartProductRepository.save(cpn); 
    }


}
