package com.tchemso.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tchemso.entities.LigneCommandeClient;
import com.tchemso.entities.LigneVente;

public interface LigneComdClientRepository extends JpaRepository<LigneCommandeClient, Long> {
	@Query("SELECT l from LigneCommandeClient l where l.commandeClient.idCmdClient=:x")
	public List<LigneCommandeClient> rechercherLigne(@Param("x") Long id);
	
	@Query("delete  from LigneCommandeClient l where l.commandeClient.idCmdClient=:x")
	public void deleteLigneCommade(@Param("x") Long d);

}
