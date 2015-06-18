import java.util.ArrayList;

public class BinarySearch 
{
    public int binarySearch(ArrayList<Integer> elements, Integer x)
    {
        int low = 0;
        int high = elements.size() - 1;
        int mid = 0;

        while (low <= high) {
            mid = (low + high)/2;
            int compare = x.compareTo(elements.get(mid));
            
            if (compare < 0) {
                high = mid - 1;
            }
            else if (compare > 0) {
                low = mid + 1;
            }
            else {
                return mid; // found!
            }
        }
        
        return -mid;
    }
}
