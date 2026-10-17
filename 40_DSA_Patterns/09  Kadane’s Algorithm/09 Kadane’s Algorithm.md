# 🚀 DSA Pattern 09 — Kadane’s Algorithm

> **Goal:** Samajhna ki Kadane’s Algorithm **kyun** use hota hai, problem mein **kaise identify** karein, aur reusable Java template kaise apply karein.

---

## 1. Kadane’s Algorithm Kya Hai?

Kadane’s Algorithm ek **Dynamic Programming** technique hai jo **maximum sum contiguous subarray** nikalne ke liye use hoti hai.

Example:

```text
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]

Maximum subarray = [4, -1, 2, 1]
Maximum sum = 6
```

Kadane’s algorithm har index par ye decide karta hai:

```text
Kya current element ko:
1. Previous subarray mein extend karein?
2. Ya naya subarray start karein?
```

Formula:

```text
currentSum = max(nums[i], currentSum + nums[i])
```

Matlab:

- Agar `currentSum + nums[i]` bada hai -> previous subarray extend karo
- Agar `nums[i]` bada hai -> naya subarray start karo

---

## 2. Kadane’s Algorithm Kyun Use Karte Hain?

Main reason: **contiguous subarray ka maximum/minimum sum efficiently nikalna.**

Brute force:

```java
for (int start = 0; start < n; start++) {
    int sum = 0;

    for (int end = start; end < n; end++) {
        sum += nums[end];
        maxSum = Math.max(maxSum, sum);
    }
}
```

Time: \(O(n^2)\)

Kadane’s Algorithm:

```text
Time: O(n)
Space: O(1)
```

### Core Intuition

> Agar previous subarray ka sum negative hai, to wo current element ke saath judkar answer ko kharab karega. Isliye naya subarray start karo.

Example:

```text
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
```

Index `3` par:

```text
currentSum before = -4
nums = 4

currentSum + nums = 0
nums = 4

max(4, 0) = 4
```

To yahan naya subarray start karna better hai.

---

## 3. Kaise Pata Chalega Ki Kadane’s Algorithm Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Kadane’s Algorithm |
|---|---|
| Maximum sum contiguous subarray | Kadane’s Algorithm |
| Minimum sum contiguous subarray | Kadane’s Algorithm variant |
| Maximum product subarray | Modified Kadane’s |
| Maximum sum circular subarray | Kadane’s + total sum |
| Best time to buy and sell stock | Kadane’s variant |
| Maximum difference / profit | Kadane’s variant |
| Longest positive-sum subarray | Kadane’s variant |

### Common Keywords

- Maximum subarray
- Maximum sum
- Contiguous subarray
- Maximum product subarray
- Circular subarray
- Maximum profit
- Maximum difference

### Mental Checklist

1. Kya problem **contiguous subarray** ke baare mein hai?  
2. Kya maximum/minimum sum ya product chahiye?  
3. Kya har index par “extend or restart” decision le sakte hain?  
4. Kya negative numbers hain?  
5. Kya \(O(n)\) time solution chahiye?  

Agar 3–4 answers **yes** hain, to Kadane’s Algorithm try karo.

---

# 4. Kadane’s Algorithm Ka Core Idea

Har index `i` par do values track karo:

```text
currentSum = current index tak ka best subarray sum
maxSum = ab tak ka global maximum subarray sum
```

Transition:

```text
currentSum = max(nums[i], currentSum + nums[i])
maxSum = max(maxSum, currentSum)
```

### Decision

```text
Agar currentSum + nums[i] > nums[i]:
    previous subarray extend karo

Else:
    naya subarray start karo
```

Simple words:

```text
Negative running sum ko discard karo.
```

---

# 5. Template — Maximum Subarray Sum

```java
public int maxSubArray(int[] nums) {
    int currentSum = nums;
    int maxSum = nums;

    for (int i = 1; i < nums.length; i++) {
        currentSum = Math.max(nums[i], currentSum + nums[i]);
        maxSum = Math.max(maxSum, currentSum);
    }

    return maxSum;
}
```

### Important

`currentSum` aur `maxSum` ko `nums[0]` se initialize karo.

`0` se initialize mat karo, kyunki agar saare numbers negative hain:

```text
nums = [-3, -1, -2]
```

