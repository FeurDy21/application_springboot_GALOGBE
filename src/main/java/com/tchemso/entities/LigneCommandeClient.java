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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;



@Entity
public class LigneCommandeClient implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idLigneCmdCtl;
	@NotNull
	@DecimalMin(value = "1")
	private int quantite;
	private int prix;
	@Transient
	private int prixTotal;
	@ManyToOne
	@JoinColumn(name = "idCmdClient")
	private CommandeClient commandeClient;
	@ManyToOne
	@JoinColumn(name = "idArticle")
	private Article article;
	

	public LigneCommandeClient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LigneCommandeClient(int quantite, int prix, Article article, CommandeClient commandeClient) {
		this.quantite = quantite;
		this.prix = prix;
		this.article = article;
		this.commandeClient = commandeClient;
	}

	public Long getIdLigneCmdCtl() {
		return idLigneCmdCtl;
	}

	public void setIdLigneCmdCtl(Long idLigneCmdCtl) {
		this.idLigneCmdCtl = idLigneCmdCtl;
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

	
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}


	public CommandeClient getCommandeClient() {
		return commandeClient;
	}

	public void setCommandeClient(CommandeClient commandeClient) {
		this.commandeClient = commandeClient;
	}

	public int getPrixTotal() {
		// c'est pour calculer la valeur de chaque
		prixTotal = quantite*prix;
		return prixTotal;
	}

	public void setPrixTotal(int prixTotal) {
		this.prixTotal = prixTotal;
	}

}
