import java.util.Random;

/**
 * Created by computerito on 6/12/15.
 */
public class Main {
    public static void main(String[] args){
        IndexedList myList = new IndexedList();
        System.out.print("Initial value\n");
        for(int i=0;i<30;i++){
            myList.add(i);
        }

        Random rand = new Random( System.currentTimeMillis() );
        int maxValue = 30;
        for( int i = 29;i >= 0;--i ){
            myList.print();
            myList.printArrayList();
            Integer val = myList.remove(rand.nextInt(maxValue));
            if( val != null ){
               maxValue--;
            }
            System.out.print( "value removed: " + val + "\n" );
        }

        myList.print();
        myList.printArrayList();
        // **/
    }
}
