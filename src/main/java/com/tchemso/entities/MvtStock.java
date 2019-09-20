package com.tchemso.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;

@Entity
public class MvtStock implements Serializable {
	private static int ENTREE = 1;
	private static int SORTIE = 2;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idMvt;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateMvt;
	@DecimalMin(value = "1")
	private double quantite;
	private int typeMvt;
	@ManyToOne
	@JoinColumn(name = "idArticle")
	private Article article;

	public Long getIdMvt() {
		return idMvt;
	}

	public void setIdMvt(Long idMvt) {
		this.idMvt = idMvt;
	}

	public Date getDateMvt() {
		return dateMvt;
	}

	public void setDateMvt(Date dateMvt) {
		this.dateMvt = dateMvt;
	}

	public double getQuantite() {
		return quantite;
	}

	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}

	public int getTypeMvt() {
		return typeMvt;
	}

	public void setTypeMvt(int typeMvt) {
		this.typeMvt = typeMvt;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

}
