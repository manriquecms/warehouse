package com.manriquecms.warehouse.infrastructure.rest;

import com.manriquecms.warehouse.domain.model.product.Product;
import com.manriquecms.warehouse.service.exception.ProductNotFoundException;
import com.manriquecms.warehouse.service.query.ProductsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductsQuery productsQuery;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getAllProducts() {
        return productsQuery.getAllProducts();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable String id) throws ProductNotFoundException {
        return productsQuery.getProductById(id);
    }

}
