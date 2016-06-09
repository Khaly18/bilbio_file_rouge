package biblio.dao;

import java.util.Date;

import biblio.metier.modele.EmpruntArchive;
import biblio.metier.modele.EmpruntEnCours;
import biblio.metier.modele.Exemplaire;
import biblio.metier.modele.Utilisateur;

public class EmpruntArchiveDB extends EmpruntArchive{


	public EmpruntArchiveDB(Date dateRestitutionEff, Date dateEmprunt, int idUtilisateur, int idExemplaire) {
		super(dateRestitutionEff, dateEmprunt, idUtilisateur, idExemplaire);
	}
	

}
