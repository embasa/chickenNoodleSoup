import java.io.IOException;

public class ProducerConsumer1 
{
    private static final int QUEUE_CAPACITY = 5;
    
    private MyQueue q = null;
    
    private void test()
    {
        System.out.println("p to produce, c to consume, q to quit");
        
        int i;
        int item = 1000;
        q = new MyQueue(QUEUE_CAPACITY);
        
        try {
            while ((i = System.in.read()) != -1) {
                char command = (char) i;
                
                switch (command) {
                    case 'p': {
                        if (q.enqueue(item)) {
                            System.out.printf("Producer: %2d size = %d\n", 
                                              item++, q.size());
                        }
                        else {
                            System.out.println("*** Queue full ***");
                        }
                        break;
                    }
                    
                    case 'c': {
                        Integer x = q.dequeue();
                        if (x != null) {
                            System.out.printf("%40s %2d size = %d\n", 
                                              "Consumer", x, q.size());
                        }
                        else {
                            System.out.printf("%51s\n", "*** Queue empty ***");
                        }
                        break;
                    }
                    
                    case 'q': {
                        System.out.println("Quit.");
                        return;
                    }
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }    
    }
    
    public static void main(String[] args) 
    {
        (new ProducerConsumer1()).test();
    }
}
