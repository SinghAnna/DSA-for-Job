# 🚀 DSA Pattern 05 — Binary Search

> **Goal:** Samajhna ki Binary Search **kyun** use hota hai, problem mein **kaise identify** karein, aur reusable Java template kaise apply karein.

---

## 1. Binary Search Kya Hai?

Binary Search ek searching technique hai jo **sorted / monotonic search space** mein target dhundhne ke liye use hoti hai.

Har step par hum search space ko **aadha** kar dete hain.

```text
Search space:
[0][1][2][3][4][5][6][7]

mid = 3

Agar target bada hai:
[4][5][6][7]

Agar target chhota hai:
[0][1][2]
```

Isliye:

```text
n elements
-> n/2 elements
-> n/4 elements
-> n/8 elements
-> ...
-> 1 element
```

Total steps:

\[
\log_2 n
\]

---

## 2. Binary Search Kyun Use Karte Hain?

Main reason: **linear search bahut slow ho jaye.**

Example:

```text
arr =[1][3][5][7][9][11]
target = 9
```

Linear Search:

```text
1 -> 3 -> 5 -> 7 -> 9
```

Time: \(O(n)\)

Binary Search:

```text
left = 0, right = 5
mid = 2 -> arr = 5

5 < 9 -> left = 3

left = 3, right = 5
mid = 4 -> arr = 9

Found
```

Time: \(O(\log n)\)

### Core Intuition

> Har comparison ke baad aadha search space eliminate kar do.

Binary Search sirf sorted array ke liye nahi hai. Ye tab bhi kaam karta hai jab answer ka search space **monotonic** ho.

Example:

```text
Agar speed x se kaam ho jayega,
to speed x+1 se bhi kaam ho jayega.
```

Ye bhi monotonic property hai.

---

## 3. Kaise Pata Chalega Ki Binary Search Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Binary Search Use |
|---|---|
| Array is **sorted** | Classic Binary Search |
| Time complexity \(O(\log n)\) chahiye | Binary Search |
| Find first / last occurrence | Modified Binary Search |
| Search in rotated sorted array | Modified Binary Search |
| Minimum / maximum answer | Binary Search on Answer |
| Monotonic yes/no condition | Binary Search on Answer |
| Peak element | Binary Search on Unsorted Array |
| Search in 2D sorted matrix | Binary Search |
| Kth smallest / largest | Binary Search on Answer |

### Mental Checklist

1. Kya array sorted hai?  
2. Kya answer ka search space monotonic hai?  
3. Kya hum ek condition check karke left/right half eliminate kar sakte hain?  
4. Kya problem mein first, last, minimum, maximum, ya boundary dhundhna hai?  
5. Kya brute force \(O(n)\) ya usse zyada hai, jabki \(O(\log n)\) possible hai?  

Agar 2–3 answers **yes** hain, to Binary Search try karo.

---

# 4. Binary Search Ke Main Variants

## Type 1: Classic Binary Search

Target exact position dhundhna hai.

```java
public int binarySearch(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return -1;
}
```

### Important

Mid calculate karne ka safe way:

```java
int mid = left + (right - left) / 2;
```

Ye avoid karo:

```java
int mid = (left + right) / 2;
```

Kyunki bade values par integer overflow ho sakta hai.

---

## Type 2: First Occurrence

Sorted array mein target ki **pehli position** dhundhni hai.

```java
public int firstOccurrence(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;
    int ans = -1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            ans = mid;
            right = mid - 1;
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return ans;
}
```

### Logic

Jab target mil jaye:

```text
Answer store karo
Left side mein aur first occurrence dhundo
right = mid - 1
```

---

## Type 3: Last Occurrence

Sorted array mein target ki **last position** dhundhni hai.

```java
public int lastOccurrence(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;
    int ans = -1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            ans = mid;
            left = mid + 1;
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return ans;
}
```

### Logic

Jab target mil jaye:

```text
Answer store karo
Right side mein aur last occurrence dhundo
left = mid + 1
```

---

## Type 4: Lower Bound

Pehla index dhundhna hai jahan:

```text
arr[index] >= target
```

```java
public int lowerBound(int[] arr, int target) {
    int left = 0;
    int right = arr.length;

    while (left < right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }

    return left;
}
```

### Use Case

- Search Insert Position
- First element greater than or equal to target

---

## Type 5: Upper Bound

Pehla index dhundhna hai jahan:

```text
arr[index] > target
```

```java
public int upperBound(int[] arr, int target) {
    int left = 0;
    int right = arr.length;

    while (left < right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] <= target) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }

    return left;
}
```

---

# 5. Example 1: Classic Binary Search

## Problem

Sorted array mein target dhundo. Agar mil jaye to index return karo, warna `-1`.

