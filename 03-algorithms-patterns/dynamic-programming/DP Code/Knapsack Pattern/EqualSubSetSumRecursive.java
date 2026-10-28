import java.util.Arrays;

public class EqualSubSetSumRecursive {

    static Boolean[][] dp;

    boolean isEqualSubSetSum(int[] arr){

     int sum = Arrays.stream(arr).sum();

        if((sum & 1) == 1){
            return false;
        }

        int target = sum >> 1;
        dp = new  Boolean[arr.length][target + 1];

        return solveEqualSubSetSum(arr, target, arr.length - 1,dp);
    }

    boolean solveEqualSubSetSum(int[] arr, int target, int n, Boolean[][] dp){

        if (target == 0) {
            return true;
        }

        if(n < 0){
            return false;
        }

        if(dp[n][target] != null) return dp[n][target];

        boolean skip = solveEqualSubSetSum(arr, target, n-1, dp);

        boolean take = false;

        if(arr[n] <= target){

          take =  solveEqualSubSetSum(arr, target - arr[n], n-1, dp);
        }

        return dp[n][target] = take || skip;
    }
    public static void main(String[] args) {
        int[] arr = {1,5,11,5};
      
        EqualSubSetSumRecursive isEqRecursive = new EqualSubSetSumRecursive();

        boolean res = isEqRecursive.isEqualSubSetSum(arr);

        System.out.println(res);
    }
}
