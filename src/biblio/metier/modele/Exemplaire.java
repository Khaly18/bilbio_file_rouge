package biblio.metier.modele;

import java.util.Date;

public class Exemplaire {
	
	private int idExemplaire;
	private Date dateAchat;
	private EnumStatusExemplaire status=EnumStatusExemplaire.DISPONIBLE;
	private String isbn;
	
	public Exemplaire(int id, String isbn) {
		this.idExemplaire=id;
		this.isbn=isbn;
	}
	public Exemplaire(int idExemplaire, Date dateAchat, EnumStatusExemplaire status, String isbn) {
		this.idExemplaire = idExemplaire;
		this.dateAchat = dateAchat;
		this.status = status;
		this.isbn = isbn;
	}
	public int getIdExemplaire(){
		return idExemplaire;
	}
	public String getISBN(){
		return isbn;
	}
	public void setStatus(EnumStatusExemplaire ese){
		status = ese;
	}
	public EnumStatusExemplaire getStatus() {
		return status;
	}
	public Date getDateAchat() {
		return dateAchat;
	}
	public String getIsbn() {
		return isbn;
	}
	@Override
	public String toString() {
		return idExemplaire+" isbn: "+isbn+" statuts: "+status;
	}
}
