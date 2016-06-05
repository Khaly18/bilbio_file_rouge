package biblio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			changeStatusExemplaire(empruntEncours);
			pstm.execute();
			conn.commit();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	private void changeStatusExemplaire(EmpruntEnCoursDB emp){
		
		String sqlBuilder = " SELECT exemplaire.idexemplaire, exemplaire.status FROM exemplaire "
							+ "WHERE exemplaire.idexemplaire = ? ";

		try {
			
			PreparedStatement pstm = conn.prepareStatement(sqlBuilder);
			pstm.setInt(1, emp.getIdExemplaire());
			pstm.execute();
			ResultSet result = pstm.getResultSet();
			while (result.next()){
				if (result.getString(2).equals("DISPONIBLE")){
					setToPrete(emp.getIdExemplaire());
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
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
}
