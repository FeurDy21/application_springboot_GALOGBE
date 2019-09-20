package com.tchemso.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tchemso.dao.ArticleRepository;
import com.tchemso.dao.LigneComdClientRepository;
import com.tchemso.dao.commandeClientRepository;
import com.tchemso.entities.Article;
import com.tchemso.entities.CommandeClient;
import com.tchemso.entities.LigneCommandeClient;
import com.tchemso.entities.LigneVente;
import com.tchemso.entities.Vente;
import com.tchemso.metier.Impl_Client;
import com.tchemso.metier.impl_Article;
import com.tchemso.utils.PDFGeneratorCommande;
import com.tchemso.utils.PDFGeneratorVente;


@Controller
public class CommandeClientControler {
	 private CommandeClient idcommande;
	@Autowired
	Impl_Client client;
	@Autowired
	commandeClientRepository commandeClientRepository;
	@Autowired
	LigneComdClientRepository ligneComdClientRepository;
	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	LigneComdClientRepository lignCmd;
	@Autowired
	impl_Article impl_Article;
	
	private String codeGenere() {
		String code = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 2).toUpperCase() + ""
				+ new Date().toString().replaceAll(" ", "").substring(3,8) + ""
				+ new Date().toString().replaceAll(" ", "").substring(19,23);
		
		return code;
	}

	@GetMapping(value = "/detail")
	private String index(Model model, Long id) {

		List<CommandeClient> commandeClients = commandeClientRepository.findAll();

		// on initialies le tableau à un tableau vide mais qui prend les parametre de la
		// fonction
		List<LigneCommandeClient> ligneCmdClt = new ArrayList<LigneCommandeClient>();
		if (commandeClients.isEmpty()) {
			commandeClients = new ArrayList<CommandeClient>();
		} else {
			for(CommandeClient commandeClient :commandeClients) {
			// on essai d'de prendre la liste des detailles d'une commdande
			ligneCmdClt = lignCmd.rechercherLigne(id);
			 System.out.print(commandeClient.getTotalCommande());
			 commandeClient.getIdCmdClient();

			}
		}
		model.addAttribute("commandeclient", commandeClients);
		model.addAttribute("lignecommandeclient", ligneCmdClt);

		return "commandeclient";
	}

	@GetMapping(value = "/commande")
	private String index2(Model model, CommandeClient cmd) {

		List<CommandeClient> commandeClients = commandeClientRepository.findAll();

		// on initialies le tableau à un tableau vide mais qui prend les parametre de la
		// fonction
		List<LigneCommandeClient> ligneCmdClt = new ArrayList<LigneCommandeClient>();
		if (commandeClients.isEmpty()) {
			commandeClients = new ArrayList<CommandeClient>();
		} else {

			for (CommandeClient c : commandeClients) {

				List<LigneCommandeClient> ligneCommandeClient = lignCmd.rechercherLigne(c.getIdCmdClient());
				c.setLigneCommandeclients(ligneCommandeClient);
				for (LigneCommandeClient l : ligneCommandeClient)
					System.out.println(l.getArticle().getCodeArticle());
			}

			// model.addAttribute("lignecommandeclient", ligneCmdClt);

		}

		model.addAttribute("commandeclient", commandeClients);
		return "commandeclient";
	}

	@GetMapping(value="/nouveau")
	public String nouvelleCommande(Model model, Long bien,Long idCmd,Long ok) {
		List<LigneCommandeClient> ligne=new ArrayList<LigneCommandeClient>();
		if(idCmd!=null) {  //ce bloc va permettre de reccuperer les detaille d'une cente en cour de selection et de faire un affichage en meme temps
			ligne=ligneComdClientRepository.rechercherLigne(idCmd);
		}
		
		model.addAttribute("ok", ok);
		model.addAttribute("commandeclient", new CommandeClient());
		model.addAttribute("lignecommandeclient", new LigneCommandeClient());
		model.addAttribute("clients", client.ConsulterClients());
		model.addAttribute("bien", bien);
		model.addAttribute("date", new Date());
		model.addAttribute("code", codeGenere());
        model.addAttribute("listeArticle", impl_Article.getAllArticle());
        model.addAttribute("ligne",ligne);
        model.addAttribute("add",true);
		return "nouvellecommande";
	}
	
	@GetMapping(value = "/nouvellecommand")
	public String nouvelCommande(Model model, Long bien) {
		
		return "redirect:/nouveau";
	}
	
	

	@GetMapping(value = "deleteCmdClient")
	public String annulerCommdande(Long id) {

		lignCmd.deleteLigneCommade(id);
		
		commandeClientRepository.deleteById(id);

		return "redirect:/commande";
	}
	

	@PostMapping(value = "ajoutercommande")
	private String ajouterCommade(Model model, CommandeClient commandeClient,LigneCommandeClient ligne) {
		commandeClient.setDateCmdClient(new Date());
		commandeClient.setCodeCmdClient(codeGenere());
		//on recupere l'enregistremement et et desormais on peut tout recuperer
		 idcommande=commandeClientRepository.save(commandeClient);
		
		//model.addAttribute("cmd", cmd);
		model.addAttribute("commandeclient", new CommandeClient());
		model.addAttribute("lignecommandeclient", new LigneCommandeClient());
		model.addAttribute("listeArticle", impl_Article.getAllArticle());
		model.addAttribute("clients", client.ConsulterClients());
		
		return  "redirect:/nouveau?bien="+idcommande.getIdCmdClient()+"&ok=10";
	}
	
	 
