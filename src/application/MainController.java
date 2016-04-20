package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainController implements Initializable{
	 

	@FXML
	private Button game1; 
	@FXML
	private Button game2; 
	@FXML
	private Button game3; 
	
	@FXML
	private Label user;
	
	@FXML
	private void OnGame3(ActionEvent event) throws Exception {
		if(event.getSource() == game3) {
			Parent root = FXMLLoader.load(getClass().getResource("game3.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Game 3");
			stage.show();
			int ROUND = 20;
			new TimeOut(ROUND,stage);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void GetUser(String user) {
		this.user.setText(user);
	}

}
