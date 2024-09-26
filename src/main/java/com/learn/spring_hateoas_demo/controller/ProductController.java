package com.learn.spring_hateoas_demo.controller;

import com.learn.spring_hateoas_demo.model.Product;
import com.learn.spring_hateoas_demo.service.ProductService;
import com.learn.spring_hateoas_demo.util.Cart;
import com.learn.spring_hateoas_demo.util.DummyProducts;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/rest/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * This is a typical endpoint which returns only the product details
     * @return
     */
    @GetMapping("/all")
    public List<Product> getAll(){
        return DummyProducts.getAllProducts();
    }


    /**
     * This is a hateoas enabled endpoint which includes all the products and related links to it.
     * The advantages of this kind of endpoint are mentioned in README.txt file
     *
     * produces = HAL_JSON_VALUE ---> This says that the response will include both the product data and hypermedia links
     * in HAL-compliant JSON format.
     * @return
     */
    @GetMapping(value = "/hateoas/all", produces = HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Product>> getAllWithHateoas(){

        List<Product> products = DummyProducts.getAllProducts();

        List<EntityModel<Product>> productModelList = products.stream()
                .map(product -> EntityModel.of(product,
                        linkTo(methodOn(ProductController.class).findProductById(product.getId())).withSelfRel(),
                        linkTo(methodOn(ProductController.class).addToCart(product.getId())).withRel("addToCart")))
                .toList();

        return CollectionModel.of(productModelList,
                linkTo(methodOn(ProductController.class).getAllWithHateoas()).withSelfRel(), // the self link always pointing to the current resource.
                linkTo(methodOn(ProductController.class).getAll()).withRel("without-links"), // withRel("cart") method in Spring HATEOAS is used to create a hypermedia link with a custom relation (rel) name.
                linkTo(methodOn(ProductController.class).viewCart()).withRel("cart"));
    }

    // This is a link we need to attach to get all products endpoint
    @GetMapping("{productId}") // http://localhost:8081/rest/products/2
    public Product findProductById(@PathVariable Long productId){
        return productService.findById(productId);
    }

    // This is a link we need to attach to get all products endpoint
    @PostMapping("/cart/add/{productId}") // http://localhost:8081/rest/products/cart/add/2
    public ResponseEntity<Void> addToCart(@PathVariable Long productId){
        productService.addToCart(productId);
        return ResponseEntity.ok().build();
    }

    /**
     * This end point is used to see what are the products added to the cart
     * @return
     */
    @GetMapping("cart")
    public List<Product> viewCart(){
        return Cart.showCart();
    }

}
