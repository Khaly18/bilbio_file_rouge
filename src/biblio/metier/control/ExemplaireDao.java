package biblio.metier.control;

import java.util.ArrayList;

import biblio.metier.modele.Exemplaire;

public class ExemplaireDao {
	private ArrayList<Exemplaire> exemplaireDB=new ArrayList<>();
	
	public ExemplaireDao() {
		Exemplaire e1 = new Exemplaire(110, "chose");
		Exemplaire e2 = new Exemplaire(112, "lamain");
		Exemplaire e3 = new Exemplaire(212, "mercredi");
		Exemplaire e4 = new Exemplaire(311, "mortitiaAdams");
		addExemplaire(e1);
		addExemplaire(e2);
		addExemplaire(e3);
		addExemplaire(e4);
	}
		
	public Exemplaire findByKey(int id){
		Exemplaire e1 = null;
		for (Exemplaire e: exemplaireDB){
			if (e.getIdExemplaire()==id)
				e1=e;
		}
		return e1;
	}
	public void addExemplaire(Exemplaire e){
		exemplaireDB.add(e);
	}
}
