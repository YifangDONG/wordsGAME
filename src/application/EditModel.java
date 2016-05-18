package application;


import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EditModel {
	
	private ObservableList data;
	private List<Vocabulary> wordlist;
	private Dom dom;
	private List<Book> book;
	
	public ObservableList showWords(String file) {
		
		wordlist = new ArrayList();
		dom = new Dom();
		try {
			Vector root = dom.readXMLFile(file);
			Iterator item = root.iterator();
			while(item.hasNext()){
				Vocabulary voc = (Vocabulary) item.next();
				wordlist.add(new Vocabulary(voc.getId(),voc.getWord(),voc.getPos(),voc.getTrans()));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data = FXCollections.observableArrayList(wordlist);
		return data;
	}
	
	public void saveinFile(String file, ObservableList<Vocabulary> table) throws Exception {
		dom = new Dom();
		wordlist = new ArrayList();
		for (int i = 0; i < table.size(); i++) {
			wordlist.add(table.get(i));
		}
		dom.writeXMLFile(file, wordlist);
	}
	
	public ObservableList showBooks(String username) throws SQLException {
		return FXCollections.observableArrayList(getBooks(username));
	}
	
	public List<Book> getBooks(String username) throws SQLException {
		book = new ArrayList();
		Connection connection = SqliteConnection.Connector();
		PreparedStatement statement = null;
		ResultSet result = null;
		
		String sql = "select bookname from user_book where username = ? ";
		statement = connection.prepareStatement(sql);
		statement.setString(1, username);
		result = statement.executeQuery();
		while(result.next()) {
			book.add(new Book(result.getString(1),"src"+"/"+username+"_"+result.getString(1)+".xml"));
		}
		statement.close();
		result.close();
		return book;
	}
	public void deleteBook(String user, String bookname, String path) {
		File file = new File(path);
		file.delete();
		Connection connection = SqliteConnection.Connector();
		String sql = "DELETE FROM book where name = ? and path = ?";
		String sql2 = "DELETE FROM user_book where username = ? and bookname = ?";
		PreparedStatement pS;
		try {
			pS = connection.prepareStatement(sql);
			pS.setString(1, bookname);
			pS.setString(2, path);
			pS.executeUpdate();		
			
			pS = connection.prepareStatement(sql2);
			pS.setString(1, user);
			pS.setString(2, bookname);

			pS.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void createBook(String user, String bookname, String path) throws Exception {
		File file = new File(path);
		Dom d = new Dom();
		d.createXMLFile(file.getPath());
		Connection connection = SqliteConnection.Connector();
		String sql = "INSERT INTO user_book"
				+ "(username, bookname) VALUES"
				+ "(? ,? )";
		String sql2 = "INSERT INTO book (name, path) VALUES (?, ?)";
		try {
			PreparedStatement pS = connection.prepareStatement(sql);
			pS.setString(1, user);
			pS.setString(2, bookname);
			pS.executeUpdate();
			
			pS = connection.prepareStatement(sql2);
			pS.setString(1, bookname);
			pS.setString(2, path);
			pS.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			connection.close();
		}
		
	}
}
