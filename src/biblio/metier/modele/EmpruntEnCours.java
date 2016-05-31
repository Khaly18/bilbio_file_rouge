package biblio.metier.modele;

import java.util.Date;

public class EmpruntEnCours {
	private Date dateEmprunt;
	private Utilisateur emprunteur;
	private Exemplaire exemplaire=null;
	
	public EmpruntEnCours(Exemplaire ex) {
		exemplaire =ex;
		dateEmprunt =new Date();
	}
	public void setDateEmprunt (Date d){
		dateEmprunt = d;
	}
	public Date getDateEmprunt(){
		return dateEmprunt;
	}
	public Exemplaire getExemplaire(){
		return exemplaire;
	}
	public Utilisateur getEmprunteur(){
		return emprunteur;
	}
	@Override
	public String toString() {
		return " isbn "+exemplaire.getISBN();
	}
	
}
