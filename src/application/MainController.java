package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainController implements Initializable{
	 
	private static final int ROUND = 20;
	private EditModel data = new EditModel();
	private String selectedBook = "src/demo.xml";
	
	@FXML
	private Button game1; 
	@FXML
	private Button game2; 
	@FXML
	private Button game3; 
	
	@FXML
	private Label user;
	
	@FXML
	private void OnGame1(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Game1.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Game 1");
		stage.show();
		new TimeOut(ROUND,stage);
	}
	
	@FXML
	private void OnGame2(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Game2.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Game 2");
		stage.show();
		new TimeOut(ROUND,stage);
	}
	
	@FXML
	private void OnGame3(ActionEvent event) throws Exception {
		if(event.getSource() == game3) {
			Parent root = FXMLLoader.load(getClass().getResource("Game3.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Game 3");
			stage.show();
			new TimeOut(ROUND,stage);
		}
	}
	
	@FXML
	private TableView<Vocabulary> table;	
	@FXML
	private TableColumn<Vocabulary, Integer> idCol;
	@FXML
	private TableColumn<Vocabulary, String> wordCol;
	@FXML
	private TableColumn<Vocabulary, String> posCol;
	@FXML
	private TableColumn<Vocabulary, String> transCol;
	
	@FXML
	private TextField word;
	@FXML
	private TextField pos;
	@FXML
	private TextField trans;
	
	@FXML
	private TableView<Book> book;
	@FXML
	private TableColumn<Book, String> nameCol;
	@FXML
	private TextField name;
	
	@FXML
	public void add() {
		Vocabulary item = new Vocabulary();
		item.setId(0);
	    item.setWord(word.getText());
	    item.setPos(pos.getText());
	    item.setTrans(trans.getText());
		table.getItems().add(item);
		
		word.clear();
		pos.clear();
		trans.clear();
	}
	
	@FXML
	public void delete() {
		ObservableList<Vocabulary> selected, all;
		all = table.getItems();
		selected = table.getSelectionModel().getSelectedItems();
		selected.forEach(all::remove);
		
	}
	
	@FXML
	public void save() throws Exception {
		data.saveinFile(selectedBook, table.getItems());
		table.setItems(data.showWords(selectedBook));
		user.getText();
	}
	@FXML
	public void bookPage() {
		idCol.setCellValueFactory(new PropertyValueFactory<Vocabulary, Integer>("id"));
		wordCol.setCellValueFactory(new PropertyValueFactory<Vocabulary, String>("word"));
		posCol.setCellValueFactory(new PropertyValueFactory<Vocabulary, String>("pos"));
		transCol.setCellValueFactory(new PropertyValueFactory<Vocabulary, String>("trans"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
		book.setEditable(true);
		table.setEditable(true);
		try {
			book.setItems(data.showBooks(user.getText()));
			book.getSelectionModel().select(0);
			selectedBook = book.getSelectionModel().getSelectedItem().getPath();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		table.setItems(data.showWords(selectedBook));
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	public void GetUser(String user) {
		this.user.setText(user);
	}

}
