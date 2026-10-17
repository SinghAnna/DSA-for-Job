# 🚀 DSA Pattern 06 — Binary Search on Answer

> **Goal:** Samajhna ki Binary Search on Answer **kyun** use hota hai, problem mein **kaise identify** karein, aur reusable Java template kaise apply karein.

---

## 1. Binary Search on Answer Kya Hai?

Normal Binary Search mein hum **array ke andar** koi value dhundhte hain.

```text
arr =[1][3][5][7][9]
target = 7
```

Binary Search on Answer mein hum **answer ki possible range** par binary search karte hain.

Example:

```text
Koko ko bananas khane hain.
Speed minimum kya honi chahiye?
```

Yahan answer ek speed hai:

```text
Speed can be 1, 2, 3, ..., max(piles)
```

Hum speed array mein search nahi karte.

Hum possible answers ke range par search karte hain:

```text
[1, 2, 3, 4, 5, ..., 11]
```

Aur har candidate answer ke liye check karte hain:

```text
Kya is speed se kaam ho jayega?
```

---

## 2. Binary Search on Answer Kyun Use Karte Hain?

Main reason: **direct answer calculate karna mushkil ho, but check karna easy ho.**

Example: Koko Eating Bananas.

```text
piles =[3][6][7][11]
h = 8
```

Koko ko minimum eating speed chahiye jisse wo `h` hours mein saare bananas kha le.

Direct formula se minimum speed nikalna mushkil hai.

But ye check karna easy hai:

```text
Kya speed = 4 se kaam ho jayega?
```

Agar haan, to answer `4` ya usse kam ho sakta hai.

Agar nahi, to answer `4` se bada hoga.

### Monotonic Condition

```text
Speed 1 -> maybe false
Speed 2 -> maybe false
Speed 3 -> maybe false
Speed 4 -> true
Speed 5 -> true
Speed 6 -> true
```

Pattern:

```text
false false false true true true
```

Jaise hi condition true hoti hai, wahi minimum answer hai.

Isliye binary search possible hai.

### Core Intuition

> Agar koi answer `x` valid hai, aur bade answers bhi valid hain, to minimum valid answer dhundhne ke liye binary search use karo.

---

## 3. Kaise Pata Chalega Ki Binary Search on Answer Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Pattern |
|---|---|
| **Minimum maximum** | Binary Search on Answer |
| **Maximum minimum** | Binary Search on Answer |
| **Minimum speed / capacity / time** | Binary Search on Answer |
| **Maximum pages / load / distance** | Binary Search on Answer |
| **Split array into K parts** | Binary Search on Answer |
| **Feasible / not feasible condition** | Binary Search on Answer |
| **Answer range monotonic hai** | Binary Search on Answer |
| **Direct formula difficult hai** | Binary Search on Answer |

### Common Keywords

- Minimum number of days
- Minimum eating speed
- Minimum capacity
- Maximum minimum distance
- Minimum maximum load
- Split array
- Ship packages within D days
- Make m bouquets

### Mental Checklist

1. Kya problem **minimum** ya **maximum** value maang rahi hai?  
2. Kya answer ek range mein lie karta hai?  
3. Kya koi answer valid hai ya nahi, ye check kar sakte hain?  
4. Kya agar ek answer valid hai, to usse bade/bade answers bhi valid hain?  
5. Kya direct greedy ya formula se answer nikalna difficult hai?  

Agar 3–4 answers **yes** hain, to Binary Search on Answer try karo.

---

# 4. Binary Search on Answer Ka Structure

Har problem mein 3 cheezein hoti hain:

```text
1. Search Space
2. Feasibility Check
3. Binary Search Direction
```

## 1. Search Space

Answer ka minimum aur maximum possible value decide karo.

Example: Koko Eating Bananas

```text
Minimum speed = 1
Maximum speed = max(piles)
```

## 2. Feasibility Check

Ek function banao:

```java
boolean canDo(answer)
```

Ye batata hai ki candidate answer valid hai ya nahi.

Example:

```java
canFinish(speed)
```

## 3. Binary Search Direction

### Minimum Answer Dhundhna Hai

```text
Agar feasible(mid):
    high = mid       // mid answer ho sakta hai
else:
    low = mid + 1    // mid chhota hai, answer bada hoga
```

### Maximum Answer Dhundhna Hai

