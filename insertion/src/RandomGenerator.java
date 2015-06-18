import java.util.ArrayList;
import java.util.Random;

public class RandomGenerator 
{
    private int max;
    private Random generator = new Random();
    
    public RandomGenerator(int max)
    {
        this.max = max;
        this.generator = new Random();
    }
    
    public int generateInt()
    {
        return generator.nextInt(max);
    }
    
    public ArrayList<Integer> generateSortedArray(int n)
    {
        ArrayList<Integer> randInts = new ArrayList<>(n);
        
        for (int i = 0; i < n; i++) {
            randInts.add(generator.nextInt(max));
        }
        
        for (int i = 0; i < randInts.size()-1; i++) {
            for (int j = i+1; j < randInts.size(); j++) {
                if (randInts.get(j) < randInts.get(i)) {
                    int temp = randInts.get(i);
                    randInts.set(i, randInts.get(j));
                    randInts.set(j, temp);
                }
            }
        }
        
        return randInts;
    }
}