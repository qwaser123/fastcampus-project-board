package org.fastcampus.projectboard.repository;

import org.fastcampus.projectboard.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}