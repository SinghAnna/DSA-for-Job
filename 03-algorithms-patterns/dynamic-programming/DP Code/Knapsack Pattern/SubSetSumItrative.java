public class SubSetSumItrative {

   static boolean subsetSumItrative(int[] arr, int sum){
    
        boolean[][] dp = new boolean[arr.length + 1][sum  + 1];

        for(int i = 0; i <= arr.length; i++){
            dp[i][0] = true;
        }

        for(int i = 1; i <= arr.length; i++){
            for(int s = 1; s <= sum; s++){

                boolean skip = dp[i-1][s];

                boolean take = false;

                if(arr[i-1] <= s){
                    take = dp[i-1][s-arr[i-1]];
                }


                dp[i][s] = take || skip;
            }
        }



    return dp[arr.length][sum];
   }

    public static void main(String[] args) {
         int[] arr = {2,3,7,8,10};
        int sum = 11;

        boolean res = subsetSumItrative(arr, sum);

        System.out.println(res);

    }
}
