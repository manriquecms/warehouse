package com.manriquecms.warehouse.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Not enough stock for the products included on the order")
public class OrderWithNotEnoughStockException extends RuntimeException {
    private static final String NOT_ENOUGH_STOCK = "Not enough stock for the products included on the order";
    public OrderWithNotEnoughStockException() {
        super(NOT_ENOUGH_STOCK);
    }
}
