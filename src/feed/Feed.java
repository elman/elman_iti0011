package feed;

import java.util.ArrayList;
import java.util.List;

/**
 * @author barmani
 * Gets ITI0011 Google Group announcements and enables to translate them 
 * into specified language. 
 */
public class Feed {

	/**
	 * @param args - arguments from command line
	 */
	public static void main(String[] args) {
		ParseArguments.parse(args);
		// checkstyle does not allow to use capital letters 
		// as first letter in varable name

		final int dAFAULTINTERVAL = 333;
		
		// reads command line option -lang argument 
		// that can default to 'en'. 
		String lang = ParseArguments.lang.value;
		if (lang == null) {
			lang = "en";
		}

		// reads command line option -interval argument 
		// that can default to 1000. 
		int interval = ParseArguments.interval.value;
		if (interval == 0) {
			interval = dAFAULTINTERVAL;
		}
		
		// reads command line option -order argument 
		// that can default to 'down'. 
		String order = ParseArguments.order.value;
		if (order == null || order != "up" || order != "down") {
			order = "down";
		}
		
		Connection conn = new Connection();
		ConvertDates cd = new ConvertDates();
		WriteXMLFile wXml =  new WriteXMLFile();
		List<FeedObject> feedList = new ArrayList<FeedObject>();
		feedList = conn.getFeed();
		wXml.write(feedList, lang);
		
		//Prints out the entire result
		Printer.print(cd.dateConvert(lang, interval, order), lang, interval);
	}
}
