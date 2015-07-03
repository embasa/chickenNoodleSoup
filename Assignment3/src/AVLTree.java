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

        // Assume t is either balanced or within one of being balance
    private BinaryNode<AnyType> balance( BinaryNode<AnyType> t ) {
        if( t == null )
            return null;
        if( height( t.getLeft() ) - height( t.getRight() ) > 1 )// if imbalance is == 2
            if( t.height( t.getLeft().getLeft() ) >= height( t.left.right ) )
                t = rotateWithLeftChild( t );
            else
                t = doubleWithLeftChild( t );
        else
        if( height( t.right ) - height( t.left ) > ALLOWED_IMBALANCE )
            if( height( t.right.right ) >= height( t.right.left ) )
                t = rotateWithRightChild( t );
            else
                t = doubleWithRightChild( t );
        t.height = Math.max( height( t.left ), height( t.right ) ) + 1;
        return t;
    }
    /**
    private class AVLNode<AnyType> extends BinaryNode<AnyType>{
        AVLTree{

        }
    }
    **/
}
