# 🪟 Sliding Window - Complete Guide

## 📚 Theory

### What is Sliding Window?

**Sliding Window** is an optimization technique that converts nested loops into a single loop. It's used when we need to find a contiguous subarray/substring that satisfies certain conditions.

### When to Use Sliding Window?

Use sliding window when:
1. Problem asks for **longest/shortest** subarray/substring
2. Problem involves **contiguous** elements
3. Given constraints on **window size** or **sum/product**
4. Problem mentions **subarray** or **substring**

## Types of Sliding Window

# 1. Fixed Size Window

* Window size is predetermined (k)

* Slide window one element at a time

* Example: Maximum sum subarray of size k

# 2. Variable Size Window

* Window size changes based on condition

* Expand right, contract left

* Example: Longest substring without repeating


### Sliding Window Template

```java
// Fixed Size Window
int fixedSizeWindow(int[] arr, int k) {
    int i = 0, j = 0;
    int windowSum = 0;
    int result = 0;
    
    while (j < arr.length) {
        // Add current element
        windowSum += arr[j];
        
        // If window size reached
        if (j - i + 1 == k) {
            // Update result
            result = Math.max(result, windowSum);
            
            // Remove left element
            windowSum -= arr[i];
            i++;
        }
        
        j++;
    }
    
    return result;
}

// Variable Size Window
int variableSizeWindow(int[] arr, int target) {
    int i = 0, j = 0;
    int windowSum = 0;
    int result = Integer.MAX_VALUE;
    
    while (j < arr.length) {
        // Add current element
        windowSum += arr[j];
        
        // While condition met, shrink window
        while (windowSum >= target) {
            result = Math.min(result, j - i + 1);
            windowSum -= arr[i];
            i++;
        }
        
        j++;
    }
    
    return result;
}
```

---

## 💻 Java Code Examples

### Problem 1: Maximum Sum Subarray of Size K

**Question:** Given an array of integers and a number k, find the maximum sum of any contiguous subarray of size k.

```java
/**
 * Problem: Maximum Sum Subarray of Size K
 * GeeksforGeeks: Max Sum Subarray of Size K
 * 
 * Visual:
 * arr =, k = 2[100][200][300][400]
 * 
 * Sliding Window Approach:
 * 
 * Window 1:[100][200]
 * Sum = 300
 * Max = 300
 * 
 * Slide →
 * 
 * Window 2:[200][300]
 * Sum = 500
 * Max = 500
 * 
 * Slide →
 * 
 * Window 3:[300][400]
 * Sum = 700
 * Max = 700 ✓
 * 
 * Result: 700
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class MaxSumSubarraySizeK {
    
    // Approach 1: Brute Force - O(n × k)
    public static int maxSumBruteForce(int[] arr, int k) {
        int maxSum = Integer.MIN_VALUE;
        
        for (int i = 0; i <= arr.length - k; i++) {
            int currentSum = 0;
            
            for (int j = i; j < i + k; j++) {
                currentSum += arr[j];
            }
            
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }
    
    // Approach 2: Sliding Window - O(n)
    public static int maxSumSlidingWindow(int[] arr, int k) {
        int n = arr.length;
        
        // Edge case
        if (n < k) {
            System.out.println("Invalid: Array size < k");
            return -1;
        }
        
        // Compute sum of first window
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        
        int maxSum = windowSum;
        
        // Slide window and update max
        for (int i = k; i < n; i++) {
            // Add new element, remove old element
            windowSum += arr[i] - arr[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }
        
        return maxSum;
    }
    
    // Approach 3: Two Pointer Sliding Window
    public static int maxSumTwoPointer(int[] arr, int k) {
        int i = 0, j = 0;
        int windowSum = 0;
        int maxSum = Integer.MIN_VALUE;
        
        while (j < arr.length) {
            // Add current element
            windowSum += arr[j];
            
            // If window size reached
            if (j - i + 1 == k) {
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= arr[i];
                i++;
            }
            
            j++;
        }
        
        return maxSum;
    }
    
    // Variation: Maximum Average Subarray of Size K
    // LeetCode: 643
    public static double findMaxAverage(int[] arr, int k) {
        int windowSum = 0;
        
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        
        int maxSum = windowSum;
        
        for (int i = k; i < arr.length; i++) {
            windowSum += arr[i] - arr[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }
        
        return (double) maxSum / k;
    }
    
    public static void main(String[] args) {
        int[] arr = {100, 200, 300, 400};
        int k = 2;
        
        System.out.println("Array: " + java.util.Arrays.toString(arr));
        System.out.println("k = " + k);
        
        System.out.println("\nMaximum sum (Brute Force): " + maxSumBruteForce(arr, k));
        System.out.println("Maximum sum (Sliding Window): " + maxSumSlidingWindow(arr, k));
        System.out.println("Maximum sum (Two Pointer): " + maxSumTwoPointer(arr, k));
        
        // Average
        int[] arr2 = {1, 12, -5, -6, 50, 3};
        k = 4;
        System.out.println("\nArray: " + java.util.Arrays.toString(arr2));
        System.out.println("Maximum average: " + String.format("%.2f", findMaxAverage(arr2, k)));
    }
}
```

