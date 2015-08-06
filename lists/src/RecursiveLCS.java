package lcs;

public class RecursiveLCS
{
    /**
     * Recursively compute the length of the LCS of strings x and y.
     * @param X the first string
     * @param Y the second string
     * @param m index into the first string
     * @param n index into the second string
     * @return the LCS length
     */
    private static int lcsLength(String X, String Y, int m, int n)
    {
        if ((m == 0) || (n == 0)) {
            return 0;
        }
        else if (X.charAt(m-1) == Y.charAt(n-1)) {
            return 1 + lcsLength(X, Y, m-1, n-1);
        }
        else {
            return Math.max(lcsLength(X, Y, m, n-1), 
                            lcsLength(X, Y, m-1, n));
        }
    }
    
    public static void main(String args[])
    {
        String X[] = {"AGGTGAB",  "CATCGA"};
        String Y[] = {"GXTXAYB", "GTACCGTCA"};
        
        for (int i = 0; i < X.length; i++) {
            String x = X[i];
            String y = Y[i];
            System.out.printf("The length of the LCS of %s and %s is %d\n",
                              x, y, lcsLength(x, y, x.length(), y.length()));
        }
    }
}
