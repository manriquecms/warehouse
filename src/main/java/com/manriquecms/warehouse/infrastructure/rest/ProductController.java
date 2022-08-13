package com.manriquecms.warehouse.infrastructure.rest;

import com.manriquecms.warehouse.domain.model.product.Product;
import com.manriquecms.warehouse.domain.model.product.ProductBuildable;
import com.manriquecms.warehouse.domain.model.product.ProductSale;
import com.manriquecms.warehouse.service.exception.ProductNotFoundException;
import com.manriquecms.warehouse.service.query.ProductsAvailableQuery;
import com.manriquecms.warehouse.service.query.ProductsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductsQuery productsQuery;
    @Autowired
    ProductsAvailableQuery productsAvailableQuery;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    List<Product> getAll() {
        return productsQuery.getAllProducts();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    Product getById(@PathVariable String id) throws ProductNotFoundException {
        return productsQuery.getProductById(id);
    }

    @RequestMapping(value = "/products/available", method = RequestMethod.GET)
    List<ProductBuildable> getAllAvailable(){
        return productsAvailableQuery.getAvailableProducts();
    }

    @RequestMapping(value = "/products/sell", method = RequestMethod.POST)
    Product sellProduct(@RequestBody List<ProductSale> productsSold){
        //return productsQuery.sellProduct(product);
        return null;
    }
}
