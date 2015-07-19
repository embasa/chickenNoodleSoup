/**
 * Created by bruno on 7/12/15.
 */

public class QuickSort extends MySort {
    final int CUTOFF = 10;// a value gives improved performances.

    public <AnyType extends Comparable<? super AnyType>> void sort(AnyType[] a) {
        moves = 0;
        comparisons = 0;
        time = System.currentTimeMillis();
        quickSort(a, 0, a.length - 1);
        time = System.currentTimeMillis() - time;
    }

    /**
     * returns the median but also sorts the references so to have sentinel
     * values for both i and j.
     *
     * @param a
     * @param left
     * @param right
     * @param <AnyType>
     * @return
     */
    private <AnyType extends Comparable<? super AnyType>> AnyType median3(AnyType[] a, int left, int right) {
        int center = (left + right) / 2;

        if (a[center].compareTo(a[left]) < 0) {
            swapReferences(a, left, center);
        }
        if (a[right].compareTo(a[left]) < 0) {
            swapReferences(a, left, right);
        }
        if (a[right].compareTo(a[center]) < 0) {
            swapReferences(a, center, right);
        }

        swapReferences(a, center, right - 1);

        return a[right - 1];
    }

    /**
     * Recursive call that implements median of 3,
     * future addition of insertion sory
     *
     * @param a         the array of Comparable elements
     * @param left
     * @param right
     * @param <AnyType> any arbitrary datatype that implements Comparable
     */
    private <AnyType extends Comparable<? super AnyType>> void quickSort(AnyType[] a, int left, int right) {
        //if ( left + CUTOFF <= right ) {
        if (left + CUTOFF <= right) {

            AnyType pivot = median3(a, left, right);

            int i = left, j = right - 1;
            for ( ; ; ) {
;
//            while ( a[++i].compareTo( pivot ) < 0 );
//            while ( a[--j].compareTo( pivot ) > 0 );
                while (compare(a[++i], pivot) < 0) ;
                while (compare(a[--j], pivot) > 0) ;
                if (i < j) {
                    swapReferences(a, i, j);
                } else {
                    break;
                }
            }

            swapReferences(a, i, right - 1);

            quickSort(a, left, i - 1);// smaller elements
            quickSort(a, i + 1, right);// bigger elements

        } else {
           insertionSort( a, left, right );
         }
    }


    private <AnyType extends Comparable<? super AnyType>> void swapReferences(AnyType[] a, int i, int j) {
        moves += 2;
        AnyType temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * insertion sort for all subarrays of size 10 or less,
     * according to textbook this reduces number of comparisons
     * by about 14%.
     *
     * @param a
     * @param left      furthest left index
     * @param right     the last usable index on the far right
     * @param <AnyType>
     */
    public <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a, int left, int right) {
        int j;
        for (int p = left + 1; p <= right; p++) {
            AnyType tmp = a[p];
//            for ( j = p; j > left && ( ++ comparisons > 0 ) && tmp.compareTo( a[ j - 1 ] ) < 0; j-- ) {
            for (j = p; j > left && compare(tmp, a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
                moves++;
            }
            if (a[j] != tmp) {
                a[j] = tmp;
                moves++;
            }
        }
    }
}
