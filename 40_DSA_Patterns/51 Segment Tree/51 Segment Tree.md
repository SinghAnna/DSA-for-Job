# 🚀 DSA Pattern 51 — Segment Tree

> **Goal:** Segment Tree ko product-based company interview level par samajhna — real-world intuition, kyun Segment Tree use hota hai, kaise identify karein, build, update, query, range sum, range min/max, lazy propagation, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Segment Tree Kya Hai?

Segment Tree ek binary tree data structure hai jo array ke **ranges** ke answers store karta hai.

### Simple Example

```text
Array:
[1][3][5][7][9][11]

Range Sum Query:
sum(1, 3) = 3 + 5 + 7 = 15
```

### Segment Tree Structure

```text
[0][5]
               /      \
[2][3][5][0]
          /    \      /    \
[1][3][4][5][0][2]
       /  \          /  \
[3][4][0][1]
```

### Core Intuition

> Har node ek range ka answer store karta hai.

Example:

```text
Node  -> sum of full array[0][5]
Node  -> sum of first 3 elements[0][2]
Node  -> sum of last 3 elements[3][5]
```

---

## 2. Segment Tree Kyun Use Karte Hain?

Main reasons:

1. Range Sum Query
2. Range Min Query
3. Range Max Query
4. Range Update
5. Point Update
6. Lazy Propagation
7. Dynamic Array Queries
8. Competitive Programming

### Core Intuition

> Segment Tree range queries ko O(log n) time mein karta hai.

Example:

```text
Naive:
sum(1, 3) -> O(n)

Segment Tree:
sum(1, 3) -> O(log n)
```

---

## 3. Real-World Production Examples

| Real System | Segment Tree Use |
|---|---|
| Stock Market | Range max price |
| Analytics Dashboard | Range sum |
| Gaming | Player score range |
| E-commerce | Price range query |
| Cloud Monitoring | CPU usage range |
| Database | Range aggregation |
| Maps | Traffic range query |
| Recommendation System | Score range query |
| Ad System | Impression range query |
| Finance | Portfolio range query |
| IoT | Sensor data range query |
| Compiler | Interval analysis |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Stock prices ke array mein kisi range ka maximum price nikalna hai. Kaise karoge?”

Answer:

```text
Segment Tree use karenge.

Har node range ka max price store karega.

Query:
max(1, 3) -> O(log n)
```

Ya:

> “Analytics dashboard mein kisi date range ka total revenue nikalna hai. Kaise approach karoge?”

Answer:

```text
Segment Tree use karenge.

Har node range ka total revenue store karega.

Query:
sum(start, end) -> O(log n)
```

---

## 4. Kaise Pata Chalega Ki Segment Tree Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Segment Tree |
|---|---|
| Range sum | Segment Tree |
| Range min | Segment Tree |
| Range max | Segment Tree |
| Range update | Segment Tree |
| Point update | Segment Tree |
| Dynamic queries | Segment Tree |
| Lazy propagation | Segment Tree |

### Common Keywords

- Range sum
- Range min
- Range max
- Update
- Query
- Dynamic
- Lazy propagation
- Interval

### Mental Checklist

1. Kya array / range hai?  
2. Kya range query chahiye?  
3. Kya update bhi chahiye?  
4. Kya dynamic data hai?  
5. Kya O(log n) chahiye?  

Agar 2–3 answers **yes** hain, to Segment Tree try karo.

---

# 5. Segment Tree Problem Solve Karne Ke 5 Steps

```text
1. Node structure decide karo
2. Build function likho
3. Update function likho
4. Query function likho
5. Final answer nikalo
```

Example — Range Sum Query:

```text
Node:
sum of range

Build:
left + right

Update:
point update

Query:
range sum
```

---

# 6. Segment Tree Template — Range Sum

```java
class SegmentTree {

    private int[] tree;

    private int n;

    public SegmentTree(int[] nums) {

        n = nums.length;

        tree = new int[4 * n];

        build(nums, 0, 0, n - 1);
    }

    private void build(int[] nums, int node, int start, int end) {

        if (start == end) {
            tree[node] = nums[start];
            return;
        }

        int mid = (start + end) / 2;

        build(nums, 2 * node + 1, start, mid);

        build(nums, 2 * node + 2, mid + 1, end);

        tree[node] =
            tree[2 * node + 1] +
            tree[2 * node + 2];
    }

    public void update(int index, int value) {
        update(0, 0, n - 1, index, value);
    }

    private void update(int node, int start, int end, int index, int value) {

        if (start == end) {
            tree[node] = value;
            return;
        }

        int mid = (start + end) / 2;

        if (index <= mid) {
            update(2 * node + 1, start, mid, index, value);
        } else {
            update(2 * node + 2, mid + 1, end, index, value);
        }

        tree[node] =
            tree[2 * node + 1] +
            tree[2 * node + 2];
    }

    public int query(int left, int right) {
        return query(0, 0, n - 1, left, right);
    }

    private int query(int node, int start, int end, int left, int right) {

        if (right < start || end < left) {
            return 0;
        }

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;

        int leftSum =
            query(2 * node + 1, start, mid, left, right);

        int rightSum =
            query(2 * node + 2, mid + 1, end, left, right);

        return leftSum + rightSum;
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

## Why Segment Tree?

Range sum + update dono chahiye.

## Solution

```java
class NumArray {

