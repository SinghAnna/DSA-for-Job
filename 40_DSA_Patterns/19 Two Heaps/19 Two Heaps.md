# 🚀 DSA Pattern 19 — Two Heaps

> **Goal:** Two Heaps ko product-based company interview level par samajhna — real-world intuition, kyun use hota hai, kaise identify karein, running median, sliding window median, balance rules, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Two Heaps Kya Hai?

Two Heaps pattern mein hum data ko **do halves** mein divide karte hain:

```text
1. Max-Heap -> smaller half
2. Min-Heap -> larger half
```

Example:

```text
Numbers: 1, 2, 3, 4, 5

Smaller half: 1, 2
Larger half: 3, 4, 5
```

```text
Max-Heap:[1][2]
Min-Heap:[3][4][5]
```

- Max-Heap ka top = smaller half ka maximum
- Min-Heap ka top = larger half ka minimum

Median inhi dono tops se nikalta hai.

---

## 2. Two Heaps Kyun Use Karte Hain?

Main reason: **streaming data ka median efficiently nikalna.**

Agar hum har naye number ke baad array sort karein:

```text
Time per insertion: O(n log n)
```

Two Heaps:

```text
Insert: O(log n)
Median: O(1)
```

### Median Kya Hota Hai?

Sorted data ka middle element.

Odd number of elements:

```text
[1][2][3][4][5]
Median = 3
```

Even number of elements:

```text
[1][2][3][4]
Median = (2 + 3) / 2 = 2.5
```

### Core Intuition

> Median ke liye humein sirf middle elements chahiye, poori sorted array nahi.

Isliye:

```text
Left half ka maximum -> Max-Heap top
Right half ka minimum -> Min-Heap top
```

---

## 3. Real-World Production Examples

| Real System | Two Heaps Use |
|---|---|
| Stock Market | Running median price |
| Payment System | Running median transaction amount |
| Analytics Dashboard | Median response time |
| Monitoring System | Median latency |
| Ride Sharing | Median trip fare |
| E-commerce | Median order value |
| Streaming Platform | Median watch time |
| Database Metrics | Median query execution time |
| Fraud Detection | Median transaction amount |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Payment dashboard mein live median transaction amount kaise nikaloge?”

Answer:

```text
Transactions continuously aa rahi hain.

Do heaps maintain karo:
- Max-Heap: lower half transactions
- Min-Heap: upper half transactions

Har new transaction ke baad heaps balance karo.

Median O(1) mein nikal jayega.
```

Ya:

> “Monitoring system mein p99 latency nahi, median latency chahiye. Streaming requests ke saath kaise maintain karoge?”

Answer:

```text
Response times ko Two Heaps mein daalo.
Har request ke baad median update karo.
Median query O(1) hogi.
```

---

## 4. Kaise Pata Chalega Ki Two Heaps Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Two Heaps |
|---|---|
| Running median | Two Heaps |
| Median from data stream | Two Heaps |
| Median after every insertion | Two Heaps |
| Sliding window median | Two Heaps + Lazy Deletion |
| Balance two halves | Two Heaps |
| Kth smallest and largest together | Two Heaps |
| Median query frequent hai | Two Heaps |

### Common Keywords

- Median
- Stream
- Running median
- Dynamic data
- Insert number
- Find median
- Sliding window median

### Mental Checklist

1. Kya median chahiye?  
2. Kya data continuously aa raha hai?  
3. Kya har insertion ke baad median chahiye?  
4. Kya sorting har baar expensive lag rahi hai?  
5. Kya data ko lower aur upper half mein divide kar sakte hain?  

Agar 2–3 answers **yes** hain, to Two Heaps try karo.

---

# 5. Two Heaps Ka Structure

```text
Max-Heap              Min-Heap
Smaller Half          Larger Half

                     
   /   \               /   \
    []               
```

```text
Max-Heap top = 2
Min-Heap top = 3
```

Median:

```text
Total elements = 5 (odd)
Median = Max-Heap top = 2? 
```

Nahi.

Correct balance:

```text
Max-Heap:[1][2]
Min-Heap:[3][4][5]
```

```text
Max-Heap top = 2
Min-Heap top = 3
```

Total elements = 5, odd.

Median:

```text
Max-Heap top = 2
```

