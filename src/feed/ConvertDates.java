package feed;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/** Prints results out to console.
 * @author barmani
 *
 */
public class ConvertDates {
	/**.
	 * creates instance of readfile class
	 */
	ReadFile rf = new ReadFile();
	/**.
	 * creates a list of RreadObject type to store 
	 * values origenated from feed.xml file
	 */
	private List<ReadObject> readList = new ArrayList<ReadObject>();
	/**.
	 * List to store objects from readList in reverse order by date
	 */
	private List<ReadObject> reversedList = new ArrayList<ReadObject>(); 
	/** Converts dates to date format manipulates item lists 
	 * based on date values.
	 * @param language is 'lang' argument from command line
	 * @param interval is option 'interval' argument from command line
	 * @param order is option 'order' argument from command line
	 * @return returns a list of feed items in correct order
	 */
	public List<ReadObject> dateConvert(String language, 
			int interval, String order) {
		readList = rf.createObject();
		Locale.setDefault(Locale.US);
		SimpleDateFormat formatter = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss 'UT'");
		SimpleDateFormat formatterOut = new SimpleDateFormat(
				"dd.MM.yyyy     HH:mm");
		for (ReadObject ro : readList) {
			if (language.equals(ro.getLanguage())) {
				try {
					Date date = formatter.parse(ro.getTime());
				//	Date date = dateFormat(ro.getTime(), 1);
					//System.out.println(formatterOut.format(date));
					ro.setTime(formatterOut.format(date));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		// fills reversedList with ReadObjects in reverse order 
		// in relation to readlist
		
		for (ReadObject ro : readList) {
			if (order.equals("up")) {
				reversedList.add(0, ro);
			}
		}
		if (order.equals("down")) {
			return readList;

		} else {
			return reversedList;
		}
	}
}