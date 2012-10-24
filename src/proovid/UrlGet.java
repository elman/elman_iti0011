package proovid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class UrlGet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		
		String rida; 
        URL loto;
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			loto = new URL("https://groups.google.com/group/iti0011/feed/rss_v2_0_msgs.xml");
	        URLConnection ee = loto.openConnection();
	        ee.setRequestProperty("User-agent", "Mozilla/5.0");
	        BufferedReader in = new BufferedReader(new InputStreamReader(ee.getInputStream()));
	        
	        Document doc = db.parse(ee.getInputStream());
	        Element docEle = doc.getDocumentElement();
            System.out.println("Root element of the document: "
                    + docEle.getNodeName());
            
            NodeList list = docEle.getElementsByTagName("item");
            if (list != null && list.getLength() > 0) {
            	for (int i = 0; i < list.getLength(); i++) {
            		Node node = list.item(i);
            		if (node.getNodeType() == Node.ELEMENT_NODE) {
                        System.out
                        .println("=====================");

                        Element e = (Element) node;
                        NodeList nodeList = e.getElementsByTagName("title");
                        System.out.println("Pealkiri: "
                        		+ nodeList.item(0).getChildNodes().item(0)
                                .getNodeValue());

                        nodeList = e.getElementsByTagName("description");
                        System.out.println("Sisu: "
                        		+ nodeList.item(0).getChildNodes().item(0)
                                .getNodeValue());
            		}
            	}
            }
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}