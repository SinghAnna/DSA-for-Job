    
import java.util.Arrays;

public class MinimumCoinChangeItrative {

static int minimumCoinChange(int[] coins, int target) {

    int n = coins.length;

    int[][] dp = new int[n + 1][target + 1];

    // Base case: target = 0, minimum coins = 0
    for (int i = 0; i <= n; i++) {
        dp[i][0] = 0;
    }

    // Base case: no coins available, target > 0
    for (int j = 1; j <= target; j++) {
        dp[0][j] = Integer.MAX_VALUE;
    }

    for (int i = 1; i <= n; i++) {

        for (int j = 1; j <= target; j++) {

            // Skip: current coin nahi lenge
            int skip = dp[i - 1][j];

            // Take: current coin lenge
            int take = Integer.MAX_VALUE;

            if (coins[i - 1] <= j) {

                int result = dp[i][j - coins[i - 1]];

                if (result != Integer.MAX_VALUE) {
                    take = 1 + result;
                }
            }

            dp[i][j] = Math.min(skip, take);
        }
    }

    return dp[n][target] == Integer.MAX_VALUE
            ? -1
            : dp[n][target];
}

public static void main(String[] args) {

    int[] coins = {1, 2, 5};
    int amount = 11;

    System.out.println(minimumCoinChange(coins, amount));
}


}
