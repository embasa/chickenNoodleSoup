import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by computerito on 6/12/15.
 */
public class Main {

    private static ArrayList<Integer> arrayList;
    private static LinkedList<Integer> linkedList;
    private static IndexedList indexedList;
    private static Random rand;
    public static void initLists(int n){
        rand = new Random(System.currentTimeMillis());
        indexedList = new IndexedList();
        arrayList = new ArrayList<>(n);
        for(int i = 0; i < n; i++){
            indexedList.add(0);
            arrayList.add(0);
        }
        linkedList = new LinkedList<>(arrayList);
    }

    private static long timeAddRandomly(List<Integer> lst){
        long start = System.currentTimeMillis();
        int n = lst.size();
        for(int i = 0; i< n; i++ ){
            lst.add(rand.nextInt(lst.size()),rand.nextInt());
        }

        return System.currentTimeMillis() - start;
    }

    private static long timeElementAccess(List<Integer> lst){
        long start = System.currentTimeMillis();

        for(int i = 0; i< lst.size(); i++ ){
            Integer elmt = lst.get(i);
        }

        return System.currentTimeMillis() - start;
    }

    private static long timeElementRemoval(List<Integer> lst){
        long start = System.currentTimeMillis();

        for(int i = 0; i< lst.size(); i++ ){
            Integer elmt = lst.remove(rand.nextInt(lst.size()));
        }

        return System.currentTimeMillis() - start;
    }

    private static long timeSetElement(List<Integer> lst){
        long start = System.currentTimeMillis();

        for(int i = 0; i< lst.size(); i++ ){
            Integer elmt = lst.set(rand.nextInt(lst.size()), rand.nextInt());
        }

        return System.currentTimeMillis() - start;
    }

    public static void main(String[] args){
        runTests();
    }

    public static void runTests(){
        int N = 100000;
        System.out.print("Test get():\n");
        System.out.printf("%8s%15s%15s%15s\n", "n", "ArrayList", "LinkedList", "IndexedList");
        for( int n = 100; n <= N ; n*=10 ){
            initLists(n);

            long timeArray = timeElementAccess(arrayList);
            long timeList = timeElementAccess(linkedList);
            long timeIndexed = timeElementAccess(indexedList);

            System.out.printf("%8d%12d ms%12d ms%12d ms\n", n,timeArray,timeList,timeIndexed);
        }

        System.out.print("\nTest set():\n");
        System.out.printf("%8s%15s%15s%15s\n","n","ArrayList","LinkedList","IndexedList");
        for( int n = 100; n <= N ; n*=10 ){
            initLists(n);

            long timeArray = timeSetElement(arrayList);
            long timeList = timeSetElement(linkedList);
            long timeIndexed = timeSetElement(indexedList);

            System.out.printf("%8d%12d ms%12d ms%12d ms\n", n,timeArray,timeList,timeIndexed);
        }

        System.out.print("\nTest remove():\n");
        System.out.printf("%8s%15s%15s%15s\n","n","ArrayList","LinkedList","IndexedList");
        for( int n = 100; n <= N ; n*=10 ){
            initLists(n);

            long timeArray = timeElementRemoval(arrayList);
            long timeList = timeElementRemoval(linkedList);
            long timeIndexed = timeElementRemoval(indexedList);

            System.out.printf("%8d%12d ms%12d ms%12d ms\n", n,timeArray,timeList,timeIndexed);
        }

        System.out.print("\nTest add():\n");
        System.out.printf("%8s%15s%15s%15s\n","n","ArrayList","LinkedList","IndexedList");
        for( int n = 100; n <= N ; n*=10 ){
            initLists(n/2);//fill up to half, then use function to add the second half

            long timeArray = timeAddRandomly(arrayList);
            long timeList = timeAddRandomly(linkedList);
            long timeIndexed = timeAddRandomly(indexedList);

            System.out.printf("%8d%12d ms%12d ms%12d ms\n", n,timeArray,timeList,timeIndexed);
        }

    }
}
