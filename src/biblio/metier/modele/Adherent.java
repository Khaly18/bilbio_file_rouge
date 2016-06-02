package biblio.metier.modele;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import biblio.metier.control.BiblioException;


public class Adherent extends Utilisateur {
	public Adherent(String nom, String prenom, int id) {
		super(nom, prenom, id);

	}
	private String telephone []=new String [2];
	private static int nbMaxPrets = 3;
	private static int dureeMaxPrets = 15;
	
	public boolean isConditionsPretAcceptees (){
		if (getNbRetards()==0 && getEmpruntEnCours().size()<3){
			return true;
		} else {
			return false;
		}
			
	}
	
	public int getNbRetards(){
		
		int i = 0;
		for (EmpruntEnCours eec : getEmpruntEnCours()){
			if (isPretEnRetard(eec)){
				i++;
			}
		}
		return i;
	}
	public boolean isPretEnRetard(EmpruntEnCours emp){
		if (emp!=null){
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
			Calendar calen = Calendar.getInstance();
			calen.setTime((emp.getDateEmprunt()));
			calen.add(Calendar.DAY_OF_YEAR, dureeMaxPrets);
			Date d1 = new Date();
			Date d2 = calen.getTime();
			return d1.after(d2);
		}
		return false;
	}
	@Override
	public void addEmpruntEnCours(EmpruntEnCours ep) {
		if (isConditionsPretAcceptees()){
			super.addEmpruntEnCours(ep);
		} else
			try {
				String mess = "Erreur logique";
				if (getEmpruntEnCours().size()==3)
					mess="Erreur nombre emprunt totale atteint";
				if (getNbRetards()!=0)
					mess="Attention nombre de retard = "+getNbRetards();
				throw new BiblioException(mess);
			} catch (BiblioException e) {
				e.printStackTrace();
			}
		
	}
	@Override
	public String toString() {
		return super.toString();
	}

	public String[] getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		if (this.telephone[0] == null){
			this.telephone[0] = telephone;
		}else{
			this.telephone[1] = telephone;
		}
	}
	
	public void setTelephone (String telephone, int index){
		this.telephone[index] = telephone;
	}
}
