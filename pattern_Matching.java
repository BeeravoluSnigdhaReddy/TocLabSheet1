package pattern_Match;

import java.util.Scanner;

/*
 * 
 * @author snehith challa
 * 
 * 
 */
public class pattern_Matching {

	// declaring variables
	String input;
	String pattern;
    static int k = 1;

    // COMPUTE-TRANSITION-FUNCTION according to the algorithm described in the book.
    public int CTS(String pattern, String input) {
    	
        char c;
        // m as described in book. m is the length of pattern.
        int m = input.length();
        String s = "";
        for (int q = 0; q < m; q++) {
            c = input.charAt(q);
            s = s + c;
            k = Math.min(k + 1, q + 1);
            while (true) {

                if (k == 0) {
                    break;
                }           
                if (s.charAt(q) != pattern.charAt(k - 1)) {
                    k=k-1;
                	break;
                }                
                break;            
            }
            if (k == pattern.length()) {
                return (q + 1 - pattern.length());
            }
        }

        return -1;
    }

    public static void main(String[] args) {
    	
        pattern_Matching p = new pattern_Matching();
    	
        Scanner s=new Scanner(System.in);
        
        System.out.println("Enter the pattern string");
        
        p.pattern=s.nextLine();
        
        System.out.println("Enter the input string");
        
        p.input=s.nextLine();
        
        int i = p.CTS(p.pattern, p.input);
        
        if (i > -1) {
        
        	System.out.println("Found Pattern Match with shift of " + i);
        } else {

        	System.out.println("Match not found");
        
        }
    }

}