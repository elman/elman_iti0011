package poomine;
import java.util.Collections;
import java.util.List;

/**
 * @author barmani
 * contains 'undo' command logic and says if the word has been guessed
 */
public class UndoArvatud {
	/**.
	 * reuseble boolean
	 */
	private boolean b = false;
	/**
	 * @param hm is a hangman class that contains hangman with its current state
	 * @param sn is a S6na class that contains the word from command line
	 */
	public void undo(Hangman hm, S6na sn) { 
		 
		if (sn.gettList().size() > 0) {
			char ch = sn.gettList().get(sn.gettList().size() - 1);
			sn.setMessege("võtsin tagasi:     " + ch);
				if (sn.getValjundList().contains(
						sn.gettList().get(sn.gettList().size() - 1))) { 
					// kontrollib, kas tähtedelisti viimane liige esineb sõnas. 
					 Collections.replaceAll(
							 sn.getValjundList(), 
							 sn.gettList().get(sn.gettList().size() - 1), '_');
					 		// hides one guessed letter
					b = true;
				}
			if (!b) {
				hm.setHangman(false);  // turns hangman back one step
			}
			sn.gettList().remove(sn.gettList().size() - 1);
		} else {
			sn.setMessege("Pole midagi tagasi võtta");
		}
		b = false;
	}
	/**
	 * @return gives true, if word is successfully guessed or false if not. 
	 * @param vL is a list on characters the user sees
	 * returns true if the word has been guessed
	 */
	public boolean arvatud(List<Character> vL) {
		if (vL.contains('_')) {
			b = true;  // if even one character equals '_' 
		}			  //  then the word is not guessed 
		if (b) {
			b = false;
			return false;
		} else {
			return true;
		}
	}	
}