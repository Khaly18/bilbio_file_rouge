package biblio.dao;


import java.util.Date;

import biblio.metier.modele.EmpruntEnCours;
import biblio.metier.modele.Exemplaire;
import biblio.metier.modele.Utilisateur;

public class EmpruntEnCoursDB extends EmpruntEnCours{
	private int idExemplaire;
	private int idUtilisateur;
	
	public EmpruntEnCoursDB(Exemplaire ex, int idUtilisateur) {
		super(ex);
		idExemplaire = ex.getIdExemplaire();
	}
	
	public EmpruntEnCoursDB( Date date, int idExemplaire, int idUtilisateur){
		this.idExemplaire = idExemplaire;
		this.idUtilisateur = idUtilisateur;
		super.setDateEmprunt(date);
	}
	
	public void setDateEmprunt (Date d){
		super.setDateEmprunt(d);
	}
	public Date getDateEmprunt(){
		return super.getDateEmprunt();
	}
	public Utilisateur getEmprunteur(){
		return null;
	}
	@Override
	public String toString() {
		return "idUtili : "+idUtilisateur +" idExemp : " + idExemplaire+" date : " + this.getDateEmprunt();
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

