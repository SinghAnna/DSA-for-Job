# 🚀 DSA Pattern 60 — Sqrt Decomposition

> **Goal:** Sqrt Decomposition ko product-based company interview level par samajhna — real-world intuition, kyun Sqrt Decomposition use hota hai, kaise identify karein, range sum, range min/max, point update, range update, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Sqrt Decomposition Kya Hai?

Sqrt Decomposition ek technique hai jisme array ko **sqrt(n) size ke blocks** mein divide karte hain.

### Simple Example

```text
Array:
[1][3][5][7][9][11][13][15][17][19]

Block size = sqrt(10) ≈ 3

Blocks:
[1][3][5]
[7][9][11]
[13][15][17]

```

### Core Intuition

> Har block ka precomputed answer store karo, jisse range queries fast ho jayein.

Example:

```text
Naive:
sum(1, 8) -> O(n)

Sqrt Decomposition:
sum(1, 8) -> O(sqrt(n))
```

---

## 2. Sqrt Decomposition Kyun Use Karte Hain?

Main reasons:

1. Range sum query
2. Range min/max query
3. Point update
4. Range update
5. Mo’s Algorithm
6. Dynamic array queries

### Core Intuition

> Sqrt Decomposition simple aur flexible hota hai.

Example:

```text
Segment Tree:
Complex but O(log n)

Sqrt Decomposition:
Simple but O(sqrt(n))
```

---

## 3. Real-World Production Examples

| Real System | Sqrt Decomposition Use |
|---|---|
| Analytics Dashboard | Range sum |
| Stock Market | Range max price |
| Gaming | Score range query |
| E-commerce | Price range query |
| Cloud Monitoring | CPU usage range |
| Database | Range aggregation |
| Maps | Traffic range query |
| Recommendation System | Score range query |
| Ad System | Impression range query |
| Finance | Portfolio range query |
| IoT | Sensor data range query |
| Logistics | Load range query |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Stock prices ke array mein kisi range ka maximum price nikalna hai. Kaise karoge?”

Answer:

```text
Sqrt Decomposition use karenge.

Har block ka max price store karo.

Query:
max(1, 8) -> O(sqrt(n))
```

Ya:

> “Analytics dashboard mein kisi date range ka total revenue nikalna hai. Kaise approach karoge?”

Answer:

```text
Sqrt Decomposition use karenge.

Har block ka total revenue store karo.

Query:
sum(start, end) -> O(sqrt(n))
```

---

## 4. Kaise Pata Chalega Ki Sqrt Decomposition Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Sqrt Decomposition |
|---|---|
| Range sum | Sqrt Decomposition |
| Range min/max | Sqrt Decomposition |
| Point update | Sqrt Decomposition |
| Range update | Sqrt Decomposition |
| Mo’s Algorithm | Sqrt Decomposition |
| Simple implementation | Sqrt Decomposition |

### Common Keywords

- Range sum
- Range min
- Range max
- Update
- Query
- Block
- Sqrt

### Mental Checklist

1. Kya array / range hai?  
2. Kya range query chahiye?  
3. Kya update bhi chahiye?  
4. Kya simple implementation chahiye?  
5. Kya O(sqrt(n)) acceptable hai?  

Agar 2–3 answers **yes** hain, to Sqrt Decomposition try karo.

---

# 5. Sqrt Decomposition Problem Solve Karne Ke 5 Steps

```text
1. Block size decide karo
2. Blocks banao
3. Block answers precompute karo
4. Query function likho
5. Update function likho
```

Example — Range Sum Query:

```text
Block size = sqrt(n)

Blocks:
Har block ka sum store karo

Query:
Partial blocks + full blocks
```

---

# 6. Sqrt Decomposition Template — Range Sum

