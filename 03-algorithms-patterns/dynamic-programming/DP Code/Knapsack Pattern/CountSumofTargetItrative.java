public class CountSumofTargetItrative {

    static int countSumofTargetItrative(int[] arr, int sum){

        int[][] dp = new int[arr.length + 1][sum  + 1];

        for(int i = 0; i <= arr.length; i++){
            dp[i][0] = 1;
        }

        for(int i = 1; i <= arr.length; i++){
            for(int s = 1; s <= sum; s++){

                int skip = dp[i-1][s];
                int take = 0;
                if(arr[i-1] <= s){
                    take = dp[i-1][s-arr[i-1]];
                }

                dp[i][s] = take + skip;
            }
        }


        return dp[arr.length][sum];
    }
    public static void main(String[] args) {
        int[] arr = {2,3,5,6,8,10};
        int sum = 10;
        
        int res = countSumofTargetItrative(arr, sum);

        System.out.println(res);
    }
}
