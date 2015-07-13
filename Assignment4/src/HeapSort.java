/**
 * Created by bruno on 7/12/15.
 * This is a binary heap so I can implement heapsort for MySorts
 * Took a raw type definition of this class by
 *
 * @author Mark Allen Weiss and made it Generic with the help
 *         of the the slides from cs146
 */
public class HeapSort extends MySort {

    /**
     * Internal method for sorting
     *
     * @param i
     * @return
     */
    private int leftChild( int i ) {
        return 2 * i + 1;
    }

    /**
     * This method is for finding the proper place for the last element in heap
     * that has no being placed at the root, breaking heap property.
     *
     * @param a         array to be sorted
     * @param i         the parent of the index being checked
     * @param n         size of the heap
     * @param <AnyType>
     */
    private < AnyType extends Comparable< ? super AnyType > > void percDown( AnyType[] a, int i, int n ) {
        int child;
        AnyType tmp;

        for ( tmp = a[ i ]; leftChild( i ) < n; i = child ) {
            child = leftChild( i );
            if ( child != n - 1 && compare( a[ child ], a[ child + 1 ] ) < 0 ) {
                child++;
            }
            if ( compare( tmp, a[ child ] ) < 0 ) {
                a[ i ] = a[ child ];
                moves++;
            } else {
                break;
            }
        }
        a[ i ] = tmp;
        moves++;
    }

    /**
     * HeapSort routine consists of making an array into a heap, and then sorting
     * Since building a heap is done in O(N) time, all that matters is the O(NlogN)
     * time that it takes to sort the binary heap.
     *
     * @param a
     * @param <AnyType>
     */
    public < AnyType extends Comparable< ? super AnyType > > void sort( AnyType[] a ) {
        moves = 0;
        comparisons = 0;
        time = System.currentTimeMillis();
        for ( int i = a.length / 2 - 1; i >= 0; i-- ) {
            percDown( a, i, a.length );
        }

        for ( int i = a.length - 1; i > 0; i-- ) {
            swapReferences( a, 0, i );
            percDown( a, 0, i );
        }
        time = System.currentTimeMillis() - time;
    }

    private < AnyType extends Comparable< ? super AnyType > > void swapReferences( AnyType[] a, int i, int j ) {
        moves += 2;
        AnyType temp = a[ i ];
        a[ i ] = a[ j ];
        a[ j ] = temp;
    }
}
