/**
 * Created by computerito on 6/9/15.
 */
public class Rectangle extends SimpleShape {
    private float width, height;

    public Rectangle(float width,float height){
        this.width = width;
        this.height = height;
    }

    public float area(){
        return width*height;
    }
}
