# 🚀 DSA Pattern 23 — Merge Two Lists

> **Goal:** Merge Two Lists pattern ko product-based company interview level par samajhna — real-world intuition, kyun use hota hai, kaise identify karein, merge two sorted lists, merge two sorted arrays, merge K sorted lists, merge sort, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Merge Two Lists Kya Hai?

Merge Two Lists ka matlab hai:

```text
Do sorted lists ko compare karke
ek sorted list banana.
```

Example:

```text
List 1: 1 -> 3 -> 5
List 2: 2 -> 4 -> 6

Merged:
1 -> 2 -> 3 -> 4 -> 5 -> 6
```

Hum har step par dono lists ke current elements compare karte hain:

```text
Chhota element result mein add hota hai
Us list ka pointer aage badhta hai
```

---

## 2. Merge Two Lists Kyun Use Karte Hain?

Main reasons:

1. **Do sorted data sources ko combine karna**
2. **Merge Sort ka core step**
3. **K sorted lists ko merge karna**
4. **External sorting / large file merging**
5. **Database merge join**
6. **Streaming data merge**

### Core Intuition

> Dono lists already sorted hain, isliye har step par sirf current heads compare karne ki zaroorat hai.

Example:

```text
l1 = 1 -> 3 -> 5
l2 = 2 -> 4 -> 6

Compare 1 and 2:
1 smaller -> result mein 1

Compare 3 and 2:
2 smaller -> result mein 2

Compare 3 and 4:
3 smaller -> result mein 3
```

---

## 3. Real-World Production Examples

| Real System | Merge Two Lists Use |
|---|---|
| Database | Merge join on sorted indexes |
| Search Engine | Merge sorted posting lists |
| Kafka Streams | Merge sorted event streams |
| Log System | Merge sorted logs from multiple servers |
| Payment System | Merge transaction histories |
| E-commerce | Merge sorted product rankings |
| Git | Merge sorted commit histories |
| External Sorting | Merge sorted chunks of large files |
| News Feed | Merge ranked posts from multiple sources |
| Analytics | Merge time-series data |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Tumhare system mein do sorted log files hain. Unhe ek sorted file mein merge karna hai, memory efficient tarike se. Kaise karoge?”

Answer:

```text
Dono files se ek-ek record read karo.
Compare karo.
Chhota record output mein likho.
Us file ka pointer aage badhao.

Time: O(n + m)
Space: O(1), output file exclude karke.
```

Ya:

> “Kafka se multiple partitions se sorted events merge karne hain. Kaise karoge?”

Answer:

```text
Har partition ka current event heap mein rakho.
Sabse chhota event pop karo.
Us partition ka next event heap mein daalo.

Ye Merge K Sorted Lists pattern hai.
```

---

## 4. Kaise Pata Chalega Ki Merge Two Lists Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Merge Pattern |
|---|---|
| Two sorted linked lists | Merge Two Lists |
| Two sorted arrays | Merge Two Arrays |
| Merge K sorted lists | Min-Heap + Merge |
| Merge sort implementation | Divide + Merge |
| Merge intervals | Sorting + Merge |
| Merge two sorted files | Two-pointer merge |
| Merge time-series data | Two-pointer merge |
| Merge sorted streams | Heap-based merge |

### Common Keywords

- Merge
- Sorted lists
- Sorted arrays
- Combine
- Merge sort
- K sorted
- Streams
- Logs
- Files

### Mental Checklist

1. Kya do sorted sequences hain?  
2. Kya unhe ek sorted sequence banana hai?  
3. Kya extra space minimize karna hai?  
4. Kya linked list hai ya array?  
5. Kya K sequences hain?  

Agar 2–3 answers **yes** hain, to Merge pattern try karo.

---

# 5. Merge Two Sorted Lists Ka Core Idea

Do pointers rakho:

```text
pointer1 -> list1 ka current node
pointer2 -> list2 ka current node
```

Compare:

```text
Agar pointer1.val <= pointer2.val:
    pointer1 node result mein add karo
    pointer1 aage badho

Else:
    pointer2 node result mein add karo
    pointer2 aage badho
```

