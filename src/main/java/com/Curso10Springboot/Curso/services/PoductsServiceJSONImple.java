package com.Curso10Springboot.Curso.services;

import com.Curso10Springboot.Curso.domain.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;


@Service("jsonResourceService")
public class PoductsServiceJSONImple implements  ProductService {


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
