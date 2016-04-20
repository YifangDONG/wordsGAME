package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
	Connection connection;
	
	public LoginModel() {
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
	
	public boolean CheckLogin(String user, String password) throws SQLException {
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = "select * from user where username = ? and password = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, user);
			statement.setString(2, password);
			result = statement.executeQuery();
			if (result.next()) {
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			statement.close();
			result.close();
		}
	}
}
