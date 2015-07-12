/**
 * Created by bruno on 7/9/15.
 */
public class InsertionSort extends MySort {

    @Override
    public < AnyType extends Comparable< ? super AnyType > > void sort( AnyType[] a ) {
        time = System.currentTimeMillis();
        int j;
        for ( int p = 1; p < a.length; p++ ) {
            AnyType tmp = a[ p ];
            for ( j = p; j > 0 && ( ++ comparisons > 0 ) && tmp.compareTo( a[ j - 1 ] ) < 0; j-- ) {
                a[ j ] = a[ j - 1 ];
                moves++;
            }
            if ( a[ j ] != tmp ) {
                a[ j ] = tmp;
                moves++;
            }

        }
        time = System.currentTimeMillis() - time;
    }

}
