package biblio.dao;


import java.util.Date;

import biblio.metier.modele.Exemplaire;
import biblio.metier.modele.Utilisateur;

public class EmpruntEnCoursDB {
	private Date dateEmprunt;
	private int idExemplaire;
	private int idUtilisateur;
	
	public EmpruntEnCoursDB(Exemplaire ex, int idUtilisateur) {
		dateEmprunt =new Date();
		idExemplaire = ex.getIdExemplaire();
	}
	public EmpruntEnCoursDB( Date date, int idExemplaire, int idUtilisateur){
		dateEmprunt = date;
		this.idExemplaire = idExemplaire;
		this.idUtilisateur = idUtilisateur;
	}
	
	public void setDateEmprunt (Date d){
		dateEmprunt = d;
	}
	public Date getDateEmprunt(){
		return dateEmprunt;
	}
	public Utilisateur getEmprunteur(){
		return null;
	}
	@Override
	public String toString() {
		return " isbn ";
	}
	public int getIdExemplaire() {
		return idExemplaire;
	}
	public void setIdExemplaire(int idExemplaire) {
		this.idExemplaire = idExemplaire;
	}
	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	
}

