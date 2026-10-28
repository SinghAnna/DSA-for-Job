import java.util.Arrays;

public class MinimumSubsetSumDiff {

static int minimumSubsetSumDiff(int[] arr, int target, int range, int n, int[][] dp) {

    if (n < 0) {
        return Math.abs(range - (2 * (range / 2 - target)));
    }

    if (dp[n][target] != -1) {
        return dp[n][target];
    }

    // Skip current element
    int skip = minimumSubsetSumDiff(arr, target, range, n - 1, dp);

    int take = Integer.MAX_VALUE;

    // Take current element
    if (arr[n] <= target) {
        take = minimumSubsetSumDiff(
                arr, target - arr[n], range, n - 1, dp
        );
    }

    return dp[n][target] = Math.min(take, skip);
}

public static void main(String[] args) {

    int[] arr = {1, 5, 6, 11};

    int sum = Arrays.stream(arr).sum();
    int target = sum / 2;

    int[][] dp = new int[arr.length][target + 1];

    for (int[] row : dp) {
        Arrays.fill(row, -1);
    }

    int res = minimumSubsetSumDiff(arr, target, sum, arr.length - 1, dp);

    System.out.println(res);
}


}

// import java.util.Arrays;

// public class MinimumSubsetSumDiff {

//     static int minimumSubsetSumDiff(int[] arr, int n, int totalSum) {

//         if (n == 0) {
//             return Math.abs(totalSum);
//         }

//         return findMinimum(arr, n - 1, 0, totalSum);
//     }

//     static int findMinimum(int[] arr, int index, int currentSum, int totalSum) {

//         if (index < 0) {
//             int otherSum = totalSum - currentSum;
//             return Math.abs(currentSum - otherSum);
//         }

//         // Take current element
//         int take = findMinimum(
//                 arr, index - 1, currentSum + arr[index], totalSum
//         );

//         // Skip current element
//         int skip = findMinimum(
//                 arr, index - 1, currentSum, totalSum
//         );

//         return Math.min(take, skip);
//     }

//     public static void main(String[] args) {

//         int[] arr = {1, 5, 6, 11};

//         int sum = Arrays.stream(arr).sum();

//         System.out.println(
//                 minimumSubsetSumDiff(arr, arr.length, sum)
//         );
//     }
// }

