# 🎯 Dynamic Programming - Advanced Patterns (Part 2)

## 📚 Theory

### Knapsack Pattern

**0/1 Knapsack:** Each item can be taken 0 or 1 time (not fractional).

```
Problem: Given weights and values of n items, put items in knapsack
of capacity W to maximize total value.

Example:
Item: 1 2 3 4
Weight: 2 3 4 5
Value: 3 4 5 6

Capacity W = 5

DP Table (0/1 Knapsack):
0 1 2 3 4 5 (capacity)
-------------------
0 | 0 0 0 0 0 0
1 | 0 0 3 3 3 3 (item 1: w=2, v=3)
i 2 | 0 0 3 4 4 7 (item 2: w=3, v=4)
3 | 0 0 3 4 5 7 (item 3: w=4, v=5)
4 | 0 0 3 4 5 7 (item 4: w=5, v=6)

Recurrence:
if weight[i] <= j:
dp[i][j] = max(dp[i-1][j], // Don't take item i
dp[i-1][j-weight[i]] + value[i]) // Take item i
else:
dp[i][j] = dp[i-1][j]

Time: O(n × W)
Space: O(n × W) → O(W) optimized
```



**Unbounded Knapsack:** Each item can be taken multiple times.

* Same as 0/1 Knapsack, but we can take item multiple times.
```
Recurrence:
if weight[i] <= j:
dp[j] = max(dp[j], dp[j-weight[i]] + value[i])
// Notice: dp[j-weight[i]] not dp[i-1][j-weight[i]]

Time: O(n × W)
Space: O(W)

```

# 🎒 Unbounded Knapsack — Complete Guide

The **Unbounded Knapsack Problem** is a variation of the classic 0/1 Knapsack problem where an **infinite supply** of each item is available. You can pick any item multiple times as long as the total weight does not exceed the knapsack capacity $W$.

---

## ⚖️ 0/1 Knapsack vs. Unbounded Knapsack

| Feature | 0/1 Knapsack | Unbounded Knapsack |
| :--- | :--- | :--- |
| **Item Availability** | Exactly once ($0$ or $1$ pick) | Unlimited times ($\ge 0$ picks) |
| **Inner Loop Direction** | Right-to-Left: $W \to \text{weight}[i]$ | Left-to-Right: $\text{weight}[i] \to W$ |
| **State Reference** | Depends on previous item state: `dp[i-1][...]` | Depends on current item state: `dp[i][...]` |

---

## 🔁 Mathematical Recurrence

### 1D Space-Optimized State
Let $\text{dp}[j]$ denote the maximum value achievable with total weight capacity $j$:

$$\text{dp}[j] = \max\Big(\text{dp}[j],\; \text{dp}[j - \text{weight}[i]] + \text{value}[i]\Big) \quad \text{for } j \ge \text{weight}[i]$$

> **Key Distinction:**
> * In **0/1 Knapsack**, we use $\text{dp}[j - \text{weight}[i]]$ from the *previous* row (or reverse loop) to prevent picking the same item again.
> * In **Unbounded Knapsack**, we iterate forward ($j = \text{weight}[i] \to W$). This lets $\text{dp}[j - \text{weight}[i]]$ already contain the updated result using the current item, naturally allowing multiple inclusions.

---

## 💻 Implementation (Space Optimized: 1D Array)

```public class ZeroOneKnapsack {
    public static int solve(int W, int[] weights, int[] values) {
        int n = weights.length;
        int[] dp = new int[W + 1];

        for (int i = 0; i < n; i++) {
            int w = weights[i];
            int val = values[i];
            // Backward traversal prevents re-using the same item in the same step
            for (int j = W; j >= w; j--) {
                dp[j] = Math.max(dp[j], dp[j - w] + val);
            }
        }
        return dp[W];
    }
}

```



---

## 💻 Java Code Examples

### Problem 6: 0/1 Knapsack

