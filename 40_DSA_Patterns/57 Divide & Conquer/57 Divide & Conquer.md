# 🚀 DSA Pattern 57 — Divide & Conquer

> **Goal:** Divide & Conquer ko product-based company interview level par samajhna — real-world intuition, kyun Divide & Conquer use hota hai, kaise identify karein, Merge Sort, Quick Sort, Quickselect, Closest Pair, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Divide & Conquer Kya Hai?

Divide & Conquer ek algorithm design technique hai jisme problem ko:

```text
1. Divide karo
2. Conquer karo
3. Combine karo
```

### Simple Example

```text
Merge Sort:
1. Array ko do halves mein divide karo
2. Har half ko sort karo
3. Dono halves ko merge karo
```

### Core Intuition

> Badi problem ko chhoti subproblems mein todo, solve karo, phir combine karo.

Example:

```text
Array:
[1][2][5][9]

Divide:
 and[2][5][9][1]

Conquer:
 and[1][2][5][9]

Combine:
[2][5][9][1]
```

---

## 2. Divide & Conquer Kyun Use Karte Hain?

Main reasons:

1. Sorting
2. Searching
3. Maximum subarray
4. Closest pair
5. Fast exponentiation
6. Matrix multiplication
7. Count inversions
8. Majority element

### Core Intuition

> Divide & Conquer complex problems ko efficient way mein solve karta hai.

Example:

```text
Naive:
O(n^2)

Divide & Conquer:
O(n log n)
```

---

## 3. Real-World Production Examples

| Real System | Divide & Conquer Use |
|---|---|
| Database | External merge sort |
| Search Engine | Index building |
| E-commerce | Product sorting |
| Cloud Computing | Distributed sorting |
| Machine Learning | Decision tree |
| Image Processing | Image segmentation |
| Compiler | Syntax tree |
| Finance | Portfolio optimization |
| Logistics | Route optimization |
| Recommendation System | Similarity search |
| Game Development | Spatial partitioning |
| Bioinformatics | Sequence alignment |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Large dataset ko sort karna hai jo memory mein fit nahi hota. Kaise karoge?”

Answer:

```text
External Merge Sort use karenge.

1. Data ko chunks mein divide karo
2. Har chunk ko sort karo
3. Chunks ko merge karo
```

Ya:

> “E-commerce app mein millions of products ko price ke hisaab se sort karna hai. Kaise approach karoge?”

Answer:

```text
Merge Sort / Quick Sort use karenge.

Divide & Conquer se efficient sorting hoga.
```

---

## 4. Kaise Pata Chalega Ki Divide & Conquer Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Divide & Conquer |
|---|---|
| Sorting | Merge Sort / Quick Sort |
| Searching | Binary Search |
| Maximum subarray | Divide & Conquer |
| Closest pair | Divide & Conquer |
| Fast exponentiation | Divide & Conquer |
| Count inversions | Merge Sort |
| Majority element | Divide & Conquer |

### Common Keywords

- Sort
- Search
- Divide
- Merge
- Maximum
- Minimum
- Count
- Majority

### Mental Checklist

1. Kya problem ko divide kar sakte hain?  
2. Kya subproblems independent hain?  
3. Kya combine kar sakte hain?  
4. Kya recursion possible hai?  
5. Kya O(n log n) chahiye?  

Agar 2–3 answers **yes** hain, to Divide & Conquer try karo.

---

# 5. Divide & Conquer Problem Solve Karne Ke 5 Steps

```text
1. Base case define karo
2. Divide step likho
3. Conquer step likho
4. Combine step likho
5. Final answer nikalo
```

Example — Merge Sort:

```text
Base case:
if (left >= right) return;

Divide:
mid = (left + right) / 2

Conquer:
mergeSort(left, mid)
mergeSort(mid + 1, right)

Combine:
merge(left, mid, right)
```

---

# 6. Merge Sort

## Kya Hai?

Merge Sort ek stable sorting algorithm hai jo:

