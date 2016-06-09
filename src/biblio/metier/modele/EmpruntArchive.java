package biblio.metier.modele;

import java.util.Date;

public class EmpruntArchive {
	private Date dateRestitutionEff;
	private Date dateEmprunt;
	private int idUtilisateur;
	private int idExemplaire;
	
	

	public EmpruntArchive(Date dateRestitutionEff, Date dateEmprunt, int idUtilisateur, int idExemplaire) {
		this.dateRestitutionEff = dateRestitutionEff;
		this.dateEmprunt = dateEmprunt;
		this.idUtilisateur = idUtilisateur;
		this.idExemplaire = idExemplaire;
	}
	
	
}