```text
nums = [-1, 0, 3, 5, 9, 12]
target = 9

Output: 4
```

## Why Binary Search?

Array sorted hai.

Har mid comparison ke baad hum aadha array discard kar sakte hain.

## Solution

```java
public int search(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return -1;
}
```

## Dry Run

```text
nums = [-1, 0, 3, 5, 9, 12]
target = 9

left = 0, right = 5
mid = 2
nums = 3
3 < 9 -> left = 3

left = 3, right = 5
mid = 4
nums = 9
Found -> return 4
```

## Complexity

- Time: \(O(\log n)\)
- Space: \(O(1)\)

---

# 6. Example 2: Search Insert Position

## Problem

Sorted array mein target dhundo. Agar present nahi hai, to jahan insert hona chahiye wo index return karo.

```text
nums =[1][3][5][6]
target = 5

Output: 2
```

```text
nums =[1][3][5][6]
target = 2

Output: 1
```

## Why Binary Search?

Hum basically ye dhundh rahe hain:

```text
First index where nums[index] >= target
```

Ye **Lower Bound** hai.

## Solution

```java
public int searchInsert(int[] nums, int target) {
    int left = 0;
    int right = nums.length;

    while (left < right) {
        int mid = left + (right - left) / 2;

        if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }

    return left;
}
```

## Dry Run

```text
nums =[1][3][5][6]
target = 2

left = 0, right = 4
mid = 2
nums = 5
5 >= 2 -> right = 2

left = 0, right = 2
mid = 1
nums = 3
3 >= 2 -> right = 1

left = 0, right = 1
mid = 0
nums = 1
1 < 2 -> left = 1

left == right
Answer = 1
```

## Complexity

- Time: \(O(\log n)\)
- Space: \(O(1)\)

---

# 7. Example 3: First and Last Position of Element

## Problem

Sorted array mein target ki first aur last position find karo.

```text
nums =[5][7][8][10]
target = 8

Output:[3][4]
```

## Why Modified Binary Search?

Normal Binary Search sirf koi ek occurrence dega.

Humein:

- First occurrence ke liye left side search karna hai
- Last occurrence ke liye right side search karna hai

## Solution

```java
public int[] searchRange(int[] nums, int target) {
    int first = firstOccurrence(nums, target);
    int last = lastOccurrence(nums, target);

    return new int[]{first, last};
}

private int firstOccurrence(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    int ans = -1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            ans = mid;
            right = mid - 1;
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return ans;
}

private int lastOccurrence(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    int ans = -1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            ans = mid;
            left = mid + 1;
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return ans;
}
```

## Complexity

- Time: \(O(\log n)\)
- Space: \(O(1)\)

---

# 8. Example 4: Search in Rotated Sorted Array

## Problem

Rotated sorted array mein target dhundo.

```text
nums =[0][1][2][4][5][6][7]
target = 0

Output: 4
```

## Why Binary Search?

Array rotated hai, but har mid point par kam se kam ek side **sorted** hoti hai.

```text
[0][1][2][4][5][6][7]
         ↑
        mid

Left side:  -> sorted[4][5][6][7]
Right side:  -> sorted[0][1][2]
```

Isliye decide kar sakte hain ki target kis side mein hoga.

## Solution

```java
public int search(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            return mid;
        }

        if (nums[left] <= nums[mid]) {
            // Left half sorted

            if (target >= nums[left] && target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        } else {
            // Right half sorted

            if (target > nums[mid] && target <= nums[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }

    return -1;
}
```

## Dry Run

```text
nums =[0][1][2][4][5][6][7]
target = 0

left = 0, right = 6
mid = 3
nums = 7

nums[left] = 4 <= nums[mid] = 7
Left half sorted

target = 0
0 >= 4? No

So target right side mein hai
left = 4

left = 4, right = 6
mid = 5
nums = 1

nums[left] = 0 <= nums[mid] = 1
Left half sorted

target = 0
0 >= 0 && 0 < 1
Yes

right = 4

left = 4, right = 4
mid = 4
nums = 0
Found
```

## Complexity

- Time: \(O(\log n)\)
- Space: \(O(1)\)

---

# 9. Example 5: Find Peak Element

## Problem

Array mein koi peak element dhundo. Peak element apne neighbors se bada hota hai.

```text
nums =[1][2][3]
Output: 2
Explanation: nums = 3 is a peak
```

## Why Binary Search?

Array sorted nahi hai, but peak exist karta hai.

Agar:

```text
nums[mid] < nums[mid + 1]
```

To right side mein definitely peak hoga.

Agar:

```text
nums[mid] > nums[mid + 1]
```

To left side mein peak hoga, ya mid khud peak ho sakta hai.

