import java.util.Random;

public class ProducerConsumer4 
{
    private static final int QUEUE_CAPACITY =  5;
    private static final int MAX_PRODUCTION = 10;
    
    private int producerCount = 1;
    private int consumerCount = 1;
    private int itemCount = 0;
    
    private QueueMonitor q = null;
    
    public ProducerConsumer4(int producerCount, int consumerCount)
    {
        this.producerCount = producerCount;
        this.consumerCount = consumerCount;
    }
    
    private void test()
    {
        q = new QueueMonitor(QUEUE_CAPACITY);

        for (int id = 1; id <= producerCount; id++) {
            (new Producer(id)).start();
        }

        for (int id = 1; id <= consumerCount; id++) {
            (new Consumer(id)).start();
        }
    }
        
    public static void main(String[] args) 
    {
        int producerCount = Integer.parseInt(args[0]);
        int consumerCount = Integer.parseInt(args[1]);
        
        (new ProducerConsumer4(producerCount, consumerCount)).test();
    }
    
    public class Producer extends Thread
    {
        private int id;
        private Random r = new Random();
        
        public Producer(int id)
        {
            this.id = id;
        }
        
        public void run()
        {
            produce();
        }
        
        private void produce()
        {
            int i = 1;
            
            while (i <= MAX_PRODUCTION) {
                int item = 1000*id + i;
                
                if (q.enqueue(item)) synchronized(System.out) {
                    for (int k = 1; k < id; k++) System.out.print("    ");
                    System.out.printf("%s %d: %4d size = %d\n", 
                                      "Producer", id, item, q.size());
                    ++i;
                }
                else synchronized(System.out) {
                    System.out.println("*** Queue full ***");
                }
                
                try {
                    Thread.sleep(r.nextInt(100));
                }
                catch (InterruptedException ignore) {}
            }
        }
    }
    
    public class Consumer extends Thread
    {
        private int id;
        private Random r = new Random();
        
        public Consumer(int id)
        {
            this.id = id;
        }
        
        public void run()
        {
            consume();
        }
        
        private void consume()
        {
            while (itemCount < producerCount*MAX_PRODUCTION) {
                Integer x = q.dequeue();
                
                if (x != null) synchronized(System.out) {
                    System.out.printf("%50s %d: %4d size = %d count = %d\n", 
                                      "Consumer", id, x, q.size(), ++itemCount);
                }
                else synchronized(System.out) {
                    System.out.printf("%61s\n", "*** Queue empty ***");
                }
                
                try {
                    Thread.sleep(r.nextInt(100));
                }
                catch (InterruptedException ignore) {}
            }
        }
    }
}