**Question:** Given weights and values of n items and a knapsack capacity W, find maximum value subset such that sum of weights ≤ W.

```java
/**
 * Problem: 0/1 Knapsack
 * 
 * Visual:
 * 
 * weights =[2][3][4][5]
 * values  =[3][4][5][6]
 * W = 5
 * 
 * DP Table:
 * 
 *       cap: 0  1  2  3  4  5
 *            -----------------
 * item 0 |   0  0  0  0  0  0
 * item 1 |   0  0  3  3  3  3  (w=2, v=3)
 * item 2 |   0  0  3  4  4  7  (w=3, v=4)
 * item 3 |   0  0  3  4  5  7  (w=4, v=5)
 * item 4 |   0  0  3  4  5  7  (w=5, v=6)
 * 
 * Optimal: Items 1 and 2 (weight=5, value=7)
 * 
 * Recurrence:
 * dp[i][j] = max(dp[i-1][j],              // Don't include item i
 *                dp[i-1][j-wt[i-1]] + val[i-1])  // Include item i
 * 
 * Time Complexity: O(n × W)
 * Space Complexity: O(n × W) → O(W) optimized
 */
public class ZeroOneKnapsack {
    
    // Approach 1: Recursion - O(2ⁿ) time
    public static int knapsackRecursive(int[] wt, int[] val, int W, int n) {
        if (n == 0 || W == 0) {
            return 0;
        }
        
        // If weight of nth item is more than capacity, skip it
        if (wt[n - 1] > W) {
            return knapsackRecursive(wt, val, W, n - 1);
        }
        
        // Return maximum of two cases:
        // 1. Include nth item
        // 2. Exclude nth item
        return Math.max(val[n - 1] + knapsackRecursive(wt, val, W - wt[n - 1], n - 1),
                       knapsackRecursive(wt, val, W, n - 1));
    }
    
    // Approach 2: Memoization - O(n × W) time, O(n × W) space
    public static int knapsackMemo(int[] wt, int[] val, int W) {
        int[][] memo = new int[wt.length + 1][W + 1];
        
        for (int[] row : memo) {
            java.util.Arrays.fill(row, -1);
        }
        
        return knapsackMemoHelper(wt, val, W, wt.length, memo);
    }
    
    private static int knapsackMemoHelper(int[] wt, int[] val, int W, int n, int[][] memo) {
        if (n == 0 || W == 0) {
            return 0;
        }
        
        if (memo[n][W] != -1) {
            return memo[n][W];
        }
        
        if (wt[n - 1] > W) {
            memo[n][W] = knapsackMemoHelper(wt, val, W, n - 1, memo);
        } else {
            memo[n][W] = Math.max(val[n - 1] + knapsackMemoHelper(wt, val, W - wt[n - 1], n - 1, memo),
                                 knapsackMemoHelper(wt, val, W, n - 1, memo));
        }
        
        return memo[n][W];
    }
    
    // Approach 3: Tabulation - O(n × W) time, O(n × W) space
    public static int knapsackTabulation(int[] wt, int[] val, int W) {
        int n = wt.length;
        int[][] dp = new int[n + 1][W + 1];
        
        // Base case: dp[j] = 0 (already initialized)
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                if (wt[i - 1] <= j) {
                    // Include or exclude item i
                    dp[i][j] = Math.max(val[i - 1] + dp[i - 1][j - wt[i - 1]],
                                       dp[i - 1][j]);
                } else {
                    // Cannot include item i
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        return dp[n][W];
    }
    
    // Approach 4: Space Optimized (2 rows) - O(n × W) time, O(W) space
    public static int knapsackSpaceOptimized(int[] wt, int[] val, int W) {
        int n = wt.length;
        int[] prev = new int[W + 1];
        int[] curr = new int[W + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                if (wt[i - 1] <= j) {
                    curr[j] = Math.max(val[i - 1] + prev[j - wt[i - 1]],
                                      prev[j]);
                } else {
                    curr[j] = prev[j];
                }
            }
            
            // Swap arrays
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        
        return prev[W];
    }
    
    // Approach 5: Space Optimized (1 array) - O(n × W) time, O(W) space
    public static int knapsack1D(int[] wt, int[] val, int W) {
        int[] dp = new int[W + 1];
        
        for (int i = 0; i < wt.length; i++) {
            // Traverse from right to left to avoid using same item twice
            for (int j = W; j >= wt[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - wt[i]] + val[i]);
            }
        }
        
        return dp[W];
    }
    
    // Get items selected in optimal solution
    public static java.util.List<Integer> getSelectedItems(int[] wt, int[] val, int W) {
        int n = wt.length;
        int[][] dp = new int[n + 1][W + 1];
        
        // Build DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                if (wt[i - 1] <= j) {
                    dp[i][j] = Math.max(val[i - 1] + dp[i - 1][j - wt[i - 1]],
                                       dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        // Backtrack to find selected items
        java.util.List<Integer> selected = new java.util.ArrayList<>();
        int i = n, j = W;
        
        while (i > 0 && j > 0) {
            if (dp[i][j] != dp[i - 1][j]) {
                // Item i-1 was included
                selected.add(i - 1);
                j -= wt[i - 1];
            }
            i--;
        }
        
        java.util.Collections.reverse(selected);
        return selected;
    }
    
    public static void main(String[] args) {
        int[] wt = {2, 3, 4, 5};
        int[] val = {3, 4, 5, 6};
        int W = 5;
        
        System.out.println("Weights: " + java.util.Arrays.toString(wt));
        System.out.println("Values: " + java.util.Arrays.toString(val));
        System.out.println("Capacity: " + W);
        
        System.out.println("\nMax value (Recursive): " + 
                          knapsackRecursive(wt, val, W, wt.length));
        System.out.println("Max value (Memo): " + knapsackMemo(wt, val, W));
        System.out.println("Max value (Tabulation): " + knapsackTabulation(wt, val, W));
        System.out.println("Max value (Space Optimized): " + 
                          knapsackSpaceOptimized(wt, val, W));
        System.out.println("Max value (1D): " + knapsack1D(wt, val, W));
        
        java.util.List<Integer> selected = getSelectedItems(wt, val, W);
        System.out.println("Selected items (indices): " + selected);
    }
}
```

