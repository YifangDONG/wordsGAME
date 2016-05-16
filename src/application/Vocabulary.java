package application;
/**
 * This is a class for representing the Four-dimensional data of vocabulary in the game
 * @author yifang
 * @version 1.0
 *
 */

public class Vocabulary {
	
	/**
	 * this vocabulary's id 
	 */
	private int id;
	
	/**
	 * this vocabulary's word
	 */
	private String word;
	
	/**
	 * this vocabulary's Part of Speech
	 */
	private String pos;
	
	/**
	 * this vocabulary's translation
	 */
	private String trans;
	
	/**Gets id of this vocabulary 
	 * @return the id
	 */
	public Vocabulary() {
		
	}
	public Vocabulary(int id, String word, String pos, String trans) {
		this.id = id;
        this.word = word;
        this.pos = pos;
        this.trans = trans;
    }
	
	public Vocabulary(String word, String pos, String trans) {
        this.word = word;
        this.pos = pos;
        this.trans = trans;
    }
	
	public final int getId() {
		return id;
	}
	/**Sets the id of this vocabulary to "id"
	 * @param id the id to set
	 */
	
	public final void setId(int id) {
		this.id = id;
	}
	/**Gets word of this vocabulary
	 * @return the word
	 */
	public final String getWord() {
		return word;
	}
	/**Sets the word of this vocabulary to "word"
	 * @param word the word to set
	 */
	public final void setWord(String word) {
		this.word = word;
	}
	/**Gets the part of speech of this vocabulary
	 * @return the pos
	 */
	public final String getPos() {
		return pos;
	}
	/**Sets the part of speech of this vocabulary to "pos"
	 * @param pos the pos to set
	 */
	public final void setPos(String pos) {
		this.pos = pos;
	}
	/**Gets translation of this vocabulary
	 * @return the trans
	 */
	public final String getTrans() {
		return trans;
	}
	/**Sets the translation of this vocabulary to "trans"
	 * @param trans the trans to set
	 */
	public final void setTrans(String trans) {
		this.trans = trans;
	}
	
}