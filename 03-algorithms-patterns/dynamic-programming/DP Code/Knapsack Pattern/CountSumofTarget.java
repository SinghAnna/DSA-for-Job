import java.util.Arrays;

public class CountSumofTarget {

    static int[][] dp;

    static int countSumofTarget(int[] arr, int sum, int n, int[][] dp){
  
      if (n < 0) {
            return sum == 0 ? 1 : 0;
        }

        if (dp[n][sum] != -1) {
            return dp[n][sum];
        }

     int skip = countSumofTarget(arr, sum, n-1,dp);
     int take = 0;
     if(arr[n] <= sum){
        take = countSumofTarget(arr, sum-arr[n], n-1, dp);
     }

     return dp[n][sum] = take + skip;

    }
    public static void main(String[] args) {
        int[] arr = {2,3,5,6,8,10};
        int sum = 10;

        dp = new int[arr.length][sum + 1];

        for(int[] row : dp){
            Arrays.fill(row, -1);
        }
        int res = countSumofTarget(arr,sum, arr.length-1, dp);
        System.out.println(res);
    }
}
