package com.tchemso.metier;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tchemso.dao.RoleRepository;
import com.tchemso.dao.UsersRepository;
import com.tchemso.entities.Role;
import com.tchemso.entities.Users;

import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
public class UserService {

    private UsersRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UsersRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Users findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Users saveUser(Users user) {
    	//on recupere le mot de passe et on l'encode
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(user.getRoleuser().equals("ADMIN"))
        user.setActive(1);   
        else
        user.setActive(2);     
        //on chercher le role de l'utilisateur en fontion de du type  user.getRoleuser
        Role userRole = roleRepository.findByRole(user.getRoleuser());
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

}