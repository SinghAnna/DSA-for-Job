# 🚀 DSA Pattern 44 — Dynamic Programming: 2D DP

> **Goal:** 2D Dynamic Programming ko product-based company interview level par samajhna — real-world intuition, kyun 2D DP use hota hai, kaise identify karein, state design, transition, base cases, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. 2D DP Kya Hai?

2D DP mein DP table ek **2D grid** hoti hai:

```text
dp[i][j]
```

Iska matlab:

```text
dp[i][j] = answer for state (i, j)
```

Example:

```text
dp[i][j] = minimum cost to reach cell (i, j)
dp[i][j] = number of ways to reach cell (i, j)
dp[i][j] = longest common subsequence till i and j
```

### Simple Intuition

> Jab problem ka answer do independent dimensions par depend kare, tab 2D DP use hoti hai.

Example:

```text
Grid problem:
Row i aur column j dono state define karte hain.

String problem:
String 1 ka index i aur String 2 ka index j dono state define karte hain.
```

---

## 2. 2D DP Kyun Use Karte Hain?

Main reasons:

1. Grid / matrix problems
2. Two sequences / strings
3. Two choices with state
4. Knapsack problems
5. Longest Common Subsequence
6. Edit Distance
7. Unique Paths
8. Minimum Path Sum

### Core Intuition

> Har cell ka answer uske previous cells par depend karta hai.

Example — Minimum Path Sum:

```text
dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j]
```

Kyunki cell `(i, j)` par sirf:

```text
1. Upar se aa sakte ho
2. Left se aa sakte ho
```

---

## 3. Real-World Production Examples

| Real System | 2D DP Use |
|---|---|
| Maps / Navigation | Minimum path cost |
| Delivery System | Minimum delivery route cost |
| Robotics | Robot movement paths |
| Game Development | Grid based movement |
| Image Processing | Pixel similarity |
| Spell Checker | Edit Distance |
| DNA Matching | Sequence alignment |
| Text Diff Tool | Longest Common Subsequence |
| E-commerce | Budget + items optimization |
| Cloud Cost Optimizer | Resource + cost optimization |
| Recommendation System | User + item matching |
| Scheduling System | Time + resource optimization |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Delivery rider ko grid ke ek corner se dusre corner tak minimum cost path se pahunchana hai. Kaise karoge?”

Answer:

```text
Grid cells nodes hain.

dp[i][j] = minimum cost to reach cell (i, j)

Transition:
dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j]
```

Ya:

> “Spell checker mein do words ke beech minimum edits nikalne hain. Kaise approach karoge?”

Answer:

```text
Edit Distance DP use karenge.

dp[i][j] = minimum edits to convert first i chars to first j chars.
```

---

## 4. Kaise Pata Chalega Ki 2D DP Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible 2D DP |
|---|---|
| Grid / matrix | 2D DP |
| Robot movement | 2D DP |
| Two strings | 2D DP |
| Two sequences | 2D DP |
| LCS | 2D DP |
| Edit Distance | 2D DP |
| Knapsack | 2D DP |
| Minimum path in grid | 2D DP |
| Number of paths in grid | 2D DP |

### Common Keywords

- Grid
- Matrix
- Robot
- Paths
- Two strings
- Longest common
- Edit distance
- Knapsack
- Minimum path
- Maximum path

### Mental Checklist

1. Kya problem grid ya matrix par hai?  
2. Kya do strings / sequences hain?  
3. Kya answer do dimensions par depend karta hai?  
4. Kya current state previous states par depend karti hai?  
5. Kya choices hain?  

Agar 2–3 answers **yes** hain, to 2D DP try karo.

---

# 5. 2D DP Problem Solve Karne Ke 5 Steps

```text
1. State define karo
2. Transition likho
3. Base case likho
4. Iteration order decide karo
5. Final answer nikalo
```

Example — Unique Paths:

```text
State:
dp[i][j] = number of ways to reach cell (i, j)

Transition:
dp[i][j] = dp[i - 1][j] + dp[i][j - 1]

Base case:
dp[j] = 1
dp[i] = 1

Final answer:
dp[m - 1][n - 1]
```

---

# 6. Example 1: Unique Paths

## Problem

Robot top-left corner se bottom-right corner tak jana hai.

Sirf right ya down move kar sakta hai.

Total unique paths nikalo.

```text
m = 3
n = 7

Output = 28
```

## Why 2D DP?

