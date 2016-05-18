package application;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

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
	
	public void createXMLFile(String file) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document doc = builder.newDocument();
		Element root = doc.createElement("words");
		Element entry = doc.createElement("entry");
		
		Element id = doc.createElement("id");
		Text text = doc.createTextNode(String.valueOf(0));
        id.appendChild(text);
		entry.appendChild(id);		
		
		Element word = doc.createElement("word");
		text = doc.createTextNode("...");
        word.appendChild(text);
		entry.appendChild(word);
		
		Element pos = doc.createElement("pos");
		text = doc.createTextNode("...");
        pos.appendChild(text);
		entry.appendChild(pos);
		
		Element trans = doc.createElement("trans");
		text = doc.createTextNode("...");
        trans.appendChild(text);
		entry.appendChild(trans);
		
		root.appendChild(entry);
		doc.appendChild(root);
		String result = callWriteXmlString(doc, "gb2312");	
		FileWriter writer = new FileWriter(file);
		writer.write(result);
		writer.close();
	}
	
	public void writeXMLFile(String file, List<Vocabulary> words) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document doc = builder.newDocument();
		
		Element root = doc.createElement("words");
		for (int i = 0; i < words.size(); i++) {
			Element entry = doc.createElement("entry");
			Element id = doc.createElement("id");
			Text text = doc.createTextNode(String.valueOf(i));
	        id.appendChild(text);
			entry.appendChild(id);
			
			Element word = doc.createElement("word");
			text = doc.createTextNode(words.get(i).getWord());
			word.appendChild(text);
			entry.appendChild(word);
			
			Element pos = doc.createElement("pos");
			text = doc.createTextNode(words.get(i).getPos());
			pos.appendChild(text);
			entry.appendChild(pos);
			
			Element trans = doc.createElement("trans");
			text = doc.createTextNode(words.get(i).getTrans());
			trans.appendChild(text);
			entry.appendChild(trans);
			
			root.appendChild(entry);
		}
		doc.appendChild(root);
		String result = callWriteXmlString(doc, "gb2312");	
		FileWriter writer = new FileWriter(file);
		writer.write(result);
		writer.close();
	}
	
	private String callWriteXmlString(Document doc, String encoding) {
        try {
            Source source = new DOMSource(doc);
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            OutputStreamWriter write = new OutputStreamWriter(outStream);
            Result result = new StreamResult(write);

            Transformer xformer = TransformerFactory.newInstance()
                    .newTransformer();
            xformer.setOutputProperty(OutputKeys.ENCODING, encoding);

            xformer.transform(source, result);
            return outStream.toString();

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            return null;
        } catch (TransformerException e) {
            e.printStackTrace();
            return null;
        }
    }
}
