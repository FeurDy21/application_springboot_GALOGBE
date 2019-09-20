package com.tchemso.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tchemso.entities.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
	// prendre les donnee en matiere de page
	/*
	 * @Query("SELECT c FROM categorie c WHERE c.code LIKE :g" ) public
	 * Page<Categorie> rechercherCategoriePage(@Param("g")String s ,Pageable page);
	 */
//prendre toutes les donnees   
	/*
	 * @Query("SELECT c FROM categorie c WHERE c.code LIKE :g" ) public
	 * List<Categorie> rechercherCategorieTous(@Param("g")String s ,Pageable page);
	 */

}
