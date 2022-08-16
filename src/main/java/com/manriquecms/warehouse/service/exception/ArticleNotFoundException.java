package com.manriquecms.warehouse.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Article not found")
public class ArticleNotFoundException extends RuntimeException{
    private static final String NOT_FOUND= "Article %s not found";
    public ArticleNotFoundException(String id) {
        super(String.format(NOT_FOUND,id));
    }
}
