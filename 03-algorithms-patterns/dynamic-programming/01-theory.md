# 🎯 Dynamic Programming - Complete Guide (Part 1)

## 📚 Theory

### What is Dynamic Programming?

**Dynamic Programming (DP)** is an optimization technique that solves complex problems by breaking them into simpler subproblems and storing their solutions to avoid recomputation.

### DP vs Recursion

```
Recursion (Fibonacci):
fib(5)
├── fib(4)
│ ├── fib(3)
│ │ ├── fib(2) ← Computed multiple times!
│ │ └── fib(1)
│ └── fib(2) ← Recomputed!
└── fib(3)
├── fib(2) ← Recomputed again!
└── fib(1)

Dynamic Programming:
fib(0) = 0 ← Store
fib(1) = 1 ← Store
fib(2) = 1 ← Use stored values
fib(3) = 2 ← Use stored values
fib(4) = 3 ← Use stored values
fib(5) = 5 ← Use stored values
```


### When to Use DP?

A problem can be solved using DP if it has:

1. **Optimal Substructure** - Optimal solution can be constructed from optimal solutions of subproblems
2. **Overlapping Subproblems** - Same subproblems are solved multiple times

### Approaches to DP

# Memoization (Top-Down)

- Start from original problem

- Recursively solve subproblems

- Store results in table (memo)

- Return stored result if already computed

# Tabulation (Bottom-Up)

- Start from base cases

- Iteratively build up to solution

- Fill table in systematic order

- No recursion needed


## Steps to Solve DP Problems

### Step 1: Identify if problem can be solved using DP
* (optimal substructure + overlapping subproblems)

### Step 2: Define the state
* (what parameters define a subproblem?)

### Step 3: Write recurrence relation
* (how to express current state using previous states?)

### Step 4: Identify base cases
* (simplest subproblems with known answers)

### Step 5: Implement
- Memoization (recursive + memo table)
- Tabulation (iterative + DP table)

Step 6: Optimize (if possible)
- Space optimization
- Reduce dimensions


### Common DP Patterns

1. **Linear DP** - 1D array, problems on sequences
2. **2D DP** - 2D array, problems on grids/matrices
3. **Knapsack Pattern** - Choose/not choose items
4. **Longest Common Subsequence** - String matching
5. **Matrix Chain Multiplication** - Partitioning problems
6. **DP on Trees** - Tree DP
7. **DP on Graphs** - Shortest paths, etc.
8. **Bitmask DP** - Subset problems
9. **Digit DP** - Counting problems
10. **Probability DP** - Expected value problems

---

## 💻 Java Code Examples

### Problem 1: Fibonacci Number

**Question:** Given n, calculate the nth Fibonacci number.

