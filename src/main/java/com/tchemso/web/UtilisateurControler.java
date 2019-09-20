package com.tchemso.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tchemso.dao.UtilisateurRepository;

@Controller
public class UtilisateurControler {

	@Autowired
	UtilisateurRepository utilisateurRepository;

//@RequestMapping("")
	public String index() {

		return "";
	}

	@RequestMapping(value = "/statistique.html")
	public String inscription(Model model) {

		return "statistique";
	}

}
