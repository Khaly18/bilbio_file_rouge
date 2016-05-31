package biblio.metier.modele;

import java.util.Date;

public abstract class Personne {
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String sexe; 
	
	public Personne(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}
	
	@Override
	public String toString() {
		return nom+" "+prenom;
	}
	
}