---

### Problem 7: Unbounded Knapsack (Rod Cutting)

**Question:** Given a rod of length n and prices for all pieces of length < n, determine maximum value obtainable by cutting and selling the rod.

```java
/**
 * Problem: Unbounded Knapsack / Rod Cutting
 * LeetCode: 322 (Coin Change - similar pattern)
 * 
 * Visual:
 * 
 * Rod length: 8
 * Prices:[1][5][8][9][10][17][20]
 *         (length 1 to 8)
 * 
 * Optimal cuts: 2 + 6 = 17 + 17 = 34
 * OR: 2 + 2 + 2 + 2 = 5 + 5 + 5 + 5 = 20 (not optimal)
 * 
 * DP Table:
 * 
 * length: 0  1  2  3  4  5  6  7  8
 * dp:     0  1  5  8  10 15 17 20 25
 * 
 * Recurrence:
 * dp[i] = max(prices[j] + dp[i - (j+1])) for all j where j+1 <= i
 * 
 * Time Complexity: O(n²)
 * Space Complexity: O(n)
 */
public class RodCutting {
    
    // Approach 1: Recursion - O(2ⁿ) time
    public static int cutRodRecursive(int[] prices, int n) {
        if (n == 0) {
            return 0;
        }
        
        int maxValue = Integer.MIN_VALUE;
        
        // Try all possible cuts
        for (int i = 1; i <= n; i++) {
            maxValue = Math.max(maxValue, 
                               prices[i - 1] + cutRodRecursive(prices, n - i));
        }
        
        return maxValue;
    }
    
    // Approach 2: Memoization - O(n²) time, O(n) space
    public static int cutRodMemo(int[] prices, int n) {
        int[] memo = new int[n + 1];
        java.util.Arrays.fill(memo, -1);
        return cutRodMemoHelper(prices, n, memo);
    }
    
    private static int cutRodMemoHelper(int[] prices, int n, int[] memo) {
        if (n == 0) {
            return 0;
        }
        
        if (memo[n] != -1) {
            return memo[n];
        }
        
        int maxValue = Integer.MIN_VALUE;
        
        for (int i = 1; i <= n; i++) {
            maxValue = Math.max(maxValue, 
                               prices[i - 1] + cutRodMemoHelper(prices, n - i, memo));
        }
        
        memo[n] = maxValue;
        return memo[n];
    }
    
    // Approach 3: Tabulation - O(n²) time, O(n) space
    public static int cutRodTabulation(int[] prices, int n) {
        int[] dp = new int[n + 1];
        dp = 0;
        
        for (int i = 1; i <= n; i++) {
            int maxValue = Integer.MIN_VALUE;
            
            for (int j = 1; j <= i; j++) {
                maxValue = Math.max(maxValue, prices[j - 1] + dp[i - j]);
            }
            
            dp[i] = maxValue;
        }
        
        return dp[n];
    }
    
    // Get the actual cuts
    public static java.util.List<Integer> getOptimalCuts(int[] prices, int n) {
        int[] dp = new int[n + 1];
        int[] bestCut = new int[n + 1];
        dp = 0;
        
        for (int i = 1; i <= n; i++) {
            int maxValue = Integer.MIN_VALUE;
            int bestLength = 0;
            
            for (int j = 1; j <= i; j++) {
                int value = prices[j - 1] + dp[i - j];
                if (value > maxValue) {
                    maxValue = value;
                    bestLength = j;
                }
            }
            
            dp[i] = maxValue;
            bestCut[i] = bestLength;
        }
        
        // Backtrack to find cuts
        java.util.List<Integer> cuts = new java.util.ArrayList<>();
        int remaining = n;
        
        while (remaining > 0) {
            cuts.add(bestCut[remaining]);
            remaining -= bestCut[remaining];
        }
        
        return cuts;
    }
    
    // Variation: Coin Change (Unbounded Knapsack)
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        java.util.Arrays.fill(dp, amount + 1);
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
    
    public static void main(String[] args) {
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20};
        int n = 8;
        
        System.out.println("Prices: " + java.util.Arrays.toString(prices));
        System.out.println("Rod length: " + n);
        
        System.out.println("\nMax value (Recursive): " + cutRodRecursive(prices, n));
        System.out.println("Max value (Memo): " + cutRodMemo(prices, n));
        System.out.println("Max value (Tabulation): " + cutRodTabulation(prices, n));
        
        java.util.List<Integer> cuts = getOptimalCuts(prices, n);
        System.out.println("Optimal cuts: " + cuts);
        
        // Coin change
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println("\nCoin Change for " + amount + ": " + coinChange(coins, amount));
    }
}
```

