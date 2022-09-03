package com.manriquecms.warehouse.service.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manriquecms.warehouse.domain.model.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ArticleInitializer {
    private static Logger logger = LoggerFactory.getLogger(ArticleInitializer.class);
    @Value("${warehouse.inventory.json.path}")
    private String inventoryJsonPath;
    @Value("${warehouse.inventory.root.value}")
    private String inventoryRootValue;

    public List<Article> readArticlesFromJson() {
        List<Article> articles = null;

        // read json and write to db
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

        TypeReference<List<Article>> typeReference = new TypeReference<List<Article>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream(inventoryJsonPath);
        try {
            logger.info("Reading articles from Json");
            articles = mapper.readerFor(typeReference)
                    .withRootName(inventoryRootValue)
                    .readValue(inputStream);
            logger.info("Read success");
        } catch (IOException e){
            logger.error("Unable to read articles: " + e.getMessage());
        }
        return articles;
    }

}
