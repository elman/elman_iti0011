package poomine;
import java.util.Scanner;
/**
 * @author barmani
 */
public class Play {
	/**
	 * @param s is a word from command line parameter
	 */
	public void play(String s) {
		Hangman hm = new Hangman();
		S6na sn = new S6na();
		Scanner in = new Scanner(System.in);
		Validator val = new Validator();
		UndoArvatud ua = new UndoArvatud();
		String sisend = "";
		boolean u = true; // False if  "undo" has been used.
		boolean b = true; // False if input character is not letter
							 // or if player attempts to use 'undo' twice.
		sn.setS(s.trim());
		while (!hm.getHanged()) {
			if (b) {  // In case of some problem, hangman is not printed.
				print(sn, hm.getHangman()); 
			}
			if (ua.arvatud(sn.getValjundList())) {
				break;
			}
			System.out.println("Arva >");
			sisend = in.next();
			if (sisend.equalsIgnoreCase("exit")) {
				// Checks if player has enterd keyword "undo".
				break;
			}
			if (sisend.equalsIgnoreCase("undo") && !u) { 
				// Checks if player has enterd keyword "undo".
				b = false;
			System.out.println(
					"Käiku pole võimalik teist korda tagasi võtta");
			} else if (sisend.equalsIgnoreCase("undo") && u) {
				ua.undo(hm, sn);   
				u = false;
			} else  {
				if (val.isLetter(sisend.charAt(0))) { 
					// Checks if player has entered a valid letter
					sn.setT(sisend.charAt(0), hm);   
					b = true;
				} else {
					System.out.println(
						"Sisestatud märk polnud täht, " 
						+ "sisestage palun täht"); 
					System.out.println(
						"___________________________________________");
					b = false;
				}
			}
		}
		if (ua.arvatud(sn.getValjundList())) {
		System.out.println("    VÕIT!\nMäng läbi");
		}
		if (!hm.getHanged() && !ua.arvatud(sn.getValjundList())) {
			System.out.println("Mäng lõpetatud mängija soovil. "); 
		} 
		if (hm.getHanged()) {
			print(sn, hm.getHangman());
			System.out.println("\n\n   MÄNG LÄBI\nSa oled poodud! ");
		}
	}
	/** Does the routine printing.
	 * @param sn is a s6na class with letterlist and wordlist for printing
	 * @param m is hangman image as a string
	 */
	public void print(S6na sn, String m) {
		System.out.println("___________________________________________");
		System.out.println(m);
		System.out.print("Poomissõna:     ");
		for (int i = 0; i < sn.getValjundList().size(); i++) {
			System.out.print(sn.getValjundList().get(i) + " "); 
		} // prints out the word player is guessing
		System.out.print("\n\n");
		System.out.println(sn.getMessege() + "\n");
		System.out.print("Pakutud tähed:     ");
		for (int i = 0; i < sn.gettList().size(); i++) {
			System.out.print(sn.gettList().get(i) + " ");
		} // prints out letters player has given
		System.out.println("\n___________________________________________");	
	}
}