# 🚀 DSA Pattern 43 — Dynamic Programming: 1D DP

> **Goal:** 1D Dynamic Programming ko product-based company interview level par samajhna — real-world intuition, kyun DP use hota hai, kaise identify karein, memoization vs tabulation, state design, transition, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Dynamic Programming Kya Hai?

Dynamic Programming — DP — ek technique hai jisme hum:

```text
1. Badi problem ko chhoti subproblems mein todte hain
2. Har subproblem ka answer ek baar calculate karte hain
3. Answer store karte hain
4. Same subproblem dobara solve nahi karte
```

Example:

```text
Climbing Stairs:

1 step ya 2 step jump kar sakte ho.

Ways to reach stair 5:
ways(5) = ways(4) + ways(3)
```

Kyunki:

```text
Stair 5 par pahunchne ke do tarike:
1. Stair 4 se 1 step
2. Stair 3 se 2 steps
```

---

## 2. DP Kyun Use Karte Hain?

Main reason: **repeated subproblems avoid karna.**

Example — Fibonacci naive recursion:

```text
fib(5)
= fib(4) + fib(3)

fib(4)
= fib(3) + fib(2)

fib(3)
= fib(2) + fib(1)
```

`fib(3)` multiple baar calculate ho raha hai.

Naive recursion:

```text
Time: O(2^n)
```

DP:

```text
Time: O(n)
Space: O(n)
```

### Core Intuition

> Jo answer ek baar nikal chuka hai, use store kar lo. Dobara calculate mat karo.

---

## 3. Real-World Production Examples

| Real System | DP Use |
|---|---|
| Payment System | Minimum transaction cost |
| E-commerce | Best discount combination |
| Delivery System | Minimum delivery cost |
| Ride Sharing | Fare optimization |
| Ad System | Best ad selection |
| Recommendation System | Best item sequence |
| Cloud Cost Optimizer | Minimum resource cost |
| Logistics | Minimum route cost |
| Game Development | Best move sequence |
| Compiler | Register allocation |
| Finance | Portfolio optimization |
| Scheduling System | Minimum waiting time |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Delivery app mein rider ko minimum number of moves mein destination tak pahunchana hai. Kaise karoge?”

Answer:

```text
Har position ek state hai.

dp[position] = minimum moves to reach position

Transition:
dp[position] = min(dp[position - 1], dp[position - 2]) + 1
```

Ya:

> “E-commerce mein user ke budget ke andar maximum value wala product combination chahiye. Kaise karoge?”

Answer:

```text
0/1 Knapsack DP use karenge.

dp[budget] = maximum value achievable with that budget.
```

---

## 4. Kaise Pata Chalega Ki DP Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible DP |
|---|---|
| Minimum / maximum value | DP |
| Number of ways | DP |
| Count possible ways | DP |
| Choices at every step | DP |
| Overlapping subproblems | DP |
| Optimal substructure | DP |
| Can reach / can partition | DP |
| Longest / shortest sequence | DP |

### Common Keywords

- Minimum
- Maximum
- Number of ways
- Count ways
- Can reach
- Can partition
- Longest
- Shortest
- Optimal
- Choose

### Mental Checklist

1. Kya problem ko chhoti subproblems mein tod sakte hain?  
2. Kya same subproblem repeat ho rahi hai?  
3. Kya current answer previous answers par depend karta hai?  
4. Kya choices hain?  
5. Kya optimal substructure hai?  

Agar 2–3 answers **yes** hain, to DP try karo.

---

# 5. DP Ke 2 Approaches

## 1. Top-Down — Memoization

```text
Recursion + Cache
```

```java
dp[state] = answer
```

Pehle recursion se solve karo, answer store karo.

## 2. Bottom-Up — Tabulation

```text
Iteration + Table
```

Chhoti subproblems se start karo, badi problem tak jao.

```java
dp = baseCase;
dp[i] = transition(dp[i - 1], dp[i - 2]);
```

---

# 6. DP Problem Solve Karne Ke 5 Steps

```text
1. State define karo
2. Transition likho
3. Base case likho
4. Order of computation decide karo
5. Final answer nikalo
```

Example — Climbing Stairs:

