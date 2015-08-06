public class FindMatch1
{
    private static String pattern = "ababaca";
    private static String text    = "bacbabababacaca";
    
    public static void main(String[] args) 
    {
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
    }
}
