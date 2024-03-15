package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
	
	private String dbHostName = "shared in the channel";
	private String username = "craterdbuser";
	private String password = "shared in the channel";
	
	private Connection connection;
	private Statement statement;
	private ResultSet resultset;
	private ResultSetMetaData rsmd;
	
	
	
	static String selectAitemQuery = "Select * From items Where name='NewItem805'";
	
	
	
	public static void main(String[] args) {
		DBUtils utils = new DBUtils();
		List<String> itemInfo = utils.selectARecord(selectAitemQuery);
		
		for (String item : itemInfo) {
			System.out.println(item);
		}
	}
	
	
	// this method gets a record from the data as per the query
	public List<String> selectARecord(String query){
		List<String> list = new ArrayList<>();
		try {
			connection = DriverManager.getConnection(dbHostName, username, password);
			System.out.println("DB connection established.");
			statement = connection.createStatement();
			resultset = statement.executeQuery(query);
			rsmd = resultset.getMetaData();
			
			resultset.next();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				list.add(resultset.getString(i));
			}
		} catch (SQLException e) {
		System.out.println("DB connection Not established.");
			e.printStackTrace();
		}
		return list;
	}
	
	
	// this method inserts a record to database table as per the query
	public void insertRecord(String query) {
		try {
			connection = DriverManager.getConnection(dbHostName, username, password);
			System.out.println("DB connection established.");
			statement = connection.createStatement();
			statement.executeUpdate(query);
			connection.close();
		} catch (SQLException e) {
			System.out.println("DB connection Not established.");
			e.printStackTrace();
		}
	}
	
	// this method updates a record as per the query
	public void updateRecord(String query) {
		try {
			connection = DriverManager.getConnection(dbHostName, username, password);
			System.out.println("DB connection established.");
			statement = connection.createStatement();
			statement.executeUpdate(query);
			connection.close();
		} catch (SQLException e) {
			System.out.println("DB connection Not established.");
			e.printStackTrace();
		}
	}
	
	// this method deletes a record as per the query; Delete action is done using id of the record only 
	public void deleteRecord(String query) {
		try {
			connection = DriverManager.getConnection(dbHostName, username, password);
			System.out.println("DB connection established.");
			statement = connection.createStatement();
			statement.executeUpdate(query);
			connection.close();
		} catch (SQLException e) {
			System.out.println("DB connection Not established.");
			e.printStackTrace();
		}
	}
	
	

}
