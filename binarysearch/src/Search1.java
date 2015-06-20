public class Search1
{
    public static void main(String[] args) 
    {
        int n = 10;
        BinarySearch searcher = new BinarySearch();
        RandomGenerator generator = new RandomGenerator(n);
        Integer intArray[] = generator.generateSortedArray(n);
            
        for (int i = 0; i < n; i++) {
            System.out.printf("%2d:%2d\n", i, intArray[i]);
        }
        
        for (int i = 0; i <= 10; i++) {
            int target = generator.generateInt();
            int index  = searcher.binarySearch(intArray, target, true);
            
            System.out.printf("Search: %2d:%2d\n", index, target);
        }
    }
}
