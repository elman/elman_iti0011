package feed;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/** Prints the results to console.
 * @author barmani
 *
 */
public class Printer {
	
	/**.
	 * @param readList creates a list of RreadObject type to store 
	 * values origenated from feed.xml file.
	 * @param language is 'lang' argument from command line.
	 * @param interval is option 'interval' argument from command line.
	 */
	public static void print(List<ReadObject> readList, 
			String language, int interval) {
		Locale.setDefault(Locale.US);
		SimpleDateFormat formatterOut = new SimpleDateFormat(
		"dd.MM.yyyy     HH:mm");
		// odd name for constant variable because checkstyle crys 
		// on names beginning with capital letter
		final long dAYMILLIS = 3600 * 24 * 1000;
		for (ReadObject ro : readList) {
			Date date = null;
			try {
				date = formatterOut.parse(ro.getTime());
//				System.out.println("+++++++++++++++   " + date);
//				System.out.println("+++++++++++++++   " + ro.getTime());
			} catch (ParseException e) {
				System.out.print("");
			}
			if (language.equals(ro.getLanguage()) 
					&& date.getTime() > System.currentTimeMillis()
					- interval * dAYMILLIS) {
				System.out.println();
				System.out.println("-= " + ro.getTitle() + " =-");
				System.out.println(ro.getTime() + "\n");
				System.out.println("\t" + ro.getContent()
						// replaces markers with lineendings
						.replace("srthathathaehatjhasrtj", "\n\t") 
						.replace("athaethathathathathaethae", "\n\n\t"));
				// Prints out line tht ends the post item. 		
				System.out.println(
				"---------------------------------------------------");
			}
			
		}
	}
}