---

### Problem 2: Longest Subarray with Sum K

**Question:** Given an array of integers and a target sum k, find the length of the longest subarray with sum equal to k.

```java
/**
 * Problem: Longest Subarray with Sum K
 * GeeksforGeeks: Longest Subarray with Sum K
 * 
 * Visual:
 * arr =, k = 15[1][2][5][7][9][10]
 * 
 * Sliding Window Approach (for positive numbers):
 * 
 * Expand window until sum ≥ k
 * Contract window when sum > k
 * Update result when sum = k
 * 
 * i=0, j=0: sum=10, 10<15, expand
 * i=0, j=1: sum=15, 15==15 ✓, maxLen=2
 * i=0, j=2: sum=17, 17>15, contract
 * i=1, j=2: sum=7, 7<15, expand
 * i=1, j=3: sum=14, 14<15, expand
 * i=1, j=4: sum=15, 15==15 ✓, maxLen=4
 * i=1, j=5: sum=24, 24>15, contract
 * ...
 * 
 * Result: 4 (subarray )[1][2][5][7]
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class LongestSubarrayWithSumK {
    
    // Approach 1: Sliding Window (for positive numbers only)
    public static int longestSubarrayPositive(int[] arr, int k) {
        int i = 0, j = 0;
        int currentSum = 0;
        int maxLen = 0;
        
        while (j < arr.length) {
            // Add current element
            currentSum += arr[j];
            
            // Contract window if sum exceeds k
            while (currentSum > k && i <= j) {
                currentSum -= arr[i];
                i++;
            }
            
            // Update max length if sum equals k
            if (currentSum == k) {
                maxLen = Math.max(maxLen, j - i + 1);
            }
            
            j++;
        }
        
        return maxLen;
    }
    
    // Approach 2: HashMap (works for negative numbers too)
    // LeetCode: 325 (similar)
    public static int longestSubarray(int[] arr, int k) {
        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();
        int currentSum = 0;
        int maxLen = 0;
        
        for (int j = 0; j < arr.length; j++) {
            currentSum += arr[j];
            
            // If sum equals k
            if (currentSum == k) {
                maxLen = Math.max(maxLen, j + 1);
            }
            
            // If (currentSum - k) exists in map
            if (map.containsKey(currentSum - k)) {
                maxLen = Math.max(maxLen, j - map.get(currentSum - k));
            }
            
            // Store currentSum with its index
            if (!map.containsKey(currentSum)) {
                map.put(currentSum, j);
            }
        }
        
        return maxLen;
    }
    
    // Variation: Count Subarrays with Sum K
    // LeetCode: 560
    public static int countSubarraysWithSumK(int[] arr, int k) {
        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();
        map.put(0, 1); // Base case
        
        int currentSum = 0;
        int count = 0;
        
        for (int num : arr) {
            currentSum += num;
            
            if (map.containsKey(currentSum - k)) {
                count += map.get(currentSum - k);
            }
            
            map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        int[] arr = {10, 5, 2, 7, 1, 9};
        int k = 15;
        
        System.out.println("Array: " + java.util.Arrays.toString(arr));
        System.out.println("Target sum: " + k);
        
        System.out.println("\nLongest subarray (Positive only): " + 
                          longestSubarrayPositive(arr, k));
        System.out.println("Longest subarray (With HashMap): " + 
                          longestSubarray(arr, k));
        
        // Count subarrays
        int[] arr2 = {1, 1, 1};
        k = 2;
        System.out.println("\nArray: " + java.util.Arrays.toString(arr2));
        System.out.println("Count of subarrays with sum " + k + ": " + 
                          countSubarraysWithSumK(arr2, k));
    }
}
```

