/**
 * Perform matches of a pattern within a text
 * using the Knuth-Morris-Pratt algorithm.
 * 
 * @author Ronald Mak
 */
public class KnuthMorrisPratt3 
{
    private String pattern;
    private String text;
    private int next[];
    private int compares;
    private int index;
    
    /**
     * Constructor.
     * @param pattern the pattern to search for.
     * @param text the text where the pattern may be found.
     */
    public KnuthMorrisPratt3(String pattern, String text)
    {
        this.pattern = pattern;
        this.text = text;
        this.next = computeNext();
        this.compares = 0;
        this.index = match();
    }
    
    public int[] getNext()   { return next; }
    public int getCompares() { return compares; }
    public int getIndex()    { return index; }
    
    /**
     * Find the first match of the pattern within the text.
     * @return the index of the first match, or -1 if no match.
     */
    public int match() 
    {
        if ((text == null) || (text.length() == 0)) return -1;

        int i = 0;
        int j = 0;
        
        System.out.println("\n i j match?");
        
        while (i < text.length()) {
            compares++;
            
            System.out.printf("                                   %s\n", text);
            System.out.print ("                                   ");
            for (int k = 0; k < i-j; k++) System.out.print(" ");
            System.out.println(pattern);
            
            System.out.printf("%2d %d", i, j);
            
            // Matching so far ...
            if (pattern.charAt(j) == text.charAt(i)) {
                System.out.print("    Y   ");
                
                // Found a complete match.
                if (j == pattern.length()-1) {
                    return i - j;
                }
                
                // Otherwise, continue comparing characters.
                else {
                    i++;
                    j++;
                }
            }
            
            // Mismatch: set j from the next[] array.
            else if (j > 0) {
                System.out.printf("    N   reset j = next[%d] = %d", j-1, next[j-1]);
                j = next[j-1];
            }
            
            // Or shift the pattern over one to the right.
            else {
                System.out.print("    N   shift pattern 1 right");
                i++;
            }
            
            System.out.println();
        }
        
        // No match.
        return -1;
    }

    /**
     * Computes the next[] array using a bootstrapping process, 
     * where the pattern is matched against itself.
     * @return the next[] array.
     */
    private int[] computeNext() 
    {
        System.out.println("\ni j next[]");
        
        int next[] = new int[pattern.length()];
        int i = 1;
        int j = 0;
        next[0] = 0;
        
        // Loop over the pattern characters.
        while (i < pattern.length()) {
            
            System.out.print("\n    ");
            for (int k = 0; k <= i; k++) System.out.printf("%d", k);
            System.out.printf("\n    %s\n", pattern.substring(0, i+1));
            System.out.printf("%d %d ", i, j);
            
            // Matching characters: Set next[i] to 
            // the number of matching characters + 1.
            if (pattern.charAt(i) == pattern.charAt(j)) {
                next[i] = j+1;
                for (int k = 0; k <= i; k++) System.out.printf("%d", next[k]);
                i++;
                j++;
            }
            
            // Set j back.
            else if (j > 0) {
                j = next[j-1];
                for (int k = 0; k <= i; k++) System.out.printf("%d", next[k]);
            }
            
            // Set next[i] to 0.
            else {
                next[i] = 0;
                for (int k = 0; k <= i; k++) System.out.printf("%d", next[k]);
                i++;
            }
            
            System.out.println();
        }
        
        return next;
    }
}
