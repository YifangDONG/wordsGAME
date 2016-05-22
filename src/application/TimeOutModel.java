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
	private Label time;
	private int currenttime = 0;
	/**
	 * the during of each playing. 
	 */
	private int round;
	/**
	 * constructor which sets second and time label
	 * @param second time delayed before task is to be executed
	 * @param time the label which shows the time left
	 */
	public TimeOutModel(int second, Label time) {
		this.time = time;
		round = second;
		timer = new Timer();
		timer.schedule(new Task(), 0, 1000 );
	}
	
	class Task extends TimerTask {

		@Override
		public void run() {
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					if (currenttime <= round) {
						time.setText(String.valueOf(round - currenttime++));
					}
				}
			});
		}	
	}
	

	
	
	
	
	
	
	
	
	
	

}

