package com.tchemso.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tchemso.entities.Client;

/**
 * c'est une interface pemertant de se connecter
 * 
 * @author RIDDEF 3
 *
 */

public interface ClientRepository extends JpaRepository<Client, Long>, CrudRepository<Client, Long> {

	@Query("SELECT c FROM Client c WHERE  c.nom LIKE :x")
	public Page<Client> RechercherClient(@Param("x") String mc, Pageable pageable);

}
