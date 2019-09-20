package com.tchemso.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tchemso.dao.CategorieRepository;
import com.tchemso.dao.ClientRepository;
import com.tchemso.dao.VenteRepository;
import com.tchemso.dao.commandeClientRepository;
import com.tchemso.entities.Categorie;
import com.tchemso.entities.Client;
import com.tchemso.entities.CommandeClient;
import com.tchemso.entities.Users;
import com.tchemso.entities.Vente;
import com.tchemso.metier.UserService;


@Controller
public class viewControler {
@Autowired
VenteRepository  venteRepository;
@Autowired
commandeClientRepository commandeClientRepository;
@Autowired
ClientRepository clientRepository;
@Autowired
CategorieRepository categorieRepository;
	
@Autowired
UserService userService;

	@RequestMapping(value = "/form.html", method = RequestMethod.GET)
	public String form() {
     
		return "form";
	}

	@GetMapping(value = "/mouvement.html")
	public String mvtstock(Model model) {
		model.addAttribute("activeMouvement", true);
		return "mouvement";
	}

	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String index(Model model) {
    List<Vente> vente= venteRepository.findAll();
    int cpt1=0;
    for(Vente v: vente ) {
    	cpt1++;
    }
    
    List<CommandeClient> commde= commandeClientRepository.findAll();
    int cpt2=0;
    for(CommandeClient c: commde ) {
    	cpt2++;
    }
    
    List<Client> client= clientRepository.findAll();
    int cpt3=0;
    for(Client clt: client ) {
    	cpt3++;
    }
    
    List<Categorie> categorie= categorieRepository.findAll();
    int cpt4=0;
    for(Categorie cat: categorie ) {
    	cpt4++;
    }
    
    double p3= (60/1000)*100; 
    
    //calcul de pourcentage de client atteint
    int c1= 10000-cpt3;  int c2=c1/10000; int c3=c2*100;
    
    //calcul de pourcentage de categorie d'article
    int cat1= 10000-cpt4;  double cat2=cat1/10000; double cat3=cat2*100;
    
    
   /* Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Users user = userService.findUserByEmail(auth.getName());
    model.addAttribute("userName", user.getName() + " " + user.getLastName());
   // model.addAttribute("userRole", user.getRoles());
*/    
    model.addAttribute("nbcategorie", cpt4);
    model.addAttribute("nbclient", cpt3);
    model.addAttribute("nbcommde", cpt2);
    model.addAttribute("nbvente", cpt1);
    
    //calcul de pourcentage de vente effectuées
   
    
    model.addAttribute("pccategorie", cat3);
    model.addAttribute("pcclient", c3);
    //model.addAttribute("pccommde", cpt2);
    model.addAttribute("pcvente", p3);
    
    
		return "index";
	}

}
