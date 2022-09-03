package com.manriquecms.warehouse.service.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.manriquecms.warehouse.domain.model.Article;
import com.manriquecms.warehouse.domain.model.Product;
import com.manriquecms.warehouse.infrastructure.repository.ArticleRepository;
import com.manriquecms.warehouse.service.exception.ArticleNotFoundException;

import java.io.IOException;

public class CustomProductDeserializer extends StdDeserializer<Product> {
    private ArticleRepository articleRepository;

    public CustomProductDeserializer(ArticleRepository articleRepository) {
        this(null, articleRepository);
    }

    public CustomProductDeserializer(Class<?> vc, ArticleRepository articleRepository) {
        super(vc);
        this.articleRepository = articleRepository;
    }

    @Override
    public Product deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);

        Product product = new Product(node.get("name").asText());
        node.get("contain_articles").forEach( ci -> {
            String articleId = ci.get("art_id").asText();
            Article article = articleRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException(articleId));
            product.addArticle(article, ci.get("amount_of").asInt());
        });

        return product;
    }
}
