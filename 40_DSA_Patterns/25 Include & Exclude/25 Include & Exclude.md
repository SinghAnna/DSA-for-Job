# 🚀 DSA Pattern 25 — Include / Exclude

> **Goal:** Include / Exclude pattern ko product-based company interview level par samajhna — real-world intuition, kyun use hota hai, kaise identify karein, subsets, subsequences, knapsack-style decisions, templates, recursion tree, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Include / Exclude Kya Hai?

Include / Exclude pattern tab use hota hai jab har element ke liye do choices ho:

```text
1. Element ko include karo
2. Element ko exclude karo
```

Example:

```text
nums =[1][2][3]
```

Element `1` ke liye:

```text
Include 1
Exclude 1
```

Element `2` ke liye:

```text
Include 2
Exclude 2
```

Element `3` ke liye:

```text
Include 3
Exclude 3
```

Total subsets:

\[
2^n
\]

For `n = 3`:

```text
2^3 = 8 subsets
```

Subsets:

```text
[]



[1][2]
[3][1]
[2][3]
[1][2][3]
```

---

## 2. Include / Exclude Kyun Use Karte Hain?

Main reasons:

1. **All subsets generate karna**
2. **All subsequences explore karna**
3. **Knapsack-style decisions lena**
4. **Target sum / partition problems solve karna**
5. **Dynamic Programming ka foundation**
6. **Backtracking problems mein decision tree banana**

### Core Intuition

> Har element ke liye decide karo: “Kya main isko apne answer mein lunga ya nahi?”

Example:

```text
nums =[1][2][3]
target = 3
```

Decision tree:

```text
                 []
            /          \
       include 1     exclude 1
                     []
        /     \       /     \
                  [][1][2]
```

Har path ek possible subset hai.

---

## 3. Real-World Production Examples

| Real System | Include / Exclude Use |
|---|---|
| E-commerce | Product select karna ya nahi karna |
| Subscription System | Feature include/exclude |
| Cloud Cost Optimizer | Resource select karna |
| Ad Campaign | Keywords include/exclude |
| Course Planner | Subjects select karna |
| Budget Planner | Expenses include/exclude |
| Machine Learning | Feature selection |
| Recommendation System | Item selection |
| Project Management | Task selection under deadline |
| Portfolio Optimization | Stocks include/exclude |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Tumhare cloud cost optimizer mein limited budget hai. Kaunse services enable karni chahiye taaki value maximum ho?”

Answer:

```text
Har service ke liye do choices:
1. Include service -> cost add, value add
2. Exclude service -> cost same, value same

Ye 0/1 Knapsack style Include / Exclude problem hai.
```

Ya:

> “E-commerce bundle builder mein user ke budget ke andar best product combination kaise choose karoge?”

Answer:

```text
Har product ke liye include/exclude decision lo.
Budget exceed na ho.
Maximum value/rating optimize karo.
```

---

## 4. Kaise Pata Chalega Ki Include / Exclude Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Include / Exclude |
|---|---|
| All subsets chahiye | Include / Exclude |
| Subsequences chahiye | Include / Exclude |
| Choose items under constraint | Include / Exclude |
| 0/1 Knapsack | Include / Exclude |
| Target sum possible hai ya nahi | Include / Exclude |
| Partition array into two subsets | Include / Exclude |
| Maximum profit with weight limit | Include / Exclude |
| Feature selection | Include / Exclude |

### Common Keywords

- Subsets
- Subsequences
- Choose
- Select
- Include
- Exclude
- Knapsack
- Target sum
- Partition
- Maximum profit

### Mental Checklist

1. Kya har element ke liye include/exclude choice hai?  
2. Kya element sirf ek baar use ho sakta hai?  
3. Kya constraint hai, jaise target sum, budget, weight?  
4. Kya all combinations explore karne hain?  
5. Kya optimal selection chahiye?  

Agar 2–3 answers **yes** hain, to Include / Exclude try karo.

---

# 5. Include / Exclude Ka Core Idea

Har index par:

