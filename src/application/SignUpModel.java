package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class SignUpModel {
	Connection connection;
	
	public SignUpModel() {
		connection = SqliteConnection.Connector();
		if (connection == null)
			System.exit(1);
	}
	
	public boolean isDbConnected() {
		try {
			return !connection.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean AddUser(String user, String password) throws SQLException {
		
		
		String sql = "INSERT INTO user"
				+ "(USERNAME, PASSWORD) VALUES"
				+ "(? ,? )";
		String sql2 = "INSERT INTO user_book (username, bookname) VALUES (?, ?)";
		try {
			PreparedStatement pS = connection.prepareStatement(sql);
			pS.setString(1, user);
			pS.setString(2, password);
			pS.executeUpdate();
			
			pS = connection.prepareStatement(sql2);
			pS.setString(1, user);
			pS.setString(2, "demo");
			pS.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			connection.close();
		}
	}
	
}
	




