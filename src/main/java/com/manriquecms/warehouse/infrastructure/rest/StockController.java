package com.manriquecms.warehouse.infrastructure.rest;

import com.manriquecms.warehouse.domain.model.product.ProductBuildable;
import com.manriquecms.warehouse.service.query.ProductsAvailableQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StockController {
    @Autowired
    ProductsAvailableQuery productsAvailableQuery;

    @RequestMapping(value = "/stock", method = RequestMethod.GET)
    public List<ProductBuildable> getProductStock(){
        return productsAvailableQuery.getAvailableProducts();
    }
}