```text
1. Array ko do halves mein divide karta hai
2. Har half ko sort karta hai
3. Dono halves ko merge karta hai
```

### Time Complexity

```text
Best: O(n log n)
Average: O(n log n)
Worst: O(n log n)
Space: O(n)
```

---

## Merge Sort Template

```java
public void mergeSort(int[] nums, int left, int right) {

    if (left >= right) {
        return;
    }

    int mid = left + (right - left) / 2;

    mergeSort(nums, left, mid);

    mergeSort(nums, mid + 1, right);

    merge(nums, left, mid, right);
}

private void merge(int[] nums, int left, int mid, int right) {

    int[] temp = new int[right - left + 1];

    int i = left;

    int j = mid + 1;

    int k = 0;

    while (i <= mid && j <= right) {

        if (nums[i] <= nums[j]) {
            temp[k++] = nums[i++];
        } else {
            temp[k++] = nums[j++];
        }
    }

    while (i <= mid) {
        temp[k++] = nums[i++];
    }

    while (j <= right) {
        temp[k++] = nums[j++];
    }

    for (int x = 0; x < temp.length; x++) {
        nums[left + x] = temp[x];
    }
}
```

---

## Example: Merge Sort

## Problem

Array ko sort karo.

```text
nums =[1][2][5][9]
Output =[2][5][9][1]
```

## Solution

```java
public int[] sortArray(int[] nums) {
    mergeSort(nums, 0, nums.length - 1);
    return nums;
}
```

## Dry Run

```text
nums =[1][2][5][9]

Divide:
 and[2][5][9][1]

Conquer:
 and[1][2][5][9]

Combine:
[2][5][9][1]

Answer =[1][2][5][9]
```

## Complexity

- Time: \(O(n \log n)\)
- Space: \(O(n)\)

---

# 7. Quick Sort

## Kya Hai?

Quick Sort ek sorting algorithm hai jo:

```text
1. Pivot choose karta hai
2. Array ko partition karta hai
3. Left aur right parts ko sort karta hai
```

### Time Complexity

```text
Best: O(n log n)
Average: O(n log n)
Worst: O(n^2)
Space: O(log n)
```

---

## Quick Sort Template

```java
public void quickSort(int[] nums, int left, int right) {

    if (left >= right) {
        return;
    }

    int pivotIndex = partition(nums, left, right);

    quickSort(nums, left, pivotIndex - 1);

    quickSort(nums, pivotIndex + 1, right);
}

private int partition(int[] nums, int left, int right) {

    int pivot = nums[right];

    int i = left - 1;

    for (int j = left; j < right; j++) {

        if (nums[j] < pivot) {
            i++;
            swap(nums, i, j);
        }
    }

    swap(nums, i + 1, right);

    return i + 1;
}

private void swap(int[] nums, int i, int j) {

    int temp = nums[i];

    nums[i] = nums[j];

    nums[j] = temp;
}
```

---

## Example: Quick Sort

## Problem

Array ko sort karo.

```text
nums =[1][2][5][9]
Output =[1][2][5][9]
```

## Solution

```java
public int[] sortArray(int[] nums) {
    quickSort(nums, 0, nums.length - 1);
    return nums;
}
```

## Dry Run

```text
nums =[1][2][5][9]

Pivot = 1

Partition:
[1][2][5][9]

Pivot = 5

Partition:
[2][5][9][1]

Answer =[1][2][5][9]
```

## Complexity

- Time: \(O(n \log n)\) average
- Space: \(O(\log n)\)

---

# 8. Quickselect

## Kya Hai?

Quickselect ek selection algorithm hai jo:

```text
1. Kth smallest / largest element nikalta hai
2. Quick Sort ka modified version hai
```

### Time Complexity

```text
Average: O(n)
Worst: O(n^2)
Space: O(1)
```

---

## Quickselect Template

