import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by bruno on 7/26/15.
 */
public class Main
{
   public static void main(String[] args) throws IOException
   {
      Graph graph = new Graph();
      Scanner scanner = new Scanner(new File("input2.txt"));
      while (scanner.hasNext())
      {
         // my input is formatted like this
         graph.addEdge(scanner.next(), scanner.next(), scanner.nextInt());
      }

      try
      {
         //graph.topSort();
         graph.unweighed();
      } catch (Exception e)
      {
         e.printStackTrace();
      }
   }
}
