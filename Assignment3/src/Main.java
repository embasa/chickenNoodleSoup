import java.util.*;

/**
 * Created by computerito on 6/30/15.
 */
//0 1 2 3  4  5
//1 2 4 8 16 32 =  63
public class Main {

    public static void main(String[] args) {
        System.out.print("starting... BinarySearchTree\n");
        testBst();
        System.out.print("starting... AVLTree\n");
        testAvl();
        System.out.print("starting... tree comparisons\n");
        compareTrees();
        System.out.print("\nending\n");
    }


    public static void compareTrees() {
        int N = 10000000;
        System.out.print("UNEVEN DISTRIBUTION\n");
        System.out.printf("%26s", "inserting..\n");
        System.out.printf("%-10s%7s%13s| %6s%13s\n", "n", "height", "BST Tree", "height", "AVL Tree");
        for (int n = 1000; n <= N; n *= 10) {
            BinarySearchTree<Integer> bst = new BinarySearchTree<>();
            BinarySearchTree<Integer> avl = new AVLTree<>();
            int[] array = generateRandomValuesNormal(n);
            long startBst = System.currentTimeMillis();
            for (Integer element : array) {
                bst.insert(element);
            }
            long endBst = System.currentTimeMillis() - startBst;
            long startAvl = System.currentTimeMillis();
            for (Integer element : array) {
                avl.insert(element);
            }
            long endAvl = System.currentTimeMillis() - startAvl;
            System.out.printf("%-9d:%7d%10d ms| %6d%10d ms\n", n, bst.getRootHeight(), endBst, avl.getRootHeight(), endAvl);
        }

        BinarySearchTree<Integer> tree1;
        BinarySearchTree<Integer> tree2;

        System.out.printf("%16s\n", "K test..");
        System.out.printf("%-10s%7s%13s| %6s%13s\n", "k", "height", "BST Tree", "height", "AVL Tree");
        for (int n = 1000; n <= N; n *= 10) {
            int[] sharedValues = generateRandomValuesNormal(n);
            tree1 = populateTree(new BinarySearchTree<Integer>(), sharedValues);
            tree2 = populateTree(new AVLTree<Integer>(), sharedValues);
            System.out.print("\n          [ n : " + n + " ]\n");

            for (int k = 1000; k <= N; k *= 10) {
                int[] array = generateRandomValues(k);
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
                System.out.printf("%-9d:%7d%10d ms| %6d%10d ms\n", k, tree1.getRootHeight(), end1, tree2.getRootHeight(), end2);
            }
        }
        System.out.printf("\n%16s", "M tests..");
        for (int x = 1; x <= 8; x += 2) {
            System.out.print("\n [ " + x * 10 + "% insertions and " + (10 - x) * 10 + "% searches ]\n");
            System.out.printf("%-10s%7s%13s| %6s%13s\n", "m", "height", "BST Tree", "height", "AVL Tree");

            for (int m = 100000; m <= N; m *= 10) {
                Integer[] values = new Integer[m / 2];// make integer array
                for (int i = 0; i < (x * m / (20)); i++) {
                    values[i] = 1;
                }
                ArrayList<Integer> value = new ArrayList<>(Arrays.asList(values));
                Random rand = new Random(System.currentTimeMillis());
                Collections.shuffle(value, rand);
                int[] array = generateRandomValues(m / 2);
                int[] array2 = generateRandomValuesNormal(N);
                tree1 = populateTree(new BinarySearchTree<Integer>(), array2);
                long start1 = System.currentTimeMillis();
                for (int i = 0; i < array.length; i++) {
                    if (value.get(i) == null) {
                        tree1.contains(array[i]);
                    } else {
                        tree1.insert(array[i]);
                    }
                }
                long end1 = System.currentTimeMillis() - start1;
                tree2 = populateTree(new AVLTree<Integer>(), array2);
                long start2 = System.currentTimeMillis();
                for (int i = 0; i < array.length; i++) {
                    if (value.get(i) == null) {
                        tree2.contains(array[i]);
                    } else {
                        tree2.insert(array[i]);
                    }
                }
                long end2 = System.currentTimeMillis() - start2;
                System.out.printf("%-9d:%7d%10d ms| %6d%10d ms\n", m / 2, tree1.getRootHeight(), end1, tree2.getRootHeight(), end2);
            }
        }

    }

    public static int[] generateRandomValuesNormal(int n) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();// use to see if number is contained
        Random rand = new Random(System.currentTimeMillis());
        int[] array = new int[n];
        int i = 0;
        while (i < n) {
            double r = rand.nextGaussian();
            int value = (int) ((r - (int) r) * (n)) + ((i / (n / 10)) * n);
            if (!hashMap.containsKey(value)) {
                array[i++] = value;
                hashMap.put(value, value);
            }
        }
        return array;
    }

    public static int[] generateRandomValues(int n) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();// use to see if number is contained
        Random rand = new Random(System.currentTimeMillis());
        int[] array = new int[n];
        int i = 0;
        while (i < n) {
            int value = rand.nextInt();
            if (!hashMap.containsKey(value)) {
                array[i++] = value;
                hashMap.put(value, value);
            }
        }
        return array;
    }

    public static void emptyAndPrint(BinarySearchTree<Integer> tree, String label) {
        TreePrinter tp = new TreePrinter(tree);
        tp.print(label);

        while (!tp.getTree().isEmpty()) {
            Integer val = tree.getRoot().getElement();
            System.out.print("\nDeleted root: \n");
            tree.remove(val);
            tp = new TreePrinter(tree);
            if (!tree.isEmpty())
                tp.print("");
            else
                tp.print("\nTree is now empty.\n");
        }
    }

    public static void testAvl() {
        BinarySearchTree<Integer> tree = new AVLTree<>();
        int size = 35;
        int[] randArray = generateRandomValuesWithRange(size);

        for (int i = 0; i < size; i++) {// load array
            tree.insert(randArray[i]);// insert the value in tree
            (new TreePrinter(tree)).print("");
        }

        emptyAndPrint(tree, "COMPLETED AVL TREE:");
    }

    public static void testBst() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        int size = 35;
        while (tree.getRootHeight() != 5) {
            tree = new BinarySearchTree<>();// new tree to fill
            int[] randArray = generateRandomValuesWithRange(size);
            for (int i = 0; i < size; i++) {// load array
                tree.insert(randArray[i]);// insert the value in tree
            }
        }
        emptyAndPrint(tree, "Original tree:");
    }

    /**
     * This is for the first part where range is fixed between 10 - 99.
     **/
    public static int[] generateRandomValuesWithRange(int n) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();// use to see if number is contained
        Random rand = new Random(System.currentTimeMillis());
        int[] array = new int[n];
        int i = 0;
        while (i < n) {
            int value = 10 + rand.nextInt(90);
            if (!hashMap.containsKey(value)) {
                array[i++] = value;
                hashMap.put(value, value);
            }
        }
        return array;
    }

    //
    public static BinarySearchTree<Integer> populateTree(BinarySearchTree<Integer> tree, int[] array) {
        for (Integer element : array) {
            tree.insert(element);
        }
        return tree;
    }

}
