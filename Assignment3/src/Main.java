import java.util.Random;
import java.util.Scanner;

/**
 * Created by computerito on 6/30/15.
 */
//0 1 2 3  4  5
//1 2 4 8 16 32 =  63
public class Main {

    public static void main(String[] args){
        //BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        BinarySearchTree<Integer> bst = new AVLTree<>();
        Random rand = new Random(System.currentTimeMillis());
        int size = 35;
        int[] randArray = new int[size];
        while( bst.getRootHeight() != 5 ){
            //bst = new BinarySearchTree<>();// new bst to fill
            bst = new AVLTree<>();
            for( int i = 0; i<size;i++){// load array
                randArray[i] = 10 + rand.nextInt(90);// value between 10-99http://www.espnfc.us/
                bst.insert(randArray[i]);// insert the value in bst
                if(i<size-1)
                    (new TreePrinter(bst)).print("");
            }
        }

        (new TreePrinter(bst)).print("COMPLETE AVL TREE");
/**
        for(int i = 0;i< size;i++){
            System.out.print(randArray[i] + " ");
        }
 **/

        TreePrinter tp = new TreePrinter(bst);
        //Integer integer = tp.getTree().getRoot().getElement();

        while( !tp.getTree().isEmpty() ){
            Integer val = bst.getRoot().getElement();
            System.out.print("\nDeleting root: \n");
            bst.remove(val);
            //tp.setTree( bst );
            tp = new TreePrinter( bst );
            if(!bst.isEmpty())
                tp.print( "" );
            else
                tp.print("\nTree is now empty\n");
        }
    }
}
