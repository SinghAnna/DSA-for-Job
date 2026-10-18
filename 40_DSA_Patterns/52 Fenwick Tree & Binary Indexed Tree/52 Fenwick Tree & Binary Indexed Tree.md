# 🚀 DSA Pattern 52 — Fenwick Tree / Binary Indexed Tree (BIT)

> **Goal:** Fenwick Tree — Binary Indexed Tree — ko product-based company interview level par samajhna — real-world intuition, kyun BIT use hota hai, kaise identify karein, prefix sum, point update, range sum, 2D BIT, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Fenwick Tree Kya Hai?

Fenwick Tree — Binary Indexed Tree — ek data structure hai jo:

```text
1. Prefix sum
2. Point update
3. Range sum query
```

efficiently handle karta hai.

### Simple Example

```text
Array:
[1][3][5][7][9][11]

Prefix Sum:
prefixSum(3) = 1 + 3 + 5 = 9

Range Sum:
sum(1, 3) = 3 + 5 = 8
```

### Core Intuition

> Fenwick Tree prefix sums ko binary representation ke basis par store karta hai.

Example:

```text
index 6 = binary 110

Fenwick Tree:
index 6 -> covers range[5][6]
index 4 -> covers range[1][4]
```

---

## 2. Fenwick Tree Kyun Use Karte Hain?

Main reasons:

1. Prefix sum with updates
2. Range sum query
3. Count inversions
4. Count smaller elements
5. Dynamic ranking
6. 2D prefix sum
7. Competitive programming
8. Order statistics

### Core Intuition

> Fenwick Tree prefix sum ko O(log n) time mein update aur query karta hai.

Example:

```text
Naive:
prefixSum(i) -> O(n)

Fenwick Tree:
prefixSum(i) -> O(log n)
```

---

## 3. Real-World Production Examples

| Real System | Fenwick Tree Use |
|---|---|
| Analytics Dashboard | Running total |
| Stock Market | Cumulative volume |
| E-commerce | Running sales total |
| Leaderboard | Rank update |
| Gaming | Score ranking |
| Cloud Monitoring | Resource usage |
| Ad System | Impression count |
| Recommendation System | Score aggregation |
| Finance | Portfolio value |
| IoT | Sensor data aggregation |
| Database | Indexed aggregation |
| Logistics | Cumulative delivery count |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Leaderboard mein player score update hota rahega aur rank query karni hai. Kaise karoge?”

Answer:

```text
Fenwick Tree use karenge.

Update:
score update -> O(log n)

Query:
rank / prefix sum -> O(log n)
```

Ya:

> “Analytics dashboard mein running sales total chahiye with updates. Kaise approach karoge?”

Answer:

```text
Fenwick Tree use karenge.

Update:
sale update -> O(log n)

Query:
prefix sum -> O(log n)
```

---

## 4. Kaise Pata Chalega Ki Fenwick Tree Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Fenwick Tree |
|---|---|
| Prefix sum | Fenwick Tree |
| Point update | Fenwick Tree |
| Range sum | Fenwick Tree |
| Count inversions | Fenwick Tree |
| Count smaller elements | Fenwick Tree |
| Dynamic ranking | Fenwick Tree |
| 2D prefix sum | 2D Fenwick Tree |

### Common Keywords

- Prefix sum
- Range sum
- Update
- Count
- Rank
- Inversion
- Dynamic
- Cumulative

### Mental Checklist

1. Kya prefix sum chahiye?  
2. Kya updates bhi chahiye?  
3. Kya range sum chahiye?  
4. Kya count / rank chahiye?  
5. Kya O(log n) chahiye?  

Agar 2–3 answers **yes** hain, to Fenwick Tree try karo.

---

# 5. Fenwick Tree Problem Solve Karne Ke 5 Steps

```text
1. BIT array banao
2. Update function likho
3. Prefix sum function likho
4. Range sum function likho
5. Final answer nikalo
```

Example — Range Sum Query:

```text
BIT:
prefix sum store karo

Update:
index update -> O(log n)

Query:
prefixSum(right) - prefixSum(left - 1)
```

---

# 6. Fenwick Tree Template — 1D

```java
class FenwickTree {

    private int[] tree;

    private int n;

    public FenwickTree(int n) {

        this.n = n;

        tree = new int[n + 1];
    }

    public void update(int index, int delta) {

        while (index <= n) {

            tree[index] += delta;

            index += index & (-index);
        }
    }

    public int prefixSum(int index) {

        int sum = 0;

        while (index > 0) {

            sum += tree[index];

            index -= index & (-index);
        }

        return sum;
    }

    public int rangeSum(int left, int right) {
        return prefixSum(right) - prefixSum(left - 1);
    }
}
```

### Important

