# 🚀 DSA Pattern 45 — Knapsack DP

> **Goal:** Knapsack Dynamic Programming ko product-based company interview level par samajhna — real-world intuition, 0/1 Knapsack, Unbounded Knapsack, Subset Sum, Partition Equal Subset Sum, Coin Change, state design, transition, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Knapsack DP Kya Hai?

Knapsack DP ek classic DP pattern hai jisme hum:

```text
1. Limited capacity ke andar
2. Items choose karne hain
3. Maximum value / minimum cost nikalna hai
```

### Simple Example

```text
Bag capacity = 7

Items:
Item 1: weight 1, value 1
Item 2: weight 3, value 4
Item 3: weight 4, value 5
Item 4: weight 5, value 7

Question:
Maximum value kya nikal sakte ho bina capacity cross kiye?
```

Answer:

```text
Item 2 + Item 3

weight = 3 + 4 = 7
value = 4 + 5 = 9
```

---

## 2. Knapsack Kyun Use Karte Hain?

Main reasons:

1. Limited resource optimization
2. Budget based selection
3. Capacity based selection
4. Maximum profit
5. Minimum cost
6. Subset selection
7. Partition problems
8. Coin change problems

### Core Intuition

> Har item ke liye decide karo — lena hai ya nahi lena.

Example:

```text
Current item:
weight = 3
value = 4

Choice 1: Item lo
Choice 2: Item skip karo
```

---

## 3. Real-World Production Examples

| Real System | Knapsack Use |
|---|---|
| E-commerce | Best product combination under budget |
| Cloud Cost Optimizer | Best resources under budget |
| Ad System | Best ads under budget |
| Delivery System | Best packages under truck capacity |
| Logistics | Best items under weight limit |
| Finance | Best investment under risk limit |
| Recommendation System | Best items under user budget |
| Game Development | Best items under inventory capacity |
| Manufacturing | Best materials under cost limit |
| Scheduling System | Best tasks under time limit |
| Marketing | Best campaigns under budget |
| Travel Planning | Best activities under time budget |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “E-commerce app mein user ke budget ke andar maximum value wala product combination chahiye. Kaise karoge?”

Answer:

```text
Products = items
Budget = capacity
Product value = value
Product price = weight

0/1 Knapsack DP use karenge.

dp[budget] = maximum value achievable with that budget.
```

Ya:

> “Cloud provider ke paas fixed budget hai. Best combination of servers choose karne hain jisse performance maximize ho. Kaise approach karoge?”

Answer:

```text
Servers = items
Cost = weight
Performance = value
Budget = capacity

Knapsack DP se maximum performance nikal sakte hain.
```

---

## 4. Kaise Pata Chalega Ki Knapsack DP Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Knapsack |
|---|---|
| Capacity / budget | Knapsack |
| Weight / cost | Knapsack |
| Value / profit | Knapsack |
| Maximum value | 0/1 Knapsack |
| Minimum coins | Unbounded Knapsack |
| Can partition | Subset Sum |
| Equal partition | Partition Equal Subset Sum |
| Number of ways | Count Knapsack |

### Common Keywords

- Capacity
- Budget
- Weight
- Value
- Profit
- Maximum value
- Minimum cost
- Can partition
- Equal subset
- Coin change
- Number of ways

### Mental Checklist

1. Kya capacity / budget hai?  
2. Kya items hain?  
3. Kya har item ka weight/cost hai?  
4. Kya har item ki value/profit hai?  
5. Kya maximum value / minimum cost chahiye?  

Agar 3–4 answers **yes** hain, to Knapsack DP try karo.

---

# 5. Knapsack Ke Types

| Type | Item Repeat? | Example |
|---|---|---|
| 0/1 Knapsack | No | House Robber, Subset Sum |
| Unbounded Knapsack | Yes | Coin Change |
| Bounded Knapsack | Limited times | Limited stock problem |
| Fractional Knapsack | Fraction allowed | Greedy problem |

---

# 6. 0/1 Knapsack

## Problem

Items diye hain.

Har item ka weight aur value hai.

Maximum value nikalo jisse total weight capacity ke andar rahe.

```text
weights =[1][3][4][5]
values =[1][4][5][7]
capacity = 7

Output = 9
```

## Why DP?

Har item ke liye do choices:

```text
1. Item lo
2. Item skip karo
```

## State

```text
dp[i][w] = maximum value using first i items with capacity w
```

## Transition

```text
If weight[i - 1] <= w:
    dp[i][w] = max(
        dp[i - 1][w],
        dp[i - 1][w - weight[i - 1]] + value[i - 1]
    )

Else:
    dp[i][w] = dp[i - 1][w]
```

## Solution

