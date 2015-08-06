/**
 * Perform brute search matches of a pattern within a text.
 * 
 * @author Ronald Mak
 */
public class BruteSearch 
{
    private String pattern;
    private String text;
    private int compares;
    private int index;
    
    /**
     * Constructor.
     * @param pattern the pattern to search for.
     * @param text the text where the pattern may be found.
     */
    public BruteSearch(String pattern, String text)
    {
        this.pattern = pattern;
        this.text = text;
        this.compares = 0;
        this.index = match();
    }
    
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
        
        // Loop to look for a match.
        do {
            // Matching so far ...
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }
            
            // Not a match. Backtrack i.
            else {
                i = i - j + 1;
                j = 0;
            }
            
            compares++;
        } while ((j < pattern.length()) && (i < text.length()));
        
        // Return the index of the match, or -1 if no match.
        return j >= pattern.length() ? i - pattern.length() : -1;
    }
}
