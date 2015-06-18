public class MyQueue
{
    private Integer buffer[];
    private int in = 0;
    private int out = 0;
    private int count = 0;
    private int capacity = 0;
    
    public MyQueue(int capacity)
    {
        this.capacity = capacity;
        buffer = new Integer[capacity];
    }
    
    public int size() { return count; }
    
    public boolean enqueue(Integer x)
    {
        if (count == capacity) {
            return false;
        }
        else {
            buffer[in] = x;
            in = (in + 1)%capacity;
            count++;
            return true;
        }
    }
    
    public Integer dequeue()
    {
        if (count == 0) {
            return null;
        }
        else {
            Integer x = buffer[out];
            out = (out + 1)%capacity;
            count--;
            return x;
        }
    }
}
