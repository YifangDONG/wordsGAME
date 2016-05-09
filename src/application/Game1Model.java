package application;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class Game1Model {
	/**
	 * the array of Vocabulary which store all the words in this game
	 */
	private List<Vocabulary> wordlist = new ArrayList();
	private Random random = new Random();
	public Game1Model(String file) {
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
	public List<String> shuffleString (String translation) {
		List<String> trans = new ArrayList();
		for (int i = 0; i < translation.length(); i++) {
			trans.add(String.valueOf(translation.charAt(i)));
		}
		Collections.shuffle(trans);
		return trans;
	}
}
