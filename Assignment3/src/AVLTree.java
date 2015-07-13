/**
 * Created by computerito on 7/1/15.
 * @author bruno
 * I used professors cleaned up version of the textbooks BinarySearchTree to implement my AVL tree
 *
 */
public class AVLTree<AnyType extends Comparable<? super AnyType>> extends BinarySearchTree<AnyType> {
    /**
     * This public method just calls the private insertion routine that takes in a
     * Node
     * @param x the item to insert.
     */
    @Override
    public void insert(AnyType x) {
//        System.out.print("\ninserting node " + x + ":\n");
        root = insert(x, root);
    }

    /**
     * Override insertion so as to be able to wrap in balance
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return
     */
    @Override
    protected BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
        return balance(super.insert(x, t));
    }

    @Override
    public void remove(AnyType x) {
        root = remove(x, root);
    }

    @Override
    protected BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
        return balance(super.remove(x, t));
    }

    private int height(BinaryNode<AnyType> n) {
        return n == null ? -1 : n.getHeight();
    }

    /**
     * This is the method that is used to rap the result of calling the insert or delete methods of the super
     * class. This then rotates the results as appropriate
     * @param t
     * @return
     */
    private BinaryNode<AnyType> balance(BinaryNode<AnyType> t) {
        if (t == null)
            return null;
        if (height(t.getLeft()) - height(t.getRight()) > 1)// if imbalance is == 2
            if (height(t.getLeft().getLeft()) >= height(t.getLeft().getRight())) {
                t = singleRotationLeft(t);
                //    System.out.print( "\n    Single left rotation: " + t.getElement() + "\n" );
            } else {
                t = doubleRotationLeft(t);
                //    System.out.print( "\n    Double left-right rotation: " + t.getElement() + "\n" );
            }
        else if (height(t.getRight()) - height(t.getLeft()) > 1)
            if (height(t.getRight().getRight()) >= height(t.getRight().getLeft())) {
                t = singleRotationRight(t);
                //    System.out.print( "\n    Single right rotation: " + t.getElement() + "\n" );
            } else {
                t = doubleRotationRight(t);
                //    System.out.print( "\n    Double right-left rotation: " + t.getElement() + "\n" );
            }
        t.setHeight(Math.max(height(t.getLeft()), height(t.getRight())) + 1);
        return t;
    }

    /**
     * takes k2, and then moves k1 to root and shifts k2 to the right
     * @param k2 root of subtree being rotated
     * @return
     */
    private BinaryNode<AnyType> singleRotationLeft(BinaryNode<AnyType> k2) {

        BinaryNode<AnyType> k1 = k2.getLeft();
        k2.setLeft(k1.getRight());
        k1.setRight(k2);
        k2.setHeight(Math.max(height(k2.getLeft()), height(k2.getRight())) + 1);
        k1.setHeight(Math.max(height(k1.getLeft()), k2.getHeight()) + 1);
        return k1;
    }

    private BinaryNode<AnyType> doubleRotationLeft(BinaryNode<AnyType> k3) {
        k3.setLeft(singleRotationRight(k3.getLeft()));
        return singleRotationLeft(k3);
    }

    private BinaryNode<AnyType> singleRotationRight(BinaryNode<AnyType> k1) {

        BinaryNode<AnyType> k2 = k1.getRight();
        k1.setRight(k2.getLeft());
        k2.setLeft(k1);
        k1.setHeight(Math.max(height(k1.getLeft()), height(k1.getRight())) + 1);
        k2.setHeight(Math.max(height(k2.getRight()), k1.getHeight()) + 1);
        return k2;
    }

    /**
     * Mirror case of doubleRotationLeft
     * @param k1
     * @return
     */
    private BinaryNode<AnyType> doubleRotationRight(BinaryNode<AnyType> k1) {
        k1.setRight(singleRotationLeft(k1.getRight()));
        return singleRotationRight(k1);
    }

    /**
     * Method to test if a tree is balance
     * @param node root of subtree that is being tested for imbalances
     * @return
     * @throws Exception
     */
    private int checkBalance(BinaryNode<AnyType> node) throws Exception {
        if (node == null)
            return -1;

        int leftHeight = checkBalance(node.getLeft());
        int rightHeight = checkBalance(node.getRight());
        if ((Math.abs(height(node.getLeft()) - height(node.getRight())) > 1) || (height(node.getLeft()) != leftHeight) || (height(node.getRight()) != rightHeight)) {
            throw new Exception("Unbalanced trees.");
        }
        return height(node);
    }
}