```java
/**
 * Problem: Fibonacci Number
 * LeetCode: 509
 * 
 * Visual:
 * 
 * Recursion Tree (without DP):
 *                    fib(5)
 *                   /      \
 *               fib(4)     fib(3)
 *              /     \      /    \
 *          fib(3)  fib(2) fib(2) fib(1)
 *          /   \    /  \   /  \
 *      fib(2) fib(1) ... ... ...
 *      /    \
 *   fib(1) fib(0)
 * 
 * Time: O(2ⁿ) - Exponential!
 * 
 * With DP (Memoization):
 * fib = 0
 * fib = 1[1]
 * fib = 1  ← Use stored[2]
 * fib = 2  ← Use stored
 * fib = 3  ← Use stored[3]
 * fib = 5  ← Use stored[4]
 * 
 * Time: O(n) - Linear!
 * 
 * Approaches:
 * 1. Recursion: O(2ⁿ) time, O(n) space
 * 2. Memoization: O(n) time, O(n) space
 * 3. Tabulation: O(n) time, O(n) space
 * 4. Space Optimized: O(n) time, O(1) space
 */
public class FibonacciNumber {
    
    // Approach 1: Pure Recursion - O(2ⁿ) time, O(n) space
    public static int fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }
    
    // Approach 2: Memoization (Top-Down) - O(n) time, O(n) space
    public static int fibonacciMemo(int n) {
        int[] memo = new int[n + 1];
        java.util.Arrays.fill(memo, -1);
        return fibonacciMemoHelper(n, memo);
    }
    
    private static int fibonacciMemoHelper(int n, int[] memo) {
        if (n <= 1) {
            return n;
        }
        
        // Return if already computed
        if (memo[n] != -1) {
            return memo[n];
        }
        
        // Compute and store
        memo[n] = fibonacciMemoHelper(n - 1, memo) + 
                  fibonacciMemoHelper(n - 2, memo);
        
        return memo[n];
    }
    
    // Approach 3: Tabulation (Bottom-Up) - O(n) time, O(n) space
    public static int fibonacciTabulation(int n) {
        if (n <= 1) {
            return n;
        }
        
        int[] dp = new int[n + 1];
        dp = 0;
        dp = 1;[1]
        
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        return dp[n];
    }
    
    // Approach 4: Space Optimized - O(n) time, O(1) space
    public static int fibonacciOptimized(int n) {
        if (n <= 1) {
            return n;
        }
        
        int prev2 = 0;
        int prev1 = 1;
        
        for (int i = 2; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    // Print Fibonacci series
    public static void printFibonacci(int n) {
        if (n <= 0) return;
        
        int prev2 = 0, prev1 = 1;
        System.out.print(prev2 + " " + prev1 + " ");
        
        for (int i = 2; i < n; i++) {
            int current = prev1 + prev2;
            System.out.print(current + " ");
            prev2 = prev1;
            prev1 = current;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int n = 10;
        
        System.out.println("Fibonacci(" + n + ") using different approaches:");
        System.out.println("Recursive: " + fibonacciRecursive(n));
        System.out.println("Memoization: " + fibonacciMemo(n));
        System.out.println("Tabulation: " + fibonacciTabulation(n));
        System.out.println("Space Optimized: " + fibonacciOptimized(n));
        
        System.out.println("\nFibonacci Series up to " + n + " terms:");
        printFibonacci(n);
    }
}
```

---

### Problem 2: Climbing Stairs

**Question:** You are climbing a staircase. It takes n steps to reach the top. Each time you can either climb 1 or 2 steps. How many distinct ways can you climb to the top?

```java
/**
 * Problem: Climbing Stairs
 * LeetCode: 70
 * 
 * Visual:
 * 
 * n = 4
 * 
 * Ways:
 * 1. 1 + 1 + 1 + 1
 * 2. 1 + 1 + 2
 * 3. 1 + 2 + 1
 * 4. 2 + 1 + 1
 * 5. 2 + 2
 * 
 * Total: 5 ways
 * 
 * Recurrence:
 * ways(n) = ways(n-1) + ways(n-2)
 * 
 * Why?
 * - Take 1 step: remaining n-1 steps → ways(n-1)
 * - Take 2 steps: remaining n-2 steps → ways(n-2)
 * 
 * This is actually Fibonacci!
 * ways(1) = 1
 * ways(2) = 2
 * ways(3) = 3
 * ways(4) = 5
 * ways(5) = 8
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1) optimized
 */
public class ClimbingStairs {
    
    // Approach 1: Recursion - O(2ⁿ) time
    public static int climbStairsRecursive(int n) {
        if (n <= 2) {
            return n;
        }
        return climbStairsRecursive(n - 1) + climbStairsRecursive(n - 2);
    }
    
    // Approach 2: Memoization - O(n) time, O(n) space
    public static int climbStairsMemo(int n) {
        int[] memo = new int[n + 1];
        java.util.Arrays.fill(memo, -1);
        return climbStairsMemoHelper(n, memo);
    }
    
    private static int climbStairsMemoHelper(int n, int[] memo) {
        if (n <= 2) {
            return n;
        }
        
        if (memo[n] != -1) {
            return memo[n];
        }
        
        memo[n] = climbStairsMemoHelper(n - 1, memo) + 
                  climbStairsMemoHelper(n - 2, memo);
        
        return memo[n];
    }
    
    // Approach 3: Tabulation - O(n) time, O(n) space
    public static int climbStairsTabulation(int n) {
        if (n <= 2) {
            return n;
        }
        
        int[] dp = new int[n + 1];
        dp = 1;[1]
        dp = 2;[2]
        
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        return dp[n];
    }
    
    // Approach 4: Space Optimized - O(n) time, O(1) space
    public static int climbStairsOptimized(int n) {
        if (n <= 2) {
            return n;
        }
        
        int prev2 = 1; // ways(1)
        int prev1 = 2; // ways(2)
        
        for (int i = 3; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    // Variation: Can climb 1, 2, or 3 steps
    public static int climbStairs123(int n) {
        if (n <= 2) {
            return n;
        }
        if (n == 3) {
            return 4; // 1+1+1, 1+2, 2+1, 3
        }
        
        int prev3 = 1; // ways(1)
        int prev2 = 2; // ways(2)
        int prev1 = 4; // ways(3)
        
        for (int i = 4; i <= n; i++) {
            int current = prev1 + prev2 + prev3;
            prev3 = prev2;
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    public static void main(String[] args) {
        int n = 5;
        
        System.out.println("Ways to climb " + n + " stairs:");
        System.out.println("Recursive: " + climbStairsRecursive(n));
        System.out.println("Memoization: " + climbStairsMemo(n));
        System.out.println("Tabulation: " + climbStairsTabulation(n));
        System.out.println("Space Optimized: " + climbStairsOptimized(n));
        
        System.out.println("\nWith 1,2,3 steps: " + climbStairs123(n));
    }
}
```

