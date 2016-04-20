package application;

import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * This class is used for extracting information from XML format file
 * @author yifang
 * @version 1.0
 *
 */

/*
 * the structure of XML file : 
 * <words>
 * 		<entry>
 * 			<id>0</id>
 * 			<word>computer</word>
 * 			<pos>n.</pos>
 * 			<trans>ordinateur</trans>
 * 		</entry>
 * </words>
 */
public class Dom {

	Vector vocabulary_Vector;
	
	/**
	 * Extracts information from XML format file
	 * @param file the XML format file where we want to get information
	 * @return The vector of vocabulary
	 * @throws Exception
	 */
	public Vector readXMLFile(String file) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document doc = builder.parse(file); // get xml document
		
		//Begins to read from xml document
		Element root = doc.getDocumentElement(); //get root element
		NodeList vocabularys = root.getElementsByTagName("entry");
		vocabulary_Vector = new Vector();
		for (int i = 0; i< vocabularys.getLength(); i++) {
			//Gets all elements in one entry
			Element item = (Element) vocabularys.item(i);
			
			//Creates a voc instance
			Vocabulary voc = new Vocabulary();
			
			//Gets information marked by tag id.
			NodeList id = item.getElementsByTagName("id");
			Element e = (Element) id.item(0);
			org.w3c.dom.Node t = e.getFirstChild();
			voc.setId(Integer.parseInt(t.getNodeValue()));
			
			//Gets information marked by tag word.
			NodeList word = item.getElementsByTagName("word");
			e = (Element) word.item(0);
			t = e.getFirstChild();
			voc.setWord(t.getNodeValue());
			
			//Gets information marked by tag pos.
			NodeList pos = item.getElementsByTagName("pos");
			e = (Element) pos.item(0);
			t = e.getFirstChild();
			voc.setPos(t.getNodeValue());
			
			//Gets information marked by tag trans.
			NodeList trans = item.getElementsByTagName("trans");
			e = (Element) trans.item(0);
			t = e.getFirstChild();
			voc.setTrans(t.getNodeValue());
			
			vocabulary_Vector.add(voc);
		}
		return vocabulary_Vector;
	}
}
