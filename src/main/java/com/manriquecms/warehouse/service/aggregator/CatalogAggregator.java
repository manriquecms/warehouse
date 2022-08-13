package com.manriquecms.warehouse.service.aggregator;

import com.manriquecms.warehouse.infrastructure.repository.article.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogAggregator {
    @Autowired
    private ProductRepository productRepository;

    public CatalogAggregator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

}
