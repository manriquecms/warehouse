package com.manriquecms.warehouse.domain.model.article.exceptions;

public class NotNumberFormatForStockQuantityException extends Exception {
    private static final String WRONG_FORMAT = "You're not assigning a number to the Stock quantity";
    public NotNumberFormatForStockQuantityException(){
        super(WRONG_FORMAT);
    }
}
