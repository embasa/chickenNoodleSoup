/**
 * Created by computerito on 7/1/15.
 */
public class AVLTree<AnyType extends Comparable<? super AnyType>>  extends BinarySearchTree<AnyType> {

    @Override
    public void insert(AnyType x){
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
            if( height(t.getLeft().getLeft()) >= height( t.getLeft().getRight() ) )
                t = singleRotationLeft( t );
            else
                t = doubleRotationLeft( t );
        else
        if( height( t.getRight() ) - height( t.getLeft() ) > 1 )
            if( height( t.getRight().getRight() ) >= height( t.getRight().getLeft() ) )
                t = singleRotationRight( t );
            else
                t = doubleRotationRight( t );
        t.setHeight(Math.max( height( t.getLeft() ), height( t.getRight() ) ) + 1);
        return t;
    }


    private BinaryNode<AnyType> singleRotationLeft(BinaryNode<AnyType> t){
        return null;
    }
    private BinaryNode<AnyType> doubleRotationLeft(BinaryNode<AnyType> t){
        return null;
    }
    private BinaryNode<AnyType> singleRotationRight(BinaryNode<AnyType> t){
        return null;
    }
    private BinaryNode<AnyType> doubleRotationRight(BinaryNode<AnyType> t){
        return null;
    }
}