But sorted array:

```text
[1][2][3][4][5]
Median = 3
```

To balance rule ye hoga:

```text
Max-Heap size = Min-Heap size + 1
```

Example:

```text
Max-Heap:[1][2][3]
Min-Heap:[4][5]
```

```text
Max-Heap top = 3
Median = 3
```

Correct.

---

# 6. Balance Rules

## Rule 1: Size Balance

```text
Max-Heap size >= Min-Heap size

Difference maximum 1 ho sakta hai.
```

```text
Allowed:
maxHeap.size() == minHeap.size()
maxHeap.size() == minHeap.size() + 1
```

## Rule 2: Order Balance

```text
Max-Heap top <= Min-Heap top
```

Example:

```text
Max-Heap top = 3
Min-Heap top = 4

Valid
```

Invalid:

```text
Max-Heap top = 5
Min-Heap top = 3
```

Is case mein tops swap karo.

---

# 7. Insert Number Template

```java
public void addNum(int num) {
    maxHeap.offer(num);
    minHeap.offer(maxHeap.poll());

    if (minHeap.size() > maxHeap.size()) {
        maxHeap.offer(minHeap.poll());
    }
}
```

### Step-by-Step Logic

1. Naye number ko Max-Heap mein daalo.
2. Max-Heap ka top Min-Heap mein bhejo.
3. Agar Min-Heap bada ho gaya, to Min-Heap ka top Max-Heap mein bhejo.

Isse dono rules maintain rehte hain:

```text
Max-Heap size >= Min-Heap size
Max-Heap top <= Min-Heap top
```

---

# 8. Find Median Template

```java
public double findMedian() {
    if (maxHeap.size() > minHeap.size()) {
        return maxHeap.peek();
    }

    return (maxHeap.peek() + minHeap.peek()) / 2.0;
}
```

### Odd Total Elements

```text
Max-Heap size > Min-Heap size
Median = Max-Heap top
```

### Even Total Elements

```text
Max-Heap size == Min-Heap size
Median = average of both tops
```

---

# 9. Example 1: Find Median from Data Stream

## Problem

Data stream se numbers aate rahenge.

Har point par median find karna hai.

```text
addNum(1)
findMedian() -> 1

addNum(2)
findMedian() -> 1.5

addNum(3)
findMedian() -> 2
```

## Why Two Heaps?

Median ke liye humein:

```text
Lower half ka maximum
Upper half ka minimum
```

chahiye.

Isliye:

```text
Max-Heap -> lower half
Min-Heap -> upper half
```

## Solution

```java
class MedianFinder {
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());

        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }

        return (maxHeap.peek() + minHeap.peek()) / 2.0;
    }
}
```

## Dry Run

### Add 1

```text
maxHeap = 
minHeap = []

Move maxHeap top to minHeap:
maxHeap = []
minHeap = 

minHeap.size() > maxHeap.size()
Move 1 back:

maxHeap = 
minHeap = []

Median = 1
```

### Add 2

```text
maxHeap =[1][2]
minHeap = []

Move 2 to minHeap:
maxHeap = 
minHeap = 

Sizes equal

Median = (1 + 2) / 2 = 1.5
```

### Add 3

```text
maxHeap =[1][3]
minHeap = 

Move 3 to minHeap:
maxHeap = 
minHeap =[2][3]

minHeap.size() > maxHeap.size()
Move 2 to maxHeap:

maxHeap =[1][2]
minHeap = 

Median = maxHeap top = 2
```

## Complexity

- `addNum`: \(O(\log n)\)
- `findMedian`: \(O(1)\)
- Space: \(O(n)\)

---

# 10. Example 2: Sliding Window Median

## Problem

Array aur window size `k` diya hai.

Har sliding window ka median return karo.

```text
nums = [1, 3, -1, -3, 5, 3, 6, 7]
k = 3

Output: [1, -1, -1, 3, 5, 6]
```

## Why Two Heaps + Lazy Deletion?

Window slide hone par purana element remove karna padta hai.

Normal heap se arbitrary element remove karna efficient nahi hota.

Isliye:

```text
Two Heaps + Lazy Deletion
```

### Lazy Deletion Idea

Element ko turant heap se remove nahi karte.

