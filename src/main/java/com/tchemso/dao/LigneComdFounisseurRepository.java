package com.tchemso.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tchemso.entities.LigneCommandeClient;
import com.tchemso.entities.LigneCommandeFourniseur;

public interface LigneComdFounisseurRepository extends JpaRepository<LigneCommandeFourniseur, Long> {

}
