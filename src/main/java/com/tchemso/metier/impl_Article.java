package com.tchemso.metier;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tchemso.dao.ArticleRepository;
import com.tchemso.entities.Article;

@Service
public class impl_Article implements I_article {

	@Autowired
	ArticleRepository articleRepository;

	private Sort sortByDesignationAsc() {
		return new Sort(Sort.Direction.ASC, "designation");
	}
   public int prixArticle(Long id) {
	   int prix=articleRepository.RecherchePrixArticle(id);
	   if(prix==0) {
		   throw new RuntimeException("Le prix de cet article est introuvable");
	   }
	   return prix;
   }
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "idArticle");
	}

	@Override
	public Article AjouterArticle(Article article) {
		return articleRepository.save(article);

	}

	@Override
	public List<Article> getAllArticle() {
		return articleRepository.findAll(sortByDesignationAsc());
	}

	@Override
	public void deleteArticle() {
		// TODO Auto-generated method stub

	}

	@Override
	public Article getArticleByCode(String code) {
		// TODO Auto-generated method stub
		return articleRepository.getArticleByCodeArticle(code);

	}
	
	

}
