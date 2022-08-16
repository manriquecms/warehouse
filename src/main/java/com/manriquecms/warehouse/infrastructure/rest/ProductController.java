package com.manriquecms.warehouse.infrastructure.rest;

import com.manriquecms.warehouse.domain.model.product.Product;
import com.manriquecms.warehouse.service.exception.ProductNotFoundException;
import com.manriquecms.warehouse.service.query.ProductQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductQuery productQuery;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getAllProducts() {
        return productQuery.getAllProducts();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable String id) throws ProductNotFoundException {
        return productQuery.getProductById(id);
    }

}
