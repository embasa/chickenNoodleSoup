/**
 * Created by bruno on 8/2/15.
 * --- chicken ---
 */
public class Edge implements Comparable
{
   private final String u;
   private final String v;
   private final int weight;

   public Edge(String u, String v, int weight){
      this.u = u;
      this.v = v;
      this.weight = weight;
   }

   public String getU(){
      return u;
   }

   public String getV(){
      return v;
   }

   public int getWeight(){
      return weight;
   }

   @Override
   @SuppressWarnings("NullableProblems")
   public int compareTo(Object o)
   {
      return ((Integer)this.weight).compareTo(((Edge)o).weight);
   }
}
