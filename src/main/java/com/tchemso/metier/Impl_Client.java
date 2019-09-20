package com.tchemso.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tchemso.dao.ClientRepository;
import com.tchemso.entities.Client;
import com.tchemso.exception.GalogbeExceptions;

@Service
@Transactional
public class Impl_Client implements I_client {
	@Autowired
	ClientRepository clientRep;

	private Sort sortByDesignationAsc() {
		return new Sort(Sort.Direction.ASC, "nom");
	}

	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "idClient");
	}

	@Override
	public Client ajouterClient(Client client) throws GalogbeExceptions {
		// on aimerait voir si l'utilisateur n'a pas enregistré num de vol inferieur à 0
		if (client.getNumVol() > 0) {
			Client clients = clientRep.save(client);

			if (clients == null) {// on verifie se l'enregistrement a eu lieu
				throw new GalogbeExceptions("Enregistrement echoué");
			} else {
				return clients;
			}

		} else {
			throw new GalogbeExceptions("le numero d'un vol doit etre suppérieur à 0");
		}
		// TODO Auto-generated method stub

	}

	@Override
	public List<Client> ConsulterClients()  {
		// TODO Auto-generated method stub
		List<Client> clients = clientRep.findAll(sortByDesignationAsc());
		if (clients == null) {
			throw new RuntimeException("probleme d affichages des clients");
		}
		return clients;
	}

	@Override
	public void supprimerClient(Long id) {
		// TODO Auto-generated method stub
		clientRep.deleteById(id);

	}

	@Override
	public Client unClient(Long id) {
		// recherche d'un client
		return clientRep.getOne(id);
	}

}