```java
class SqrtDecomposition {

    private int[] nums;

    private int[] blockSum;

    private int blockSize;

    private int n;

    public SqrtDecomposition(int[] nums) {

        this.nums = nums;

        this.n = nums.length;

        this.blockSize = (int) Math.sqrt(n);

        int blocks = (n + blockSize - 1) / blockSize;

        blockSum = new int[blocks];

        for (int i = 0; i < n; i++) {
            blockSum[i / blockSize] += nums[i];
        }
    }

    public void update(int index, int value) {

        int blockIndex = index / blockSize;

        blockSum[blockIndex] += value - nums[index];

        nums[index] = value;
    }

    public int rangeSum(int left, int right) {

        int sum = 0;

        int startBlock = left / blockSize;

        int endBlock = right / blockSize;

        if (startBlock == endBlock) {

            for (int i = left; i <= right; i++) {
                sum += nums[i];
            }

            return sum;
        }

        for (int i = left; i < (startBlock + 1) * blockSize; i++) {
            sum += nums[i];
        }

        for (int block = startBlock + 1; block < endBlock; block++) {
            sum += blockSum[block];
        }

        for (int i = endBlock * blockSize; i <= right; i++) {
            sum += nums[i];
        }

        return sum;
    }
}
```

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

## Why Sqrt Decomposition?

Range sum + update chahiye.

## Solution

```java
class NumArray {

    private SqrtDecomposition sqrtDecomposition;

    public NumArray(int[] nums) {
        sqrtDecomposition = new SqrtDecomposition(nums);
    }

    public void update(int index, int val) {
        sqrtDecomposition.update(index, val);
    }

    public int sumRange(int left, int right) {
        return sqrtDecomposition.rangeSum(left, right);
    }
}
```

## Dry Run

```text
nums =[1][3][5]

sumRange(0, 2):
1 + 3 + 5 = 9

update(1, 2):
nums =[1][2][5]

sumRange(0, 2):
1 + 2 + 5 = 8

Answer = 8
```

## Complexity

- Build: \(O(n)\)
- Update: \(O(1)\)
- Query: \(O(\sqrt{n})\)
- Space: \(O(\sqrt{n})\)

---

# 8. Example 2: Range Minimum Query

## Problem

Array diya hai.

Range minimum nikalo.

```text
nums =[1][2][3][5][6]

min(1, 3) -> 1
```

## Why Sqrt Decomposition?

Range min + update chahiye.

## Solution

```java
class SqrtDecompositionMin {

    private int[] nums;

    private int[] blockMin;

    private int blockSize;

    private int n;

    public SqrtDecompositionMin(int[] nums) {

        this.nums = nums;

        this.n = nums.length;

        this.blockSize = (int) Math.sqrt(n);

        int blocks = (n + blockSize - 1) / blockSize;

        blockMin = new int[blocks];

        Arrays.fill(blockMin, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {

            int blockIndex = i / blockSize;

            blockMin[blockIndex] = Math.min(
                blockMin[blockIndex],
                nums[i]
            );
        }
    }

    public void update(int index, int value) {

        int blockIndex = index / blockSize;

        nums[index] = value;

        blockMin[blockIndex] = Integer.MAX_VALUE;

        int start = blockIndex * blockSize;

        int end = Math.min(n, start + blockSize);

        for (int i = start; i < end; i++) {
            blockMin[blockIndex] = Math.min(
                blockMin[blockIndex],
                nums[i]
            );
        }
    }

    public int rangeMin(int left, int right) {

        int min = Integer.MAX_VALUE;

        int startBlock = left / blockSize;

        int endBlock = right / blockSize;

        if (startBlock == endBlock) {

            for (int i = left; i <= right; i++) {
                min = Math.min(min, nums[i]);
            }

            return min;
        }

        for (int i = left; i < (startBlock + 1) * blockSize; i++) {
            min = Math.min(min, nums[i]);
        }

        for (int block = startBlock + 1; block < endBlock; block++) {
            min = Math.min(min, blockMin[block]);
        }

        for (int i = endBlock * blockSize; i <= right; i++) {
            min = Math.min(min, nums[i]);
        }

        return min;
    }
}
```