---

### Problem 8: Partition Equal Subset Sum

**Question:** Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal.

```java
/**
 * Problem: Partition Equal Subset Sum
 * LeetCode: 416
 * 
 * Visual:
 * 
 * nums =[1][5][11]
 * Total sum = 22
 * Target subset sum = 22 / 2 = 11
 * 
 * Subset 1:  → sum = 11[1][5]
 * Subset 2:  → sum = 11[1]
 * 
 * This is 0/1 Knapsack variant!
 * Can we find subset with sum = total/2?
 * 
 * DP Table:
 * 
 *       sum: 0  1  2  3  4  5  6  7  8  9  10 11
 *            -------------------------------------
 * num 0 |   T  F  F  F  F  F  F  F  F  F  F  F
 * num 1 |   T  T  F  F  F  F  F  F  F  F  F  F  (1)
 * num 5 |   T  T  F  F  F  T  T  F  F  F  F  F  (5)
 * num 11|   T  T  F  F  F  T  T  F  F  F  F  T  (11)
 * num 5 |   T  T  F  F  F  T  T  F  F  F  T  T  (5)
 * 
 * Recurrence:
 * dp[i][j] = dp[i-1][j] OR dp[i-1][j-nums[i-1]]
 * 
 * Time Complexity: O(n × sum)
 * Space Complexity: O(n × sum) → O(sum) optimized
 */
public class PartitionEqualSubsetSum {
    
    // Approach 1: Recursion - O(2ⁿ) time
    public static boolean canPartitionRecursive(int[] nums, int target, int index) {
        if (target == 0) {
            return true;
        }
        
        if (index >= nums.length || target < 0) {
            return false;
        }
        
        // Include or exclude current number
        return canPartitionRecursive(nums, target - nums[index], index + 1) ||
               canPartitionRecursive(nums, target, index + 1);
    }
    
    // Approach 2: Memoization - O(n × sum) time, O(n × sum) space
    public static boolean canPartitionMemo(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        
        // If sum is odd, cannot partition
        if (sum % 2 != 0) {
            return false;
        }
        
        int target = sum / 2;
        Boolean[][] memo = new Boolean[nums.length][target + 1];
        
        return canPartitionMemoHelper(nums, target, 0, memo);
    }
    
    private static boolean canPartitionMemoHelper(int[] nums, int target, 
                                                  int index, Boolean[][] memo) {
        if (target == 0) {
            return true;
        }
        
        if (index >= nums.length || target < 0) {
            return false;
        }
        
        if (memo[index][target] != null) {
            return memo[index][target];
        }
        
        memo[index][target] = canPartitionMemoHelper(nums, target - nums[index], index + 1, memo) ||
                             canPartitionMemoHelper(nums, target, index + 1, memo);
        
        return memo[index][target];
    }
    
    // Approach 3: Tabulation - O(n × sum) time, O(n × sum) space
    public static boolean canPartitionTabulation(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        
        if (sum % 2 != 0) {
            return false;
        }
        
        int target = sum / 2;
        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][target + 1];
        
        // Base case: sum 0 is always possible (empty subset)
        for (int i = 0; i <= n; i++) {
            dp[i] = true;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j]; // Exclude current number
                
                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i - 1]]; // Include
                }
            }
        }
        
        return dp[n][target];
    }
    
    // Approach 4: Space Optimized - O(n × sum) time, O(sum) space
    public static boolean canPartitionOptimized(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        
        if (sum % 2 != 0) {
            return false;
        }
        
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp = true;
        
        for (int num : nums) {
            // Traverse from right to left
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }
        
        return dp[target];
    }
    
    // Variation: Partition to K Equal Sum Subsets
    // LeetCode: 698
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        
        if (sum % k != 0) {
            return false;
        }
        
        int target = sum / k;
        boolean[] used = new boolean[nums.length];
        
        return canPartitionKSubsetsHelper(nums, 0, k, 0, target, used);
    }
    
    private static boolean canPartitionKSubsetsHelper(int[] nums, int start, 
                                                      int k, int currentSum, 
                                                      int target, boolean[] used) {
        if (k == 1) {
            return true; // Last subset will automatically be valid
        }
        
        if (currentSum == target) {
            return canPartitionKSubsetsHelper(nums, 0, k - 1, 0, target, used);
        }
        
        for (int i = start; i < nums.length; i++) {
            if (!used[i] && currentSum + nums[i] <= target) {
                used[i] = true;
                
                if (canPartitionKSubsetsHelper(nums, i + 1, k, currentSum + nums[i], target, used)) {
                    return true;
                }
                
                used[i] = false;
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        int[] nums1 = {1, 5, 11, 5};
        System.out.println("Array: " + java.util.Arrays.toString(nums1));
        System.out.println("Can partition (Memo): " + canPartitionMemo(nums1));
        System.out.println("Can partition (Tabulation): " + canPartitionTabulation(nums1));
        System.out.println("Can partition (Optimized): " + canPartitionOptimized(nums1));
        
        int[] nums2 = {1, 2, 3, 5};
        System.out.println("\nArray: " + java.util.Arrays.toString(nums2));
        System.out.println("Can partition: " + canPartitionOptimized(nums2));
        
        // K subsets
        int[] nums3 = {4, 3, 2, 3, 5, 2, 1};
        int k = 4;
        System.out.println("\nPartition to " + k + " subsets: " + 
                          canPartitionKSubsets(nums3, k));
    }
}
```

