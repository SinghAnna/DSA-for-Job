public class SubSetSumRecursive {

    static Boolean[][] dp;

    static  boolean subSetSum(int[] arr, int sum, int n, Boolean[][] dp){
        
        if(sum == 0){
            return true;
        }
        
        if(n < 0 ){
            return false;
        }

        if (dp[n][sum] != null) {
            return dp[n][sum];
        }

        boolean skip = subSetSum(arr, sum, n-1, dp);

        boolean take = false;

        if (arr[n] <= sum) {
            take = subSetSum(arr, sum - arr[n], n-1, dp);
        }

        return dp[n][sum] = take || skip;
    }


    public static void main(String[] args) {
        int[] arr = {2,3,7,8,10};
        int sum = 11;

        dp = new Boolean[arr.length][sum + 1];

        boolean res = subSetSum(arr, sum,  arr.length -1,dp);

        System.out.println(res);
    }
}
