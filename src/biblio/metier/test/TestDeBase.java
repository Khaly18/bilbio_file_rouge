package biblio.metier.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import biblio.metier.control.ExemplaireDao;
import biblio.metier.control.UtilisateursDao;
import biblio.metier.modele.Exemplaire;
import biblio.metier.modele.Adherent;
import biblio.metier.modele.Employe;
import biblio.metier.modele.EmpruntEnCours;

public class TestDeBase {
	
	public static void main(String[] args) throws ParseException {
		Exemplaire e1 = new Exemplaire(110, "chose");
		Exemplaire e2 = new Exemplaire(112, "lamain");
		Exemplaire e3 = new Exemplaire(212, "mercredi");
		Exemplaire e4 = new Exemplaire(311, "mortitiaAdams");
		ExemplaireDao dataExemplaire = new ExemplaireDao();
		dataExemplaire.addExemplaire(e1);
		dataExemplaire.addExemplaire(e2);
		dataExemplaire.addExemplaire(e3);
		dataExemplaire.addExemplaire(e4);
		Adherent u1 = new Adherent("LALEYE", "Laetitia",0);
		Adherent u2 = new Adherent("LA","Lydie", 122);
		UtilisateursDao dataUtilisateur = new UtilisateursDao();
		dataUtilisateur.addExemplaire(u1);
		dataUtilisateur.addExemplaire(u2);
		Employe emp1 = new Employe("MJ","Carthy",125);
		Employe emp2 = new Employe("Diouf","Kaly",215);
		dataUtilisateur.addExemplaire(emp1);
		dataUtilisateur.addExemplaire(emp2);
		
		
		/////////////////// Scenario Emprunt ////////////////////
		System.out.println(dataUtilisateur.findByKey(512));
		
		/////////////////// creatEmpruntCours ////////////////////
		EmpruntEnCours empp1 = new EmpruntEnCours(e1);
		u1.addEmpruntEnCours(empp1);
		System.out.println(u1);
		EmpruntEnCours empp2 = new EmpruntEnCours(e2);
		emp1.addEmpruntEnCours(empp2);
		System.out.println(emp1);
		
		
	}
}