End mein jo list bachi ho, use directly attach kar do.

---

# 6. Template — Merge Two Sorted Linked Lists

```java
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode current = dummy;

    while (l1 != null && l2 != null) {
        if (l1.val <= l2.val) {
            current.next = l1;
            l1 = l1.next;
        } else {
            current.next = l2;
            l2 = l2.next;
        }

        current = current.next;
    }

    current.next = (l1 != null) ? l1 : l2;

    return dummy.next;
}
```

### Why Dummy Node?

Result list ka head pehle se known nahi hota.

Dummy node se head handling easy ho jati hai.

---

# 7. Example 1: Merge Two Sorted Lists

## Problem

Do sorted linked lists merge karo.

```text
l1 = 1 -> 2 -> 4
l2 = 1 -> 3 -> 4

Output:
1 -> 1 -> 2 -> 3 -> 4 -> 4
```

## Brute Force

Dono lists ke values array mein lo, sort karo, nayi list banao.

```text
Time: O((n + m) log(n + m))
Space: O(n + m)
```

## Why Two Pointer Merge?

Lists already sorted hain.

Isliye \(O(n + m)\) merge possible hai.

## Solution

```java
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode current = dummy;

    while (l1 != null && l2 != null) {
        if (l1.val <= l2.val) {
            current.next = l1;
            l1 = l1.next;
        } else {
            current.next = l2;
            l2 = l2.next;
        }

        current = current.next;
    }

    current.next = (l1 != null) ? l1 : l2;

    return dummy.next;
}
```

## Dry Run

```text
l1 = 1 -> 2 -> 4
l2 = 1 -> 3 -> 4

dummy -> null
current = dummy

Compare 1 and 1:
1 <= 1
current.next = 1 from l1
l1 = 2
current = 1

List:
1

Compare 2 and 1:
2 > 1
current.next = 1 from l2
l2 = 3
current = 1

List:
1 -> 1

Compare 2 and 3:
2 <= 3
current.next = 2
l1 = 4
current = 2

List:
1 -> 1 -> 2

Compare 4 and 3:
4 > 3
current.next = 3
l2 = 4
current = 3

List:
1 -> 1 -> 2 -> 3

Compare 4 and 4:
4 <= 4
current.next = 4 from l1
l1 = null
current = 4

List:
1 -> 1 -> 2 -> 3 -> 4

l1 == null
current.next = l2 remaining 4

Final:
1 -> 1 -> 2 -> 3 -> 4 -> 4
```

## Complexity

- Time: \(O(n + m)\)
- Space: \(O(1)\)

---

# 8. Example 2: Merge Two Sorted Arrays

## Problem

Do sorted arrays merge karo.

```text
nums1 =[1][3][5]
nums2 =[2][4][6]

Output:
[1][3][4][5][6][2]
```

## Solution

```java
public int[] mergeSortedArrays(int[] nums1, int[] nums2) {
    int n = nums1.length;
    int m = nums2.length;

    int[] result = new int[n + m];

    int i = 0, j = 0, k = 0;

    while (i < n && j < m) {
        if (nums1[i] <= nums2[j]) {
            result[k++] = nums1[i++];
        } else {
            result[k++] = nums2[j++];
        }
    }

    while (i < n) {
        result[k++] = nums1[i++];
    }

    while (j < m) {
        result[k++] = nums2[j++];
    }

    return result;
}
```

## Dry Run

```text
nums1 =[1][3][5]
nums2 =[2][4][6]

Compare 1 and 2:
1 <= 2
result = 1
i = 1

Compare 3 and 2:
3 > 2
result = 2
j = 1

Compare 3 and 4:
3 <= 4
result = 3
i = 2

Compare 5 and 4:
5 > 4
result = 4
j = 2

Compare 5 and 6:
5 <= 6
result = 5
i = 3

nums1 finished
Copy nums2 remaining:
result = 6

Final:
[1][2][3][4][5][6]
```

## Complexity

- Time: \(O(n + m)\)
- Space: \(O(n + m)\)

---

# 9. Example 3: Merge Sorted Array — In-place

