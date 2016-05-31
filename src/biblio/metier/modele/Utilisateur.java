package biblio.metier.modele;

import java.util.ArrayList;

public class Utilisateur extends Personne{
	public Utilisateur(String nom, String prenom, int id) {
		super(nom, prenom);
		this.idUtilisateur = id;
	}
	public Utilisateur(int id) {
		super("Indeterminé", "Indeterminé");
		this.idUtilisateur = id;
	}
	private int idUtilisateur;
	private String pwd;
	private String pseudonyme;
	private ArrayList<EmpruntEnCours> empruntEnCours = new ArrayList<EmpruntEnCours>();
	private ArrayList<EmpruntArchive> empruntArchives = new ArrayList<EmpruntArchive>();
	
	public void addEmpruntEnCours(EmpruntEnCours ep){
		ep.getExemplaire().setStatus(EnumStatusExemplaire.PRETE);
		empruntEnCours.add(ep);
		
		
	}
	public ArrayList<EmpruntEnCours> getEmpruntEnCours(){
		return empruntEnCours;
	}
	public int getNbEmpruntsEnCours(){
		return empruntEnCours.size();
	}
	public void retour(EmpruntEnCours ep){
		ep.getExemplaire().setStatus(EnumStatusExemplaire.DISPONIBLE);
		empruntEnCours.remove(ep);
		empruntArchives.add(new EmpruntArchive(ep));
	}
	public int getIdUtilisateur(){
		return idUtilisateur;
	}
	
	@Override
	public String toString() {
		String s = new String();
		for (EmpruntEnCours eec : empruntEnCours){
			s += eec.toString()+"\n";
		}
		return super.toString()+" id= "+idUtilisateur+" nbEmprunt= "+getNbEmpruntsEnCours()+"\n"+s;
	}
	
}
