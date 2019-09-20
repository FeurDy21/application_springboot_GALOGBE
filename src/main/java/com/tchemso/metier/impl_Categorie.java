package com.tchemso.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tchemso.dao.CategorieRepository;
import com.tchemso.entities.Categorie;

@Service
@Transactional
public class impl_Categorie implements I_Categorie {
	@Autowired
	CategorieRepository cat;

	private Sort sortByDesignationAsc() {
		return new Sort(Sort.Direction.ASC, "designation");
	}

	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "idArticle");
	}

	@Override
	public Categorie ajouterCategorie(Categorie categorie) {
		// TODO Auto-generated method stub
		return cat.save(categorie);
	}

	/*
	 * @Autowired private CategorieRepository categorieRepository;
	 */

}
