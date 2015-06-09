import java.util.Objects;

/**
 * Created by computerito on 6/9/15.
 */
public abstract class SimpleShape implements Comparable{
    public abstract float area();

    public int compareTo(Object other){
        //must coerce Object to SimpleShape before calling area
        return (int) (this.area() - ((SimpleShape) other).area());
    }
}
