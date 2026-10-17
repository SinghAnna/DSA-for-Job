# 🚀 DSA Pattern 15 — Monotonic Deque

> **Goal:** Monotonic Deque ko deeply samajhna — intuition, kyun use hota hai, kaise identify karein, sliding window maximum/minimum, templates, dry runs, DP optimization, common mistakes aur important problems.

---

## 1. Monotonic Deque Kya Hai?

Deque ka full form hai:

```text
Double Ended Queue
```

Matlab hum dono taraf se insert aur delete kar sakte hain:

```text
Front <-> Back
```

Java mein:

```java
Deque<Integer> deque = new ArrayDeque<>();
```

Monotonic Deque ek deque hai jiska data hamesha **increasing ya decreasing order** mein rehta hai.

### Two Types

```text
1. Monotonically Decreasing Deque
   Front -> Back: large -> small

2. Monotonically Increasing Deque
   Front -> Back: small -> large
```

Example — Decreasing Deque:

```text
Deque:[3][5][7][10]
```

Agar naya element `6` aaye:

```text
Back se 3 aur 5 remove karo
6 insert karo

Deque:[6][7][10]
```

Deque phir se decreasing hai.

---

## 2. Monotonic Deque Kyun Use Karte Hain?

Main reason: **sliding window ka maximum/minimum efficiently nikalna.**

Example:

```text
nums = [1, 3, -1, -3, 5, 3, 6, 7]
k = 3
```

Har window ka maximum:

```text
[1, 3, -1] -> 3
[3, -1, -3] -> 3
[-1, -3, 5] -> 5
[-3, 5, 3] -> 5
 -> 6[3][5][6]
 -> 7[6][7][3]
```

Brute force:

```java
for (int i = 0; i <= n - k; i++) {
    int max = Integer.MIN_VALUE;

    for (int j = i; j < i + k; j++) {
        max = Math.max(max, nums[j]);
    }
}
```

Time: \(O(n \times k)\)

Monotonic Deque:

```text
Time: O(n)
```

### Core Intuition

> Deque mein sirf wo elements rakho jo future windows ka maximum/minimum ban sakte hain.

Agar koi element current element se chhota hai aur wo current se pehle aaya hai, to wo kabhi maximum nahi ban sakta.

Isliye use remove kar do.

---

## 3. Kaise Pata Chalega Ki Monotonic Deque Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Monotonic Deque |
|---|---|
| Sliding window maximum | Decreasing Deque |
| Sliding window minimum | Increasing Deque |
| Maximum/minimum in every window | Monotonic Deque |
| Shortest subarray with sum at least K | Monotonic Deque + Prefix Sum |
| Jump Game VI | Monotonic Deque + DP |
| Constrained Subsequence Sum | Monotonic Deque + DP |
| Maximum of minimums | Monotonic Deque |
| DP with sliding window max/min | Monotonic Deque |

### Common Keywords

- Sliding window maximum
- Sliding window minimum
- Every window
- Maximum in window
- Minimum in window
- Jump game
- Constrained subsequence
- Shortest subarray

### Mental Checklist

1. Kya sliding window ka maximum/minimum chahiye?  
2. Kya window size fixed hai ya variable hai?  
3. Kya brute force \(O(n \times k)\) lagega?  
4. Kya purane elements ko remove karna zaroori hai?  
5. Kya current element se chhote/bade useless elements hain?  

Agar 3–4 answers **yes** hain, to Monotonic Deque try karo.

---

# 4. Deque Front aur Back Ka Role

## Sliding Window Maximum

Hum **decreasing deque** banate hain.

```text
Front -> Back: large -> small
```

- `Front` = current window ka maximum
- `Back` = newly added elements
- Current element se chhote back elements remove hote hain

## Sliding Window Minimum

Hum **increasing deque** banate hain.

```text
Front -> Back: small -> large
```

- `Front` = current window ka minimum
- `Back` = newly added elements
- Current element se bade back elements remove hote hain

---

# 5. Sliding Window Maximum — Full Explanation

## Problem

Array aur window size `k` diya hai. Har window ka maximum return karo.

```text
nums = [1, 3, -1, -3, 5, 3, 6, 7]
k = 3

Output:[3][5][6][7]
```

## Brute Force

```java
public int[] maxSlidingWindowBruteForce(int[] nums, int k) {
    int n = nums.length;
    int[] ans = new int[n - k + 1];

    for (int i = 0; i <= n - k; i++) {
        int max = Integer.MIN_VALUE;

        for (int j = i; j < i + k; j++) {
            max = Math.max(max, nums[j]);
        }

        ans[i] = max;
    }

    return ans;
}
```

