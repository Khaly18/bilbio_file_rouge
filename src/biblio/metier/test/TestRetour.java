package biblio.metier.test;

import biblio.metier.control.ExemplaireDao;
import biblio.metier.modele.Adherent;
import biblio.metier.modele.EmpruntEnCours;
import biblio.metier.modele.Exemplaire;

public class TestRetour {
	public static void main(String[] args) {
		// Création de 4 exemplaires
		
				System.out.println("//////////////Création de 4 exemplaires/////////////\n");
				Exemplaire e1 = new Exemplaire(01, "L'oeil le plus bleu");
				Exemplaire e2 = new Exemplaire(02, "Moi, Tituba");
				Exemplaire e3 = new Exemplaire (03, "Gone Girl");
				Exemplaire e4 = new Exemplaire (04, "The Grownup");
				
				// Ajout des exemplaires dans la DB
				
				System.out.println("//////////////Ajout des exemplaires dans la DB/////////////\n");
				ExemplaireDao exemplaireData  = new ExemplaireDao();
				exemplaireData.addExemplaire(e1);

				// Création de 2 Adhérents
						
				System.out.println("//////////////Création de 2 adhérents/////////////\n");
				Adherent a1 = new Adherent("Jean-Jules", "Dormevil", 111);
				Adherent a2 = new Adherent("Juno", "Dormevil", 112);
				
				//Création d'un emprunt en cours pour un adhérent
					
				System.out.println("\n///////////Création de 3 emprunts en cours pour un adhérent ////////\n");
				EmpruntEnCours eec = new EmpruntEnCours(e1);
				a1.addEmpruntEnCours(eec);
				EmpruntEnCours eec2 = new EmpruntEnCours(e2);
				a1.addEmpruntEnCours(eec2);
				EmpruntEnCours eec3 = new EmpruntEnCours(e3);
				a1.addEmpruntEnCours(eec3);
				
				System.out.println(a1);

				// Retour d'un ouvrage
				System.out.println("L'utilisateur rend un ouvrage.\n");
				a1.retour(eec2);
				
				//vérification du statut de l'ouvrage rendu
				System.out.println("Le statut de l'ouvrage est : ");
				System.out.println(e2.getStatus());
				
				//vérification de la diminution de la collection de l'adhérent
				System.out.println("vérification de la diminution de la collection de l'adhérent");
				System.out.println(a1 + "\n");
				
				// vérification de la création de l'emprunt archivé
				System.out.println("Contenu de la collection emprunt archivé");
				System.out.println(eec2);
				
			
				

			
	}
}
