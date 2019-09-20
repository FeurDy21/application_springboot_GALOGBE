package com.tchemso.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tchemso.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	@Query("SELECT c FROM Utilisateur c WHERE c.nom LIKE :x")
	public Page<Utilisateur> rechercherUtilisateur(@Param("x") String mc, Pageable page);

}