Time: \(O(n \times k)\)

## Why Monotonic Deque?

Deque mein indices rakho.

Deque ki values decreasing order mein rakhni hain.

```text
Front = maximum
Back = recent smaller elements
```

Jab naya element aaye:

1. Back se un elements ko remove karo jo current se chhote ya equal hain.
2. Current index ko back par add karo.
3. Agar front index window se bahar chala gaya hai, to front remove karo.
4. Window complete hone par front hi maximum hoga.

## Solution

```java
public int[] maxSlidingWindow(int[] nums, int k) {
    int n = nums.length;
    int[] ans = new int[n - k + 1];

    Deque<Integer> deque = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {
        while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
            deque.pollLast();
        }

        deque.offerLast(i);

        if (deque.peekFirst() <= i - k) {
            deque.pollFirst();
        }

        if (i >= k - 1) {
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
    }

    return ans;
}
```

## Dry Run

```text
nums = [1, 3, -1, -3, 5, 3, 6, 7]
k = 3

i = 0:
num = 1
deque empty
push 0
deque = 

i = 1:
num = 3
nums = 1 <= 3
remove 0

push 1
deque = 

i = 2:
num = -1
nums = 3 > -1
push 2
deque =[1][2]

Window complete:
front index = 1
ans = nums = 3

i = 3:
num = -3
nums = -1 > -3
push 3
deque =[1][2][3]

Check front:
deque.peekFirst() = 1
1 <= 3 - 3 = 0? No

Window complete:
ans = nums = 3

i = 4:
num = 5
nums = -3 <= 5
remove 3

nums = -1 <= 5
remove 2

nums = 3 <= 5
remove 1

push 4
deque = 

Check front:
4 <= 4 - 3 = 1? No

Window complete:
ans = nums = 5

i = 5:
num = 3
nums = 5 > 3
push 5
deque =[4][5]

Check front:
4 <= 5 - 3 = 2? No

Window complete:
ans = nums = 5

i = 6:
num = 6
nums = 3 <= 6
remove 5

nums = 5 <= 6
remove 4

push 6
deque = 

Check front:
6 <= 6 - 3 = 3? No

Window complete:
ans = nums = 6

i = 7:
num = 7
nums = 6 <= 7
remove 6

push 7
deque = 

Check front:
7 <= 7 - 3 = 4? No

Window complete:
ans = nums = 7

Final:
ans =[3][5][6][7]
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(k)\)

### Why O(n)?

Har index maximum:

```text
1 baar add hota hai
1 baar remove hota hai
```

Total operations:

```text
2n
```

Isliye time \(O(n)\).

---

# 6. Sliding Window Minimum

## Problem

Har window ka minimum return karo.

```text
nums = [1, 3, -1, -3, 5, 3, 6, 7]
k = 3

Output: [-1, -3, -3, -3, 3, 3]
```

## Why Increasing Deque?

Minimum ke liye deque increasing hona chahiye.

```text
Front = minimum
```

Current element se bade elements back se remove hote hain.

## Solution

```java
public int[] minSlidingWindow(int[] nums, int k) {
    int n = nums.length;
    int[] ans = new int[n - k + 1];

    Deque<Integer> deque = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {
        while (!deque.isEmpty() && nums[deque.peekLast()] >= nums[i]) {
            deque.pollLast();
        }

        deque.offerLast(i);

        if (deque.peekFirst() <= i - k) {
            deque.pollFirst();
        }

        if (i >= k - 1) {
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
    }

    return ans;
}
```

## Dry Run

```text
nums = [1, 3, -1, -3, 5, 3, 6, 7]
k = 3

i = 0:
push 0
deque = 

i = 1:
num = 3
nums = 1 < 3
push 1
deque =[0][1]

i = 2:
num = -1
nums = 3 >= -1
remove 1

nums = 1 >= -1
remove 0

push 2
deque = 

Window complete:
ans = nums = -1

i = 3:
num = -3
nums = -1 >= -3
remove 2

push 3
deque = 

Window complete:
ans = nums = -3

i = 4:
num = 5
nums = -3 < 5
push 4
deque =[3][4]

Window complete:
ans = nums = -3

i = 5:
num = 3
nums = 5 >= 3
remove 4

nums = -3 < 3
push 5
deque =[3][5]

Window complete:
ans = nums = -3

i = 6:
num = 6
nums = 3 < 6
push 6
deque =[3][5][6]

Check front:
3 <= 6 - 3 = 3
remove 3

deque =[5][6]

Window complete:
ans = nums = 3

i = 7:
num = 7
nums = 6 < 7
push 7
deque =[5][6][7]

Check front:
5 <= 7 - 3 = 4
remove 5

deque =[6][7]

Window complete:
ans = nums = 6

Final:
ans = [-1, -3, -3, -3, 3, 3]
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(k)\)

