# 🚀 DSA Pattern 02 — Sliding Window

> **Goal:** Samajhna ki Sliding Window **kyun** use hota hai, problem mein **kaise identify** karein, aur reusable Java template kaise apply karein.

---

## 1. Sliding Window Kya Hai?

Sliding Window ek technique hai jisme hum array ya string ke **contiguous part** ko window ki tarah maintain karte hain.

```text
String: a b c a b c
Window:   [b c a]
           ↑     ↑
         left   right
```

Window ke andar hum kuch information maintain karte hain:

- Sum
- Frequency
- Distinct characters
- Maximum / minimum
- Count of some condition

Then window ko move karte hain:

```text
[a b c] a b c
a [b c a] b c
a b [c a b] c
a b c [a b c]
```

Har step par poori window recalculate nahi karni hoti. Purani information ko update karke naya answer nikalte hain.

---

## 2. Sliding Window Kyun Use Karte Hain?

Main reason: **jab brute force mein har subarray/substring ko dobara calculate karna pade.**

Example: Length `k` ke subarray ka maximum sum.

```text
arr =[0][1][2][3][4][10][20]
k = 4
```

Brute force:

```java
for (int i = 0; i <= n - k; i++) {
    int sum = 0;

    for (int j = i; j < i + k; j++) {
        sum += arr[j];
    }

    maxSum = Math.max(maxSum, sum);
}
```

Time: \(O(n \times k)\)

But sliding window mein:

```text
First window: 1 + 4 + 2 + 10 = 17

Next window:
Remove 1
Add 2

17 - 1 + 2 = 18
```

Har next window sirf:

```text
newSum = oldSum - outgoingElement + incomingElement
```

Isliye time \(O(n)\) ho jata hai.

### Core Intuition

> Purane window ka result rakho, sirf window ke enter/exit hone wale elements ka effect update karo.

---

## 3. Kaise Pata Chalega Ki Sliding Window Use Hoga?

Problem mein ye words/signals dhundo:

| Signal | Meaning |
|---|---|
| **Subarray** | Array ke contiguous elements |
| **Substring** | String ke contiguous characters |
| **Longest** | Maximum length window chahiye |
| **Shortest** | Minimum length window chahiye |
| **Maximum / Minimum** | Window ka best value chahiye |
| **At most K** | Window mein K se kam ya equal allowed |
| **At least K** | Window mein K ya usse zyada chahiye |
| **Exactly K** | Often `atMost(K) - atMost(K - 1)` |
| **Contains / without repeating** | Frequency-based window |
| **Sum / product condition** | Variable-size window |

### Mental Checklist

1. Kya problem **contiguous subarray/substring** ke baare mein hai?  
2. Kya window expand/shrink karke condition maintain ho sakti hai?  
3. Kya brute force \(O(n^2)\) ya \(O(n^3)\) lagega?  
4. Kya window ke andar ki information easily update ho sakti hai?  
5. Kya answer window ki length, sum, frequency, ya count par depend karta hai?  

Agar 2–3 answers **yes** hain, to Sliding Window try karo.

---

# 4. Types of Sliding Window

## Type 1: Fixed Size Window

Window ki length fixed hoti hai.

```text
k = 3

[a b c] d e f
a [b c d] e f
a b [c d e] f
```

### Use When

- Maximum sum subarray of size `k`
- Average of all subarrays of size `k`
- Find all anagrams of a string
- Sliding window maximum

### Template

```java
public int fixedWindow(int[] arr, int k) {
    int windowSum = 0;

    for (int i = 0; i < k; i++) {
        windowSum += arr[i];
    }

    int ans = windowSum;

    for (int right = k; right < arr.length; right++) {
        windowSum += arr[right];          // new element enter
        windowSum -= arr[right - k];      // old element exit

        ans = Math.max(ans, windowSum);
    }

    return ans;
}
```

---

## Type 2: Variable Size Window

Window ki length change hoti hai.