```java
public int knapsack(int[] weights, int[] values, int capacity) {
    int n = weights.length;

    int[][] dp = new int[n + 1][capacity + 1];

    for (int i = 1; i <= n; i++) {
        for (int w = 0; w <= capacity; w++) {

            dp[i][w] = dp[i - 1][w];

            if (weights[i - 1] <= w) {
                dp[i][w] = Math.max(
                    dp[i][w],
                    dp[i - 1][w - weights[i - 1]] + values[i - 1]
                );
            }
        }
    }

    return dp[n][capacity];
}
```

## Dry Run

```text
weights =[1][3][4][5]
values =[4][5][7][1]
capacity = 7

Best combination:
item 2 + item 3

weight = 3 + 4 = 7
value = 4 + 5 = 9

Answer = 9
```

## Complexity

- Time: \(O(n \times capacity)\)
- Space: \(O(n \times capacity)\)

## Space Optimized

```java
public int knapsackOptimized(int[] weights, int[] values, int capacity) {
    int[] dp = new int[capacity + 1];

    for (int i = 0; i < weights.length; i++) {
        for (int w = capacity; w >= weights[i]; w--) {
            dp[w] = Math.max(
                dp[w],
                dp[w - weights[i]] + values[i]
            );
        }
    }

    return dp[capacity];
}
```

### Important

```java
for (int w = capacity; w >= weights[i]; w--)
```

Reverse loop isliye chahiye taaki same item do baar use na ho.

---

# 7. Unbounded Knapsack

## Problem

Items unlimited times use kar sakte ho.

Maximum value nikalo.

```text
weights =[1][3][4][5]
values =[1][4][5][7]
capacity = 7

Output = 9? 
```

Actually best:

```text
item 2 + item 2 = weight 6, value 8
item 3 + item 2 = weight 7, value 9
```

Answer = 9.

## State

```text
dp[w] = maximum value with capacity w
```

## Transition

```text
dp[w] = max(dp[w], dp[w - weight[i]] + value[i])
```

## Solution

```java
public int unboundedKnapsack(int[] weights, int[] values, int capacity) {
    int[] dp = new int[capacity + 1];

    for (int w = 1; w <= capacity; w++) {
        for (int i = 0; i < weights.length; i++) {
            if (weights[i] <= w) {
                dp[w] = Math.max(
                    dp[w],
                    dp[w - weights[i]] + values[i]
                );
            }
        }
    }

    return dp[capacity];
}
```

## Complexity

- Time: \(O(capacity \times items)\)
- Space: \(O(capacity)\)

---

# 8. Coin Change — Minimum Coins

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

## Why Unbounded Knapsack?

Coins unlimited times use ho sakte hain.

## State

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

dp = 1
dp = 1
dp = 2
dp = 2
dp = 1

dp = 2
dp = 2
dp = 3
dp = 3
dp = 2
dp = 3

Answer = 3
```

## Complexity

- Time: \(O(amount \times coins)\)
- Space: \(O(amount)\)

---

# 9. Coin Change II — Number of Ways

## Problem

Coins diye hain aur amount diya hai.

Number of ways nikalo jisse amount bane.

```text
coins =[1][2][5]
amount = 5

Output = 4

Ways:
1 + 1 + 1 + 1 + 1
1 + 1 + 1 + 2
1 + 2 + 2
5
```

## Why DP?

```text
dp[amount] = number of ways to make amount
```

## Transition

```text
dp[i] += dp[i - coin]
```

## Solution

```java
public int change(int amount, int[] coins) {
    int[] dp = new int[amount + 1];

    dp = 1;

    for (int coin : coins) {
        for (int i = coin; i <= amount; i++) {
            dp[i] += dp[i - coin];
        }
    }

    return dp[amount];
}
```

## Dry Run

```text
coins =[1][2][5]
amount = 5

Start:
dp =[0][1]

After coin 1:
dp =[1]

After coin 2:
dp =[1][2][3]

After coin 5:
dp =[1][2][3][4]

Answer = 4
```

## Complexity

- Time: \(O(amount \times coins)\)
- Space: \(O(amount)\)

---

# 10. Subset Sum

## Problem

Array diya hai aur target diya hai.

Kya koi subset hai jiska sum target ke barabar ho?

```text
nums =[1][2][3][7]
target = 6

Output = true
Explanation: 1 + 2 + 3 = 6
```

## Why DP?

```text
dp[i][target] = can we make target using first i numbers?
```

## Transition

```text
dp[i][target] = dp[i - 1][target] || dp[i - 1][target - nums[i - 1]]
```

## Solution

```java
public boolean canPartition(int[] nums, int target) {
    boolean[] dp = new boolean[target + 1];

    dp = true;

    for (int num : nums) {
        for (int i = target; i >= num; i--) {
            dp[i] = dp[i] || dp[i - num];
        }
    }

    return dp[target];
}
```

## Dry Run

```text
nums =[1][2][3][7]
target = 6

Start:
dp = true

