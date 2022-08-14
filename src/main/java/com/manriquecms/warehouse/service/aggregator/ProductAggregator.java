package com.manriquecms.warehouse.service.aggregator;

import com.manriquecms.warehouse.infrastructure.repository.article.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductAggregator {
    @Autowired
    private ProductRepository productRepository;

    public ProductAggregator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

}
