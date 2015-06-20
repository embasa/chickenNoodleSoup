public class BinarySearch
{
    private static final int NOT_FOUND = -1;

    public <AnyType extends Comparable<? super AnyType>>
        int binarySearch(AnyType elements[], AnyType x, boolean flag)
    {
        int low = 0;
        int high = elements.length - 1;
        int count = 0;

        while (low <= high) {
            int mid = (low + high)/2;
            int compare = x.compareTo(elements[mid]);
            ++count;
            
            if (compare < 0) {
                high = mid - 1;
            }
            else if (compare > 0) {
                low = mid + 1;
            }
            else {
                return flag ? mid : count; // found!
            }
        }
        
        return flag ? NOT_FOUND : count;
    }
}
