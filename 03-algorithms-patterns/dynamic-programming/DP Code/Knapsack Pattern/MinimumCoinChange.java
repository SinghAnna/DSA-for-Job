public class MinimumCoinChange {

static int minimumCoinChange(int[] coins, int target, int n) {

    if (target == 0) {
        return 0;
    }

    if (n < 0) {
        return Integer.MAX_VALUE;
    }

    // Skip: current coin nahi lenge
    int skip = minimumCoinChange(coins, target, n - 1);

    // Take: current coin lenge
    int take = Integer.MAX_VALUE;

    if (coins[n] <= target) {
        int result = minimumCoinChange(
            coins, target - coins[n], n
        );

        if (result != Integer.MAX_VALUE) {
            take = 1 + result;
        }
    }

    return Math.min(skip, take);
}

public static void main(String[] args) {

    int[] coins = {1, 2, 5};
    int amount = 11;

    int res = minimumCoinChange(
        coins, amount, coins.length - 1
    );

    if (res == Integer.MAX_VALUE) {
        System.out.println(-1);
    } else {
        System.out.println(res);
    }
}


}
