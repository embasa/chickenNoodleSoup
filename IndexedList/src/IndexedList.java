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
    private static final int K = 5;

    protected class Node{
        protected Node(){

        }
        Node next;
        Node prev;
        Integer integer;
    }

    public IndexedList(){
        head = tail = null;
        arrayList = new ArrayList<>();
    }

    @Override
    public Integer get(int i){
        Node temp = this.getNode(i);
        if( temp == null ){
            return null;
        }
        return temp.integer;
    }

    @Override
    public void add(int i, Integer integer) {
        if( (i<0) || (i>=size)){
            return;
        }

        /** prepare Node **/
        Node newNode = new Node();// make reference to new Node
        newNode.integer = integer;// I don't know how to avoid warning
        Node currentNode = this.getNode(i);// get a reference to Node in that ith position
        if(currentNode == null){// check for now in case there is null in linkedList
            return;
        }

        /** insert new Node to next of current Node **/
        newNode.next = currentNode;// connect new Node's next reference to current Node
        newNode.prev = currentNode.prev;// connect Node's prev to current Node's prev
        currentNode.prev = newNode;// connect current Node's prev to new Node
        if(newNode.prev != null){
            newNode.prev.next = newNode;// if not null attach previous node to new Node
        }else{
            head = newNode;// if new Node's prev is null, then it is new head
        }

        /** adjust arrayList values accordingly **/
        int index = i/K + 1;
        if ( i % K == 0 ) {
            index--;
        }

        for( ; index<arrayList.size() ; index++ ){
            arrayList.set( index, arrayList.get(index).prev );// adjust all pointers 1 back
        }


        /** add tail to end of arrayList **/
        if ( size%K == 0 ){// if size%K == 0, then must add a pointer
            arrayList.add( tail );
        }

        size++;
    }

    @Override
    public boolean add(Integer integer) {
        /** prepare Node **/
        Node temp = new Node();
        temp.integer = integer;// I don't know how to avoid warning
        /** insert node where appropriate **/

        if(size%K == 0){
            arrayList.add(temp);
        }

        size++;

        if(head == null){
            //if head empty then tail and head point to same reference
            tail = head = temp;
            System.out.print( "successfully added integer [" + temp.integer + "]\n");
            return true;
        }

        //if head is not null then tail.prev is not null
        System.out.print( "successfully added integer [" + temp.integer + "]\n");
        tail.next = temp;
        temp.prev = tail;
        tail = temp;

        return true;
    }

    @Override
    public Integer remove(int i) {
        System.out.print( "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" );
        System.out.print( "begin removing index position " + i + "\n" );
        /** Get a reference to Node that needs to be removed **/
        Node temp = getNode(i);// used arrayList to help find needed Node faster
        System.out.print("reference of temp: " + temp + "\n");
        if ( temp == null || isEmpty() ) {// if temp is null or list is empty return null
            return null;// leave leave leave
        }


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


        // if the last position is a arrayList reference. remove last one.
        if( ( --size )%K == 0 ) {// if last position added has a node pointer remove
            arrayList.remove(arrayList.size() - 1);// now remove last position of arrayList
        }

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
        System.out.print( "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" );
        /** This code is for adjusting arrayList **/
        return temp.integer;
    }

    @Override
    public Integer set(int i, Integer integer) {
        if( (i<0) || (i>=size) ){
            return null;
        }
        Node temp = getNode(i);
        Integer previousInteger = temp.integer;
        temp.integer = integer;
        return previousInteger;
    }

    // This is the search algorithm for finding the ith Node
    private Node getNode(int i){
        if( (i<0) || (i>= size) ){
            return null;
        }
        int arrayListIndex = i/K;//position in arrayList closest to i
        int listCount = i%K;//how many to count from arrayListIndex
        Node temp;
        if((arrayListIndex < arrayList.size()-1) && (K-listCount) < listCount ){
            arrayListIndex++;
            temp = arrayList.get(arrayListIndex);//starting node
            for( int j=0; j<K-listCount ; j++ ){
                temp = temp.prev;
            }
        }else {
            temp = arrayList.get(arrayListIndex);//starting node
            for ( int j=0;j < listCount; j++ ) {
                temp = temp.next;
            }
        }
        return temp;
    }

    public void print(){

        System.out.print( "========================================\n" );
        Node temp = head;
        System.out.printf("%5s  %7s  %s\n","index","integer","array index");
        int count = 0;
        while( temp != null ){
            System.out.printf( "%5d  %7d  ", count ,temp.integer );
            System.out.print("temp: " + temp + " ");
            if( count%K == 0 ){
                System.out.print( (count/K) + "\n" );
            }else {
                System.out.println();
            }
            count++;
            temp=temp.next;
        }
        System.out.print( "========================================\n" );
    }

    public void printArrayList(){
        System.out.print( "----------------------------------------\n" );
        for( int i = 0 ; i < arrayList.size() ; ++i ){
            System.out.print( "AL[" + i + "]: " + arrayList.get(i) + "\n" );
        }
        System.out.print( "----------------------------------------\n" );
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
        return size;
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
        head = tail = null;
        arrayList.clear();;
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

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public ListIterator<Integer> listIterator() {
        return null;
    }

}