```text
Agar feasible(mid):
    low = mid        // mid answer ho sakta hai
else:
    high = mid - 1   // mid bada hai, answer chhota hoga
```

---

# 5. Template — Minimum Valid Answer

```java
public int minimizeAnswer(int[] arr, int conditionValue) {
    int low = minimumPossibleAnswer;
    int high = maximumPossibleAnswer;

    while (low < high) {
        int mid = low + (high - low) / 2;

        if (feasible(arr, conditionValue, mid)) {
            high = mid;
        } else {
            low = mid + 1;
        }
    }

    return low;
}
```

### Why `high = mid`?

Kyunki `mid` khud answer ho sakta hai.

Isliye hum use discard nahi karte.

### Why `low = mid + 1`?

Kyunki `mid` feasible nahi hai.

To answer `mid` se bada hoga.

---

# 6. Template — Maximum Valid Answer

```java
public int maximizeAnswer(int[] arr, int conditionValue) {
    int low = minimumPossibleAnswer;
    int high = maximumPossibleAnswer;

    while (low < high) {
        int mid = low + (high - low + 1) / 2;

        if (feasible(arr, conditionValue, mid)) {
            low = mid;
        } else {
            high = mid - 1;
        }
    }

    return low;
}
```

### Important

Maximum answer dhundhte waqt mid aise calculate karo:

```java
int mid = low + (high - low + 1) / 2;
```

Warna infinite loop aa sakta hai.

Example:

```text
low = 2, high = 3

mid = (2 + 3) / 2 = 2

if feasible:
    low = mid = 2

No progress -> infinite loop
```

But:

```java
mid = low + (high - low + 1) / 2;
```

```text
mid = 2 + (3 - 2 + 1) / 2
    = 2 + 1
    = 3
```

Ab progress hoga.

---

# 7. Example 1: Koko Eating Bananas

## Problem

Koko ke paas banana piles hain. Wo `h` hours mein saare bananas khana chahti hai.

Har hour wo ek pile choose karke `k` bananas kha sakti hai.

Agar pile mein `k` se kam bananas hain, to wo pile khatam ho jata hai, but wo us hour mein dusri pile nahi kha sakti.

Minimum eating speed `k` nikalo.

```text
piles =[3][6][7][11]
h = 8

Output: 4
```

## Why Binary Search on Answer?

Answer speed hai.

Speed range:

```text
1 to max(piles)
```

Agar speed `4` se kaam ho jata hai, to speed `5, 6, 7...` se bhi hoga.

Condition monotonic hai:

```text
false false true true true
```

## Feasibility Check

Speed `k` ke liye total hours:

```text
For each pile:
    hours += ceil(pile / k)
```

Example:

```text
pile = 7
speed = 4

ceil(7 / 4) = 2 hours
```

## Solution

```java
public int minEatingSpeed(int[] piles, int h) {
    int low = 1;
    int high = 0;

    for (int pile : piles) {
        high = Math.max(high, pile);
    }

    while (low < high) {
        int mid = low + (high - low) / 2;

        if (canFinish(piles, h, mid)) {
            high = mid;
        } else {
            low = mid + 1;
        }
    }

    return low;
}

private boolean canFinish(int[] piles, int h, int speed) {
    long hours = 0;

    for (int pile : piles) {
        hours += (pile + speed - 1L) / speed;
    }

    return hours <= h;
}
```

## Why This Formula?

```java
(pile + speed - 1) / speed
```

Ye ceiling division hai:

```text
ceil(a / b)
```

Example:

```text
pile = 7
speed = 4

(7 + 4 - 1) / 4
= 10 / 4
= 2
```

Correct:

```text
ceil(7 / 4) = 2
```

## Dry Run

```text
piles =[3][6][7][11]
h = 8

low = 1
high = 11

Iteration 1:
mid = 6

hours:
ceil(3 / 6) = 1
ceil(6 / 6) = 1
ceil(7 / 6) = 2
ceil(11 / 6) = 2

total = 6

6 <= 8 -> feasible
high = 6

Iteration 2:
low = 1, high = 6
mid = 3

hours:
ceil(3 / 3) = 1
ceil(6 / 3) = 2
ceil(7 / 3) = 3
ceil(11 / 3) = 4

total = 10

10 > 8 -> not feasible
low = 4

Iteration 3:
low = 4, high = 6
mid = 5

hours:
ceil(3 / 5) = 1
ceil(6 / 5) = 2
ceil(7 / 5) = 2
ceil(11 / 5) = 3

total = 8

8 <= 8 -> feasible
high = 5

Iteration 4:
low = 4, high = 5
mid = 4

hours:
ceil(3 / 4) = 1
ceil(6 / 4) = 2
ceil(7 / 4) = 2
ceil(11 / 4) = 3

total = 8

8 <= 8 -> feasible
high = 4

low == high
Answer = 4
```

