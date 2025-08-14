package com.Curso10Springboot.Curso.services;

import com.Curso10Springboot.Curso.domain.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;


//@Service("jsonResourceService")
@Service
@ConditionalOnProperty (name = "service.products", havingValue = "json")
public class PoductsServiceJSONImple implements  ProductService {

    public PoductsServiceJSONImple() {
    System.out.println("Intancia de ProductsServiceJSONImple creada");
    }


    @Override
    public List<Product> getProducts() {
        List<Product> products;

        // Aquí deberías implementar la lógica para leer el archivo JSON
        try{
            products = new ObjectMapper()
                    .readValue( this.getClass().getResourceAsStream("/products.json"),
                            new TypeReference<List<Product>>() {});

            return products;

        }
        catch (IOException e){
            throw new RuntimeException(e);

        }
    }
}