Answer `-1` hona chahiye, not `0`.

---

# 6. Example 1: Maximum Subarray

## Problem

Integer array diya hai. Maximum sum wala contiguous subarray dhundo aur uska sum return karo.

```text
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
Output: 6
Explanation: [4, -1, 2, 1]
```

## Brute Force

```java
public int maxSubArrayBruteForce(int[] nums) {
    int maxSum = Integer.MIN_VALUE;

    for (int start = 0; start < nums.length; start++) {
        int sum = 0;

        for (int end = start; end < nums.length; end++) {
            sum += nums[end];
            maxSum = Math.max(maxSum, sum);
        }
    }

    return maxSum;
}
```

Time: \(O(n^2)\)

## Why Kadane’s Algorithm?

Har index par sirf ek decision lena hai:

```text
Previous sum extend karna hai ya naya start karna hai?
```

## Kadane’s Solution

```java
public int maxSubArray(int[] nums) {
    int currentSum = nums;
    int maxSum = nums;

    for (int i = 1; i < nums.length; i++) {
        currentSum = Math.max(nums[i], currentSum + nums[i]);
        maxSum = Math.max(maxSum, currentSum);
    }

    return maxSum;
}
```

## Dry Run

```text
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]

i = 0:
currentSum = -2
maxSum = -2

i = 1:
currentSum = max(1, -2 + 1)
           = max(1, -1)
           = 1

maxSum = max(-2, 1) = 1

i = 2:
currentSum = max(-3, 1 + -3)
           = max(-3, -2)
           = -2

maxSum = 1

i = 3:
currentSum = max(4, -2 + 4)
           = max(4, 2)
           = 4

maxSum = 4

i = 4:
currentSum = max(-1, 4 + -1)
           = max(-1, 3)
           = 3

maxSum = 4

i = 5:
currentSum = max(2, 3 + 2)
           = max(2, 5)
           = 5

maxSum = 5

i = 6:
currentSum = max(1, 5 + 1)
           = max(1, 6)
           = 6

maxSum = 6

i = 7:
currentSum = max(-5, 6 + -5)
           = max(-5, 1)
           = 1

maxSum = 6

i = 8:
currentSum = max(4, 1 + 4)
           = max(4, 5)
           = 5

maxSum = 6

Final Answer = 6
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 7. Example 2: Maximum Subarray With Indices

## Problem

Maximum sum subarray ka sum bhi chahiye aur subarray bhi chahiye.

```text
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]

Maximum subarray = [4, -1, 2, 1]
Maximum sum = 6
```

## Why Track Indices?

Jab naya subarray start hota hai:

```text
currentSum = nums[i]
tempStart = i
```

Jab `currentSum > maxSum` hota hai:

```text
maxSum = currentSum
start = tempStart
end = i
```

## Solution

```java
public int[] maxSubArrayWithIndices(int[] nums) {
    int currentSum = nums;
    int maxSum = nums;

    int start = 0;
    int end = 0;
    int tempStart = 0;

    for (int i = 1; i < nums.length; i++) {
        if (nums[i] > currentSum + nums[i]) {
            currentSum = nums[i];
            tempStart = i;
        } else {
            currentSum = currentSum + nums[i];
        }

        if (currentSum > maxSum) {
            maxSum = currentSum;
            start = tempStart;
            end = i;
        }
    }

    return new int[]{maxSum, start, end};
}
```

## Dry Run

```text
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]

At index 3:
currentSum = 4
tempStart = 3

At index 6:
currentSum = 6
maxSum = 6
start = 3
end = 6

Subarray:
nums to nums
= [4, -1, 2, 1]
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 8. Example 3: Maximum Product Subarray

## Problem

Array mein maximum product wala contiguous subarray dhundo.

```text
nums = [2, 3, -2, 4]
Output: 6
Explanation:[2][3]
```

```text
nums = [-2, 0, -1]
Output: 0
```

## Why Normal Kadane’s Fails?

Product mein negative number problem create karta hai.

Example:

```text
[-2, 3, -4]

Maximum product = 24
Subarray = [-2, 3, -4]
```

Kyunki:

```text
-2 * 3 = -6
-6 * -4 = 24
```

Negative product suddenly maximum ban sakta hai.

Isliye humein dono track karna padega:

