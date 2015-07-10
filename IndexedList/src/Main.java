import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by computerito on 6/12/15.
 */

public class Main {

    private static ArrayList< Integer > arrayList;
    private static LinkedList< Integer > linkedList;
    private static IndexedList indexedList;
    private static Random rand;
    private static int randArray[];

    public static void main( String[] args ) {
        //testIndexedList();
        runTests();
    }

    public static void testIndexedList() {
        System.out.print( "test with K== 3 to keep list short.. \n" );
        indexedList = new IndexedList( 3 );
        System.out.print( "add 4 elements to List.. \n" );
        indexedList.add( 1 );
        indexedList.add( 2 );
        indexedList.add( 3 );
        indexedList.add( 4 );
        indexedList.print();
        System.out.print( "add 6 in position 1..\n" );
        indexedList.add( 1, 6 );
        indexedList.print();

        System.out.print( "change 2 to 7..\n" );
        indexedList.set( 2, 7 );
        indexedList.print();
        System.out.print( "remove last element.. \n" );
        indexedList.remove( indexedList.size() - 1 );
        indexedList.print();
        System.out.print( "remove first element.. \n" );
        indexedList.remove( 0 );
        indexedList.print();

        System.out.print( "clear list.. \n" );
        indexedList.clear();
        indexedList.print();
        System.out.print( "add 5 elements.. \n" );
        for ( int i = 0; i < 5; i++ ) {
            indexedList.add( i );
        }
        indexedList.print();
        System.out.print( "get 3rd element.. \n" );
        System.out.print( indexedList.get( 2 ) );
        System.out.print( "set all of them to 42.. \n" );
        for ( int i = 0; i < 5; i++ ) {
            indexedList.set( i, 42 );
        }
        indexedList.print();


    }

    public static void initLists( int n ) {
        rand = new Random( System.currentTimeMillis() );
        indexedList = new IndexedList();
        arrayList = new ArrayList<>( n );
        for ( int i = 0; i < n; i++ ) {
            indexedList.add( 0 );
            arrayList.add( 0 );
        }
        linkedList = new LinkedList<>( arrayList );
    }

    private static long timeAddRandomly( List< Integer > lst ) {
        long start = System.currentTimeMillis();
        int n = lst.size();
        for ( int i = 0; i < n; i++ ) {
            lst.add( randArray[ i ], rand.nextInt() );
        }

        return System.currentTimeMillis() - start;
    }

    private static long timeElementAccess( List< Integer > lst ) {
        long start = System.currentTimeMillis();

        for ( int i = 0; i < lst.size(); i++ ) {
            Integer elmt = lst.get( i );
        }

        return System.currentTimeMillis() - start;
    }

    private static long timeElementAdd( List< Integer > lst, int n ) {
        long start = System.currentTimeMillis();

        for ( int i = 0; i < n; i++ ) {
            lst.add( i );
        }

        return System.currentTimeMillis() - start;
    }

    private static long timeElementRemoval( List< Integer > lst, int n ) {
        long start = System.currentTimeMillis();

        for ( int i = 0; i < n; i++ ) {
            Integer elmt = lst.remove( randArray[ i ] );
        }

        return System.currentTimeMillis() - start;
    }

    private static long timeSetElement( List< Integer > lst ) {
        long start = System.currentTimeMillis();

        for ( int i = 0; i < lst.size(); i++ ) {
            Integer elmt = lst.set( i, rand.nextInt() );
        }

        return System.currentTimeMillis() - start;
    }


