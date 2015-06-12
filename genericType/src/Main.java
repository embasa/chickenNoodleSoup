import java.util.ArrayList;

/**
 * Created by computerito on 6/9/15.
 */
public class Main {
    public static void main(String[] args){
        /**
        ArrayList<SimpleShape> shapes2 = new ArrayList<>();
        shapes.add(new Square(5));
        shapes.add(new Rectangle(3,4));
        shapes.add(new Circle(2));
        System.out.print("Before sorting:"); print(shapes);
        sort(shapes);
        System.out.print(" After sorting:"); print(shapes);
        SimpleShape shapes[] = new SimpleShape[] {
                new Square(5),
                new Rectangle(3, 4),
                new Circle(2)
        };

        System.out.println("Total area: " + totalArea(shapes));

        Square squares[] = new Square[] {
                new Square(5),
                new Square(3),
                new Square(2)
        };


        System.out.println("Total area: " + totalArea(squares));
       **/
        ArrayList<SimpleShape> shapes2 = new ArrayList<>();
        Object we = shapes2;
        ArrayList<String> two = (ArrayList<String>) we;
        //do it do it do it do it do it do it do it do it do it do it do it do it
        GenericMemoryCell<String>[] arr1 = new GenericMemoryCell[10];
        GenericMemoryCell<Integer> a = new GenericMemoryCell<>();
        a.write(5);
        Object[] arr2 = arr1;
        Object b = a;
        arr2[0] = b;
        System.out.print(":" + a.read() + "\n");
        //two.add(new String("what")); <--- This is a run time error.
        shapes2.add(new Square(5));
        shapes2.add(new Rectangle(3,4));
        shapes2.add(new Circle(2));
        ArrayList<Square> squares = new ArrayList<>();
        squares.add(new Square(5));
        squares.add(new Square(3));
        squares.add(new Square(2));

        System.out.println("Total area: " + totalArea(squares));
        System.out.println("Total area: " + totalArea(shapes2));

    }

    private static void print(ArrayList<SimpleShape> elements)
    {
        for (SimpleShape elmt : elements) {
            System.out.print(" " + elmt);
        }
        System.out.println();
    }

    private static void sort(ArrayList<SimpleShape> elements)
    {
        for (int i = 0; i < elements.size()-1; i++) {
            for (int j = i+1; j < elements.size(); j++) {
                if (elements.get(j).compareTo(elements.get(i)) < 0) {
                    SimpleShape temp = elements.get(i);
                    elements.set(i, elements.get(j));
                    elements.set(j, temp);
                }
            }
        }
    }

    private static float totalArea(SimpleShape elements[]) {
        float total = 0;

        for (SimpleShape elmt : elements) {
            total += elmt.area();
        }

        return total;
    }

    private static float totalArea(ArrayList< ? extends SimpleShape> elements){
        float total = 0;

        for (SimpleShape elmt : elements) {
            total += elmt.area();
        }

        return total;
    }
/**
    private static <MyType> MyType max(MyType elements[], MyType other)
    {
        MyType another = other;
        return another;
    }
  **/
    private static <MyType extends Comparable<? super MyType>> MyType max(MyType elements[])
    {
        int maxIndex = 0;

        for (int i = 1; i < elements.length; i++) {
            if (elements[i].compareTo(elements[maxIndex]) > 0) {
                maxIndex = i;
            }
        }

        return elements[maxIndex];
    }
}
