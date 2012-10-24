package poomine;
import java.util.ArrayList;
import java.util.List;
/**
 * @author barmani
 *
 */
public class Validator {
	/**.
	 * aList holds a given word as a list of chars
	 */
	private List<Character> aList = new ArrayList<Character>();
	/**.
	 * b is false if given word is not a valid word
	 */
	private boolean b = true;
	/**
	 * @param s is a word to be validated.
	 * @return is either a validation conformation or not. 
	 */
	public boolean isWord(String s) {
		for (char c : s.toUpperCase().toCharArray()) {
			aList.add(c);     // Creates a list from given word
		}
		for (int i = 0; i < aList.size(); i++) {	
			if (!Character.isLetter(aList.get(i))
					&& aList.get(i) != '-' && aList.get(i) != ' ') {
				b = false;
			}
		}
		if (b && s != "") {
			return true;
		} else {
			b = true;
			aList.clear();
			return false;
		}
	}
	/**
	 * @param c is a letter to be vaidated
	 * @return is either a validation conformation or not. 
	 */
	public boolean isLetter(char c) {
		return Character.isLetter(c);
	}	
}