```text
State:
dp[i] = ways to reach stair i

Transition:
dp[i] = dp[i - 1] + dp[i - 2]

Base case:
dp = 1
dp = 1

Final answer:
dp[n]
```

---

# 7. Example 1: Climbing Stairs

## Problem

Tum `1` ya `2` steps jump kar sakte ho.

`n` stairs climb karne ke total ways nikalo.

```text
n = 3
Output = 3

Ways:
1 + 1 + 1
1 + 2
2 + 1
```

## Why DP?

```text
ways(n) = ways(n - 1) + ways(n - 2)
```

Same subproblems repeat hoti hain.

## Naive Recursion

```java
public int climbStairs(int n) {
    if (n <= 2) {
        return n;
    }

    return climbStairs(n - 1) + climbStairs(n - 2);
}
```

Time: \(O(2^n)\)

## Memoization

```java
public int climbStairsMemo(int n) {
    int[] memo = new int[n + 1];

    return helper(n, memo);
}

private int helper(int n, int[] memo) {
    if (n <= 2) {
        return n;
    }

    if (memo[n] != 0) {
        return memo[n];
    }

    memo[n] = helper(n - 1, memo) + helper(n - 2, memo);

    return memo[n];
}
```

Time: \(O(n)\)  
Space: \(O(n)\)

## Tabulation

```java
public int climbStairs(int n) {
    if (n <= 2) {
        return n;
    }

    int[] dp = new int[n + 1];

    dp = 1;
    dp = 2;

    for (int i = 3; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2];
    }

    return dp[n];
}
```

## Space Optimized

```java
public int climbStairsOptimized(int n) {
    if (n <= 2) {
        return n;
    }

    int first = 1;
    int second = 2;

    for (int i = 3; i <= n; i++) {
        int current = first + second;

        first = second;
        second = current;
    }

    return second;
}
```

## Dry Run

```text
n = 5

dp = 1
dp = 2

dp = dp + dp = 3
dp = dp + dp = 5
dp = dp + dp = 8

Answer = 8
```

## Complexity

- Tabulation: \(O(n)\), \(O(n)\)
- Space optimized: \(O(n)\), \(O(1)\)

---

# 8. Example 2: House Robber

## Problem

Houses mein paise rakhe hain.

Adjacent houses rob nahi kar sakte.

Maximum money nikalo.

```text
nums =[1][2][3][7][9]
Output = 12
Explanation: 2 + 9 + 1
```

## Why DP?

Har house ke liye do choices:

```text
1. Rob current house
2. Skip current house
```

```text
dp[i] = maximum money till house i
```

## Transition

```text
dp[i] = max(
    dp[i - 1],
    dp[i - 2] + nums[i]
)
```

## Solution

```java
public int rob(int[] nums) {
    int n = nums.length;

    if (n == 1) {
        return nums;
    }

    int[] dp = new int[n];

    dp = nums;
    dp = Math.max(nums, nums);

    for (int i = 2; i < n; i++) {
        dp[i] = Math.max(
            dp[i - 1],
            dp[i - 2] + nums[i]
        );
    }

    return dp[n - 1];
}
```

## Space Optimized

```java
public int robOptimized(int[] nums) {
    int prev2 = 0;
    int prev1 = 0;

    for (int num : nums) {
        int current = Math.max(prev1, prev2 + num);

        prev2 = prev1;
        prev1 = current;
    }

    return prev1;
}
```

## Dry Run

```text
nums =[1][2][3][7][9]

dp = 2
dp = max(2, 7) = 7

dp = max(dp, dp + 9)
      = max(7, 11)
      = 11

dp = max(dp, dp + 3)
      = max(11, 10)
      = 11

dp = max(dp, dp + 1)
      = max(11, 12)
      = 12

Answer = 12
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\) / \(O(1)\)

---

# 9. Example 3: Coin Change

## Problem

Coins diye hain aur amount diya hai.

Minimum number of coins nikalo jisse amount bane.

Agar possible nahi, `-1` return karo.

```text
coins =[1][2][5]
amount = 11

