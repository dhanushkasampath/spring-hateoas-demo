package com.learn.spring_hateoas_demo.service.impl;

import com.learn.spring_hateoas_demo.model.Product;
import com.learn.spring_hateoas_demo.service.ProductService;
import com.learn.spring_hateoas_demo.util.Cart;
import com.learn.spring_hateoas_demo.util.DummyProducts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Override
    public Product findById(Long productId) {
        List<Product> productList = DummyProducts.getAllProducts();
        List<Product> filteredProductList = productList.stream()
                .filter(product -> product.getId() == productId)
                .toList();

        if(filteredProductList.isEmpty()){
            return null;
        } else {
            return filteredProductList.getFirst();
        }
    }

    @Override
    public void addToCart(Long productId) {
        Product product = findById(productId);
        if (product == null) {
            log.info("There is no product to add to the cart");
        } else {
            Cart.addToCart(product);
        }
    }
}
