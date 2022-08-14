package com.manriquecms.warehouse.infrastructure.rest;

import com.manriquecms.warehouse.domain.model.article.Article;
import com.manriquecms.warehouse.service.exception.ArticleNotFoundException;
import com.manriquecms.warehouse.service.query.ArticlesQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {
    @Autowired
    ArticlesQuery articlesQuery;

    //@RequestMapping(value = "/articles", method = RequestMethod.GET)
    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public List<Article> getAllArticles(){
        return articlesQuery.getAllArticles();
    }

    @RequestMapping(value = "/articles/{id}", method = RequestMethod.GET)
    public Article getArticleById(@PathVariable String id) throws ArticleNotFoundException {
        return articlesQuery.getArticleById(id);
    }
}