```text
dp[i][j] = number of ways to reach cell (i, j)
```

## Transition

```text
dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
```

Kyunki cell `(i, j)` par sirf:

```text
1. Upar se aa sakte ho
2. Left se aa sakte ho
```

## Solution

```java
public int uniquePaths(int m, int n) {
    int[][] dp = new int[m][n];

    for (int i = 0; i < m; i++) {
        dp[i] = 1;
    }

    for (int j = 0; j < n; j++) {
        dp[j] = 1;
    }

    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        }
    }

    return dp[m - 1][n - 1];
}
```

## Dry Run

```text
m = 3
n = 3

dp:
1 1 1
1 2 3
1 3 6

Answer = 6
```

## Complexity

- Time: \(O(m \times n)\)
- Space: \(O(m \times n)\)

## Space Optimized

```java
public int uniquePathsOptimized(int m, int n) {
    int[] dp = new int[n];

    Arrays.fill(dp, 1);

    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            dp[j] += dp[j - 1];
        }
    }

    return dp[n - 1];
}
```

Space: \(O(n)\)

---

# 7. Example 2: Minimum Path Sum

## Problem

Grid diya hai.

Top-left se bottom-right tak minimum path sum nikalo.

Sirf right ya down move kar sakte ho.

```text
grid = [
 ,[1][3]
 ,[5][1]
[2][4][1]
]

Output = 7
Explanation: 1 -> 3 -> 1 -> 1 -> 1
```

## Why 2D DP?

```text
dp[i][j] = minimum cost to reach cell (i, j)
```

## Transition

```text
dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j]
```

## Solution

```java
public int minPathSum(int[][] grid) {
    int m = grid.length;
    int n = grid.length;

    int[][] dp = new int[m][n];

    dp = grid;

    for (int j = 1; j < n; j++) {
        dp[j] = dp[j - 1] + grid[j];
    }

    for (int i = 1; i < m; i++) {
        dp[i] = dp[i - 1] + grid[i];
    }

    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            dp[i][j] =
                Math.min(dp[i - 1][j], dp[i][j - 1]) +
                grid[i][j];
        }
    }

    return dp[m - 1][n - 1];
}
```

## Dry Run

```text
grid:
1 3 1
1 5 1
4 2 1

dp:
1 4 5
2 7 6
6 8 7

Answer = 7
```

## Complexity

- Time: \(O(m \times n)\)
- Space: \(O(m \times n)\)

---

# 8. Example 3: Longest Common Subsequence — LCS

## Problem

Do strings diye hain.

Longest Common Subsequence ki length nikalo.

```text
text1 = "abcde"
text2 = "ace"

Output = 3
Explanation: "ace"
```

## Why 2D DP?

```text
dp[i][j] = LCS length of text1[0..i-1] and text2[0..j-1]
```

## Transition

```text
If text1[i - 1] == text2[j - 1]:
    dp[i][j] = dp[i - 1][j - 1] + 1

Else:
    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
```

## Solution

```java
public int longestCommonSubsequence(String text1, String text2) {
    int m = text1.length();
    int n = text2.length();

    int[][] dp = new int[m + 1][n + 1];

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {

            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {
                dp[i][j] = Math.max(
                    dp[i - 1][j],
                    dp[i][j - 1]
                );
            }
        }
    }

    return dp[m][n];
}
```

## Dry Run

```text
text1 = "abcde"
text2 = "ace"

dp:
    "" a c e
""  0 0 0 0
a   0 1 1 1
b   0 1 1 1
c   0 1 2 2
d   0 1 2 2
e   0 1 2 3

Answer = 3
```

## Complexity

- Time: \(O(m \times n)\)
- Space: \(O(m \times n)\)

---

# 9. Example 4: Edit Distance

## Problem

Do words diye hain.

Minimum operations nikalo jisse word1 ko word2 banaya ja sake.

Operations:

```text
1. Insert
2. Delete
3. Replace
```

```text
word1 = "horse"
word2 = "ros"

Output = 3
```

## Why 2D DP?

```text
dp[i][j] = minimum edits to convert word1[0..i-1] to word2[0..j-1]
```

## Transition

```text
If characters match:
    dp[i][j] = dp[i - 1][j - 1]

Else:
    dp[i][j] = 1 + min(
        dp[i - 1][j],     // delete
        dp[i][j - 1],     // insert
        dp[i - 1][j - 1]  // replace
    )
```

## Solution

