package com.tchemso.web;

import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tchemso.entities.Client;
import com.tchemso.exception.GalogbeExceptions;
import com.tchemso.metier.Impl_Client;

@Controller
public class ClientControler {

	@Autowired
	Impl_Client imp_client;

	/**
	 * pour laffichage de la page concernant les clients
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/client")
	private String viewClients(Model model, String succes, String ajour) {
		// le parametre susses va nous permetre de reconnaitre qu'un client est bien
		// ajouté
		try {
			// on essai d'aficher les client à l'affichage de la page
			model.addAttribute("listeClients", imp_client.ConsulterClients());
			model.addAttribute("clients", new Client());
			model.addAttribute("ok", succes);
			model.addAttribute("updateClient", ajour);
			model.addAttribute("add", true);
		} catch (GalogbeExceptions e) {
			// s'in une eccption est lévée, on aimerait l'afficher
			model.addAttribute("exception", e);
		}
		// on passe le model afin de recupperer les données dans la classe en vu de les
		// afficher eventuellement dans la page à aficher
		model.addAttribute("clients", new Client());

		return "clients";
	}

	/**
	 * pour l'enregistrement d'un client elle peu faire office de mise ajour d'un
	 * client
	 * 
	 * @param model
	 * @param client
	 * @param bindingResult
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ajouterClient", method = RequestMethod.POST)
	private String AjouterClient(Model model, @Valid Client client, BindingResult bindingResult, Long id) {
		if (bindingResult.hasErrors()) {
			try {
				model.addAttribute("listeClients", imp_client.ConsulterClients());
				model.addAttribute("clients", new Client());
				model.addAttribute("add", true);
			} catch (GalogbeExceptions e) {
				// TODO Auto-generated catch block
				model.addAttribute("exception", e);
			}
			// on verifie ici si les données correspondent à ce que nous demandons dans la
			// classe
			return "clients";
		}

		try {
			// on fait l'ajout du client s'il n'ya pas d'exeptiion et on le passe au model
			// pour pourvoir l'afficher si possible
			client.setDateInscrit(new Date());
			model.addAttribute("client", imp_client.ajouterClient(client));

			model.addAttribute("add", true);// ceci n'est qu'un drapeau qui va permetre de visualiser le button ajouter
											// des clients
		} catch (GalogbeExceptions e) {
			// TODO Auto-generated catch block
			model.addAttribute("exception", e);
		}

		return "redirect:/client?succes='bien'";
	}

	@RequestMapping(value = "/ajouterClien", method = RequestMethod.POST)
	private String updateClient(Model model, @Valid Client client, BindingResult bindingResult, Long id) {
		if (bindingResult.hasErrors()) {
			try {
				model.addAttribute("listeClients", imp_client.ConsulterClients());
				model.addAttribute("clients", new Client());
				model.addAttribute("add", true);
			} catch (GalogbeExceptions e) {
				// TODO Auto-generated catch block
				model.addAttribute("exception", e);
			}
			// on verifie ici si les données correspondent à ce que nous demandons dans la
			// classe
			return "clients";
		}

		try {
			// on fait l'ajout du client s'il n'ya pas d'exeptiion et on le passe au model
			// pour pourvoir l'afficher si possible
			client.setDateInscrit(new Date());
			model.addAttribute("client", imp_client.ajouterClient(client));

			model.addAttribute("add", true);// ceci n'est qu'un drapeau qui va permetre de visualiser le button ajouter
											// des clients
		} catch (GalogbeExceptions e) {
			// TODO Auto-generated catch block
			model.addAttribute("exception", e);
		}

		return "redirect:/client?ajour='bien'";
	}

	@GetMapping("/updateClient")
	private String AfficherClient(Model model, Long id) {// ce son les parametre que attendent mes valeurs
		model.addAttribute("listeClients", imp_client.ConsulterClients());
		model.addAttribute("clients", imp_client.unClient(id));
		model.addAttribute("update", true);

		return "clients";
	}

	/**
	 * pour la quppression d'un compte
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/deleteClient")
	private String deleteClient(Model model, Long id) {// ce son les parametre que attendent mes valeurs

		imp_client.supprimerClient(id);

		return "redirect:/client";
	}
}
