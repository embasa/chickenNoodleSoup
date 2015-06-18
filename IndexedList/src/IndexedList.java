import javax.sql.rowset.spi.SyncFactory;
import java.util.*;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by computerito on 6/12/15.
 */
public class IndexedList implements List<Integer> {

    private ArrayList<Node> arrayList;
    private Node head;
    private Node tail;
    private int size = 0;
    private static final int K = 3;

    protected class Node{
        protected Node(){

        }
        Node next;
        Node prev;
        Integer integer;
        int index;
    }

    public IndexedList(){
        head = tail = null;
        arrayList = new ArrayList<>();
    }

    @Override
    public void add(int i, Integer integer) {
    }

    @Override
    public boolean add(Integer integer) {
        /** prepare Node **/
        System.out.print("attempting to add " + integer +" \n");
        Node temp = new Node();
        temp.index = size;//current size is the best index
        temp.integer = integer;// I don't know how to avoid warning
        /** insert node where appropriate **/

        if(size%K == 0){
            System.out.print("added to arrayList " + size + "\n");
            arrayList.add(temp);
        }
        size++;

        if(head == null){
            //if head empty then tail and head point to same reference
            tail = head = temp;
            System.out.print( "successfully added integer [" + temp.integer + "]\n");
            return true;
        }
        System.out.print(tail.integer+ "\n");

        //if head is not null then tail.prev is not null
        System.out.print( "successfully added integer [" + temp.integer + "]\n");
        tail.next = temp;
        temp.prev = tail;
        tail = temp;

        return true;
    }

    @Override
    public Integer get(int i){
        Node temp = this.getNode(i);
        if( temp == null ){
            return null;
        }
        return temp.integer;
    }

    private Node getNode(int i){
        if( (i<0) || (i>= size) ){
            return null;
        }
        int arrayListIndex = i/K;//position in arrayList closest to i
        int listCount = i%K;//how many to count from arrayListIndex
        Node temp = arrayList.get(arrayListIndex);//starting node

        for( int j=0; j<listCount ; j++ ){
            temp = temp.next;
        }
        return temp;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public ListIterator<Integer> listIterator() {
        return null;
    }

    public void print(){
        Node temp = head;
        System.out.print((head != null) + "\n");
        System.out.printf("%5s  %7s  %s\n","index","integer","array index");
        int count = 0;
        while( temp != null ){
            System.out.printf( "%5d  %7d  ", temp.index ,temp.integer );
            System.out.print("temp: " + temp + " ");
            if( count%K == 0 ){
                System.out.print( (count/K) + "\n" );
            }else {
                System.out.println();
            }
            count++;
            temp=temp.next;
        }
    }

    public void printArrayList(){
        System.out.print( "size: " + arrayList.size() + "\n" );
        System.out.print("HEAD: " + head + "\n");
        for( int i = 0 ; i < arrayList.size() ; ++i ){
            System.out.print( arrayList.get(i) + "\n" );
        }
    }

    public void testShit(){

    }


    @Override
    public Integer remove(int i) {
        /** Get a reference to Node that needs to be removed **/
        Node temp = getNode(i);// used arrayList to help find needed Node faster
        if ( temp == null || isEmpty() ) {// if temp is null or list is empty return null
            return null;// leave leave leave
        }
        System.out.print("temp: " + temp + "\n");
        System.out.print("AL.g(0): " + arrayList.get(0) + "\n");

        /** This is where we adjust arrayList after removal **/
        int index = i/K;

        if( i%K != 0 ){
            index++;
        }

        // minus 1 because we will deal with last position
        for( ; index < arrayList.size() ;index++ ){
            System.out.print("IN 4 loop\n");
            arrayList.set( index , arrayList.get(index).next );// the value at index will now point to the next pointer
        }



        if( ( size-1 )%K == 0 ){// if last position added has a node pointer remove
            // the first one has to be treated differently,
            // most
            arrayList.remove( arrayList.size()-1 );// now remove last position of arrayList

            System.out.print("i=0: " + head + "\n");
            /**
            if(( i == 0 )){// i=0 is a special case. can't delete

                System.out.print("i=0: " + head + "\n");
                arrayList.set(arrayList.size() - 1, temp.next);// make last position of arrayList null
            }else {
                System.out.print(": " + head + "\n");
                arrayList.set(arrayList.size() - 1, arrayList.get(arrayList.size()-1).next);// make last position of arrayList null

                arrayList.remove( arrayList.size()-1 );// now remove last position of arrayList
            }
             */
        }

        //System.out.print( "temp: " + temp + " t.prev: " + temp.prev + " t.next: " + temp.next + "\n");
        //System.out.print( "head: " + head + " h.prev: " + head.prev + " h.next: " + head.next + "\n");
        //System.out.print( "tail: " + tail + " T.prev: " + tail.prev + " T.next: " + tail.next + "\n");

        /** This code is for dealing with Node removal **/
        if( temp != head ){// #2 or #4
            if( temp != tail ){// #4
                // here we do the normal swap
                System.out.print("condition #4\n");
                temp.prev.next = temp.next;// connect prev node to next node
                temp.next.prev = temp.prev;// connect next node to prev node
                temp.next = temp.prev = null;// now disconnect temp from the rest of the list
            }else{// #2
                System.out.print("condition #2\n");
                tail = tail.prev;// move tail to new tail
                tail.next = null;// if temp is not null and is not the head, then we know new tail is a valid Node
                temp.prev = null;// detach temp from linked list
            }
        }else {// #1 or #3
            if( head != tail ){// #3
                System.out.print("condition #3\n");
                head = head.next;// move head to new head.
                head.prev = null;// detach new head from temp Node.
                temp.next = null;// detach temp from the list.
            }else {// #1
                System.out.print("condition #1\n");
                head = tail = null;// empty list
            }
        }
        /** This code is for adjusting arrayList **/
        return temp.integer;
    }

    @Override
    public Integer set(int i, Integer integer) {
        return null;
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }

    @Override
    public List<Integer> subList(int i, int i1) {
        return null;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Integer[] toArray(){
        Integer[] arr = new Integer[size];
        while(!isEmpty()){

        }
        return null;
    }

    @Override
    public void clear(){

    }

    @Override
    public boolean addAll(int i, Collection<? extends Integer> collection) {
        return false;
    }

    @Override
    public ListIterator<Integer> listIterator(int i) {
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> collection) {
        return false;
    }
}
