package com.tchemso.entities;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/*
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor*/
@Entity
@Table(name = "user")
public class Users {

	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "user_id")
	    private Long id;
	    @Column(name = "email")
	    @Email(message = "*Donnez un email valide")
	    @NotEmpty(message = "*Email necessaire")
	    private String email;
	    @Column(name = "password")
	    @Length(min = 5, message = "*5 carateres au moins ")
	    @NotEmpty(message = "*Donner un mot de passe")
	    private String password;
	    @Column(name = "name")
	    @NotEmpty(message = "*Le champs ne doit pas etre vide")
	    private String name;
	    @Column(name = "last_name")
	    @NotEmpty(message = "*Please provide your last name")
	    private String lastName;
	    @Column(name = "active")
	    private int active;
	    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},fetch = FetchType.EAGER)
	   /* @JoinTable(name ="role_user", joinColumns= @JoinColumn(name ="user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))*/
	    private Set<Role> roles;
	    
	    //c'est un attribut qui permet de faire recupperer le role de l'utlisateur
	    @Transient
	    private String roleuser;

    public Users() {
    }

    public Users(Users users) {
        this.active = users.getActive();
        this.email = users.getEmail();
        this.roles = users.getRoles();
        this.name = users.getName();
        this.lastName =users.getLastName();
        this.id = users.getId();
        this.password = users.getPassword();
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public String getRoleuser() {
		return roleuser;
	}

	public void setRoleuser(String roleuser) {
		this.roleuser = roleuser;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
