package org.fastcampus.projectboard.repository;

import org.fastcampus.projectboard.config.JpaConfig;
import org.fastcampus.projectboard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("testdb")
@DisplayName("Jpa 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
//@ContextConfiguration(classes = FastCampusProjectBoardApplication.class)
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository
    ) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select Test")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {
        // Given

        // When
        List<Article> articles = articleRepository.findAll();

        // Then
        assertThat(articles).isNotNull().hasSize(0);
    }

    @DisplayName("insert Test")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {
        // Given
        long previousCount = articleRepository.count();
        // When
        Article savedArticle = articleRepository.save(Article.of("new article", "new content", "new hashtag"));
        // Then
        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);
    }

    @DisplayName("update Test")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {
        // Give
        Article article = articleRepository.findById(1L).orElseThrow();
        String updatedHashtag = "#springboot";
        article.setHashtag(updatedHashtag);
        // When
        Article savedArticle = articleRepository.saveAndFlush(article);
        // Then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
    }
}