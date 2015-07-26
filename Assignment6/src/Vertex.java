import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by bruno on 7/26/15.
 */
public class Vertex
{
   class Tuple<X, Y> implements Comparable<X>
   {
      public final X x;
      public final Y y;

      public Tuple(X x, Y y)
      {
         this.x = x;
         this.y = y;
      }

      /**
       * Text if object is equal to x value
       *
       * @param object Object being compared to x
       * @return
       */
      public boolean equals(Object object)
      {
         return object.getClass() == x.getClass() && x == object;
      }

      public int compareTo(X x)
      {
         return 0;
      }
   }

   int indegree;
   int topNum;
   int distance;
   boolean known;
   Vertex path;
   private final int CAPACITY = 10;
   String name;
   ArrayList<Tuple<String, Integer>> adjList;

   public Vertex(String name)
   {
      this.indegree = 0;
      this.name = name;
      this.topNum = -1;// flag value, unassigned
      this.distance = Integer.MAX_VALUE;
      this.path = null;
      this.known = false;
      this.adjList = new ArrayList<>(CAPACITY);
   }

   boolean addAdjacentVertex(String other, Integer weight)
   {
      if (!adjList.contains(other))
      {
         adjList.add(new Tuple<>(other, weight));
         return true;
      }
      return false;
   }

   public void print()
   {
      for (Tuple tuple : adjList)
      {
         System.out.print("    " + tuple.x + " - " + tuple.y + "\n");
      }
   }
}
