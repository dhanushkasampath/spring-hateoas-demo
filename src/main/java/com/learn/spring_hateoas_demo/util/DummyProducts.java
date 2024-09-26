package com.learn.spring_hateoas_demo.util;

import com.learn.spring_hateoas_demo.model.Product;

import java.util.List;

/**
 * This class used as a resource which returns list of products. Ideally this should be replaced by a database
 */
public class DummyProducts {

    public static List<Product> getAllProducts(){
        return List.of(
            Product.builder().id(1L).name("Gas Cooker").price(1000.00).build(),
            Product.builder().id(2L).name("Washing Machine").price(7000.00).build(),
            Product.builder().id(3L).name("Toaster").price(2000.00).build(),
            Product.builder().id(4L).name("Blender").price(4000.00).build()
            );
    }
}
