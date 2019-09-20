package com.tchemso.dao;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tchemso.entities.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {

	@Query("SELECT f FROM Fournisseur f WHERE  f.nom like :x")
	public Page<Fournisseur> rechercherFournisseur(@Param("x") String nom, Pageable pager);

}
