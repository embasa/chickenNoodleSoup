/**
 * Created by computerito on 7/1/15.
 */
public class AVLTree<AnyType extends Comparable<? super AnyType>>  extends BinarySearchTree<AnyType> {

    @Override
    public void insert(AnyType x){
        System.out.print("\ninserting node " + x + ":\n");
        root = insert(x, root);
    }

    @Override
    protected BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t ){
        return balance(super.insert(x,t));
    }

    @Override
    public void remove(AnyType x){
        root = remove( x, root );
    }

    @Override
    protected BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t ){
        return balance(super.remove( x, t ));
    }

    private int height(BinaryNode<AnyType> n){
        return n == null ? -1 : n.getHeight();
    }

        // Assume t is either balanced or within one of being balance
    private BinaryNode<AnyType> balance( BinaryNode<AnyType> t ) {
        if( t == null )
            return null;
        if( height( t.getLeft() ) - height( t.getRight() ) > 1 )// if imbalance is == 2
            if( height(t.getLeft().getLeft()) >= height( t.getLeft().getRight() ) ) {
                System.out.print( "\n    single left rotation: " + t.getElement() + "\n" );
                t = singleRotationLeft(t);
            } else {
                System.out.print( "\n    double left rotation: " + t.getElement() + "\n" );
                t = doubleRotationLeft(t);
            }
        else
        if( height( t.getRight() ) - height( t.getLeft() ) > 1 )
            if( height( t.getRight().getRight() ) >= height( t.getRight().getLeft() ) ) {
                System.out.print( "\n    single right rotation: " + t.getElement() + "\n" );
                t = singleRotationRight(t);
            } else {
                System.out.print( "\n    double right rotation: " + t.getElement() + "\n" );
                t = doubleRotationRight(t);
            }
        t.setHeight(Math.max( height( t.getLeft() ), height( t.getRight() ) ) + 1);
        return t;
    }


    private BinaryNode<AnyType> singleRotationLeft( BinaryNode<AnyType> k2 ){

        BinaryNode<AnyType> k1 = k2.getLeft();
        k2.setLeft( k1.getRight() );
        k1.setRight( k2 );
        k2.setHeight( Math.max(height(k2.getLeft()), height(k2.getRight())) + 1 );
        k1.setHeight( Math.max( height( k1.getLeft() ), k2.getHeight() ) + 1 );
        return k1;
    }
    private BinaryNode<AnyType> doubleRotationLeft(BinaryNode<AnyType> k3){
        k3.setLeft( singleRotationRight( k3.getLeft() ));
        return singleRotationLeft( k3 );
    }
    private BinaryNode<AnyType> singleRotationRight( BinaryNode<AnyType> k1 ){

        BinaryNode<AnyType> k2 = k1.getRight();
        k1.setRight( k2.getLeft() );
        k2.setLeft( k1 );
        k1.setHeight( Math.max( height( k1.getLeft() ), height( k1.getRight() ) ) +1 );
        k2.setHeight( Math.max( height( k2.getRight() ), k1.getHeight() ) + 1 );
        return k2;
    }
    private BinaryNode<AnyType> doubleRotationRight(BinaryNode<AnyType> k1 ){
        k1.setRight( singleRotationLeft( k1.getRight() ) );
        return singleRotationRight( k1 );
    }
}
