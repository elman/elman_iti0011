package feed;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
 
/** Buffers translation results.
 * @author barmani
 *
 */
public class WriteXMLFile {
	/**.
	 * Creaes jsoup document for
	 */
	private Document doc =  Jsoup.parse("");
	/**.
	 * Creates a list of ReadObject type to store data from feed.xml file
	 */
	private List<ReadObject> readList = new ArrayList<ReadObject>();
	
	/** (over)Writes a feed.xml file.
	 * @param feedList is a list with data from fromiti0011 feed.
	 * @param language is option lang argument from command line.
	 */
	public void write(List<FeedObject> feedList, String language) {
		ReadFile rf = new ReadFile();
		Translation tr = new Translation();
		readList = rf.createObject();   
		doc.html(""); // clears html content from jsoup document
	
		Element juurikas = doc.appendElement("body");
		Element t6lge = juurikas.appendElement(
				"translation").attr("language", language);	
		for (FeedObject fo : feedList) {
			boolean itemExists = false;
			for (ReadObject ro : readList) {
				
		    	 // If item exists in feed.xml file  
				 // then it will be read from file 
				 // and not sent into translation
				
				if (language.equals(ro.getLanguage()) 
						&& fo.getTime().equals(ro.getTime())) {
					Element sub = t6lge.appendElement("item");
					sub.appendElement("title").text(ro.getTitle());
					sub.appendElement("time").text(ro.getTime());
					sub.appendElement("content").text(ro.getContent());
					itemExists = true;
				}
			}
			
			// If item does not exist in feed.xml file
			// then it will be sent to translation
			
			if (!itemExists) {
				// For testing
				
				System.out.println(
						"Tõlkesse läks item pealkirjaga: " + fo.getTitle());
				Element sub = t6lge.appendElement("item");
				sub.appendElement("title").text(
					tr.translate(fo.getTitle(), language));
				sub.appendElement("time").text(fo.getTime());
				sub.appendElement("content").text(
					tr.translate(fo.getContent(), language));
			}
		}
		String tempLanguage = "";
		
		// All items in other than current languages
		// will be rewritten in file unchanged. 
		
		for (ReadObject ro : readList) {
			if (!language.equals(ro.getLanguage()) 
					&& !tempLanguage.equals(ro.getLanguage())) {
				t6lge = juurikas.appendElement(
				"translation").attr("language", ro.getLanguage());
				tempLanguage = ro.getLanguage();
			}
			if (tempLanguage.equals(ro.getLanguage())) {
				Element sub = t6lge.appendElement("item");
				sub.appendElement("title").text(ro.getTitle());
				sub.appendElement("time").text(ro.getTime());
				sub.appendElement("content").text(ro.getContent());
			}	
		}
			BufferedWriter out;
			
			// Writes data into feed.xml file
			try {
				out = new BufferedWriter(new FileWriter("feed.xml"));
				out.write(doc.toString());
				out.close();
			} catch (IOException e1) {
				System.out.println("Error in writeing file");
			}
	}
	
}