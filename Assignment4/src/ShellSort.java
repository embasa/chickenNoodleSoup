/**
 * Created by bruno on 7/9/15.
 */
public class ShellSort extends MySort {
    @Override
    public < AnyType extends Comparable< ? super AnyType > > void sort( AnyType[] a ) {
        int j;
        time = System.currentTimeMillis();
        for ( int h = a.length / 2; h > 0; h /= 2 ) {
            for ( int i = h; i < a.length; i++ ) {
                AnyType tmp = a[ i ];

                for ( j = i; ( j >= h ) && ( ++ comparisons > 0 ) && ( tmp.compareTo( a[ j - h ] ) < 0 ); j -= h ) {
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