```java
index += index & (-index);
```

Ye next index nikalta hai jo current index ko cover karta hai.

```java
index -= index & (-index);
```

Ye previous index nikalta hai jo current index se pehle ka sum deta hai.

---

# 7. Example 1: Range Sum Query — Mutable

## Problem

Array diya hai.

Operations:

```text
1. update(index, value)
2. sumRange(left, right)
```

```text
nums =[1][3][5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
```

## Why Fenwick Tree?

Prefix sum + update chahiye.

## Solution

```java
class NumArray {

    private FenwickTree fenwickTree;

    private int[] nums;

    public NumArray(int[] nums) {

        this.nums = nums;

        fenwickTree = new FenwickTree(nums.length);

        for (int i = 0; i < nums.length; i++) {
            fenwickTree.update(i + 1, nums[i]);
        }
    }

    public void update(int index, int val) {

        int delta = val - nums[index];

        nums[index] = val;

        fenwickTree.update(index + 1, delta);
    }

    public int sumRange(int left, int right) {
        return fenwickTree.rangeSum(left + 1, right + 1);
    }
}
```

## Dry Run

```text
nums =[1][3][5]

sumRange(0, 2):
prefixSum(3) = 9

update(1, 2):
delta = 2 - 3 = -1

sumRange(0, 2):
prefixSum(3) = 8

Answer = 8
```

## Complexity

- Build: \(O(n \log n)\)
- Update: \(O(\log n)\)
- Query: \(O(\log n)\)
- Space: \(O(n)\)

---

# 8. Example 2: Count Inversions

## Problem

Array diya hai.

Number of inversions nikalo.

Inversion:

```text
i < j
nums[i] > nums[j]
```

```text
nums =[1][2][3][4][5]
Output = 10
```

## Why Fenwick Tree?

Har element ke liye:

```text
1. Kitne smaller elements already aaye hain
2. Inversions count karo
```

## Solution

```java
public int countInversions(int[] nums) {

    int n = nums.length;

    int max = Integer.MIN_VALUE;

    for (int num : nums) {
        max = Math.max(max, num);
    }

    FenwickTree fenwickTree = new FenwickTree(max);

    int inversions = 0;

    for (int i = n - 1; i >= 0; i--) {

        inversions += fenwickTree.prefixSum(nums[i] - 1);

        fenwickTree.update(nums[i], 1);
    }

    return inversions;
}
```

## Dry Run

```text
nums =[1][2][3][4][5]

Traverse from right:

i = 4:
nums[i] = 1
prefixSum(0) = 0
update(1)

i = 3:
nums[i] = 2
prefixSum(1) = 1
inversions = 1
update(2)

i = 2:
nums[i] = 3
prefixSum(2) = 2
inversions = 3
update(3)

i = 1:
nums[i] = 4
prefixSum(3) = 3
inversions = 6
update(4)

i = 0:
nums[i] = 5
prefixSum(4) = 4
inversions = 10
update(5)

Answer = 10
```

## Complexity

- Time: \(O(n \log n)\)
- Space: \(O(n)\)

---

# 9. Example 3: Count Smaller Elements After Self

## Problem

Array diya hai.

Har element ke liye count nikalo ki uske right mein kitne smaller elements hain.

```text
nums =[1][2][5][6]
Output =[0][2][1]
```

## Why Fenwick Tree?

Har element ke liye:

```text
1. Right side mein smaller elements count karo
```

## Solution

```java
public List<Integer> countSmaller(int[] nums) {

    int n = nums.length;

    int max = Integer.MIN_VALUE;

    for (int num : nums) {
        max = Math.max(max, num);
    }

    FenwickTree fenwickTree = new FenwickTree(max);

    Integer[] result = new Integer[n];

    for (int i = n - 1; i >= 0; i--) {

        result[i] = fenwickTree.prefixSum(nums[i] - 1);

        fenwickTree.update(nums[i], 1);
    }

    return Arrays.asList(result);
}
```

## Dry Run

```text
nums =[1][2][5][6]

Traverse from right:

i = 3:
nums[i] = 1
prefixSum(0) = 0
result = 0
update(1)

i = 2:
nums[i] = 6
prefixSum(5) = 1
result = 1
update(6)

i = 1:
nums[i] = 2
prefixSum(1) = 1
result = 1
update(2)

i = 0:
nums[i] = 5
prefixSum(4) = 2
result = 2
update(5)

Answer =[0][1][2]
```

## Complexity

- Time: \(O(n \log n)\)
- Space: \(O(n)\)

---

# 10. Example 4: 2D Fenwick Tree

## Problem

2D matrix diya hai.

Operations:

```text
1. update(row, col, value)
2. sumRegion(row1, col1, row2, col2)
```

