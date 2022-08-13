package com.manriquecms.warehouse.domain.model.article.exceptions;

public class NotEnoughStockToReduce extends Exception {
    private static final String NOT_ENOUGH_STOCK_TO_REDUCE =
            "You're trying to reduce the stock in %d units but only %d are available";
    public NotEnoughStockToReduce (Integer units, Integer quantity){
        super(String.format(NOT_ENOUGH_STOCK_TO_REDUCE, units, quantity));
    }
}
