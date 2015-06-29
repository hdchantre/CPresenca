package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainDAO {
	
	private Connection connection;
	
	public Connection conectarDB(){
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver error");
			e.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5433/projeto", "postgres",
					"jesik846213");
		} catch (SQLException e) {
			System.out.println("Connection Failed");
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public void fecharConexaoDB(){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
