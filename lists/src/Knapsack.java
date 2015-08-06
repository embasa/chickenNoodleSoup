public class Knapsack
{
    private static final int N =  4;
    private static final int W = 10;

    //added bullshit
    public static void main(String args[])
    {
        int w[] = {0, 6, 3, 4, 2};
        int v[] = {0, 30, 14, 16, 9};
        
        int K[] = new int[W+1];
        K[0] = 0;
        
        System.out.println(" w item weight value K[w]");

        for (int ww = 1; ww <= W; ww++) {
            K[ww] = K[ww-1];

            // Compute K[w] = max{ K[w - w[i]] + v[i] : w[i] <= w }
            int max = 0;
            int item = 0;
            for (int i = 1; i <= N; i++) {
                if (w[i] <= ww) {
                    int value = K[ww - w[i]] + v[i];
                    
                    if (max < value) {
                        max = value;
                        item = i;
                    }
                    
                    K[ww] = max;
                }
            }
            
            System.out.printf("%2d", ww);
            if (item > 0) {
                System.out.printf("%4d %5d %5d %5d\n",
                                  item, w[item], v[item], K[ww]);
            }
            else {
                System.out.printf("%22d\n", K[ww]);
            }
        }

    }

}
