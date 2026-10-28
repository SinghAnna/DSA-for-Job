public class KnapsackItrative {

    static int knapsackItrat(int W, int[] wt, int[] val){

        int[][] dp = new int[wt.length + 1][W +1];

        for(int i = 1; i <= wt.length; i++){
            for(int w = 1; w <= W; w++){

                int skip = dp[i-1][w];

                int take = 0;

                if(wt[i-1] <= w){
                    take = val[i-1] + dp[i-1][w-wt[i-1]];
                }

                dp[i][w] = Math.max(take, skip);

            }
        }

        return dp[wt.length][W];
    }

    public static void main(String[] args) {
        int[] wt = {1,3,4,5};
        int[] val = {1,4,5,7};
        int W = 7;

        int res = knapsackItrat( W ,wt,val);
        System.out.println(res);
    }
}
