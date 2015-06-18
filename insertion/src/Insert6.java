import java.util.ArrayList;

public class Insert6
{
    private static RandomGenerator generator;
    private static BinarySearch searcher;
    private static ArrayList<Integer> intList;
    
    private static void print(String label, ArrayList<Integer> list)
    {
        System.out.println(label);
        
        for (int i = 0; i < list.size(); i++) {
            String marker = (i > 0) && (list.get(i) < list.get(i-1)) ? "*" : " ";
            System.out.printf("%2d:%2d%2s\n", i, list.get(i), marker);
        }
    }
    
    public static void main(String[] args) 
    {
        int n = 20;     // size of list
        int count = 5;  // count of insertions
        int many = 10;  // how many threads
        
        generator = new RandomGenerator(10*n);
        searcher = new BinarySearch();
        Inserter inserters[] = new Inserter[many];
        intList = generator.generateSortedArray(n);        
            
        print("Before insertions:", intList);
        
        for (int id = 0; id < many; id++) {
            inserters[id] = new Inserter(id, count);
            inserters[id].start();
        }
        
        try {
            for (int id = 0; id < many; id++) {
                inserters[id].join();
            }
        }
        catch (InterruptedException ignore) {}
        
        print("After insertions:", intList);
    }
    
    private static class Inserter extends Thread
    {
        private int id;
        private int count;
        
        public Inserter(int id, int count)
        {
            this.id = id;
            this.count = count;
        }
        
        public void run()
        {
            doInserts();
        }
                
        public void doInserts()
        {
            for (int i = 1; i <= count; i++) {
                int value = generator.generateInt();
                int index;
                
                // Critical section: Only one thread at a time in here!
                synchronized(intList) {
                    index = searcher.binarySearch(intList, value);
                    
                    if (index <= 0) {
                        index = -index;
                        if (value > intList.get(index)) index++;
                    }
                    
                    intList.add(index, value);
                }
                
                System.out.printf("#%2d inserted: %2d:%2d\n", id, index, value);
                
                try {
                    Thread.sleep(10*generator.generateInt());
                }
                catch (InterruptedException ignore) {}
            }
        }
    }
}
