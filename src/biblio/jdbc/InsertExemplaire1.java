package biblio.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import biblio.dao.EmpruntEnCoursDB;
import biblio.dao.EmpruntEnCoursDao;
import biblio.dao.UtilisateurDao;
import biblio.metier.modele.Employe;
import biblio.metier.modele.Exemplaire;
import biblio.metier.modele.Utilisateur;

public class InsertExemplaire1 {
	private Statement stm;
	private static final DateFormat DF = new SimpleDateFormat("dd/MM/yy", Locale.FRENCH );
	private Connection conn =ConnectionFactory.getConnectionSansAutocomit();
	
	public void initConnection(){
		try {
			this.stm = conn.createStatement();
			System.out.println("Connecting to a selected database ...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("ERROR : Can not close the connection");
		}
	}
	public void insertExemplaire(Exemplaire ex){
		int retour = 0;
		String d = dateToString(ex.getDateAchat());
		String sqlBuilder= "INSERT INTO exemplaire (idexemplaire, dateachat, status, isbn) VALUES ("
		+25+", '"+d+"' , '"+ex.getStatus()+"' , '"+ex.getIsbn()+"')";
		System.out.println(sqlBuilder);
		
		
		try {
			System.out.println("Code retour "+(retour = stm.executeUpdate(sqlBuilder))+"  OK.");
			conn.commit();
			
		} catch (SQLException e) {
			System.out.println("Code ERR : "+retour+" Echec Insertion");
			e.printStackTrace();
		}
	}
	
	public static  String dateToString(Date date){
		return DF.format(date);
	}
	public static Date stringToDate(String string){
		try {
			return DF.parse(string);
		} catch (ParseException e) {
			System.out.println("Can not convert string to date");
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		InsertExemplaire1 ie1 = new InsertExemplaire1();
		ie1.initConnection();
		//Exemplaire ex = new Exemplaire(2311, InsertExemplaire1.stringToDate("18/01/16"), EnumStatusExemplaire.DISPONIBLE, "3200066559" );
		//System.out.println(InsertExemplaire1.stringToDate("18/01/16"));
		//ie1.insertExemplaire(ex);
		Connection cnx = ConnectionFactory.getConnectionSansAutocomit();
		System.out.println(cnx.equals(ConnectionFactory.getConnectionSansAutocomit()));
		UtilisateurDao u1= new UtilisateurDao(cnx);
		Utilisateur utilisateur1 =u1.findByKey(6);
		Employe emp = (Employe) utilisateur1;
		//System.out.println(emp);
		//System.out.println(u1.findAll());
		EmpruntEnCoursDB eec = new EmpruntEnCoursDB(new Date(), 25, 6);
		EmpruntEnCoursDao eecd = new EmpruntEnCoursDao(cnx);
		eecd.insertEmpruntEnCours(eec);
		//EmpruntEnCoursDB eec = new EmpruntEnCoursDB(new Date(), 25, 6);
		//eecd.insertEmpruntEnCours(eec);
		eecd.retourEmprunt(25);
		//ie1.closeConnection();
		
	}
}
