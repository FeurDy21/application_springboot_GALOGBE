package com.tchemso.entities;

import java.io.IOException;



import java.io.Serializable;

import java.math.BigDecimal;
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
import javax.persistence.Transient;

@Entity
public class CommandeClient implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idCmdClient;
	private String codeCmdClient;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCmdClient;
	@Transient
	private int totalCommande;
	@Transient
	private int nbArticleCommande;
	@Transient
	public String ligneCommandeJson;
	@ManyToOne
	@JoinColumn(name = "idClient")
	private Client client;
	@OneToMany(mappedBy ="commandeClient")
	private List<LigneCommandeClient> ligneCommandeClients;

	public CommandeClient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommandeClient(String codeCmdClient, Date dateCmdClient, int totalCommande, Client client) {
		super();
		this.codeCmdClient = codeCmdClient;
		this.dateCmdClient = dateCmdClient;
		this.totalCommande = totalCommande;
		this.client = client;

	}

	public Long getIdCmdClient() {
		return idCmdClient;
	}

	public void setIdCmdClient(Long idCmdClient) {
		this.idCmdClient = idCmdClient;
	}

	public String getCodeCmdClient() {
		return codeCmdClient;
	}

	public void setCodeCmdClient(String codeCmdClient) {
		this.codeCmdClient = codeCmdClient;
	}

	public Date getDateCmdClient() {
		return dateCmdClient;
	}

	public void setDateCmdClient(Date dateCmdClient) {
		this.dateCmdClient = dateCmdClient;
	}

	public int getTotalCommande() {
		this.totalCommande = 0;
		if (!ligneCommandeClients.isEmpty()) {
			for (LigneCommandeClient ligneCommandeClient : this.ligneCommandeClients) {
				int totaligne = ligneCommandeClient.getQuantite()*ligneCommandeClient.getPrix();

				this.totalCommande = this.totalCommande+totaligne;
			}
		}
		this.setTotalCommande(totalCommande);
		return this.totalCommande;
	}

	public int getNbArticleCommande() {

		// pour compter le nombre d'artile enregister sous le nom d'une categorie
		if (!ligneCommandeClients.isEmpty())
			for (LigneCommandeClient ligneCommandeClient : ligneCommandeClients) {

				if (ligneCommandeClient.getArticle() != null)

					this.nbArticleCommande++;
			}

		return nbArticleCommande;
	}

	public void setNbArticleCommande(int nbArticleCommande) {
		this.nbArticleCommande = nbArticleCommande;
	}

	public void setTotalCommande(int totalCommande) {

		this.totalCommande = totalCommande;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<LigneCommandeClient> getLigneCommandeclients() {
		return ligneCommandeClients;
	}

	public void setLigneCommandeclients(List<LigneCommandeClient> ligneCommandeclients) {
		this.ligneCommandeClients = ligneCommandeclients;
	}

	/*public String getLigneCommandeJson() {
		if (!ligneCommandeclients.isEmpty()) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				// System.out.println("bien JSON
				// "+mapper.writeValueAsString(ligneCommandeclients));
				this.ligneCommandeJson = mapper.writeValueAsString(ligneCommandeclients);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return this.ligneCommandeJson;
	}*/

}
