# 🚀 40 Important DSA Patterns — Identify, Apply & Master

> Goal: Recognize the pattern within 1–2 minutes, apply a reusable template, and explain time/space complexity confidently.

## How to Use This File

For every pattern:

1. **Identify** — keywords and signals in the problem.
2. **Core Idea** — why the pattern works.
3. **Template** — reusable Java skeleton.
4. **Apply** — step-by-step mapping from problem to template.
5. **Example** — dry run.
6. **Complexity** — time and space.
7. **Practice** — important problems.

---

# 1. Arrays & Searching Patterns

## 1. Brute Force → Optimization

### Identify
- Problem asks for **all pairs**, **all subarrays**, or **all combinations**.
- Brute force is \(O(n^2)\), \(O(n^3)\), or exponential.
- Constraints are large, so brute force will TLE.
- You repeatedly compute the same information.

### Core Idea
Do not remove brute force blindly. First write it, then find the repeated work and replace it with:
- HashMap / HashSet
- Prefix sum
- Sorting + two pointers
- Binary search
- Sliding window
- DP

### Apply
1. Write brute force.
2. Find the inner repeated calculation.
3. Ask: “Can I store this result?”
4. Choose the best optimization structure.
5. Verify correctness with edge cases.

### Example
**Problem:** Count pairs with sum `k`.

Brute force:

```java
int count = 0;
for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
        if (arr[i] + arr[j] == k) count++;
    }
}
```

Optimized with HashMap:

```java
public int countPairs(int[] arr, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    int count = 0;

    for (int num : arr) {
        count += map.getOrDefault(k - num, 0);
        map.put(num, map.getOrDefault(num, 0) + 1);
    }

    return count;
}
```

### Complexity
- Brute force: \(O(n^2)\)
- Optimized: \(O(n)\)
- Space: \(O(n)\)

### Practice
- Two Sum
- Subarray Sum Equals K
- Longest Consecutive Sequence
- Count Subarrays with Given Sum

---

## 2. Two Pointers

### Identify
- Array or string is **sorted**, or can be sorted.
- Need to find a **pair**, **triplet**, or **subarray** satisfying a condition.
- Need to compare elements from both ends.
- Need to remove duplicates or partition an array.

### Core Idea
Use two indices that move based on comparison. This avoids checking all pairs.

### Types
1. **Opposite ends:** `left = 0`, `right = n - 1`
2. **Same direction:** `slow` and `fast`
3. **Three pointers:** used in 3Sum, Dutch National Flag

### Template — Opposite Direction

```java
public boolean twoSumSorted(int[] arr, int target) {
    int left = 0, right = arr.length - 1;

    while (left < right) {
        int sum = arr[left] + arr[right];

        if (sum == target) return true;
        else if (sum < target) left++;
        else right--;
    }

    return false;
}
```

### Apply — 3Sum
1. Sort array.
2. Fix first element `i`.
3. Use two pointers for remaining range.
4. Skip duplicates.

```java
public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> ans = new ArrayList<>();

    for (int i = 0; i < nums.length - 2; i++) {
        if (i > 0 && nums[i] == nums[i - 1]) continue;

        int left = i + 1, right = nums.length - 1;

        while (left < right) {
            int sum = nums[i] + nums[left] + nums[right];

            if (sum == 0) {
                ans.add(Arrays.asList(nums[i], nums[left], nums[right]));

                while (left < right && nums[left] == nums[left + 1]) left++;
                while (left < right && nums[right] == nums[right - 1]) right--;

                left++;
                right--;
            } else if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }
    }

    return ans;
}
```

### Complexity
- Two Sum II: \(O(n)\)
- 3Sum: \(O(n^2)\)
- Space: \(O(1)\), excluding output

### Practice
- Two Sum II
- 3Sum
- Container With Most Water
- Remove Duplicates from Sorted Array
- Sort Colors

---

## 3. Sliding Window

### Identify
- Problem involves **contiguous** subarray or substring.
- Keywords: `longest`, `shortest`, `maximum`, `minimum`, `at most K`, `exactly K`.
- Window condition can expand and shrink.

### Core Idea
Maintain a window `[left, right]`. Expand `right`; when condition breaks, shrink `left`.

### Types
1. **Fixed-size window**
2. **Variable-size window**
3. **Window with HashMap / frequency**
4. **At most K distinct / at most K flips**

### Template — Variable Window

```java
public int longestSubstring(String s) {
    int left = 0, ans = 0;
    Map<Character, Integer> map = new HashMap<>();

    for (int right = 0; right < s.length(); right++) {
        char c = s.charAt(right);
        map.put(c, map.getOrDefault(c, 0) + 1);

        while (invalid(map)) {
            char leftChar = s.charAt(left);
            map.put(leftChar, map.get(leftChar) - 1);
            if (map.get(leftChar) == 0) map.remove(leftChar);
            left++;
        }

        ans = Math.max(ans, right - left + 1);
    }

    return ans;
}
```

### Apply — Longest Substring Without Repeating Characters

```java
public int lengthOfLongestSubstring(String s) {
    int[] freq = new int;
    int left = 0, ans = 0;

    for (int right = 0; right < s.length(); right++) {
        freq[s.charAt(right)]++;

        while (freq[s.charAt(right)] > 1) {
            freq[s.charAt(left)]--;
            left++;
        }

        ans = Math.max(ans, right - left + 1);
    }

    return ans;
}
```

### Dry Run
`s = "abcabc"`

| right | char | window | ans |
|---|---|---|---|
| 0 | a | a | 1 |
| 1 | b | ab | 2 |
| 2 | c | abc | 3 |
| 3 | a | bca | 3 |
| 4 | b | cab | 3 |
| 5 | c | abc | 3 |

### Complexity
- Time: \(O(n)\)
- Space: \(O(k)\), where `k` is charset/window state size

### Practice
- Longest Substring Without Repeating Characters
- Minimum Size Subarray Sum
- Fruit Into Baskets
- Longest Repeating Character Replacement
- Sliding Window Maximum

---

## 4. Prefix Sum

### Identify
- Repeated **range sum** queries.
- Need sum of subarray `arr[i...j]`.
- Need count of subarrays with a given sum.
- Need equality of prefix frequencies.

### Core Idea
Store cumulative sums so that:

\[
\text{sum}(i, j) = prefix[j + 1] - prefix[i]
\]

### Template