```java
public int findKthLargest(int[] nums, int k) {

    int left = 0;

    int right = nums.length - 1;

    int target = nums.length - k;

    while (true) {

        int pivotIndex = partition(nums, left, right);

        if (pivotIndex == target) {
            return nums[pivotIndex];
        } else if (pivotIndex < target) {
            left = pivotIndex + 1;
        } else {
            right = pivotIndex - 1;
        }
    }
}

private int partition(int[] nums, int left, int right) {

    int pivot = nums[right];

    int i = left - 1;

    for (int j = left; j < right; j++) {

        if (nums[j] < pivot) {
            i++;
            swap(nums, i, j);
        }
    }

    swap(nums, i + 1, right);

    return i + 1;
}

private void swap(int[] nums, int i, int j) {

    int temp = nums[i];

    nums[i] = nums[j];

    nums[j] = temp;
}
```

---

## Example: Kth Largest Element

## Problem

Array diya hai.

Kth largest element nikalo.

```text
nums =[1][2][3][4][5][6]
k = 2

Output = 5
```

## Solution

```java
public int findKthLargest(int[] nums, int k) {
    return quickselect(nums, k);
}
```

## Dry Run

```text
nums =[1][2][3][4][5][6]
k = 2

Sorted:
[1][2][3][4][5][6]

2nd largest = 5

Answer = 5
```

## Complexity

- Time: \(O(n)\) average
- Space: \(O(1)\)

---

# 9. Maximum Subarray — Divide & Conquer

## Problem

Maximum sum contiguous subarray nikalo.

```text
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
Output = 6
```

## Why Divide & Conquer?

```text
1. Left half ka max subarray
2. Right half ka max subarray
3. Cross middle ka max subarray
```

## Solution

```java
public int maxSubArray(int[] nums) {
    return maxSubArray(nums, 0, nums.length - 1);
}

private int maxSubArray(int[] nums, int left, int right) {

    if (left == right) {
        return nums[left];
    }

    int mid = left + (right - left) / 2;

    int leftMax = maxSubArray(nums, left, mid);

    int rightMax = maxSubArray(nums, mid + 1, right);

    int crossMax = maxCrossingSum(nums, left, mid, right);

    return Math.max(
        leftMax,
        Math.max(rightMax, crossMax)
    );
}

private int maxCrossingSum(int[] nums, int left, int mid, int right) {

    int leftSum = Integer.MIN_VALUE;

    int sum = 0;

    for (int i = mid; i >= left; i--) {

        sum += nums[i];

        leftSum = Math.max(leftSum, sum);
    }

    int rightSum = Integer.MIN_VALUE;

    sum = 0;

    for (int i = mid + 1; i <= right; i++) {

        sum += nums[i];

        rightSum = Math.max(rightSum, sum);
    }

    return leftSum + rightSum;
}
```

## Dry Run

```text
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]

Left half:
[-2, 1, -3, 4]

Right half:
[-1, 2, 1, -5, 4]

Cross middle:
[4, -1, 2, 1]

Answer = 6
```

## Complexity

- Time: \(O(n \log n)\)
- Space: \(O(\log n)\)

---

# 10. Closest Pair of Points

## Problem

2D points diye hain.

Closest pair distance nikalo.

```text
points = [
 ,[1][2]
 ,[4][6]
 ,[3][1]
[7][8]
]

Output = 1.0
Explanation:  and[1][2][3]
```

## Why Divide & Conquer?

```text
1. Points ko x-coordinate ke hisaab se sort karo
2. Do halves mein divide karo
3. Left, right, aur strip ka closest pair nikalo
```

## Solution

