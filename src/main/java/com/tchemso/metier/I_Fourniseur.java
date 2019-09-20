package com.tchemso.metier;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import com.tchemso.entities.Fournisseur;

public interface I_Fourniseur {

	public Fournisseur searchOneFournisseur(Long id);

	public Page<Fournisseur> searAllFournisseur(int page, int size);

	public boolean deleFournisseur(Long id);

	public Fournisseur addFournisseur(Fournisseur fournisseur);

	public Fournisseur updateFournisseur(Fournisseur fournisseur);

}
