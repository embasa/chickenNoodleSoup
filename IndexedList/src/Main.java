import java.util.Random;

/**
 * Created by computerito on 6/12/15.
 */
public class Main {
    public static void main(String[] args){
        IndexedList myList = new IndexedList();
        System.out.print("Initial value\n");
        for( int i=0;i<10;i++ ){
            myList.add(i);
        }

        myList.print();
        myList.printArrayList();
        myList.add(9,666);
        myList.print();
        myList.printArrayList();
        //**
        Random rand = new Random( System.currentTimeMillis() );
        int maxValue = 100;
        for( int i = maxValue;i >= 0;--i ){
            int index = rand.nextInt(myList.size());
            myList.set( index, 666 );
            myList.print();
            myList.printArrayList();
        }
        // **/
    }
}
