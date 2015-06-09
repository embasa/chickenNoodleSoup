/**
 * Created by computerito on 6/9/15.
 */
public class Circle extends SimpleShape {
    private static final float PI = 3.1415926f;
    private float radius;

    public Circle(float radius)
    {
        this.radius = radius;
    }

    public float area()
    {
        return PI*radius*radius;
    }
}
