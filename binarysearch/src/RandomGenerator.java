import java.util.Arrays;
import java.util.Random;

public class RandomGenerator 
{
    private int max;
    private Random generator = new Random();
    
    public RandomGenerator(int max)
    {
        this.max = max;
        this.generator = new Random();
        //this.generator.setSeed(0);
    }
    
    public int generateInt()
    {
        return generator.nextInt(max);
    }
    
    public Integer[] generateSortedArray(int n)
    {
        Integer randInts[] = new Integer[n];
        
        for (int i = 0; i < n; i++) {
            randInts[i] = generator.nextInt(max);
        }
        
        Arrays.sort(randInts);
        return randInts;
    }
}