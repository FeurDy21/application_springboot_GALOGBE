package com.tchemso.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tchemso.entities.CommandeClient;
public interface commandeClientRepository extends JpaRepository<CommandeClient, Long> {

}
