package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Game1Controller implements Initializable {

	private String selectedBook;
	private Game1Model model;
	private boolean start = true;
	private static final int SCORE = 10;
	private static final int ROUND = 20;
	private int number = 0;
	final int ROW = 4;
	final int COL = 6;
	@FXML
    private Text output;
	@FXML
	private GridPane grid;
	@FXML
	private Label word;
	@FXML
	private Label answer;
	@FXML
	private Label score;
	@FXML
	private Label time;
	@FXML
	private Button ok;
	@FXML
	private Button next;
	
	public void setBook(String book) {
		selectedBook = book;
		if (selectedBook == null)
			selectedBook = "src/demo.xml";
		model = new Game1Model(selectedBook);
		word.setText(model.getWordlist().get(0).getWord() + " " + model.getWordlist().get(0).getPos());
		alphabetSet(model.shuffleString(model.getWordlist().get(0).getTrans()));
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		score.setText("0");
		new TimeOutModel(ROUND,time);
	}
	
	@FXML
    public void pushButton(ActionEvent e) {
        if (start) {
            output.setText("");
            start = false;
        }
        String value = ((Button)e.getSource()).getText();
        output.setText(output.getText() + value);
        ((Button)e.getSource()).setVisible(false);
    }
	
	public void alphabetSet(List<String> word) {
		for (int i = 0; i < ROW && !word.isEmpty(); i++) {
			for (int j = 0; j < COL && !word.isEmpty(); j++) {
				Button button = new Button();
				button.setText(word.get(0));
				button.setStyle("-fx-base: #DDA0DD");
				button.setOnAction((ActionEvent e)-> pushButton(e));
				grid.add(button, j, i);
				word.remove(0);
			}
		}
	}
	
	@FXML
	public void OK(ActionEvent event) {
		int currentScore = Integer.parseInt(score.getText());
		String currentAnswer = output.getText();
		if (model.getWordlist().get(number).getTrans().equals(currentAnswer) && ok.isVisible()) {
			score.setText(String.valueOf(currentScore + SCORE));
			ok.setVisible(false);
		}
		answer.setText("right answer : " + model.getWordlist().get(number).getTrans());
		output.setDisable(true);
	}
	
	@FXML
	public void Next(ActionEvent event) {
		ok.setVisible(true);
		grid.getChildren().clear();
		output.setText("");
		ok.setText("OK");
		number++;
		number %= model.getWordlist().size();
		word.setText(model.getWordlist().get(number).getWord()
				+ " " + model.getWordlist().get(number).getPos()); 
		alphabetSet(model.shuffleString(model.getWordlist().get(number).getTrans()));
		answer.setText("");
	}
}
