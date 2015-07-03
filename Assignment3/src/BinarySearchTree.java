// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws Exception as appropriate.

/**
 * Implements an unbalanced binary search tree. Note that all "matching" is
 * based on the compareTo method.
 * 
 * @author Mark Allen Weiss (as cleaned up by Ronald Mak)
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> 
{
    /** The tree root. */
    protected BinaryNode<AnyType> root;

    /** get Root... **/
    public BinaryNode<AnyType> getRoot() {
         return root;
    }

    /**
     * Construct the tree.
     */
    public BinarySearchTree() 
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert(AnyType x) 
    {
        root = insert(x, root);
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove(AnyType x) 
    {
        root = remove(x, root);
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     * @throws Exception 
     */
    public AnyType findMin() throws Exception 
    {
        if (isEmpty()) throw new Exception();
        return findMin(root).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     * @throws Exception 
     */
    public AnyType findMax() throws Exception 
    {
        if (isEmpty()) throw new Exception();
        return findMax(root).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains(AnyType x) 
    {
        return contains(x, root);
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() 
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree() 
    {
        if (isEmpty()) {
            System.out.println("Empty tree");
        }
        else {
            printTree(root);
        }
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    protected BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t)
    {
        // Create a new node to be attached 
        // to the last-visited node.
        if (t == null) {
            return new BinaryNode<>(x, null, null);
        }

        int compareResult = x.compareTo(t.element);

        // Find the insertion point.
        if (compareResult < 0) {
            t.left = insert(x, t.left);
        }
        else if (compareResult > 0) {
            t.right = insert(x, t.right);
        }
        else {
            // Duplicate: do nothing.
        }
        
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    protected BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t)
    {
        // If item not found, do nothing.
        if (t == null) return t; 

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = remove(x, t.left);
        }
        else if (compareResult > 0) {
            t.right = remove(x, t.right);
        }
        
        // Two children.
        else if (t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } 
        
        // Zero or one child.
        else {
            t = (t.left != null) ? t.left : t.right;
        }
        
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) 
    {
        if (t == null) {
            return null;
        }
        else if (t.left == null) {
            return t;
        }
        else {
            return findMin(t.left);
        }
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) 
    {
        if (t != null) {
            while (t.right != null) {
                t = t.right;
            }
        }

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains(AnyType x, BinaryNode<AnyType> t) 
    {
        if (t == null) return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            return contains(x, t.left);
        }
        else if (compareResult > 0) {
            return contains(x, t.right);
        }
        else {
            return true; // Match
        }
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree(BinaryNode<AnyType> t) 
    {
        if (t != null) {
            printTree(t.left);
            System.out.print(t.element + " ");
            printTree(t.right);
        }
    }

    public int getRootHeight(){
        return isEmpty() ? -1 : 1 + Math.max(height(root.left),height(root.right));
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height(BinaryNode<AnyType> t) 
    {
        return t == null ? -1 
                         : 1 + Math.max(height(t.left), height(t.right));
    }

    /**
     * Basic node stored in unbalanced binary search trees.
     */
    public static class BinaryNode<AnyType>
    {
        private AnyType element;           // data in the node
        private BinaryNode<AnyType> left;  // left child
        private BinaryNode<AnyType> right; // right child
        private int height;
        
        /**
         * Constructor.
         * @param theElement the node data
         */
        BinaryNode(AnyType theElement) 
        {
            this(theElement, null, null);
        }

        /**
         * Constructor
         * @param theElement
         * @param lt left subtree
         * @param rt right subtree
         */
        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt,
                                       BinaryNode<AnyType> rt) 
        {
            element = theElement;
            left = lt;
            right = rt;
            height = 0;
        }

        public void incrementHeight(){
            height++;
        }

        public void setElement(AnyType element){
            this.element = element;
        }

        public void setLeft(BinaryNode<AnyType> left){
            this.left = left;
        }

        public  void setRight(BinaryNode<AnyType> right){
            this.right = right;
        }

        public BinaryNode<AnyType> getLeft(){
            return left;
        }

        public BinaryNode<AnyType> getRight(){
            return right;
        }

        public AnyType getElement(){
            return element;
        }
    }

}