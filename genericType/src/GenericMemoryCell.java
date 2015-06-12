/**
 * Created by computerito on 6/10/15.
 */
public class GenericMemoryCell<AnyType> {
    public AnyType read( ){
        return storedValue;
    }
    public void write( AnyType x ){
        storedValue = x;
    }
    private AnyType storedValue;
}