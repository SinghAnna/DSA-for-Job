import java.util.Arrays;

public class MinimumSubsetSumDiffItrative {

    static int minimumSubsetSumDiffItrative(int[] arr) {

        int range = Arrays.stream(arr).sum();

        int target = range >> 1;

        int n = arr.length;

        boolean[][] dp = new boolean[n + 1][target + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                // Skip current element
                dp[i][j] = dp[i - 1][j];
                // Take current element
                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - arr[i - 1]];

                }
            }
        }


        for (int s1 = target; s1 >= 0; s1--) { 
            if (dp[n][s1]) {
                 return range - 2 * s1; 
                } 
            } 
            
            return range;

    }

    public static void main(String[] args) {
        int[] arr = { 1, 5, 6, 11 };

        int res = minimumSubsetSumDiffItrative(arr);
        System.out.println(res);
    }
}
