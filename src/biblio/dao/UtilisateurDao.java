package biblio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import biblio.metier.modele.Adherent;
import biblio.metier.modele.Employe;
import biblio.metier.modele.EnumCategorieEmploye;
import biblio.metier.modele.Utilisateur;

public class UtilisateurDao {
	private Connection conn = null;
	private PreparedStatement pstm;
	private ResultSet result;
	private ArrayList<Utilisateur> arrUtilisateur = new ArrayList<Utilisateur>();
	
	public UtilisateurDao(Connection conn) {
		this.conn = conn;
	}
	
	public Utilisateur findByKey(int idKey){
		Utilisateur user = null;
		int id;
		char sexe;
		Date dateNaissance;
		String pwd = "";
		String nom = "";
		String prenom = "";
		String cat_utili = "";
		String tel = "";
		String code_matri = "";
		String cat_employe = "";
		String pseudo;
		String stBuilder = "SELECT utilisateur.idutilisateur, utilisateur.pwd, utilisateur.nom, "
							+ "utilisateur.prenom, utilisateur.categorieutilisateur, "
							+ "adherent.telephone, employe.codematricule, employe.categorieemploye, utilisateur.sexe, "
							+ "utilisateur.datenaissance, utilisateur.pseudonyme "
							+ "FROM utilisateur, adherent, employe "
							+ "where utilisateur.idutilisateur=adherent.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=employe.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=?";
		try {
			pstm = conn.prepareStatement(stBuilder);
			pstm.setInt(1, idKey);
			if (pstm.execute() != false){
				result = pstm.getResultSet();
				result.next();
				id = result.getInt(1);
				pwd = result.getString(2);
				nom = result.getString(3);
				prenom = result.getString(4);
				cat_utili = result.getString(5);
				tel = result.getString(6);
				sexe = result.getString(9).toUpperCase().charAt(0);
				dateNaissance = result.getDate(10);
				pseudo = result.getString(11);
				
				if (cat_utili.equals("ADHERENT")) {
					tel = result.getString(6);
					user = new Adherent(nom, prenom, id);
					user.setDateNaissance(dateNaissance);
					user.setPwd(pwd);
					user.setSexe(sexe);
					user.setPseudonyme(pseudo);
					user.setCategorieUtilisateur(cat_utili);
					((Adherent) user).setTelephone(tel);
				}
				if (cat_utili.equals("EMPLOYE")) {
					code_matri = result.getString(7);
					cat_employe = result.getString(8);
					EnumCategorieEmploye cat2 = EnumCategorieEmploye.valueOf(cat_employe.toUpperCase());
					user = new Employe(nom, prenom, id);
					user.setPwd(pwd);
					user.setPseudonyme(pseudo);
					user.setSexe(sexe);
					System.out.println(dateNaissance);
					user.setDateNaissance(dateNaissance);
					((Employe) user).setCodeMatricule(code_matri);
					((Employe) user).setCategorieEpmloye(cat2);
				}
			}
		} catch (SQLException e) {
			System.out.println("impossible d'executer la requete");
			e.printStackTrace();
		}
		
		return user;
	}
	
	public ArrayList<Utilisateur> findAll(){
		Utilisateur user = null;
		int id;
		char sexe;
		Date dateNaissance;
		String pwd = "";
		String nom = "";
		String prenom = "";
		String cat_utili = "";
		String tel = "";
		String code_matri = "";
		String cat_employe = "";
		String pseudo;
		String stBuilder = "SELECT utilisateur.idutilisateur, utilisateur.pwd, utilisateur.nom, "
							+ "utilisateur.prenom, utilisateur.categorieutilisateur, "
							+ "adherent.telephone, employe.codematricule, employe.categorieemploye, utilisateur.sexe, "
							+ "utilisateur.datenaissance, utilisateur.pseudonyme "
							+ "FROM utilisateur, adherent, employe "
							+ "where utilisateur.idutilisateur=adherent.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=employe.idutilisateur (+) ";
		try {
			pstm = conn.prepareStatement(stBuilder);
			if (pstm.execute() != false){
				result = pstm.getResultSet();
				while(result.next()){
					id = result.getInt(1);
					pwd = result.getString(2);
					nom = result.getString(3);
					prenom = result.getString(4);
					cat_utili = result.getString(5);
					tel = result.getString(6);
					sexe = result.getString(9).toUpperCase().charAt(0);
					dateNaissance = result.getDate(10);
					pseudo = result.getString(11);
					
					
					if (cat_utili.equals("ADHERENT")) {
						tel = result.getString(6);
						user = new Adherent(nom, prenom, id);
						user.setDateNaissance(dateNaissance);
						user.setPwd(pwd);
						user.setSexe(sexe);
						user.setPseudonyme(pseudo);
						user.setCategorieUtilisateur(cat_utili);
						((Adherent) user).setTelephone(tel);
					}
					if (cat_utili.equals("EMPLOYE")) {
						code_matri = result.getString(7);
						cat_employe = result.getString(8);
						EnumCategorieEmploye cat2 = EnumCategorieEmploye.valueOf(cat_employe.toUpperCase());
						user = new Employe(nom, prenom, id);
						user.setPwd(pwd);
						user.setPseudonyme(pseudo);
						user.setSexe(sexe);
						System.out.println(dateNaissance);
						user.setDateNaissance(dateNaissance);
						((Employe) user).setCodeMatricule(code_matri);
						((Employe) user).setCategorieEpmloye(cat2);
					}
					arrUtilisateur.add(user);
				}
			}
		} catch (SQLException e) {
			System.out.println("impossible d'executer la requete");
			e.printStackTrace();
		}
		return arrUtilisateur;
	}
}
