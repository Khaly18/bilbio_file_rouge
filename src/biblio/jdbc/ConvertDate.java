package biblio.jdbc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ConvertDate {
	private static final DateFormat DF = new SimpleDateFormat("dd/MM/yy", Locale.FRENCH );
	//private static final DateFormat DFsql = new SimpleDateFormat()"dd-MM-yy"
	
	public static  String dateToString(Date date){
		return DF.format(date);
	}
	public static Date stringToDate(String string){
		try {
			return DF.parse(string);
		} catch (ParseException e) {
			System.out.println("Can not convert string to date");
			//e.printStackTrace();
		}
		return null;
	}
	private Date stringToDateSqlFormat(String string){
		return null;
	}
}
