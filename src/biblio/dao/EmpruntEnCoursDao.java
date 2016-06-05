package biblio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import biblio.jdbc.ConvertDate;

public class EmpruntEnCoursDao {
	private Connection conn;
	
	public EmpruntEnCoursDao(Connection con) {
		this.conn = con;
	}
	
	public void insertEmpruntEnCours(EmpruntEnCoursDB empruntEncours){
		
		String sqlBuilder = "INSERT INTO empruntencours (idexemplaire, idutilisateur, dateemprunt) VALUES (?, ?, ?)";
		
		
		
		try {
			PreparedStatement pstm = conn.prepareStatement(sqlBuilder);
			
			
			pstm.setInt(1, empruntEncours.getIdExemplaire());
			pstm.setInt(2, empruntEncours.getIdUtilisateur());
			pstm.setString(3, ConvertDate.dateToString(empruntEncours.getDateEmprunt()));
			pstm.execute();
			conn.commit();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
