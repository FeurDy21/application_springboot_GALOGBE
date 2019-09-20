package com.tchemso.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tchemso.entities.Vente;

public interface VenteRepository extends JpaRepository<Vente, Long> {

}
