package biblio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import biblio.jdbc.ConvertDate;
import biblio.metier.modele.Adherent;
import biblio.metier.modele.Employe;
import biblio.metier.modele.EnumCategorieEmploye;
import biblio.metier.modele.Utilisateur;

public class UtilisateurDao {
	private Connection conn = null;
	private PreparedStatement pstm;
	private ResultSet result;
	
	public UtilisateurDao(Connection conn) {
		this.conn = conn;
	}
	
	public Utilisateur findByKey(int idKey){
		Utilisateur user = null;
		int id;
		char sexe;
		String dateNaissance;
		String pwd = "";
		String nom = "";
		String prenom = "";
		String cat = "";
		String tel = "";
		String code = "";
		String cat_employe = "";
		String stBuilder = "SELECT utilisateur.idutilisateur, utilisateur.pwd, utilisateur.nom, "
							+ "utilisateur.prenom, utilisateur.categorieutilisateur, "
							+ "adherent.telephone, employe.codematricule, employe.categorieemploye, utilisateur.sexe, "
							+ "utilisateur.datenaissance"
							+ "from utilisateur, adherent, employe "
							+ "where utilisateur.idutilisateur=adherent.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=employe.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=?";
		try {
			pstm = conn.prepareStatement(stBuilder);
			pstm.setInt(1, idKey);
			
			if (pstm.execute() != false){
				result = pstm.getResultSet();
				id = result.getInt(1);
				pwd = result.getString(2);
				nom = result.getString(3);
				prenom = result.getString(4);
				cat = result.getString(5);
				tel = result.getString(6);
				sexe = result.getString(9).toUpperCase().charAt(0);
				dateNaissance = result.getString(10);
				
				if (cat.equals("ADHERENT")) {
					tel = result.getString(6);
					user = new Adherent(nom, prenom, id);
					user.setDateNaissance(ConvertDate.stringToDate(dateNaissance));
					user.setPwd(pwd);
					user.setSexe(sexe);
					user.setCategorieUtilisateur(cat);
					((Adherent) user).setTelephone(tel);
				}
				if (cat.equals("EMPLOYE")) {
					code = result.getString(7);
					cat_employe = result.getString(8);
					EnumCategorieEmploye cat2 = EnumCategorieEmploye.valueOf(cat_employe.toLowerCase());
					user = new Employe(nom, prenom, id);
					((Employe) user).setCodeMatricule(code);
					((Employe) user).setCategorieEpmloye(cat2);
				}
			}
		} catch (SQLException e) {
			System.out.println("impossible d'executer la requete");
		}
		
		return user;
	}
}
