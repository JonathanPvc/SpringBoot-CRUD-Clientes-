package com.Curso10Springboot.Curso.Controllers;
import com.Curso10Springboot.Curso.configurations.ExternalizedConfigurations;
import com.Curso10Springboot.Curso.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Curso10Springboot.Curso.services.ProductService;

import java.util.List;
//@Primary
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    @Lazy
    //@Qualifier("jsonResourceService")
    private ProductService productService;



    @Autowired
    private ExternalizedConfigurations ExternalizedConfigurations;

    @GetMapping
    public ResponseEntity <?> getProducts() {

        System.out.println(ExternalizedConfigurations.toString());

        List<Product> products = productService.getProducts();

        return ResponseEntity.ok(products);
    }

}
