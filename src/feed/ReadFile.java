package feed;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


/** Reads feed.xml file and puts its contents into list of objects. 
 * @author barmani
 *
 */
public class ReadFile {

	/**.
	 *  creates a list of ReadObject type
	 *  for storing translated feeditems written in xml file
	 */
	private List<ReadObject> rList = new ArrayList<ReadObject>();
	/**.
	 * Creates a list to store all languages in xml file
	 */
	private List<String> languages = new ArrayList<String>();
	/** Reads the feed.xml file.
	 * @return returns String containing all cnontents of feed.xml file
	 */
	
	private String read() {
		BufferedReader br = null;
		String sCurrentLine, total = null;
		try {
			br = new BufferedReader(new FileReader("feed.xml"));
			while ((sCurrentLine = br.readLine()) != null) {
				total = total + sCurrentLine;
			}
		} catch (FileNotFoundException e) {
			System.err.println("Viga andmete lugemisel failist");
		} catch (IOException e) {
			System.err.println("Viga andmete lugemisel failist");
		}
		return total;
	}
	
	
	/** Puts data from feed.xml file to list of ReadObject Type.
	 * @return returns list containing feeditems written in xml file
	 */
	public List<ReadObject> createObject() {
		String total = read();
		Document doc = Jsoup.parse(total);
		for (Element e : doc.getElementsByAttribute("language")) {
			languages.add(e.attr("language"));
		}

		for (int i = 0; i < languages.size(); i++) {
			for (Element e : doc.select(
					"translation[language=" + languages.get(i) + "]")
					.select("item")) {
				ReadObject ro = new ReadObject(e.select("title").text()
						, e.select("time").text()
						, e.select("content").text(), languages.get(i));
				rList.add(ro);
			}
		}
		return rList;
	}
}