Usse `invalid` mark karte hain.

Jab wo heap ke top par aaye, tab remove karte hain.

## Solution

```java
public double[] medianSlidingWindow(int[] nums, int k) {
    double[] ans = new double[nums.length - k + 1];

    PriorityQueue<Integer> maxHeap =
        new PriorityQueue<>((a, b) -> Long.compare(b, a));

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    Map<Integer, Integer> invalid = new HashMap<>();

    for (int i = 0; i < k; i++) {
        addNum(maxHeap, minHeap, invalid, nums[i]);
    }

    ans = getMedian(maxHeap, minHeap, k);

    for (int i = k; i < nums.length; i++) {
        removeNum(maxHeap, minHeap, invalid, nums[i - k]);
        addNum(maxHeap, minHeap, invalid, nums[i]);
        ans[i - k + 1] = getMedian(maxHeap, minHeap, k);
    }

    return ans;
}
```

Helper methods:

```java
private void addNum(PriorityQueue<Integer> maxHeap,
                    PriorityQueue<Integer> minHeap,
                    Map<Integer, Integer> invalid,
                    int num) {

    if (!maxHeap.isEmpty() && num > maxHeap.peek()) {
        minHeap.offer(num);
    } else {
        maxHeap.offer(num);
    }

    balance(maxHeap, minHeap, invalid);
}

private void removeNum(PriorityQueue<Integer> maxHeap,
                       PriorityQueue<Integer> minHeap,
                       Map<Integer, Integer> invalid,
                       int num) {

    invalid.put(num, invalid.getOrDefault(num, 0) + 1);

    if (!maxHeap.isEmpty() && num <= maxHeap.peek()) {
        maxHeap.offer(num);
        maxHeap.poll();
    } else {
        minHeap.offer(num);
        minHeap.poll();
    }

    balance(maxHeap, minHeap, invalid);
}

private void balance(PriorityQueue<Integer> maxHeap,
                     PriorityQueue<Integer> minHeap,
                     Map<Integer, Integer> invalid) {

    while (!maxHeap.isEmpty() &&
           invalid.getOrDefault(maxHeap.peek(), 0) > 0) {

        invalid.put(maxHeap.peek(),
                    invalid.get(maxHeap.peek()) - 1);
        maxHeap.poll();
    }

    while (!minHeap.isEmpty() &&
           invalid.getOrDefault(minHeap.peek(), 0) > 0) {

        invalid.put(minHeap.peek(),
                    invalid.get(minHeap.peek()) - 1);
        minHeap.poll();
    }

    if (maxHeap.size() > minHeap.size() + 1) {
        minHeap.offer(maxHeap.poll());
    } else if (minHeap.size() > maxHeap.size()) {
        maxHeap.offer(minHeap.poll());
    }
}

private double getMedian(PriorityQueue<Integer> maxHeap,
                         PriorityQueue<Integer> minHeap,
                         int k) {

    if (k % 2 == 1) {
        return maxHeap.peek();
    }

    return ((double) maxHeap.peek() + minHeap.peek()) / 2.0;
}
```

## Complexity

- Time: \(O(n \log n)\)
- Space: \(O(n)\)

---

# 11. Example 3: IPO

## Problem

Tumhare paas initial capital `w` hai.

`k` projects complete karne hain.

Har project ke liye:

```text
capital[i] -> project start karne ke liye required capital
profit[i] -> project complete hone par profit
```

Maximum final capital nikalo.

```text
k = 2
w = 0
profits =[1][2][3]
capital =[0][1]

Output: 4
```

## Why Two Heaps?

Hum har step par:

```text
Available capital se start ho sakne wale projects mein
maximum profit wala project choose karna chahte hain.
```

Isliye:

```text
Min-Heap -> capital ke according projects
Max-Heap -> profit ke according available projects
```

## Solution

```java
public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
    PriorityQueue<int[]> minHeap =
        new PriorityQueue<>((a, b) -> a - b);

    PriorityQueue<int[]> maxHeap =
        new PriorityQueue<>((a, b) -> b - a);

    for (int i = 0; i < profits.length; i++) {
        minHeap.offer(new int[]{capital[i], profits[i]});
    }

    for (int i = 0; i < k; i++) {
        while (!minHeap.isEmpty() && minHeap.peek() <= w) {
            maxHeap.offer(minHeap.poll());
        }

        if (maxHeap.isEmpty()) {
            break;
        }

        w += maxHeap.poll();
    }

    return w;
}
```

