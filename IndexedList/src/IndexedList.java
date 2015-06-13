import java.util.*;

/**
 * Created by computerito on 6/12/15.
 */
public class IndexedList implements List<Integer> {

    protected class Node{
        protected Node(){

        }
        Node next;
        Node prev;
        Integer integer;
        int index;
    }

    private ArrayList<Node> arrayList;
    private Node head;
    private Node tail;
    private int size = 0;
    private static final int K = 3;
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
        if(temp == null){
            return null;
        }
        return temp.integer;
    }

    private Node getNode(int i){
        if((i<0)|| (i>= size)){
            return null;
        }
        int arrayListIndex = i/K;//position in arrayList closest to i
        int listCount = i%K;//how many to count from arrayListIndex
        Node temp = arrayList.get(arrayListIndex);//starting node

        for (int j=0;j<listCount;j++){
            temp = temp.next;
        }
        return temp;
    }

    @Override
    public Integer remove(int i) {
        Node temp = getNode(i);
        if(temp == null){
            return null;
        }
        System.out.printf("%5d  %7d  \n",temp.index,temp.integer);

        int val = temp.integer;
        temp.integer = tail.integer;// copy integer reference to temp's integer reference.
        temp = tail;// use temp to hold onto current tail.
        temp.prev = null;// isolate temp from any other node (next is already null).
        tail = tail.prev;// move tail one back to its new position.

        // if tail is not null..
        if(tail != null){
            tail.next = null;// disconnect tail from next.
        }else {
            // otherwise make head point to null!
            head = null;
        }
/**
        if((i/K)*K == size){
            arrayList.remove(arrayList.size()-1);
        }
 **/
        System.out.print("end of func\n");
        size--;
        return val;
    }

    /**
    @Override
    public Integer remove(int i) {

        if((i<0)|| (i>= size)){
            return null;
        }
        int arrayListIndex = i/K;//position in arrayList closest to i
        int listCount = i%K;//how many to count from arrayListIndex

        Node temp = arrayList.get(arrayListIndex);//starting node

        for (int j=0;j<listCount;j++){
            temp = temp.next;
        }
        //if listCount is zero, then remove last position from arrayList
        if(arrayListIndex*K == size ){
            arrayList.remove(size-1);//not to be confused with my remove.
        }else if(listCount == 0) {//removing an arrayListPosition
            temp.integer = tail.integer;
        }
        size--;

        if(temp == head){
            if( head == tail ){
                head = tail = null;
            }else {
                head = head.next;
                head.prev = null;
                temp.next = null;
            }
        }else if( tail == temp ){
            tail = tail.prev;
            tail.next = null;
            temp.prev = null;
        }else{
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
            temp.next = temp.prev = null;
        }
        return temp.integer;
    }
    **/

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
        while(temp != null){
            System.out.printf("%5d  %7d  ",temp.index,temp.integer);
            if(temp.integer%K == 0){
                System.out.print((temp.integer/K) + "\n");
            }else {
                System.out.println();
            }
            temp=temp.next;
        }
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
