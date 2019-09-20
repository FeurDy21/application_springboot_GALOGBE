package com.tchemso.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ManyToAny;

@Entity
public class Article implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idArticle;

	@NotBlank
	@Size(min = 3, max = 10)
	private String codeArticle;
	@NotBlank
	@Size(min = 4, max = 50)
	private String designation;
	@NotNull
	private double prixUnitaireHT;
	@NotNull
	private double tauxTVA;
	private double prixUnitaireTTC;
	private String photo;
	@Transient
	private int quantite;
	@ManyToOne
	@JoinColumn(name = "idCategorie")
	private Categorie categorie;
	
	@OneToMany(mappedBy="article") 
	private List<LigneCommandeClient>  ligneCommandeClients;
	
	@OneToMany(mappedBy="article") 
	private List<LigneVente>  ligneVentes;
	 
	@OneToMany(mappedBy = "article")
	private List<MvtStock> mvtstock;

	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * public Article(String designation, double prix) { super(); this.designation =
	 * designation; this.prixUnitaireHT = prix;
	 * 
	 * }
	 */

	public Article(String codeArticle, String designation, double prixUnitaireHT, double tauxTVA, String photo,
			Categorie categorie) {
		super();
		this.codeArticle = codeArticle;
		this.designation = designation;
		this.prixUnitaireHT = prixUnitaireHT;
		this.tauxTVA = tauxTVA;
		this.photo = photo;
		this.categorie = categorie;
	}

	public Article(String codeArticle, String designation, double prixUnitaireHT, double tauxTVA,
			double prixUnitaireTTC, Categorie categorie) {
		super();
		this.codeArticle = codeArticle;
		this.designation = designation;
		this.prixUnitaireHT = prixUnitaireHT;
		this.tauxTVA = tauxTVA;
		this.prixUnitaireTTC = prixUnitaireTTC;
		this.categorie = categorie;
	}

	public Long getId() {
		return idArticle;
	}

	public void setId(Long id) {
		this.idArticle = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getPrix() {
		return prixUnitaireHT;
	}

	public void setPrix(double prix) {
		this.prixUnitaireHT = prix;
	}

	public String getCodeArticle() {
		return codeArticle;
	}

	public void setCodeArticle(String codeArticle) {
		this.codeArticle = codeArticle;
	}

	public double getPrixUnitaireHT() {
		return prixUnitaireHT;
	}

	public void setPrixUnitaireHT(double prixUnitaireHT) {
		this.prixUnitaireHT = prixUnitaireHT;
	}

	public double getTauxTVA() {
		return tauxTVA;
	}

	public void setTauxTVA(double tauxTVA) {
		this.tauxTVA = tauxTVA;
	}

	public double getPrixUnitaireTTC() {
		return prixUnitaireTTC;
	}
    
	public void setPrixUnitaireTTC(double prixUnitaireTTC) {
		this.prixUnitaireTTC = prixUnitaireTTC;
	}

	public Long getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(Long idArticle) {
		this.idArticle = idArticle;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	/*
	 * public List<LigneCommandeClient> getLigneCommandeClients() { return
	 * ligneCommandeClients; }
	 * 
	 * public void setLigneCommandeClients(List<LigneCommandeClient>
	 * ligneCommandeClients) { this.ligneCommandeClients = ligneCommandeClients; }
	 */
	public List<MvtStock> getMvtstock() {
		return mvtstock;
	}

	public void setMvtstock(List<MvtStock> mvtstock) {
		this.mvtstock = mvtstock;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getQuantite() {
		// c'est une fonction nous permùattant de retrouver le nombre la quantié en
		// stock d'un article donné
		int pos = 0, neg = 0;
		this.quantite = 0;
		if (!mvtstock.isEmpty()) {
			
			for (MvtStock mvtStock : mvtstock) {
				if (mvtStock.getTypeMvt() == 1) {
					//si le type est 1 alors on vient de faire un mouvement d'entré en stock d'un produit 
					pos = (int) mvtStock.getQuantite();
					this.quantite += pos;
				} else if (mvtStock.getTypeMvt() == 2) {
					//si le type est 2 alors c'est une vente sortie efectué dans le stock en car de defaillance du produit
					neg = (int) mvtStock.getQuantite();
					this.quantite -= neg;
				}

			}
		}
		
		if(!ligneVentes.isEmpty()) {
			for(LigneVente ligneVente : ligneVentes) {
				if(ligneVente.getQuantite()!=0)
				neg =ligneVente.getQuantite();
				this.quantite -= neg;
			}
		}

		
		return this.quantite;
	}

}
