import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Access1
{
    private static ArrayList<Integer> testArray;
    private static LinkedList<Integer> testLinked;
    
    private static void initLists(int n)
    {
        testArray = new ArrayList<>(n);
        
        for (int i = 0; i < n; i++) {
            testArray.add(0);
        }
        
        testLinked = new LinkedList<>(testArray);
    }
    
    private static long timeElementAccess(List<Integer> lst)
    {
        long start = System.currentTimeMillis();
        
        for (int i = 0; i < lst.size(); i++) {
            Integer elmt = lst.get(i);
        }
        
        return System.currentTimeMillis() - start;
    }
    
    public static void main(String args[])
    {
        System.out.printf("%8s%15s%15s\n", "n", "ArrayList", "LinkedList");
        for (int n = 100; n <= 1000000; n*=10) {
            initLists(n);
            
            long timeArray = timeElementAccess(testArray);
            long timeList  = timeElementAccess(testLinked);
            
            System.out.printf("%8d%12d ms%12d ms\n", n, timeArray, timeList);
        }
    }
}
