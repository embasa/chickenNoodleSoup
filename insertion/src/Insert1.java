import java.util.ArrayList;

public class Insert1 
{
    private static RandomGenerator generator;
    private static BinarySearch searcher;
    private static ArrayList<Integer> intList;
    
    private static void print(String label)
    {
        System.out.println(label);
        
        for (int i = 0; i < intList.size(); i++) {
            String marker = (i > 0) && (intList.get(i) < intList.get(i-1)) 
                          ? "*" : " ";
            System.out.printf("%2d:%2d%2s\n", i, intList.get(i), marker);
        }
    }
    
    public static void main(String[] args) 
    {
        int n = 10;     // size of list
        int count = 10; // count of insertions
        
        searcher = new BinarySearch();
        generator = new RandomGenerator(10*n);
        intList = generator.generateSortedArray(n);
            
        print("Before insertions");
        
        for (int i = 1; i <= count; i++) {
            int value = generator.generateInt();
            int index = searcher.binarySearch(intList, value);
            
            if (index <= 0) {
                index = -index;
                String marker = value > intList.get(index) ? "*" :" ";
                
                System.out.printf("Test insert: %2d:%2d%3s\n", 
                                  index, value, marker);
            }
        }
    }
}