    public static void runTests() {
        int N = 1000000;

        System.out.print( "Test get(i):\n" );
        System.out.printf( "%8s%15s%15s%15s\n", "n", "ArrayList", "LinkedList", "IndexedList" );
        for ( int n = 100; n <= N; n *= 10 ) {
            initLists( n );

            long timeArray = timeElementAccess( arrayList );
            long timeList = timeElementAccess( linkedList );
            long timeIndexed = timeElementAccess( indexedList );

            System.out.printf( "%8d%12d ms%12d ms%12d ms\n", n, timeArray, timeList, timeIndexed );
        }

        System.out.print( "\nTest set(i,I):\n" );
        System.out.printf( "%8s%15s%15s%15s\n", "n", "ArrayList", "LinkedList", "IndexedList" );
        for ( int n = 100; n <= N; n *= 10 ) {
            initLists( n );

            long timeArray = timeSetElement( arrayList );
            long timeList = timeSetElement( linkedList );
            long timeIndexed = timeSetElement( indexedList );

            System.out.printf( "%8d%12d ms%12d ms%12d ms\n", n, timeArray, timeList, timeIndexed );
        }

        System.out.print( "\nTest add(I):\n" );//this test requires empty lists
        System.out.printf( "%8s%15s%15s%15s\n", "n", "ArrayList", "LinkedList", "IndexedList" );
        for ( int n = 100; n <= N; n *= 10 ) {
            //initLists(n/2);//fill up to half, then use function to add the second half
            arrayList = new ArrayList<>();
            long timeArray = timeElementAdd( arrayList, n );
            linkedList = new LinkedList<>();
            long timeList = timeElementAdd( linkedList, n );
            indexedList = new IndexedList();
            long timeIndexed = timeElementAdd( indexedList, n );

            System.out.printf( "%8d%12d ms%12d ms%12d ms\n", n, timeArray, timeList, timeIndexed );
        }

        System.out.print( "\nTest add(i,I):\n" );
        System.out.printf( "%8s%15s%15s%15s\n", "n", "ArrayList", "LinkedList", "IndexedList" );
        for ( int n = 100; n <= N; n *= 10 ) {
            initLists( n / 2 );//fill up to half, then use function to add the second half
            loadRandomArrayTwo( n / 2 );
            long timeArray = timeAddRandomly( arrayList );
            long timeList = timeAddRandomly( linkedList );
            long timeIndexed = timeAddRandomly( indexedList );

            System.out.printf( "%8d%12d ms%12d ms%12d ms\n", n, timeArray, timeList, timeIndexed );
        }

        System.out.print( "\nTest remove(i):\n" );
        System.out.printf( "%8s%15s%15s%15s\n", "n", "ArrayList", "LinkedList", "IndexedList" );
        for ( int n = 100; n <= N; n *= 10 ) {
            initLists( n );
            loadRandomArray( n );//call this function to make a random array of appropriate size
            long timeArray = timeElementRemoval( arrayList, n );
            long timeList = timeElementRemoval( linkedList, n );
            long timeIndexed = timeElementRemoval( indexedList, n );

            System.out.printf( "%8d%12d ms%12d ms%12d ms\n", n, timeArray, timeList, timeIndexed );
        }

        /** Int test for K values **/
        for ( int n = 100; n <= N; n *= 10 ) {
            System.out.print( "n value " + n + ":\n" );
            System.out.printf( "%-15s%15s%15s%15s%15s%15s\n", "k", "add(i)", "get(i)", "set(i,I)", "remove(i)", "add(i,I)" );

            for ( int k = 1; k <= N; k *= 10 ) {//do full test for each K value
                rand = new Random( System.currentTimeMillis() );
                indexedList = new IndexedList( k );
                long timeAdd = timeElementAdd( indexedList, n );//Time it takes to populate up to n
                long timeGet = timeElementAccess( indexedList );//time it takes to get() all those elements
                long timeSet = timeSetElement( indexedList );//time it takes to set all those elements
                loadRandomArray( n );//call this function to make a random array of appropriate size
                //System.out.print("size before Remove: " + indexedList.size() + "\n");
                long timeRemove = timeElementRemoval( indexedList, n );// Remove all of those elements
                //System.out.print("size after Remove: " + indexedList.size() + "\n");
                int half = n % 2 == 0 ? n / 2 : n / 2 + 1;//find appropriate half
                timeElementAdd( indexedList, half );// use it to fill up to half the value for the final test
                loadRandomArrayTwo( half );//
                //System.out.print("size after filling n/2: " + indexedList.size() + "\n");
                long timeAddRand = timeAddRandomly( indexedList );
                System.out.printf( "%-14d:%12d ms%12d ms%12d ms%12d ms%12d ms\n", k, timeAdd, timeGet, timeSet, timeRemove, timeAddRand );
            }
        }
    }

    // this method is used so same sequence of random numbers can be shared
    // among the 3 lists
    private static void loadRandomArray( int n ) {
        randArray = new int[ n ];
        int range = n;
        for ( int i = 0; i < n; i++ ) {
            randArray[ i ] = rand.nextInt( range-- );
        }
    }

    // this method is used for adding random elements
    private static void loadRandomArrayTwo( int n ) {
        randArray = new int[ n ];
        int range = n;
        for ( int i = 0; i < n; i++ ) {
            randArray[ i ] = rand.nextInt( range++ );
        }
    }
}
