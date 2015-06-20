import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A suggested solution to Assignment #1
 * CS 146
 * Summer 2015
 * 
 * @author Ron Mak
 *
 * This solution uses an algorithm that scales linearly
 * with the size of the text. The number and lengths of
 * the names do not matter. The major (one time) startup
 * cost is the creation (by hand) of state transition
 * switch statements.
 */
public class Searcher2
{
    // Names to search for.
    private static final String BORIS  = "Boris Drubetskoy";
    private static final String JOSEPH = "Joseph Bazdeev";
    private static final String MAKAR  = "Makar Alexeevich";
    
    // Accepting states in the matrix.
    private static final int BD = -1;  // recognize Boris Drubetskoy
    private static final int JB = -2;  // recognize Joseph Bazdeev
    private static final int MA = -3;  // recognize Makar Alexeevich
    
    private char ch;              // current input character
    private int  state      = 0;  // current state
    private int  line       = 1;  // current line number
    private int  position   = 0;  // current position in line
    private int  prevLength = 0;  // length of previous line
    
    private static boolean firstIteration = true;
    private static boolean firstName      = true;

    /**
     * Search for names by using the state transition matrix
     * to make a single pass over the entire input file.
     * For optimal performance, the while loop should run
     * as fast as possible.
     * @throws IOException if an I/O error occurs
     */
    private void search() throws IOException
    {
        ch = nextChar();
        
        // Loop until the end of the input file.
        while (ch != 0) {
            
            // Obtain the next state.
            switch (state) {
                case 0: {
                    switch (ch) {
                        case 'B': state =  1; break;
                        case 'J': state = 16; break;
                        case 'M': state = 29; break;
                        default:  state =  0;
                    };
                    break;
                }
                
                // Boris Drubetskoy
                
                case 1: state = ch == 'o' ? 2 : 0; break;
                case 2: state = ch == 'r' ? 3 : 0; break;
                case 3: state = ch == 'i' ? 4 : 0; break;
                case 4: state = ch == 's' ? 5 : 0; break;
                case 5: {
                    switch (ch) {
                        case ' ':
                        case '\n': state = 6; break;
                        default:   state = 0;
                    }
                    break;
                }
                case  6: state = ch == 'D' ?  7 : 0; break;
                case  7: state = ch == 'r' ?  8 : 0; break;
                case  8: state = ch == 'u' ?  9 : 0; break;
                case  9: state = ch == 'b' ? 10 : 0; break;
                case 10: state = ch == 'e' ? 11 : 0; break;
                case 11: state = ch == 't' ? 12 : 0; break;
                case 12: state = ch == 's' ? 13 : 0; break;
                case 13: state = ch == 'k' ? 14 : 0; break;
                case 14: state = ch == 'o' ? 15 : 0; break;
                case 15: state = ch == 'y' ? BD : 0; break;
                
                // Joseph Bazdeev
                
                case 16: state = ch == 'o' ? 17 : 0; break;
                case 17: state = ch == 's' ? 18 : 0; break;
                case 18: state = ch == 'e' ? 19 : 0; break;
                case 19: state = ch == 'p' ? 20 : 0; break;
                case 20: state = ch == 'h' ? 21 : 0; break;
                case 21: {
                    switch (ch) {
                        case ' ':
                        case '\n': state = 22; break;
                        default:   state =  0;
                    }
                    break;
                }
                case 22: state = ch == 'B' ? 23 : 0; break;
                case 23: state = ch == 'a' ? 24 : 0; break;
                case 24: state = ch == 'z' ? 25 : 0; break;
                case 25: state = ch == 'd' ? 26 : 0; break;
                case 26: state = ch == 'e' ? 27 : 0; break;
                case 27: state = ch == 'e' ? 28 : 0; break;
                case 28: state = ch == 'v' ? JB : 0; break;
                
                // Makar Alexeevich
                
                case 29: state = ch == 'a' ? 30 : 0; break;
                case 30: state = ch == 'k' ? 31 : 0; break;
                case 31: state = ch == 'a' ? 32 : 0; break;
                case 32: state = ch == 'r' ? 33 : 0; break;
                case 33: {
                    switch (ch) {
                        case ' ':
                        case '\n': state = 34; break;
                        default:   state =  0;
                    }
                    break;
                }
                case 34: state = ch == 'A' ? 35 : 0; break;
                case 35: state = ch == 'l' ? 36 : 0; break;
                case 36: state = ch == 'e' ? 37 : 0; break;
                case 37: state = ch == 'x' ? 38 : 0; break;
                case 38: state = ch == 'e' ? 39 : 0; break;
                case 39: state = ch == 'e' ? 40 : 0; break;
                case 40: state = ch == 'v' ? 41 : 0; break;
                case 41: state = ch == 'i' ? 42 : 0; break;
                case 42: state = ch == 'c' ? 43 : 0; break;
                case 43: state = ch == 'h' ? MA : 0; break;
            }
            
            // Did we find a name to print?
            if (state < 0) {
                if (firstIteration) printName();
                state = 0;
            }
            
            ch = nextChar();
        }
    }
    