---

### Problem 3: Longest Substring Without Repeating Characters

**Question:** Given a string s, find the length of the longest substring without repeating characters.

```java
/**
 * Problem: Longest Substring Without Repeating Characters
 * LeetCode: 3
 * 
 * Visual:
 * s = "abcabcbb"
 * 
 * Sliding Window Approach:
 * 
 * i=0, j=0: "a", no repeat, maxLen=1
 * i=0, j=1: "ab", no repeat, maxLen=2
 * i=0, j=2: "abc", no repeat, maxLen=3
 * i=0, j=3: "abca", 'a' repeats!
 *            Contract from left until no repeat
 * i=1, j=3: "bca", no repeat, maxLen=3
 * i=1, j=4: "bcab", 'b' repeats!
 * i=2, j=4: "cab", no repeat, maxLen=3
 * i=2, j=5: "cabc", 'c' repeats!
 * i=3, j=5: "abc", no repeat, maxLen=3
 * i=3, j=6: "abcb", 'b' repeats!
 * i=4, j=6: "bcb", 'b' repeats!
 * i=5, j=6: "cb", no repeat, maxLen=3
 * i=5, j=7: "cbb", 'b' repeats!
 * i=6, j=7: "bb", 'b' repeats!
 * i=7, j=7: "b", no repeat, maxLen=3
 * 
 * Result: 3 (substring "abc")
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(min(m, n)) where m is charset size
 */
public class LongestSubstringWithoutRepeating {
    
    // Approach 1: Sliding Window with HashSet
    public static int lengthOfLongestSubstring(String s) {
        java.util.Set<Character> set = new java.util.HashSet<>();
        int i = 0, j = 0;
        int maxLen = 0;
        
        while (j < s.length()) {
            char c = s.charAt(j);
            
            // If character already in window, remove from left
            while (set.contains(c)) {
                set.remove(s.charAt(i));
                i++;
            }
            
            // Add current character
            set.add(c);
            
            // Update max length
            maxLen = Math.max(maxLen, j - i + 1);
            
            j++;
        }
        
        return maxLen;
    }
    
    // Approach 2: Sliding Window with HashMap (optimized)
    public static int lengthOfLongestSubstringOptimized(String s) {
        java.util.Map<Character, Integer> map = new java.util.HashMap<>();
        int i = 0, j = 0;
        int maxLen = 0;
        
        while (j < s.length()) {
            char c = s.charAt(j);
            
            // If character seen, move left pointer
            if (map.containsKey(c)) {
                i = Math.max(i, map.get(c) + 1);
            }
            
            // Update character's last seen position
            map.put(c, j);
            
            // Update max length
            maxLen = Math.max(maxLen, j - i + 1);
            
            j++;
        }
        
        return maxLen;
    }
    
    // Approach 3: Sliding Window with Array (most optimized for ASCII)
    public static int lengthOfLongestSubstringArray(String s) {
        int[] lastIndex = new int; // ASCII characters
        java.util.Arrays.fill(lastIndex, -1);
        
        int i = 0, j = 0;
        int maxLen = 0;
        
        while (j < s.length()) {
            char c = s.charAt(j);
            
            // If character seen, move left pointer
            if (lastIndex[c] >= i) {
                i = lastIndex[c] + 1;
            }
            
            // Update last seen position
            lastIndex[c] = j;
            
            // Update max length
            maxLen = Math.max(maxLen, j - i + 1);
            
            j++;
        }
        
        return maxLen;
    }
    
    // Return the actual substring
    public static String longestSubstring(String s) {
        java.util.Map<Character, Integer> map = new java.util.HashMap<>();
        int i = 0, j = 0;
        int maxLen = 0;
        int maxStart = 0;
        
        while (j < s.length()) {
            char c = s.charAt(j);
            
            if (map.containsKey(c)) {
                i = Math.max(i, map.get(c) + 1);
            }
            
            map.put(c, j);
            
            if (j - i + 1 > maxLen) {
                maxLen = j - i + 1;
                maxStart = i;
            }
            
            j++;
        }
        
        return s.substring(maxStart, maxStart + maxLen);
    }
    
    public static void main(String[] args) {
        String s = "abcabcbb";
        
        System.out.println("String: \"" + s + "\"");
        
        System.out.println("\nLength (HashSet): " + lengthOfLongestSubstring(s));
        System.out.println("Length (HashMap): " + lengthOfLongestSubstringOptimized(s));
        System.out.println("Length (Array): " + lengthOfLongestSubstringArray(s));
        
        System.out.println("Longest substring: \"" + longestSubstring(s) + "\"");
        
        // More examples
        String[] testCases = {"bbbbb", "pwwkew", "", "abcdef"};
        System.out.println("\nMore examples:");
        for (String test : testCases) {
            System.out.println("\"" + test + "\" → " + 
                              lengthOfLongestSubstringOptimized(test));
        }
    }
}
```