```text
Condition valid:
Expand right

Condition invalid:
Shrink left
```

### Use When

- Longest substring without repeating characters
- Minimum size subarray sum
- Longest repeating character replacement
- Fruit into baskets
- Max consecutive ones III

### Template — Find Longest Valid Window

```java
public int longestWindow(int[] arr) {
    int left = 0;
    int ans = 0;

    for (int right = 0; right < arr.length; right++) {
        // add arr[right] into window

        while (windowInvalid()) {
            // remove arr[left] from window
            left++;
        }

        ans = Math.max(ans, right - left + 1);
    }

    return ans;
}
```

### Template — Find Shortest Valid Window

```java
public int shortestWindow(int[] arr, int target) {
    int left = 0;
    int ans = Integer.MAX_VALUE;

    for (int right = 0; right < arr.length; right++) {
        // add arr[right] into window

        while (windowValid()) {
            ans = Math.min(ans, right - left + 1);

            // remove arr[left] from window
            left++;
        }
    }

    return ans == Integer.MAX_VALUE ? 0 : ans;
}
```

---

## Type 3: Frequency / HashMap Window

Window ke andar characters ya numbers ki frequency maintain karte hain.

### Use When

- Longest substring without repeating characters
- Find all anagrams
- Minimum window substring
- Longest repeating character replacement

### Template

```java
Map<Character, Integer> map = new HashMap<>();

for (int right = 0; right < s.length(); right++) {
    char addChar = s.charAt(right);
    map.put(addChar, map.getOrDefault(addChar, 0) + 1);

    while (invalid(map)) {
        char removeChar = s.charAt(left);
        map.put(removeChar, map.get(removeChar) - 1);

        if (map.get(removeChar) == 0) {
            map.remove(removeChar);
        }

        left++;
    }

    ans = Math.max(ans, right - left + 1);
}
```

---

# 5. Example 1: Maximum Sum Subarray of Size K

## Problem

Array aur window size `k` diya hai. Size `k` ke kisi subarray ka maximum sum nikalo.

```text
arr =[1][2][3][5]
k = 3

Output: 9
Explanation:[1][3][5]
```

## Brute Force

```java
public int maxSumBruteForce(int[] arr, int k) {
    int maxSum = Integer.MIN_VALUE;

    for (int i = 0; i <= arr.length - k; i++) {
        int sum = 0;

        for (int j = i; j < i + k; j++) {
            sum += arr[j];
        }

        maxSum = Math.max(maxSum, sum);
    }

    return maxSum;
}
```

Time: \(O(n \times k)\)

## Why Sliding Window?

Har next window mein:

- Ek element add hota hai
- Ek element remove hota hai

```text
 -> sum = 8[1][2][5]
 -> sum = 8 - 2 + 1 = 7[5][1]
 -> sum = 7 - 1 + 3 = 9[1][3][5]
 -> sum = 9 - 5 + 2 = 6[1][2][3]
```

## Sliding Window Solution

```java
public int maxSum(int[] arr, int k) {
    int windowSum = 0;

    for (int i = 0; i < k; i++) {
        windowSum += arr[i];
    }

    int maxSum = windowSum;

    for (int right = k; right < arr.length; right++) {
        windowSum += arr[right];
        windowSum -= arr[right - k];

        maxSum = Math.max(maxSum, windowSum);
    }

    return maxSum;
}
```

## Dry Run