## Dry Run

```text
nums =[1][2][3][5][6]

min(1, 3):
min(2, 6, 1) = 1

Answer = 1
```

## Complexity

- Build: \(O(n)\)
- Update: \(O(\sqrt{n})\)
- Query: \(O(\sqrt{n})\)
- Space: \(O(\sqrt{n})\)

---

# 9. Example 3: Range Maximum Query

## Problem

Array diya hai.

Range maximum nikalo.

```text
nums =[1][2][3][5][6]

max(1, 3) -> 6
```

## Why Sqrt Decomposition?

Range max + update chahiye.

## Solution

```java
class SqrtDecompositionMax {

    private int[] nums;

    private int[] blockMax;

    private int blockSize;

    private int n;

    public SqrtDecompositionMax(int[] nums) {

        this.nums = nums;

        this.n = nums.length;

        this.blockSize = (int) Math.sqrt(n);

        int blocks = (n + blockSize - 1) / blockSize;

        blockMax = new int[blocks];

        Arrays.fill(blockMax, Integer.MIN_VALUE);

        for (int i = 0; i < n; i++) {

            int blockIndex = i / blockSize;

            blockMax[blockIndex] = Math.max(
                blockMax[blockIndex],
                nums[i]
            );
        }
    }

    public void update(int index, int value) {

        int blockIndex = index / blockSize;

        nums[index] = value;

        blockMax[blockIndex] = Integer.MIN_VALUE;

        int start = blockIndex * blockSize;

        int end = Math.min(n, start + blockSize);

        for (int i = start; i < end; i++) {
            blockMax[blockIndex] = Math.max(
                blockMax[blockIndex],
                nums[i]
            );
        }
    }

    public int rangeMax(int left, int right) {

        int max = Integer.MIN_VALUE;

        int startBlock = left / blockSize;

        int endBlock = right / blockSize;

        if (startBlock == endBlock) {

            for (int i = left; i <= right; i++) {
                max = Math.max(max, nums[i]);
            }

            return max;
        }

        for (int i = left; i < (startBlock + 1) * blockSize; i++) {
            max = Math.max(max, nums[i]);
        }

        for (int block = startBlock + 1; block < endBlock; block++) {
            max = Math.max(max, blockMax[block]);
        }

        for (int i = endBlock * blockSize; i <= right; i++) {
            max = Math.max(max, nums[i]);
        }

        return max;
    }
}
```

## Dry Run

```text
nums =[1][2][3][5][6]

max(1, 3):
max(2, 6, 1) = 6

Answer = 6
```

## Complexity

- Build: \(O(n)\)
- Update: \(O(\sqrt{n})\)
- Query: \(O(\sqrt{n})\)
- Space: \(O(\sqrt{n})\)

---

# 10. Example 4: Mo’s Algorithm

## Problem

Array diya hai.

Multiple range queries hain.

Har range mein distinct elements count karo.

```text
nums =[1][2][3]
queries = [
 ,[0][4]
 ,[1][3]
[2][4]
]

Output =[2][3]
```

## Why Sqrt Decomposition?

Mo’s Algorithm Sqrt Decomposition use karta hai.

## Solution