```text
maxProduct
minProduct
```

## Modified Kadane’s Idea

Har index par:

```text
currentMax = max(nums[i], currentMax * nums[i], currentMin * nums[i])
currentMin = min(nums[i], currentMax * nums[i], currentMin * nums[i])
```

## Solution

```java
public int maxProduct(int[] nums) {
    int currentMax = nums;
    int currentMin = nums;
    int ans = nums;

    for (int i = 1; i < nums.length; i++) {
        int num = nums[i];

        int tempMax = Math.max(
            num,
            Math.max(currentMax * num, currentMin * num)
        );

        int tempMin = Math.min(
            num,
            Math.min(currentMax * num, currentMin * num)
        );

        currentMax = tempMax;
        currentMin = tempMin;

        ans = Math.max(ans, currentMax);
    }

    return ans;
}
```

## Dry Run

```text
nums = [2, 3, -2, 4]

i = 0:
currentMax = 2
currentMin = 2
ans = 2

i = 1:
num = 3

tempMax = max(3, 2 * 3, 2 * 3)
        = max(3, 6, 6)
        = 6

tempMin = min(3, 6, 6)
        = 3

currentMax = 6
currentMin = 3
ans = 6

i = 2:
num = -2

tempMax = max(-2, 6 * -2, 3 * -2)
        = max(-2, -12, -6)
        = -2

tempMin = min(-2, -12, -6)
        = -12

currentMax = -2
currentMin = -12
ans = 6

i = 3:
num = 4

tempMax = max(4, -2 * 4, -12 * 4)
        = max(4, -8, -48)
        = 4

tempMin = min(4, -8, -48)
        = -48

currentMax = 4
currentMin = -48
ans = 6

Final Answer = 6
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 9. Example 4: Maximum Sum Circular Subarray

## Problem

Circular array mein maximum sum subarray nikalo.

```text
nums = [5, -3, 5]
Output: 10
Explanation:  circular subarray[5]
```

## Why Normal Kadane’s Fails?

Circular case mein subarray wrap ho sakta hai:

```text
Normal subarray:


Circular subarray:
 -> last 5 + first 5[5]
```

## Two Cases

### Case 1: Maximum subarray non-wrapping hai

Normal Kadane’s se answer milega.

### Case 2: Maximum subarray wrapping hai

Wrapping subarray ka matlab:

```text
Total sum - minimum non-wrapping subarray
```

Example:

```text
nums = [5, -3, 5]
total = 7

Minimum subarray = [-3]

Answer = 7 - (-3) = 10
```

## Special Case

Agar saare numbers negative hain:

```text
nums = [-3, -2, -1]
total = -6
minimum subarray = -6

total - minimum = 0
```

But answer `0` nahi ho sakta, kyunki empty subarray allowed nahi hai.

Isliye:

```text
Agar maxKadane < 0:
    return maxKadane
```

## Solution

```java
public int maxSubarraySumCircular(int[] nums) {
    int total = 0;
    int currentMax = 0;
    int maxSum = Integer.MIN_VALUE;

    int currentMin = 0;
    int minSum = Integer.MAX_VALUE;

    for (int num : nums) {
        total += num;

        currentMax = Math.max(num, currentMax + num);
        maxSum = Math.max(maxSum, currentMax);

        currentMin = Math.min(num, currentMin + num);
        minSum = Math.min(minSum, currentMin);
    }

    if (maxSum < 0) {
        return maxSum;
    }

    return Math.max(maxSum, total - minSum);
}
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 10. Example 5: Best Time to Buy and Sell Stock

## Problem

Ek stock ka price array diya hai. Ek buy aur ek sell karke maximum profit nikalo.

```text
prices =[1][3][4][5][6][7]
Output: 5
Explanation: Buy at 1, sell at 6
```

## Why Kadane’s Variant?

Daily profit:

```text
profit[i] = prices[i] - prices[i - 1]
```

Ab problem ban gayi:

```text
Maximum sum contiguous subarray of profits
```

## Solution

```java
public int maxProfit(int[] prices) {
    int maxProfit = 0;
    int currentProfit = 0;

    for (int i = 1; i < prices.length; i++) {
        int dailyProfit = prices[i] - prices[i - 1];

        currentProfit = Math.max(0, currentProfit + dailyProfit);
        maxProfit = Math.max(maxProfit, currentProfit);
    }

    return maxProfit;
}
```

