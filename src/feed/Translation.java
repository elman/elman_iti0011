package feed;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/** Translates given strings.
 * @author barmani
 *
 */
public class Translation {
	
	
	/**
	 * @param str is a string to be translated.
	 * @param language is option lang argument from command line
	 * @return returns translated string
	 */
	public String translate(String str, String language) {
		String content = "",
		urlStr = "";
		
		final int aDDR = 6; 
		
		// Cuts out <a href= ...  a>  part of string, if it exists
		// and replaces it with a marker 'gaergaergaertyharth'
			if (str.contains("href=")) {
				urlStr = str.substring(str.indexOf("<a")
						, str.indexOf("a>") + 2);
				str = str.replace(str.substring(str.indexOf("<a")
						, str.indexOf("a>") + 2), "gaergaergaertyharth");
			}
			Document doc = Jsoup.parse(Connection.conn(
					"http://translate.google.com" 
					+ "/m?hl=en&sl=et&tl=" + language + "&ie=UTF-8&prev=_m&q=" 
					+ str.replace(" ", "+")));
			
			Element t0 = doc.select("div.t0").first();
			content = t0.text();
			
			// if marker 'gaergaergaertyharth' exists in translated string,
			// then it will be replaced with URL 
			// from <a href= ...  a>  part cut out earlier 
			//  from string going into translation . 
		if (content.contains("gaergaergaertyharth")) {
			return content.replace(
					"aertyharth", urlStr.substring(
							urlStr.indexOf("href=") + aDDR
							, urlStr.indexOf(">") - 1)).replace("gaergaerg"
									, urlStr.substring(urlStr.indexOf(">") + 1
											, urlStr.indexOf("</a>")) + "  ");
		} else {
			return content;
		}
	}
}