```text
arr =[1][2][3][5]
k = 3

First window:
[1][2][5]
sum = 8
maxSum = 8

right = 3:
add arr = 1
remove arr = 2
sum = 8 + 1 - 2 = 7
maxSum = 8

right = 4:
add arr = 3
remove arr = 1
sum = 7 + 3 - 1 = 9
maxSum = 9

right = 5:
add arr = 2
remove arr = 5
sum = 9 + 2 - 5 = 6
maxSum = 9

Answer = 9
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 6. Example 2: Longest Substring Without Repeating Characters

## Problem

String mein longest substring nikalo jisme koi character repeat na ho.

```text
s = "abcabcbb"
Output: 3
Explanation: "abc"
```

## Brute Force

Har substring check karo ki unique hai ya nahi.

Time: \(O(n^2)\) ya \(O(n^3)\)

## Why Sliding Window?

Jaise hi duplicate character aata hai, window invalid ho jati hai.

To left pointer ko aage badha kar duplicate remove karo.

```text
a b c a b c b b
[l-----r]
    duplicate 'a' aaya
  [l-----r]
```

## Solution

```java
public int lengthOfLongestSubstring(String s) {
    int[] freq = new int;
    int left = 0;
    int ans = 0;

    for (int right = 0; right < s.length(); right++) {
        char current = s.charAt(right);
        freq[current]++;

        while (freq[current] > 1) {
            char removeChar = s.charAt(left);
            freq[removeChar]--;

            left++;
        }

        ans = Math.max(ans, right - left + 1);
    }

    return ans;
}
```

## Dry Run

```text
s = "abcabcbb"

right = 0, char = a
window = "a"
ans = 1

right = 1, char = b
window = "ab"
ans = 2

right = 2, char = c
window = "abc"
ans = 3

right = 3, char = a
'a' repeat
remove left 'a'
window = "bca"
ans = 3

right = 4, char = b
'b' repeat
remove left 'b'
window = "cab"
ans = 3

right = 5, char = c
'c' repeat
remove left 'c'
window = "abc"
ans = 3

right = 6, char = b
'b' repeat
remove left 'a', then 'b'
window = "cb"
ans = 3

right = 7, char = b
'b' repeat
remove left 'c', then 'b'
window = "b"
ans = 3

Final Answer = 3
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\), because only 128 ASCII characters

---

# 7. Example 3: Minimum Size Subarray Sum

## Problem

Positive integers array aur target diya hai. Chhota subarray find karo jiska sum `>= target` ho.

```text
arr =[1][2][3][4]
target = 7

Output: 2
Explanation:[3][4]
```

## Why Sliding Window?

- Sum kam hai -> window expand karo
- Sum target se bada hai -> window shrink karo

Kyunki saare numbers positive hain, window shrink karne par sum decrease hoga.

## Solution

```java
public int minSubArrayLen(int target, int[] nums) {
    int left = 0;
    int sum = 0;
    int ans = Integer.MAX_VALUE;

    for (int right = 0; right < nums.length; right++) {
        sum += nums[right];

        while (sum >= target) {
            ans = Math.min(ans, right - left + 1);

            sum -= nums[left];
            left++;
        }
    }

    return ans == Integer.MAX_VALUE ? 0 : ans;
}
```

## Dry Run

```text
arr =[1][2][3][4]
target = 7

right = 0:
window = 
sum = 2

right = 1:
window =[2][3]
sum = 5

right = 2:
window =[1][3][2]
sum = 6

right = 3:
window =[1][2][3]
sum = 8
8 >= 7
length = 4
ans = 4

remove 2
window =[1][2][3]
sum = 6

right = 4:
window =[1][2][3][4]
sum = 10
10 >= 7
length = 4
ans remains 4

remove 3
window =[1][2][4]
sum = 7
7 >= 7
length = 3
ans = 3

remove 1
window =[2][4]
sum = 6

right = 5:
window =[3][4][2]
sum = 9
9 >= 7
length = 3
ans remains 3

remove 2
window =[3][4]
sum = 7
7 >= 7
length = 2
ans = 2

Final Answer = 2
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

### Important Note

Ye pattern tabhi kaam karega jab array ke elements **positive** hon.

Agar negative numbers ho, to simple sliding window fail ho sakta hai. Us case mein prefix sum + monotonic deque use hota hai.

---

# 8. Example 4: Fruit Into Baskets

## Problem

Ek window mein maximum 2 distinct fruits allowed hain. Longest window nikalo.

```text
fruits =[1][2][3]
Output: 4
Explanation:[2][1]
```

## Why Sliding Window?

Ye basically hai:

> Longest subarray with at most 2 distinct numbers.

- Window mein fruits add karo
- Agar distinct fruits `> 2` ho jaye, to left se remove karo

## Solution

```java
public int totalFruit(int[] fruits) {
    Map<Integer, Integer> map = new HashMap<>();
    int left = 0;
    int ans = 0;

    for (int right = 0; right < fruits.length; right++) {
        int fruit = fruits[right];
        map.put(fruit, map.getOrDefault(fruit, 0) + 1);

        while (map.size() > 2) {
            int leftFruit = fruits[left];
            map.put(leftFruit, map.get(leftFruit) - 1);

            if (map.get(leftFruit) == 0) {
                map.remove(leftFruit);
            }

            left++;
        }

        ans = Math.max(ans, right - left + 1);
    }

    return ans;
}
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\), because map size maximum 3 tak jayega

