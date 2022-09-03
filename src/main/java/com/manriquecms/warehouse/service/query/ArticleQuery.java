package com.manriquecms.warehouse.service.query;

import com.manriquecms.warehouse.domain.dto.ArticleDto;
import com.manriquecms.warehouse.infrastructure.repository.ArticleRepository;
import com.manriquecms.warehouse.service.exception.ArticleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ArticleQuery {
    @Autowired
    private ArticleRepository articleRepository;

    public ArticleQuery(){}

    public List<ArticleDto> getAllArticles(){
        return StreamSupport.stream(articleRepository.findAll().spliterator(),false)
                .map(a -> ArticleDto.fromArticle(a))
                .collect(Collectors.toList());
    }

    public ArticleDto getArticleById(String id) throws ArticleNotFoundException {
        return ArticleDto.fromArticle(articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id)));
    }
}
