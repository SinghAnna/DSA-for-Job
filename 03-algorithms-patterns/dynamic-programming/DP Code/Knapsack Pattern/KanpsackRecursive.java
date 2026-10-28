import java.util.Arrays;

public class KanpsackRecursive{


     private  static  int knapsackRecur(int i, int capacity,int[] wt, int[] val, int[][] dp){

            if(i < 0){
                return 0;
            }

            if (dp[i][capacity] != -1) {
                return dp[i][capacity];
            }

            int skip = knapsackRecur(i-1, capacity, wt, val,dp);

            int take = 0;
            if (wt[i] <= capacity) {
                take = val[i] + knapsackRecur(i-1, capacity - wt[i], wt, val, dp);
            }

            return dp[i][capacity]  = Math.max(skip, take);

     }
    public static void main(String[] args) {
        int[] wt = {1,3,4,5};
        int[] val = {1,4,5,7};
        int W = 7;
        int[][] dp = new int[wt.length][W + 1];

        for(int i = 0; i < wt.length; i++){
            Arrays.fill(dp[i], -1);
        }
        int result = knapsackRecur(wt.length -1,W,wt,val, dp);
        System.out.println(result);
    }
}