---

### Problem 3: House Robber

**Question:** You are a professional robber planning to rob houses along a street. Each house has a certain amount of money. The only constraint is that adjacent houses have security systems connected, and it will automatically contact the police if two adjacent houses were broken into on the same night. Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

```java
/**
 * Problem: House Robber
 * LeetCode: 198
 * 
 * Visual:
 * 
 * nums =[1][2][3][7][9]
 * 
 * Decision at each house:
 * House 0 (2): Rob or Skip?
 * House 1 (7): Rob or Skip?
 * House 2 (9): Rob or Skip?
 * House 3 (3): Rob or Skip?
 * House 4 (1): Rob or Skip?
 * 
 * Optimal: Rob houses 0, 2, 4
 * Total: 2 + 9 + 1 = 12
 * 
 * OR: Rob houses 1, 3
 * Total: 7 + 3 = 10
 * 
 * Best: 12
 * 
 * Recurrence:
 * dp[i] = max(dp[i-1],           // Skip current house
 *             dp[i-2] + nums[i]) // Rob current house
 * 
 * Base cases:
 * dp = nums
 * dp = max(nums, nums)[1]
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1) optimized
 */
public class HouseRobber {
    
    // Approach 1: Recursion - O(2ⁿ) time
    public static int robRecursive(int[] nums, int index) {
        if (index < 0) {
            return 0;
        }
        
        // Two choices: rob current or skip
        int robCurrent = nums[index] + robRecursive(nums, index - 2);
        int skipCurrent = robRecursive(nums, index - 1);
        
        return Math.max(robCurrent, skipCurrent);
    }
    
    // Approach 2: Memoization - O(n) time, O(n) space
    public static int robMemo(int[] nums) {
        int[] memo = new int[nums.length];
        java.util.Arrays.fill(memo, -1);
        return robMemoHelper(nums, nums.length - 1, memo);
    }
    
    private static int robMemoHelper(int[] nums, int index, int[] memo) {
        if (index < 0) {
            return 0;
        }
        
        if (memo[index] != -1) {
            return memo[index];
        }
        
        int robCurrent = nums[index] + robMemoHelper(nums, index - 2, memo);
        int skipCurrent = robMemoHelper(nums, index - 1, memo);
        
        memo[index] = Math.max(robCurrent, skipCurrent);
        
        return memo[index];
    }
    
    // Approach 3: Tabulation - O(n) time, O(n) space
    public static int robTabulation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        if (nums.length == 1) {
            return nums;
        }
        
        int[] dp = new int[nums.length];
        dp = nums;
        dp = Math.max(nums, nums);[1]
        
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1],        // Skip current
                            dp[i - 2] + nums[i]); // Rob current
        }
        
        return dp[nums.length - 1];
    }
    
    // Approach 4: Space Optimized - O(n) time, O(1) space
    public static int robOptimized(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        if (nums.length == 1) {
            return nums;
        }
        
        int prev2 = nums;
        int prev1 = Math.max(nums, nums);[1]
        
        for (int i = 2; i < nums.length; i++) {
            int current = Math.max(prev1,        // Skip
                                  prev2 + nums[i]); // Rob
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    // Variation: House Robber II (Circular houses)
    // LeetCode: 213
    public static int robCircular(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        if (nums.length == 1) {
            return nums;
        }
        
        // Two cases:
        // 1. Rob houses 0 to n-2 (exclude last)
        // 2. Rob houses 1 to n-1 (exclude first)
        // Return maximum of both
        
        int rob1 = robLinear(nums, 0, nums.length - 2);
        int rob2 = robLinear(nums, 1, nums.length - 1);
        
        return Math.max(rob1, rob2);
    }
    
    private static int robLinear(int[] nums, int start, int end) {
        if (start > end) {
            return 0;
        }
        
        int prev2 = 0;
        int prev1 = 0;
        
        for (int i = start; i <= end; i++) {
            int current = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    public static void main(String[] args) {
        int[] nums1 = {2, 7, 9, 3, 1};
        System.out.println("Houses: " + java.util.Arrays.toString(nums1));
        System.out.println("Max amount (Recursive): " + robRecursive(nums1, nums1.length - 1));
        System.out.println("Max amount (Memo): " + robMemo(nums1));
        System.out.println("Max amount (Tabulation): " + robTabulation(nums1));
        System.out.println("Max amount (Optimized): " + robOptimized(nums1));
        
        int[] nums2 = {2, 3, 2}; // Circular
        System.out.println("\nCircular houses: " + java.util.Arrays.toString(nums2));
        System.out.println("Max amount (Circular): " + robCircular(nums2));
    }
}
```

