package application;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class TimeOut {

	private Timer timer;
	private Stage stage;
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
	public TimeOut (int second, Stage stage) {
		timer = new Timer();
		this.stage = stage;
		timer.schedule(new Task(), second * 1000);
	}
	/**
	 * constructor which sets second and time label
	 * @param second time delayed before task is to be executed
	 * @param time the label which shows the time left
	 */
	public TimeOut(int second, Label time) {
		this.time = time;
		round = second;
		timer = new Timer();
		timer.schedule(new Task2(), 0, 1000 );
	}
	class Task extends TimerTask {

		@Override
		public void run() {
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					display("Game over", "congratulation !");		
				}
			});	
		}
	}
	/**
	 * Displays a new window with title and message 
	 * @param title the title of this window
	 * @param message the message showed in this window
	 */
	public void display(String title, String message) {
        Stage finish = new Stage();

        //Block events to other windows
        finish.initModality(Modality.APPLICATION_MODAL);
        
        finish.setTitle(title);
        finish.setMinWidth(250);
        finish.setMinHeight(150);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> {
        	finish.close();
        	stage.close();
        });
        finish.setOnCloseRequest(e -> {
        	finish.close();
        	stage.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        finish.setScene(scene);
        finish.showAndWait();
    }
	class Task2 extends TimerTask {

		@Override
		public void run() {
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					if (currenttime <= round)
	            		time.setText(String.valueOf(round - currenttime++));
				}
			});
		}	
	}
}