```java
public int[] buildPrefixSum(int[] arr) {
    int n = arr.length;
    int[] prefix = new int[n + 1];

    for (int i = 0; i < n; i++) {
        prefix[i + 1] = prefix[i] + arr[i];
    }

    return prefix;
}

public int rangeSum(int[] prefix, int left, int right) {
    return prefix[right + 1] - prefix[left];
}
```

### Apply — Subarray Sum Equals K

Use HashMap to store frequency of previous prefix sums.

```java
public int subarraySum(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);

    int sum = 0, count = 0;

    for (int num : nums) {
        sum += num;
        count += map.getOrDefault(sum - k, 0);
        map.put(sum, map.getOrDefault(sum, 0) + 1);
    }

    return count;
}
```

### Why `map.put(0, 1)`?
It handles subarrays starting from index `0`.

Example: `nums = [1, 2, 3]`, `k = 3`.

At index `2`, `sum = 6`, `sum - k = 3`, and prefix `3` occurred before, so one valid subarray exists.

### Complexity
- Build prefix: \(O(n)\)
- Range sum query: \(O(1)\)
- Subarray Sum Equals K: \(O(n)\)
- Space: \(O(n)\)

### Practice
- Subarray Sum Equals K
- Range Sum Query Immutable
- Product of Array Except Self
- Continuous Subarray Sum
- Contiguous Array

---

## 5. Difference Array

### Identify
- Multiple **range updates**.
- Queries like: add `x` from index `l` to `r`.
- Final array is needed after all updates.
- Direct update for every element would be \(O(n)\) per query.

### Core Idea
Instead of updating every index, mark changes at boundaries.

For range `[l, r]` add `x`:

```text
diff[l] += x
diff[r + 1] -= x
```

Then take prefix sum to get final array.

### Template

```java
public int[] applyRangeUpdates(int n, int[][] updates) {
    int[] diff = new int[n + 1];

    for (int[] update : updates) {
        int left = update, right = update, value = update;
        diff[left] += value;
        diff[right + 1] -= value;
    }

    int[] result = new int[n];
    int sum = 0;

    for (int i = 0; i < n; i++) {
        sum += diff[i];
        result[i] = sum;
    }

    return result;
}
```

### Example
`n = 5`, update: add `2` from index `1` to `3`.

```text
diff = [0, 2, 0, 0, -2, 0]
prefix =[0][2]
```

### Complexity
- Each update: \(O(1)\)
- Final construction: \(O(n)\)
- Space: \(O(n)\)

### Practice
- Range Addition
- Corporate Flight Bookings
- Car Pooling
- Shifting Letters II

---

## 6. Binary Search

### Identify
- Array is **sorted**.
- Search space is monotonic.
- Need first/last occurrence.
- Need minimum/maximum satisfying a condition.
- Time complexity must be \(O(\log n)\).

### Core Idea
Repeatedly divide search space into half.

### Template

```java
public int binarySearch(int[] arr, int target) {
    int left = 0, right = arr.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] == target) return mid;
        else if (arr[mid] < target) left = mid + 1;
        else right = mid - 1;
    }

    return -1;
}
```

### First Occurrence

```java
public int firstOccurrence(int[] arr, int target) {
    int left = 0, right = arr.length - 1, ans = -1;

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

### Apply — Search Insert Position

Find the first index where `arr[index] >= target`.

```java
public int searchInsert(int[] nums, int target) {
    int left = 0, right = nums.length;

    while (left < right) {
        int mid = left + (right - left) / 2;

        if (nums[mid] < target) left = mid + 1;
        else right = mid;
    }

    return left;
}
```

### Complexity
- Time: \(O(\log n)\)
- Space: \(O(1)\)

### Practice
- Binary Search
- Search Insert Position
- First Bad Version
- Find First and Last Position
- Search in Rotated Sorted Array

---

## 7. Binary Search on Answer

### Identify
- Problem asks **minimum maximum** or **maximum minimum**.
- Direct computation is difficult.
- Answer lies in a monotonic range.
- You can check whether an answer is feasible.

### Core Idea
Instead of searching in array, binary search the possible answer.

```text
low = minimum possible answer
high = maximum possible answer

while low < high:
    mid = (low + high) / 2
    if feasible(mid):
        high = mid      // minimize answer
    else:
        low = mid + 1
