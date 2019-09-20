package com.tchemso.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;

import net.bytebuddy.asm.Advice.This;

public final class JDBCconnection {
	
	private static Connection con;
	private static PreparedStatement ps;
	
	//c'est un fonction qui etablie la connexion
	public static Connection connexion(String url,String user,String password) {
		try {
			con=DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return con;
	}
	
	//c'est un fonction qui prepare la requete
	public static PreparedStatement statement(String sql) {
		try {
			ps=JDBCconnection.connexion("jdbc:mysql://localhost:3306/gestiondestock","root", "").prepareStatement(sql);
			System.out.println("la connexion est bonne");
		} catch (Exception e) {
			//
			System.out.println("la connexion à échouée");
		}
		
		return ps;
	}
}
