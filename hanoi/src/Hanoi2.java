public class Hanoi2
{
    private static final char A = 'A';
    private static final char B = 'B';
    private static final char C = 'C';
    
    private static int count = 0;
  
    private static void move(char from, char to)
    {
        ++count;
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
        System.out.println("Disks Moves");
        for (int n = 1; n <= 10; n++) {
            count = 0;
            solve(n, A, C, B);
            System.out.printf("%5d %5d\n", n, count);
        }
    }
}
