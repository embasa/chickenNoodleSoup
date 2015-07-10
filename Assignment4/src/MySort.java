/**
 * Created by bruno on 7/9/15.
 * This class is so I can pass a MySort to
 * my timeAndPrint functioning without carrying
 * what I am passing to it
 */
public abstract class MySort {
    long comparisons = 0;
    long moves = 0;

    /**
     * This abstract method is to be implemented by all of my Sort
     * routines as to allow me to use Main to test all of them
     * arbitrarily.
     * @param a
     * @param <AnyType>
     * @return
     */
    protected abstract < AnyType extends Comparable< ? super AnyType > > AnyType[] sort( AnyType[] a );

    public long getComparisons() {
        return comparisons;
    }

    public long getMoves() {
        return moves;
    }
}

