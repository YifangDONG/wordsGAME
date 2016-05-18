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

public class LoginController implements Initializable {

	public LoginModel loginModel = new LoginModel();
	@FXML
	private Label message;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Button login;
	@FXML
	private Button signup;
	
	@FXML
	private void Signup(ActionEvent event) throws Exception {
		if (event.getSource() == signup) {
			FXMLLoader loader = new FXMLLoader();
			Parent root = loader.load(getClass().getResource("SignUp.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Sign up");
			stage.show();			
		}
	}
	@FXML
	private void Login(ActionEvent event) throws Exception {
		if (event.getSource() == login) {
			if (loginModel.CheckLogin(username.getText(),password.getText()) == true) {
				((Node)(event.getSource())).getScene().getWindow().hide();
				FXMLLoader loader = new FXMLLoader();
				Parent root = loader.load(getClass().getResource("Main.fxml").openStream());
				MainController mainController = (MainController)loader.getController();
				mainController.GetUser(username.getText());
				Stage stage = new Stage();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Word Game");
				stage.show();
			}
			else {
				message.setText("Invalid login or password. Please try again.");
			}
		}
		
	}

	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if (!loginModel.isDbConnected()) {
			message.setText("Datebase is not connected");
		}
		
	}

}
