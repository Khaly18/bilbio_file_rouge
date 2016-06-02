package biblio.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

/**
 * @author John-Weetaker
 *
 */
public class ConnectionFactory{
	private static final Properties connJDBC = new Properties();
	private static Connection conn =null;
	
	private ConnectionFactory(){
	}
	
	public static Connection getConnection(){
		if (conn == null){
			try {
				System.out.println("tentative execution getInstance");
				
				if (connJDBC.getProperty("driver") == null){
					loadProperties(JOptionPane.showInputDialog("Entrez le path du fichier properties"));
				}
				Class.forName(connJDBC.getProperty("driver"));
				return conn = DriverManager.getConnection(connJDBC.getProperty("url"), 
						connJDBC.getProperty("user"), connJDBC.getProperty("pwd"));
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	public static void creatPropertiesFile(String user, String pass, String fileName){
		connJDBC.setProperty("driver", "oracle.jdbc.driver.OracleDriver");
		connJDBC.setProperty("url", "jdbc:oracle:thin:@localhost:1521:xe");
		connJDBC.setProperty("user", user);
		connJDBC.setProperty("pwd", pass);
		writeTheFile(fileName);
	}
	public static void creatPropertiesFile(String driver, String url, String user, String pass, String fileName){
		connJDBC.setProperty("driver", driver);
		connJDBC.setProperty("url", url);
		connJDBC.setProperty("user", user);
		connJDBC.setProperty("pwd", pass);
		writeTheFile(fileName);
	}
	public static void loadProperties(String filePath){
		FileInputStream fis;
		try {
			fis = new FileInputStream(filePath);
			connJDBC.load(fis);
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 *	write the file in the hardDrive with the specified fileName
	 */
	private static void writeTheFile(String fileName){
		try {
			FileOutputStream outPath = new FileOutputStream(fileName+".properties");
			connJDBC.store(outPath, null);
			outPath.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
