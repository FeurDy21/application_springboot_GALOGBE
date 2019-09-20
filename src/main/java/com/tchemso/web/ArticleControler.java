package com.tchemso.web;

import java.awt.PageAttributes.MediaType;


import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tchemso.dao.ArticleRepository;
import com.tchemso.dao.CategorieRepository;
import com.tchemso.dao.MvtRepository;
import com.tchemso.entities.AppResponse;
import com.tchemso.entities.Article;
import com.tchemso.entities.Categorie;
import com.tchemso.entities.MvtStock;


import com.tchemso.metier.impl_Article;
import com.tchemso.metier.impl_Categorie;
import com.tchemso.metier.impl_MvtStock;


@Controller
public class ArticleControler {

	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	CategorieRepository categorieRepository;
	@Autowired
	impl_Categorie categori;
	@Autowired
	impl_Article impl_Article;
	@Autowired
	MvtRepository mvtRepository;
	@Autowired
	impl_MvtStock impl_MvtStock;
	
	
  private Connection con;


	private void rechercheArticle(Model model) {
		// c'est une fonction qui va etre utilisée dans nombreuses fonctions pour
		// afficher tous les articles pouvant exister
        
		model.addAttribute("listeArticle", impl_Article.getAllArticle());

//c'est just pour activer le menu de la page dans laquelle nous sommes
	}

	/**
	 * cette fonction va permettre de faire
	 * 
	 * @param model
	 */
	private void rechercheCategorie(Model model) {
		// c'est une fonction qui va etre utilisée dans nombreuses fonctions
		List<Categorie> listCategorie = categorieRepository.findAll(sortByIdDesc());
		model.addAttribute("listeCategorie", listCategorie);

	}

	/**
	 * pour rechercher par ordre
	 * 
	 * @return
	 */
	private Sort sortByIdAsc() {
		return new Sort(Sort.Direction.ASC, "idCategorie");
	}

