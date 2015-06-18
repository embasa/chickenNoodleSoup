/**
 * Created by computerito on 6/12/15.
 */
public class Main {
    public static void main(String[] args){
        IndexedList myList = new IndexedList();
        System.out.print("Initial value\n");
        for(int i=0;i<3;i++){
            myList.add(i);
        }

        myList.print();
        myList.printArrayList();
        System.out.print( "value removed: " + myList.remove(0) + "\n" );
        myList.print();
        myList.printArrayList();
        System.out.print( "value removed: " + myList.remove(0) + "\n" );
        myList.print();
        myList.printArrayList();
        System.out.print( "value removed: " + myList.remove(0) + "\n" );
        myList.print();
        myList.printArrayList();
    }
}
