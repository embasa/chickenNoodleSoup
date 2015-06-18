import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Compare the performance of solving the Towers of Hanoi puzzle recursively 
 * against solving it nonrecursively using the built-in Stack class.
 * 
 * CS 146
 * Summer 2015
 * San Jose State University
 * Instructor: Ron Mak
 * 
 * @author rmak
 */
public class Hanoi5
{
    private static final char A = 'A'; // initial source
    private static final char B = 'B'; // initial temp
    private static final char C = 'C'; // initial destination
    
    private static int n          = 3;
    private static int count      = 0;
    private static int iterations = 1;

    private static Stack<Parms> stack = new Stack<>();

    private static boolean printMoves     = false;
    private static boolean printPushPop   = false;
    private static boolean firstIteration = true;
  
    /**
     * Move a disk:
     * @param from pin
     * @param to pin
     */
    private void move(char from, char to)
    {
        ++count;
        if (firstIteration && printMoves) {
            System.out.printf("%2d: Move disk from %c to %c.\n", 
                              count, from, to);
        }
    }
    
    /**
     * Solve the puzzle recursively.
     * @param n number of pins
     * @param source pin
     * @param destination pin
     * @param temp pin
     */
    private void solve(int n, char source, char destination, char temp)
    {
        if (firstIteration && printPushPop) {
            System.out.printf("PUSH %2d%2s%2s%2s\n", n, source, destination, temp);
        }
        
        if (n > 0) {
            solve(n-1, source, temp, destination);
            move(source, destination);
            solve(n-1, temp, destination, source);
        }
        
        if (firstIteration && printPushPop) {
            System.out.printf("POP  %2d%2s%2s%2s\n", n, source, destination, temp);
        }
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
        if (firstIteration && printPushPop) {
            System.out.printf("PUSH %2d%2s%2s%2s\n", 
                              p.n, p.source, p.destination, p.temp);
        }
        return p;
    }
    
    /**
     * Pop parameters off the stack.
     * @return the popped-off parameters object
     */
    private Parms pop()
    {
        Parms p = stack.pop();
        if (firstIteration && printPushPop) {
            System.out.printf("POP  %2d%2s%2s%2s\n", 
                              p.n, p.source, p.destination, p.temp);
        }
        return p;
    }
    
    /**
     * Repeatedly solve the puzzle recursively 
     * and keep track of the elapsed times.
     */
    private void solveRecursively()
    {
        System.out.printf("\nSolve recursively for %d disks:\n\n", n);
        
        ArrayList<Long> times = new ArrayList<>();
        firstIteration = true;
        
        for (int i = 1; i <= iterations; i++) {
            long now = System.currentTimeMillis();
            
            count = 0;
            solve(n, A, C, B);
            
            long elapsed = System.currentTimeMillis() - now;
            System.out.printf("%3d: %d moves, %d ms\n", i, count, elapsed);
            times.add(elapsed);
            
            firstIteration = false;
        }
        
        printStats(times);
    }
    
    /**
     * Repeatedly solve the puzzle nonrecursively 
     * and keep track of the elapsed times.
     */
    private void solveNonrecursively()
    {
        System.out.printf("\nSolve nonrecursively for %d disks:\n", n);
        //stack.ensureCapacity((int) Math.pow(2, n));
        System.out.printf("Stack capacity: %d\n\n", stack.capacity());
        
        ArrayList<Long> times = new ArrayList<>();
        firstIteration = true;
        
        for (int i = 1; i <= iterations; i++) {
            long now = System.currentTimeMillis();
            
            count = 0;
            
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
            
            long elapsed = System.currentTimeMillis() - now;
            System.out.printf("%3d: %d moves, %d ms\n", i, count, elapsed);
            times.add(elapsed);
            
            firstIteration = false;
        }
        
        printStats(times);
    }
    
    /**
     * Main
     * @param args command-line arguments
     */
    public static void main(String args[])
    {
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg.equals("-n")) {
                n = Integer.parseInt(args[++i]);
            }
            else if (arg.equals("-i")) {
                iterations = Integer.parseInt(args[++i]);
            }
            else if (arg.equals("-pm")) printMoves   = true;
            else if (arg.equals("-pp")) printPushPop = true;
        }
        
        Hanoi5 hanoi = new Hanoi5();
        
        hanoi.solveRecursively();
        hanoi.solveNonrecursively();
    }
    
    /**
     * Print iteration times.
     * @param times array of times.
     */
    private static void printStats(ArrayList<Long> times)
    {
        Collections.sort(times);  // sort the times
        
        int size = times.size();
        int half = size/2;        // midpoint index for the median time
        
        long   minTime = times.get(0);
        long   maxTime = times.get(size-1);
        double median  = half << 1 < size 
                            ? times.get(half)
                            : (times.get(half-1) + times.get(half))/2.0;
        
        System.out.println();
        System.out.printf("Mininum time: %3d   ms\n", minTime);
        System.out.printf("Maximum time: %3d   ms\n", maxTime);
        System.out.printf("Median  time: %5.1f ms\n", median);
    }
}
