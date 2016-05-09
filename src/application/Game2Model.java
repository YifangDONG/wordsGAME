package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Vector;


import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Game2Model {

	private List<Vocabulary> wordlist = new ArrayList();
	private Random random = new Random();
	public Game2Model(String file) {
		Dom dom = new Dom();
		try {
			Vector root = dom.readXMLFile(file);
			Iterator item = root.iterator();
			while(item.hasNext()){
				Vocabulary voc = (Vocabulary) item.next();
				wordlist.add(voc);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.shuffle(wordlist);
	}
	public List<Vocabulary> getWordlist() {
		return wordlist;
	}

	public int getID(String word) {
		for(int i = 0;;i++){
			if(word.equals(wordlist.get(i).getWord())){
				return wordlist.get(i).getId();
			}
			if(word.equals(wordlist.get(i).getTrans())){
				return wordlist.get(i).getId();
			}
		}		
	}
	public List<String> TileWord(int num){
		List<String> tiles = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			tiles.add(wordlist.get(i).getWord());
			tiles.add(wordlist.get(i).getTrans());
		}
		Collections.shuffle(tiles);
		return tiles;
	}
	
	public void shuffle() {
		Collections.shuffle(wordlist);
	}
}