    private SegmentTree segmentTree;

    public NumArray(int[] nums) {
        segmentTree = new SegmentTree(nums);
    }

    public void update(int index, int val) {
        segmentTree.update(index, val);
    }

    public int sumRange(int left, int right) {
        return segmentTree.query(left, right);
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
```

## Complexity

- Build: \(O(n)\)
- Update: \(O(\log n)\)
- Query: \(O(\log n)\)
- Space: \(O(n)\)

---

# 8. Example 2: Range Minimum Query

## Problem

Array diya hai.

Range minimum nikalo.

```text
nums =[1][2][3][5][6]

min(1, 3) -> 1
```

## Why Segment Tree?

Range min + update chahiye.

## Solution

```java
class SegmentTreeMin {

    private int[] tree;

    private int n;

    public SegmentTreeMin(int[] nums) {

        n = nums.length;

        tree = new int[4 * n];

        build(nums, 0, 0, n - 1);
    }

    private void build(int[] nums, int node, int start, int end) {

        if (start == end) {
            tree[node] = nums[start];
            return;
        }

        int mid = (start + end) / 2;

        build(nums, 2 * node + 1, start, mid);

        build(nums, 2 * node + 2, mid + 1, end);

        tree[node] = Math.min(
            tree[2 * node + 1],
            tree[2 * node + 2]
        );
    }

    public int query(int left, int right) {
        return query(0, 0, n - 1, left, right);
    }

    private int query(int node, int start, int end, int left, int right) {

        if (right < start || end < left) {
            return Integer.MAX_VALUE;
        }

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;

        int leftMin =
            query(2 * node + 1, start, mid, left, right);

        int rightMin =
            query(2 * node + 2, mid + 1, end, left, right);

        return Math.min(leftMin, rightMin);
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
- Query: \(O(\log n)\)
- Space: \(O(n)\)

---

# 9. Example 3: Range Maximum Query

## Problem

Array diya hai.

Range maximum nikalo.

```text
nums =[1][2][3][5][6]

max(1, 3) -> 6
```

## Why Segment Tree?

Range max + update chahiye.

## Solution

```java
class SegmentTreeMax {

    private int[] tree;

    private int n;

    public SegmentTreeMax(int[] nums) {

        n = nums.length;

        tree = new int[4 * n];

        build(nums, 0, 0, n - 1);
    }

    private void build(int[] nums, int node, int start, int end) {

        if (start == end) {
            tree[node] = nums[start];
            return;
        }

        int mid = (start + end) / 2;

        build(nums, 2 * node + 1, start, mid);

        build(nums, 2 * node + 2, mid + 1, end);

        tree[node] = Math.max(
            tree[2 * node + 1],
            tree[2 * node + 2]
        );
    }

    public int query(int left, int right) {
        return query(0, 0, n - 1, left, right);
    }

    private int query(int node, int start, int end, int left, int right) {

        if (right < start || end < left) {
            return Integer.MIN_VALUE;
        }

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;

        int leftMax =
            query(2 * node + 1, start, mid, left, right);

        int rightMax =
            query(2 * node + 2, mid + 1, end, left, right);

        return Math.max(leftMax, rightMax);
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
- Query: \(O(\log n)\)
- Space: \(O(n)\)

---

# 10. Example 4: Lazy Propagation — Range Update

## Problem

Array diya hai.

Range update + range sum query chahiye.

```text
nums =[1][3][5][7][9][11]

update(1, 3, +10)
sum(1, 3) -> 3 + 5 + 7 + 30 = 45
```

## Why Lazy Propagation?

Range update efficiently karna hai.

## Solution

```java
class SegmentTreeLazy {

    private int[] tree;

    private int[] lazy;

    private int n;

    public SegmentTreeLazy(int[] nums) {

        n = nums.length;

        tree = new int[4 * n];

        lazy = new int[4 * n];

        build(nums, 0, 0, n - 1);
    }

    private void build(int[] nums, int node, int start, int end) {

        if (start == end) {
            tree[node] = nums[start];
            return;
        }

        int mid = (start + end) / 2;

        build(nums, 2 * node + 1, start, mid);

        build(nums, 2 * node + 2, mid + 1, end);

        tree[node] =
            tree[2 * node + 1] +
            tree[2 * node + 2];
    }

    public void updateRange(int left, int right, int value) {
        updateRange(0, 0, n - 1, left, right, value);
    }

    private void updateRange(
        int node,
        int start,
        int end,
        int left,
        int right,
        int value
    ) {

        if (lazy[node] != 0) {

            tree[node] += (end - start + 1) * lazy[node];

            if (start != end) {
                lazy[2 * node + 1] += lazy[node];
                lazy[2 * node + 2] += lazy[node];
            }

            lazy[node] = 0;
        }

        if (right < start || end < left) {
            return;
        }

        if (left <= start && end <= right) {

            tree[node] += (end - start + 1) * value;

            if (start != end) {
                lazy[2 * node + 1] += value;
                lazy[2 * node + 2] += value;
            }

            return;
        }

        int mid = (start + end) / 2;

        updateRange(2 * node + 1, start, mid, left, right, value);

        updateRange(2 * node + 2, mid + 1, end, left, right, value);

        tree[node] =
            tree[2 * node + 1] +
            tree[2 * node + 2];
    }

    public int queryRange(int left, int right) {
        return queryRange(0, 0, n - 1, left, right);
    }

    private int queryRange(
        int node,
        int start,
        int end,
        int left,
        int right
    ) {

        if (lazy[node] != 0) {

            tree[node] += (end - start + 1) * lazy[node];

            if (start != end) {
                lazy[2 * node + 1] += lazy[node];
                lazy[2 * node + 2] += lazy[node];
            }

            lazy[node] = 0;
        }

        if (right < start || end < left) {
            return 0;
        }

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;

        int leftSum =
            queryRange(2 * node + 1, start, mid, left, right);

        int rightSum =
            queryRange(2 * node + 2, mid + 1, end, left, right);

        return leftSum + rightSum;
    }
}
```

## Dry Run

```text
nums =[1][3][5][7][9][11]

updateRange(1, 3, +10)

sum(1, 3):
3 + 5 + 7 + 30 = 45

Answer = 45
```

## Complexity

- Build: \(O(n)\)
- Range Update: \(O(\log n)\)
- Range Query: \(O(\log n)\)
- Space: \(O(n)\)

---

# 11. Segment Tree Patterns

| Pattern | Node Value | Query |
|---|---|---|
| Range Sum | Sum | sum(left, right) |
| Range Min | Min | min(left, right) |
| Range Max | Max | max(left, right) |
| Range GCD | GCD | gcd(left, right) |
| Lazy Propagation | Sum / Min / Max | range update + query |
| Dynamic Segment Tree | Custom | dynamic range |

---

# 12. Segment Tree vs Fenwick Tree

| Feature | Segment Tree | Fenwick Tree |
|---|---|---|
| Range query | Yes | Yes |
| Range update | Yes | Limited |
| Lazy propagation | Yes | No |
| Memory | More | Less |
| Implementation | Complex | Simple |
| Best for | Complex range queries | Prefix sum with updates |

### Rule

```text
Complex range queries -> Segment Tree
Simple prefix sum + update -> Fenwick Tree
```

---

# 13. Common Mistakes

## Mistake 1: Tree Size Galat Karna

```java
tree = new int[4 * n];
```

---

## Mistake 2: Build Function Galat Karna

```java
tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
```

---

## Mistake 3: Query Boundaries Galat Karna

```java
if (right < start || end < left) {
    return 0;
}
```

---

## Mistake 4: Lazy Propagation Mein Lazy Clear Bhoolna

```java
lazy[node] = 0;
```

---

## Mistake 5: Update Mein Parent Update Bhoolna

```java
tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
```

---

# 14. Segment Tree Decision Flow

```text
Problem padho
      |
      v
Kya range query chahiye?
      |
      +-- No --> Segment Tree probably nahi
      |
      +-- Yes
          |
          v
Kya update bhi chahiye?
      |
      +-- Yes --> Segment Tree
      |
      +-- Kya range update chahiye?
              |
              +-- Yes --> Lazy Propagation
              |
              +-- Kya simple prefix sum hai?
                      |
                      +-- Yes --> Fenwick Tree
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

> **Agar problem range sum, range min/max, range update, ya dynamic queries maang rahi hai — Segment Tree try karo.**

Segment Tree ka main power ye hai ki range based