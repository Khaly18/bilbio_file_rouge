package biblio.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

import biblio.metier.modele.EnumStatusExemplaire;
import biblio.metier.modele.Exemplaire;

public class InsertExemplaire1 {
	private Statement stm;
	private static final DateFormat DF = new SimpleDateFormat("dd/MM/yy", Locale.FRENCH );
	private Connection conn =ConnectionProperties.getInstance();
	
	public void initConnection(){
		try {
			conn.setAutoCommit(false);
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
		ConnectionProperties.creatPropertiesFile("biblio", "biblio", "jdbc");
		InsertExemplaire1 ie1 = new InsertExemplaire1();
		ie1.initConnection();
		Exemplaire ex = new Exemplaire(2311, InsertExemplaire1.stringToDate("18/01/16"), EnumStatusExemplaire.DISPONIBLE, "3200066559" );
		System.out.println(InsertExemplaire1.stringToDate("18/01/16"));
		ie1.insertExemplaire(ex);
	}
}
