package com.tchemso.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.tchemso.dao.ArticleRepository;
import com.tchemso.dao.LigneVenteRepository;
import com.tchemso.dao.MvtRepository;
import com.tchemso.dao.VenteRepository;
import com.tchemso.entities.Article;
import com.tchemso.entities.CommandeClient;
import com.tchemso.entities.LigneCommandeClient;
import com.tchemso.entities.LigneVente;
import com.tchemso.entities.MvtStock;
import com.tchemso.entities.Users;
import com.tchemso.entities.Vente;
import com.tchemso.metier.Impl_Client;
import com.tchemso.metier.UserService;
import com.tchemso.metier.imp_vente;
import com.tchemso.metier.impl_Article;
import com.tchemso.metier.impl_LigneVente;
import com.tchemso.metier.impl_MvtStock;
import com.tchemso.utils.JDBCconnection;
import com.tchemso.utils.PDFGenerator;
import com.tchemso.utils.PDFGeneratorVente;

@Controller
public class venteControler {
   private static PreparedStatement ps;
   private Vente idvente;//c'est un objet qui va nous permettre de reccuperer l'id de la vente qui vient d'ettre enregitstré
	@Autowired
	Impl_Client client;
	@Autowired
	VenteRepository venteRepository;
	@Autowired
	LigneVenteRepository ligneVenteRepository;
	@Autowired
	impl_Article impl_Article;
	@Autowired
	 ArticleRepository articleRepository;
	@Autowired
	impl_LigneVente lignevente;
	@Autowired
	MvtRepository mvtRepository;
	@Autowired
	impl_MvtStock impl_MvtStock;
   
	
	
	
	

	private String codeGenere() {
		String code = UUID.randomUUID().toString().replaceAll("-", "").substring(2, 4).toUpperCase() + ""
				+ new Date().toString().replaceAll(" ", "").substring(3, 8) + ""
				+ new Date().toString().replaceAll(" ", "").substring(19, 23);
		
		return code;
	}

	@GetMapping(value = "/detailvente")
	private String index(Model model, Long id) {

		List<Vente> ventes = venteRepository.findAll();

		// on initialies le tableau à un tableau vide mais qui prend les parametre de la
		// fonction
		List<LigneVente> lignevente = new ArrayList<LigneVente>();
		if (ventes.isEmpty()) {
			ventes = new ArrayList<Vente>();
		} else {
			for(Vente vente :ventes) {
			// on essai d'de prendre la liste des detailles d'une commdande
			lignevente = ligneVenteRepository.rechercherLigneVente(id);
			 System.out.print(vente.getTotalVente());
			}
		}
		model.addAttribute("vente", ventes);
		model.addAttribute("lignevente", lignevente);

		return "ventearticles";
	}

	
	@GetMapping(value = "/vente")//initialisation de la page commande
	private String index2(Model model, Vente cmd) {

		List<Vente> ventes = venteRepository.findAll();

		// on initialies le tableau à un tableau vide mais qui prend les parametre de la
		// fonction
		List<LigneVente>  ligneventes = new ArrayList<LigneVente>();
		if (ventes.isEmpty()) {
			ventes = new ArrayList<Vente>();
		} else {
            for (Vente c : ventes) {

           	ligneventes=ligneVenteRepository.rechercherLigneVente(c.getIdVente());
			}	
		}
		model.addAttribute("lignevente", ligneventes);
		model.addAttribute("vente", ventes);
		return "ventearticles";
		
	}

	@GetMapping(value = "/nouvelle")
	public String nouvelleCommande(Model model, Long bien, Long idVent,Long ok) {
		Vente v=null;
		List<LigneVente> ligne=new ArrayList<LigneVente>();
		if(idVent!=null) {  //ce bloc va permettre de reccuperer les detaille d'une cente en cour de selection et de faire un affichage en meme temps
			ligne=ligneVenteRepository.rechercherLigneVente(idVent);
			v=venteRepository.getOne(idVent);
		}
		
	
	        
	        
	       
	        
		model.addAttribute("ven", v);
		model.addAttribute("vente", new Vente());
		model.addAttribute("lignevente", new LigneVente());
		model.addAttribute("clients", client.ConsulterClients());
		model.addAttribute("bien", bien);
		model.addAttribute("ok",ok);
		model.addAttribute("date", new Date());
		model.addAttribute("code", codeGenere());
        model.addAttribute("listeArticle", impl_Article.getAllArticle());
        model.addAttribute("ligne",ligne);
        model.addAttribute("add",true);
		return "nouvellevente";
	}
	
	@GetMapping(value="/addArticle")
	public String addArtlicle(Model model,Long id){
		idvente=venteRepository.getOne(id);
		//cette vonction va permettre d'ajouter des article à une vente demandé au cas où cela n'a pas ete fait par exemeple
		return "redirect:/nouvelle?idVent="+idvente.getIdVente()+"&ok=10";
	}
	
	@GetMapping(value = "/nouvellevente")
	public String nouvelCommande(Model model, Long bien) {
		
		return "redirect:/nouvelle";
	}

	/*@GetMapping(value = "deleteCmdClient")
	public String annulerCommdande(Long id) {

		ligneVenteRepository.deleteLigneCommade(id);
		
		venteRepository.deleteById(id);

		return "redirect:/commande";
	}*/

