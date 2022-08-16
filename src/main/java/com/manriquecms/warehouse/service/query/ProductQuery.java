package com.manriquecms.warehouse.service.query;

import com.manriquecms.warehouse.domain.model.product.Product;
import com.manriquecms.warehouse.infrastructure.repository.article.ProductRepository;
import com.manriquecms.warehouse.service.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductQuery {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return StreamSupport.stream(productRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    public Product getProductById(String name){
        return productRepository.findById(name)
                .orElseThrow(() -> new ProductNotFoundException(name));
    }
}
