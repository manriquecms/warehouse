package com.manriquecms.warehouse.domain.model.article.exceptions;

public class NotNumberFormatForStockQuantity extends Exception {
    private static final String WRONG_FORMAT = "You're not assigning a number to the Stock quantity";
    public NotNumberFormatForStockQuantity(){
        super(WRONG_FORMAT);
    }
}
