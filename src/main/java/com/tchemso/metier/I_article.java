package com.tchemso.metier;

import java.util.List;

import com.tchemso.entities.Article;

public interface I_article {

	public Article AjouterArticle(Article article);

	public List<Article> getAllArticle();

	public void deleteArticle();

	Article getArticleByCode(String code);
}
