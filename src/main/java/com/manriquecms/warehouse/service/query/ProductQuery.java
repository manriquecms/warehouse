package com.manriquecms.warehouse.service.query;

import com.manriquecms.warehouse.domain.dto.ProductDto;
import com.manriquecms.warehouse.infrastructure.repository.ProductRepository;
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

    public List<ProductDto> getAllProducts(){
        return StreamSupport.stream(productRepository.findAll().spliterator(),false)
                .map(p -> ProductDto.fromProduct(p))
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(String name){
        return ProductDto.fromProduct(productRepository.findById(name)
                .orElseThrow(() -> new ProductNotFoundException(name)));
    }
}