---

### Problem 9: Longest Increasing Subsequence

**Question:** Given an integer array nums, return the length of the longest strictly increasing subsequence.

```java
/**
 * Problem: Longest Increasing Subsequence (LIS)
 * LeetCode: 300
 * 
 * Visual:
 * 
 * nums =[2][3][5][7][9][10][18][101]
 * 
 * LIS:  or  (length 4)[2][3][7][18][101]
 * 
 * DP Approach:
 * dp[i] = length of LIS ending at index i
 * 
 * DP Table:
 * index:  0   1   2   3   4   5   6    7
 * nums:[2][3][5][7][9][10][18][101]
 * dp:     1   1   1   2   2   3   4    4
 * 
 * Recurrence:
 * dp[i] = max(dp[j] + 1) for all j < i where nums[j] < nums[i]
 * 
 * Binary Search Approach:
 * Maintain array of smallest tail elements for each length
 * 
 * Time Complexity:
 * - DP: O(n²)
 * - Binary Search: O(n log n)
 * 
 * Space Complexity: O(n)
 */
public class LongestIncreasingSubsequence {
    
    // Approach 1: Recursion - O(2ⁿ) time
    public static int lengthOfLISRecursive(int[] nums, int index, int prevIndex) {
        if (index == nums.length) {
            return 0;
        }
        
        // Exclude current element
        int exclude = lengthOfLISRecursive(nums, index + 1, prevIndex);
        
        // Include current element (if valid)
        int include = 0;
        if (prevIndex == -1 || nums[index] > nums[prevIndex]) {
            include = 1 + lengthOfLISRecursive(nums, index + 1, index);
        }
        
        return Math.max(exclude, include);
    }
    
    // Approach 2: DP - O(n²) time, O(n) space
    public static int lengthOfLISDP(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int[] dp = new int[n];
        java.util.Arrays.fill(dp, 1); // Each element is LIS of length 1
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        // Find maximum in dp array
        int maxLIS = 0;
        for (int len : dp) {
            maxLIS = Math.max(maxLIS, len);
        }
        
        return maxLIS;
    }
    
    // Approach 3: Binary Search - O(n log n) time, O(n) space
    public static int lengthOfLISBinarySearch(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // tails[i] = smallest tail element for LIS of length i+1
        int[] tails = new int[nums.length];
        int size = 0;
        
        for (int num : nums) {
            // Binary search for first element >= num
            int left = 0, right = size;
            
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            
            tails[left] = num;
            
            if (left == size) {
                size++;
            }
        }
        
        return size;
    }
    
    // Get the actual LIS
    public static java.util.List<Integer> getLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new java.util.ArrayList<>();
        }
        
        int n = nums.length;
        int[] dp = new int[n];
        int[] parent = new int[n];
        java.util.Arrays.fill(dp, 1);
        java.util.Arrays.fill(parent, -1);
        
        int maxLen = 1;
        int maxIndex = 0;
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                }
            }
            
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxIndex = i;
            }
        }
        
        // Backtrack to find LIS
        java.util.List<Integer> lis = new java.util.ArrayList<>();
        int current = maxIndex;
        
        while (current != -1) {
            lis.add(nums[current]);
            current = parent[current];
        }
        
        java.util.Collections.reverse(lis);
        return lis;
    }
    
    // Variation: Longest Continuous Increasing Subsequence
    // LeetCode: 674
    public static int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int maxLen = 1;
        int currentLen = 1;
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                currentLen++;
                maxLen = Math.max(maxLen, currentLen);
            } else {
                currentLen = 1;
            }
        }
        
        return maxLen;
    }
    
    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        System.out.println("LIS length (DP): " + lengthOfLISDP(nums));
        System.out.println("LIS length (Binary Search): " + lengthOfLISBinarySearch(nums));
        
        java.util.List<Integer> lis = getLIS(nums);
        System.out.println("LIS: " + lis);
        
        // Continuous increasing
        int[] nums2 = {1, 3, 5, 4, 7};
        System.out.println("\nArray: " + java.util.Arrays.toString(nums2));
        System.out.println("Longest continuous increasing: " + findLengthOfLCIS(nums2));
    }
}
```

