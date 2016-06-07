package biblio.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;


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
			if ((isNotMAxPret(eec)==true) && (isTooLate(eec)==false) && (isDelete(eec)==false) && (isDisponible(eec))){
				System.out.println("Emprunt possible pour adherent");
				return true;
			}
		}else{
			if (isDelete(eec)==true){
				System.out.println("Employé Emprunt impossible cause exemplaire suprimé");
				return false;
			}else{
				System.out.println("Employé Emprunt possible");
				return true;
			}
		}
		System.out.println(isNotMAxPret(eec)+""+isTooLate(eec)+""+isDelete(eec));
		System.out.println("Adherent emprunt impossible motif explicité antérieureremnt");
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
					System.out.println("Exemplaire suprrimé");
					return true;
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		System.out.println("Exemplaire present");
		return false;
	}
	
	
	private boolean isAdherent(EmpruntEnCoursDB eec){
		
		String sqlBuilder = "SELECT categorieutilisateur FROM utilisateur WHERE idutilisateur = "+ eec.getIdUtilisateur();
		
		try {
			result = stm.executeQuery(sqlBuilder);
			while(result.next()){
				if (result.getString(1).equals("ADHERENT")){
					System.out.println("C'est un adherent");
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Ce n'est pas un adhérent");
		return false;
	}
	
	private boolean isNotMAxPret(EmpruntEnCoursDB eec){
		
		EmpruntEnCoursDao eecd = new EmpruntEnCoursDao(conn);
		
		if ((eecd.findByUtilisateur(eec.getIdUtilisateur()).size())<(NOMBRE_MAX_PRET)){
			// code de debugage
			System.out.println("nombre max prêt non atteint : "+ (eecd.findByUtilisateur(eec.getIdUtilisateur()).size()));
			
			return true;
		}
		System.out.println("nombre de prêt actuel atteint : "+ (eecd.findByUtilisateur(eec.getIdUtilisateur()).size()));
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
			}
		}
		//code de debuggage
		if (var == true){
			System.out.println("Attention retard");
		}else{
			System.out.println("Pas de de retard");
		}
		return var;
	}
	
	private boolean checkDate(Date d){
		
		Calendar calen = Calendar.getInstance();
		calen.setTime(d);
		calen.add(Calendar.DAY_OF_YEAR, NOMBRE_JOURS_MAX);
		Date d1 = new Date();
		Date d2 = calen.getTime();
		
		return d1.after(d2);
	}
	
	private boolean isDisponible(EmpruntEnCoursDB emp){
		
		String sqlBuilder = " SELECT exemplaire.idexemplaire, exemplaire.status FROM exemplaire "
							+ "WHERE exemplaire.idexemplaire = ? ";

		try {
			
			PreparedStatement pstm = conn.prepareStatement(sqlBuilder);
			pstm.setInt(1, emp.getIdExemplaire());
			pstm.execute();
			ResultSet result = pstm.getResultSet();
			while (result.next()){
				if (result.getString(2).equals("DISPONIBLE")){
					
					System.out.println("Exemplaire satuts disponible");
					return true;
					
				}else{
					System.out.println("Exemplaire non disponible impossible d'effectué le pret");
					return false;
				}
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
		
	}
	
}
