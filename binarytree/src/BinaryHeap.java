/**
 * Created by bruno on 7/12/15.
 * This is a binary heap so I can implement heapsort for MySorts
 * Took a raw type definition of this class by
 *
 * @author Mark Allen Weiss and made it Generic with the help
 *         of the the slides from cs146
 */
public class BinaryHeap< AnyType extends Comparable< ? super AnyType > > {
    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;
    private AnyType[] array;

    public BinaryHeap() {
        this( DEFAULT_CAPACITY );
    }

    @SuppressWarnings( "unchecked" )// Unchecked cast.. got it.
    public BinaryHeap( int capacity ) {
        currentSize = -1;
        array = ( AnyType[] ) new Comparable[ capacity + 1 ];
    }

    @SuppressWarnings( "unchecked" )// Unchecked cast.. got it.
    public BinaryHeap( AnyType[] items ) {
        currentSize = items.length;
        array = ( AnyType[] ) new Comparable[ ( currentSize + 2 ) * 11 / 10 ];// maybe we don't want to copy..
        int i = 1;
        for ( AnyType item : items ) {
            array[ i++ ] = item; }
        buildHeap();
    }

    public static void main( String[] args ) {
        int numItems = 10;
        BinaryHeap< Integer > h = new BinaryHeap<>( numItems );
        int i = 37;
        int count = 0;

        try {
            for ( i = 37; i != 0; i = ( i + 37 ) % numItems ) {
                count++;
                h.insert( i );
            }

            h.print();
            h.insert( 40 );
            for(int j = 0;j<count;j++){
                System.out.println( "max value: " + h.deleteMax() );
            }

        } catch ( Exception e ) {
            System.out.println( "Overflow (expected)! " + i );
        }
    }

    public void insert( AnyType x ) {
        if ( currentSize == array.length - 1 ) {
            enlargeArray( array.length * 2 + 1 );
        }
        //perculate up
        int hole = ++ currentSize;
        if(hole > 0){
            for ( ;hole != 0 && x.compareTo( array[ (hole-1) / 2 ] ) > 0; hole=(hole-1)/ 2 ) {
                array[ hole ] = array[ (hole-1) / 2 ];
            }
        }
        array[ hole ] = x;
    }

    public AnyType findMax() {
        if ( isEmpty() ) {
            return null;
        }
        return array[ 0 ];
    }

    public AnyType deleteMax() throws Exception {
        if ( isEmpty() ) {
            throw new Exception();
        }

        AnyType maxItem = findMax();
        array[ 0 ] = array[ currentSize-- ];
        percolateDown( 0 );
        return maxItem;
    }

    public boolean isEmpty() {
        return currentSize == -1;
    }

    public void makeEmpty() {
        currentSize = -1;
    }

    public boolean isFUll() {
        return currentSize == array.length - 1;
    }

    private void percolateDown( int hole ) {
        int child;
        AnyType tmp = array[ hole ];

        for (; hole * 2 <= currentSize; hole = child ) {
            child = hole * 2;
            if ( child != currentSize &&
                    array[ child + 1 ].compareTo( array[ child ] ) > 0 ) {
                child++;
            }
            if ( array[ child ].compareTo( tmp ) > 0 ) {
                array[ hole ] = array[ child ];
            } else {
                break;
            }
        }
        array[ hole ] = tmp;
    }

    private void buildHeap() {
        for ( int i = (currentSize-1) / 2; i >= 0; i-- ) {
            percolateDown( i );
        }
    }

    public void enlargeArray( int newSize ) {
        @SuppressWarnings( "unchecked" )// unchecked cast.. I get it.
                AnyType[] tempArray = ( AnyType[] ) new Comparable[ newSize ];
        System.arraycopy( array, 0, tempArray, 0, array.length );
        array = tempArray;
    }
    // Test program

    public void print() {
        for ( int i = 0; i <= currentSize; i++ ) {
            System.out.print( array[ i ].toString() + " " );
        }
        System.out.println(" ===== currentSize: " + currentSize);

    }
}
