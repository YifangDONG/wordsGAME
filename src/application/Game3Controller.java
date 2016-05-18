package application;

import java.net.URL;
import java.util.EventObject;
import java.util.Iterator;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * This is a class for controlling the view of this application
 * @author yifang
 * @version 1.0
 *
 */
public class Game3Controller implements Initializable {

	/**
	 * the number of words in word list for each playing
	 */
	final int NUMBER_OF_WORDS = 10;
	
	/**
	 * the score added when player has one answer correct
	 */
	final int SCORE = 10;
	
	/**
	 * the during of each playing. the default is {@link #ROUND}
	 */
	final int ROUND = 20;
	
	/**
	 * the array of Vocabulary which store all the words in this game
	 */
	private Vocabulary[] wordlist = new Vocabulary[NUMBER_OF_WORDS];
	private Random random = new Random();
	
	/**
	 * when the button hasn't been clicked, clicked = false <br>
	 * when the button has been clicked, clicked = true <br>
	 * it is used to make sure that button can be clicked only one time for each word
	 */
	private boolean clicked = false;
	
	/**
	 * the id of the word which is showed know
	 */
	private int number;
	private int currentTime = 0;
	private String selectedBook = "src/demo.xml";
	@FXML
	private Label word;
	@FXML
	private Label answer;
	@FXML
	private Label score;
	@FXML
	private Label time;
	@FXML
    private TextField writting;
	@FXML
	private Button ok;
	@FXML
	private Button next;

	/**
	 * When the button OK is clicked, this method will be carried out <br>
	 * Function 1 : Judges the answer of player <br>
	 * Function 2 : Shows the right answer <br>
	 * This method is tested by
	 * @param event the button OK is clicked
	 */
	@FXML
	public void OK(ActionEvent event) {
        if (event.getSource() == ok) {
        	int currentScore = Integer.parseInt(score.getText());
        	String currentAnswer = writting.getText();
        	if (wordlist[number].getTrans().equals(currentAnswer) && clicked == false) {
        		score.setText(String.valueOf(currentScore + SCORE));
        		ok.setOnMouseClicked(e -> clicked = true);
        	}
        	answer.setText("right answer : " + wordlist[number].getTrans());
        	ok.setText("");
        }
	}
    
	/**
	 * When the button NEXT is clicked, this method will be carried out <br>
	 * Function : Gives a word from word list randomly <br>
	 * This method is tested by
	 * @param event the button NEXT is clicked
	 */
	@FXML
	public void Next(ActionEvent event) {
		clicked = false;
        if (event.getSource() == next) {
        	writting.setText("");
        	ok.setText("OK");
        	int i = random.nextInt(NUMBER_OF_WORDS);
        	word.setText(wordlist[i].getWord() + " " + wordlist[i].getPos());
        	number = i;
        }
    }
	
	/**
	 * This method will be carried out automatically when this game begins
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		score.setText("0");
		
		Dom t = new Dom();
		try {
			Vector v = t.readXMLFile(selectedBook);
			Iterator it = v.iterator();
			for (int i = 0; i < NUMBER_OF_WORDS ; i++) {
				Vocabulary voc = (Vocabulary) it.next();
				wordlist[i] = new Vocabulary();
				
				wordlist[i].setId(voc.getId()); 
				wordlist[i].setWord(voc.getWord());
				wordlist[i].setPos(voc.getPos());
				wordlist[i].setTrans(voc.getTrans());
			} 
		}catch (Exception e) {
			e.printStackTrace();
		}
    	int i = random.nextInt(NUMBER_OF_WORDS);
    	word.setText(wordlist[i].getWord() + " " + wordlist[i].getPos());
    	number = i;
    	new TimeOut(ROUND,time);
	}
}
