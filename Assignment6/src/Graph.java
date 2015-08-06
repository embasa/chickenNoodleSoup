import java.util.*;

/**
 * This class is for testing graphing algorithms
 * topological sort should be shortened in length
 * Maybe have to use a lot more helper methods.
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

   @SuppressWarnings("unused")
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
   @SuppressWarnings( "unused" )
   public void topSort() throws Exception
   {
      printHeader();
      printCurrentState(1);

      Queue<Vertex> order = new LinkedList<>();
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
      System.out.printf( "%" + spacing + "s|", " " );
      comma = "";
      while (!q.isEmpty())
      {
         Vertex vertex = q.remove();// will throw exception if attempt to remove from empty list
         System.out.printf("%-3s\n", comma + vertex.name);
         vertex.topNum = ++counter;
         order.add(vertex);
         comma = "";
         spacing = 15;
         StringBuilder stringBuilder = new StringBuilder();
         //     for each Vertex w adjacent to v
         for (Vertex.Edge<String, Integer> adjacentVertexEdge : vertex.adjList)
         {
            Vertex adjacentVertex = graph.get(adjacentVertexEdge.name);
            if (--adjacentVertex.indegree == 0)
            {//decrement indegree and if 0 add to q
               q.add(adjacentVertex);
               //System.out.printf("%-3s", comma + graph.get(w.x).name);

               stringBuilder.append(String.format("%s", comma + adjacentVertex.name));

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
      comma = "";
      System.out.print( "Ordering from s: " );
      while(!order.isEmpty())
      {
         System.out.print( comma + order.remove().name );
         comma = ", ";
      }
      System.out.println();
   }

   /**
    * Had to change the Queue into a LinkedList in order to be
    * able to print it.
    */
   @SuppressWarnings("unused")
   public void unweighed()
   {
      LinkedList<Vertex> q = new LinkedList<>();
      Vertex s = graph.get("A");
      s.distance = 0;
      s.known = true;
      q.addLast(s);
      String comma = "";
      System.out.print(" Initial State\n");
      System.out.print("---------------------------------\n");
      System.out.printf("%-6s%-10s%-10s%-10s\n", "v", "known", "dist", "parent");
      System.out.print("---------------------------------\n");
      printGraph();
      System.out.printf("Q     %s\n", comma + s.name);
      while (!q.isEmpty())
      {
         Vertex vertex = q.removeFirst();
         System.out.printf(" %s Dequeued\n", vertex.name);
         System.out.print("---------------------------------\n");
         System.out.printf("%-6s%-10s%-10s%-10s\n", "v", "known", "dist", "parent");
         System.out.print("---------------------------------\n");
         for (Vertex.Edge<String, Integer> adjacentVertices : vertex.adjList)
         {
            Vertex adjacentVertex = graph.get(adjacentVertices.name);
            if (adjacentVertex.distance == Integer.MAX_VALUE)
            {
               adjacentVertex.distance = vertex.distance + 1;
               adjacentVertex.path = vertex;
               adjacentVertex.known = true;
               q.add(adjacentVertex);
            }
         }
         printGraph();
         System.out.printf("Q     %s\n", toStringList(q));
      }
   }

   public String toStringList(List<Vertex> list){
      if(list.size() == 0){
         return "empty";
      }
      String comma = "";
      @SuppressWarnings("MismatchedQueryAndUpdateOfStringBuilder") StringBuilder stringBuilder = new StringBuilder();
      for(Vertex vertex: list){
         stringBuilder.append(comma);
         stringBuilder.append(vertex.name);
         comma = ", ";
      }
      return stringBuilder.toString();
   }

   public void printGraph()
   {
      Collection<Vertex> collection = graph.values();
      List<Vertex> sortedGraph = new LinkedList<>( collection );
      Collections.sort( sortedGraph );
      for( Vertex value : sortedGraph){

         System.out.printf("%-6s%-10s%-10s%-10s\n",
                 value.name,
                 value.known?"T":"F",
                 (value.distance == Integer.MAX_VALUE) ? "\u221E" : value.distance,
                 (value.path == null) ? "0" : value.path.name
         );
      }
   }

   @SuppressWarnings("unused")
   public void dijkstra()
   {
      Vertex s = graph.get("A");
      s.distance = 0;
      Vertex vertex = getMinUnkownVertex();
      System.out.print(" Initial State\n");
      System.out.print("---------------------------------\n");
      System.out.printf("%-6s%-10s%-10s%-10s\n", "v", "known", "dist", "parent");
      System.out.print("---------------------------------\n");
      printGraph();
      while (vertex != null){
         vertex.known = true;
         for (Vertex.Edge<String, Integer> adjacentVertices : vertex.adjList){
            Vertex adjacentVertex = graph.get(adjacentVertices.name);
            if(!adjacentVertex.known){
               int distanceToAdjacent = adjacentVertices.edgeWeight;
               if(vertex.distance + distanceToAdjacent < adjacentVertex.distance){
                  adjacentVertex.distance = vertex.distance + distanceToAdjacent;
                  adjacentVertex.path = vertex;
               }
            }
         }
         System.out.print("---------------------------------\n");
         System.out.printf("%-6s%-10s%-10s%-10s\n", "v", "known", "dist", "parent");
         System.out.print("---------------------------------\n");
         vertex = getMinUnkownVertex();
         printGraph();
      }
   }

   /**
    * uses linear search to find the smallest unknown vertex.
    * This can be implemented as a priority queue if performance
    * becomes an issue
    * @return a Vertex reference with smallest distance that is
    * unknown.
    */
   private Vertex getMinUnkownVertex(){
      ArrayList<Vertex> unknownVertex = new ArrayList<>();
      for (Map.Entry<String, Vertex> vertex : graph.entrySet()){
         if(!vertex.getValue().known){
            unknownVertex.add(vertex.getValue());
         }
      }

      if(unknownVertex.isEmpty()){
         return null;
      }
      Vertex minVertex = null;
      int minDistance = Integer.MAX_VALUE;
      for(Vertex vertex : unknownVertex){
         if(vertex.distance < minDistance ){
            minVertex = vertex;
            minDistance = vertex.distance;
         }
      }
      return minVertex;
   }

   /**
    * makes a minimum spanning tree from the default settings.
    * This is for all the marbles bitch. distance is shortest
    * path to a known vertex.
    */
   public void primsAlgorithm(){
      List<Vertex> known = new LinkedList<>( );
      Vertex vertex = graph.get( "v1" );
      vertex.distance = 0;
      System.out.print(" Initial State\n");
      System.out.print("---------------------------------\n");
      System.out.printf("%-6s%-10s%-10s%-10s\n", "v", "known", "dist", "parent");
      System.out.print("---------------------------------\n");
      printGraph();
      while (vertex != null){
         vertex.known = true;
         known.add( vertex );
         for (Vertex.Edge<String, Integer> adjacentVertices : vertex.adjList){
            Vertex adjacentVertex = graph.get(adjacentVertices.name);
            if(!adjacentVertex.known){
               adjacentVertex.distance = min(adjacentVertex.distance,adjacentVertex);
               //adjacentVertex.path = vertex;
               //adjacentVertex.known = f;
               /*
               int distanceToAdjacent = adjacentVertices.edgeWeight;
               if(vertex.distance + distanceToAdjacent < adjacentVertex.distance){
                  adjacentVertex.distance = vertex.distance + distanceToAdjacent;
                  adjacentVertex.path = vertex;
               }*/
            }
         }
         System.out.print("---------------------------------\n");
         System.out.printf( "%-6s%-10s%-10s%-10s\n", "v", "known", "dist", "parent" );
         System.out.print( "---------------------------------\n" );
         vertex = nextMinVertex( known );
         if(vertex!= null) {
            System.out.print( "new Vertex: " + vertex.name + "\n" );
         }
         printGraph();
      }
   }

   /**
    * takes weight between current node and adjacent node
    * and the adjacent node. We then test what the minimum
    * weight of an edge is between the unkown vertex and all
    * known vertices
    * @param originalDistance the weight between the vertex adjacent
    * @param adjacent adjacent vertex to original vertex
    * @return the smallest weight of unknown edge to a known edge
    */
   public int min(int originalDistance, Vertex adjacent){
      int minimumWeight = originalDistance;
      for(Vertex.Edge<String,Integer> vertices : adjacent.adjList){
         Vertex vertex = graph.get( vertices.name );
         if(vertex.known){
            if(vertices.edgeWeight < minimumWeight){
               minimumWeight = vertices.edgeWeight;
               adjacent.path = vertex;
            }
         }
      }
      return minimumWeight;
   }

   /**
    * returns a vertex that is connected to the known tree
    * by the shortest edgeWeight.
    * @param knowns List of all known vertexes
    * @return next Vertex to add to known tree
    */
   public Vertex nextMinVertex(List<Vertex> knowns){
      Vertex minVertex=null;// graph.get(vertex.adjList.get( 0 ).name);
      int minDistance = Integer.MAX_VALUE;
      for( Vertex vertex : knowns) {
         for ( int i = 0; i < vertex.adjList.size(); i++ ) {
            Vertex nextVertex = graph.get( vertex.adjList.get( i ).name );
            if ( ! nextVertex.known ) {
               if ( nextVertex.distance < minDistance ) {
                  minVertex = nextVertex;
                  minDistance = nextVertex.distance;
               }
            }
         }
      }
      return minVertex;
   }

   public void kruskalsAlgorithm(){
      Collection<Vertex> collection = graph.values();
      int numVertices = graph.size();
      List<Vertex> graphVertices = new LinkedList<>( collection );
      List<Edge> edges = new ArrayList<>();

      for(Vertex vertex: graphVertices){
         for( Vertex.Edge<String,Integer> edge: vertex.adjList){
            edges.add(new Edge(vertex.name,edge.name,edge.edgeWeight));
         }
      }
      /* NOW THE ALGORITHM ACTUALLY BEGINS!!!!!!!!!!! */
      System.out.println("------------------------------");
      System.out.printf("%-8s%8s%12s\n", "Edge", "Weight", "Action");
      System.out.println("------------------------------");
      DisjSets ds = new DisjSets(numVertices);
      PriorityQueue<Edge> pq = new PriorityQueue<>(edges);
      List<Edge> mst = new ArrayList<>();
      while( mst.size() != numVertices -1){
         Edge e = pq.poll();
         Integer uset = ds.find(Integer.parseInt(e.getU().substring(1)) - 1);//index in arraylist requires minus one
         Integer vset = ds.find(Integer.parseInt(e.getV().substring(1))-1);

         if(uset != vset ){
            mst.add(e);
            ds.union(uset, vset);
            System.out.printf("(%s, %s)%7s%d%6sAccepted\n",e.getU(),e.getV()," ",e.getWeight()," ");
         }else{
            System.out.printf("(%s, %s)%7s%d%6sRejected\n",e.getU(),e.getV()," ",e.getWeight()," ");
         }
      }
   }
}
