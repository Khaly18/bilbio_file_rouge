package biblio.metier.modele;

public class Employe extends Utilisateur {
	
	
	private String codeMatricule;
	private EnumCategorieEmploye categorieEpmloye;
	
	public Employe(String nom, String prenom, int id){
		super(nom, prenom, id);
	}
	public Employe(int id) {
		super(id);
	}
	
	public String getCodeMatricule() {
		return codeMatricule;
	}
	public EnumCategorieEmploye getCategorieEpmloye() {
		return categorieEpmloye;
	}
	
	public void setCodeMatricule(String codeMatricule) {
		this.codeMatricule = codeMatricule;
	}
	public void setCategorieEpmloye(EnumCategorieEmploye categorieEpmloye) {
		this.categorieEpmloye = categorieEpmloye;
	}
	
	
	@Override
	public String toString() {
		return super.toString();
	}
}