---

### Problem 10: Edit Distance

**Question:** Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2. You can insert, delete, or replace a character.

```java
/**
 * Problem: Edit Distance (Levenshtein Distance)
 * LeetCode: 72
 * 
 * Visual:
 * 
 * word1 = "horse"
 * word2 = "ros"
 * 
 * Operations:
 * horse → rorse (replace 'h' with 'r')
 * rorse → rose (remove 'r')
 * rose → ros (remove 'e')
 * 
 * Total: 3 operations
 * 
 * DP Table:
 *       ""  r   o   s
 *       0   1   2   3
 * ""    0   1   2   3
 * h     1   1   2   3
 * o     2   2   1   2
 * r     3   2   2   2
 * s     4   3   3   2
 * e     5   4   4   3
 * 
 * Recurrence:
 * if word1[i-1] == word2[j-1]:
 *     dp[i][j] = dp[i-1][j-1]
 * else:
 *     dp[i][j] = 1 + min(dp[i-1][j],    // Delete
 *                        dp[i][j-1],    // Insert
 *                        dp[i-1][j-1])  // Replace
 * 
 * Time Complexity: O(m × n)
 * Space Complexity: O(m × n) → O(min(m, n)) optimized
 */
public class EditDistance {
    
    // Approach 1: Recursion - O(3^(m+n)) time
    public static int minDistanceRecursive(String word1, String word2, 
                                         int i, int j) {
        // Base cases
        if (i == 0) {
            return j; // Insert all remaining characters
        }
        if (j == 0) {
            return i; // Delete all remaining characters
        }
        
        // If characters match, no operation needed
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
            return minDistanceRecursive(word1, word2, i - 1, j - 1);
        }
        
        // Try all three operations
        int insert = minDistanceRecursive(word1, word2, i, j - 1);
        int delete = minDistanceRecursive(word1, word2, i - 1, j);
        int replace = minDistanceRecursive(word1, word2, i - 1, j - 1);
        
        return 1 + Math.min(insert, Math.min(delete, replace));
    }
    
    // Approach 2: Memoization - O(m × n) time, O(m × n) space
    public static int minDistanceMemo(String word1, String word2) {
        int[][] memo = new int[word1.length() + 1][word2.length() + 1];
        
        for (int[] row : memo) {
            java.util.Arrays.fill(row, -1);
        }
        
        return minDistanceMemoHelper(word1, word2, word1.length(), 
                                    word2.length(), memo);
    }
    
    private static int minDistanceMemoHelper(String word1, String word2, 
                                            int i, int j, int[][] memo) {
        if (i == 0) {
            return j;
        }
        if (j == 0) {
            return i;
        }
        
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
            memo[i][j] = minDistanceMemoHelper(word1, word2, i - 1, j - 1, memo);
        } else {
            int insert = minDistanceMemoHelper(word1, word2, i, j - 1, memo);
            int delete = minDistanceMemoHelper(word1, word2, i - 1, j, memo);
            int replace = minDistanceMemoHelper(word1, word2, i - 1, j - 1, memo);
            
            memo[i][j] = 1 + Math.min(insert, Math.min(delete, replace));
        }
        
        return memo[i][j];
    }
    
    // Approach 3: Tabulation - O(m × n) time, O(m × n) space
    public static int minDistanceTabulation(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        int[][] dp = new int[m + 1][n + 1];
        
        // Base cases
        for (int j = 0; j <= n; j++) {
            dp[j] = j; // Insert j characters
        }
        for (int i = 0; i <= m; i++) {
            dp[i] = i; // Delete i characters
        }
        
        // Fill DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int insert = dp[i][j - 1];
                    int delete = dp[i - 1][j];
                    int replace = dp[i - 1][j - 1];
                    
                    dp[i][j] = 1 + Math.min(insert, Math.min(delete, replace));
                }
            }
        }
        
        return dp[m][n];
    }
    
    // Approach 4: Space Optimized - O(m × n) time, O(min(m, n)) space
    public static int minDistanceOptimized(String word1, String word2) {
        // Make sure word2 is shorter for space optimization
        if (word1.length() < word2.length()) {
            return minDistanceOptimized(word2, word1);
        }
        
        int m = word1.length();
        int n = word2.length();
        
        int[] prev = new int[n + 1];
        int[] curr = new int[n + 1];
        
        // Base case
        for (int j = 0; j <= n; j++) {
            prev[j] = j;
        }
        
        for (int i = 1; i <= m; i++) {
            curr = i;
            
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    curr[j] = prev[j - 1];
                } else {
                    curr[j] = 1 + Math.min(curr[j - 1],    // Insert
                                          Math.min(prev[j],    // Delete
                                                  prev[j - 1])); // Replace
                }
            }
            
            // Swap arrays
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        
        return prev[n];
    }
    
    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        
        System.out.println("Word1: " + word1);
        System.out.println("Word2: " + word2);
        
        System.out.println("Min distance (Recursive): " + 
                          minDistanceRecursive(word1, word2, word1.length(), word2.length()));
        System.out.println("Min distance (Memo): " + minDistanceMemo(word1, word2));
        System.out.println("Min distance (Tabulation): " + minDistanceTabulation(word1, word2));
        System.out.println("Min distance (Optimized): " + minDistanceOptimized(word1, word2));
        
        // More examples
        String w1 = "intention", w2 = "execution";
        System.out.println("\n\"" + w1 + "\" → \"" + w2 + "\": " + 
                          minDistanceTabulation(w1, w2));
    }
}
```

