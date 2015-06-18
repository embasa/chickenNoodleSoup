import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by computerito on 6/12/15.
 */
public class Main {
    public static void main(String[] args){
        int N = 100000;

        /** Make set of N number of to test **/
        Random rand = new Random( System.currentTimeMillis() );
        long start = System.currentTimeMillis();
        LinkedList<Integer> arrayList = new LinkedList<>();
        arrayList.add(10);
        for( int i=0;i<N;i++ ){
            int value = rand.nextInt(arrayList.size());
            arrayList.add(value,rand.nextInt());
        }
        long elapsed = System.currentTimeMillis() - start;
        System.out.print("elapsed: " + elapsed + "\n");
        /**
        LinkedList<Integer> linkedList = new LinkedList<>();
        for( int i=0;i<N;i++ ){
            linkedList.add( arrayList.get(i) );
        }

        IndexedList myList = new IndexedList();
        System.out.print("Initial value\n");
        for( int i=0;i<N;i++ ){
            myList.add( arrayList.get(i) );
        }
        int iterations = 10;
        int count = 0;
        int sum = 0;
        System.out.print("indexedList:\n");
        while( count++<iterations ){
            sum += testGet(myList);
        }
        System.out.print("average time elasped: " + sum/iterations + "\n");
        count = 0;
        sum = 0;
        System.out.print("linkedList:\n");
        while( count++<iterations ){
            sum += testGet(linkedList);
        }
        System.out.print("average time elasped: " + sum/iterations + "\n");
        count = 0;
        sum = 0;
        System.out.print("arrayList:\n");
        while( count++<iterations ){
            sum += testGet(arrayList);
        }
        System.out.print("average time elasped: " + sum/iterations + "\n");
         **/
    }

    public static long testGet(List<Integer> list){
        Random rand = new Random( System.currentTimeMillis() );
        int N = 10000;
        int iterations = 1000000;
        long start = System.currentTimeMillis();
        for( int i = iterations;i > 0;--i ){
            int val = rand.nextInt(N);
            list.get(val);
        }
        return System.currentTimeMillis() - start;
    }
}
