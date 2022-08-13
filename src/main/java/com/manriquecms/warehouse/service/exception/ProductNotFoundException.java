package com.manriquecms.warehouse.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductNotFoundException  extends RuntimeException{
    private static final String NOT_FOUND= "Product %s not found";
    public ProductNotFoundException(String id) {
        super(String.format(NOT_FOUND,id));
    }
}
