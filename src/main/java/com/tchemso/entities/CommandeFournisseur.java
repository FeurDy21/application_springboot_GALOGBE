package com.tchemso.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CommandeFournisseur implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idCmdFourniseur;
	private Long code;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCmdFournisseur;
	@ManyToOne
	@JoinColumn(name = "idFournisseur")
	private Fournisseur fournisseur;

	@OneToMany(mappedBy = "commandeFournisseur")
	private List<LigneCommandeFourniseur> ligneCommandeFournisseurs;
}