## Dry Run

```text
k = 2
w = 0
profits =[1][2][3]
capital =[0][1]

Min-Heap by capital:
,,[0][1][2][3]

Iteration 1:
Projects with capital <= 0:
[0][1]

Move to Max-Heap by profit:
Max-Heap = 

Pick profit 1
w = 0 + 1 = 1

Iteration 2:
Projects with capital <= 1:
,[1][2][3]

Move to Max-Heap:
Max-Heap =[2][3]

Pick profit 3
w = 1 + 3 = 4

Answer = 4
```

## Complexity

- Time: \(O(n \log n)\)
- Space: \(O(n)\)

---

# 12. Two Heaps vs One Heap

| Feature | One Heap | Two Heaps |
|---|---|---|
| Kth largest/smallest | Best | Not needed |
| Running median | Difficult | Best |
| Streaming median | Difficult | Best |
| Data divide into halves | No | Yes |
| Space | \(O(k)\) or \(O(n)\) | \(O(n)\) |
| Example | Top K Frequent | Median Finder |

### Rule

```text
Median / balanced halves -> Two Heaps
Top K / repeated min-max -> One Heap
```

---

# 13. Common Mistakes

## Mistake 1: Max-Heap aur Min-Heap Ulta Use Karna

Correct:

```text
Max-Heap -> smaller half
Min-Heap -> larger half
```

Galat:

```text
Max-Heap -> larger half
Min-Heap -> smaller half
```

---

## Mistake 2: Balance Rule Bhoolna

```text
Max-Heap size >= Min-Heap size
Difference <= 1
```

Agar balance nahi hoga, median galat aayega.

---

## Mistake 3: Even Median Mein Integer Division

Galat:

```java
return (maxHeap.peek() + minHeap.peek()) / 2;
```

Ye integer division karega.

Correct:

```java
return (maxHeap.peek() + minHeap.peek()) / 2.0;
```

---

## Mistake 4: Overflow Ignore Karna

Bade numbers mein:

```java
(maxHeap.peek() + minHeap.peek())
```

Overflow ho sakta hai.

Safe version:

```java
return ((double) maxHeap.peek() + minHeap.peek()) / 2.0;
```

---

## Mistake 5: Sliding Window Median Mein Lazy Deletion Bhoolna

Window se element remove karna hai, but heap arbitrary deletion support nahi karta.

Isliye:

```text
Invalid map + lazy deletion
```

Use karo.

---

## Mistake 6: Order Violation Handle Na Karna

Agar:

```text
maxHeap.top > minHeap.top
```

To heaps swap/balance karo.

---

# 14. Two Heaps Decision Flow

```text
Problem padho
      |
      v
Kya median chahiye?
      |
      +-- No --> Two Heaps probably nahi
      |
      +-- Yes
          |
          v
Kya data stream / dynamic insertion hai?
      |
      +-- Yes --> Two Heaps
      |
      +-- Kya sliding window median chahiye?
              |
              +-- Yes --> Two Heaps + Lazy Deletion
              |
              +-- Kya project selection with capital chahiye?
                      |
                      +-- Yes --> Min-Heap + Max-Heap
```

---

# 15. Important Problems

## Easy

- Find Median from Data Stream — basic version
- Last Stone Weight

## Medium

- Sliding Window Median
- IPO
- Minimum Cost to Connect Sticks
- Maximum Performance of a Team

## Hard

- Find Median from Data Stream
- Sliding Window Median
- IPO
- Median of Two Sorted Arrays
- Minimum Cost to Hire K Workers

---

# 16. One-Line Recognition

> **Agar problem running median, streaming median, sliding window median, ya data ko lower/upper half mein balance karne ki baat kar rahi hai — Two Heaps try karo.**

Two Heaps ka main power ye hai ki median ke liye poori array sort karne ki zaroorat nahi hoti. Lower half ka maximum Max-Heap se aur upper half ka minimum Min-Heap se milta hai, isliye insertion \(O(\log n)\) aur median \(O(1)\) rehta hai.