---

## 📝 Practice Problems (Part 2)

### Medium
| Problem | Pattern | Link |
|---------|---------|------|
| Partition Equal Subset Sum | 0/1 Knapsack | LeetCode 416 |
| Longest Increasing Subsequence | Linear DP | LeetCode 300 |
| Coin Change II | Unbounded Knapsack | LeetCode 518 |
| Target Sum | 0/1 Knapsack | LeetCode 494 |
| Perfect Squares | Unbounded Knapsack | LeetCode 279 |

### Hard
| Problem | Pattern | Link |
|---------|---------|------|
| Edit Distance | 2D DP | LeetCode 72 |
| Regular Expression Matching | 2D DP | LeetCode 10 |
| Burst Balloons | Interval DP | LeetCode 312 |
| Distinct Subsequences | 2D DP | LeetCode 115 |
| Minimum Window Subsequence | 2D DP | LeetCode 727 |

---

## ✅ Key Takeaways

1. **0/1 Knapsack** - Each item once, traverse backwards for space optimization
2. **Unbounded Knapsack** - Items unlimited, traverse forwards
3. **LIS** - O(n²) DP or O(n log n) binary search
4. **LCS Pattern** - String matching, 2D DP
5. **Partition Problems** - Subset sum variant
6. **Edit Distance** - 3 operations (insert, delete, replace)
7. **Space Optimization** - Reduce 2D to 1D when possible

---

**Previous:** [DP Part 1](./01-theory.md)  
**Next:** [Backtracking](../backtracking/01-theory.md)