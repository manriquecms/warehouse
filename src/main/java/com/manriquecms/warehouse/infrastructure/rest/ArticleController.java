package com.manriquecms.warehouse.infrastructure.rest;

import com.manriquecms.warehouse.domain.dto.ArticleDto;
import com.manriquecms.warehouse.domain.model.Article;
import com.manriquecms.warehouse.service.aggregator.ArticleAggregator;
import com.manriquecms.warehouse.service.command.CreateArticleCommand;
import com.manriquecms.warehouse.service.command.UpdateArticleCommand;
import com.manriquecms.warehouse.service.exception.ArticleNotFoundException;
import com.manriquecms.warehouse.service.query.ArticleQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ArticleController {
    @Autowired
    ArticleQuery articleQuery;

    @Autowired
    ArticleAggregator articleAggregator;

    //@RequestMapping(value = "/articles", method = RequestMethod.GET)
    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public List<ArticleDto> getAllArticles(){
        return articleQuery.getAllArticles();
    }

    @RequestMapping(value = "/articles/{id}", method = RequestMethod.GET)
    public ArticleDto getArticleById(@PathVariable String id) throws ArticleNotFoundException {
        return articleQuery.getArticleById(id);
    }

    @RequestMapping(value="/articles", method = RequestMethod.POST)
    public List<Article> addArticles(@RequestBody List<CreateArticleCommand> createArticleCommands){
        return createArticleCommands.stream().map(createArticleCommand -> {
            return articleAggregator.handleCreateArticleCommand(createArticleCommand);
        }).collect(Collectors.toList());
    }

    @RequestMapping(value="/articles/{id}", method = RequestMethod.PUT)
    public Article updateArticle(@PathVariable String id, @RequestBody UpdateArticleCommand updateArticleCommand){
        updateArticleCommand.setArticleId(id);
        return articleAggregator.handleUpdateArticleCommand(updateArticleCommand);
    }

}