Output = 3
Explanation: 5 + 5 + 1
```

## Why DP?

```text
dp[amount] = minimum coins to make amount
```

## Transition

```text
dp[i] = min(dp[i - coin] + 1)
for every coin <= i
```

## Solution

```java
public int coinChange(int[] coins, int amount) {
    int[] dp = new int[amount + 1];

    Arrays.fill(dp, amount + 1);

    dp = 0;

    for (int i = 1; i <= amount; i++) {
        for (int coin : coins) {
            if (coin <= i) {
                dp[i] = Math.min(
                    dp[i],
                    dp[i - coin] + 1
                );
            }
        }
    }

    return dp[amount] > amount ? -1 : dp[amount];
}
```

## Dry Run

```text
coins =[1][2][5]
amount = 11

dp = 0

dp = dp + 1 = 1
dp = min(dp + 1, dp + 1) = 1
dp = dp + 1 = 2
dp = min(dp + 1, dp + 1) = 2
dp = min(dp + 1, dp + 1, dp + 1) = 1

dp = dp + 1 = 2
dp = dp + 1 = 3? 
```

Correct:

```text
dp = min(dp + 1, dp + 1, dp + 1)
      = min(3, 2, 2)
      = 2
```

Continue:

```text
dp = 3

Answer = 3
```

## Complexity

- Time: \(O(amount \times coins)\)
- Space: \(O(amount)\)

---

# 10. Example 4: Minimum Cost Climbing Stairs

## Problem

Har stair par cost hai.

`1` ya `2` step jump kar sakte ho.

Top tak pahunchne ka minimum cost nikalo.

```text
cost =[10][15][20]
Output = 15
Explanation:
Start at index 1
Pay 15
Jump 2 steps to top
```

## Why DP?

```text
dp[i] = minimum cost to reach stair i
```

## Transition

```text
dp[i] = min(dp[i - 1], dp[i - 2]) + cost[i]
```

## Solution

```java
public int minCostClimbingStairs(int[] cost) {
    int n = cost.length;

    int[] dp = new int[n];

    dp = cost;
    dp = cost;

    for (int i = 2; i < n; i++) {
        dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
    }

    return Math.min(dp[n - 1], dp[n - 2]);
}
```

## Dry Run

```text
cost =[10][15][20]

dp = 10
dp = 15

dp = min(15, 10) + 20
      = 30

Answer = min(dp, dp)
       = min(30, 15)
       = 15
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\)

---

# 11. Example 5: Maximum Subarray — Kadane’s as DP

## Problem

Maximum sum contiguous subarray nikalo.

```text
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
Output = 6
```

## Why DP?

```text
dp[i] = maximum subarray sum ending at index i
```

## Transition

```text
dp[i] = max(nums[i], dp[i - 1] + nums[i])
```

## Solution

```java
public int maxSubArray(int[] nums) {
    int[] dp = new int[nums.length];

    dp = nums;

    int answer = dp;

    for (int i = 1; i < nums.length; i++) {
        dp[i] = Math.max(
            nums[i],
            dp[i - 1] + nums[i]
        );

        answer = Math.max(answer, dp[i]);
    }

    return answer;
}
```

## Dry Run

```text
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]

dp = -2
dp = max(1, -2 + 1) = 1
dp = max(-3, 1 - 3) = -2
dp = max(4, -2 + 4) = 4
dp = max(-1, 4 - 1) = 3
dp = max(2, 3 + 2) = 5
dp = max(1, 5 + 1) = 6
dp = max(-5, 6 - 5) = 1
dp = max(4, 1 + 4) = 5

Answer = 6
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\) / \(O(1)\)

---

# 12. Example 6: Decode Ways

## Problem

Encoded string ko decode karna hai.

```text
'A' -> 1
'B' -> 2
...
'Z' -> 26
```

Total decoding ways nikalo.

```text
s = "226"
Output = 3

Ways:
2 2 6 -> BBF
22 6 -> VF
2 26 -> BZ
```

## Why DP?

```text
dp[i] = number of ways to decode first i characters
```

## Transition

```text
Single digit valid:
dp[i] += dp[i - 1]