```text
current element = nums[index]
```

Do recursive calls:

```text
1. Include:
   current list mein element add karo
   index + 1 par jao

2. Exclude:
   current list mein element add mat karo
   index + 1 par jao
```

Base case:

```text
index == nums.length
```

---

# 6. Template — Generate All Subsets

```java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();

    backtrack(nums, 0, new ArrayList<>(), ans);

    return ans;
}

private void backtrack(int[] nums,
                       int index,
                       List<Integer> current,
                       List<List<Integer>> ans) {

    if (index == nums.length) {
        ans.add(new ArrayList<>(current));
        return;
    }

    // Include nums[index]
    current.add(nums[index]);
    backtrack(nums, index + 1, current, ans);

    // Exclude nums[index]
    current.remove(current.size() - 1);
    backtrack(nums, index + 1, current, ans);
}
```

### Important

```java
ans.add(new ArrayList<>(current));
```

Direct `current` add mat karo:

```java
ans.add(current); // Wrong
```

Kyunki same list reference baar-baar change hoga.

---

# 7. Example 1: Subsets

## Problem

Array ke saare possible subsets return karo.

```text
nums =[1][2][3]

Output:
[
  [],
  ,
 ,[1][2]
 ,[2][3][1]
 ,[3][1]
  ,
 ,[2][3]
  
]
```

## Why Include / Exclude?

Har element ke liye include/exclude decision lena hai.

## Solution

```java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();

    backtrack(nums, 0, new ArrayList<>(), ans);

    return ans;
}

private void backtrack(int[] nums,
                       int index,
                       List<Integer> current,
                       List<List<Integer>> ans) {

    if (index == nums.length) {
        ans.add(new ArrayList<>(current));
        return;
    }

    current.add(nums[index]);
    backtrack(nums, index + 1, current, ans);

    current.remove(current.size() - 1);
    backtrack(nums, index + 1, current, ans);
}
```

## Recursion Tree

```text
                 []
           /            \
      include 1       exclude 1
                      []
       /      \        /      \
                    [][1][2]
   /   \      /  \   /  \     /  \
[][1][2][3]
```

## Dry Run

```text
nums =[2][3][1]

index = 0:
include 1 -> 
exclude 1 -> []

index = 1:
from :
include 2 ->[1][2]
exclude 2 -> 

from []:
include 2 -> 
exclude 2 -> []

index = 2:
from:[1][2]
include 3 ->[1][2][3]
exclude 3 ->[2][1]

from :
include 3 ->[1][3]
exclude 3 -> 

from :
include 3 ->[2][3]
exclude 3 -> 

from []:
include 3 -> 
exclude 3 -> []

Final subsets:
[
  [],
  ,
 ,[1][2]
 ,[2][3][1]
 ,[3][1]
  ,
 ,[2][3]
  
]
```

## Complexity

- Time: \(O(n \times 2^n)\)
- Space: \(O(n)\), recursion stack

---

# 8. Example 2: Subsets — Iterative Approach

## Problem

Same subsets problem ko iteration se solve karo.

## Idea

Har naye element ke liye:

```text
Existing har subset mein naya element add karke naye subsets banao.
```

## Solution

```java
public List<List<Integer>> subsetsIterative(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();

    ans.add(new ArrayList<>());

    for (int num : nums) {
        int size = ans.size();

        for (int i = 0; i < size; i++) {
            List<Integer> newSubset = new ArrayList<>(ans.get(i));
            newSubset.add(num);

            ans.add(newSubset);
        }
    }

    return ans;
}
```

## Dry Run

```text
nums =[1][2][3]

Initial:
[[]]

After 1:
[]


After 2:
[]


[1][2]

After 3:
[]


[1][2]

[3][1]
[2][3]
[1][2][3]
```

## Complexity

- Time: \(O(n \times 2^n)\)
- Space: \(O(n \times 2^n)\)

---

# 9. Example 3: Subsets — Bitmask Approach

## Problem

Same subsets problem ko bitmask se solve karo.

## Idea

