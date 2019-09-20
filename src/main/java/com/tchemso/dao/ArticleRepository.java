package com.tchemso.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tchemso.entities.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>, CrudRepository<Article, Long> {
     
	  @Query("select a.prixUnitaireHT from Article a where a.idArticle=:x")
	   public int RecherchePrixArticle(@Param("x") Long id);
	 
	/*
	 * @Query("select p from article p where p.designation like :y") public
	 * Page<Article> RechercheCommande(@Param("y") String mc, Pageable pageable);
	 */
	public Article getArticleByCodeArticle(String code);

	public Article getArticleByDesignation(String designation);
}
