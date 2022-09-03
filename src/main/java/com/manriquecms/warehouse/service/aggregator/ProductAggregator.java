package com.manriquecms.warehouse.service.aggregator;

import com.manriquecms.warehouse.infrastructure.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(
        isolation = Isolation.READ_COMMITTED,
        propagation = Propagation.SUPPORTS,
        readOnly = false,
        timeout = 30)
public class ProductAggregator {
    @Autowired
    private ProductRepository productRepository;

    public ProductAggregator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    //TODO Create article, update article
}
