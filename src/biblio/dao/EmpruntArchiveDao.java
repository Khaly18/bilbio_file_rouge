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
<<<<<<< HEAD
				return emp = new EmpruntArchiveDB(result.getDate(4), result.getDate(3), result.ge, null);
=======
				return emp = new EmpruntArchiveDB(result.getDate(4), result.getDate(3), result.getInt(2), result.getInt(1));
>>>>>>> dd2be9d639cba31745c4df732e330ca04b9daa17
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
		
<<<<<<< HEAD
		return null;
	}
	
	
	
	public ArrayList<EmpruntArchiveDB> findByUtilisateur(int idUtilisateur){
		ArrayList<EmpruntArchiveDB> arrEmpruntAdb = new ArrayList<EmpruntArchiveDB>();
		
		return null;
=======
	}
	
	public ArrayList<EmpruntArchiveDB> findByUtilisateur(int idUtilisateur){
		ArrayList<EmpruntArchiveDB> arrEmpruntAdb = new ArrayList<EmpruntArchiveDB>();
		
		String sqlBuilder = "SELECT idexemplaire, idutilisateur, dateemprunt, daterestitutioneff, idempruntarchive"
				+ " FROM empruntarchive WHERE idexemplaire = "+ idUtilisateur;
		try {
			Statement stm = conn.createStatement();
			ResultSet result = stm.executeQuery(sqlBuilder);
			while (result.next()){
				arrEmpruntAdb.add(new EmpruntArchiveDB(result.getDate(4), result.getDate(3), result.getInt(2), result.getInt(1)));
			}
			return arrEmpruntAdb;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return arrEmpruntAdb;
	}
	
	//ajouter table retardEncours et Retardarchive 
	public void retourEmprunt(int idExemplaire) {
		String sqlBuilder = "SELECT idexemplaire, idutilisateur, dateemprunt"
				+ " FROM empruntencours WHERE idexemplaire = "+ idExemplaire;
		
		try {
			
			Statement stm = conn.createStatement();
			ResultSet result = stm.executeQuery(sqlBuilder);
			if(result.next()){
				sqlBuilder = "DELETE FROM empruntencours WHERE idexemplaire = "+ idExemplaire;
				stm.executeUpdate(sqlBuilder);
				conn.commit();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
>>>>>>> dd2be9d639cba31745c4df732e330ca04b9daa17
	}
	
	
}
