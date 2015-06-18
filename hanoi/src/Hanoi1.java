public class Hanoi1
{
    private static final char A = 'A'; // initial source
    private static final char B = 'B'; // initial temp
    private static final char C = 'C'; // initial destination
    
    private static int count = 0;
  
    private static void move(char from, char to)
    {
        System.out.printf("%2d: Move disk from %c to %c.\n", ++count, from, to);
    }
    
    private static void solve(int n, char source, char destination, char temp)
    {
        if (n > 0) {
            solve(n-1, source, temp, destination);
            move(source, destination);
            solve(n-1, temp, destination, source);
        }
    }
    
    public static void main(String args[])
    {
        int n = 3;
        System.out.printf("Solve for %d disks:\n\n", n);
        solve(n, A, C, B);
    }
}
