package com.tchemso.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LigneCommandeFourniseur implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idLigneCmdFournisseur;
	@ManyToOne
	@JoinColumn(name = "idArticle")
	private Article article;
	@ManyToOne
	@JoinColumn(name = "idCmdFourniseur")
	private CommandeFournisseur commandeFournisseur;

}
