package biblio.metier.control;

import java.util.ArrayList;

import biblio.metier.modele.Adherent;
import biblio.metier.modele.Employe;
import biblio.metier.modele.Exemplaire;
import biblio.metier.modele.Utilisateur;

public class UtilisateursDao {
	private ArrayList<Utilisateur> utilisateurDB=new ArrayList<>();
	
	public UtilisateursDao() {
		Adherent u1 = new Adherent("LALEYE", "Laetitia",0);
		Adherent u2 = new Adherent("LALEYE","Lydie", 122);

		Employe emp1 = new Employe("MJ","Carthy",125);
		Employe emp2 = new Employe("Diouf","Kaly",215);
		
		addExemplaire(u1);
		addExemplaire(u2);
		addExemplaire(emp1);
		addExemplaire(emp2);
	}
	
	public Utilisateur findByKey(int id){
		Utilisateur u1 = null;
		for (Utilisateur u : utilisateurDB){
			if (u.getIdUtilisateur()==id)
				u1=u;
		}
		return u1;
	}
	
	
	public void addExemplaire(Utilisateur u){
		utilisateurDB.add(u);
	}
}
