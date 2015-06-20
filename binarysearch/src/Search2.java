public class Search2
{
    private static final double LOG2 = Math.log(2);
    
    public static void main(String[] args) 
    {
        System.out.printf("%8s%8s%15s\n", "n", "count", "log2(n)");
        
        for (int n = 10; n <= 1000000; n*=10) {
            BinarySearch searcher = new BinarySearch();
            RandomGenerator generator = new RandomGenerator(100*n);
            Integer intArray[] = generator.generateSortedArray(n);
            
            int target = generator.generateInt();
            int count = searcher.binarySearch(intArray, target, false);
            
            double log = Math.log(n)/LOG2;
            System.out.printf("%8d%8d%15f\n", n, count, log);
        }
    }
}
