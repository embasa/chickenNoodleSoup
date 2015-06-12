import java.util.Objects;

/**
 * Created by computerito on 6/9/15.
 */
public abstract class SimpleShape implements Comparable<SimpleShape> {
    public abstract float area();

    public int compareTo(SimpleShape other) {
        //must coerce Object to SimpleShape before calling area
        return (int) (this.area() - other.area());
    }
}
