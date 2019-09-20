package com.tchemso.entities;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.Transient;

@Entity
public class Categorie implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idCategorie;
	private String code;
	private String designation;
	private String description;
	@Transient
	private int quantiteArticle;
	@OneToMany(mappedBy = "categorie")
	private List<Article> articles;

	public Categorie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Categorie(String code, String designation, String description) {
		super();
		this.code = code;
		this.designation = designation;
		this.description = description;
	}

	public int getQuantiteArticle() {
		// pour compter le nombre d'artile enregister sous le nom d'une categorie
		if (!articles.isEmpty())
			for (Article article : articles) {
				this.quantiteArticle++;
			}
		return quantiteArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public Long getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(Long idCategorie) {
		this.idCategorie = idCategorie;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public List<Article> getArticle() {
		return articles;
	}

	public void setArticle(List<Article> article) {
		this.articles = article;
	}

}
