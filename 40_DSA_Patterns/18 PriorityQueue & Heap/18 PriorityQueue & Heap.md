# 🚀 DSA Pattern 18 — PriorityQueue / Heap

> **Goal:** PriorityQueue / Heap ko product-based company interview level par samajhna — real-world intuition, kyun use hota hai, kaise identify karein, min-heap/max-heap, Top K, Kth largest/smallest, merge K sorted lists, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. PriorityQueue / Heap Kya Hai?

PriorityQueue ek special queue hai jisme elements ko **priority** ke basis par process kiya jata hai.

Normal Queue:

```text
FIFO
Jo pehle aaya, wo pehle bahar
```

PriorityQueue:

```text
Priority ke basis par bahar
Sabse chhota ya sabse bada element pehle
```

Java mein:

```java
// Min-Heap
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// Max-Heap
PriorityQueue<Integer> maxHeap =
    new PriorityQueue<>(Collections.reverseOrder());
```

### Min-Heap

```text
Sabse chhota element top par
```

```text
        1
       / \
      3   2
     / \
    5   4
```

### Max-Heap

```text
Sabse bada element top par
```

```text
        9
       / \
      7   8
     / \
    3   5
```

---

## 2. Heap Kyun Use Karte Hain?

Main reason: **baar-baar smallest/largest element chahiye.**

Example:

```text
Array se K largest elements nikalne hain.
```

Sorting:

```text
Time: O(n log n)
```

Heap:

```text
Time: O(n log k)
```

Agar `k << n`, to heap better hai.

Example:

```text
n = 1,000,000
k = 10

Sorting:
O(n log n)

Heap:
O(n log k)
```

### Core Operations

| Operation | Min-Heap | Max-Heap |
|---|---|---|
| Get smallest/largest | `peek()` | `peek()` |
| Insert | `offer(x)` | `offer(x)` |
| Remove top | `poll()` | `poll()` |
| Time | \(O(\log n)\) | \(O(\log n)\) |

### Core Intuition

> Agar problem mein repeatedly minimum ya maximum element chahiye, to Heap use karo.

---

## 3. Real-World Production Examples

| Real System | Heap Use |
|---|---|
| Operating System | CPU task scheduling by priority |
| Hospital System | Emergency patients by severity |
| Uber / Ola | Nearest drivers by distance |
| Swiggy / Zomato | Top restaurants by rating |
| YouTube / Netflix | Top trending videos |
| Stock Market | Highest price / lowest price tracking |
| Kafka Streams | Top K trending events |
| Search Engine | Top K relevant results |
| Job Scheduler | Highest priority job first |
| Ride Sharing | Nearest cab assignment |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Uber mein nearest 5 drivers kaise nikaloge?”

Answer:

```text
Max-Heap of size 5 maintain karo.

Har driver ke liye distance calculate karo.

Agar heap size < 5:
    driver insert karo

Else:
    Agar current driver current max se closer hai:
        max remove karo
        current driver insert karo

End mein heap mein 5 nearest drivers honge.
```

Ya:

> “Twitter trending topics kaise nikaloge?”

Answer:

```text
HashMap se frequency count karo.
Min-Heap of size K maintain karo frequency ke basis par.
Heap se top K trending topics nikalo.
```

---

## 4. Kaise Pata Chalega Ki Heap Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Heap |
|---|---|
| Kth largest element | Min-Heap of size K |
| Kth smallest element | Max-Heap of size K |
| Top K frequent | HashMap + Heap |
| K closest points | Max-Heap of size K |
| Merge K sorted lists | Min-Heap |
| Median from stream | Two Heaps |
| Task scheduler | Max-Heap |
| Reorganize string | Max-Heap |
| Last stone weight | Max-Heap |
| Minimum cost to connect ropes | Min-Heap |

### Common Keywords

- Kth largest
- Kth smallest
- Top K
- K closest
- K most frequent
- Merge K
- Median
- Priority
- Schedule
- Repeatedly minimum/maximum

### Mental Checklist

1. Kya repeatedly smallest/largest element chahiye?  
2. Kya K largest/smallest elements chahiye?  
3. Kya elements ko priority ke basis par process karna hai?  
4. Kya multiple sorted lists merge karni hain?  
5. Kya running median chahiye?  

Agar 2–3 answers **yes** hain, to Heap try karo.

---

# 5. Java PriorityQueue Basics

## Min-Heap

```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

minHeap.offer(5);
minHeap.offer(1);
minHeap.offer(3);

minHeap.peek(); // 1
minHeap.poll(); // removes 1
```

## Max-Heap

