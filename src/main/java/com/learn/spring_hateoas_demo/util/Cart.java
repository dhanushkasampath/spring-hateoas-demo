package com.learn.spring_hateoas_demo.util;

import com.learn.spring_hateoas_demo.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * This class used as a resource which act as a cart.
 */
public class Cart {

    private static final List<Product> productsInCart = new ArrayList<>();

    public static void addToCart(Product product){
        productsInCart.add(product);
    }

    public static List<Product> showCart(){
        return productsInCart;
    }
}