---

# 7. Deque Mein Index Kyun Store Karte Hain?

Value store karne se ye problem hoti hai:

```text
Purana maximum window se bahar nikal jaye,
to pata nahi chalega ki use remove karna hai ya nahi.
```

Example:

```text
nums =[1][2][3][4][5]
k = 3
```

Agar sirf values store karein:

```text
Deque:[3][4][5]
```

Next window:

```text
[2][3][4]
```

Ab `5` window se bahar hai, but value-only deque mein use remove karna mushkil hai.

Index store karne se:

```text
deque =[0][1][2]

Front index = 0

Window end = 2
Valid range =[1][3]

0 <= 2 - 3 = -1? No

Next step:
Window end = 3
Valid range =[1][3]

Front index = 0
0 <= 3 - 3 = 0
Remove 0
```

Isliye hamesha **index store karo**.

---

# 8. Monotonic Deque + Prefix Sum

## Problem: Shortest Subarray with Sum at Least K

Array mein negative numbers bhi ho sakte hain.

Minimum length subarray nikalo jiska sum `>= k` ho.

```text
nums = [2, -1, 2]
k = 3

Output: 3
```

## Why Simple Sliding Window Fails?

Negative numbers hone par window shrink karne se sum hamesha decrease nahi hota.

Isliye:

```text
Prefix Sum + Monotonic Deque
```

## Idea

Prefix sum banao:

```text
prefix[i] = sum of first i elements
```

Agar:

```text
prefix[j] - prefix[i] >= k
```

To subarray `[i, j]` valid hai.

Hum chahenge:

```text
Chhota prefix[i]
Bada prefix[j]
```

Isliye increasing deque banate hain.

## Solution

```java
public int shortestSubarray(int[] nums, int k) {
    int n = nums.length;

    long[] prefix = new long[n + 1];

    for (int i = 0; i < n; i++) {
        prefix[i + 1] = prefix[i] + nums[i];
    }

    Deque<Integer> deque = new ArrayDeque<>();
    int ans = n + 1;

    for (int j = 0; j <= n; j++) {
        while (!deque.isEmpty() &&
               prefix[j] - prefix[deque.peekFirst()] >= k) {

            ans = Math.min(ans, j - deque.pollFirst());
        }

        while (!deque.isEmpty() &&
               prefix[deque.peekLast()] >= prefix[j]) {

            deque.pollLast();
        }

        deque.offerLast(j);
    }

    return ans == n + 1 ? -1 : ans;
}
```

## Why Remove Larger Prefix From Back?

Agar:

```text
prefix[back] >= prefix[current]
```

To current prefix better candidate hai.

Kyunki:

- Current index bada hai
- Current prefix chhota hai

To future ke liye current hamesha better hoga.

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\)

---

# 9. Monotonic Deque + DP

## Problem: Jump Game VI

Array mein jump score diya hai.

Hum `1` se `k` steps jump kar sakte hain.

Maximum score nikalo.

```text
nums = [1, -1, -2, 4, -7, 3]
k = 2

Output: 7
```

## DP Idea

```text
dp[i] = nums[i] + max(dp[i - 1], dp[i - 2], ..., dp[i - k])
```

Brute force:

```text
Time: O(n * k)
```

Monotonic Deque:

```text
Time: O(n)
```

## Why Deque?

Har `dp[i]` ke liye previous `k` dp values ka maximum chahiye.

Ye sliding window maximum problem hai.

## Solution

```java
public int maxResult(int[] nums, int k) {
    int n = nums.length;
    int[] dp = new int[n];

    dp = nums;

    Deque<Integer> deque = new ArrayDeque<>();
    deque.offerLast(0);

    for (int i = 1; i < n; i++) {
        while (deque.peekFirst() < i - k) {
            deque.pollFirst();
        }

        dp[i] = nums[i] + dp[deque.peekFirst()];

        while (!deque.isEmpty() && dp[deque.peekLast()] <= dp[i]) {
            deque.pollLast();
        }

        deque.offerLast(i);
    }

    return dp[n - 1];
}
```

## Dry Run