---

### Problem 4: Coin Change

**Question:** You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money. Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

```java
/**
 * Problem: Coin Change
 * LeetCode: 322
 * 
 * Visual:
 * 
 * coins =, amount = 11[1][2][5]
 * 
 * DP Table:
 * 
 * amount: 0  1  2  3  4  5  6  7  8  9  10 11
 * dp:     0  1  1  2  2  1  2  2  3  3  2  3
 * 
 * How to fill:
 * dp = 0 (base case)
 * dp = min(dp[1-1]) + 1 = dp + 1 = 1[1]
 * dp = min(dp[2-1], dp[2-2]) + 1 = min(dp, dp) + 1 = 1[1][2]
 * dp = min(dp[3-1], dp[3-2]) + 1 = min(dp, dp) + 1 = 2[1][2]
 * ...
 * 
 * Recurrence:
 * dp[i] = min(dp[i - coin] + 1) for all coins where i >= coin
 * 
 * Base case:
 * dp = 0
 * 
 * Time Complexity: O(amount × number of coins)
 * Space Complexity: O(amount)
 */
public class CoinChange {
    
    // Approach 1: Recursion - O(coins^amount) time
    public static int coinChangeRecursive(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        
        if (amount == 0) {
            return 0;
        }
        
        int minCoins = Integer.MAX_VALUE;
        
        for (int coin : coins) {
            int result = coinChangeRecursive(coins, amount - coin);
            if (result != -1) {
                minCoins = Math.min(minCoins, result + 1);
            }
        }
        
        return (minCoins == Integer.MAX_VALUE) ? -1 : minCoins;
    }
    
    // Approach 2: Memoization - O(amount × coins) time, O(amount) space
    public static int coinChangeMemo(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        java.util.Arrays.fill(memo, -2); // -2 means not computed
        return coinChangeMemoHelper(coins, amount, memo);
    }
    
    private static int coinChangeMemoHelper(int[] coins, int amount, int[] memo) {
        if (amount < 0) {
            return -1;
        }
        
        if (amount == 0) {
            return 0;
        }
        
        if (memo[amount] != -2) {
            return memo[amount];
        }
        
        int minCoins = Integer.MAX_VALUE;
        
        for (int coin : coins) {
            int result = coinChangeMemoHelper(coins, amount - coin, memo);
            if (result != -1) {
                minCoins = Math.min(minCoins, result + 1);
            }
        }
        
        memo[amount] = (minCoins == Integer.MAX_VALUE) ? -1 : minCoins;
        
        return memo[amount];
    }
    
    // Approach 3: Tabulation - O(amount × coins) time, O(amount) space
    public static int coinChangeTabulation(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        java.util.Arrays.fill(dp, amount + 1); // Infinity
        dp = 0;
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        return (dp[amount] > amount) ? -1 : dp[amount];
    }
    
    // Get the actual coins used
    public static java.util.List<Integer> coinChangeWithCoins(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int[] parent = new int[amount + 1];
        java.util.Arrays.fill(dp, amount + 1);
        java.util.Arrays.fill(parent, -1);
        dp = 0;
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin && dp[i - coin] + 1 < dp[i]) {
                    dp[i] = dp[i - coin] + 1;
                    parent[i] = coin;
                }
            }
        }
        
        java.util.List<Integer> result = new java.util.ArrayList<>();
        if (dp[amount] <= amount) {
            int current = amount;
            while (current > 0) {
                result.add(parent[current]);
                current -= parent[current];
            }
        }
        
        return result;
    }
    
    // Variation: Coin Change 2 (Number of combinations)
    // LeetCode: 518
    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp = 1;
        
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        
        return dp[amount];
    }
    
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        
        System.out.println("Coins: " + java.util.Arrays.toString(coins));
        System.out.println("Amount: " + amount);
        System.out.println("Min coins (Memo): " + coinChangeMemo(coins, amount));
        System.out.println("Min coins (Tabulation): " + coinChangeTabulation(coins, amount));
        
        java.util.List<Integer> coinList = coinChangeWithCoins(coins, amount);
        System.out.println("Coins used: " + coinList);
        
        // Number of combinations
        System.out.println("Number of combinations: " + change(amount, coins));
    }
}
```

