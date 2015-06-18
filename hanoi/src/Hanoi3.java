/**
 * Solve the Towers of Hanoi puzzle recursively.
 * Print the pushes and pops of the parameters.
 * 
 * CS 146
 * Summer 2015
 * San Jose State University
 * Instructor: Ron Mak
 * 
 * @author rmak
 */
public class Hanoi3 
{
    private static final char A = 'A'; // initial source
    private static final char B = 'B'; // initial temp
    private static final char C = 'C'; // initial destination
    
    private static int count = 0;
  
    /**
     * Move a disk:
     * @param from pin
     * @param to pin
     */
    private static void move(char from, char to)
    {
        System.out.printf("%2d: Move disk from %c to %c.\n", ++count, from, to);
    }
    
    /**
     * Solve the puzzle recursively.
     * @param n number of pins
     * @param source pin
     * @param destination pin
     * @param temp pin
     */
    private static void solve(int n, char source, char destination, char temp)
    {
        System.out.printf("PUSH %2d%2s%2s%2s\n", n, source, destination, temp);
            
        if (n > 0) {
            solve(n-1, source, temp, destination);
            move(source, destination);
            solve(n-1, temp, destination, source);
        }
            
        System.out.printf("POP  %2d%2s%2s%2s\n", n, source, destination, temp);
    }
    
    /**
     * Main
     * @param args (not used)
     */
    public static void main(String args[])
    {
        int n = 3;
        System.out.printf("Solve for %d disks:\n\n", n);
        solve(n, A, C, B);
    }
}
