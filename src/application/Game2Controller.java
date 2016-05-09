package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Game2Controller implements Initializable {

	private Game2Model model = new Game2Model("src/demo.xml");
	private boolean start = true;
	private static final int SCORE = 10;
	private static final int ROUND = 20;
	private int number = 0;
	final int ROW = 4;
	final int COL = 3;
	
	@FXML
	private GridPane grid;
	@FXML
	private Label score;
	@FXML
	private Label time;
	@FXML
	private Button next;
	
	List<String> tiles;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		tiles = model.TileWord(ROW*COL>model.getWordlist().size()?ROW*COL/2:model.getWordlist().size()/2);
		Set(tiles);
		score.setText("0");
		time.setText("");
	}
	public void Set(List<String> word) {
		for (int i = 0; i < ROW && !word.isEmpty(); i++) {
			for (int j = 0; j < COL && !word.isEmpty(); j++) {
				Button button = new Button();
				button.setText(tiles.get(i*COL+j));
				button.setPrefSize(100, 80);
				button.setUnderline(false);
				button.setOnAction(e->Click(e));
				grid.add(button, j, i);
			}
		}
	}
	
	int click = 2;
	private Button select = null;
	
	private void Click(ActionEvent event) {
		if(((Button)event.getSource()).isUnderline()) {
			((Button)event.getSource()).setUnderline(false);
			click++;
		}
		else if (click == 2) {
			select = (Button)event.getSource();
			((Button)event.getSource()).setUnderline(true);
			click--;
		}
		else if (click == 1) {
			if(match(select,(Button)event.getSource())) {
				((Button)event.getSource()).setVisible(false);
				select.setVisible(false);
				int currentScore = Integer.parseInt(score.getText());
				score.setText(String.valueOf(currentScore + SCORE));
				click = 2;
			}
			else {
				select.setUnderline(false);
				select = null;
				click = 2;
			}
			
		}
	}
	private boolean match(Button select1, Button select2) {
		if (model.getID(select1.getText()) == model.getID(select2.getText())) {
			return true;
		}
		else {
			return false;
		}
	}
	@FXML
	public void Next(ActionEvent event) {
		grid.getChildren().clear();
		model.shuffle();
		tiles = model.TileWord(ROW*COL>model.getWordlist().size()?ROW*COL/2:model.getWordlist().size()/2);
		Set(tiles);
	}
}
