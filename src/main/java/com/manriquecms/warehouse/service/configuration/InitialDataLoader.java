package com.manriquecms.warehouse.service.configuration;

import com.manriquecms.warehouse.domain.model.Article;
import com.manriquecms.warehouse.domain.model.Product;
import com.manriquecms.warehouse.infrastructure.repository.ArticleRepository;
import com.manriquecms.warehouse.infrastructure.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Configuration
@Transactional
public class InitialDataLoader {
    private static final Logger logger = LoggerFactory.getLogger(InitialDataLoader.class);
    @Value("${warehouse.initialize}")
    private Boolean initialize;

    @Bean
    CommandLineRunner runner(ArticleInitializer articleInitializer,
                             ArticleRepository articleRepository,
                             ProductInitializer productInitializer,
                             ProductRepository productRepository) {
        return args -> {
            if (initialize) {
                logger.info("Initialize process activated");
                List<Article> articles = articleInitializer.readArticlesFromJson();
                articles.stream().forEach(a -> articleRepository.save(a));
                logger.info(String.format("Loaded %d articles", articles.size()));

                List<Product> products = productInitializer.readProductsFromJson();
                products.stream().forEach(p -> productRepository.save(p));
                logger.info(String.format("Loaded %d products", products.size()));

            }
        };
    }
}
