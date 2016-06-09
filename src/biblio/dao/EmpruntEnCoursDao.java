package biblio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
<<<<<<< HEAD
=======
import java.util.Date;

>>>>>>> dd2be9d639cba31745c4df732e330ca04b9daa17
import biblio.jdbc.ConvertDate;

public class EmpruntEnCoursDao {
	private Connection conn;
	private EmprunterCtl checkerCtl;
	
	public EmpruntEnCoursDao(Connection con) {
		this.conn = con;
		checkerCtl = new EmprunterCtl(conn);
	}
	
	
	public void insertEmpruntEnCours(EmpruntEnCoursDB empruntEncours){
		
		String sqlBuilder = "INSERT INTO empruntencours (idexemplaire, idutilisateur, dateemprunt) VALUES (?, ?, ?)";
		
		try {
			
			PreparedStatement pstm = conn.prepareStatement(sqlBuilder);
			pstm.setInt(1, empruntEncours.getIdExemplaire());
			pstm.setInt(2, empruntEncours.getIdUtilisateur());
			pstm.setString(3, ConvertDate.dateToString(empruntEncours.getDateEmprunt()));
			if(checkerCtl.isEmpruntPossible(empruntEncours)){
				setToPrete(empruntEncours.getIdExemplaire());
				pstm.execute();
				conn.commit();
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
<<<<<<< HEAD
=======
	
	public void insertEmpruntEnCours(int idExemplaire, int idUtilisateur, Date today){
		
		String sqlBuilder = "INSERT INTO empruntencours (idexemplaire, idutilisateur, dateemprunt) VALUES (?, ?, ?)";
		
		try {
			
			PreparedStatement pstm = conn.prepareStatement(sqlBuilder);
			pstm.setInt(1, idExemplaire);
			pstm.setInt(2, idUtilisateur);
			pstm.setString(3, ConvertDate.dateToString(today));
			if(checkerCtl.isEmpruntPossible(new EmpruntEnCoursDB(today, idExemplaire, idUtilisateur))){
				setToPrete(new EmpruntEnCoursDB(today, idExemplaire, idUtilisateur).getIdExemplaire());
				pstm.execute();
				conn.commit();
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	
>>>>>>> dd2be9d639cba31745c4df732e330ca04b9daa17
	public EmpruntEnCoursDB findByKey(int idex){
		EmpruntEnCoursDB emp = null;
		String sqlBuilder = "SELECT idexemplaire, idutilisateur, dateemprunt FROM empruntencours WHERE idexemplaire = "+ idex;
		
		Statement stm;
		try {
			
			stm = conn.createStatement();
			ResultSet result= stm.executeQuery(sqlBuilder);
			while (result.next()){
				return emp = new EmpruntEnCoursDB(result.getDate(3), result.getInt(1), result.getInt(2));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}
	
	public ArrayList<EmpruntEnCoursDB> findByUtilisateur(int util){
		String sqlBuilder = "SELECT idexemplaire, idutilisateur, dateemprunt FROM empruntencours WHERE idutilisateur = "+ util;
		ArrayList<EmpruntEnCoursDB> arrEmp = new ArrayList<EmpruntEnCoursDB>();
		
		try {
			
			Statement stm = conn.createStatement();
			ResultSet result= stm.executeQuery(sqlBuilder);
			while (result.next()){
				arrEmp.add(new EmpruntEnCoursDB(result.getDate(3), result.getInt(1), result.getInt(2)));
			}
			return arrEmp;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrEmp;
	}
	
	
	private void setToPrete(int idexemplaire){
		
		String sqlBuilder = "UPDATE exemplaire SET status = 'PRETE' WHERE idexemplaire = "+idexemplaire;
		
		try {
			
			Statement stm = conn.createStatement();
			
			stm.executeUpdate(sqlBuilder);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
<<<<<<< HEAD
=======
	
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
			
		}
>>>>>>> dd2be9d639cba31745c4df732e330ca04b9daa17
}
