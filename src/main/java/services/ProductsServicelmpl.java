package services;

import com.Curso10Springboot.Curso.domain.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductsServicelmpl {

    List <Product> products = new ArrayList<>(Arrays.asList(
            new Product( 1, "Laptop", 999.99, 10),
            new Product( 2, "Smartphone", 499.99, 25),
            new Product( 3, "Tablet", 299.99, 15),
            new Product( 4, "Smartwatch", 199.99, 30),
            new Product( 5, "Headphones", 89.99, 50)
    ));


    public List<Product> getAllProducts() {
        return products;
    }

}