Har subset ko ek binary mask maan lo.

```text
nums =[1][2][3]

mask 000 -> []
mask 001 -> 
mask 010 -> 
mask 011 ->[1][2]
mask 100 -> 
mask 101 ->[3][1]
mask 110 ->[2][3]
mask 111 ->[1][3][2]
```

## Solution

```java
public List<List<Integer>> subsetsBitmask(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();

    int n = nums.length;

    for (int mask = 0; mask < (1 << n); mask++) {
        List<Integer> subset = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) != 0) {
                subset.add(nums[i]);
            }
        }

        ans.add(subset);
    }

    return ans;
}
```

## Dry Run

```text
nums =[1][2][3]

mask = 0 -> 000 -> []
mask = 1 -> 001 -> 
mask = 2 -> 010 -> 
mask = 3 -> 011 ->[1][2]
mask = 4 -> 100 -> 
mask = 5 -> 101 ->[1][3]
mask = 6 -> 110 ->[2][3]
mask = 7 -> 111 ->[1][2][3]
```

## Complexity

- Time: \(O(n \times 2^n)\)
- Space: \(O(n \times 2^n)\)

---

# 10. Example 4: Target Sum Possible

## Problem

Array mein se aise numbers choose karo jinka sum exactly `target` ho.

Check karo ki possible hai ya nahi.

```text
nums =[1][2][3]
target = 4

Output: true
Explanation: 1 + 3 = 4
```

## Why Include / Exclude?

Har number ke liye:

```text
Include -> target reduce karo
Exclude -> target same rakho
```

## Solution

```java
public boolean canTargetSum(int[] nums, int target) {
    return helper(nums, 0, target);
}

private boolean helper(int[] nums, int index, int target) {
    if (target == 0) {
        return true;
    }

    if (index == nums.length || target < 0) {
        return false;
    }

    boolean include = helper(nums, index + 1, target - nums[index]);
    boolean exclude = helper(nums, index + 1, target);

    return include || exclude;
}
```

## Dry Run

```text
nums =[1][2][3]
target = 4

helper(0, 4)

Include 1:
helper(1, 3)

Include 2:
helper(2, 1)

Include 3:
helper(3, -2)
false

Exclude 3:
helper(3, 1)
false

Exclude 2:
helper(2, 3)

Include 3:
helper(3, 0)
true

Answer = true
```

## Complexity

- Time: \(O(2^n)\)
- Space: \(O(n)\)

### Optimization

Repeated subproblems hain, isliye memoization use kar sakte hain:

```java
Boolean[][] memo;
```

State:

```text
(index, remainingTarget)
```

---

# 11. Example 5: Partition Equal Subset Sum

## Problem

Array ko do subsets mein divide karo such that dono ka sum equal ho.

```text
nums =[1][5][11]
Output: true
Explanation:  and[1][5]
```

## Why Include / Exclude?

Total sum nikalo:

```text
total = 22
```

Agar koi subset ka sum:

```text
total / 2 = 11
```

Ho jaye, to answer `true`.

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

    return helper(nums, 0, target);
}

private boolean helper(int[] nums, int index, int target) {
    if (target == 0) {
        return true;
    }

    if (index == nums.length || target < 0) {
        return false;
    }

    boolean include = helper(nums, index + 1, target - nums[index]);
    boolean exclude = helper(nums, index + 1, target);

    return include || exclude;
}
```

## Complexity

- Time: \(O(2^n)\)
- Space: \(O(n)\)

### Better — DP

```java
public boolean canPartitionDP(int[] nums) {
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
        for (int j = target; j >= num; j--) {
            dp[j] = dp[j] || dp[j - num];
        }
    }

    return dp[target];
}
```

Time: \(O(n \times target)\)  
Space: \(O(target)\)

---

# 12. Example 6: House Robber

## Problem

Houses mein paise rakhe hain.

Adjacent houses rob nahi kar sakte.

Maximum money nikalo.

```text
nums =[1][2][3][7][9]
Output: 12
Explanation: 2 + 9 + 1 = 12
```

## Why Include / Exclude?

Har house ke liye:

```text
Include -> current money + rob from i + 2
Exclude -> rob from i + 1
```

## Solution

```java
public int rob(int[] nums) {
    return helper(nums, 0);
}

