package com.tchemso.metier;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.tchemso.entities.Article;
import com.tchemso.entities.LigneCommandeClient;
import com.tchemso.entities.LigneVente;
import com.tchemso.entities.Vente;

import ch.qos.logback.core.net.server.Client;

public class imp_vente {
	private Vente vente;
	private Map<Long, LigneVente> ligneVente = new HashMap<Long, LigneVente>();

/*	@Override
	public void creerCommandeClient(Client client) {
		// pour l'ajout d'une commande
		if (client == null) {
			return;
		}
		vente = new Vente();
		vente.setClient(client);
		vente.setDateCmdClient(new Date());
		vente.setCodeCmdClient(genererCodeCommande());
	}*/

	/*@Override
	public LigneVente ajouterLigneCommande(Article article) {
		// ajouter un ligne de commande
		if (article == null) {
			return null;
		}
		LigneCommandeClient lc = ligneVente.get(article.getIdArticle());

		if (lc != null) {
			BigDecimal qte = lc.getQuantite().add(BigDecimal.ONE);
			lc.setQuantite(qte);
			ligneVente.put(article.getIdArticle(), lc);
			// return lc;
		} else {
			LigneCommandeClient ligne = new LigneCommandeClient();
			ligne.setCommandeClient(vente);
			ligne.setQuantite(BigDecimal.ONE);
			ligne.setPrix(BigDecimal.valueOf(article.getPrixUnitaireTTC()));
			ligne.setArticle(article);
			ligneVente.put(article.getIdArticle(), ligne);
			return ligne;
		}

		return null;
	}*/

	/*@Override
	public LigneCommandeClient suprimerLigneCommande(Article article) {
		// suppresion de ligne de commande
		if (article == null) {
			return null;
		}
		return ligneVente.remove(article.getIdArticle());
	}*/

	/*@Override
	public LigneCommandeClient modifierQuantite(Article article, double qte) {
		// TODO Auto-generated method stub
		if (article == null) {
			return null;
		}
		LigneCommandeClient lc = ligneVente.get(article.getIdArticle());
		if (lc == null) {
			return null;
		}
		lc.setQuantite(BigDecimal.valueOf(qte));

		return lc;
	}*/

	/*@Override
	public String genererCodeCommande() {
		// TODO Auto-generated method stub
		return UUID.randomUUID().toString().replaceAll("-", "".toUpperCase());
	}

	public Vente getVente() {
		return vente;
	}

	public void setVente(Vente vente) {
		this.vente = vente;
	}

	public Map<Long, LigneCommandeClient> getLigneVente() {
		return ligneVente;
	}

	public void setLignCmde(Map<Long, LigneCommandeClient> lignCmde) {
		this.ligneVente = lignCmde;
	}
*/
}
