package com.manriquecms.warehouse.domain.model.article.exceptions;

public class InitializingStockNegativeNotAllowed extends Exception {
    private static final String STOCK_NEGATIVE_NOT_ALLOWED =
            "You're trying to initialize the Stock with a negative number and it's not allowed";
    public InitializingStockNegativeNotAllowed(){
        super(STOCK_NEGATIVE_NOT_ALLOWED);
    }
}
