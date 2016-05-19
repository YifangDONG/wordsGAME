package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TimeOutController implements Initializable {
	
	@FXML
	private Button OK;
	
	@FXML
	private Label stats;
	
	//Game2Controller game2Controller = new Game2Controller();
	//String tt = game2Controller.score.getText();
	
	@FXML
	private void Close(ActionEvent event) throws Exception {
		if (event.getSource() == OK) {
			((Node)(event.getSource())).getScene().getWindow().hide();
			//((Node)(event.getSource())).getParent().getScene().getWindow().hide();
			
		}

	}
	
	
	
	
	
	
	public void initialize(URL arg0, ResourceBundle arg1) {


		stats.setText("Vous avez ... points !");

		// TODO Auto-generated method stub
		
	}
	

}