## Complexity

- Feasibility check: \(O(n)\)
- Binary search iterations: \(O(\log \max(piles))\)
- Total time: \(O(n \log \max(piles))\)
- Space: \(O(1)\)

---

# 8. Example 2: Capacity to Ship Packages Within D Days

## Problem

Packages ke weights diye hain. Ship ko `D` days mein saare packages ship karne hain.

Ship ek din mein consecutive packages le sakta hai.

Minimum ship capacity nikalo.

```text
weights =[1][2][3][4][5][6][7][8][9][10]
days = 5

Output: 15
```

## Why Binary Search on Answer?

Answer ship capacity hai.

Capacity range:

```text
Minimum capacity = max(weights)
Maximum capacity = sum(weights)
```

Kyunki:

- Capacity `max(weights)` se kam ho to bada package kabhi ship nahi hoga.
- Capacity `sum(weights)` ho to ek din mein sab ship ho jayega.

## Feasibility Check

Capacity `cap` ke saath kitne days lagenge?

```text
Greedy:
Ek din mein jitne packages fit ho, le lo.
Jaise hi capacity exceed ho, naya day start karo.
```

## Solution

```java
public int shipWithinDays(int[] weights, int days) {
    int low = 0;
    int high = 0;

    for (int weight : weights) {
        low = Math.max(low, weight);
        high += weight;
    }

    while (low < high) {
        int mid = low + (high - low) / 2;

        if (canShip(weights, days, mid)) {
            high = mid;
        } else {
            low = mid + 1;
        }
    }

    return low;
}

private boolean canShip(int[] weights, int days, int capacity) {
    int requiredDays = 1;
    int currentWeight = 0;

    for (int weight : weights) {
        if (currentWeight + weight > capacity) {
            requiredDays++;
            currentWeight = 0;
        }

        currentWeight += weight;
    }

    return requiredDays <= days;
}
```

## Dry Run

```text
weights =[1][2][3][4][5][6][7][8][9][10]
days = 5

low = 10
high = 55

mid = 32

Greedy packing:
Day 1: 1 + 2 + 3 + 4 + 5 + 6 + 7 = 28
Next 8 would make 36 > 32

Day 2: 8 + 9 = 17
Next 10 would make 27 <= 32
Day 2: 8 + 9 + 10 = 27

Total days = 2

2 <= 5 -> feasible
high = 32
```

Binary search continues until minimum feasible capacity `15` mil jata hai.

## Complexity

- Feasibility check: \(O(n)\)
- Binary search: \(O(\log(sum(weights)))\)
- Total time: \(O(n \log(sum(weights)))\)
- Space: \(O(1)\)

---

# 9. Example 3: Split Array Largest Sum

## Problem

Array ko `k` non-empty continuous subarrays mein divide karo.

Har subarray ka sum hoga.

Aisa division karo ki **largest subarray sum minimum** ho.

```text
nums =[2][5][7][8][10]
k = 2

Output: 18
Explanation:
 and[2][5][7][8][10]
largest sum = 18
```

## Why Binary Search on Answer?

Problem keh rahi hai:

```text
Minimum maximum sum
```

Ye classic Binary Search on Answer signal hai.

Answer range:

```text
low = max(nums)
high = sum(nums)
```

Kyunki:

- Har subarray mein kam se kam ek element hoga.
- Maximum element se chhoti capacity impossible hai.
- Poora array ek subarray ban sakta hai, to maximum possible answer sum(nums).

## Feasibility Check

```text
Kya given maximum allowed sum ke saath array ko k ya usse kam parts mein split kar sakte hain?
```

## Solution

