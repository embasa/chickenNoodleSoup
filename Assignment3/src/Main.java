import java.util.*;

/**
 * Created by computerito on 6/30/15.
 */
//0 1 2 3  4  5
//1 2 4 8 16 32 =  63
public class Main {

    public static void main(String[] args){
        //BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        //testBst();
        //testAvl();
        compareTrees();
    }


    public static void compareTrees(){
        System.out.print("Inserting\n");
        System.out.printf("%-10s%16s%16s\n", "n", "BST Tree", "AVL Tree");
        for( int n= 1000;n <=10000000;n*=10){
            BinarySearchTree<Integer> bst = new BinarySearchTree<>();
            BinarySearchTree<Integer> avl = new AVLTree<>();
            int[] array = generateRandomValues( n );
            long startBst = System.currentTimeMillis();
            for( Integer element: array){
                bst.insert(element);
            }
            long endBst = System.currentTimeMillis() - startBst;
            long startAvl = System.currentTimeMillis();
            for( Integer element: array){
                avl.insert(element);
            }
            long endAvl = System.currentTimeMillis() - startAvl;
            System.out.printf( "%-9d:%13d ms%13d ms\n", n, endBst, endAvl );
        }

        BinarySearchTree<Integer> tree1 = null;
        BinarySearchTree<Integer> tree2 = null;
        /**
        System.out.print("K tests with 1 million elements\n");
        System.out.printf("%-10s%16s%16s\n", "k", "BST Tree", "AVL Tree");
        for( int n= 1000;n<=10000000;n*=10) {
            int[] array = generateRandomValues(n);
            long start1 = System.currentTimeMillis();
            for (Integer element : array) {
                tree1.contains(element);
            }
            long end1 = System.currentTimeMillis() - start1;
            long start2 = System.currentTimeMillis();
            for (Integer element : array) {
                tree2.contains(element);
            }
            long end2 = System.currentTimeMillis() - start2;
            System.out.printf( "%-9d:%13d ms%13d ms\n", n, end1, end2 );
        }
         **/
        System.out.print("\nM tests\n");
        for( int x = 3 ; x <=7 ; x+=2 ) {
            int siize = 1000000;
            Integer[] values = new Integer[siize];
            System.out.print("\nratio " + x + "\n");
            System.out.printf("%-10s%16s%16s\n", "m", "BST Tree", "AVL Tree");
            for (int i = 0; i < ( x*siize/10) ; i++) {
                values[i] = 1;
            }

            ArrayList<Integer> value = new ArrayList<>(Arrays.asList(values));
            Random rand = new Random(System.currentTimeMillis());
            Collections.shuffle(value, rand);
            int n = 1000000;
            for (int m = 1000; m <= 100000; m *= 10) {
                int[] array = generateRandomValues(m);
                tree1 = populateTree(new BinarySearchTree<Integer>(), generateRandomValues(n));
                long start1 = System.currentTimeMillis();
                for (int i = 0; i < array.length; i++) {
                    if (values[i] < 1) {
                        tree1.contains(array[i]);
                    } else {
                        tree1.insert(array[i]);
                    }
                }
                long end1 = System.currentTimeMillis() - start1;
                tree2 = populateTree(new AVLTree<Integer>(), generateRandomValues(n));
                long start2 = System.currentTimeMillis();
                for (int i = 0; i < array.length; i++) {
                    if (values[i] < 1) {
                        tree2.contains(array[i]);
                    } else {
                        tree2.insert(array[i]);
                    }
                }
                long end2 = System.currentTimeMillis() - start2;
                System.out.printf("%-9d:%13d ms%13d ms\n", m, end1, end2);
            }
        }

    }

    public static int[] generateRandomValues( int n ){
        HashMap< Integer, Integer > hashMap = new HashMap<>();// use to see if number is contained
        Random rand = new Random(System.currentTimeMillis());
        int[] array = new int[n];
        int i=0;
        while( i < n ){
            int value = rand.nextInt();
            if( !hashMap.containsKey( value ) ){
                array[i++] = value;
                hashMap.put( value,value );
            }
        }
        return array;
    }
    public static void emptyAndPrint( BinarySearchTree<Integer> tree , String label ){
        TreePrinter tp = new TreePrinter(tree);
        tp.print( label );

        while( !tp.getTree().isEmpty() ){
            Integer val = tree.getRoot().getElement();
            System.out.print("\nDeleted root: \n");
            tree.remove(val);
            tp = new TreePrinter( tree );
            if(!tree.isEmpty())
                tp.print( "" );
            else
                tp.print("\nTree is now empty.\n");
        }
    }

    public static void testAvl(  ){
        BinarySearchTree<Integer> tree = new AVLTree<>();
        int size = 35;
        int[] randArray = generateRandomValuesWithRange( size );

        for( int i = 0; i<size ;i++ ){// load array
            tree.insert(randArray[i]);// insert the value in tree
            ( new TreePrinter(tree) ).print( "" );
        }

        emptyAndPrint(tree, "COMPLETED AVL TREE:");
    }

    public static void testBst(  ){
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        int size = 35;
        while( tree.getRootHeight() != 5 ){
            tree = new BinarySearchTree<>();// new tree to fill
            int[] randArray = generateRandomValuesWithRange( size );
            for( int i = 0; i<size ;i++ ){// load array
                tree.insert(randArray[i]);// insert the value in tree
            }
        }
        emptyAndPrint(tree,"Original tree:");
    }

    /** This is for the first part where range is fixed between 10 - 99. **/
    public static int[] generateRandomValuesWithRange( int n ){
        HashMap< Integer, Integer > hashMap = new HashMap<>();// use to see if number is contained
        Random rand = new Random(System.currentTimeMillis());
        int[] array = new int[n];
        int i=0;
        while( i < n ){
            int value = 10 + rand.nextInt(90);
            if( !hashMap.containsKey( value ) ){
                array[i++] = value;
                hashMap.put( value,value );
            }
        }
        return array;
    }

    //
    public static BinarySearchTree<Integer> populateTree(BinarySearchTree<Integer> tree,int[] array){
        for( Integer element : array ){
            tree.insert(element);
        }
        return tree;
    }

}