    /*
     * Map an input character to its column index
     * of the state transition matrix.
     */
    private int index(char ch)
    {
        switch (ch) {
            case 'A'  : return 1;
            case 'B'  : return 2;
            case 'D'  : return 3;
            case 'J'  : return 4;
            case 'M'  : return 5;
            case 'a'  : return 6;
            case 'b'  : return 7;
            case 'c'  : return 8;
            case 'd'  : return 9;
            case 'e'  : return 10;
            case 'h'  : return 11;
            case 'i'  : return 12;
            case 'k'  : return 13;
            case 'l'  : return 14;
            case 'o'  : return 15;
            case 'p'  : return 16;
            case 'r'  : return 17;
            case 's'  : return 18;
            case 't'  : return 19;
            case 'u'  : return 20;
            case 'v'  : return 21;
            case 'x'  : return 22;
            case 'y'  : return 23;
            case 'z'  : return 24;
            case ' '  : return 25;
            case '\n' : return 26;           
            default   : return 0;
        }
    }
    
    /**
     * Print a name that was just found.
     */
    private void printName()
    {
        // Header line.
        if (firstName) {
            System.out.printf("%5s  %8s  %s\n", 
                              "LINE", "POSITION", "NAME");
            firstName = false;
        }
        
        // The accepting state determines which name was found.
        String name = null;
        switch (state) {
            case BD : name = BORIS;  break;
            case JB : name = JOSEPH; break;
            case MA : name = MAKAR;  break;
        }
        
        int lin = line;
        int pos = position - name.length();
        
        // The name started at the end of the previous line if pos < 0.
        if (pos < 0) {
            lin--;
            pos = prevLength + pos;
        }
        
        System.out.printf("%5d  %8d  %s\n", lin, pos+1, name);
    }

    private static final int BUFFER_SIZE = 8*1024;  // input buffer size
    
    private int readLength = BUFFER_SIZE;  // size of each read
    private int bufferx    = BUFFER_SIZE;  // input buffer index
    
    private BufferedReader in = null;               // input file reader
    private char buffer[] = new char[BUFFER_SIZE];  // input buffer

    /**
     * @return the next character from the input file
     * @throws IOException if an I/O error occurred
     */
    private char nextChar() throws IOException
    {
        char ch = 0;  // null character
        
        // If we're done with characters in the input buffer,
        // read in the next BUFFER_SIZE batch of characters.
        if (bufferx == readLength) {
            readLength = in.read(buffer, 0, BUFFER_SIZE);
            if (readLength <= 0) return 0;  // end of file
            bufferx = 0;
        }

        // Obtain the next character from the input buffer.
        // Special processing at the end of each line.
        ch = buffer[bufferx++];
        if (ch == '\n') {
            prevLength = position + 1;  // length of line just completed
            position = 0;
            line++;
        }
        else {
            position++;
        }
        
        return ch;
    }
    
    /**
     * Start to search an input file. Open the input file,
     * do the search, and then close the file.
     * @param filePath path of the input file
     * @throws IOException if an I/O error occurred
     */
    private void go(String filePath) throws IOException
    {
        try {
            in = new BufferedReader(new FileReader(filePath));
            search();
        }
        finally {
            if (in != null) in.close();
        }
    }
    
    /**
     * Main
     * @param args Arg 0: input file path; 
     *             Arg 1: processor speed in GHz    @Override
     *             Arg 2: (optional) number of iterations (default 1)
     */
    public static void main(String args[])
    {
        ArrayList<Long> times = new ArrayList<Long>();  // execution times
                    
        if (args.length < 2) {
            System.out.println("Usage: java Searcher <file> <speed> <iterations>");
            System.out.println("where <file> is the file to search,");
            System.out.println("      <speed> is your processor speed in GHz (ex: 2.3)");
            System.out.println("      <iterations> is the number of iterations (default 1)");
            System.exit(0);
        }

        try {
            double speed = Double.parseDouble(args[1]);
            int iterations = args.length > 2
                                 ? Integer.parseInt(args[2])
                                 : 1;
            
            // Do a complete search each time through the loop.
            // Time how long each iteration took.
            for (int i = 1; i <= iterations; i++) {
                long start = System.currentTimeMillis();
                
                // Kick off the searcher.
                (new Searcher2()).go(args[0]);

                long elapsed = System.currentTimeMillis() - start;
                times.add(elapsed);
                if (firstIteration) System.out.println();
                System.out.printf("Iteration %3d: %3d ms\n", 
                                  i, elapsed);
            
                firstIteration = false;
            }
            
            printStats(times, speed);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /*
     * Print the timing statistics from each iteration.
     * @param the list of times
     */
    private static void printStats(ArrayList<Long> times, double speed)
    {
        Collections.sort(times);  // sort the times
        
        int size = times.size();
        int half = size/2;        // midpoint index for the median time
        
        long   minTime = times.get(0);
        long   maxTime = times.get(size-1);
        double median  = half << 1 < size 
                            ? times.get(half)
                            : (times.get(half-1) + times.get(half))/2.0;
        
        System.out.printf("\nProcessor speed: %3.1f GHz\n\n", speed);
        System.out.printf("Mininum time: %3d   ms, performance %6.1f\n", 
                          minTime, minTime*speed);
        System.out.printf("Maximum time: %3d   ms, performance %6.1f\n",
                          maxTime, maxTime*speed);
        System.out.printf("Median  time: %5.1f ms, performance %6.1f\n", 
                          median, median*speed);
    }
}