```java
PriorityQueue<Integer> maxHeap =
    new PriorityQueue<>(Collections.reverseOrder());

maxHeap.offer(5);
maxHeap.offer(1);
maxHeap.offer(3);

maxHeap.peek(); // 5
maxHeap.poll(); // removes 5
```

## Custom Comparator

Min-Heap by second value:

```java
PriorityQueue<int[]> minHeap =
    new PriorityQueue<>((a, b) -> a - b);
```

Max-Heap by second value:

```java
PriorityQueue<int[]> maxHeap =
    new PriorityQueue<>((a, b) -> b - a);
```

---

# 6. Heap Internals — Simple Explanation

Heap ek **Complete Binary Tree** hota hai.

Array mein store hota hai:

```text
Parent index = (i - 1) / 2
Left child = 2 * i + 1
Right child = 2 * i + 2
```

Example array:

```text
[1][2][3][4][5]
```

Tree:

```text
        1
       / \
      3   2
     / \
    5   4
```

### Insert

New element ko last mein add karo.

Phir parent se compare karke upar move karo.

```text
Heapify Up
```

Time: \(O(\log n)\)

### Remove Top

Root remove karo.

Last element ko root par le aao.

Phir children se compare karke neeche move karo.

```text
Heapify Down
```

Time: \(O(\log n)\)

---

# 7. Template — Kth Largest Element

## Problem

Array mein `kth` largest element nikalo.

```text
nums =[1][2][3][4][5][6]
k = 2

Output: 5
```

## Why Min-Heap?

Hum K largest elements chahiye.

Min-Heap mein sabse chhota element top par hota hai.

Heap size `k` rakho.

Agar naya element current min se bada hai:

```text
Current min remove karo
Naya element insert karo
```

End mein heap ka top hi `kth` largest hoga.

## Solution

```java
public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    for (int num : nums) {
        minHeap.offer(num);

        if (minHeap.size() > k) {
            minHeap.poll();
        }
    }

    return minHeap.peek();
}
```

## Dry Run

```text
nums =[1][2][3][4][5][6]
k = 2

num = 3:
heap = 

num = 2:
heap =[2][3]

num = 1:
heap =[1][2][3]
size > 2
remove 1
heap =[2][3]

num = 5:
heap =[2][3][5]
size > 2
remove 2
heap =[3][5]

num = 6:
heap =[3][5][6]
size > 2
remove 3
heap =[5][6]

num = 4:
heap =[4][5][6]
size > 2
remove 4
heap =[5][6]

Answer = heap.peek() = 5
```

## Complexity

- Time: \(O(n \log k)\)
- Space: \(O(k)\)

---

# 8. Template — Kth Smallest Element

## Problem

Array mein `kth` smallest element nikalo.

```text
nums =[3][4][7][10][15][20]
k = 3

Output: 7
```

## Why Max-Heap?

Hum K smallest elements chahiye.

Max-Heap mein sabse bada element top par hota hai.

Heap size `k` rakho.

Agar naya element current max se chhota hai:

```text
Current max remove karo
Naya element insert karo
```

End mein heap ka top hi `kth` smallest hoga.

## Solution

```java
public int findKthSmallest(int[] nums, int k) {
    PriorityQueue<Integer> maxHeap =
        new PriorityQueue<>(Collections.reverseOrder());

    for (int num : nums) {
        maxHeap.offer(num);

        if (maxHeap.size() > k) {
            maxHeap.poll();
        }
    }

    return maxHeap.peek();
}
```

## Dry Run

```text
nums =[3][4][7][10][15][20]
k = 3

num = 7:
heap = 

num = 10:
heap =[7][10]

num = 4:
heap =[4][10][7]
size > 3
remove 10
heap =[4][7]

num = 3:
heap =[3][4][7]
size > 3
remove 7
heap =[4][3]

num = 20:
heap =[3][4][20]
size > 3
remove 20
heap =[4][3]

num = 15:
heap =[3][4][15]
size > 3
remove 15
heap =[4][3]

Answer = heap.peek() = 4
```

Wait — expected answer `7` tha.

Dhyan do: `k = 3`, to 3rd smallest hai:

```text
Sorted:[3][4][7][10][15][20]
3rd smallest = 7
```

Dry run mein heap size handling galat ho gayi.

Correct heap after processing:

```text
num = 7:
heap = 

num = 10:
heap =[7][10]

num = 4:
heap =[4][10][7]

num = 3:
heap =[3][4][7][10]
size > 3
remove 10
heap =[4][7][3]

num = 20:
heap =[3][4][7][20]
size > 3
remove 20
heap =[3][4][7]

num = 15:
heap =[4][7][15][3]
size > 3
remove 15
heap =[3][4][7]

Answer = heap.peek() = 7
```

## Complexity