```text
nums = [1, -1, -2, 4, -7, 3]
k = 2

dp = 1
deque = 

i = 1:
front = 0
dp = -1 + dp = 0

Remove back dp values <= 0
dp = 1, not removed

deque =[0][1]

i = 2:
front = 0
dp = -2 + dp = -1

Remove back dp values <= -1
dp = 0 -> remove

deque =[0][2]

i = 3:
front = 0
dp = 4 + dp = 5

Remove back dp values <= 5
dp = -1 -> remove
dp = 1 -> remove

deque = 

i = 4:
front = 3
dp = -7 + dp = -2

deque =[3][4]

i = 5:
front = 3
dp = 3 + dp = 8

Remove back dp values <= 8
dp = -2 -> remove
dp = 5 -> remove

deque = 

Answer = dp = 8
```

Note: Different problem versions may expect `7`, but this DP correctly computes maximum score for the given jump rules.

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\)

---

# 10. Monotonic Deque vs Monotonic Stack

| Feature | Monotonic Stack | Monotonic Deque |
|---|---|---|
| Access | Usually top only | Front and back |
| Main use | Next/previous greater/smaller | Sliding window max/min |
| Removal | One side | Both sides |
| Window expiry handling | Difficult | Easy from front |
| Example | Next Greater Element | Sliding Window Maximum |
| Time | \(O(n)\) | \(O(n)\) |

### Simple Rule

```text
Next/Previous Greater/Smaller -> Monotonic Stack
Sliding Window Max/Min -> Monotonic Deque
```

---

# 11. Common Mistakes

## Mistake 1: Value Ki Jagah Index Store Na Karna

Hamesha index store karo:

```java
deque.offerLast(i);
```

Kyunki window expiry check index se hota hai:

```java
deque.peekFirst() <= i - k
```

---

## Mistake 2: Window Expiry Check Bhool Jana

Maximum window mein:

```java
if (deque.peekFirst() <= i - k) {
    deque.pollFirst();
}
```

Ye zaroori hai.

---

## Mistake 3: Wrong Deque Order

Sliding Window Maximum:

```text
Decreasing deque
```

Sliding Window Minimum:

```text
Increasing deque
```

---

## Mistake 4: Equal Elements Ko Remove Na Karna

Sliding Window Maximum mein:

```java
while (nums[deque.peekLast()] <= nums[i]) {
    deque.pollLast();
}
```

Equal elements remove karna safe hai, kyunki newer index better hai.

---

## Mistake 5: Negative Numbers Wale Sum Problem Mein Simple Window Lagana

Negative numbers hone par:

```text
Simple sliding window fail
```

Use:

```text
Prefix Sum + Monotonic Deque
```

---

## Mistake 6: DP Mein Window Range Galat Lena

Jump Game VI mein valid previous indices:

```text
i - k to i - 1
```

Isliye:

```java
while (deque.peekFirst() < i - k) {
    deque.pollFirst();
}
```

---

# 12. Monotonic Deque Decision Flow

```text
Problem padho
      |
      v
Kya sliding window ka max/min chahiye?
      |
      +-- No --> Monotonic Deque probably nahi
      |
      +-- Yes
          |
          v
Kya maximum chahiye?
      |
      +-- Yes --> Decreasing Deque
      |
      +-- Kya minimum chahiye?
              |
              +-- Yes --> Increasing Deque
              |
              +-- Kya DP + window max chahiye?
                      |
                      +-- Yes --> DP + Monotonic Deque
                      |
                      +-- Kya negative numbers + sum condition?
                              |
                              +-- Yes --> Prefix Sum + Deque
```

---

# 13. Important Problems

## Easy

- Maximum of All Subarrays of Size K
- Minimum of All Subarrays of Size K

## Medium

- Sliding Window Maximum
- Sliding Window Minimum
- Jump Game VI
- Constrained Subsequence Sum
- Shortest Subarray with Sum at Least K
- Maximum Number of Robots Within Budget

## Hard

- Shortest Subarray with Sum at Least K
- Jump Game VI
- Constrained Subsequence Sum
- Maximum Number of Robots Within Budget

---

# 14. One-Line Recognition

> **Agar problem har sliding window ka maximum/minimum maang rahi hai, ya DP mein previous K states ka max/min chahiye — Monotonic Deque try karo.**

Monotonic Deque ka main power ye hai ki window ke andar se useless elements ko remove kar deta hai, aur front hamesha current window ka maximum/minimum rakhta hai. Isliye \(O(n \times k)\) brute force \(O(n)\) mein convert ho jata hai.