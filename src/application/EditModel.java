package application;


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
		book = new ArrayList();
		Connection connection = SqliteConnection.Connector();
		PreparedStatement statement = null;
		ResultSet result = null;
		
		String sql = "select bookname from user_book where username = ? ";
		statement = connection.prepareStatement(sql);
		statement.setString(1, username);
		result = statement.executeQuery();
		while(result.next()) {
			book.add(new Book(result.getString(1),"src"+"/"+result.getString(1)+".xml"));
		}
		statement.close();
		result.close();
		return FXCollections.observableArrayList(book);
	}
}