## Problem

`nums1` mein `m` valid elements hain aur usme extra space hai `n` elements ke liye.

`nums2` ke `n` elements ko `nums1` mein merge karo in-place.

```text
nums1 =[0][1][2][3]
m = 3

nums2 =[2][5][6]
n = 3

Output:
[1][2][3][5][6]
```

## Why Merge From Back?

Agar front se merge karenge, to `nums1` ke elements overwrite ho sakte hain.

Isliye back se merge karo.

## Solution

```java
public void merge(int[] nums1, int m, int[] nums2, int n) {
    int i = m - 1;
    int j = n - 1;
    int k = m + n - 1;

    while (j >= 0) {
        if (i >= 0 && nums1[i] > nums2[j]) {
            nums1[k--] = nums1[i--];
        } else {
            nums1[k--] = nums2[j--];
        }
    }
}
```

## Dry Run

```text
nums1 =[0][1][2][3]
nums2 =[2][5][6]

i = 2 -> nums1[i] = 3
j = 2 -> nums2[j] = 6
k = 5

Compare 3 and 6:
3 < 6
nums1 = 6
j = 1
k = 4

Compare 3 and 5:
3 < 5
nums1 = 5
j = 0
k = 3

Compare 3 and 2:
3 > 2
nums1 = 3
i = 1
k = 2

Compare 2 and 2:
2 <= 2
nums1 = 2
j = -1
k = 1

nums2 finished

Final:
[1][2][3][5][6]
```

## Complexity

- Time: \(O(n + m)\)
- Space: \(O(1)\)

---

# 10. Example 4: Merge K Sorted Lists

## Problem

`k` sorted linked lists ko ek sorted linked list mein merge karo.

```text
lists = [
  1 -> 4 -> 5,
  1 -> 3 -> 4,
  2 -> 6
]

Output:
1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6
```

## Why Min-Heap?

Har list ka current head heap mein rakho.

Har baar smallest node pop karo.

Us node ka next node heap mein daalo.

## Solution

```java
public ListNode mergeKLists(ListNode[] lists) {
    PriorityQueue<ListNode> minHeap =
        new PriorityQueue<>((a, b) -> a.val - b.val);

    for (ListNode node : lists) {
        if (node != null) {
            minHeap.offer(node);
        }
    }

    ListNode dummy = new ListNode(0);
    ListNode current = dummy;

    while (!minHeap.isEmpty()) {
        ListNode node = minHeap.poll();

        current.next = node;
        current = current.next;

        if (node.next != null) {
            minHeap.offer(node.next);
        }
    }

    return dummy.next;
}
```

## Dry Run

```text
Lists:
1 -> 4 -> 5
1 -> 3 -> 4
2 -> 6

Heap initially:
1, 1, 2

Poll 1:
result: 1
push 4

Heap:
1, 2, 4

Poll 1:
result: 1 -> 1
push 3

Heap:
2, 3, 4

Poll 2:
result: 1 -> 1 -> 2
push 6

Heap:
3, 4, 6

Poll 3:
result: 1 -> 1 -> 2 -> 3
push 4

Heap:
4, 4, 6

Poll 4:
result: 1 -> 1 -> 2 -> 3 -> 4
push 5

Heap:
4, 5, 6

Poll 4:
result: 1 -> 1 -> 2 -> 3 -> 4 -> 4

Heap:
5, 6

Poll 5:
result: 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5

Heap:
6

Poll 6:
result:
1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6
```

## Complexity

Let:

```text
n = total nodes
k = number of lists
```

- Time: \(O(n \log k)\)
- Space: \(O(k)\)

---

# 11. Example 5: Merge Sort — Divide and Merge

## Problem

Array ko sort karo using Merge Sort.

```text
arr =[1][2][5][9]
Output:[1][2][5][9]
```

## Why Merge Two Lists?

Merge Sort mein:

1. Array ko halves mein divide karo.
2. Har half sort karo.
3. Dono sorted halves merge karo.

## Solution

