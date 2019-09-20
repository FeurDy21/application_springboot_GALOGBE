package com.tchemso.entities;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Client implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idClient;
	@NotNull
	private String nom;
	@NotNull
	private String prenom;
	private String adresse;
	@DecimalMin(value = "1")
	private int numVol;
	@Column(unique = true)
	private String mail;
	private String telephone;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateInscrit;

	@OneToMany(mappedBy = "client")
	private List<CommandeClient> commandeClients;

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(@NotBlank String nom, @NotBlank String prenom, String adresse, @DecimalMin("1") int numVol,
			String mail, String telephone, List<CommandeClient> commandeClients) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.numVol = numVol;
		this.mail = mail;
		this.telephone = telephone;
		this.commandeClients = commandeClients;

	}

	public Date getDateInscrit() {
		return dateInscrit;
	}

	public void setDateInscrit(Date dateInscrit) {
		this.dateInscrit = dateInscrit;
	}

	public int getNumVol() {
		return numVol;
	}

	public void setNumVol(int numVol) {
		this.numVol = numVol;
	}

	public List<CommandeClient> getCommandeClients() {
		return commandeClients;
	}

	public void setCommandeClients(List<CommandeClient> commandeClients) {
		this.commandeClients = commandeClients;
	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
