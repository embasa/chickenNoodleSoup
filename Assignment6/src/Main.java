import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * This main is for testing graph and passing graphs to object
 * potentially the actual algorithms should be decoupled from
 * Graph class and moved here.
 * Created by bruno on 7/26/15.
 */
public class Main
{
   public static void main(String[] args) throws IOException
   {
      try
      {
         getGraph("input.txt").topSort();
         getGraph("input2.txt").unweighed();
         getGraph("input2.txt").dijkstra();
         getUnweighedGraph( "input3.txt" ).primsAlgorithm();
         getGraph( "input3.txt" ).kruskalsAlgorithm();
      } catch (Exception e){
         e.printStackTrace();
      }
   }

   /**
    * instantiates and populates a Graph instance
    * @param fileName where graph can be found
    * @return returns reference of instance
    * @throws IOException
    */
   public static Graph getGraph(String fileName) throws IOException{
      Scanner scanner = new Scanner(new File(fileName));
      Graph graph = new Graph();
      while (scanner.hasNext())
      {
         // my input is formatted like this
         graph.addEdge(scanner.next(), scanner.next(), scanner.nextInt());
      }
      return graph;
   }

   public static Graph getUnweighedGraph(String fileName) throws IOException{
      Scanner scanner = new Scanner(new File(fileName));
      Graph graph = new Graph();
      while (scanner.hasNext())
      {
         // my input is formatted like this
         String vertexOne = scanner.next();
         String vertexTwo = scanner.next();
         int weight = scanner.nextInt();
         graph.addEdge(vertexOne, vertexTwo, weight);
         graph.addEdge(vertexTwo, vertexOne, weight);
      }
      return graph;
   }
}