```java
public double closestPair(int[][] points) {

    Arrays.sort(points, (a, b) ->
        Integer.compare(a, b)
    );

    return closestPair(points, 0, points.length - 1);
}

private double closestPair(int[][] points, int left, int right) {

    if (right - left <= 3) {
        return bruteForce(points, left, right);
    }

    int mid = left + (right - left) / 2;

    double leftDist = closestPair(points, left, mid);

    double rightDist = closestPair(points, mid + 1, right);

    double minDist = Math.min(leftDist, rightDist);

    return Math.min(minDist, stripClosest(points, left, right, mid, minDist));
}

private double bruteForce(int[][] points, int left, int right) {

    double minDist = Double.MAX_VALUE;

    for (int i = left; i <= right; i++) {

        for (int j = i + 1; j <= right; j++) {

            minDist = Math.min(
                minDist,
                distance(points[i], points[j])
            );
        }
    }

    return minDist;
}

private double stripClosest(
    int[][] points,
    int left,
    int right,
    int mid,
    double minDist
) {

    List<int[]> strip = new ArrayList<>();

    for (int i = left; i <= right; i++) {

        if (Math.abs(points[i] - points[mid]) < minDist) {
            strip.add(points[i]);
        }
    }

    double best = minDist;

    for (int i = 0; i < strip.size(); i++) {

        for (int j = i + 1; j < strip.size() &&
             strip.get(j) - strip.get(i) < best; j++) {

            best = Math.min(
                best,
                distance(strip.get(i), strip.get(j))
            );
        }
    }

    return best;
}

private double distance(int[] a, int[] b) {

    double dx = a - b;

    double dy = a - b;

    return Math.sqrt(dx * dx + dy * dy);
}
```

## Complexity

- Time: \(O(n \log n)\)
- Space: \(O(n)\)

---

# 11. Divide & Conquer Patterns

| Pattern | Technique | Use |
|---|---|---|
| Merge Sort | Divide + Merge | Sorting |
| Quick Sort | Partition | Sorting |
| Quickselect | Partition | Kth element |
| Maximum Subarray | Divide + Combine | Max sum |
| Closest Pair | Divide + Strip | Geometry |
| Fast Exponentiation | Divide + Multiply | Power calculation |

---

# 12. Divide & Conquer vs DP

| Feature | Divide & Conquer | DP |
|---|---|---|
| Subproblems | Independent | Overlapping |
| Memoization | Not needed | Needed |
| Example | Merge Sort | Fibonacci |
| Time | Usually O(n log n) | Usually O(n) / O(n^2) |

### Rule

```text
Independent subproblems -> Divide & Conquer
Overlapping subproblems -> DP
```

---

# 13. Common Mistakes

## Mistake 1: Base Case Bhoolna

```java
if (left >= right) {
    return;
}
```

---

## Mistake 2: Merge Function Galat Karna

```java
while (i <= mid && j <= right) {
    // merge
}
```

---

## Mistake 3: Quick Sort Mein Pivot Selection Galat Karna

```java
int pivot = nums[right];
```

---

## Mistake 4: Quickselect Mein Target Index Galat Karna

```java
int target = nums.length - k;
```

---

## Mistake 5: Closest Pair Mein Strip Logic Bhoolna

```java
if (Math.abs(points[i] - points[mid]) < minDist) {
    strip.add(points[i]);
}
```

---

# 14. Divide & Conquer Decision Flow

```text
Problem padho
      |
      v
Kya sorting chahiye?
      |
      +-- Yes --> Merge Sort / Quick Sort
      |
      +-- Kya Kth element chahiye?
              |
              +-- Yes --> Quickselect
              |
              +-- Kya maximum subarray chahiye?
                      |
                      +-- Yes --> Divide & Conquer
                      |
                      +-- Kya closest pair chahiye?
                              |
                              +-- Yes --> Divide & Conquer
```

---

# 15. Important Problems

## Medium

- Sort an Array
- Kth Largest Element in an Array
- Maximum Subarray
- Count Inversions
- Majority Element

## Hard

- Median of Two Sorted Arrays
- Closest Pair of Points
- Reverse Pairs
- Count of Range Sum

---

# 16. One-Line Recognition

> **Agar problem sorting, searching, maximum subarray, closest pair, ya Kth element maang rahi hai aur subproblems independent hain — Divide & Conquer try karo.**

Divide & Conquer ka main power ye hai ki complex problems ko efficient way mein solve karta