package biblio.metier.modele;

public class Employe extends Utilisateur {
	public Employe(String nom, String prenom, int id){
		super(nom, prenom, id);
	}
	
	public Employe(int id) {
		super(id);
	}
	private String codeMatricule;
	private EnumCategorieEmploye categorieEpmloye;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
