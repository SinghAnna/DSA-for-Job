import java.util.Arrays;

public class CountTheSubsetSumWithGiveDiffItative {

static int count(int[] arr, int diff) {

    int sum = Arrays.stream(arr).sum();

    // Invalid cases
    if (diff > sum || (sum - diff) % 2 != 0) {
        return 0;
    }

    int target = (sum - diff) / 2;
    int n = arr.length;

    int[][] dp = new int[n + 1][target + 1];

    // Base case: sum = 0
    for (int i = 0; i <= n; i++) {
        dp[i][0] = 1;
    }

    for (int i = 1; i <= n; i++) {
        for (int s = 0; s <= target; s++) {

            // Skip current element
            int skip = dp[i - 1][s];

            // Take current element
            int take = 0;

            if (arr[i - 1] <= s) {
                take = dp[i - 1][s - arr[i - 1]];
            }

            dp[i][s] = skip + take;
        }
    }

    return dp[n][target];
}

public static void main(String[] args) {

    int[] arr = {1, 1, 2, 3};
    int diff = 1;

    System.out.println(count(arr, diff));
}


}
