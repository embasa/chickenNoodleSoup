import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Remove2
{
    private static ArrayList<Integer> testArray;
    private static LinkedList<Integer> testLinked;
    
    private static void initLists(int n)
    {
        Random generator = new Random();
        testArray = new ArrayList<>(n);
        
        for (int i = 0; i < n; i++) {
            testArray.add(generator.nextInt());
        }
        
        testLinked = new LinkedList<>(testArray);
    }
    
    private static long timeElementRemove(List<Integer> lst)
    {
        long start = System.currentTimeMillis();
        Iterator<Integer> iter = lst.iterator();
        
        while (iter.hasNext()) {
            if (iter.next()%2 == 0) {
                iter.remove();
            }
        }
        
        return System.currentTimeMillis() - start;
    }
    
    public static void main(String args[])
    {
        System.out.printf("%8s%12s%12s\n", "n", "ArrayList", "LinkedList");
        for (int n = 100; n <= 1000000; n*=10) {
            initLists(n);
            
            long timeArray = timeElementRemove(testArray);
            long timeList  = timeElementRemove(testLinked);
            
            System.out.printf("%8d%12d%12d\n", n, timeArray, timeList);
        }
    }
}