```java
public int splitArray(int[] nums, int k) {
    int low = 0;
    int high = 0;

    for (int num : nums) {
        low = Math.max(low, num);
        high += num;
    }

    while (low < high) {
        int mid = low + (high - low) / 2;

        if (canSplit(nums, k, mid)) {
            high = mid;
        } else {
            low = mid + 1;
        }
    }

    return low;
}

private boolean canSplit(int[] nums, int k, int maxAllowedSum) {
    int parts = 1;
    int currentSum = 0;

    for (int num : nums) {
        if (currentSum + num > maxAllowedSum) {
            parts++;
            currentSum = 0;
        }

        currentSum += num;
    }

    return parts <= k;
}
```

## Dry Run

```text
nums =[2][5][7][8][10]
k = 2

low = 10
high = 32

mid = 21

Greedy split:
currentSum = 7
+2 = 9
+5 = 14
+10 = 24 > 21

New part:
currentSum = 10
+8 = 18

parts = 2

2 <= 2 -> feasible
high = 21
```

Binary search continues and final answer becomes `18`.

## Complexity

- Feasibility check: \(O(n)\)
- Binary search: \(O(\log(sum(nums)))\)
- Total time: \(O(n \log(sum(nums)))\)
- Space: \(O(1)\)

---

# 10. Example 4: Minimum Number of Days to Make m Bouquets

## Problem

Flowers ke bloom days diye hain.

`m` bouquets banane hain, har bouquet mein `k` **adjacent** flowers chahiye.

Minimum number of days nikalo.

```text
bloomDay =[1][2][3][10]
m = 3
k = 1

Output: 3
```

## Why Binary Search on Answer?

Answer number of days hai.

Agar `day = x` par bouquets ban sakte hain, to `x + 1`, `x + 2` par bhi ban sakte hain.

Condition monotonic hai:

```text
false false true true true
```

## Feasibility Check

Given day `d` par:

- Jis flower ka bloom day `<= d` hai, wo bloom ho chuka hai.
- Consecutive bloomed flowers count karo.
- Har `k` consecutive bloomed flowers se ek bouquet banta hai.

## Solution

```java
public int minDays(int[] bloomDay, int m, int k) {
    long requiredFlowers = (long) m * k;

    if (requiredFlowers > bloomDay.length) {
        return -1;
    }

    int low = Integer.MAX_VALUE;
    int high = Integer.MIN_VALUE;

    for (int day : bloomDay) {
        low = Math.min(low, day);
        high = Math.max(high, day);
    }

    while (low < high) {
        int mid = low + (high - low) / 2;

        if (canMake(bloomDay, m, k, mid)) {
            high = mid;
        } else {
            low = mid + 1;
        }
    }

    return low;
}

private boolean canMake(int[] bloomDay, int m, int k, int day) {
    int bouquets = 0;
    int consecutive = 0;

    for (int bloom : bloomDay) {
        if (bloom <= day) {
            consecutive++;

            if (consecutive == k) {
                bouquets++;
                consecutive = 0;
            }
        } else {
            consecutive = 0;
        }
    }

    return bouquets >= m;
}
```

## Complexity

- Feasibility check: \(O(n)\)
- Binary search: \(O(\log \max(bloomDay))\)
- Total time: \(O(n \log \max(bloomDay))\)
- Space: \(O(1)\)

---

# 11. Example 5: Aggressive Cows

## Problem

`n` stalls hain aur `c` cows hain.

Cows ko aise place karo ki minimum distance between any two cows **maximum** ho.

```text
stalls =[1][2][4][8][9]
cows = 3

Output: 3
Explanation:
Place cows at 1, 4, 8
Minimum distance = 3
```

## Why Binary Search on Answer?

Problem keh rahi hai:

```text
Maximum minimum distance
```

Answer distance hai.

Agar minimum distance `d` possible hai, to `d - 1` bhi possible hoga.

Condition monotonic:

```text
true true true false false
```

## Feasibility Check

Given minimum distance `d`:

- Pehli cow ko sabse pehle stall par rakho.
- Next cow ko tabhi rakho jab previous cow se distance `>= d` ho.
- Agar `c` cows place ho gayi, to feasible.

## Solution

```java
public int aggressiveCows(int[] stalls, int cows) {
    Arrays.sort(stalls);

    int low = 1;
    int high = stalls[stalls.length - 1] - stalls;

    while (low < high) {
        int mid = low + (high - low + 1) / 2;

        if (canPlace(stalls, cows, mid)) {
            low = mid;
        } else {
            high = mid - 1;
        }
    }

    return low;
}

private boolean canPlace(int[] stalls, int cows, int minDistance) {
    int count = 1;
    int lastPosition = stalls;

    for (int i = 1; i < stalls.length; i++) {
        if (stalls[i] - lastPosition >= minDistance) {
            count++;
            lastPosition = stalls[i];
        }
    }

    return count >= cows;
}
```

