public class FindMatch2
{
    public static void main(String[] args) 
    {
        String text = "abacadabrabracabracadabrabrabracad";               
        String patterns[] = {
                "abracadabra", 
                "rab", 
                "bcara", 
                "rabrabracad", 
                "abacad",
        };
        
        for (String pattern : patterns) {
            System.out.println();
            BruteSearch brute = new BruteSearch(pattern, text);
            int index = brute.getIndex();
            int compares = brute.getCompares();
            
            System.out.printf("Text    = %s\n", text);
            System.out.print("Pattern = ");
            int offset = index >= 0 ? index : text.length();
            for (int i = offset; i > 0; i--) System.out.print(" ");
            System.out.println(pattern);
            System.out.println();
            
            System.out.println("Brute search");
            System.out.printf("    Index = %d, compares = %d\n", index, compares);
            
            KnuthMorrisPratt kmp = new KnuthMorrisPratt(pattern, text);
            int next[] = kmp.getNext();
            index = kmp.getIndex();
            compares = kmp.getCompares();
            
            System.out.println();
            System.out.println("Knuth Morris Pratt");
            System.out.print("    Next = [");
            for (int i = 0; i < next.length; i++) System.out.printf("%3d", next[i]);
            System.out.println(" ]");
            System.out.printf("    Index = %d, compares = %d\n", index, compares);
        }
    }
}