After 1:
dp = true

After 2:
dp = true

After 3:
dp = true

Answer = true
```

## Complexity

- Time: \(O(n \times target)\)
- Space: \(O(target)\)

---

# 11. Partition Equal Subset Sum

## Problem

Array ko do equal sum subsets mein divide karna hai.

```text
nums =[1][5][11]
Output = true
Explanation:  and[1][5]
```

## Why DP?

```text
Total sum = S

Agar S odd -> false

Agar S even:
target = S / 2

Subset Sum DP use karo.
```

## Solution

```java
public boolean canPartition(int[] nums) {
    int total = 0;

    for (int num : nums) {
        total += num;
    }

    if (total % 2 != 0) {
        return false;
    }

    int target = total / 2;

    boolean[] dp = new boolean[target + 1];

    dp = true;

    for (int num : nums) {
        for (int i = target; i >= num; i--) {
            dp[i] = dp[i] || dp[i - num];
        }
    }

    return dp[target];
}
```

## Dry Run

```text
nums =[1][5][11]

total = 22
target = 11

Subset Sum:
1 + 5 + 5 = 11

Answer = true
```

## Complexity

- Time: \(O(n \times target)\)
- Space: \(O(target)\)

---

# 12. Knapsack Patterns

| Pattern | State | Transition |
|---|---|---|
| 0/1 Knapsack | `dp[i][w]` | max(skip, take) |
| Unbounded Knapsack | `dp[w]` | max(dp[w], dp[w - weight] + value) |
| Coin Change Min | `dp[amount]` | min(dp[amount - coin] + 1) |
| Coin Change Ways | `dp[amount]` | dp[amount] += dp[amount - coin] |
| Subset Sum | `dp[target]` | dp[i] || dp[i - num] |
| Partition Equal | `dp[target]` | Subset Sum with target = total/2 |

---

# 13. 0/1 vs Unbounded Knapsack

| Feature | 0/1 Knapsack | Unbounded Knapsack |
|---|---|---|
| Item repeat? | No | Yes |
| Loop order | Reverse | Forward |
| Example | Subset Sum | Coin Change |
| Space | \(O(capacity)\) | \(O(capacity)\) |
| Time | \(O(n \times capacity)\) | \(O(capacity \times items)\) |

### Important Difference

```text
0/1 Knapsack:
for (int w = capacity; w >= weight; w--)

Unbounded Knapsack:
for (int w = weight; w <= capacity; w++)
```

---

# 14. Common Mistakes

## Mistake 1: Loop Order Galat Karna

0/1 Knapsack:

```java
for (int w = capacity; w >= weight; w--)
```

Unbounded Knapsack:

```java
for (int w = weight; w <= capacity; w++)
```

---

## Mistake 2: Base Case Bhoolna

Coin Change:

```java
dp = 0;
```

Coin Change II:

```java
dp = 1;
```

---

## Mistake 3: State Galat Define Karna

0/1 Knapsack:

```text
dp[i][w] = max value using first i items with capacity w
```

---

## Mistake 4: Item Repeat Karna

0/1 Knapsack mein:

```java
dp[i - 1][w - weight]
```

Use hota hai, `dp[i][w - weight]` nahi.

---

## Mistake 5: Initialization Galat Karna

Coin Change:

```java
Arrays.fill(dp, amount + 1);
dp = 0;
```

---

# 15. Knapsack Decision Flow

```text
Problem padho
      |
      v
Kya capacity / budget hai?
      |
      +-- No --> DP probably nahi
      |
      +-- Yes
          |
          v
Kya items repeat ho sakte hain?
      |
      +-- No --> 0/1 Knapsack
      |
      +-- Yes --> Unbounded Knapsack
          |
          v
Kya minimum cost chahiye?
      |
      +-- Yes --> Coin Change Min
      |
      +-- Kya number of ways chahiye?
              |
              +-- Yes --> Coin Change Ways
              |
              +-- Kya partition chahiye?
                      |
                      +-- Yes --> Subset Sum / Partition
```

---

# 16. Important Problems

## Easy

- Climbing Stairs
- Min Cost Climbing Stairs

## Medium

- Partition Equal Subset Sum
- Target Sum
- Coin Change
- Coin Change II
- Last Stone Weight II
- Ones and Zeroes
- Combination Sum IV

## Hard

- Profitable Schemes
- Number of Ways to Wear Different Hats to Each Other
- Tallest Billboard
- Minimum Cost to Cut a Stick

---

# 17. One-Line Recognition

> **Agar problem capacity/budget ke andar items choose karke maximum value, minimum cost, number of ways, ya partition maang rahi hai — Knapsack DP try karo.**

Knapsack DP ka main power ye hai ki limited resource optimization problems ko simple state + transition + base case structure mein convert kar deta hai — jisse exponential choices polynomial time