---

### Problem 4: Minimum Size Subarray Sum

**Question:** Given an array of positive integers and a target sum, find the minimal length of a contiguous subarray with sum ≥ target.

```java
/**
 * Problem: Minimum Size Subarray Sum
 * LeetCode: 209
 * 
 * Visual:
 * arr =, target = 7[1][2][3][4]
 * 
 * Sliding Window Approach:
 * 
 * Expand until sum ≥ target
 * Contract to find minimum length
 * 
 * i=0, j=0: sum=2, 2<7, expand
 * i=0, j=1: sum=5, 5<7, expand
 * i=0, j=2: sum=6, 6<7, expand
 * i=0, j=3: sum=8, 8≥7 ✓, minLen=4
 *            Contract to minimize
 * i=1, j=3: sum=6, 6<7, expand
 * i=1, j=4: sum=10, 10≥7 ✓, minLen=min(4,4)=4
 *            Contract
 * i=2, j=4: sum=7, 7≥7 ✓, minLen=min(4,3)=3
 *            Contract
 * i=3, j=4: sum=6, 6<7, expand
 * i=3, j=5: sum=9, 9≥7 ✓, minLen=min(3,3)=3
 *            Contract
 * i=4, j=5: sum=7, 7≥7 ✓, minLen=min(3,2)=2 ✓
 * 
 * Result: 2 (subarray )[3][4]
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class MinimumSizeSubarraySum {
    
    // Approach 1: Sliding Window
    public static int minSubArrayLen(int[] arr, int target) {
        int i = 0, j = 0;
        int currentSum = 0;
        int minLen = Integer.MAX_VALUE;
        
        while (j < arr.length) {
            // Add current element
            currentSum += arr[j];
            
            // Contract while sum ≥ target
            while (currentSum >= target) {
                minLen = Math.min(minLen, j - i + 1);
                currentSum -= arr[i];
                i++;
            }
            
            j++;
        }
        
        return (minLen == Integer.MAX_VALUE) ? 0 : minLen;
    }
    
    // Approach 2: Prefix Sum + Binary Search - O(n log n)
    public static int minSubArrayLenBinarySearch(int[] arr, int target) {
        int n = arr.length;
        
        // Build prefix sum array
        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + arr[i];
        }
        
        int minLen = Integer.MAX_VALUE;
        
        // For each starting position, use binary search
        for (int i = 0; i < n; i++) {
            int left = i, right = n;
            
            while (left < right) {
                int mid = left + (right - left) / 2;
                
                if (prefixSum[mid + 1] - prefixSum[i] >= target) {
                    minLen = Math.min(minLen, mid - i + 1);
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
        }
        
        return (minLen == Integer.MAX_VALUE) ? 0 : minLen;
    }
    
    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 2, 4, 3};
        int target = 7;
        
        System.out.println("Array: " + java.util.Arrays.toString(arr));
        System.out.println("Target: " + target);
        
        System.out.println("\nMinimum length (Sliding Window): " + 
                          minSubArrayLen(arr, target));
        System.out.println("Minimum length (Binary Search): " + 
                          minSubArrayLenBinarySearch(arr, target));
        
        // More examples
        int[][] testCases = {
            {1, 4, 4},
            {1, 1, 1, 1, 1, 1, 1, 1}
        };
        int[] targets = {4, 11};
        
        System.out.println("\nMore examples:");
        for (int i = 0; i < testCases.length; i++) {
            System.out.println(java.util.Arrays.toString(testCases[i]) + 
                              ", target=" + targets[i] + " → " + 
                              minSubArrayLen(testCases[i], targets[i]));
        }
    }
}
```

