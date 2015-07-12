import java.text.NumberFormat;
import java.util.*;

/**
 * Created by bruno on 7/9/15.
 *  Merge sort from cs146 summer 2015 lecture.
 */
public class MainAsgnFour {

    public static void main( String[] args ) {
        runComparisons();
 //       Integer[] array = generateArray( 100 );
  //      timeAndPrint( "Merge sort", array.clone(), new MergeSort() );
    }

    /**
     * Method that runs all tests required in this assignment
     * from 100 elements to 100,000 for all four tests
     */
    public static void runComparisons(){
        System.out.printf( "%50s\n", "== Random Unique Integers ==" );
        for ( int n = 100; n <= 100000; n *= 10 ) {
            System.out.printf( "\nN = %6s\n", NumberFormat.getNumberInstance( Locale.US ).format( n ) );
            System.out.printf( "\n%30s%15s%15s%15s\n", "ALGORITHM", "MOVES", "COMPARISONS", "MILLISECONDS" );
            Integer[] array = generateArray( n );
            timeAndPrint( "Merge sort", array.clone(), new MergeSort() );
            timeAndPrint( "Insertion sort", array.clone(), new InsertionSort() );
            timeAndPrint( "Shellsort suboptimal", array.clone(), new ShellSort() );
            timeAndPrint( "Shellsort Knuth", array.clone(), new ShellSortKnuth() );
        }
        System.out.println();
        System.out.printf( "%50s\n", "== Sorted Unique Integers: Ascending ==" );
        for ( int n = 100; n <= 100000; n *= 10 ) {
            System.out.printf( "N = %6s\n", NumberFormat.getNumberInstance( Locale.US ).format( n ) );
            System.out.printf( "\n%30s%15s%15s%15s\n", "ALGORITHM", "MOVES", "COMPARISONS", "MILLISECONDS" );
            Integer[] array = generateArray( n );
            Collections.sort( Arrays.asList( array ) );
            timeAndPrint( "Merge sort", array.clone(), new MergeSort() );
            timeAndPrint( "Insertion sort", array.clone(), new InsertionSort() );
            timeAndPrint( "Shellsort suboptimal", array.clone(), new ShellSort() );
            timeAndPrint( "Shellsort Knuth", array.clone(), new ShellSortKnuth() );
        }
        System.out.println();

        System.out.printf( "%50s\n", "== Sorted Unique Integers: Descending ==" );
        for ( int n = 100; n <= 100000; n *= 10 ) {
            System.out.printf( "N = %6s\n", NumberFormat.getNumberInstance( Locale.US ).format( n ) );
            System.out.printf( "\n%30s%15s%15s%15s\n", "ALGORITHM", "MOVES", "COMPARISONS", "MILLISECONDS" );
            Integer[] array = generateArray( n );
            Collections.sort( Arrays.asList( array ), Collections.reverseOrder() );
            timeAndPrint( "Merge sort", array.clone(), new MergeSort() );
            timeAndPrint( "Insertion sort", array.clone(), new InsertionSort() );
            timeAndPrint( "Shellsort suboptimal", array.clone(), new ShellSort() );
            timeAndPrint( "Shellsort Knuth", array.clone(), new ShellSortKnuth() );
        }
        System.out.println();

        System.out.printf( "%60s\n", "== All Zeroes ==" );
        for ( int n = 100; n <= 100000; n *= 10 ) {
            System.out.printf( "N = %6s\n", NumberFormat.getNumberInstance( Locale.US ).format( n ) );
            System.out.printf( "\n%30s%15s%15s%15s\n", "ALGORITHM", "MOVES", "COMPARISONS", "MILLISECONDS" );
            Integer[] array = new Integer[ n ];// generateArray(n);
            for ( int i = 0; i < array.length; i++ ) {
                array[ i ] = 0 ;// an array of Integers are all initialized to null..
            }

            timeAndPrint( "Merge sort", array.clone(), new MergeSort() );
            timeAndPrint( "Insertion sort", array.clone(), new InsertionSort() );
            timeAndPrint( "Shellsort suboptimal", array.clone(), new ShellSort() );
            timeAndPrint( "Shellsort Knuth", array.clone(), new ShellSortKnuth() );
        }
        System.out.println();

    }

    /**
     * This method times how long it takes to sort, and prints the result
     * formatted as preferred
     * @param name name of sort
     * @param array an array of elements to sort
     * @param mySort a object of MySort type to use sort
     */
    public static void timeAndPrint( String name, Integer[] array, MySort mySort ) {
        mySort.sort( array );
        System.out.printf( "%30s%15s%15s%15s\n", name,
                NumberFormat.getNumberInstance( Locale.US ).format( mySort.getMoves() ),
                NumberFormat.getNumberInstance( Locale.US ).format( mySort.getComparisons() ),
                NumberFormat.getNumberInstance( Locale.US ).format( mySort.getTime() ));
    }

    /**
     * Generate an array with n ammount of unique integers
     * @param n
     * @return array
     */
    public static Integer[] generateArray( int n ) {
        HashMap< Integer, Integer > hashMap = new HashMap<>();// use to see if number is contained
        Random rand = new Random( System.currentTimeMillis() );
        Integer[] array = new Integer[ n ];
        int i = 0;
        while ( i < n ) {
            int value = rand.nextInt();
            if ( ! hashMap.containsKey( value ) ) {
                array[ i++ ] = value;
                hashMap.put( value, value );
            }
        }
        return array;
    }

}

