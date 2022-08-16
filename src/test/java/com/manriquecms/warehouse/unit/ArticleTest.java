package com.manriquecms.warehouse.unit;

import com.manriquecms.warehouse.domain.model.article.Article;
import com.manriquecms.warehouse.domain.model.article.exceptions.NotEnoughStockToReduceException;
import com.manriquecms.warehouse.domain.model.article.exceptions.NotNumberFormatForStockQuantityException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ArticleTest {

    @Test
    public void givenArticle_WhenReduceMoreStockThanAvailable_ThenRaiseNotEnoughStockToReduceException() {
        final String EXCEPTION_MESSAGE = "You're trying to reduce the stock in 11 units but only 10 are available";

        Article article = new Article("1","article_1",10);
        NotEnoughStockToReduceException notEnoughStockException = Assertions.assertThrows(NotEnoughStockToReduceException.class, () -> {
            article.reduceStock(11);
        }, EXCEPTION_MESSAGE);
    }

    @Test
    public void givenArticle_WhenSetStockNotNumeric_ThenRaiseException() {
        final String EXCEPTION_MESSAGE = "You're not assigning a number to the Stock quantity";

        Article article = new Article("1","article_1",10);
        NotNumberFormatForStockQuantityException notNumberFormatForStockQuantityException
                = Assertions.assertThrows(NotNumberFormatForStockQuantityException.class, () -> {
            article.setStock("not_number");
        }, EXCEPTION_MESSAGE);
    }

    @Test
    public void givenArticle_WhenModifyStock_ThenNewStockQuantityIsExpected (){
        Article article = new Article("1","article_1",10);

        article.incrementStock(20);
        Assertions.assertEquals(article.getStock(), 30);

        Assertions.assertDoesNotThrow(() -> {
            article.reduceStock(22);
        });
        Assertions.assertEquals(article.getStock(), 8);

    }

}
