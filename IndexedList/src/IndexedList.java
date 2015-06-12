import com.sun.istack.internal.NotNull;

import java.util.*;

/**
 * Created by computerito on 6/12/15.
 */
public class IndexedList implements List<Integer> {

    protected class Node{
        protected Node(){
            /* EMPTY */
        }
        Node next = null;
        Node prev = null;
        Integer element = null;
    }

    ArrayList<Node> arrayList = null;

    IndexedList(){
        arrayList = new ArrayList<>();
    }

    @Override
    public ListIterator<Integer> listIterator() {
        return null;
    }

    @Override
    public Integer get(int i){
        return null;
    }

    @Override
    public boolean add(Integer integer) {
        return false;
    }

    @Override
    public void add(int i, Integer integer) {

    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public Integer remove(int i) {
        return null;
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
        return false;
    }

    @Override
    public Integer[] toArray(){
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
