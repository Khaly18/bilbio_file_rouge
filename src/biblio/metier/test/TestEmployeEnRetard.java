package biblio.metier.test;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import biblio.metier.modele.Employe;
import biblio.metier.modele.EmpruntEnCours;
import biblio.metier.modele.Exemplaire;

public class TestEmployeEnRetard {
		
		public static void main(String[] args) throws ParseException {
			
			/////////////////// creatPerson ////////////////////
			Employe u1 = new Employe("LALEYE", "Laetitia",0);
			
			/////////////////// creatExemplaire ////////////////////
			Exemplaire e1 = new Exemplaire(110, "chose");
			Exemplaire e2 = new Exemplaire(112, "lamain");
			
			
			/////////////////// creatEmpruntCours ////////////////////
			EmpruntEnCours empp1 = new EmpruntEnCours(e1);
			u1.addEmpruntEnCours(empp1);
			
			/////////////////// retardEmpruntCours ////////////////////
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			empp1.setDateEmprunt(sdf.parse("15/03/2016"));
			
			/////////////////// creatEmpruntCours ////////////////////
			EmpruntEnCours empp2 = new EmpruntEnCours(e2);
			u1.addEmpruntEnCours(empp2);
			System.out.println(u1);
			
		}
	

}
