package com.tchemso.metier;

import java.util.List;

import org.hibernate.loader.custom.Return;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tchemso.dao.FournisseurRepository;
import com.tchemso.entities.Fournisseur;

@Service
@Transactional
public class impl_Fournisseur implements I_Fourniseur {
	@Autowired
	FournisseurRepository fournisseurRepository;
	// @Autowired
	Fournisseur fournisseur;

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	/**
	 * la fonction du haut et de bas vont nous permettre de reccuperer 'id du
	 * fournisseur à l'exterieur de la classe
	 * 
	 * @param fournisseur
	 */
	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	@Override
	public Page<Fournisseur> searAllFournisseur(int page, int size) {
		// c'est une fonction qui va permettre de retrouver tous les fournisseur mais
		// avec pagination

		PageRequest pagesAndNumber = new PageRequest(page, size); // on donne la page et le nombre de ligne q'on
																	// aimerait voir
		if (fournisseurRepository.findAll(pagesAndNumber) != null)
			return fournisseurRepository.findAll(pagesAndNumber);
		else
			return null;
	}

	@Override
	public boolean deleFournisseur(Long id) {
		// TODO Auto-generated method stub
		fournisseurRepository.deleteById(id);

		return false;
	}

	@Override
	public Fournisseur addFournisseur(Fournisseur fournisseur) {
		// cette fonction va nous permettre d'anregistrer un fournisseur

		fournisseurRepository.save(fournisseur);
		// (fournisseur==null)? return fournisseur:return null;
		return null;
	}

	@Override
	public Fournisseur updateFournisseur(Fournisseur fournisseur) {
		// TODO Auto-generated method stub

		if (fournisseurRepository.existsById(fournisseur.getIdFournisseur())) {

		}
		return null;
	}

	@Override
	public Fournisseur searchOneFournisseur(Long id) {
		// TODO Auto-generated method stub
		fournisseur = fournisseurRepository.getOne(id);
		if (fournisseur == null)
			throw new RuntimeException("Ce fournisseur n'existe pas");
		return null;

	}

}
