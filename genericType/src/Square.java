/**
 * Created by computerito on 6/9/15.
 */
public class Square extends SimpleShape{

    private float width;

    public Square(float width){
        this.width = width;
    }

    public float area(){
        return width*width;
    }
}