```java
public int[] distinctElements(
    int[] nums,
    int[][] queries
) {

    int n = nums.length;

    int q = queries.length;

    int blockSize = (int) Math.sqrt(n);

    Integer[] queryOrder = new Integer[q];

    for (int i = 0; i < q; i++) {
        queryOrder[i] = i;
    }

    Arrays.sort(queryOrder, (a, b) -> {

        int blockA = queries[a] / blockSize;

        int blockB = queries[b] / blockSize;

        if (blockA != blockB) {
            return Integer.compare(blockA, blockB);
        }

        return Integer.compare(queries[a], queries[b]);
    });

    int[] result = new int[q];

    int[] frequency = new int[1000001];

    int distinctCount = 0;

    int currentLeft = 0;

    int currentRight = -1;

    for (int index : queryOrder) {

        int left = queries[index];

        int right = queries[index];

        while (currentLeft > left) {
            currentLeft--;
            add(nums[currentLeft], frequency, result, index);
        }

        while (currentRight < right) {
            currentRight++;
            add(nums[currentRight], frequency, result, index);
        }

        while (currentLeft < left) {
            remove(nums[currentLeft], frequency, result, index);
            currentLeft++;
        }

        while (currentRight > right) {
            remove(nums[currentRight], frequency, result, index);
            currentRight--;
        }
    }

    return result;
}

private void add(
    int num,
    int[] frequency,
    int[] result,
    int queryIndex
) {

    if (frequency[num] == 0) {
        result[queryIndex]++;
    }

    frequency[num]++;
}

private void remove(
    int num,
    int[] frequency,
    int[] result,
    int queryIndex
) {

    frequency[num]--;

    if (frequency[num] == 0) {
        result[queryIndex]--;
    }
}
```

## Dry Run

```text
nums =[1][2][3]
queries = [
 ,[0][4]
 ,[1][3]
[2][4]
]

Mo's Algorithm:
Query 1:
distinct = 3

Query 2:
distinct = 2

Query 3:
distinct = 3

Answer =[2][3]
```

## Complexity

- Time: \(O((n + q) \sqrt{n})\)
- Space: \(O(n)\)

---

# 11. Sqrt Decomposition Patterns

| Pattern | Block Value | Query |
|---|---|---|
| Range Sum | Sum | sum(left, right) |
| Range Min | Min | min(left, right) |
| Range Max | Max | max(left, right) |
| Mo’s Algorithm | Frequency | Distinct count |
| Range Update | Lazy value | Range update + query |

---

# 12. Sqrt Decomposition vs Segment Tree

| Feature | Sqrt Decomposition | Segment Tree |
|---|---|---|
| Time | O(sqrt(n)) | O(log n) |
| Space | O(sqrt(n)) | O(n) |
| Implementation | Simple | Complex |
| Best for | Simple queries | Complex queries |
| Example | Range sum | Range min + update |

### Rule

```text
Simple queries -> Sqrt Decomposition
Complex queries -> Segment Tree
```

---

# 13. Common Mistakes

## Mistake 1: Block Size Galat Karna

```java
int blockSize = (int) Math.sqrt(n);
```

---

## Mistake 2: Block Sum Galat Karna

```java
blockSum[i / blockSize] += nums[i];
```

---

## Mistake 3: Query Boundaries Galat Karna

```java
if (startBlock == endBlock) {
    // partial block
}
```

---

## Mistake 4: Update Mein Block Update Bhoolna

```java
blockSum[blockIndex] += value - nums[index];
```

---

## Mistake 5: Mo’s Algorithm Mein Order Galat Karna

```java
Arrays.sort(queryOrder, (a, b) -> {
    // sort by block then right
});
```

---

# 14. Sqrt Decomposition Decision Flow

```text
Problem padho
      |
      v
Kya range query chahiye?
      |
      +-- No --> Sqrt Decomposition probably nahi
      |
      +-- Yes
          |
          v
Kya simple implementation chahiye?
      |
      +-- Yes --> Sqrt Decomposition
      |
      +-- Kya O(log n) chahiye?
              |
              +-- Yes --> Segment Tree
              |
              +-- Kya Mo’s Algorithm chahiye?
                      |
                      +-- Yes --> Sqrt Decomposition
```

---

# 15. Important Problems

## Medium

- Range Sum Query — Mutable
- Range Sum Query 2D — Mutable
- Count of Smaller Numbers After Self
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

> **Agar problem range sum, range min/max, point update, ya Mo’s Algorithm maang rahi hai — Sqrt Decomposition try karo.**

Sqrt Decomposition ka main power ye hai ki range based queries ko simple way mein solve karta hai — jisse dynamic array problems efficiently handle ho jati hain.