package com.tchemso.metier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.tchemso.utils.JDBCconnection;

@Service
public class impl_LigneVente implements I_LigneVente {
	
 private static PreparedStatement ps;
	
	public PreparedStatement insererLigneVente() {
		String query="INSERT INTO ligne_vente(quantite,prix,id_vente,id_article)  VALUE(?,?,?,?)";
		
		ps=JDBCconnection.statement(query);
		 
		return ps;
	}

	@Override
	public PreparedStatement ente() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