```java
public int minDistance(String word1, String word2) {
    int m = word1.length();
    int n = word2.length();

    int[][] dp = new int[m + 1][n + 1];

    for (int i = 0; i <= m; i++) {
        dp[i] = i;
    }

    for (int j = 0; j <= n; j++) {
        dp[j] = j;
    }

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {

            if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1];
            } else {
                dp[i][j] = 1 + Math.min(
                    dp[i - 1][j],
                    Math.min(
                        dp[i][j - 1],
                        dp[i - 1][j - 1]
                    )
                );
            }
        }
    }

    return dp[m][n];
}
```

## Dry Run

```text
word1 = "horse"
word2 = "ros"

Answer = 3

horse -> rorse  (replace h with r)
rorse -> rose   (remove r)
rose  -> ros    (remove e)
```

## Complexity

- Time: \(O(m \times n)\)
- Space: \(O(m \times n)\)

---

# 10. Example 5: 0/1 Knapsack

## Problem

Items diye hain.

Har item ka weight aur value hai.

Maximum value nikalo jisse total weight capacity ke andar rahe.

```text
weights =[1][3][4][5]
values =[1][4][5][7]
capacity = 7

Output = 9
Explanation: item 2 + item 4
weight = 3 + 4 = 7
value = 4 + 5 = 9
```

## Why 2D DP?

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

---

# 11. 2D DP Patterns

| Pattern | State | Transition |
|---|---|---|
| Unique Paths | `dp[i][j]` | `dp[i - 1][j] + dp[i][j - 1]` |
| Minimum Path Sum | `dp[i][j]` | `min(up, left) + grid[i][j]` |
| LCS | `dp[i][j]` | match ? diagonal + 1 : max(up, left) |
| Edit Distance | `dp[i][j]` | match ? diagonal : 1 + min(up, left, diagonal) |
| Knapsack | `dp[i][w]` | max(skip, take) |
| Coin Change 2D | `dp[i][amount]` | min(skip, take) |

---

# 12. Memoization vs Tabulation

| Feature | Memoization | Tabulation |
|---|---|---|
| Approach | Top-down | Bottom-up |
| Style | Recursion | Iteration |
| Stack overflow | Possible | No |
| Space | Recursion stack + DP | DP only |
| Best for | Complex states | Simple states |
| Example | Edit Distance | Unique Paths |

### Rule

```text
Simple grid / string DP -> Tabulation
Complex state transitions -> Memoization
```

---

# 13. Common Mistakes

## Mistake 1: Base Case Bhoolna

Unique Paths:

```java
dp[j] = 1;
dp[i] = 1;
```

Edit Distance:

```java
dp[i] = i;
dp[j] = j;
```

---

## Mistake 2: State Galat Define Karna

LCS:

```text
dp[i][j] = LCS till i and j
```

Galat:

```text
dp[i][j] = LCS at i and j
```

---

## Mistake 3: Transition Galat Likna

Minimum Path Sum:

```java
dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
```

Unique Paths:

```java
dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
```

---

## Mistake 4: Iteration Order Galat Karna

Grid DP mein:

```java
for (int i = 1; i < m; i++) {
    for (int j = 1; j < n; j++) {
        // dp[i][j]
    }
}
```

---

## Mistake 5: Knapsack Mein Item Repeat Karna

0/1 Knapsack mein:

```java
dp[i - 1][w - weight]
```

Use hota hai, `dp[i][w - weight]` nahi.

---

# 14. 2D DP Decision Flow

```text
Problem padho
      |
      v
Kya grid / matrix hai?
      |
      +-- Yes --> Grid DP
      |
      +-- Kya do strings / sequences hain?
              |
              +-- Yes --> String DP
              |
              +-- Kya item + capacity hai?
                      |
                      +-- Yes --> Knapsack DP
```

---

# 15. Important Problems

## Easy

- Unique Paths
- Minimum Path Sum
- Fibonacci Number

## Medium

- Unique Paths II
- Longest Common Subsequence
- Edit Distance
- Partition Equal Subset Sum
- Target Sum
- Coin Change II
- Maximal Square

## Hard

- Regular Expression Matching
- Wildcard Matching
- Distinct Subsequences
- Interleaving String
- Burst Balloons

---

# 16. One-Line Recognition

> **Agar problem grid, two strings, ya item + capacity type structure par hai aur answer do dimensions par depend karta hai — 2D DP try karo.**

2D DP ka main power ye hai ki complex grid/string/knapsack problems ko