package project;
import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;

public class Database {
	/*
	 *	Global Variable
	 *		static final String JDBC_DRIVER , DB_URL, USER, PASS, LOG
	 *		static dateFormat 
	 */
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/";
	static final String USER = "root";
	static final String PASS = "";
	static final String LOG = "./src/project/ArchivedLog.txt";
	static DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss");
	Date date = new Date();
	private Connection conn = null;
	public static Statement stmt = null;
	public static PrintWriter archivedLog;
	public Database(){
		try{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			archivedLog = new PrintWriter(LOG);
			String sql = "Create Database FORK";
			stmt.executeUpdate(sql);
			
			archivedLog.append(dateFormat.format(date)+":\n"+sql);
			
			sql = createRestaurantTable();
			stmt.executeUpdate(sql);
			
			archivedLog.append(dateFormat.format(date)+":\n"+sql);
			
			sql = createCuisineTable();
			stmt.executeUpdate(sql);
			
			archivedLog.append(dateFormat.format(date)+":\n"+sql);
			
			sql = createRestaurantTypeTable();
			stmt.executeUpdate(sql);
			
			archivedLog.append(dateFormat.format(date)+":\n"+sql);
			
			sql = createItemsTable();
			stmt.executeUpdate(sql);
			
		}
		catch(Exception error){
			JOptionPane.showMessageDialog(null, "Database is ready");
		}
	}
	
	private static String createRestaurantTable(){
		String statement = "Create Table Restaurant("
				+ "RestaurantID int NOT NULL AUTO_INCREMENT,"
				+ "Restaurant_Name varchar(50) NOT NULL,"
				+ "Address varchar(90) NOT NULL,"
				+ "City varchar(40) NOT NULL,"
				+ "State varchar(50) NOT NULL,"
				+ "PRIMARY KEY (RestaurantID)"
				+ ")";
		return statement;
	}
	private static String createCuisineTable(){
		String statement = "Create Table Cuisines("
				+ "CuisineID int NOT NULL AUTO_INCREMENT,"
				+ "Cuisine_Type varchar(20) NOT NULL,"
				+ "PRIMARY KEY (CuisineID)"
				+ ")";
		return statement;
	}
	private static String createRestaurantTypeTable(){
		String statement = "Create Table RestaurantType("
				+ "RestaurantTypeID int NOT NULL AUTO_INCREMENT,"
				+ "RestaurantID int NOT NULL,"
				+ "CuisineID int NOT NULL,"
				+ "PRIMARY KEY (RestaurantTypeID),"
				+ "FOREIGN KEY (RestaurantID) REFERENCES Restaurant(RestaurantID),"
				+ "FOREIGN KEY (CuisineID) REFERENCES Cuisines(CuisineID)"
				+ ")";
		return statement;
	}
	private static String createItemsTable(){
		String statement = "Create Table Items("
				+ "ItemID int NOT NULL AUTO_INCREMENT,"
				+ "Item_Name varchar(50) NOT NULL,"
				+ "Item_Price DECIMAL(3,2) NOT NULL,"
				+ "Item_Description varchar(200),"
				+ "RestaurantID int NOT NULL,"
				+ "PRIMARY KEY (ItemID),"
				+ "FOREIGN KEY (RestaurantID) REFERENCES Restaurant(RestaurantID)"
				+ ")";
		return statement;
	}
}
