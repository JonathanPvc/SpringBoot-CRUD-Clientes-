package com.Curso10Springboot.Curso.services;

import com.Curso10Springboot.Curso.domain.Product;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//@Service("listResourceService")
@Lazy
@Service
@ConditionalOnProperty(name = "service.products", havingValue = "list")



public class ProductsServiceImpl implements ProductService {

    public ProductsServiceImpl() {
        System.out.println("Intancia de ProductsServiceImpl creada");
    }



    List <Product> products = new ArrayList<>(Arrays.asList(
            new Product( 1, "Laptop", 999.99, 10),
            new Product( 2, "Smartphone", 499.99, 25),
            new Product( 3, "Tablet", 299.99, 15),
            new Product( 4, "Smartwatch", 199.99, 30),
            new Product( 5, "Headphones", 89.99, 50)
    ));

    @Override
    public List<Product> getProducts() {
        return products;
    }


}
