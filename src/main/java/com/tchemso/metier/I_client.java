package com.tchemso.metier;

import java.util.List;

import com.tchemso.entities.Client;
import com.tchemso.exception.GalogbeExceptions;

public interface I_client {
	public Client ajouterClient(Client client) throws GalogbeExceptions;

	public List<Client> ConsulterClients();

	public void supprimerClient(Long id);

	public Client unClient(Long id);
}
