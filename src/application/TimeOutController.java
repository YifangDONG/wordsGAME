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
	
	@FXML
	private void Close(ActionEvent event) throws Exception {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	

	public void setScore(String score) {
		// TODO Auto-generated method stub
		stats.setText("Congratulation! your score£º" + score);
	}
}
