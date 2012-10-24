package poomine;
/**.
 * Poomismäng
 * @author barmani
 */
public class Mangi {
	/**
	 * @param args  Arguments from commant line
	 */
	public static void main(String[] args) {
		Play pl = new Play();
		Validator val = new Validator();
		String sisend = "";
		int k = 0;
		try {
			while (args[k] != null) {
				sisend += args[k] + " ";
				k++;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.print("");  // just to put something in catch
		}
		if (!val.isWord(sisend)) {
			System.out.println("Sisendis oli viga, katkestan!");
			System.exit(0); 
		} else {
			pl.play(sisend);
		}
	}
}