package com.manriquecms.warehouse.service.configuration;

import com.manriquecms.warehouse.domain.model.article.Article;
import com.manriquecms.warehouse.domain.model.product.Product;
import com.manriquecms.warehouse.infrastructure.repository.article.ProductRepository;
import com.manriquecms.warehouse.infrastructure.repository.article.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class InitialDataLoader {
    private static final Logger logger = LoggerFactory.getLogger(InitialDataLoader.class);
    @Value("${warehouse.initialize}")
    private Boolean initialize;

    @Bean
    CommandLineRunner runner(InventoryInitializer inventoryInitializer,
                             ArticleRepository articleRepository,
                             CatalogInitializer catalogInitializer,
                             ProductRepository productRepository) {
        return args -> {
            if (initialize) {
                logger.info("Initialize process activated");
                List<Article> articles = inventoryInitializer.readArticlesFromJson();
                articles.stream().forEach(a -> articleRepository.save(a));
                logger.info(String.format("Loaded %d articles", articles.size()));

                List<Product> products = catalogInitializer.readProductsFromJson();
                products.stream().forEach(p -> productRepository.save(p));
                logger.info(String.format("Loaded %d products", products.size()));

            }
        };
    }
}
