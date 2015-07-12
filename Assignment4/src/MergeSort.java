/**
 * Created by bruno on 7/10/15.
 */
public class MergeSort extends MySort{

    @Override
    public < AnyType extends Comparable< ? super AnyType > > void sort( AnyType[] a ) {
        time = System.currentTimeMillis();
        AnyType[] tmpArray = (AnyType[])new Comparable[a.length];
        mergeSort( a, tmpArray, 0, a.length -1 );
        time = System.currentTimeMillis() - time;// == final - initial
    }

    public <  AnyType extends Comparable< ? super AnyType > > void mergeSort( AnyType[] a, AnyType[] tmpArray, int left, int right){
        if( left < right){
            int center = (left + right)/2;
            mergeSort( a, tmpArray, left, center );
            mergeSort( a, tmpArray, center+1, right );
            merge( a, tmpArray, left, center+1, right );
        }
    }

    /**
     * Merges two already sorted portions of array, a, by using a temporary array
     * copying values in order, and then returning them to original array.
     * @param a
     * @param tmpArray
     * @param leftPos
     * @param rightPos
     * @param rightEnd
     * @param <AnyType>
     */
    public <  AnyType extends Comparable< ? super AnyType > > void merge( AnyType[] a, AnyType[] tmpArray, int leftPos, int rightPos, int rightEnd){
        int leftEnd = rightPos -1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos +1;
        while(leftPos<= leftEnd && rightPos <= rightEnd){
            if((++comparisons>0) &&a[leftPos].compareTo( a[rightPos] ) <=0){
                tmpArray[tmpPos++] = a[leftPos++];
            }else {
                tmpArray[tmpPos++] = a[rightPos++];
            }
            moves++;
        }

        while( leftPos <= leftEnd ){
            tmpArray[tmpPos++] = a[leftPos++];
            moves++;
        }

        while ( rightPos <= rightEnd ){
            tmpArray[tmpPos++] = a[rightPos++];
            moves++;
        }

        for(int i=0; i< numElements; i++,  rightEnd--){
            a[rightEnd] = tmpArray[rightEnd];
        }
    }
}
