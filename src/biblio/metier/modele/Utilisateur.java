package biblio.metier.modele;

import java.util.ArrayList;

public class Utilisateur extends Personne{
	private int idUtilisateur;
	private String pwd;
	private String pseudonyme;
	private String categorieUtilisateur;
	private ArrayList<EmpruntEnCours> empruntEnCours = new ArrayList<EmpruntEnCours>();
	private ArrayList<EmpruntArchive> empruntArchives = new ArrayList<EmpruntArchive>();
	
	
	//						____CONSTRUCTOR____
	public Utilisateur(String nom, String prenom, int id) {
		super(nom, prenom);
		this.idUtilisateur = id;
	}
	public Utilisateur(int id) {
		super("Indeterminé", "Indeterminé");
		this.idUtilisateur = id;
	}
	
	
	//**********************	GETTER		**********************

	public String getPwd() {
		return pwd;
	}
	public String getPseudonyme() {
		return pseudonyme;
	}
	public String getCategorieUtilisateur() {
		return categorieUtilisateur;
	}
	public ArrayList<EmpruntArchive> getEmpruntArchives() {
		return empruntArchives;
	}
	public int getNbEmpruntsEnCours(){
		return empruntEnCours.size();
	}
	public int getIdUtilisateur(){
		return idUtilisateur;
	}
	
	//**********************	SETTER		**********************

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public void setPseudonyme(String pseudonyme) {
		this.pseudonyme = pseudonyme;
	}
	public void setCategorieUtilisateur(String categorieUtilisateur) {
		this.categorieUtilisateur = categorieUtilisateur;
	}
	public void setEmpruntEnCours(ArrayList<EmpruntEnCours> empruntEnCours) {
		this.empruntEnCours = empruntEnCours;
	}
	public void setEmpruntArchives(ArrayList<EmpruntArchive> empruntArchives) {
		this.empruntArchives = empruntArchives;
	}
	
	// ***********************		METHODE METIER		*************************
	
	public void addEmpruntEnCours(EmpruntEnCours ep){
		ep.getExemplaire().setStatus(EnumStatusExemplaire.PRETE);
		empruntEnCours.add(ep);
	}
	public ArrayList<EmpruntEnCours> getEmpruntEnCours(){
		return empruntEnCours;
	}
	public void retour(EmpruntEnCours ep){
		ep.getExemplaire().setStatus(EnumStatusExemplaire.DISPONIBLE);
		empruntEnCours.remove(ep);
		//modifier les parametre de lobjet pour la construction
		//empruntArchives.add(new EmpruntArchive(ep));
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