private int helper(int[] nums, int index) {
    if (index >= nums.length) {
        return 0;
    }

    int include = nums[index] + helper(nums, index + 2);
    int exclude = helper(nums, index + 1);

    return Math.max(include, exclude);
}
```

## Dry Run

```text
nums =[1][2][3][7][9]

helper(0)

Include 2:
2 + helper(2)

Include 9:
9 + helper(4)

Include 1:
1 + helper(6)
= 1

Exclude 9:
helper(4)
= 1

max(10, 1) = 10

Exclude 2:
helper(1)

Include 7:
7 + helper(3)

Include 3:
3 + helper(5)
= 3

Exclude 3:
helper(4)
= 1

max(10, 1) = 10

max(12, 10) = 12
```

## Complexity

- Naive recursion: \(O(2^n)\)
- Memoization: \(O(n)\)
- Space: \(O(n)\)

---

# 13. Include / Exclude vs For Loop Backtracking

| Feature | Include / Exclude | For Loop Backtracking |
|---|---|---|
| Decision | Har element include/exclude | Current index se aage choose |
| Best for | Subsets, knapsack | Combinations, permutations |
| Duplicate handling | Index-based | Loop start index |
| Example | Subsets | Combination Sum |
| Recursion calls | 2 per element | Multiple per level |

### Rule

```text
Har element include/exclude -> Include / Exclude
Combination banana hai -> For Loop Backtracking
```

---

# 14. Common Mistakes

## Mistake 1: Same List Reference Add Karna

Galat:

```java
ans.add(current);
```

Correct:

```java
ans.add(new ArrayList<>(current));
```

---

## Mistake 2: Exclude Case Mein Remove Na Karna

Include ke baad:

```java
current.add(nums[index]);
backtrack(...);
current.remove(current.size() - 1);
```

Ye backtrack zaroori hai.

---

## Mistake 3: Base Case Galat Lenа

Subsets mein:

```java
if (index == nums.length)
```

Target sum mein:

```java
if (target == 0)
```

Problem ke according base case change hota hai.

---

## Mistake 4: Duplicate Elements Handle Na Karna

Agar duplicates hain:

```text
nums =[1][2]
```

To duplicate subsets avoid karne ke liye sorting + skip logic chahiye.

---

## Mistake 5: Repeated Subproblems Ko Brute Force Mein Chhodna

Target sum, partition, house robber jaise problems mein memoization/DP lagao.

---

# 15. Include / Exclude Decision Flow

```text
Problem padho
      |
      v
Kya har element include/exclude ho sakta hai?
      |
      +-- No --> Include / Exclude probably nahi
      |
      +-- Yes
          |
          v
Kya all subsets chahiye?
      |
      +-- Yes --> Backtracking Include / Exclude
      |
      +-- Kya optimal value chahiye?
              |
              +-- Yes --> Memoization / DP
              |
              +-- Kya constraint hai?
                      |
                      +-- Yes --> Knapsack style DP
```

---

# 16. Important Problems

## Easy

- Subsets
- Power Set
- Sum of All Subset XOR Totals

## Medium

- Subsets II
- Target Sum
- Partition Equal Subset Sum
- House Robber
- House Robber II
- Last Stone Weight II
- Ones and Zeroes

## Hard

- Partition to K Equal Sum Subsets
- Maximum Profit in Job Scheduling
- Number of Ways to Wear Different Hats to Each Other

---

# 17. One-Line Recognition

> **Agar har element ko choose karna hai ya nahi karna, aur subsets/subsequences/knapsack-type decision chahiye — Include / Exclude pattern try karo.**

Include / Exclude ka main power ye hai ki har element ke liye clear binary decision leta hai: include ya exclude. Isi decision tree se subsets, subsequences, knapsack aur DP problems solve hoti hain.