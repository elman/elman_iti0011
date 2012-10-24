package poomine;
/**.
 * Generates hangman
 * @author barmani
 * 
 */
public class Hangman {
	private int i = 0;
	private String tyhi = "\n";
	private String a = "  | \n";
	private String b = "  o \n";
	private String cc = "   -\n";
	private String c = " - -\n";
	private String d = "  | \n";
	private String e = " / \\\n";
	private String ee = "   \\\n";
	private String m = tyhi + tyhi + tyhi + tyhi + ee;
	/**.
	 * 
	 * @return Returns hangman
	 */
	public String getHangman() {
		return m;		
	}	
	/**
	 * @return returns whether or not the man is hanged
	 */
	public boolean getHanged() {
		if(i < 6) {
			return false;
		} else {
			return true;
		}
	}
	/** Sets the state of hangman.
	 * @param bo desides whether the hangman increases on decreases 
	 */
	public void setHangman(boolean bo) {
		if (bo) {
			this.i++;
		} else  {
			this.i--;
		}
		switch (this.i) {
		case 0:
			this.m = tyhi + tyhi + tyhi + tyhi + ee;
			break;
		case 1:
			this.m = tyhi + tyhi + tyhi + tyhi + e;
			break;
		case 2:
			this.m = tyhi + tyhi + tyhi + d + e;
			break;
		case 3:
			this.m = tyhi + tyhi + cc + d + e;
			break;
		case 4:
			this.m = tyhi + tyhi + c + d + e;
			break;
		case 5:
			this.m = tyhi + b + c + d + e;
			break;
		case 6:
			this.m = a + b + c + d + e;
			break;
		default: this.m = "Tere tali!";
		}
	}
 }