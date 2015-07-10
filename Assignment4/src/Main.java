import java.text.NumberFormat;
import java.util.*;

/**
 * Created by bruno on 7/9/15.
 */
public class Main {

    public static void main(String[] args) {
/**
        Integer[] array = {5,4,3,2,1};
        System.out.printf("\n%30s%15s%15s%15s\n", "ALGORITHM", "MOVES", "COMPARISONS", "MILLISECONDS");
        timeAndPrint("Insertion sort", array.clone(), new InsertionSort());
        timeAndPrint("Shellsort suboptimal", array.clone(), new ShellSort());
        timeAndPrint("Shellsort Knuth", array.clone(), new ShellSortKnuth());
 **/
        System.out.printf("%50s\n", "== Random Unique Integers ==");
        for(int n =100;n<=100000 ;n*=10) {
            System.out.printf("\nN = %6s\n",NumberFormat.getNumberInstance(Locale.US).format(n));
            System.out.printf("\n%30s%15s%15s%15s\n", "ALGORITHM", "MOVES", "COMPARISONS", "MILLISECONDS");
            Integer[] array = generateArray(n);
            timeAndPrint("Insertion sort", array.clone(), new InsertionSort());
            timeAndPrint("Shellsort suboptimal", array.clone(), new ShellSort());
            timeAndPrint("Shellsort Knuth", array.clone(), new ShellSortKnuth());
        }
        System.out.println();
        System.out.printf("%50s\n","== Sorted Unique Integers: Ascending ==");
        for(int n =100;n<=100000 ;n*=10) {
            System.out.printf("N = %6s\n", NumberFormat.getNumberInstance(Locale.US).format(n));
            System.out.printf("\n%30s%15s%15s%15s\n", "ALGORITHM", "MOVES", "COMPARISONS", "MILLISECONDS");
            Integer[] array = generateArray(n);
            Collections.sort(Arrays.asList(array));
            timeAndPrint("Insertion sort", array.clone(), new InsertionSort());
            timeAndPrint("Shellsort suboptimal", array.clone(), new ShellSort());
            timeAndPrint("Shellsort Knuth", array.clone(), new ShellSortKnuth());
        }
        System.out.println();

        System.out.printf("%50s\n","== Sorted Unique Integers: Descending ==");
        for(int n =100;n<=100000 ;n*=10) {
            System.out.printf("N = %6s\n", NumberFormat.getNumberInstance(Locale.US).format(n));
            System.out.printf("\n%30s%15s%15s%15s\n", "ALGORITHM", "MOVES", "COMPARISONS", "MILLISECONDS");
            Integer[] array = generateArray(n);
            Collections.sort(Arrays.asList(array),Collections.reverseOrder());
            timeAndPrint("Insertion sort", array.clone(), new InsertionSort());
            timeAndPrint("Shellsort suboptimal", array.clone(), new ShellSort());
            timeAndPrint("Shellsort Knuth", array.clone(), new ShellSortKnuth());
        }
        System.out.println();

        System.out.printf("%80s\n","== All Zeroes ==");
        for(int n =100;n<=100000 ;n*=10) {
            System.out.printf("N = %6s\n", NumberFormat.getNumberInstance(Locale.US).format(n));
            System.out.printf("\n%30s%15s%15s%15s\n", "ALGORITHM", "MOVES", "COMPARISONS", "MILLISECONDS");
            Integer[] array = new Integer[n];// generateArray(n);
            for(int i = 0;i<array.length;i++) {
                array[i] = 0;
            }
            //Collections.sort(Arrays.asList(array),Collections.reverseOrder());
            timeAndPrint("Insertion sort", array.clone(), new InsertionSort());
            timeAndPrint("Shellsort suboptimal", array.clone(), new ShellSort());
        timeAndPrint("Shellsort Knuth", array.clone(), new ShellSortKnuth());
        }
        System.out.println();
        /**
        **/
    }

    public static void timeAndPrint(String name,Integer[] array,MySort mySort){
        long start = System.currentTimeMillis();
        mySort.sort(array);
        long end = System.currentTimeMillis() - start;
        System.out.printf("%30s%15s%15s%15d\n", name,
                NumberFormat.getNumberInstance(Locale.US).format(mySort.getMoves()),
                NumberFormat.getNumberInstance(Locale.US).format( mySort.getComparisons() ), end);

    }

    public static Integer[] generateArray(int n) {
        HashMap< Integer, Integer > hashMap = new HashMap<>();// use to see if number is contained
        Random rand = new Random(System.currentTimeMillis());
        Integer[] array = new Integer[n];
        int i=0;
        while( i < n ){
            int value = rand.nextInt();
            if( !hashMap.containsKey( value ) ){
                array[i++] = value;
                hashMap.put( value,value );
            }
        }
        return array;
    }

}
