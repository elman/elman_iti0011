package feed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/** Connectsd to the server and gets iti0011 feed. 
 * Static conn() method is also used by Translation class. 
 * @author barmani
 *
 */
public class Connection {

	/** List of objects containing feed items from iti0011 feed.
	 *  
	 */
	private List<FeedObject> fList = new ArrayList<FeedObject>();
	
	/** Creates a connection to server.
	 * @param url is server URL
	 * @return returns text content of received page.
	 */
	public static String conn(String url) {
		String s = "", 
		content = "";
		try {
			URL feed = new URL(url);
			URLConnection c = feed.openConnection();
			c.setRequestProperty("User-agent", "Mozilla/5.0");
			BufferedReader in = new BufferedReader(
					new InputStreamReader(c.getInputStream()));
			while ((s = in.readLine()) != null) {
				s = s.replace("&lt;br&gt;", "srthathathaehatjhasrtj ")
				.replace("&lt;p&gt;", "athaethathathathathaethae ");
				content += s;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return content;	
	}
	/** Creates list of objects contanining feed items.
	 * @return returns that list.
	 */
	public List<FeedObject> getFeed() {
		String url = "https://groups.google.com" 
				+ "/group/iti0011/feed/rss_v2_0_msgs.xml";
		Document doc = Jsoup.parse(conn(url));
		for (Element e : doc.select("item")) {
			FeedObject fo = new FeedObject(e.select("title").text()
					, e.select("pubDate").text()
					, e.select("description").text()
			.replace("&lt;br&gt;", "------------------"));
			fList.add(fo);
		}
		return fList;
	}
}
