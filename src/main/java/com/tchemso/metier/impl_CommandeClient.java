package com.tchemso.metier;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.tchemso.entities.Article;
import com.tchemso.entities.Client;
import com.tchemso.entities.CommandeClient;
import com.tchemso.entities.LigneCommandeClient;

@Component
public class impl_CommandeClient implements I_commandeClient {
	private CommandeClient commande;
	private Map<Long, LigneCommandeClient> lignCmde = new HashMap<Long, LigneCommandeClient>();

	@Override
	public void creerCommandeClient(Client client) {
		// pour l'ajout d'une commande
		if (client == null) {
			return;
		}
		commande = new CommandeClient();
		commande.setClient(client);
		commande.setDateCmdClient(new Date());
		commande.setCodeCmdClient(genererCodeCommande());
	}

	@Override
	public LigneCommandeClient ajouterLigneCommande(Article article) {
		// ajouter un ligne de commande
		if (article == null) {
			return null;
		}
		LigneCommandeClient lc = lignCmde.get(article.getIdArticle());

		if (lc != null) {
			int qte = lc.getQuantite()+1;
			lc.setQuantite(qte);
			lignCmde.put(article.getIdArticle(), lc);
			// return lc;
		} else {
			LigneCommandeClient ligne = new LigneCommandeClient();
			ligne.setCommandeClient(commande);
			ligne.setQuantite(Integer.BYTES);
			ligne.setPrix(Integer.parseInt(String.valueOf((article.getPrixUnitaireTTC()))));
			ligne.setArticle(article);
			lignCmde.put(article.getIdArticle(), ligne);
			return ligne;
		}

		return null;
	}

	@Override
	public LigneCommandeClient suprimerLigneCommande(Article article) {
		// suppresion de ligne de commande
		if (article == null) {
			return null;
		}
		return lignCmde.remove(article.getIdArticle());
	}

	@Override
	public LigneCommandeClient modifierQuantite(Article article, int qte) {
		// TODO Auto-generated method stub
		if (article == null) {
			return null;
		}
		LigneCommandeClient lc = lignCmde.get(article.getIdArticle());
		if (lc == null) {
			return null;
		}
		lc.setQuantite(qte);

		return lc;
	}

	@Override
	public String genererCodeCommande() {
		// TODO Auto-generated method stub
		return UUID.randomUUID().toString().replaceAll("-", "".toUpperCase());
	}

	public CommandeClient getCommande() {
		return commande;
	}

	public void setCommande(CommandeClient commande) {
		this.commande = commande;
	}

	public Map<Long, LigneCommandeClient> getLignCmde() {
		return lignCmde;
	}

	public void setLignCmde(Map<Long, LigneCommandeClient> lignCmde) {
		this.lignCmde = lignCmde;
	}

}
