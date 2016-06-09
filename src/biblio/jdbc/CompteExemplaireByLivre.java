package biblio.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class CompteExemplaireByLivre {
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
	public void compteExemplaire(){
		int retour = 0;
		String sqlBuilder= " SELECT '3200066559' AS ISBN, f_compte_exemplaire('3200066559') AS \"Nb Exemplaires non supprimés\" "
				+ "FROM dual";
		System.out.println(sqlBuilder);
		ResultSet result;
		
		
		try {
			System.out.println("Code retour "+(retour = stm.executeUpdate(sqlBuilder))+"  OK.");
			result = stm.getResultSet();
			while(result.next()){
				System.out.println("ISBN : " +result.getString(1)+ " \t Nombre exemplaire : "+ result.getInt(2));
			}
			
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
		CompteExemplaireByLivre cebl = new CompteExemplaireByLivre();
		cebl.initConnection();
		cebl.compteExemplaire();
		cebl.closeConnection();
	}
}


