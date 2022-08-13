package com.manriquecms.warehouse.infrastructure.repository.article;

import com.manriquecms.warehouse.domain.model.article.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository<Article,String> {

}
