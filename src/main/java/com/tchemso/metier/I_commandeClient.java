package com.tchemso.metier;

import com.tchemso.entities.Article;
import com.tchemso.entities.Client;
import com.tchemso.entities.LigneCommandeClient;

public interface I_commandeClient {
	public void creerCommandeClient(Client client);

	public LigneCommandeClient ajouterLigneCommande(Article article);

	public LigneCommandeClient suprimerLigneCommande(Article article);

	public LigneCommandeClient modifierQuantite(Article article, int qte);

	public String genererCodeCommande();
}