```java
public int[] mergeSort(int[] arr) {
    if (arr.length <= 1) {
        return arr;
    }

    int mid = arr.length / 2;

    int[] left = Arrays.copyOfRange(arr, 0, mid);
    int[] right = Arrays.copyOfRange(arr, mid, arr.length);

    return merge(mergeSort(left), mergeSort(right));
}

private int[] merge(int[] left, int[] right) {
    int[] result = new int[left.length + right.length];

    int i = 0, j = 0, k = 0;

    while (i < left.length && j < right.length) {
        if (left[i] <= right[j]) {
            result[k++] = left[i++];
        } else {
            result[k++] = right[j++];
        }
    }

    while (i < left.length) {
        result[k++] = left[i++];
    }

    while (j < right.length) {
        result[k++] = right[j++];
    }

    return result;
}
```

## Dry Run

```text
arr =[1][2][5][9]

Divide:
 and[1][2][5][9]

Divide again:
, , , 

Merge:
[2][5]
[1][9]

Final merge:
[5][9][1][2]
```

## Complexity

- Time: \(O(n \log n)\)
- Space: \(O(n)\)

---

# 12. Merge Two Lists vs Merge K Lists

| Feature | Merge Two Lists | Merge K Lists |
|---|---|---|
| Inputs | 2 sorted lists | K sorted lists |
| Approach | Two pointers | Min-Heap |
| Time | \(O(n + m)\) | \(O(n \log k)\) |
| Space | \(O(1)\) | \(O(k)\) |
| Example | Merge Two Sorted Lists | Merge K Sorted Lists |

### Rule

```text
2 lists -> Two-pointer merge
K lists -> Min-Heap merge
```

---

# 13. Common Mistakes

## Mistake 1: Remaining List Attach Na Karna

Loop ke baad ek list mein nodes bach sakte hain.

```java
current.next = (l1 != null) ? l1 : l2;
```

Ye zaroori hai.

---

## Mistake 2: Dummy Node Return Kar Dena

Galat:

```java
return dummy;
```

Correct:

```java
return dummy.next;
```

---

## Mistake 3: Merge K Lists Mein Null Node Heap Mein Dalna

```java
if (node != null) {
    minHeap.offer(node);
}
```

Null check zaroori hai.

---

## Mistake 4: In-place Array Merge Mein Front Se Merge Karna

`nums1` ke valid elements overwrite ho sakte hain.

Isliye back se merge karo.

---

## Mistake 5: Merge Sort Mein Stable Comparison Bhoolna

Stability ke liye:

```java
if (left[i] <= right[j])
```

Use karo, not `<`.

---

## Mistake 6: Large Files Mein Poori Data Memory Mein Load Karna

Production mein streaming merge karo:

```text
Ek-ek record read karo
Compare karo
Output mein likho
```

---

# 14. Merge Decision Flow

```text
Problem padho
      |
      v
Kya sorted lists/arrays/streams merge karni hain?
      |
      +-- No --> Merge pattern probably nahi
      |
      +-- Yes
          |
          v
Kya 2 lists hain?
      |
      +-- Yes --> Two-pointer merge
      |
      +-- Kya K lists hain?
              |
              +-- Yes --> Min-Heap merge
              |
              +-- Kya merge sort implement karna hai?
                      |
                      +-- Yes --> Divide + Merge
```

---

# 15. Important Problems

## Easy

- Merge Two Sorted Lists
- Merge Sorted Array
- Merge Two Sorted Arrays

## Medium

- Merge K Sorted Lists
- Sort List
- Merge Intervals
- Insert Interval
- Merge Triplets to Form Target Triplet

## Hard

- Merge K Sorted Lists
- Smallest Range Covering Elements from K Lists
- Find K Pairs with Smallest Sums
- Median of Two Sorted Arrays

---

# 16. One-Line Recognition

> **Agar do ya K sorted lists/arrays/streams ko ek sorted output mein combine karna hai — Merge Two Lists / Merge K Lists pattern try karo.**

Merge pattern ka main power ye hai ki already sorted data ka fayda uthakar har step par sirf current elements compare karta hai, isliye merging \(O(n + m)\) ya \(O(n \log k)\) mein efficient ho jati hai.