---

# 9. Example 5: Longest Repeating Character Replacement

## Problem

String mein maximum `k` characters replace karke longest substring of same character banana hai.

```text
s = "AABABBA"
k = 1

Output: 4
Explanation: "AABA" -> "AAAA"
```

## Why Sliding Window?

Window valid hai agar:

```text
windowLength - maxFrequency <= k
```

Matlab:

```text
Jo characters max frequency wale character se different hain,
unhe replace karna padega.
```

## Solution

```java
public int characterReplacement(String s, int k) {
    int[] freq = new int;
    int left = 0;
    int maxFreq = 0;
    int ans = 0;

    for (int right = 0; right < s.length(); right++) {
        char current = s.charAt(right);
        freq[current - 'A']++;

        maxFreq = Math.max(maxFreq, freq[current - 'A']);

        while ((right - left + 1) - maxFreq > k) {
            freq[s.charAt(left) - 'A']--;
            left++;
        }

        ans = Math.max(ans, right - left + 1);
    }

    return ans;
}
```

## Why `maxFreq` ko decrease nahi karte?

Technically purane `maxFreq` stale ho sakta hai, but answer correct rehta hai.

Kyunki hum sirf **longer window** dhundh rahe hain. Agar koi better window milegi, to `maxFreq` automatically update ho jayega.

## Complexity

- Time: \(O(n)\)
- Space: \(O(26)\)

---

# 10. Example 6: Find All Anagrams in a String

## Problem

String `s` mein string `p` ke saare anagrams ke starting indices find karo.

```text
s = "cbaebabacd"
p = "abc"

Output:[0][6]
```

## Why Fixed Sliding Window?

`p` ki length fixed hai.

To `s` mein bhi usi length ki window slide karni hai aur frequency compare karni hai.

## Solution

```java
public List<Integer> findAnagrams(String s, String p) {
    List<Integer> ans = new ArrayList<>();

    if (p.length() > s.length()) return ans;

    int[] pFreq = new int;
    int[] windowFreq = new int;

    for (char c : p.toCharArray()) {
        pFreq[c - 'a']++;
    }

    int windowSize = p.length();

    for (int i = 0; i < s.length(); i++) {
        windowFreq[s.charAt(i) - 'a']++;

        if (i >= windowSize) {
            windowFreq[s.charAt(i - windowSize) - 'a']--;
        }

        if (i >= windowSize - 1 && Arrays.equals(pFreq, windowFreq)) {
            ans.add(i - windowSize + 1);
        }
    }

    return ans;
}
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(26)\)

---

# 11. Example 7: Sliding Window Maximum

## Problem

Array aur window size `k` diya hai. Har window ka maximum element return karo.

```text
nums = [1, 3, -1, -3, 5, 3, 6, 7]
k = 3

