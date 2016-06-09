package biblio.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmpruntArchiveDao {
	private Connection conn;
	
	public EmpruntArchiveDao( Connection con) {
		conn = con;
	}
	
	public EmpruntArchiveDB findByKey(int idExemplaire){
		
		EmpruntArchiveDB emp = null;
		String sqlBuilder = "SELECT idexemplaire, idutilisateur, dateemprunt, daterestitutioneff, idempruntarchive"
				+ " FROM empruntarchive WHERE idexemplaire = "+ idExemplaire;
		
		Statement stm;
		try {
			
			stm = conn.createStatement();
			ResultSet result= stm.executeQuery(sqlBuilder);
			while (result.next()){
				return emp = new EmpruntArchiveDB(result.getDate(4), result.getDate(3), result.ge, null);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
		
		return null;
	}
	
	
	
	public ArrayList<EmpruntArchiveDB> findByUtilisateur(int idUtilisateur){
		ArrayList<EmpruntArchiveDB> arrEmpruntAdb = new ArrayList<EmpruntArchiveDB>();
		
		return null;
	}
	
	
}
