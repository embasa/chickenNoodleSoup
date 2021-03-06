import java.util.ArrayList;

public class Insert2 
{
    private static RandomGenerator generator;
    private static BinarySearch searcher;
    private static ArrayList<Integer> intList;
    
    private static void print(String label, ArrayList<Integer> list)
    {
        System.out.println(label);
        
        for (int i = 0; i < list.size(); i++) {
            String marker = (i > 0) && (list.get(i) < list.get(i-1)) 
                          ? "*" : " ";
            System.out.printf("%2d:%2d%2s\n", i, list.get(i), marker);
        }
    }
    
    public static void main(String[] args) 
    {
        int n = 10;     // size of list
        int count = 5;  // count of insertions

        searcher = new BinarySearch();
        generator = new RandomGenerator(10*n);
        intList = generator.generateSortedArray(n);
            
        print("Before insertions:", intList);
        
        for (int i = 1; i <= count; i++) {
            int value = generator.generateInt();
            int index = searcher.binarySearch(intList, value);
            
            if (index <= 0) {
                index = -index;
                if (value > intList.get(index)) index++;
            }
            
            intList.add(index, value);
            System.out.printf("Inserted: %2d:%2d\n", index, value);
        }
        
        print("After insertions:", intList);
    }
}
