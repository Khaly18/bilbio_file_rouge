package biblio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import biblio.metier.control.BiblioException;


public class EmprunterCtl {
	private Connection conn;
	private ResultSet result;
	private Statement stm;
	private static final int NOMBRE_MAX_PRET = 3;
	private static final int NOMBRE_JOURS_MAX = 15;
	
	
	public EmprunterCtl(Connection conn) {
		this.conn = conn;
		try {
			this.stm = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean isEmpruntPossible(EmpruntEnCoursDB eec){

		if (isAdherent(eec) == true){
			if ((isNotMAxPret(eec)==true) && (isTooLate(eec)==false) && (isDelete(eec))){
				return true;
			}
		}else{
			if (isDelete(eec)==true){
				return false;
			}
		}
		
		return false;
	}
	
	private boolean isDelete(EmpruntEnCoursDB emp){
		
		String sqlBuilder = " SELECT exemplaire.idexemplaire, exemplaire.status FROM exemplaire "
							+ "WHERE exemplaire.idexemplaire = ? ";

		try {
			
			PreparedStatement pstm = conn.prepareStatement(sqlBuilder);
			pstm.setInt(1, emp.getIdExemplaire());
			pstm.execute();
			ResultSet result = pstm.getResultSet();
			while (result.next()){
				if (result.getString(2).equals("SUPPRIME")){
					return true;
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}
	
	
	private boolean isAdherent(EmpruntEnCoursDB eec){
		
		String sqlBuilder = "SELECT categorieutilsateur FROM utilisateur WHERE idutilisateur = "+ eec.getIdUtilisateur();
		
		try {
			result = stm.executeQuery(sqlBuilder);
			while(result.next()){
				if (result.getString(1).equals("ADHERENT")){
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	private boolean isNotMAxPret(EmpruntEnCoursDB eec){
		
		EmpruntEnCoursDao eecd = new EmpruntEnCoursDao(conn);
		
		if (eecd.findByUtilisateur(eec.getIdUtilisateur()).size()<(NOMBRE_MAX_PRET-1)){
			return true;
		}
		
		return false;
	}
	
	private boolean isTooLate(EmpruntEnCoursDB eec) {
		
		boolean var = false;
		
		EmpruntEnCoursDao eecd = new EmpruntEnCoursDao(conn);
		for (EmpruntEnCoursDB edb : eecd.findByUtilisateur(eec.getIdUtilisateur())){
			if (checkDate(edb.getDateEmprunt())==true){
				var = true;
				System.out.println(edb.getDateEmprunt());
				break;
				//throw new BiblioException("Erreur il y a au moin un retard"+ edb.getDateEmprunt());
			}
		}
		return var;
	}
	
	private boolean checkDate(Date d){
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calen = Calendar.getInstance();
		calen.setTime(d);
		calen.add(Calendar.DAY_OF_YEAR, NOMBRE_JOURS_MAX);
		Date d1 = new Date();
		Date d2 = calen.getTime();
		
		return d1.after(d2);
	}
}