	/**
	 * pour rechercher par
	 * 
	 * @return
	 */
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "idCategorie");
	}

	/**
	 * ajouter une categorie d'objet
	 * 
	 * @param model
	 * @param categorie
	 * @return
	 */
	@RequestMapping(value = "/ajouter", method = RequestMethod.POST)
	public String ajouterCategorie(Model model, @Valid Categorie categorie, BindingResult bindingResult) {
		// categorieRepository.save(categorie);
		rechercheCategorie(model);

		if (bindingResult.hasErrors()) {
			return "table";
		}

		categori.ajouterCategorie(categorie);

		// c'est pour afficher les categorie d'article au sorti de la page
		return "table";
	}

	/**
	 * retrouver toute la liste des produits mis.
	 * 
	 * @param model
	 * @param categorie
	 * @return
	 */
	@RequestMapping(value = "/table.html", method = RequestMethod.GET)
	public String retrouverCategorie(Model model, Categorie categorie,boolean succes,boolean impossible) {
		// categorieRepository.save());
		rechercheCategorie(model);
        model.addAttribute("succes", succes);
        model.addAttribute("impossible", impossible);
		return "table";
	}
	
	@RequestMapping(value="/deletecategorie")
	private String deleteMaping(Model model, Long id ) {
   Categorie cat=categorieRepository.getOne(id);
	 if(cat.getQuantiteArticle()>0) {
		return  "redirect:/table.html?impossible="+true;
	 }else {
		 categorieRepository.deleteById(id);
	 }
   
		return "redirect:/table.html?succes="+true;
	}

	/**
	 * la fonction permettant de reccuperer toutes les fonction 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/article.html", method = RequestMethod.GET)
	public String article(Model model) {

		model.addAttribute("listeCategorie", categorieRepository.findAll());
		rechercheArticle(model);
		model.addAttribute("article", new Article());
		// model.addAttribute("count",mvtRepository.nbArticle());
		return "article";
	}

	@RequestMapping(value = "/ajouterArticle", method = RequestMethod.POST)
	public String ajouterAticl(Model model, @Valid Article article, BindingResult bindingResult) {
		// on y ballance aussi les element se trrouvant dans le model pour affichage
		rechercheArticle(model);
		// on va chercher toutes les categorie afin de les charger dans le combo qui se
		// toruve dans la page affichée
		rechercheCategorie(model);
		if (bindingResult.hasErrors()) {
			// si il y a une erreur dans la validation, afficher celles-ci
			return "article";
		}
		// on calcul le prix ttc de l'article
		article.setPrixUnitaireTTC(article.getPrixUnitaireHT() + article.getTauxTVA() );
		// System.out.println("test : "+article.getCategorie().getIdCategorie());

		impl_Article.AjouterArticle(article);
		// ajouter un article dans la base de donnée
		// articleRepository.save(article);

		return "redirect:/article.html?ok=produit bien ajouté";
	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String deleteProduit(Model model, Long id) {
		articleRepository.deleteById(id);

		return "redirect:/article.html";
	}

	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String editerProduit(Model model, Long id) {
		// pour editer l'article, on le recherche dans la base de donnée
		Article articl = articleRepository.getOne(id);
		// apres l'avoir trouvé, on le place dans le model, pour visualiser les valeurs
		// dans un formulaire modal
		model.addAttribute("article", articl);

		rechercheCategorie(model);
		return "editerproduit";
	}

	@RequestMapping(value = "/modification", method = RequestMethod.GET)
	public String editerProduit(Model model, Article article, Long idArticle) {
		// pour editer l'article, on le recherche dans la base de donnée
		Article articl = articleRepository.getOne(idArticle);
		// apres l'avoir trouvé, on le place dans le model, pour visualiser les valeurs
		// dans un formulaire modal
		articleRepository.save(article);

		rechercheCategorie(model);
		return "redirect:/article.html";
	}

	@RequestMapping(value = "/stock", method = RequestMethod.GET)
	public String stockageProduit(Model model, Long id) {
		// pour editer l'article, on le recherche dans la base de donnée
		Article articl = articleRepository.getOne(id);
		// apres l'avoir trouvé, on le place dans le model, pour visualiser les valeurs
		// dans un formulaire modal
		model.addAttribute("mvtStock", new MvtStock());
		model.addAttribute("article", articl);

		return "mouvement";
	}

	@PostMapping(value = "/ajouterStock")
	public String EntreSortie(Model model, Article article, @Valid MvtStock mvtStock, BindingResult bindingResult,
			Long id) {
		// pour editer l'article, on le recherche dans la base de donnée
		// mvtStock.setArticle(article);
		if (bindingResult.hasErrors()) {
			return "mouvement";
		}
		Article articl = articleRepository.getOne(id);
		mvtStock.setArticle(articl);
		mvtStock.setDateMvt(new Date());
		try {// on va verifier si la fonction leve une exception
			mvtStock = impl_MvtStock.AjouterEnStock(mvtStock);
			model.addAttribute("mvtStock", mvtStock);
			model.addAttribute("succes", true);

		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("exception", e);
		}

		// apres l'avoir trouvé, on le place dans le model, pour visualiser les valeurs
		// dans un formulaire modal

		return "mouvement";
	}
	/*
	 * @RequestMapping(value = "artic", method = RequestMethod.POST) public
	 * AppResponse creerArticle(
	 * 
	 * @RequestParam(value = AppConstants.EMPLOYEE_JSON_PARAM, required = false)
	 * String empJson,
	 * 
	 * @RequestParam(required = true, value = AppConstants.EMPLOYEE_FILE_PARAM)
	 * MultipartFile file) throws JsonParseException, JsonMappingException,
	 * IOException { String fileName = fileStorageService.storeFile(file); String
	 * fileDownloadUri =
	 * ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.
	 * DOWNLOAD_PATH) .path(fileName).toUriString();
	 * 
	 * Article article = objectMapper.readValue(empJson, Article.class);
	 * article.setPhoto(fileDownloadUri); impl_Article.AjouterArticle(article);
	 * 
	 * return new AppResponse(AppConstants.SUCCESS_CODE, AppConstants.SUCCESS_MSG);
	 * }
	 * 
	 */
}
