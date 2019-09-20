package com.tchemso.dao;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tchemso.entities.LigneCommandeClient;
import com.tchemso.entities.LigneVente;

public interface LigneVenteRepository extends JpaRepository<LigneVente, Long> {
	
	
	@Query("SELECT l from LigneVente l where l.vente.idVente=:x")
	public List<LigneVente> rechercherLigneVente(@Param("x") Long id);

}