---

### Problem 5: Maximum Points from Cards

**Question:** There are several cards arranged in a row. In one step, you can take one card from either beginning or end. Find maximum points you can obtain by taking exactly k cards.

```java
/**
 * Problem: Maximum Points You Can Obtain from Cards
 * LeetCode: 1423
 * 
 * Visual:
 * cardPoints =, k = 3[1][2][3][4][5][6]
 * 
 * Sliding Window Approach (Find minimum sum subarray of size n-k):
 * 
 * Total sum = 1+2+3+4+5+6+1 = 22
 * We need to leave (n-k) = 4 cards
 * Find minimum sum subarray of size 4
 * 
 * Window 1:, sum = 10[1][2][3][4]
 * Window 2:, sum = 14[2][3][4][5]
 * Window 3:, sum = 18[3][4][5][6]
 * Window 4:, sum = 16[1][4][5][6]
 * 
 * Minimum = 10
 * Maximum points = Total - Minimum = 22 - 10 = 12
 * 
 * Alternative: Take k cards from ends
 * Take 3 from left:  = 6[1][2][3]
 * Take 2 from left, 1 from right:  = 4[2][1]
 * Take 1 from left, 2 from right:  = 8[1][6]
 * Take 3 from right:  = 8[1][6]
 * 
 * Maximum = 12 (take  from left and  from right)[1][2][3][6]
 * 
 * Time Complexity: O(k) or O(n)
 * Space Complexity: O(1)
 */
public class MaximumPointsFromCards {
    
    // Approach 1: Sliding Window (Find minimum middle subarray)
    public static int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int windowSize = n - k;
        
        // Calculate total sum
        int totalSum = 0;
        for (int point : cardPoints) {
            totalSum += point;
        }
        
        // If we need to take all cards
        if (windowSize == 0) {
            return totalSum;
        }
        
        // Find minimum sum subarray of size (n-k)
        int windowSum = 0;
        for (int i = 0; i < windowSize; i++) {
            windowSum += cardPoints[i];
        }
        
        int minWindowSum = windowSum;
        
        for (int i = windowSize; i < n; i++) {
            windowSum += cardPoints[i] - cardPoints[i - windowSize];
            minWindowSum = Math.min(minWindowSum, windowSum);
        }
        
        return totalSum - minWindowSum;
    }
    
    // Approach 2: Take k cards from ends
    public static int maxScoreEnds(int[] cardPoints, int k) {
        int n = cardPoints.length;
        
        // Take all k from left
        int leftSum = 0;
        for (int i = 0; i < k; i++) {
            leftSum += cardPoints[i];
        }
        
        int maxScore = leftSum;
        
        // Try taking i from left and (k-i) from right
        for (int i = 1; i <= k; i++) {
            leftSum -= cardPoints[k - i];
            int rightSum = 0;
            
            for (int j = 0; j < i; j++) {
                rightSum += cardPoints[n - j - 1];
            }
            
            maxScore = Math.max(maxScore, leftSum + rightSum);
        }
        
        return maxScore;
    }
    
    // Approach 3: Optimized two ends
    public static int maxScoreOptimized(int[] cardPoints, int k) {
        int n = cardPoints.length;
        
        // Take all k from left initially
        int currentScore = 0;
        for (int i = 0; i < k; i++) {
            currentScore += cardPoints[i];
        }
        
        int maxScore = currentScore;
        
        // Replace left cards with right cards one by one
        for (int i = 1; i <= k; i++) {
            currentScore -= cardPoints[k - i];
            currentScore += cardPoints[n - i];
            maxScore = Math.max(maxScore, currentScore);
        }
        
        return maxScore;
    }
    
    public static void main(String[] args) {
        int[] cardPoints = {1, 2, 3, 4, 5, 6, 1};
        int k = 3;
        
        System.out.println("Card Points: " + java.util.Arrays.toString(cardPoints));
        System.out.println("k = " + k);
        
        System.out.println("\nMaximum score (Sliding Window): " + 
                          maxScore(cardPoints, k));
        System.out.println("Maximum score (Two Ends): " + 
                          maxScoreEnds(cardPoints, k));
        System.out.println("Maximum score (Optimized): " + 
                          maxScoreOptimized(cardPoints, k));
        
        // More examples
        int[][] testCases = {
            {2, 2, 2},
            {9, 7, 7, 9, 7, 7, 9},
            {1, 1000, 1}
        };
        int[] ks = {2, 7, 1};
        
        System.out.println("\nMore examples:");
        for (int i = 0; i < testCases.length; i++) {
            System.out.println(java.util.Arrays.toString(testCases[i]) + 
                              ", k=" + ks[i] + " → " + 
                              maxScoreOptimized(testCases[i], ks[i]));
        }
    }
}
```

