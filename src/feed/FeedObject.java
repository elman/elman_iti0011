package feed;

/**An object that contains data from iti0011 feed.
 * @author barmani
 *
 */
public class FeedObject {
	/**.
	 * item title stored in this object
	 */
	String title = "",  
	content = "",  
	time = "";
	
	/**
	 * @param tle title of that feed item
	 * @param tme time in that feed item
	 * @param cont content of that feed item
	 */
	public FeedObject(String tle, String tme, String cont) {
		this.title = tle;
		this.content = cont;
		this.time = tme;
	}
	/**
	 * @return retutns item time stored in this object
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @return retutns item title stored in this object
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @return retutns item content stored in this object
	 */
	public String getContent() {
		return content;
	}
}