```

### Apply — Koko Eating Bananas

Koko must eat all bananas within `h` hours. Find minimum eating speed.

```java
public int minEatingSpeed(int[] piles, int h) {
    int low = 1;
    int high = Arrays.stream(piles).max().getAsInt();

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

### Feasibility Function
For speed `k`, calculate total hours:

\[
\text{hours} = \sum \left\lceil \frac{pile}{k} \right\rceil
\]

### Complexity
- Time: \(O(n \log \max(piles))\)
- Space: \(O(1)\)

### Practice
- Koko Eating Bananas
- Minimum Number of Days to Make m Bouquets
- Split Array Largest Sum
- Capacity to Ship Packages Within D Days
- Median of Two Sorted Arrays

---

## 8. Cyclic Sort / Index Placement

### Identify
- Array contains numbers from `1` to `n` or `0` to `n`.
- Exactly one or more numbers are missing/duplicated.
- Need \(O(n)\) time and \(O(1)\) space.
- Values can be placed at value-based indices.

### Core Idea
Place every number at its correct index:

```text
value v should go to index v - 1, if values are 1 to n
value v should go to index v, if values are 0 to n - 1
```

### Template

```java
public void cyclicSort(int[] arr) {
    int i = 0;

    while (i < arr.length) {
        int correct = arr[i] - 1;

        if (arr[i] != arr[correct]) {
            swap(arr, i, correct);
        } else {
            i++;
        }
    }
}

private void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}
```

### Apply — First Missing Positive

```java
public int firstMissingPositive(int[] nums) {
    int i = 0;

    while (i < nums.length) {
        int correct = nums[i] - 1;

        if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[correct]) {
            swap(nums, i, correct);
        } else {
            i++;
        }
    }

    for (i = 0; i < nums.length; i++) {
        if (nums[i] != i + 1) return i + 1;
    }

    return nums.length + 1;
}
```

### Complexity
- Time: \(O(n)\)
- Space: \(O(1)\)

### Practice
- First Missing Positive
- Find All Numbers Disappeared in an Array
- Find the Duplicate Number
- Missing Number
- Set Mismatch

---

## 9. Sorting + Cycle Detection

### Identify
- Need minimum swaps to sort.
- Elements have a natural sorted position.
- Values may be transformed, such as digit sum.
- Need to detect cycles in index mapping.

### Core Idea
Create pairs of `(value, originalIndex)`, sort them, then follow cycles.

### Apply — Minimum Swaps to Sort

```java
public int minSwaps(int[] arr) {
    int n = arr.length;
    int[][] pairs = new int[n];

    for (int i = 0; i < n; i++) {
        pairs[i] = arr[i];
        pairs[i] = i;
    }

    Arrays.sort(pairs, (a, b) -> Integer.compare(a, b));

    boolean[] visited = new boolean[n];
    int swaps = 0;

    for (int i = 0; i < n; i++) {
        if (visited[i] || pairs[i] == i) continue;

        int cycleSize = 0;
        int j = i;

        while (!visited[j]) {
            visited[j] = true;
            j = pairs[j];
            cycleSize++;
        }

        swaps += cycleSize - 1;
    }

    return swaps;
}
```

### Why `cycleSize - 1`?
A cycle of `k` misplaced elements needs `k - 1` swaps.

### Complexity
- Time: \(O(n \log n)\)
- Space: \(O(n)\)

### Practice
- Minimum Swaps to Sort an Array
- Minimum Swaps to Arrange a Binary Grid
- Minimum Swaps to Sort by Digit Sum
- Largest Number

---

## 10. Kadane’s Algorithm

### Identify
- Need maximum sum of a **contiguous subarray**.
- Need maximum product subarray variant.
- Negative numbers are present.
- Need best subarray ending at each index.

### Core Idea
At every index, decide:

```text
currentMax = max(arr[i], currentMax + arr[i])
```

Either extend the previous subarray or start fresh.

### Template

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

### Dry Run
`nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]`

| num | currentSum | maxSum |
|---|---:|---:|
| -2 | -2 | -2 |
| 1 | 1 | 1 |
| -3 | -2 | 1 |
| 4 | 4 | 4 |
| -1 | 3 | 4 |
| 2 | 5 | 5 |
| 1 | 6 | 6 |
| -5 | 1 | 6 |
| 4 | 5 | 6 |

Answer: `6`, subarray `[4, -1, 2, 1]`.

### Complexity
- Time: \(O(n)\)
- Space: \(O(1)\)

### Practice
- Maximum Subarray
- Maximum Product Subarray
- Maximum Sum Circular Subarray
- Best Time to Buy and Sell Stock

---

# 2. Hashing & Counting Patterns

## 11. HashMap Frequency

### Identify
- Need frequency of elements.
- Need count pairs, duplicates, or anagrams.
- Need first non-repeating element.
- Need Top K frequent elements.

### Core Idea
Map each key to its count, index, or latest position.

### Template

```java
Map<Character, Integer> freq = new HashMap<>();

for (char c : s.toCharArray()) {
    freq.put(c, freq.getOrDefault(c, 0) + 1);
}
```

### Apply — Top K Frequent Elements

```java
public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> freq = new HashMap<>();

    for (int num : nums) {
        freq.put(num, freq.getOrDefault(num, 0) + 1);
    }

    PriorityQueue<int[]> heap =
        new PriorityQueue<>((a, b) -> a - b);

    for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
        heap.offer(new int[]{entry.getKey(), entry.getValue()});

        if (heap.size() > k) {
            heap.poll();
        }
    }

    int[] ans = new int[k];

    for (int i = k - 1; i >= 0; i--) {
        ans[i] = heap.poll();
    }

    return ans;
}
```

### Complexity
- Time: \(O(n \log k)\)
- Space: \(O(n)\)

### Practice
- Top K Frequent Elements
- Group Anagrams
- First Unique Character
- Valid Anagram
- Subarray Sum Equals K

---

## 12. HashSet

### Identify
- Need fast duplicate detection.
- Need membership check in \(O(1)\).
- Need longest consecutive sequence.
- Need visited tracking.

### Core Idea
HashSet gives average \(O(1)\) insert, search, and delete.

### Apply — Longest Consecutive Sequence

Only start counting from the beginning of a sequence.

```java
public int longestConsecutive(int[] nums) {
    Set<Integer> set = new HashSet<>();

    for (int num : nums) set.add(num);

    int longest = 0;

    for (int num : set) {
        if (!set.contains(num - 1)) {
            int current = num;
            int length = 1;

            while (set.contains(current + 1)) {
                current++;
                length++;
            }

            longest = Math.max(longest, length);
        }
    }

    return longest;
}
```

### Complexity
- Time: \(O(n)\)
- Space: \(O(n)\)

### Practice
- Longest Consecutive Sequence
- Contains Duplicate
- Intersection of Two Arrays
- Happy Number
- Longest Substring Without Repeating Characters

---

## 13. Counting / Frequency Array

### Identify
- Characters or values are limited, such as `a-z`, `0-25`, or small range.
- Need counts instead of full HashMap.
- Need sorting by frequency.
- Need anagram or character comparison.

### Core Idea
Use an array as a faster frequency table.

### Template

```java
int[] freq = new int;

for (char c : s.toCharArray()) {
    freq[c - 'a']++;
}
```

### Apply — Sort Characters by Frequency

```java
public String frequencySort(String s) {
    int[] freq = new int;

    for (char c : s.toCharArray()) {
        freq[c]++;
    }

    PriorityQueue<Character> heap =
        new PriorityQueue<>((a, b) -> freq[b] - freq[a]);

    for (char c = 0; c < 128; c++) {
        if (freq[c] > 0) heap.offer(c);
    }

    StringBuilder sb = new StringBuilder();

    while (!heap.isEmpty()) {
        char c = heap.poll();

        for (int i = 0; i < freq[c]; i++) {
            sb.append(c);
        }
    }

    return sb.toString();
}
```

### Complexity
- Time: \(O(n + k \log k)\), where `k` is number of distinct characters
- Space: \(O(k)\)

### Practice
- Sort Characters by Frequency
- Valid Anagram
- Find All Anagrams in a String
- Task Scheduler
- Reorganize String

---

## 14. Bit Manipulation

### Identify
- Elements appear once, twice, or three times.
- Need XOR-based uniqueness.
- Need powers of two.
- Need set/unset/check bits.
- Need optimize space using bits.

### Core Operations

| Operation | Code | Meaning |
|---|---|---|
| Check bit | `(n & (1 << i)) != 0` | Is bit `i` set? |
| Set bit | `n | (1 << i)` | Make bit `i` 1 |
| Unset bit | `n & ~(1 << i)` | Make bit `i` 0 |
| Toggle bit | `n ^ (1 << i)` | Flip bit `i` |
| Power of two | `n > 0 && (n & (n - 1)) == 0` | Only one set bit |

### Apply — Single Number

XOR removes duplicate pairs.

```java
public int singleNumber(int[] nums) {
    int ans = 0;

    for (int num : nums) {
        ans ^= num;
    }

    return ans;
}
```

### Apply — Counting Bits

```java
public int[] countBits(int n) {
    int[] ans = new int[n + 1];

    for (int i = 1; i <= n; i++) {
        ans[i] = ans[i >> 1] + (i & 1);
    }

    return ans;
}
```

### Complexity
- Single Number: \(O(n)\), \(O(1)\)
- Counting Bits: \(O(n)\), \(O(n)\)

### Practice
- Single Number
- Single Number II
- Number of 1 Bits
- Counting Bits
- Missing Number
- Power of Two

---

# 3. Stack & Queue Patterns

## 15. Monotonic Stack

### Identify
- Need **next greater**, **previous greater**, **next smaller**, or **previous smaller** element.
- Need span, stock span, temperature wait days.
- Need largest rectangle in histogram.
- Need remove digits to make smallest number.

### Core Idea
Maintain a stack whose values are increasing or decreasing. Pop elements that violate monotonicity.

### Template — Next Greater Element

```java
public int[] nextGreaterElements(int[] nums) {
    int n = nums.length;
    int[] ans = new int[n];
    Arrays.fill(ans, -1);

    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {
        while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
            ans[stack.pop()] = nums[i];
        }

        stack.push(i);
    }

    return ans;
}
```

### Apply — Daily Temperatures

```java
public int[] dailyTemperatures(int[] temperatures) {
    int n = temperatures.length;
    int[] ans = new int[n];

    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {
        while (!stack.isEmpty() &&
               temperatures[stack.peek()] < temperatures[i]) {
            int prevIndex = stack.pop();
            ans[prevIndex] = i - prevIndex;
        }

        stack.push(i);
    }

    return ans;
}
```

### Complexity
- Time: \(O(n)\), each index pushed and popped once
- Space: \(O(n)\)

### Practice
- Next Greater Element I
- Next Greater Element II
- Daily Temperatures
- Largest Rectangle in Histogram
- Remove K Digits

---

## 16. Monotonic Deque

### Identify
- Need maximum or minimum in every sliding window.
- Need efficient removal from both ends.
- Sliding window maximum/minimum.
- Need optimized DP transitions.

### Core Idea
Maintain a deque of indices in decreasing order for maximum window.

### Apply — Sliding Window Maximum

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

### Complexity
- Time: \(O(n)\)
- Space: \(O(k)\)

### Practice
- Sliding Window Maximum
- Shortest Subarray with Sum at Least K
- Jump Game VI
- Constrained Subsequence Sum

---

## 17. Stack for Parentheses

### Identify
- Problem has brackets: `()`, `[]`, `{}`.
- Need valid parentheses.
- Need minimum insertions/removals.
- Need longest valid substring.

### Core Idea
Push opening brackets. On closing bracket, verify matching opening bracket.

### Apply — Valid Parentheses

```java
public boolean isValid(String s) {
    Deque<Character> stack = new ArrayDeque<>();

    for (char c : s.toCharArray()) {
        if (c == '(') stack.push(')');
        else if (c == '[') stack.push(']');
        else if (c == '{') stack.push('}');
        else {
            if (stack.isEmpty() || stack.pop() != c) return false;
        }
    }

    return stack.isEmpty();
}
```

### Complexity
- Time: \(O(n)\)
- Space: \(O(n)\)

### Practice
- Valid Parentheses
- Minimum Remove to Make Valid Parentheses
- Longest Valid Parentheses
- Generate Parentheses

---

## 18. Queue / BFS

### Identify
- Need level-by-level processing.
- Need shortest path in unweighted graph.
- Need nearest distance from multiple sources.
- Need process nodes in FIFO order.

### Core Idea
BFS explores all nodes at distance `1`, then distance `2`, and so on.

### Apply — Rotting Oranges

```java
public int orangesRotting(int[][] grid) {
    int rows = grid.length, cols = grid.length;
    Queue<int[]> queue = new LinkedList<>();
    int fresh = 0;

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (grid[i][j] == 2) queue.offer(new int[]{i, j});
            else if (grid[i][j] == 1) fresh++;
        }
    }

    if (fresh == 0) return 0;

    int minutes = 0;
    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    while (!queue.isEmpty() && fresh > 0) {
        int size = queue.size();
        minutes++;

        for (int i = 0; i < size; i++) {
            int[] cell = queue.poll();

            for (int[] dir : directions) {
                int r = cell + dir;
                int c = cell + dir;

                if (r >= 0 && r < rows && c >= 0 && c < cols &&
                    grid[r][c] == 1) {
                    grid[r][c] = 2;
                    fresh--;
                    queue.offer(new int[]{r, c});
                }
            }
        }
    }

    return fresh == 0 ? minutes : -1;
}
```

### Complexity
- Time: \(O(rows \times cols)\)
- Space: \(O(rows \times cols)\)

### Practice
- Rotting Oranges
- Binary Tree Level Order Traversal
- Shortest Path in Binary Matrix
- 01 Matrix
- Walls and Gates

---

## 19. PriorityQueue / Heap

### Identify
- Need smallest or largest K elements.
- Need repeatedly extract min/max.
- Need merge K sorted lists.
- Need scheduling by priority.
- Need median from stream.

### Core Idea
Java `PriorityQueue` is a binary heap.

- Min-heap: smallest at top.
- Max-heap: largest at top.

### Templates

```java
// Min-heap
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// Max-heap
PriorityQueue<Integer> maxHeap =
    new PriorityQueue<>(Collections.reverseOrder());
```

### Apply — Kth Largest Element

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

### Complexity
- Time: \(O(n \log k)\)
- Space: \(O(k)\)

### Practice
- Kth Largest Element in an Array
- K Closest Points to Origin
- Merge K Sorted Lists
- Task Scheduler
- Last Stone Weight

---

## 20. Two Heaps

### Identify
- Need running median.
- Need split data into lower half and upper half.
- Need balance two priority queues.
- Need max of lower half and min of upper half.

### Core Idea
- `maxHeap`: smaller half, largest at top.
- `minHeap`: larger half, smallest at top.
- Keep sizes balanced: `maxHeap.size()` is equal to or one more than `minHeap.size()`.

### Apply — Find Median from Data Stream

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

### Complexity
- Add number: \(O(\log n)\)
- Find median: \(O(1)\)
- Space: \(O(n)\)

### Practice
- Find Median from Data Stream
- Sliding Window Median
- IPO
- Minimum Cost to Connect Sticks

---

# 4. Linked List Patterns

## 21. Fast & Slow Pointers

### Identify
- Detect cycle in linked list.
- Find middle node.
- Find start of cycle.
- Check palindrome linked list.

### Core Idea
Fast pointer moves 2 steps; slow pointer moves 1 step. If a cycle exists, they meet.

### Apply — Linked List Cycle

```java
public boolean hasCycle(ListNode head) {
    ListNode slow = head, fast = head;

    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;

        if (slow == fast) return true;
    }

    return false;
}
```

### Find Cycle Start

```java
public ListNode detectCycle(ListNode head) {
    ListNode slow = head, fast = head;

    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;

        if (slow == fast) {
            ListNode start = head;

            while (start != slow) {
                start = start.next;
                slow = slow.next;
            }

            return start;
        }
    }

    return null;
}
```

### Complexity
- Time: \(O(n)\)
- Space: \(O(1)\)

### Practice
- Linked List Cycle
- Linked List Cycle II
- Middle of the Linked List
- Palindrome Linked List
- Happy Number

---

## 22. In-place Reversal

### Identify
- Need reverse whole list or part of list.
- Need reverse nodes in K-groups.
- Need reorder list.
- Need reverse between positions.

### Core Idea
Use three pointers: `prev`, `curr`, `next`.

### Template

```java
public ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;

    while (curr != null) {
        ListNode next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }

    return prev;
}
```

### Reverse Between Positions

```java
public ListNode reverseBetween(ListNode head, int left, int right) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev = dummy;

    for (int i = 1; i < left; i++) {
        prev = prev.next;
    }

    ListNode curr = prev.next;

    for (int i = 0; i < right - left; i++) {
        ListNode next = curr.next;
        curr.next = next.next;
        next.next = prev.next;
        prev.next = next;
    }

    return dummy.next;
}
```

### Complexity
- Time: \(O(n)\)
- Space: \(O(1)\)

### Practice
- Reverse Linked List
- Reverse Linked List II
- Reverse Nodes in k-Group
- Reorder List
- Palindrome Linked List

---

## 23. Dummy Node

### Identify
- Head node may change.
- Need deletion at head.
- Need merge lists.
- Need remove nth node from end.
- Need simplify edge cases.

### Core Idea
Create a fake node before head:

```text
dummy -> head -> ...
```

### Apply — Remove Nth Node From End

```java
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;

    ListNode fast = dummy, slow = dummy;

    for (int i = 0; i <= n; i++) {
        fast = fast.next;
    }

    while (fast != null) {
        fast = fast.next;
        slow = slow.next;
    }

    slow.next = slow.next.next;

    return dummy.next;
}
```

### Complexity
- Time: \(O(n)\)
- Space: \(O(1)\)

### Practice
- Remove Nth Node From End
- Merge Two Sorted Lists
- Delete Node in a Linked List
- Add Two Numbers
- Partition List

---

## 24. Merge Two Lists

### Identify
- Two sorted linked lists.
- Need merge without extra list nodes.
- Need merge K sorted lists.
- Need merge sort on linked list.

### Core Idea
Compare current nodes and attach smaller node.

### Apply — Merge Two Sorted Lists

```java
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode curr = dummy;

    while (l1 != null && l2 != null) {
        if (l1.val <= l2.val) {
            curr.next = l1;
            l1 = l1.next;
        } else {
            curr.next = l2;
            l2 = l2.next;
        }

        curr = curr.next;
    }

    curr.next = (l1 != null) ? l1 : l2;

    return dummy.next;
}
```

### Complexity
- Time: \(O(n + m)\)
- Space: \(O(1)\)

### Practice
- Merge Two Sorted Lists
- Merge K Sorted Lists
- Sort List
- Reorder List

---

# 5. Recursion & Backtracking Patterns

## 25. Basic Recursion

### Identify
- Problem can be divided into smaller same-type subproblems.
- Need factorial, Fibonacci, reverse string, power calculation.
- Need tree traversal.

### Core Idea
Define:
1. Base case
2. Recursive case
3. Return combined result

### Template

```java
public int factorial(int n) {
    if (n <= 1) return 1;
    return n * factorial(n - 1);
}
```

### Apply — Reverse String

```java
public void reverseString(char[] s) {
    reverse(s, 0, s.length - 1);
}

private void reverse(char[] s, int left, int right) {
    if (left >= right) return;

    char temp = s[left];
    s[left] = s[right];
    s[right] = temp;

    reverse(s, left + 1, right - 1);
}
```

### Complexity
- Factorial: \(O(n)\), \(O(n)\) recursion stack
- Reverse string: \(O(n)\), \(O(n)\)

### Practice
- Reverse String
- Fibonacci Number
- Pow(x, n)
- Merge Sort
- Binary Tree Inorder Traversal

---

## 26. Include / Exclude

### Identify
- Need subsets or subsequences.
- For each element, choose include or exclude.
- Need generate all combinations.
- Need knapsack-style decision.

### Core Idea

```text
For each element:
1. Include it
2. Exclude it
```

### Apply — Subsets

```java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    backtrack(nums, 0, new ArrayList<>(), ans);
    return ans;
}

private void backtrack(int[] nums, int index,
                       List<Integer> current,
                       List<List<Integer>> ans) {

    if (index == nums.length) {
        ans.add(new ArrayList<>(current));
        return;
    }

    current.add(nums[index]);
    backtrack(nums, index + 1, current, ans);

    current.remove(current.size() - 1);
    backtrack(nums, index + 1, current, ans);
}
```

### Complexity
- Time: \(O(2^n \times n)\)
- Space: \(O(n)\)

### Practice
- Subsets
- Subsets II
- Target Sum
- Partition Equal Subset Sum
- House Robber

---

## 27. Backtracking + For Loop

### Identify
- Need combinations.
- Elements can be reused.
- Need combination sum.
- Need choose from remaining candidates.

### Core Idea
Use a loop starting from `index`. This avoids duplicate combinations.

### Apply — Combination Sum

```java
public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> ans = new ArrayList<>();
    backtrack(candidates, 0, target, new ArrayList<>(), ans);
    return ans;
}

private void backtrack(int[] candidates, int index, int target,
                       List<Integer> current,
                       List<List<Integer>> ans) {

    if (target == 0) {
        ans.add(new ArrayList<>(current));
        return;
    }

    if (target < 0) return;

    for (int i = index; i < candidates.length; i++) {
        current.add(candidates[i]);
        backtrack(candidates, i, target - candidates[i], current, ans);
        current.remove(current.size() - 1);
    }
}
```

### Complexity
- Time: exponential, approximately \(O(k^{target})\)
- Space: \(O(target)\)

### Practice
- Combination Sum
- Combination Sum II
- Combination Sum III
- Letter Combinations of a Phone Number
- Generate Parentheses

---

## 28. Permutations

### Identify
- Order matters.
- Need all arrangements.
- Need swap-based generation.
- Need unique permutations with duplicates.

### Core Idea
At every position, try every unused element.

### Apply — Permutations

```java
public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    boolean[] used = new boolean[nums.length];
    backtrack(nums, new ArrayList<>(), used, ans);
    return ans;
}

private void backtrack(int[] nums, List<Integer> current,
                       boolean[] used,
                       List<List<Integer>> ans) {

    if (current.size() == nums.length) {
        ans.add(new ArrayList<>(current));
        return;
    }

    for (int i = 0; i < nums.length; i++) {
        if (used[i]) continue;

        used[i] = true;
        current.add(nums[i]);

        backtrack(nums, current, used, ans);

        used[i] = false;
        current.remove(current.size() - 1);
    }
}
```

### Complexity
- Time: \(O(n! \times n)\)
- Space: \(O(n)\)

### Practice
- Permutations
- Permutations II
- Next Permutation
- Letter Case Permutation
- Beautiful Arrangement

---

## 29. Constraint-Based Backtracking

### Identify
- Need place items under constraints.
- N-Queens, Sudoku, word search.
- Need undo choices.
- Need explore all valid configurations.

### Core Idea

```text
choose -> validate -> recurse -> unchoose
```

### Apply — N-Queens

```java
public List<List<String>> solveNQueens(int n) {
    List<List<String>> ans = new ArrayList<>();
    char[][] board = new char[n][n];

    for (char[] row : board) {
        Arrays.fill(row, '.');
    }

    backtrack(board, 0, ans);
    return ans;
}

private void backtrack(char[][] board, int row,
                       List<List<String>> ans) {

    if (row == board.length) {
        ans.add(construct(board));
        return;
    }

    for (int col = 0; col < board.length; col++) {
        if (isValid(board, row, col)) {
            board[row][col] = 'Q';
            backtrack(board, row + 1, ans);
            board[row][col] = '.';
        }
    }
}

private boolean isValid(char[][] board, int row, int col) {
    for (int i = 0; i < row; i++) {
        if (board[i][col] == 'Q') return false;
    }

    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
        if (board[i][j] == 'Q') return false;
    }

    for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
        if (board[i][j] == 'Q') return false;
    }

    return true;
}

private List<String> construct(char[][] board) {
    List<String> result = new ArrayList<>();

    for (char[] row : board) {
        result.add(new String(row));
    }

    return result;
}
```

### Complexity
- Time: exponential, approximately \(O(n!)\)
- Space: \(O(n^2)\)

### Practice
- N-Queens
- Sudoku Solver
- Word Search
- Rat in a Maze
- Palindrome Partitioning

---

# 6. Trees & Binary Search Trees

## 30. Tree DFS

### Identify
- Need path-based calculation.
- Need depth, height, subtree information.
- Need validate tree properties.
- Need traverse root-left-right or root-right-left.

### Core Idea
DFS recursion:

```text
solve(node):
    base case
    solve(left)
    solve(right)
    combine results
```

### Apply — Maximum Depth

```java
public int maxDepth(TreeNode root) {
    if (root == null) return 0;

    int left = maxDepth(root.left);
    int right = maxDepth(root.right);

    return 1 + Math.max(left, right);
}
```

### Complexity
- Time: \(O(n)\)
- Space: \(O(h)\), where `h` is tree height

### Practice
- Maximum Depth of Binary Tree
- Same Tree
- Symmetric Tree
- Path Sum
- Binary Tree Maximum Path Sum

---

## 31. Tree BFS / Level Order

### Identify
- Need process nodes level by level.
- Need right/left view.
- Need minimum depth.
- Need zigzag traversal.
- Need connect next pointers.

### Apply — Level Order Traversal

```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> ans = new ArrayList<>();

    if (root == null) return ans;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
        int size = queue.size();
        List<Integer> level = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
            level.add(node.val);

            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

        ans.add(level);
    }

    return ans;
}
```

### Complexity
- Time: \(O(n)\)
- Space: \(O(w)\), where `w` is maximum width

### Practice
- Binary Tree Level Order Traversal
- Binary Tree Zigzag Level Order Traversal
- Binary Tree Right Side View
- Minimum Depth of Binary Tree
- Populating Next Right Pointers

---

## 32. BST Property

### Identify
- Tree is BST.
- Need search, insert, delete, validate.
- Need kth smallest/largest.
- Need inorder sorted order.

### Core BST Rule

```text
left subtree < node < right subtree
```

Inorder traversal gives sorted values.

### Apply — Validate BST

Use range limits.

```java
public boolean isValidBST(TreeNode root) {
    return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
}

private boolean validate(TreeNode node, long min, long max) {
    if (node == null) return true;

    if (node.val <= min || node.val >= max) return false;

    return validate(node.left, min, node.val) &&
           validate(node.right, node.val, max);
}
```

### Apply — Kth Smallest

```java
public int kthSmallest(TreeNode root, int k) {
    Deque<TreeNode> stack = new ArrayDeque<>();
    TreeNode curr = root;

    while (curr != null || !stack.isEmpty()) {
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }

        curr = stack.pop();

        if (--k == 0) return curr.val;

        curr = curr.right;
    }

    return -1;
}
```

### Complexity
- Validate BST: \(O(n)\)
- Kth smallest: \(O(h + k)\)

### Practice
- Validate Binary Search Tree
- Kth Smallest Element in a BST
- Lowest Common Ancestor of a BST
- Insert into a BST
- Delete Node in a BST

---

## 33. Tree Path / Diameter

### Identify
- Need longest path between nodes.
- Need path sum.
- Need maximum path sum.
- Need subtree contribution.

### Core Idea
For every node, compute:
- Best downward path through left child
- Best downward path through right child
- Combine both for answer

### Apply — Diameter of Binary Tree

```java
public int diameterOfBinaryTree(TreeNode root) {
    int[] diameter = new int;
    height(root, diameter);
    return diameter;
}

private int height(TreeNode node, int[] diameter) {
    if (node == null) return 0;

    int left = height(node.left, diameter);
    int right = height(node.right, diameter);

    diameter = Math.max(diameter, left + right);

    return 1 + Math.max(left, right);
}
```

### Complexity
- Time: \(O(n)\)
- Space: \(O(h)\)

### Practice
- Diameter of Binary Tree
- Binary Tree Maximum Path Sum
- Path Sum III
- Longest ZigZag Path
- Binary Tree Cameras

---

# 7. Graph Patterns

## 34. Graph DFS

### Identify
- Need explore connected components.
- Need flood fill.
- Need detect paths.
- Need count islands.
- Need traverse all reachable nodes.

### Core Idea
Visit node, mark visited, recursively visit neighbors.

### Apply — Number of Islands

```java
public int numIslands(char[][] grid) {
    int count = 0;
    int rows = grid.length, cols = grid.length;

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (grid[i][j] == '1') {
                count++;
                dfs(grid, i, j);
            }
        }
    }

    return count;
}

private void dfs(char[][] grid, int i, int j) {
    if (i < 0 || i >= grid.length ||
        j < 0 || j >= grid.length ||
        grid[i][j] != '1') {
        return;
    }

    grid[i][j] = '0';

    dfs(grid, i + 1, j);
    dfs(grid, i - 1, j);
    dfs(grid, i, j + 1);
    dfs(grid, i, j - 1);
}
```

### Complexity
- Time: \(O(rows \times cols)\)
- Space: \(O(rows \times cols)\)

### Practice
- Number of Islands
- Flood Fill
- Max Area of Island
- Surrounded Regions
- Number of Distinct Islands

---

## 35. Graph BFS / Shortest Path

### Identify
- Graph is **unweighted**.
- Need shortest path or minimum moves.
- Need nearest source.
- Need level-by-level traversal.

### Core Idea
BFS gives shortest distance in unweighted graphs because it explores nodes in increasing distance order.

### Apply — Shortest Path in Unweighted Graph

```java
public int shortestPath(int n, List<List<Integer>> adj,
                        int source, int destination) {

    int[] distance = new int[n];
    Arrays.fill(distance, -1);

    Queue<Integer> queue = new LinkedList<>();
    queue.offer(source);
    distance[source] = 0;

    while (!queue.isEmpty()) {
        int node = queue.poll();

        for (int neighbor : adj.get(node)) {
            if (distance[neighbor] == -1) {
                distance[neighbor] = distance[node] + 1;
                queue.offer(neighbor);
            }
        }
    }

    return distance[destination];
}
```

### Complexity
- Time: \(O(V + E)\)
- Space: \(O(V)\)

### Practice
- Shortest Path in Binary Matrix
- Word Ladder
- Open the Lock
- 01 Matrix
- Nearest Exit from Entrance in Maze

---

## 36. Cycle Detection

### Identify
- Need detect cycle in directed or undirected graph.
- Need check if graph is valid tree.
- Need detect deadlock.
- Course Schedule prerequisites.

### Directed Graph — DFS with Colors

```text
0 = unvisited
1 = visiting
2 = visited
```

If we reach a node with state `1`, cycle exists.

```java
public boolean hasCycle(int n, List<List<Integer>> adj) {
    int[] state = new int[n];

    for (int i = 0; i < n; i++) {
        if (state[i] == 0 && dfs(i, adj, state)) {
            return true;
        }
    }

    return false;
}

private boolean dfs(int node, List<List<Integer>> adj, int[] state) {
    state[node] = 1;

    for (int neighbor : adj.get(node)) {
        if (state[neighbor] == 1) return true;

        if (state[neighbor] == 0 && dfs(neighbor, adj, state)) {
            return true;
        }
    }

    state[node] = 2;
    return false;
}
```

### Undirected Graph — Parent Tracking

```java
public boolean hasCycleUndirected(int node, int parent,
                                  List<List<Integer>> adj,
                                  boolean[] visited) {

    visited[node] = true;

    for (int neighbor : adj.get(node)) {
        if (!visited[neighbor]) {
            if (hasCycleUndirected(neighbor, node, adj, visited)) {
                return true;
            }
        } else if (neighbor != parent) {
            return true;
        }
    }

    return false;
}
```

### Complexity
- Time: \(O(V + E)\)
- Space: \(O(V)\)

### Practice
- Course Schedule
- Course Schedule II
- Detect Cycle in Undirected Graph
- Redundant Connection
- Find Eventual Safe States

---

## 37. Topological Sort

### Identify
- Directed acyclic graph.
- Need ordering of tasks with dependencies.
- Course Schedule.
- Build order, compilation order.
- Need detect cycle using Kahn’s algorithm.

### Core Idea
Repeatedly remove nodes with indegree `0`.

### Apply — Course Schedule

```java
public boolean canFinish(int numCourses, int[][] prerequisites) {
    List<List<Integer>> adj = new ArrayList<>();

    for (int i = 0; i < numCourses; i++) {
        adj.add(new ArrayList<>());
    }

    int[] indegree = new int[numCourses];

    for (int[] prerequisite : prerequisites) {
        int course = prerequisite;
        int prereq = prerequisite;

        adj.get(prereq).add(course);
        indegree[course]++;
    }

    Queue<Integer> queue = new LinkedList<>();

    for (int i = 0; i < numCourses; i++) {
        if (indegree[i] == 0) queue.offer(i);
    }

    int processed = 0;

    while (!queue.isEmpty()) {
        int course = queue.poll();
        processed++;

        for (int next : adj.get(course)) {
            indegree[next]--;

            if (indegree[next] == 0) {
                queue.offer(next);
            }
        }
    }

    return processed == numCourses;
}
```

### Complexity
- Time: \(O(V + E)\)
- Space: \(O(V + E)\)

### Practice
- Course Schedule
- Course Schedule II
- Alien Dictionary
- Minimum Height Trees
- Sequence Reconstruction

---

## 38. Union-Find / DSU

### Identify
- Need dynamic connectivity.
- Need merge groups/components.
- Need cycle detection in undirected graph.
- Need number of provinces/components.
- Need accounts merge.

### Core Idea
DSU supports:
- `find(x)`: find representative/root
- `union(x, y)`: merge two components

Use path compression and union by size/rank.

### Template

```java
class DSU {
    int[] parent;
    int[] size;

    public DSU(int n) {
        parent = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return false;

        if (size[rootX] < size[rootY]) {
            int temp = rootX;
            rootX = rootY;
            rootY = temp;
        }

        parent[rootY] = rootX;
        size[rootX] += size[rootY];

        return true;
    }
}
```

### Apply — Number of Provinces

```java
public int findCircleNum(int[][] isConnected) {
    int n = isConnected.length;
    DSU dsu = new DSU(n);

    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            if (isConnected[i][j] == 1) {
                dsu.union(i, j);
            }
        }
    }

    Set<Integer> components = new HashSet<>();

    for (int i = 0; i < n; i++) {
        components.add(dsu.find(i));
    }

    return components.size();
}
```

### Complexity
- With path compression and union by size: nearly \(O(\alpha(n))\), effectively constant
- Space: \(O(n)\)

### Practice
- Number of Provinces
- Redundant Connection
- Accounts Merge
- Number of Operations to Make Network Connected
- Most Stones Removed with Same Row or Column

---

## 39. Multi-Source BFS

### Identify
- Multiple starting points.
- Need nearest distance from any source.
- Rotting oranges, fire spread, 01 matrix.
- Need simultaneous expansion.

### Core Idea
Push all sources into queue initially, then perform BFS.

### Apply — 01 Matrix

```java
public int[][] updateMatrix(int[][] mat) {
    int rows = mat.length, cols = mat.length;
    int[][] distance = new int[rows][cols];
    Queue<int[]> queue = new LinkedList<>();

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (mat[i][j] == 0) {
                queue.offer(new int[]{i, j});
            } else {
                distance[i][j] = -1;
            }
        }
    }

    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    while (!queue.isEmpty()) {
        int[] cell = queue.poll();

        for (int[] dir : directions) {
            int r = cell + dir;
            int c = cell + dir;

            if (r >= 0 && r < rows && c >= 0 && c < cols &&
                distance[r][c] == -1) {

                distance[r][c] = distance[cell][cell] + 1;
                queue.offer(new int[]{r, c});
            }
        }
    }

    return distance;
}
```

### Complexity
- Time: \(O(rows \times cols)\)
- Space: \(O(rows \times cols)\)

### Practice
- Rotting Oranges
- 01 Matrix
- Walls and Gates
- As Far from Land as Possible
- Map of Highest Peak

---

## 40. Dijkstra’s Algorithm

### Identify
- Weighted graph with **non-negative** weights.
- Need shortest path from one source.
- Network delay time.
- Cheapest flights with constraints.
- Path with minimum effort.

### Core Idea
Always process the node with the smallest current distance.

### Apply — Network Delay Time

```java
public int networkDelayTime(int[][] times, int n, int k) {
    List<List<int[]>> adj = new ArrayList<>();

    for (int i = 0; i <= n; i++) {
        adj.add(new ArrayList<>());
    }

    for (int[] time : times) {
        adj.get(time).add(new int[]{time, time});
    }

    int[] distance = new int[n + 1];
    Arrays.fill(distance, Integer.MAX_VALUE);
    distance[k] = 0;

    PriorityQueue<int[]> pq =
        new PriorityQueue<>((a, b) -> a - b);

    pq.offer(new int[]{k, 0});

    while (!pq.isEmpty()) {
        int[] current = pq.poll();
        int node = current;
        int dist = current;

        if (dist > distance[node]) continue;

        for (int[] neighbor : adj.get(node)) {
            int nextNode = neighbor;
            int weight = neighbor;

            if (distance[node] + weight < distance[nextNode]) {
                distance[nextNode] = distance[node] + weight;
                pq.offer(new int[]{nextNode, distance[nextNode]});
            }
        }
    }

    int answer = 0;

    for (int i = 1; i <= n; i++) {
        if (distance[i] == Integer.MAX_VALUE) return -1;
        answer = Math.max(answer, distance[i]);
    }

    return answer;
}
```

### Complexity
- Time: \(O((V + E) \log V)\)
- Space: \(O(V + E)\)

### Important
Dijkstra does **not** work with negative weights. Use Bellman-Ford for negative weights.

### Practice
- Network Delay Time
- Cheapest Flights Within K Stops
- Path with Minimum Effort
- Path with Maximum Probability
- Swim in Rising Water

---

# Pattern Selection Cheat Sheet

| If problem says… | Think pattern |
|---|---|
| Sorted array, pair/triplet | Two Pointers |
| Contiguous subarray/substring | Sliding Window |
| Range sum | Prefix Sum |
| Range update | Difference Array |
| Sorted search space | Binary Search |
| Minimum max / maximum min | Binary Search on Answer |
| Values 1 to n, O(1) space | Cyclic Sort |
| Maximum subarray sum | Kadane’s |
| Frequency, duplicates, pairs | HashMap |
| Unique elements, membership | HashSet |
| Limited character range | Frequency Array |
| Once/twice/thrice, XOR | Bit Manipulation |
| Next greater/smaller | Monotonic Stack |
| Window max/min | Monotonic Deque |
| Matching brackets | Stack |
| Level-wise / shortest unweighted | BFS Queue |
| K largest/smallest | Heap |
| Running median | Two Heaps |
| Cycle/middle in linked list | Fast & Slow |
| Reverse linked list | In-place Reversal |
| Head may change | Dummy Node |
| Merge sorted lists | Merge Pattern |
| All subsets | Include/Exclude |
| Combinations with reuse | Backtracking + For Loop |
| All arrangements | Permutations |
| Constraints/placement | Constraint Backtracking |
| Tree depth/path | DFS |
| Tree levels | BFS |
| BST search/order | BST Property |
| Longest tree path | Diameter Pattern |
| Connected components | DFS / DSU |
| Shortest unweighted path | BFS |
| Task dependency | Topological Sort |
| Dynamic connectivity | DSU |
| Multiple sources | Multi-Source BFS |
| Weighted shortest path | Dijkstra |

---

# Final Revision Order

1. Two Pointers  
2. Sliding Window  
3. Prefix Sum  
4. Binary Search  
5. Binary Search on Answer  
6. HashMap / HashSet  
7. Stack / Monotonic Stack  
8. Linked List Pointers  
9. Recursion & Backtracking  
10. Tree DFS / BFS  
11. Graph DFS / BFS  
12. Topological Sort  
13. DSU  
14. Dijkstra  
15. Heap / Two Heaps  

Practice each pattern with 5–8 problems, then solve mixed-pattern sets without looking at tags.