---

## 📝 Practice Problems

### Easy
| Problem | Pattern | Link |
|---------|---------|------|
| Maximum Sum Subarray of Size K | Fixed Window | GeeksforGeeks |
| Maximum Average Subarray | Fixed Window | LeetCode 643 |
| Contains Duplicate II | Fixed Window | LeetCode 219 |

### Medium
| Problem | Pattern | Link |
|---------|---------|------|
| Longest Substring Without Repeating | Variable Window | LeetCode 3 |
| Minimum Size Subarray Sum | Variable Window | LeetCode 209 |
| Longest Repeating Character Replacement | Variable Window | LeetCode 424 |
| Permutation in String | Fixed Window | LeetCode 567 |
| Maximum Points from Cards | Fixed Window | LeetCode 1423 |

### Hard
| Problem | Pattern | Link |
|---------|---------|------|
| Minimum Window Substring | Variable Window | LeetCode 76 |
| Sliding Window Maximum | Monotonic Queue | LeetCode 239 |
| Find All Anagrams in String | Fixed Window | LeetCode 438 |

---

## ✅ Key Takeaways

1. **Fixed Size Window** - Use when window size k is given
2. **Variable Size Window** - Use when finding longest/shortest
3. **Expand Right** - Add elements to window
4. **Contract Left** - Remove elements when condition violated
5. **HashSet/HashMap** - Track elements in current window
6. **Optimization** - Convert O(n²) to O(n) using sliding window

---

**Previous:** [Greedy Algorithms](../greedy/01-theory.md)  
**Next:** [Two Pointers](../two-pointers/01-theory.md)