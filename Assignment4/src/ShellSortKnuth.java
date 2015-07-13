/**
 * Had to be its own class as to be able to @Override sort
 * from MySort
 */
public class ShellSortKnuth extends MySort {
    /**
     * This version of shell sort uses Knuth's series to cut down
     * on the number of comparisons done
     *
     * @param a
     * @param <AnyType>
     */
    @Override
    public < AnyType extends Comparable< ? super AnyType > > void sort( AnyType[] a ) {
        moves = 0;
        comparisons = 0;
        time = System.currentTimeMillis();
        int j;
        int k = 1;
        while ( ( int ) ( Math.pow( 3, k + 1 ) - 1 ) / 2 < a.length / 2 ) {
            k++;
        }

        for ( int h = ( int ) ( Math.pow( 3, k ) - 1 ) / 2; h > 0; h = ( int ) ( Math.pow( 3, -- k ) - 1 ) / 2 ) {
            for ( int i = h; i < a.length; i++ ) {
                AnyType tmp = a[ i ];
                for ( j = i; j >= h && compare( tmp, a[ j - h ] ) < 0; j -= h ) {
                    a[ j ] = a[ j - h ];
                    moves++;
                }
                if ( a[ j ] != tmp ) {
                    a[ j ] = tmp;
                    moves++;
                }
            }
        }
        time = System.currentTimeMillis() - time;
    }
}