## Solution

```java
public int findPeakElement(int[] nums) {
    int left = 0;
    int right = nums.length - 1;

    while (left < right) {
        int mid = left + (right - left) / 2;

        if (nums[mid] < nums[mid + 1]) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }

    return left;
}
```

## Complexity

- Time: \(O(\log n)\)
- Space: \(O(1)\)

---

# 10. Example 6: Search a 2D Matrix

## Problem

2D matrix hai jisme:

- Har row sorted hai
- Har row ka first element previous row ke last element se bada hai

Target dhundo.

```text
matrix = [
 ,[1][3][5][7]
 ,[10][11][16][20]
[23][30][34][60]
]
target = 3

Output: true
```

## Why Binary Search?

Poori matrix ko ek sorted 1D array ki tarah treat kar sakte hain.

```text
1, 3, 5, 7, 10, 11, 16, 20, 23, 30, 34, 60
```

Index conversion:

```text
row = index / cols
col = index % cols
```

## Solution

```java
public boolean searchMatrix(int[][] matrix, int target) {
    int rows = matrix.length;
    int cols = matrix.length;

    int left = 0;
    int right = rows * cols - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        int row = mid / cols;
        int col = mid % cols;

        if (matrix[row][col] == target) {
            return true;
        } else if (matrix[row][col] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return false;
}
```

## Complexity

- Time: \(O(\log(m \times n))\)
- Space: \(O(1)\)

---

# 11. Binary Search on Answer — Preview

Kabhi-kabhi array sorted nahi hota, but **answer ka range monotonic** hota hai.

Example: Koko Eating Bananas.

```text
Agar speed 5 se bananas kha sakti hai,
to speed 6, 7, 8... se bhi kha sakti hai.
```

Condition:

```text
Can finish with speed x?
```

Ye monotonic hai:

```text
false false false true true true
```

Isliye hum answer par binary search karte hain.

```text
Search space:
[minimum speed, maximum speed]
```

Ye next detailed pattern hoga: **Binary Search on Answer**.

---

# 12. Common Mistakes

## Mistake 1: Wrong Mid Calculation

Galat:

```java
int mid = (left + right) / 2;
```

Bade values par overflow ho sakta hai.

Correct:

```java
int mid = left + (right - left) / 2;
```

---

## Mistake 2: `left <= right` vs `left < right` Confusion

### Classic Binary Search

```java
while (left <= right)
```

### Lower Bound / Upper Bound

```java
while (left < right)
```

---

## Mistake 3: Infinite Loop

Lower bound template mein:

```java
if (arr[mid] < target) {
    left = mid + 1;
} else {
    right = mid;
}
```

Agar galat likha:

```java
right = mid - 1;
```

To answer skip ho sakta hai.

---

## Mistake 4: Rotated Array Mein Sorted Half Galat Identify Karna

Hamesha check karo:

```java
if (nums[left] <= nums[mid]) {
    // left half sorted
}
```

`<=` important hai, especially single-element cases ke liye.

---

## Mistake 5: Answer Store Na Karna

First/last occurrence mein jab target mil jaye, turant return mat karo agar first/last chahiye.

```java
ans = mid;
right = mid - 1;   // first occurrence
```

ya

```java
ans = mid;
left = mid + 1;    // last occurrence
```

---

# 13. Binary Search Decision Flow

```text
Problem padho
      |
      v
Kya sorted array / monotonic condition hai?
      |
      +-- No --> Binary Search probably nahi
      |
      +-- Yes
          |
          v
Kya exact target dhundhna hai?
      |
      +-- Yes --> Classic Binary Search
      |
      +-- First / last occurrence?
      |       |
      |       +-- Yes --> Modified Binary Search
      |
      +-- Minimum / maximum feasible answer?
              |
              +-- Yes --> Binary Search on Answer
```

---

# 14. Important Problems

## Easy

- Binary Search
- Search Insert Position
- First Bad Version
- Sqrt(x)
- Guess Number Higher or Lower

## Medium

- Find First and Last Position of Element in Sorted Array
- Search in Rotated Sorted Array
- Find Minimum in Rotated Sorted Array
- Find Peak Element
- Search a 2D Matrix
- Koko Eating Bananas

## Hard

- Median of Two Sorted Arrays
- Split Array Largest Sum
- Kth Smallest Element in a Sorted Matrix
- Find K-th Smallest Pair Distance

---

# 15. One-Line Recognition

> **Agar sorted array hai, ya answer monotonic range mein hai, aur har check ke baad aadha space eliminate ho sakta hai — Binary Search use karo.**

Binary Search ka main power ye hai ki har step par search space half ho jata hai, isliye \(O(n)\) search \(O(\log n)\) mein convert ho jata hai.