## Why `low = mid`?

Kyunki hum **maximum** valid distance dhundh rahe hain.

Agar `mid` feasible hai, to answer `mid` ho sakta hai.

## Complexity

- Sorting: \(O(n \log n)\)
- Feasibility check: \(O(n)\)
- Binary search: \(O(\log \maxDistance)\)
- Total time: \(O(n \log n + n \log \maxDistance)\)
- Space: \(O(1)\)

---

# 12. Minimum vs Maximum Answer

| Goal | Feasible Case | Movement | Mid Calculation |
|---|---|---|---|
| Minimum valid answer | `feasible(mid) == true` | `high = mid` | `low + (high - low) / 2` |
| Minimum valid answer | `feasible(mid) == false` | `low = mid + 1` | `low + (high - low) / 2` |
| Maximum valid answer | `feasible(mid) == true` | `low = mid` | `low + (high - low + 1) / 2` |
| Maximum valid answer | `feasible(mid) == false` | `high = mid - 1` | `low + (high - low + 1) / 2` |

### Easy Trick

```text
Minimum answer chahiye:
feasible -> answer ho sakta hai -> left side search

Maximum answer chahiye:
feasible -> answer ho sakta hai -> right side search
```

---

# 13. Common Mistakes

## Mistake 1: Wrong Search Space

Koko Eating Bananas mein:

```text
low = 1
high = max(piles)
```

Galat:

```text
high = sum(piles)
```

Ye valid hai, but unnecessarily bada search space hoga.

---

## Mistake 2: Feasibility Check Galat Banana

Binary Search on Answer mein sabse important part `feasible()` function hai.

Pehle clearly likho:

```text
Given answer x, kya condition satisfy hoti hai?
```

---

## Mistake 3: `high = mid - 1` Karna Minimum Answer Case Mein

Minimum answer dhundhte waqt:

```java
if (feasible(mid)) {
    high = mid;
}
```

`high = mid - 1` mat karo, kyunki `mid` khud answer ho sakta hai.

---

## Mistake 4: Maximum Answer Case Mein Infinite Loop

Maximum answer ke liye:

```java
int mid = low + (high - low + 1) / 2;
```

Use karo.

Simple mid se infinite loop aa sakta hai.

---

## Mistake 5: Integer Overflow

Sum ya product bade ho sakte hain.

```java
long hours = 0;
long total = 0;
```

Use karo where needed.

---

# 14. Binary Search on Answer Decision Flow

```text
Problem padho
      |
      v
Kya minimum / maximum answer chahiye?
      |
      +-- No --> Normal Binary Search try karo
      |
      +-- Yes
          |
          v
Kya answer ka range decide kar sakte ho?
      |
      +-- No --> Binary Search on Answer difficult
      |
      +-- Yes
          |
          v
Kya feasibility check monotonic hai?
      |
      +-- No --> Binary Search on Answer fail
      |
      +-- Yes
          |
          v
Minimum answer chahiye?
      |
      +-- Yes --> high = mid on feasible
      |
      +-- No --> Maximum answer
              |
              +-- low = mid on feasible
```

---

# 15. Important Problems

## Easy

- Koko Eating Bananas
- Sqrt(x)
- First Bad Version

## Medium

- Capacity to Ship Packages Within D Days
- Minimum Number of Days to Make m Bouquets
- Split Array Largest Sum
- Find K-th Smallest Pair Distance
- Magnetic Force Between Two Balls
- Minimum Limit of Balls in a Bag

## Hard

- Median of Two Sorted Arrays
- Kth Smallest Element in a Sorted Matrix
- Divide Chocolate
- Minimum Time to Complete Trips

---

# 16. One-Line Recognition

> **Agar problem minimum possible maximum, maximum possible minimum, minimum speed/capacity/time, ya feasibility-based answer maang rahi hai — Binary Search on Answer try karo.**

Binary Search on Answer ka main power ye hai ki direct answer calculate karne ki jagah hum answer ki range par binary search karte hain, aur har candidate answer ko feasibility check se validate karte hain.