- Time: \(O(n \log k)\)
- Space: \(O(k)\)

---

# 9. Example: Top K Frequent Elements

## Problem

Array mein `k` sabse frequently occurring elements return karo.

```text
nums =[1][2][3]
k = 2

Output:[1][2]
```

## Why HashMap + Heap?

Step 1: Frequency count karo.

```text
1 -> 3
2 -> 2
3 -> 1
```

Step 2: Top `k` frequency wale elements chahiye.

Min-Heap of size `k` use karo.

## Solution

```java
public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> freq = new HashMap<>();

    for (int num : nums) {
        freq.put(num, freq.getOrDefault(num, 0) + 1);
    }

    PriorityQueue<int[]> minHeap =
        new PriorityQueue<>((a, b) -> a - b);

    for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
        minHeap.offer(new int[]{entry.getKey(), entry.getValue()});

        if (minHeap.size() > k) {
            minHeap.poll();
        }
    }

    int[] ans = new int[k];

    for (int i = k - 1; i >= 0; i--) {
        ans[i] = minHeap.poll();
    }

    return ans;
}
```

## Dry Run

```text
nums =[1][2][3]
k = 2

freq:
1 -> 3
2 -> 2
3 -> 1

Heap:
[1][3]
,[2][3][1]
,,[1][2][3]

Heap size > 2:
remove[3][1]

Final heap:
,[1][2][3]

Answer =[2][1]
```

## Complexity

- Time: \(O(n \log k)\)
- Space: \(O(n)\)

---

# 10. Example: K Closest Points to Origin

## Problem

2D points diye hain. Origin se `k` closest points return karo.

```text
points = [, [-2, 2]][1][3]
k = 1

Output: [[-2, 2]]
```

## Why Max-Heap?

Hum K closest points chahiye.

Max-Heap mein sabse door point top par rahega.

Heap size `k` rakho.

Agar naya point current max se closer hai:

```text
Current max remove karo
Naya point insert karo
```

## Distance Formula

```text
distance = x^2 + y^2
```

Square root ki zaroorat nahi, kyunki comparison same rahega.

## Solution

```java
public int[][] kClosest(int[][] points, int k) {
    PriorityQueue<int[]> maxHeap =
        new PriorityQueue<>((a, b) ->
            Integer.compare(
                b * b + b * b,
                a * a + a * a
            )
        );

    for (int[] point : points) {
        maxHeap.offer(point);

        if (maxHeap.size() > k) {
            maxHeap.poll();
        }
    }

    int[][] ans = new int[k];

    for (int i = 0; i < k; i++) {
        ans[i] = maxHeap.poll();
    }

    return ans;
}
```

## Dry Run

```text
points = [, [-2, 2]][1][3]
k = 1

Point:[3][1]
distance = 1 + 9 = 10
heap = [][1][3]

Point [-2, 2]:
distance = 4 + 4 = 8

8 < 10
Remove[1][3]
Insert [-2, 2]

Answer = [[-2, 2]]
```

## Complexity

- Time: \(O(n \log k)\)
- Space: \(O(k)\)

---

# 11. Example: Merge K Sorted Lists

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

Har baar smallest node nikalo.

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
Push 4

Heap:
1, 2, 4

Poll 1:
result: 1 -> 1
Push 3

Heap:
2, 3, 4

Poll 2:
result: 1 -> 1 -> 2
Push 6

Heap:
3, 4, 6

Poll 3:
result: 1 -> 1 -> 2 -> 3
Push 4

Heap:
4, 4, 6

Poll 4:
result: 1 -> 1 -> 2 -> 3 -> 4
Push 5

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
n = total number of nodes
k = number of lists
```

- Time: \(O(n \log k)\)
- Space: \(O(k)\)

---

# 12. Example: Last Stone Weight

## Problem

Stones ke weights diye hain.

Har step mein do sabse bade stones lo:

- Agar equal hain, dono destroy ho jate hain
- Agar different hain, difference wala stone wapas aa jata hai

Last stone weight return karo.

```text
stones =[1][2][4][7][8]
Output: 1
```

## Why Max-Heap?

Har step mein do largest stones chahiye.

## Solution

```java
public int lastStoneWeight(int[] stones) {
    PriorityQueue<Integer> maxHeap =
        new PriorityQueue<>(Collections.reverseOrder());

    for (int stone : stones) {
        maxHeap.offer(stone);
    }

    while (maxHeap.size() > 1) {
        int first = maxHeap.poll();
        int second = maxHeap.poll();

        if (first != second) {
            maxHeap.offer(first - second);
        }
    }

    return maxHeap.isEmpty() ? 0 : maxHeap.peek();
}
```

## Dry Run

```text
stones =[1][2][4][7][8]

