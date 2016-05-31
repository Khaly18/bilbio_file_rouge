package biblio.metier.modele;

import java.util.Date;

public class EmpruntArchive {
	private Date dateRestitutionEff;
	private Date dateEmprunt;
	private Utilisateur emprunteur;
	private Exemplaire exemplaire;
	
	public EmpruntArchive(EmpruntEnCours eec) {
		dateEmprunt=eec.getDateEmprunt();
		dateRestitutionEff=new Date();
		emprunteur=eec.getEmprunteur();
		exemplaire=eec.getExemplaire();
	}
}
