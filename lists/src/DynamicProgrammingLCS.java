package lcs;

public class DynamicProgrammingLCS
{
    /**
     * Build the table for dynamic programming.
     * @param X the first string
     * @param Y the second string
     * @return the table
     */
    private static int[][] buildTable(String X, String Y)
    {
        int m = X.length();
        int n = Y.length();
        int L[][] = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    L[i][j] = 0;
                }
                else if (X.charAt(i-1) == Y.charAt(j-1)) {
                    L[i][j] = L[i-1][j-1] + 1;
                }
                else {
                    L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
                }
            }
        }
        
        return L;
    }
    
    /**
     * Print the table
     * @param L the table to print
     * @param X the first string
     * @param Y the second string
     */
    public static void printTable(int L[][], String X, String Y)
    {
        int m = X.length();
        int n = Y.length();

        System.out.print("     ");
        for (int j = 0; j <= n; j++) System.out.printf("%2d", j);
        
        System.out.print("\n       ");
        for (int j = 0; j < n; j++) System.out.printf("%2s", Y.charAt(j));
        
        System.out.print("\n    +");
        for (int j = 0; j <= n; j++) System.out.print("--");
        System.out.println();
        
        for (int i = 0; i <= m; i++) {
            System.out.printf("%d ", i);
            
            if (i > 0) System.out.printf("%s |", X.charAt(i-1));
            else       System.out.print("  |");
            
            for (int j = 0; j <= n; j++) {
                System.out.printf("%2d", L[i][j]);
            }
            
            System.out.println();
        }
    }

    /**
     * Generate the LCS from the table.
     * @param L the table
     * @param X the first string
     * @param Y the second string
     * @return the LCS
     */
    private static String lcs(int L[][], String X, String Y)   
    {
        int m = X.length();
        int n = Y.length();
        String lcs = new String();

        // Start from the rightmost bottommost corner
        // and one by one store characters backwards in lcs.
        int i = m;
        int j = n;
        while (i > 0 && j > 0) {
            // If the current characters in X and Y are the same,
            // then prepend that common character to the LCS.
            if (X.charAt(i-1) == Y.charAt(j-1)) {
                lcs = X.charAt(i-1) + lcs;
                i--;
                j--;
            }

            // If not the same, then find the larger of the two 
            // table values and go in the direction of larger value.
            else if (L[i-1][j] > L[i][j-1]) i--;
            else                            j--;
        }

        return lcs;
    }

    public static void main(String args[])
    {
        String X[] = { "AGGTGAB", "CATCGA" };
        String Y[] = { "GXTXAYB", "GTACCGTCA" };

        for (int i = 0; i < X.length; i++) {
            String x = X[i];
            String y = Y[i];
            int L[][] = buildTable(x, y);
            printTable(L, x, y);
            
            System.out.printf("\nThe LCS of %s and %s is %s\n\n", x, y,
                              lcs(L, x, y));
        }
    }
}
