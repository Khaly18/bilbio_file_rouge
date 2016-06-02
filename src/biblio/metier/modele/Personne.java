package biblio.metier.modele;

import java.util.Date;

public abstract class Personne {
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private char sexe; 
	
	public Personne(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}
	
	@Override
	public String toString() {
		return nom+" "+prenom;
	}
	
	//						______GETTER______	
	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public char getSexe() {
		return sexe;
	}
	
	
	//							______SETTER______	
	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public void setSexe(char sexe) {
		this.sexe = sexe;
	}
	
	
	
	
	
	
}
