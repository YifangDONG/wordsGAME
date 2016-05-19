package application;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TimeOutModel {
	
	
	
	
	
	
	
	
	
	
	
	
	


	private Timer timer;
	public Stage stage;
	private Label time;
	private int currenttime = 0;
	/**
	 * the during of each playing. 
	 */
	private int round;
	
	/**
	 * constructor which sets second and stage
	 * @param second time delayed before task is to be executed
	 * @param stage the stage of this main application
	 */
	public TimeOutModel (int second, Stage stage) {
		timer = new Timer();
		this.stage = stage;
		timer.schedule(new Task1(), second * 1000);
	}
	/**
	 * constructor which sets second and time label
	 * @param second time delayed before task is to be executed
	 * @param time the label which shows the time left
	 */
	public TimeOutModel(int second, Label time) {
		this.time = time;
		round = second;
		timer = new Timer();
		timer.schedule(new Task2(), 0, 1000 );
	}
	class Task1 extends TimerTask {

		@Override
		public void run() {
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					try {
						display("Game over", "congratulation !");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
				}
			});	
		}
	}
	/**
	 * Displays a new window with title and message 
	 * @param title the title of this window
	 * @param message the message showed in this window
	 * @throws IOException 
	 */
	
	public void display(String title, String message) throws IOException {
		if (stage.isShowing()) {
			//Parent root = loader.load(getClass().getResource("TimeOutController.fxml").openStream());
			Parent root = FXMLLoader.load(getClass().getResource("TimeOut.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			//Group root = new Group();			
			stage.setScene(scene);
			//stage.setScene(scene);
			stage.setTitle("TimeOutController");
			stage.show();
		}
	}
	
	

	
	
	
	
	class Task2 extends TimerTask {

		@Override
		public void run() {
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					if (currenttime <= round) {
						time.setText(String.valueOf(round - currenttime++));
					}
				}
			});
		}	
	}
	

	
	
	
	
	
	
	
	
	
	

}