	@PostMapping(value = "ajoutervente")
	private String ajouterCommade(Model model, Vente vente,LigneVente ligne) {
		vente.setDateVente(new Date());
		vente.setCodeVente(codeGenere());
		idvente=venteRepository.save(vente);
		
		model.addAttribute("vente", new Vente());
		model.addAttribute("lignevente", new LigneVente());
		model.addAttribute("listeArticle", impl_Article.getAllArticle());
		
		return "redirect:/nouvelle?bien="+idvente.getIdVente()+"&ok=10";
	}
	 
@GetMapping(value="ligneventes")
private String ajouterArticleVente(Model model,@Valid LigneVente ligneVente,BindingResult bindingResult,Long quantite, Long article) throws SQLException {
	/*Vente vente =new Vente();
	vente.setDateVente(new Date());
	vente.setCodeVente(codeGenere());
	Vente cmd=venteRepository.save(vente);*/
	if(bindingResult.hasErrors()) {
		model.addAttribute("vente", new Vente());
		model.addAttribute("lignevente", new LigneVente());
		model.addAttribute("listeArticle", impl_Article.getAllArticle());
		model.addAttribute("ok", 10);
		model.addAttribute("add",true);
		return "nouvellevente";
	}
	
	  Article art=articleRepository.getOne(article) ;
	if(art.getQuantite()<=0 ) {	
		model.addAttribute("vente", new Vente());
		model.addAttribute("lignevente", new LigneVente());
		model.addAttribute("listeArticle", impl_Article.getAllArticle());
		model.addAttribute("ok", 10);
		
		model.addAttribute("add",true);
		model.addAttribute("manque", art.getDesignation()+" manque en stock");
		return "nouvellevente";
	}
	
	if(art.getQuantite()< ligneVente.getQuantite() ) {	
		model.addAttribute("vente", new Vente());
		model.addAttribute("lignevente", new LigneVente());
		model.addAttribute("listeArticle", impl_Article.getAllArticle());
		model.addAttribute("ok", 10);
		
		model.addAttribute("add",true);
		model.addAttribute("manque", art.getDesignation()+" a une quantite egale à "+ art.getQuantite());
		return "nouvellevente";
	}
	ligneVente.setQuantite(quantite.intValue());
	ligneVente.setPrix(impl_Article.prixArticle(article));
	//on recupere l'id de la vente
	ligneVente.setVente(idvente);
	 //on recupere l'id de l'article
	ligneVente.setArticle(articleRepository.getOne(article));
	
	ligneVenteRepository.save(ligneVente);
	return "redirect:/nouvelle?idVent="+idvente.getIdVente()+"&ok=10";
}

	
	@GetMapping(value="ligneventeupdate")
	private String updateArticleVente(Model model,@Valid LigneVente ligneVente,BindingResult bindingResult,Long quantite, Long article) throws SQLException {
		/*Vente vente =new Vente();
		vente.setDateVente(new Date());
		vente.setCodeVente(codeGenere());
		Vente cmd=venteRepository.save(vente);*/
		if(bindingResult.hasErrors()) {
			model.addAttribute("vente", new Vente());
			model.addAttribute("lignevente", new LigneVente());
			model.addAttribute("listeArticle", impl_Article.getAllArticle());
			model.addAttribute("ok", 10);
			model.addAttribute("add",true);
			return "nouvellevente";
		}
		
		System.out.println(ligneVente.getVente());
		ligneVente.setQuantite(quantite.intValue());
		ligneVente.setPrix(impl_Article.prixArticle(article));
		ligneVente.setVente(idvente);
		ligneVente.setArticle(articleRepository.getOne(article));//cest dans le but de donner l'article a la classe movement afin de recupperer son id
		
		ligneVenteRepository.save(ligneVente);
	
	//le ok correspond à une variable qui permettra d'afficher les champs de detail au cas où elle n'est pas null  ligneventeupdate
	return "redirect:/nouvelle?idVent="+idvente.getIdVente()+"&ok=10";
}

@GetMapping(value="deleteLigne")
public String  deleteLigne(Model model,Long id) {
	ligneVenteRepository.deleteById(id);
	return "redirect:/nouvelle?idVent="+idvente.getIdVente()+"&ok=10";
}

@GetMapping(value="updateLigne")
public String  updateLigne(Model model,Long id) {
	 LigneVente lign= ligneVenteRepository.getOne(id);
	  model.addAttribute("vente", new Vente());
		model.addAttribute("lignevente", lign);
		model.addAttribute("listeArticle", impl_Article.getAllArticle());
	  model.addAttribute("ok", 10);
	  model.addAttribute("update",true);
	  return "nouvellevente";
	  
}

@GetMapping(value = "/Facture", produces = MediaType.APPLICATION_PDF_VALUE)
public ResponseEntity<InputStreamResource> customerReport() throws IOException {
	Vente v=null;
	List<LigneVente> ligne=new ArrayList<LigneVente>();
	if(idvente.getIdVente()!=null) {  //ce bloc va permettre de reccuperer les detaille d'une cente en cour de selection et de faire un affichage en meme temps
		ligne=ligneVenteRepository.rechercherLigneVente(idvente.getIdVente());
		v=venteRepository.getOne(idvente.getIdVente());
	}

	ByteArrayInputStream bis = PDFGeneratorVente.ArticlePDFReport(ligne,v);

	HttpHeaders headers = new HttpHeaders();
	headers.add("Content-Disposition", "inline; filename=Facture_de_la_vente.pdf");

	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
			.body(new InputStreamResource(bis));
}
	
	@RequestMapping("/detailArticl")
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