Output:[3][5][6][7]
```

## Why Simple Sliding Window Fails?

Yahan sum jaisa simple update possible nahi hai.

Agar maximum element window se bahar chala jaye, to naya maximum dhundhna padega.

Isliye yahan **Monotonic Deque** use hota hai.

## Idea

Deque mein indices rakho such that values decreasing order mein ho.

```text
Front of deque = current window maximum
```

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

## Complexity

- Time: \(O(n)\)
- Space: \(O(k)\)

---

# 12. Fixed vs Variable Window

| Feature | Fixed Size Window | Variable Size Window |
|---|---|---|
| Window length | Fixed `k` | Change hoti hai |
| Movement | `right` badhta hai, `right - k` remove hota hai | `right` expand, `left` shrink |
| Example | Max sum of size K | Longest substring without repeat |
| Condition | Window length always `k` | Window validity condition |
| Common data | Sum, frequency array | HashMap, frequency array |
| Answer | Usually max/min value | Usually longest/shortest length |

---

# 13. Common Mistakes

## Mistake 1: Non-Contiguous Elements Par Sliding Window Lagana

Sliding window sirf **contiguous** subarray/substring ke liye hai.

```text
Subsequence: not sliding window
Subarray: sliding window possible
```

---

## Mistake 2: Negative Numbers Wale Sum Problem Mein Simple Window Lagana

```text
arr = [1, -1, 5, -2, 3]
target = 3
```

Negative numbers hone par window shrink karne se sum hamesha decrease nahi hota.

Is case mein:

- Prefix Sum + HashMap
- Monotonic Deque

Better ho sakta hai.

---

## Mistake 3: Window Length Formula Galat Lena

Window length:

```java
right - left + 1
```

Agar `right` inclusive hai aur `left` inclusive hai.

---

## Mistake 4: Frequency Remove Karte Waqt Zero Handle Na Karna

```java
map.put(c, map.get(c) - 1);

if (map.get(c) == 0) {
    map.remove(c);
}
```

Agar zero-frequency entries remove nahi karoge, to `map.size()` galat ho sakta hai.

---

## Mistake 5: Har Window Ka Sum Dobara Calculate Karna

```java
// Wrong
for (int i = left; i <= right; i++) {
    sum += arr[i];
}
```

Instead:

```java
sum += arr[right];       // enter
sum -= arr[left];        // exit
left++;
```

---

# 14. Sliding Window Decision Flow

```text
Problem padho
      |
      v
Kya contiguous subarray / substring chahiye?
      |
      +-- No --> Sliding Window probably nahi
      |
      +-- Yes
          |
          v
Kya window size fixed hai?
      |
      +-- Yes --> Fixed Size Sliding Window
      |
      +-- No
          |
          v
Kya condition monotonic hai?
      |
      +-- Yes --> Variable Size Sliding Window
      |
      +-- Window max/min chahiye?
              |
              +-- Yes --> Monotonic Deque
```

---

# 15. Important Problems

## Easy

- Maximum Average Subarray I
- Best Time to Buy and Sell Stock
- Longest Nice Substring
- Minimum Difference Between Highest and Lowest of K Scores

## Medium

- Longest Substring Without Repeating Characters
- Maximum Sum Subarray of Size K
- Fruit Into Baskets
- Longest Repeating Character Replacement
- Permutation in String
- Find All Anagrams in a String
- Minimum Size Subarray Sum
- Max Consecutive Ones III
- Sliding Window Maximum

## Hard

- Minimum Window Substring
- Sliding Window Median
- Subarrays with K Different Integers
- Shortest Subarray with Sum at Least K

---

# 16. One-Line Recognition

> **Agar problem contiguous subarray/substring ki longest, shortest, maximum, minimum, ya count value maang rahi hai — Sliding Window try karo.**

Sliding Window ka main power ye hai ki har window ka result scratch se calculate karne ki jagah hum previous window ka result update karte hain, isliye \(O(n^2)\) se \(O(n)\) tak optimize hota hai.