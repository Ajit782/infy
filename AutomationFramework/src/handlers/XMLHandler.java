package handlers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import utils.*;

public class XMLHandler {

	// / <summary>
	// / Author : Rohit Vyas
	// / Description : Opening Environment(xml) File
	// / </summary>

	public static Document OpenXML(String xmlFilePath) throws Exception {
		// CloseXML(xmlFilePath);

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(xmlFilePath);
       	return doc;
	}
	// / <summary>
	// / Author : Rohit Vyas
	// / Description : Closing Environment(xml) File
	// / </summary>

	public static void CloseAllOpenXML() throws IOException, Exception {
		if (UtilityFunctions.isProcessRunning("XML.EXE")) {
			Runtime.getRuntime().exec("taskkill /IM XML.EXE");
		}
	}

	/*
	 * public static void CloseXML(String xmlFilePath) throws IOException,
	 * Exception
	 * 
	 * { try { File xmlfile=new File(xmlFilePath);
	 * 
	 * //if(UtilityFunctions.isFileOpened(xmlfile) && xmlfile.exists()) //{
	 * FileOutputStream xmlInputFile = new FileOutputStream(xmlfile);
	 * xmlInputFile.close(); //} } catch (Exception e) { e.printStackTrace(); }
	 * }
	 */

	// / <summary>
	// / Author : Rohit Vyas
	// / Description : Storing Values of Environment File in Hashmap in key
	// Value Pair
	// / </summary>

	public static Map<String, String> populateXMLDictionary(Document doc) {
		try {
			Map<String, String> Dictionary = new HashMap<String, String>();
         	Dictionary.clear();
         	Node parentNode = doc.getFirstChild();
			NodeList childNode = parentNode.getChildNodes();
     		Node currentNode;
			for (int nodeCounter = 0; nodeCounter < childNode.getLength(); nodeCounter++) {
				currentNode = childNode.item(nodeCounter);
				Dictionary.put(currentNode.getNodeName(),
						currentNode.getTextContent());
			}
			return Dictionary;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
