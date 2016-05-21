package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class Game3Model {
	private List<Vocabulary> wordlist = new ArrayList();
	private Random random = new Random();
	public Game3Model(String file) {
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
}
