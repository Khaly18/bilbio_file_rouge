package biblio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import biblio.metier.Adherent;
import biblio.metier.Employe;
import biblio.metier.EnumCategorieEmploye;
import biblio.metier.Utilisateur;

public class UtilisateursDao {

	Connection con = null;

	public UtilisateursDao(Connection con) {
		this.con = con;
	}

	public Utilisateur findByKey(int idUser) {
		PreparedStatement pstm;
		Utilisateur user = null;
		String id = "";
		String pwd = "";
		String nom = "";
		String prenom = "";
		String cat = "";
		String tel = "";
		String code = "";
		String cat_employe = "";
		try {
			pstm = con
					.prepareStatement("select utilisateur.idutilisateur, utilisateur.pwd, utilisateur.nom, utilisateur.prenom, categorie_utilisateur, telephone, codematricule, categorieemploye "
							+ "from utilisateur, adherent, employe "
							+ "where utilisateur.idutilisateur=adherent.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=employe.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=?");
			pstm.setInt(1, idUser);
			ResultSet result = pstm.executeQuery();
			while (result.next()) {
				id = result.getString(1);
				pwd = result.getString(2);
				nom = result.getString(3);
				prenom = result.getString(4);
				cat = result.getString(5);
				if (cat.equals("ADHERENT")) {
					tel = result.getString(6);
					user = new Adh�rent(nom, prenom, id, pwd, tel);
				}
				if (cat.equals("EMPLOYE")) {
					code = result.getString(7);
					cat_employe = result.getString(8);
					EnumCategorieEmploye cat2 = EnumCategorieEmploye.valueOf(cat_employe.toLowerCase());
					user = new Employe(nom, prenom, id, pwd, code, cat2);
				}

			}

			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	public ArrayList<Utilisateur> findAll() {
		ArrayList<Utilisateur> listUtilisateur = new ArrayList<Utilisateur>();
		Utilisateur user = null;
		String id = "";
		String pwd = "";
		String nom = "";
		String prenom = "";
		String cat = "";
		String tel = "";
		String code = "";
		String cat_employe = "";
		try {
			Statement stm = con.createStatement();
			ResultSet result = stm
					.executeQuery("select utilisateur.idutilisateur, utilisateur.pwd, utilisateur.nom, utilisateur.prenom, categorie_utilisateur, telephone, codematricule, categorieemploye "
							+ "from utilisateur, adherent, employe "
							+ "where utilisateur.idutilisateur=adherent.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=employe.idutilisateur (+)");
			while (result.next()) {
				id = result.getString(1);
				pwd = result.getString(2);
				nom = result.getString(3);
				prenom = result.getString(4);
				cat = result.getString(5);
				if (cat.equals("ADHERENT")) {
					tel = result.getString(6);
					user = new Adh�rent(nom, prenom, id, pwd, tel);
				}
				if (cat.equals("EMPLOYE")) {
					code = result.getString(7);
					cat_employe = result.getString(8);
					EnumCategorieEmploye cat2 = EnumCategorieEmploye.valueOf(cat_employe.toLowerCase());
					user = new Employe(nom, prenom, id, pwd, code, cat2);
				}

				listUtilisateur.add(user);
			}
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listUtilisateur;
	}

}
