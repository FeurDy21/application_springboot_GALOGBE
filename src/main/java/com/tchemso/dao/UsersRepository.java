package com.tchemso.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tchemso.entities.Users;

import java.util.Optional;

@Repository("usersRepository")
public interface UsersRepository extends JpaRepository<Users, Long> {
    
    Optional<Users> findByName(String name);
    Users findByEmail(String email);
}
