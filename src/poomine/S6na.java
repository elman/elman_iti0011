package poomine;
import java.util.ArrayList;
import java.util.List;
/**
 * Operates with word and characters from command line.
 * Prints out hangman and guessed words and characters.
 * @author barmani
 *
 */
public class S6na {
	/**.
	 * multy purpouse, reuseble boolean in the followinmg code
	 */
	private boolean b = false; //

	/**.
	 * sList holds the given word to be guessed as a list of chars
	 */
	private List<Character> sList = new ArrayList<Character>();
	/**.
	 * tList holds player given letters as a list of chars
	 */
	private List<Character> tList = new ArrayList<Character>();
	/**.
	 * holds the word in printable form 
	 * (unguessed letters hidden) as a list of chars
	 */
	private List<Character> valjundList = new ArrayList<Character>();
	/**.
	 * contains a messege to be printed out tu player
	 */
	private String messege = "";
	
	/**
	 * @param m is messege if command 'undo' can not be executed 
	 * due to the fact that the list of characters is empty. 
	 */
	public void setMessege(String m) {
		this.messege = m;
	}
	/**
	 * @return gives list of letters playr has entered
	 */
	public List<Character> gettList() {
		return tList;
	}
	/**
	 * @return gives a list to be printed as word to be guessed
	 */
	public List<Character> getValjundList() {
		return valjundList;
	}
	/**
	 * @return gives positive or negative messege as feedback to given letter
	 *         and 'undo' command
	 */ 
	public String getMessege() {
		return messege;
	}
	/**
	 * @param s is a word about to be guessed, given from command line. 
	 */
	public void setS(String s) {  
		for (char c : s.toUpperCase().toCharArray()) {
			sList.add(c);     // Creates a list from given word
		}
		for (int i = 0; i < sList.size(); i++) {
			// Creates a list from given word with hidden characters
			if (sList.get(i) == '-') {
				valjundList.add('-');   
			} else if (sList.get(i) == ' ') {
				valjundList.add('-'); 
				} else {
					valjundList.add('_'); 
				  }
		}
	}
	/** Gets a character and puts it in the array.
	 * @param t is a character from command line given by player.
	 * @param hm is Hangman class
	 *  in which the hangman will be haged folllowingly:
	 */
	public void setT(char t, Hangman hm) {
		if (tList.contains(Character.toUpperCase(t))) {
			messege = t + " ... JUBA PAKUTUD!";
			b = true;
		} else {
			// adds given character to characters list
			tList.add(Character.toUpperCase(t));
			for (int i = 0; i < sList.size(); i++) {
				if (tList.get(tList.size() - 1) == sList.get(i)) {
					// unhides correctly guessed character
					valjundList.set(i, sList.get(i));  
					b = true;
					messege = t + " ... OLEMAS!";
				}
			}
		}
		if (!b && tList.size() > 0) {
			hm.setHangman(true);
			messege = t + " ... VALESTI!";
		}
		b = false;
	}
}