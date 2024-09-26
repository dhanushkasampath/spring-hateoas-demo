package com.learn.spring_hateoas_demo.service;

import com.learn.spring_hateoas_demo.model.Product;

public interface ProductService {
    Product findById(Long productId);

    void addToCart(Long productId);
}
