package application;

import java.io.IOException;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class SingUpController implements Initializable {

		public SignUpModel addUser = new SignUpModel();
		@FXML
		private Label message;
		@FXML
		private TextField username;
		@FXML
		private PasswordField password;
		@FXML
		private Button SSignUUP;
		@FXML
		private PasswordField password2;
		
		@FXML
		private void Signup(ActionEvent event) throws Exception {
			if (event.getSource() == SSignUUP) {
				if (password.getText().equals(password2.getText() ) ) {
					if (addUser.AddUser(username.getText(),password.getText()) == true) {
						message.setText("New user added succesful");
						((Node)(event.getSource())).getScene().getWindow().hide();
					}
					else {
						message.setText("Please chosse an other username");
					}
				}
				else {
					message.setText("Please enter the same password");
				}
			}

		}
		
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			if (!addUser.isDbConnected()) {
				message.setText("Datebase is not connected");
			}
			
		}


	
	
	
	
	
	
	
	
	
}



