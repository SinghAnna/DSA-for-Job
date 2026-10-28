import java.util.Arrays;

public class CountTheSubsetSumWithGiveDiff {

static int count(int[] arr, int target, int n, int[][] dp) {

    if (target < 0) {
        return 0;
    }

    if (n < 0) {
        return target == 0 ? 1 : 0;
    }

    if (dp[n][target] != -1) {
        return dp[n][target];
    }

    // Skip current element
    int skip = count(arr, target, n - 1, dp);

    // Take current element
    int take = 0;

    if (arr[n] <= target) {
        take = count(arr, target - arr[n], n - 1, dp);
    }

    return dp[n][target] = take + skip;
}

public static void main(String[] args) {

    int[] arr = {1, 1, 2, 3};
    int diff = 1;

    int sum = Arrays.stream(arr).sum();

    if (diff > sum || (sum - diff) % 2 != 0) {
        System.out.println(0);
        return;
    }

    int target = (sum - diff) / 2;
    int n = arr.length;

    int[][] dp = new int[n][target + 1];

    for (int[] row : dp) {
        Arrays.fill(row, -1);
    }

    int res = count(arr, target, n - 1, dp);

    System.out.println(res);
}


}
