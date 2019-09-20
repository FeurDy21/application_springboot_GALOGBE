package com.tchemso.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tchemso.entities.CommandeClient;

public interface commandeClient extends JpaRepository<CommandeClient, Long> {

}
