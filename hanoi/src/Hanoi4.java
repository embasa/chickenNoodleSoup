import java.util.Stack;

/**
 * Solve the Towers of Hanoi puzzle nonrecursively 
 * using the built-in Stack class.
 * 
 * CS 146
 * Summer 2015
 * San Jose State University
 * Instructor: Ron Mak
 * 
 * @author rmak
 */
public class Hanoi4 
{
    private static final char A = 'A'; // initial source
    private static final char B = 'B'; // initial temp
    private static final char C = 'C'; // initial destination
    
    private static Stack<Parms> stack = new Stack<>();
    
    private static int n     = 3;
    private static int count = 0;
  
    /**
     * Move a disk:
     * @param from pin
     * @param to pin
     */
    private void move(char from, char to)
    {
        System.out.printf("%2d: Move disk from %c to %c.\n", ++count, from, to);
    }
    
    /**
     * Parameters to be stacked.
     */
    private class Parms
    {
        public int n;
        public char source;
        public char destination;
        public char temp;
        
        public Parms(int n, char source, char destination, char temp)
        {
            this.n = n;
            this.source = source;
            this.destination = destination;
            this.temp = temp;
        }
    }
    
    /**
     * Push parameters onto the stack.
     * @param p the parameters object
     * @return the parameters object
     */
    private Parms push(Parms p)
    {
        stack.push(p);
        System.out.printf("PUSH %2d%2s%2s%2s\n", 
                          p.n, p.source, p.destination, p.temp);
        return p;
    }
    
    /**
     * Pop parameters off the stack.
     * @return the popped-off parameters object
     */
    private Parms pop()
    {
        Parms p = stack.pop();
        System.out.printf("POP  %2d%2s%2s%2s\n", 
                          p.n, p.source, p.destination, p.temp);
        return p;
    }
    
    /**
     * Solve the puzzle nonrecursively with an explicit stack. 
     */
    private void solveNonrecursively()
    {
        System.out.printf("Solve nonrecursively for %d disks:\n\n", n);
        push(new Parms(n, A, C, B));
        
        do {
            Parms top = stack.peek();
            
            if (top.n > 0) {
                push(new Parms(top.n-1, top.source, top.temp, top.destination));
            }
            else {
                pop();
                
                if (!stack.empty()) {
                    top = stack.peek();
                    move(top.source, top.destination);
                    pop();
                    push(new Parms(top.n-1, top.temp, top.destination, top.source));
                }
            }
        } while (!stack.empty());
    }
    
    /**
     * Main
     * @param args (not used)
     */
    public static void main(String args[])
    {
        Hanoi4 hanoi = new Hanoi4();
        hanoi.solveNonrecursively();
    }
}
