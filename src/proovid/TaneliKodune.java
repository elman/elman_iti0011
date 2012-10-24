package proovid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TaneliKodune {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String a = null;
		int b = 0;
//        InputStreamReader converter = new InputStreamReader(System.in);
//        BufferedReader in = new BufferedReader(converter);
        Scanner s = new Scanner(System.in);
        System.out.println("Sisesta keskmine hinne: ");
        
        a = s.next(); 
        float f = Float.valueOf(a.trim()).floatValue();
        System.out.println("Sisesta eksami hinne: ");
        b = s.nextInt();
        if(f >= 4.5 && b == 5)
        	System.out.println("Õnnitlused !!!");
        else
		System.out.println("Sry, loll oled");
		
	}
}
