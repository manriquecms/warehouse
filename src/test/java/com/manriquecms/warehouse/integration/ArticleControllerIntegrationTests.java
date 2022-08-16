package com.manriquecms.warehouse.integration;

import com.manriquecms.warehouse.WarehouseApplication;
import com.manriquecms.warehouse.domain.model.article.Article;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = WarehouseApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArticleControllerIntegrationTests {
    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    // Expected values from test case file inventory.json
    private final Article ARTICLE_EXISTS = new Article("1","leg",12);
    private final Article ARTICLE_NEW = new Article("10","new article",15);
    private final Integer NUMBER_OF_ARTICLES = 4;

    @Test
    @Order(1)
    public void givenArticles_whenGetArticles_thenStatus200AndContentIsExpected()
            throws Exception {

        ResponseEntity<Article[]> response = restTemplate.getForEntity(getBaseUrl()+"/articles",Article[].class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().length, NUMBER_OF_ARTICLES);
    }

    @Test
    public void givenArticles_whenGetArticleWithId1_thenStatus200AndContentIsExpected()
            throws Exception {
        String urlWithArticleId = String.format("%s/articles/%s", getBaseUrl(), ARTICLE_EXISTS.getId());
        ResponseEntity<Article> response = restTemplate.getForEntity(urlWithArticleId,Article.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody(), ARTICLE_EXISTS);
    }

    @Test
    public void givenArticles_whenGetArticleNotExists_thenStatus404()
            throws Exception {
        ResponseEntity<Article> response = restTemplate.getForEntity(getBaseUrl()+"/articles/999",Article.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void givenArticles_whenAddNewArticleAndNotExists_thenStatus200()
            throws Exception {
        HttpEntity<Article[]> request = new HttpEntity<>(new Article[]{ARTICLE_NEW});
        ResponseEntity<Article[]> response = restTemplate.postForEntity(
                getBaseUrl()+"/articles", request ,Article[].class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void givenArticles_whenAddNewArticleAndIsNotAList_thenStatus400()
            throws Exception {

        HttpEntity<Article> request = new HttpEntity<>(ARTICLE_EXISTS);
        ResponseEntity<Object> response = restTemplate.postForEntity(
                getBaseUrl()+"/articles", request ,Object.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void givenArticles_whenAddNewArticleAndAlreadyExists_thenStatus406()
            throws Exception {

        HttpEntity<Article[]> request = new HttpEntity<>(new Article[]{ARTICLE_EXISTS});
        ResponseEntity<Object> response = restTemplate.postForEntity(
                getBaseUrl()+"/articles", request ,Object.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_ACCEPTABLE);
    }

    private String getBaseUrl(){
        return String.format("http://localhost:%d", port);
    }

}
