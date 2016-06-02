package biblio.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Trigger {
	private Statement stm;
	private static final DateFormat DF = new SimpleDateFormat("dd/MM/yy", Locale.FRENCH );
	private Connection conn =ConnectionFactory.getConnection();
	
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
	public void declencheur(){
		int retour = 0;
		String sqlBuilder= " DELETE FROM empruntencours WHERE idexemplaire = 3 AND idutilisateur = 4";
		System.out.println(sqlBuilder);
		
		try {
			System.out.println("Code retour "+(retour = stm.executeUpdate(sqlBuilder))+"  OK.");
			
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
		Trigger t= new Trigger();
		t.initConnection();
		t.declencheur();
		t.closeConnection();
	}
}