## Dry Run

```text
prices =[1][3][4][5][6][7]

Daily profits:
1 - 7 = -6
5 - 1 = 4
3 - 5 = -2
6 - 3 = 3
4 - 6 = -2

Kadane’s:
currentProfit = 0
maxProfit = 0

-6:
currentProfit = max(0, 0 - 6) = 0

4:
currentProfit = max(0, 0 + 4) = 4
maxProfit = 4

-2:
currentProfit = max(0, 4 - 2) = 2

3:
currentProfit = max(0, 2 + 3) = 5
maxProfit = 5

-2:
currentProfit = max(0, 5 - 2) = 3

Final Answer = 5
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 11. Kadane’s Variants

| Problem | Track | Transition |
|---|---|---|
| Maximum subarray sum | `currentSum`, `maxSum` | `max(num, currentSum + num)` |
| Minimum subarray sum | `currentMin`, `minSum` | `min(num, currentMin + num)` |
| Maximum product subarray | `currentMax`, `currentMin` | Consider both max and min |
| Circular maximum sum | `maxKadane`, `minKadane`, `total` | `max(maxKadane, total - minKadane)` |
| Stock profit | `currentProfit`, `maxProfit` | `max(0, currentProfit + dailyProfit)` |

---

# 12. Common Mistakes

## Mistake 1: `currentSum` aur `maxSum` Ko 0 Se Initialize Karna

Galat:

```java
int currentSum = 0;
int maxSum = 0;
```

Agar saare numbers negative hain:

```text
nums = [-3, -1, -2]
```

Correct answer `-1` hai, not `0`.

Correct initialization:

```java
int currentSum = nums;
int maxSum = nums;
```

---

## Mistake 2: Empty Subarray Allow Karna

Agar problem keh rahi hai:

```text
Subarray must contain at least one element
```

To `0` answer nahi ho sakta jab saare numbers negative hon.

---

## Mistake 3: Maximum Product Mein Sirf Max Track Karna

Product mein negative * negative = positive ho sakta hai.

Isliye `currentMin` bhi track karna zaroori hai.

---

## Mistake 4: Circular Case Mein Empty Subarray Consider Karna

```text
total - minSum
```

Agar `minSum == total`, to answer `0` ban jayega.

But empty subarray allowed nahi hai.

Isliye:

```java
if (maxSum < 0) {
    return maxSum;
}
```

---

## Mistake 5: Subarray Return Karte Waqt Wrong Index Update

Naya subarray start hone par:

```java
tempStart = i;
```

Aur answer update hone par:

```java
start = tempStart;
end = i;
```

Ye order yaad rakho.

---

# 13. Kadane’s Decision Flow

```text
Problem padho
      |
      v
Kya contiguous subarray chahiye?
      |
      +-- No --> Kadane’s probably nahi
      |
      +-- Yes
          |
          v
Kya maximum/minimum sum chahiye?
      |
      +-- Yes --> Kadane’s Algorithm
      |
      +-- Kya product chahiye?
              |
              +-- Yes --> Modified Kadane’s with max & min
              |
              +-- Kya circular array hai?
                      |
                      +-- Yes --> Kadane’s + total - minKadane
```

---

# 14. Important Problems

## Easy

- Best Time to Buy and Sell Stock
- Maximum Subarray
- Maximum Ascending Subarray Sum

## Medium

- Maximum Product Subarray
- Maximum Sum Circular Subarray
- Maximum Sum of Two Non-Overlapping Subarrays
- Best Sightseeing Pair
- Maximum Subarray Sum with One Deletion

## Hard

- Maximum Sum of 3 Non-Overlapping Subarrays
- Maximum Score of Good Subarray
- Maximum Subarray Min-Product

---

# 15. One-Line Recognition

> **Agar problem contiguous subarray ka maximum/minimum sum, product, profit, ya circular sum maang rahi hai — Kadane’s Algorithm try karo.**

Kadane’s Algorithm ka main power ye hai ki har index par sirf ek local decision leta hai: **extend karo ya restart karo**. Isse \(O(n^2)\) brute force \(O(n)\) mein convert ho jata hai.