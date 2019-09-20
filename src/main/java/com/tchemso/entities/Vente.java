package com.tchemso.entities;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;





@Entity
public class Vente implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idVente;
	private String codeVente;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateVente;
	@OneToMany(mappedBy = "vente")
	private List<LigneVente> ligneVentes;
	@Transient
	private int  totalVente;
	@Transient
	private int nbArticleVendus;
	
	public Long getIdVente() {
		return idVente;
	}

	public void setIdVente(Long idVente) {
		this.idVente = idVente;
	}
	
	

	public void setTotalVente(int totalCommande) {
		this.totalVente = totalCommande;
	}

	public String getCodeVente() {
		return codeVente;
	}

	public void setCodeVente(String codeVente) {
		this.codeVente = codeVente;
	}

	public Date getDateVente() {
		return dateVente;
	}

	public void setDateVente(Date dateVente) {
		this.dateVente = dateVente;
	}
   @JsonIgnore
	public List<LigneVente> getLigneVentes() {
		return ligneVentes;
	}

	public void setLigneVentes(List<LigneVente> ligneVentes) {
		this.ligneVentes = ligneVentes;
	}

	public int getTotalVente(){
		this.totalVente = 0;
		if (!ligneVentes.isEmpty()) {
			for (LigneVente ligneVente : this.ligneVentes) {
				int totaligne = ligneVente.getQuantite()*ligneVente.getPrix();

				this.totalVente = this.totalVente+totaligne;
			}
		}
		
		this.setTotalVente(totalVente);
		return this.totalVente;
	}
	
	public int getNbArticleVendus() {
		this.nbArticleVendus=0;
		// pour compter le nombre d'artile enregister sous le nom d'une categorie
		if (!ligneVentes.isEmpty())
			
			for (LigneVente ligneVente : ligneVentes) {

				if (ligneVente.getArticle() != null)

					this.nbArticleVendus++;
			}

		return nbArticleVendus;
	}

	
	
}
