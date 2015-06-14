/**
 * Created by computerito on 6/12/15.
 */
public class Main {
    public static void main(String[] args){
        IndexedList myList = new IndexedList();
        System.out.print("chicken NOODLE SOUP\n");
        for(int i=0;i<10;i++){
            myList.add(i);
        }
        myList.print();
        System.out.print("Holly Stephens\n");
        System.out.print(myList.remove(4) + "\n");
        myList.print();
    }
}
