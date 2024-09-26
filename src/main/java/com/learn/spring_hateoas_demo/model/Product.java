package com.learn.spring_hateoas_demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@Builder
public class Product extends RepresentationModel<Product> {

    private Long id;
    private String name;
    private Double price;

}