Two digit valid:
dp[i] += dp[i - 2]
```

## Solution

```java
public int numDecodings(String s) {
    if (s.charAt(0) == '0') {
        return 0;
    }

    int n = s.length();

    int[] dp = new int[n + 1];

    dp = 1;
    dp = 1;

    for (int i = 2; i <= n; i++) {

        int oneDigit =
            Integer.parseInt(s.substring(i - 1, i));

        int twoDigit =
            Integer.parseInt(s.substring(i - 2, i));

        if (oneDigit >= 1) {
            dp[i] += dp[i - 1];
        }

        if (twoDigit >= 10 && twoDigit <= 26) {
            dp[i] += dp[i - 2];
        }
    }

    return dp[n];
}
```

## Dry Run

```text
s = "226"

dp = 1
dp = 1

i = 2:
oneDigit = 2
dp += dp = 1

twoDigit = 22
dp += dp = 2

i = 3:
oneDigit = 6
dp += dp = 2

twoDigit = 26
dp += dp = 3

Answer = 3
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\)

---

# 13. 1D DP Patterns

| Pattern | State | Transition |
|---|---|---|
| Fibonacci style | `dp[i]` | `dp[i - 1] + dp[i - 2]` |
| House Robber | `dp[i]` | `max(dp[i - 1], dp[i - 2] + value)` |
| Coin Change | `dp[amount]` | `min(dp[amount - coin] + 1)` |
| Kadane’s | `dp[i]` | `max(num, dp[i - 1] + num)` |
| Decode Ways | `dp[i]` | `dp[i - 1] + dp[i - 2]` |
| Jump Game | `dp[i]` | Reachability |

---

# 14. Memoization vs Tabulation

| Feature | Memoization | Tabulation |
|---|---|---|
| Approach | Top-down | Bottom-up |
| Style | Recursion | Iteration |
| Stack overflow | Possible | No |
| Space | Recursion stack + DP | DP only |
| Best for | Complex states | Simple states |
| Example | Decode Ways | Climbing Stairs |

### Rule

```text
Simple state -> Tabulation
Complex state / tree-like recursion -> Memoization
```

---

# 15. Common Mistakes

## Mistake 1: Base Case Bhoolna

```java
dp = 0;
dp = 1;
```

Base case galat hone par pura answer galat hoga.

---

## Mistake 2: State Galat Define Karna

Coin Change mein:

```text
dp[i] = minimum coins for amount i
```

Galat state:

```text
dp[i] = coins[i]
```

---

## Mistake 3: Transition Galat Likna

Climbing Stairs:

```java
dp[i] = dp[i - 1] + dp[i - 2];
```

House Robber:

```java
dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
```

---

## Mistake 4: Initialization Galat Karna

Coin Change:

```java
Arrays.fill(dp, amount + 1);
dp = 0;
```

`0` se initialize karna galat hai.

---

## Mistake 5: Space Optimization Mein Order Galat Karna

House Robber space optimized:

```java
int current = Math.max(prev1, prev2 + num);

prev2 = prev1;
prev1 = current;
```

Order galat hone par values corrupt ho jayengi.

---

# 16. DP Decision Flow

```text
Problem padho
      |
      v
Kya minimum/maximum/count chahiye?
      |
      +-- No --> DP probably nahi
      |
      +-- Yes
          |
          v
Kya choices hain?
      |
      +-- Yes
          |
          v
Kya overlapping subproblems hain?
      |
      +-- Yes --> DP
      |
      +-- Kya only previous 1-2 states chahiye?
              |
              +-- Yes --> Space optimized DP
              |
              +-- Kya multiple states hain?
                      |
                      +-- Yes --> 2D DP / Memoization
```

---

# 17. Important Problems

## Easy

- Climbing Stairs
- Min Cost Climbing Stairs
- Fibonacci Number
- Divisor Game

## Medium

- House Robber
- House Robber II
- Coin Change
- Maximum Subarray
- Decode Ways
- Jump Game
- Longest Increasing Subsequence
- Partition Equal Subset Sum

## Hard

- Coin Change II
- Decode Ways II
- Jump Game II
- Maximum Product Subarray
- Best Time to Buy and Sell Stock III

---

# 18. One-Line Recognition

> **Agar problem minimum/maximum value, number of ways, ya optimal choice maang rahi hai aur