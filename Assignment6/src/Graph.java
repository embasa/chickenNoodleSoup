import java.util.*;

/**
 * Created by bruno on 7/26/15.
 */
public class Graph
{
   private int indegreeSum;
   private HashMap<String, Vertex> graph;

   public Graph()
   {
      this.graph = new HashMap<>();
      this.indegreeSum = 0;
   }

   public void addEdge(String name, String destination, Integer weight)
   {
      Vertex vertex = graph.get(name);
      Vertex destinationVertex = graph.get(destination);
      if (vertex == null)
      {//if vertex doesn't exist, create it
         vertex = new Vertex(name);
         graph.put(name, vertex);
      }

      if (destinationVertex == null)
      {//if destination doesn't exist. create it too
         destinationVertex = new Vertex(destination);
         graph.put(destination, destinationVertex);
      }

      vertex.addAdjacentVertex(destination, weight);
      destinationVertex.indegree++;
   }

   public void print()
   {
      printHeader();
        /*
        for(Map.Entry<String,Vertex> vertex:graph.entrySet()){
            System.out.print(vertex.getKey() +  " indegree " + vertex.getValue().getIndegree() + "\n" );
            vertex.getValue().print();
        }
        */
   }

   public void printHeader()
   {
      System.out.printf("%-10s| ", "Dequeue #");
      for (Map.Entry<String, Vertex> vertex : graph.entrySet())
      {
         System.out.printf("%6s", vertex.getKey());
      }
      System.out.printf("|%-15s|%-15s\n", "Enqueue", "Dequeue");
   }

   public void printCurrentState(int iterationNumber)
   {
      this.indegreeSum = 0;
      System.out.printf("%-10d| ", iterationNumber);
      for (Map.Entry<String, Vertex> vertex : graph.entrySet())
      {
         System.out.printf("%6d", vertex.getValue().indegree);
         this.indegreeSum += vertex.getValue().indegree;
      }
   }

   public void topSort() throws Exception
   {
      printHeader();
      printCurrentState(1);

      System.out.print("|");
      String comma = "";
      int spacing = 15;
      Queue<Vertex> q = new LinkedList<>();
      int counter = 0;
      for (Map.Entry<String, Vertex> vertex : graph.entrySet())
      {
         if (vertex.getValue().indegree == 0)
         {
            q.add(vertex.getValue());
            System.out.printf("%-3s", comma + vertex.getValue().name);
            spacing -= 3;
            comma = ", ";
         }
      }
      System.out.printf("%" + spacing + "s|", " ");
      comma = "";
      while (!q.isEmpty())
      {
         Vertex v = q.remove();// will throw exception if attempt to remove from empty list
         System.out.printf("%-3s\n", comma + v.name);
         v.topNum = ++counter;

         comma = "";
         spacing = 15;
         StringBuilder stringBuilder = new StringBuilder();
         //     for each Vertex w adjacent to v
         for (Vertex.Tuple<String, Integer> w : v.adjList)
         {
            if (--graph.get(w.x).indegree == 0)
            {//decrement indegree and if 0 add to q
               q.add(graph.get(w.x));
               //System.out.printf("%-3s", comma + graph.get(w.x).name);

               stringBuilder.append(String.format("%s", comma + graph.get(w.x).name));

               spacing -= comma.length() + 1;
               comma = ", ";
            }
         }
         if (indegreeSum == 0)
         {
            break;
         }
         printCurrentState(counter + 1);
         System.out.print("|");
         System.out.printf("%s%" + spacing + "s|", stringBuilder.toString(), " ");
         comma = "";
      }
      if (counter != graph.size())
      {
         throw new Exception("cycle found!!");
      }
   }

   public void unweighed()
   {
      Queue<Vertex> q = new LinkedList<>();
      Vertex s = graph.get("A");
      s.distance = 0;
      q.add(s);
      String comma = "";
      StringBuilder stringBuilder = new StringBuilder( comma + s.name );
      System.out.print(" Initial State\n");
      System.out.print("-----------------------------------------\n");
      System.out.printf("%-6s%-10s%-10s%-10s\n", "v", "known", "dist", "parent");
      System.out.print("-----------------------------------------\n");
      printGraph();
      System.out.printf("Q     %s\n",stringBuilder.toString());
      while (!q.isEmpty())
      {
         Vertex vertex = q.remove();
         System.out.printf("%15s Dequeued\n",vertex.name);
         System.out.print("-----------------------------------------\n");
         System.out.printf("%-6s%-10s%-10s%-10s\n", "v", "known", "dist", "parent");
         System.out.print("-----------------------------------------\n");
         StringBuilder stringBuilder2 = new StringBuilder(  );
         for (Vertex.Tuple<String, Integer> adjacentVertices : vertex.adjList)
         {
            Vertex adjacentVertex = graph.get(adjacentVertices.x);
            if (adjacentVertex.distance == Integer.MAX_VALUE)
            {
               adjacentVertex.distance = vertex.distance + 1;
               adjacentVertex.path = vertex;
               q.add(adjacentVertex);
               stringBuilder2.append(comma);
               stringBuilder2.append(adjacentVertex.name);
               comma = ", ";
            }
         }
         comma = "";
         printGraph();
         System.out.printf("Q     %s\n",stringBuilder2.toString());
      }


   }
   public void printGraph(){

      for (Map.Entry<String, Vertex> vertex : graph.entrySet()){
         Vertex value = vertex.getValue();
         System.out.printf("%-6s%-10s%-10s%-10s\n",
               value.name,
               value.known,
               (value.distance == Integer.MAX_VALUE)?"Inf":value.distance,
               (value.path == null)?"0":value.path.name
         );

      }
   }
}