Max-Heap:
[2][4][7][8][1]

Step 1:
8 and 7
difference = 1
heap =[1][2][4]

Step 2:
4 and 2
difference = 2
heap =[1][2]

Step 3:
2 and 1
difference = 1
heap =[1]

Step 4:
1 and 1
both destroyed
heap = 

Answer = 1
```

## Complexity

- Time: \(O(n \log n)\)
- Space: \(O(n)\)

---

# 13. Two Heaps — Preview

Running median ke liye do heaps use hote hain:

```text
Max-Heap -> smaller half
Min-Heap -> larger half
```

Example:

```text
Stream: 1, 2, 3

Max-Heap: 
Min-Heap:[2][3]

Median = (1 + 2) / 2 = 1.5
```

Rules:

```text
Max-Heap size == Min-Heap size
ya
Max-Heap size = Min-Heap size + 1
```

Median:

```text
Agar sizes equal:
    (maxHeap.peek() + minHeap.peek()) / 2

Else:
    maxHeap.peek()
```

---

# 14. Heap vs Sorting

| Feature | Heap | Sorting |
|---|---|---|
| K largest/smallest | \(O(n \log k)\) | \(O(n \log n)\) |
| Repeated min/max | \(O(\log n)\) | Not efficient |
| Full sorted order | Not directly | Yes |
| Streaming data | Best | Difficult |
| Space | \(O(k)\) possible | \(O(n)\) or \(O(1)\) |
| Example | Top K trending | Sort complete array |

### Rule

```text
Complete sorting chahiye -> Sorting
Top K / repeated min-max / streaming -> Heap
```

---

# 15. Common Mistakes

## Mistake 1: Kth Largest Ke Liye Max-Heap Use Karna

Kth largest ke liye:

```text
Min-Heap of size K
```

Kth smallest ke liye:

```text
Max-Heap of size K
```

Ye ulta mat karo.

---

## Mistake 2: Heap Size Control Bhoolna

```java
if (minHeap.size() > k) {
    minHeap.poll();
}
```

Ye line zaroori hai.

Warna heap poori array store karega aur time \(O(n \log n)\) ho jayega.

---

## Mistake 3: Comparator Direction Galat Karna

Min-Heap by frequency:

```java
(a, b) -> a - b
```

Max-Heap by frequency:

```java
(a, b) -> b - a
```

---

## Mistake 4: Custom Objects Mein Comparator Bhoolna

```java
PriorityQueue<int[]> heap = new PriorityQueue<>();
```

Ye arrays ko lexicographically compare karega, jo aksar galat hota hai.

Correct:

```java
PriorityQueue<int[]> heap =
    new PriorityQueue<>((a, b) -> a - b);
```

---

## Mistake 5: Peek vs Poll Confusion

```java
peek()
```

Sirf top dekhta hai, remove nahi karta.

```java
poll()
```

Top remove karta hai.

---

## Mistake 6: Null Nodes Heap Mein Dalna

Merge K Sorted Lists mein:

```java
if (node != null) {
    minHeap.offer(node);
}
```

Null check zaroori hai.

---

# 16. Heap Decision Flow

```text
Problem padho
      |
      v
Kya repeatedly min/max chahiye?
      |
      +-- No --> Heap probably nahi
      |
      +-- Yes
          |
          v
Kya K largest chahiye?
      |
      +-- Yes --> Min-Heap of size K
      |
      +-- Kya K smallest chahiye?
              |
              +-- Yes --> Max-Heap of size K
              |
              +-- Kya K sorted lists merge karni hain?
                      |
                      +-- Yes --> Min-Heap
                      |
                      +-- Kya running median chahiye?
                              |
                              +-- Yes --> Two Heaps
```

---

# 17. Important Problems

## Easy

- Kth Largest Element in a Stream
- Last Stone Weight
- Maximum Product of Two Elements in an Array

## Medium

- Kth Largest Element in an Array
- K Closest Points to Origin
- Top K Frequent Elements
- Merge K Sorted Lists
- Task Scheduler
- Reorganize String
- Sort Characters by Frequency
- Kth Smallest Element in a Sorted Matrix

## Hard

- Find Median from Data Stream
- Sliding Window Median
- Merge K Sorted Lists
- IPO
- Minimum Cost to Hire K Workers

---

# 18. One-Line Recognition

> **Agar problem repeatedly smallest/largest element, K largest/smallest, Top K, priority scheduling, ya K sorted lists merge maang rahi hai — PriorityQueue / Heap try karo.**

Heap ka main power ye hai ki complete sorting ki jagah sirf required priority elements maintain karta hai, isliye Top K problems \(O(n \log k)\) mein efficiently solve hoti hain.