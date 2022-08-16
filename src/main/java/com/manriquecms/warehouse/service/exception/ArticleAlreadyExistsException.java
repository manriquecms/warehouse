package com.manriquecms.warehouse.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Article already exists")
public class ArticleAlreadyExistsException extends RuntimeException{
    private static final String ALREADY_EXISTS = "Article %s already exists";
    public ArticleAlreadyExistsException(String id) {
        super(String.format(ALREADY_EXISTS,id));
    }
}