@GetMapping(value="ligne")
private String ajouterArticleCommande(Model model, @Valid LigneCommandeClient ligneCommandeClient,BindingResult bindingResult,int quantite, Long article) {
	//cette une fonction qui permet d'enregistrer la ligne de commande
	if(bindingResult.hasErrors()) {
		model.addAttribute("commandeclient", new CommandeClient());
		model.addAttribute("lignecommande", new LigneCommandeClient());
		model.addAttribute("listeArticle", impl_Article.getAllArticle());
		model.addAttribute("ok", 10);
		model.addAttribute("add",true);
		return "nouvellecommande";
	}
	
	
	ligneCommandeClient.setQuantite(quantite);
	ligneCommandeClient.setPrix(impl_Article.prixArticle(article));
	ligneCommandeClient.setCommandeClient(idcommande);
	ligneCommandeClient.setArticle(articleRepository.getOne(article));
	
	ligneComdClientRepository.save(ligneCommandeClient);
	
	return "redirect:/nouveau?idCmd="+idcommande.getIdCmdClient()+"&ok=10";
}

@GetMapping(value="deleteLigneCommande")
public String  deleteLigne(Model model,Long id) {
	ligneComdClientRepository.deleteById(id);
	return "redirect:/nouveau?idCmd="+idcommande.getIdCmdClient()+"&ok=10";
}


@GetMapping(value="updateLignecommande")
public String  updateLigne(Model model,Long id) {
	 LigneCommandeClient lign= ligneComdClientRepository.getOne(id);
	 
	
      model.addAttribute("ok", "ok");
		model.addAttribute("commandeclient", new CommandeClient());
		model.addAttribute("clients", client.ConsulterClients());
	 
		model.addAttribute("lignecommandeclient", lign);
		model.addAttribute("listeArticle", impl_Article.getAllArticle());
	  model.addAttribute("ok",10);
	  model.addAttribute("update",true);
	  return "nouvellecommande";
	  
}


@GetMapping(value="editerCommande")
public String  editerCommande(Model model,Long id) {
	//cette sonction va permetre d'editer une ligne de commande
	
	 LigneCommandeClient lign= ligneComdClientRepository.getOne(id);
      model.addAttribute("ok", "ok");
		model.addAttribute("commandeclient", new CommandeClient());
		model.addAttribute("clients", client.ConsulterClients());
		model.addAttribute("lignecommandeclient", lign);
		model.addAttribute("listeArticle", impl_Article.getAllArticle());
	  model.addAttribute("ok",10);
	  model.addAttribute("update",true);
	  return "nouvellecommande";
	  
}


@GetMapping(value = "/detailCommande")
private String detailCommande(Model model, Long id) {

	List<CommandeClient> commandeClients = commandeClientRepository.findAll();

	// on initialies le tableau à un tableau vide mais qui prend les parametre de la
	// fonction
	List<LigneCommandeClient> ligneCommandeClients = new ArrayList<LigneCommandeClient>();
	if (commandeClients.isEmpty()) {
		commandeClients = new ArrayList<CommandeClient>();
	} else {
		for(CommandeClient commandeClient :commandeClients) {
		// on essai d'de prendre la liste des detailles d'une commdande
		ligneCommandeClients= ligneComdClientRepository.rechercherLigne(id);
	}
	}
	model.addAttribute("commandeclient", commandeClients);
	model.addAttribute("lignecommandeclient", ligneCommandeClients);

	return "commandeclient";
}


@GetMapping(value="/addArticlecommade")
public String addArtlicle(Model model,Long id){
	idcommande=commandeClientRepository.getOne(id);
	//cette vonction va permettre d'ajouter des article à une vente demandé au cas où cela n'a pas ete fait par exemeple
	return "redirect:/nouveau?idCmd="+idcommande.getIdCmdClient()+"&ok=10";
}

@GetMapping(value = "/FactureCommande", produces = MediaType.APPLICATION_PDF_VALUE)
public ResponseEntity<InputStreamResource> customerReport() throws IOException {
	CommandeClient v=null;
	List<LigneCommandeClient> ligne=new ArrayList<LigneCommandeClient>();
	if(idcommande.getIdCmdClient()!=null) {  //ce bloc va permettre de reccuperer les detaille d'une cente en cour de selection et de faire un affichage en meme temps
		ligne=ligneComdClientRepository.rechercherLigne(idcommande.getIdCmdClient());
		v=commandeClientRepository.getOne(idcommande.getIdCmdClient());
	}

	ByteArrayInputStream bis = PDFGeneratorCommande.ArticlePDFReport(ligne, v);
	HttpHeaders headers = new HttpHeaders();
	headers.add("Content-Disposition", "inline; filename=Facture_de_la_vente.pdf");

	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
			.body(new InputStreamResource(bis));
}
    
	
	@RequestMapping("/detailArticle")
	@ResponseBody
	public Article getArticleByCode(final String codeArticle) {

		if (codeArticle == null) {
			return null;
		}else {
		Article article = impl_Article.getArticleByCode(codeArticle);
		
		if (article == null)
			return null;
		
		return article;
		}
	}

}