---

### Problem 5: Longest Common Subsequence

**Question:** Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

```java
/**
 * Problem: Longest Common Subsequence (LCS)
 * LeetCode: 1143
 * 
 * Visual:
 * 
 * text1 = "ABCBDAB"
 * text2 = "BDCABA"
 * 
 * LCS: "BCBA" or "BDAB" or "BCAB" (length 4)
 * 
 * DP Table:
 *       "" B D C A B A
 * ""    0  0 0 0 0 0 0
 * A     0  0 0 0 1 1 1
 * B     0  1 1 1 1 2 2
 * C     0  1 1 2 2 2 2
 * B     0  1 1 2 2 3 3
 * D     0  1 2 2 2 3 3
 * A     0  1 2 2 3 3 4
 * B     0  1 2 2 3 4 4
 * 
 * Recurrence:
 * if text1[i-1] == text2[j-1]:
 *     dp[i][j] = dp[i-1][j-1] + 1
 * else:
 *     dp[i][j] = max(dp[i-1][j], dp[i][j-1])
 * 
 * Base case:
 * dp[j] = 0
 * dp[i] = 0
 * 
 * Time Complexity: O(m × n)
 * Space Complexity: O(m × n), can be optimized to O(min(m, n))
 */
public class LongestCommonSubsequence {
    
    // Approach 1: Recursion - O(2^(m+n)) time
    public static int lcsRecursive(String text1, String text2, 
                                   int i, int j) {
        if (i == 0 || j == 0) {
            return 0;
        }
        
        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
            return 1 + lcsRecursive(text1, text2, i - 1, j - 1);
        } else {
            return Math.max(lcsRecursive(text1, text2, i - 1, j),
                           lcsRecursive(text1, text2, i, j - 1));
        }
    }
    
    // Approach 2: Memoization - O(m × n) time, O(m × n) space
    public static int lcsMemo(String text1, String text2) {
        int[][] memo = new int[text1.length() + 1][text2.length() + 1];
        
        for (int[] row : memo) {
            java.util.Arrays.fill(row, -1);
        }
        
        return lcsMemoHelper(text1, text2, text1.length(), 
                            text2.length(), memo);
    }
    
    private static int lcsMemoHelper(String text1, String text2, 
                                    int i, int j, int[][] memo) {
        if (i == 0 || j == 0) {
            return 0;
        }
        
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        
        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
            memo[i][j] = 1 + lcsMemoHelper(text1, text2, i - 1, j - 1, memo);
        } else {
            memo[i][j] = Math.max(lcsMemoHelper(text1, text2, i - 1, j, memo),
                                 lcsMemoHelper(text1, text2, i, j - 1, memo));
        }
        
        return memo[i][j];
    }
    
    // Approach 3: Tabulation - O(m × n) time, O(m × n) space
    public static int lcsTabulation(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[m][n];
    }
    
    // Get the actual LCS string
    public static String lcsWithString(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        
        int[][] dp = new int[m + 1][n + 1];
        
        // Build DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        // Backtrack to find LCS
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        
        while (i > 0 && j > 0) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                lcs.append(text1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        
        return lcs.reverse().toString();
    }
    
    // Space Optimized - O(m × n) time, O(min(m, n)) space
    public static int lcsSpaceOptimized(String text1, String text2) {
        // Make sure text2 is shorter
        if (text1.length() < text2.length()) {
            return lcsSpaceOptimized(text2, text1);
        }
        
        int m = text1.length();
        int n = text2.length();
        
        int[] prev = new int[n + 1];
        int[] curr = new int[n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    curr[j] = prev[j - 1] + 1;
                } else {
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }
            
            // Swap arrays
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        
        return prev[n];
    }
    
    // Variation: Longest Palindromic Subsequence
    // LeetCode: 516
    public static int longestPalindromeSubseq(String s) {
        String reversed = new StringBuilder(s).reverse().toString();
        return lcsTabulation(s, reversed);
    }
    
    public static void main(String[] args) {
        String text1 = "ABCBDAB";
        String text2 = "BDCABA";
        
        System.out.println("Text1: " + text1);
        System.out.println("Text2: " + text2);
        System.out.println("LCS Length (Recursive): " + 
                          lcsRecursive(text1, text2, text1.length(), text2.length()));
        System.out.println("LCS Length (Memo): " + lcsMemo(text1, text2));
        System.out.println("LCS Length (Tabulation): " + lcsTabulation(text1, text2));
        System.out.println("LCS Length (Space Optimized): " + 
                          lcsSpaceOptimized(text1, text2));
        System.out.println("LCS String: " + lcsWithString(text1, text2));
        
        // Longest palindromic subsequence
        String s = "bbbab";
        System.out.println("\nString: " + s);
        System.out.println("Longest Palindromic Subsequence: " + 
                          longestPalindromeSubseq(s));
    }
}
```

---

## 📝 Practice Problems (Part 1)

### Easy
| Problem | Pattern | Link |
|---------|---------|------|
| Fibonacci Number | 1D DP | LeetCode 509 |
| Climbing Stairs | 1D DP | LeetCode 70 |
| House Robber | 1D DP | LeetCode 198 |

### Medium
| Problem | Pattern | Link |
|---------|---------|------|
| Coin Change | Unbounded Knapsack | LeetCode 322 |
| Longest Common Subsequence | 2D DP | LeetCode 1143 |
| Longest Palindromic Subsequence | 2D DP | LeetCode 516 |
| House Robber II | Circular DP | LeetCode 213 |
| Delete and Earn | 1D DP | LeetCode 740 |

### Hard
| Problem | Pattern | Link |
|---------|---------|------|
| Edit Distance | 2D DP | LeetCode 72 |
| Regular Expression Matching | 2D DP | LeetCode 10 |
| Burst Balloons | Interval DP | LeetCode 312 |

---

**Note:** DP ka content bahut bada hai, **Part 2** mein continue karenge with:
- 0/1 Knapsack
- Unbounded Knapsack
- Matrix Chain Multiplication pattern
- DP on Grids
- More advanced problems

**Part 2 continue karein?** [1][2][6][14]