```text
matrix = [
 ,[0][1][2][3][4]
 ,[1][2][3][5][6]
 ,[0][2][5][1]
 ,[4][7][0][1]
[3][5][0][1]
]

sumRegion(2, 1, 4, 3) -> 8
```

## Why 2D Fenwick Tree?

2D prefix sum + update chahiye.

## Solution

```java
class FenwickTree2D {

    private int[][] tree;

    private int rows;

    private int cols;

    public FenwickTree2D(int rows, int cols) {

        this.rows = rows;

        this.cols = cols;

        tree = new int[rows + 1][cols + 1];
    }

    public void update(int row, int col, int delta) {

        for (int i = row; i <= rows; i += i & (-i)) {

            for (int j = col; j <= cols; j += j & (-j)) {

                tree[i][j] += delta;
            }
        }
    }

    public int prefixSum(int row, int col) {

        int sum = 0;

        for (int i = row; i > 0; i -= i & (-i)) {

            for (int j = col; j > 0; j -= j & (-j)) {

                sum += tree[i][j];
            }
        }

        return sum;
    }

    public int rangeSum(
        int row1,
        int col1,
        int row2,
        int col2
    ) {
        return prefixSum(row2, col2)
             - prefixSum(row1 - 1, col2)
             - prefixSum(row2, col1 - 1)
             + prefixSum(row1 - 1, col1 - 1);
    }
}
```

## Dry Run

```text
matrix:
3 0 1 4 2
5 6 3 2 1
1 2 0 1 5
4 1 0 1 7
1 0 3 0 5

sumRegion(2, 1, 4, 3):
8

Answer = 8
```

## Complexity

- Update: \(O(\log n \times \log m)\)
- Query: \(O(\log n \times \log m)\)
- Space: \(O(n \times m)\)

---

# 11. Fenwick Tree Patterns

| Pattern | Use |
|---|---|
| 1D BIT | Prefix sum + update |
| 2D BIT | 2D prefix sum + update |
| Count Inversions | Count smaller elements |
| Count Smaller | Right side smaller count |
| Dynamic Ranking | Rank update |
| Order Statistics | Kth smallest |

---

# 12. Fenwick Tree vs Segment Tree

| Feature | Fenwick Tree | Segment Tree |
|---|---|---|
| Prefix sum | Best | Good |
| Range sum | Good | Best |
| Range min/max | Poor | Best |
| Range update | Limited | Best |
| Lazy propagation | No | Yes |
| Memory | Less | More |
| Implementation | Simple | Complex |

### Rule

```text
Prefix sum + update -> Fenwick Tree
Range min/max + update -> Segment Tree
```

---

# 13. Common Mistakes

## Mistake 1: Index 0 Se Start Karna

Fenwick Tree 1-based indexing use karta hai.

```java
tree = new int[n + 1];
```

---

## Mistake 2: Update Function Galat Karna

```java
index += index & (-index);
```

---

## Mistake 3: Prefix Sum Function Galat Karna

```java
index -= index & (-index);
```

---

## Mistake 4: Range Sum Formula Galat Karna

```java
prefixSum(right) - prefixSum(left - 1)
```

---

## Mistake 5: 2D BIT Mein Loop Order Galat Karna

```java
for (int i = row; i <= rows; i += i & (-i)) {
    for (int j = col; j <= cols; j += j & (-j)) {
        tree[i][j] += delta;
    }
}
```

---

# 14. Fenwick Tree Decision Flow

```text
Problem padho
      |
      v
Kya prefix sum chahiye?
      |
      +-- No --> Fenwick Tree probably nahi
      |
      +-- Yes
          |
          v
Kya update bhi chahiye?
      |
      +-- Yes --> Fenwick Tree
      |
      +-- Kya range sum chahiye?
              |
              +-- Yes --> Fenwick Tree
              |
              +-- Kya 2D prefix sum hai?
                      |
                      +-- Yes --> 2D Fenwick Tree
                      |
                      +-- Kya range min/max hai?
                              |
                              +-- Yes --> Segment Tree
```

---

# 15. Important Problems

## Medium

- Range Sum Query — Mutable
- Count of Smaller Numbers After Self
- Count Inversions
- Reverse Pairs
- My Calendar I
- My Calendar II

## Hard

- Count of Range Sum
- Reverse Pairs
- Count of Smaller Numbers After Self
- My Calendar III
- The Skyline Problem

---

# 16. One-Line Recognition

> **Agar problem prefix sum, range sum, count inversions, ya dynamic ranking maang rahi hai — Fenwick Tree try karo.**

Fenwick Tree ka main power ye hai ki prefix sum based problems ko O(log n) time mein solve karta hai — jisse dynamic array problems efficiently handle ho jati hain.