package com.tchemso.entities;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class LigneVente implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idLigneVent;
	@NotNull
	@DecimalMin(value = "1")//juste dire que la quantité acheter ne doit commencer à 1
	private int  quantite;
	private int prix;
	@Transient
	private int prixTotal;
	
	@ManyToOne
	@JoinColumn(name = "idArticle")
	private Article article;
	
	@ManyToOne
	@JoinColumn(name = "idVente")
	private Vente vente;
	
	public LigneVente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LigneVente(int quantite, int prix, Article article, Vente vente) {
		super();
		this.quantite = quantite;
		this.prix = prix;
		this.article = article;
		this.vente = vente;
	}
	public Long getIdLigneVent() {
		return idLigneVent;
	}
	public void setIdLigneVent(Long idLigneVent) {
		this.idLigneVent = idLigneVent;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public int getPrixTotal() {
		// c'est pour calculer la valeur de chaque chaque article en foncton de la quantte
				prixTotal = quantite*prix;
				return prixTotal;
			
	}
	public void setPrixTotal(int prixTotal) {
		this.prixTotal = prixTotal;
	}
	       @JsonIgnore
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public Vente getVente() {
		return vente;
	}
	public void setVente(Vente vente) {
		this.vente = vente;
	}
	
}
