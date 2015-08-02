import java.util.ArrayList;

/**
 * This class is for Graph class to use to populate graphs
 * Created by bruno on 7/26/15.
 */
public class Vertex implements Comparable
{
   @Override
   @SuppressWarnings( "NullableProblems" )
   public int compareTo( Object o ) {
      return name.compareTo(((Vertex) o).name );
   }

   /**
    * This class is meant to hold a name and edge to use
    * for our adjacency list. Potentially <Y> should implement
    * comparable as to be able to use in Priority Queue
    *
    * @param <X> normally a String that identifies a vertex
    * @param <Y> normally a Integer representing the weight
    *           between this vertex and the vertex who owns
    *           this Edge
    */
   class Edge<X, Y>
   {
      public final X name;
      public final Y edgeWeight;

      public Edge(X name, Y edgeWeight)
      {
         this.name = name;
         this.edgeWeight = edgeWeight;
      }

      /**
       * This method is for the contains() moethod. It matches up the argument with
       * the name value of the Edge to test for equality. Since name is of type
       * String, the object must be tested for match
       *
       * @param object Object being compared to x
       * @return returns true only if object is a String matching the name of calling reference
       */
      @Override
      public boolean equals(Object object)
      {
         return object.getClass() == name.getClass() && name == object;
      }

   }

   int indegree;
   int topNum;
   int distance;
   boolean known;
   Vertex path;
   String name;
   ArrayList<Edge<String, Integer>> adjList;

   /**
    * default constructor
    * @param name a String that identifies this vertex
    *             will be used as key in HashMap.
    */
   public Vertex(String name)
   {
      this.indegree = 0;
      this.name = name;
      this.topNum = -1;// flag value, unassigned
      this.distance = Integer.MAX_VALUE;
      this.path = null;
      this.known = false;
      this.adjList = new ArrayList<>(10);// magical number for min size
   }

   /**
    * adjList is of type Edge, but other is type string.
    * this is fine because equals() is overridden in Edges class
    * suppress SuspiciousMethodCalls warning.
    * @param other a string containing the name of an adjacent
    *              vertex
    * @param weight the weight of the corresponding edge between
    *               pair of vertex
    * @return false if duplicate other, true otherwise
    */
   boolean addAdjacentVertex(String other, Integer weight)
   {
      //noinspection SuspiciousMethodCalls
      if (!adjList.contains(other))
      {
         adjList.add(new Edge<>(other, weight));
         return true;
      }
      return false;
   }

   /**
    * Method for printing if code is ever reused and needs
    * to be debugged.
    */
   @SuppressWarnings("unused")
   public void print()
   {
      for (Edge Edge : adjList)
      {
         System.out.print("    " + Edge.name + " - " + Edge.edgeWeight + "\n");
      }
   }
}
