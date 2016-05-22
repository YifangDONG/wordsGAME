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
	public boolean CountUsername (String user) throws SQLException {
		String sql = "SELECT COUNT(*) AS total from user WHERE username = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, user);
			ResultSet result = statement.executeQuery();
			
			
			return (result.getInt("total") == 0 );
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			//connection.close();
			//result.close();
		}
	}
	public boolean AddUser(String user, String password) throws Exception {
		
		String sql = "INSERT INTO user"
				+ "(USERNAME, PASSWORD) VALUES"
				+ "(? ,? )";
		try {
			PreparedStatement pS = connection.prepareStatement(sql);
			pS.setString(1, user);
			pS.setString(2, password);
			pS.executeUpdate();	
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			connection.close();
		}
		EditModel edit = new EditModel();
		String path = "src/" + user + "_demo.xml"; 
		edit.createBook(user, "demo", path);
		return true;
	}
	
}
	




