package com.tchemso.metier;

import com.tchemso.entities.Article;
import com.tchemso.entities.Client;
import com.tchemso.entities.LigneCommandeClient;

public interface I_Vente {
	public void creerCommandeClient(Client client);

	public LigneCommandeClient ajouterLingeVente(Article article);

	public LigneCommandeClient suprimerLigneVente(Article article);

	public LigneCommandeClient modifierQuantite(Article article, double qte);

	public String genererCodeCommande();
}
