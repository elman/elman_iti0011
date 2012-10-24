package feed;

import argparser.ArgParser;
import argparser.IntHolder;
import argparser.StringHolder;

/** Parses the command line arguments.
 * @author barmani
 *
 */
public class ParseArguments {
	
    /**.
     *  holder object for option lang argument
     */
    static StringHolder lang = new StringHolder();
    /**.
     * holder object for option order argument
     */
    static StringHolder order = new StringHolder();
    /**.
     * holder object for option iterval argument
     */
    static IntHolder interval = new IntHolder();
    
	/**
	 * @param args contain options and arguments from command line
	 */
	public static void parse(String[] args) {
   
      // creates the parser and specifys the allowed options 
   
      ArgParser parser = new ArgParser("java argparser.SimpleExample");
      parser.addOption("-lang %s #Tõlke sihtkeel (nt 'en')", lang); 
      parser.addOption("-order %s #Kuvamise järjekord - 'up' ja 'down'", order);
      parser.addOption("-interval %i " 
      		+ "#Mitme päeva vanuseid kirjeid kuvatakse", interval);
   
      // matches the arguments 
   
      parser.matchAllArgs(args);
      
      // For testing
      
//      System.out.println ("Keel=" + lang.value);
//      System.out.println ("Järjekord=" + order.value);
//      System.out.println ("Intervall=" + interval.value);
   }
}
	