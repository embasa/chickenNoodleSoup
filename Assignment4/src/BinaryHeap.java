
/**
 * Created by bruno on 7/12/15.
 * This is a binary heap so I can implement heapsort for MySorts
 * Took a raw type definition of this class by
 * @author Mark Allen Weiss and made it Generic with the help
 * of the the slides from cs146
 */
public class BinaryHeap< AnyType extends  Comparable<? super AnyType>> {
    private  int currentSize;
    private AnyType[] array;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(){
        this( DEFAULT_CAPACITY );
    }
    public BinaryHeap(int capacity){
        currentSize = 0;
        array = (AnyType[])new Comparable[capacity+1];
    }

    public BinaryHeap(AnyType[] items){
        currentSize = items.length;
        array = (AnyType[])new Comparable[(currentSize+2)*11/10];// maybe we don't want to copy..
        int i = 1;
        for( AnyType item : items){
            array[i++] = item;
        }
        buildHeap();
    }

    public void insert(AnyType x){
        if( currentSize == array.length -1 ){
            enlargeArray( array.length*2+1 );
        }
        //perculate up
        int hole = ++currentSize;
        for(array[0] = x; x.compareTo( array[hole/2] )< 0; hole/=2){
            array[hole] = array[ hole/2];
        }
        array[hole] = x;
    }

    public AnyType findMin(){
        if(isEmpty()){
            return null;
        }
        return array[1];
    }

    public  AnyType deleteMin()throws Exception{
        if( isEmpty() ){
            throw new Exception();
        }

        AnyType minItem = findMin();
        array[ 1] = array[currentSize--];
        percolateDown( 1 );
        return minItem;
    }

    public boolean isEmpty(){
        return currentSize == 0;
    }

    public void makeEmpty(){
        currentSize = 0;
    }

    public boolean isFUll(){
        return currentSize == array.length-1;
    }

    private void percolateDown(int hole){
        int child;
        AnyType tmp = array[ hole];

        for( ; hole*2 <= currentSize; hole = child){
            child = hole *2;
            if( child != currentSize &&
                    array[child +1 ].compareTo( array[child] )< 0){
                child++;
            }
            if( array[child].compareTo( tmp )< 0){
                array[hole ] = array[child];
            }else {
                break;
            }
        }
        array[hole] = tmp;
    }

    private void buildHeap(){
        for( int i = currentSize/2;i>0;i--){
            percolateDown( i );
        }
    }

    public void enlargeArray( int newSize ){
        @SuppressWarnings( "unchecked" )// unchecked cast.. I get it.
        AnyType[] tempArray = (AnyType[]) new Comparable[newSize];
        System.arraycopy( array, 0,tempArray,0, array.length );
        array = tempArray;
    }
    public void print(){
        for(int i = 0;i<=currentSize;i++){
            System.out.print(array[i].toString() + " ");
        }
        System.out.println();
    }
         // Test program

    public static void main( String [ ] args )
    {
        int numItems = 10;
        BinaryHeap<Integer> h = new BinaryHeap<>( numItems );
        int i = 37;
        int count = 0;

        try
        {
            for( i = 37; i != 0; i = ( i + 37 ) % numItems ) {
                count++;
                h.insert( i );
            }

            System.out.println("count: " + count);
            h.print();
            for( i = 1; i < numItems; i++ ) {
                if ( ( h.deleteMin() != i ) ) {
                    System.out.println( "Oops! " + i );
                }
            }

            count =0;
            System.out.println("\nvalue is heree lol not really");
            for( i = 37; i != 0; i = ( i + 37 ) % numItems ) {
                h.insert( i );
            }

            System.out.println("count: " + count);
            h.print();
            h.insert(  0  );
            i = 9;
            h.insert(  i  );


            for( i = 1; i <= numItems; i++ ) {
                if ( ( h.deleteMin() ) != i ) {
                    System.out.println( "Oops! " + i + " " );
                }
            }
        }
        catch( Exception e )
        { System.out.println( "Overflow (expected)! " + i  ); }
    }

}
