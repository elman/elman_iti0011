package feed;

/** An object that contains data from xml file.
 * @author barmani
 *
 */
public class ReadObject extends FeedObject {
	/**
	 * 
	 */
	private String lang = null;
	
	/**
	 * @param title returns item title from this object
	 * @param time returns item time from this object
	 * @param content returns item content of this object
	 * @param language returns language of this object
	 */
	public ReadObject(
			String title, String time, String content, String language) {
		super(title, time, content);
		this.lang = language;
	}
	/**
	 * @return language returns language of this object
	 */
	public String getLanguage() {
		return lang;
	}
	/** Allows time to be changed.
	 * @param time is time in new format.
	 */
	public void setTime(String time) {
		this.time = time;
	}
}
