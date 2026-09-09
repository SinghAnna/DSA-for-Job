# 📊 Prefix Sum - Complete Guide

## 📚 Theory

### What is Prefix Sum?

**Prefix Sum** (also called Cumulative Sum) is a technique where we precompute cumulative sums to answer range sum queries in O(1) time.

### When to Use Prefix Sum?

Use prefix sum when:
1. Problem asks for **sum of subarray** multiple times
2. Need to find **subarray with given sum**
3. Problem involves **range sum queries**
4. Need to calculate **sum between indices** efficiently

### Prefix Sum Formula

```
Original Array: [a₀, a₁, a₂, a₃, a₄]
Prefix Sum: [0, a₀, a₀+a₁, a₀+a₁+a₂, a₀+a₁+a₂+a₃, a₀+a₁+a₂+a₃+a₄]

prefixSum[i] = sum of first i elements
prefixSum = 0 (base case)
prefixSum[i] = prefixSum[i-1] + arr[i-1]

Sum from index i to j:
sum(i, j) = prefixSum[j+1] - prefixSum[i]

```
### Visualization

```
Array:

Index: 0 1 2 3 4

Prefix Sum:

Index: 0 1 2 3 4 5

Query: Sum from index 1 to 3 (elements: 2, 3, 4)
Answer: prefixSum - prefixSum = 10 - 1 = 6 ✓

```

---

## 💻 Java Code Examples

### Problem 1: Range Sum Query

**Question:** Given an integer array nums, handle multiple queries of the form: Calculate the sum of the elements of nums between indices left and right inclusive.

```java
/**
 * Problem: Range Sum Query - Immutable
 * LeetCode: 303
 * 
 * Visual:
 * nums = [-2, 0, 3, -5, 2, -1]
 * 
 * Build Prefix Sum:
 * prefixSum = 0
 * prefixSum = 0 + (-2) = -2[2]
 * prefixSum = -2 + 0 = -2[3]
 * prefixSum = -2 + 3 = 1[4]
 * prefixSum = 1 + (-5) = -4[1]
 * prefixSum = -4 + 2 = -2[5]
 * prefixSum = -2 + (-1) = -3[6]
 * 
 * Prefix Sum Array: [0, -2, -2, 1, -4, -2, -3]
 * 
 * Queries:
 * sumRange(0, 2) = prefixSum - prefixSum = 1 - 0 = 1[4]
 * sumRange(2, 5) = prefixSum - prefixSum = -3 - (-2) = -1[3][6]
 * sumRange(0, 5) = prefixSum - prefixSum = -3 - 0 = -3[6]
 * 
 * Time Complexity:
 * - Constructor: O(n)
 * - Query: O(1)
 * Space Complexity: O(n)
 */
public class RangeSumQuery {
    
    static class NumArray {
        private int[] prefixSum;
        
        public NumArray(int[] nums) {
            int n = nums.length;
            prefixSum = new int[n + 1];
            prefixSum = 0;
            
            // Build prefix sum array
            for (int i = 0; i < n; i++) {
                prefixSum[i + 1] = prefixSum[i] + nums[i];
            }
        }
        
        public int sumRange(int left, int right) {
            return prefixSum[right + 1] - prefixSum[left];
        }
    }
    
    // Variation: Range Sum Query - Mutable (with updates)
    // LeetCode: 307 (using Segment Tree or BIT)
    static class NumArrayMutable {
        private int[] nums;
        private int[] prefixSum;
        
        public NumArrayMutable(int[] nums) {
            this.nums = nums.clone();
            buildPrefixSum();
        }
        
        private void buildPrefixSum() {
            int n = nums.length;
            prefixSum = new int[n + 1];
            prefixSum = 0;
            
            for (int i = 0; i < n; i++) {
                prefixSum[i + 1] = prefixSum[i] + nums[i];
            }
        }
        
        public void update(int index, int val) {
            nums[index] = val;
            buildPrefixSum(); // Rebuild (inefficient, use Segment Tree for better)
        }
        
        public int sumRange(int left, int right) {
            return prefixSum[right + 1] - prefixSum[left];
        }
    }
    
    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        
        NumArray numArray = new NumArray(nums);
        
        System.out.println("\nQueries:");
        System.out.println("sumRange(0, 2) = " + numArray.sumRange(0, 2)); // 1
        System.out.println("sumRange(2, 5) = " + numArray.sumRange(2, 5)); // -1
        System.out.println("sumRange(0, 5) = " + numArray.sumRange(0, 5)); // -3
        
        // Mutable version
        NumArrayMutable mutable = new NumArrayMutable(nums);
        System.out.println("\nMutable version:");
        System.out.println("sumRange(0, 2) = " + mutable.sumRange(0, 2));
        mutable.update(2, 10);
        System.out.println("After update(2, 10):");
        System.out.println("sumRange(0, 2) = " + mutable.sumRange(0, 2)); // 8
    }
}
```

---

### Problem 2: Subarray Sum Equals K

**Question:** Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.

```java
/**
 * Problem: Subarray Sum Equals K
 * LeetCode: 560
 * 
 * Visual:
 * nums =, k = 2[1]
 * 
 * Prefix Sum Approach:
 * 
 * i=0: currentSum=1, need=1-2=-1
 *      map={0:1, 1:1}
 *      count=0
 * 
 * i=1: currentSum=2, need=2-2=0
 *      map contains 0? Yes! count += map.get(0) = 1
 *      map={0:1, 1:1, 2:1}
 *      count=1
 * 
 * i=2: currentSum=3, need=3-2=1
 *      map contains 1? Yes! count += map.get(1) = 1
 *      map={0:1, 1:1, 2:1, 3:1}
 *      count=2
 * 
 * Result: 2 subarrays
 * Subarrays:  (indices 0-1),  (indices 1-2)[1]
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class SubarraySumEqualsK {
    
    // Approach 1: Brute Force - O(n²)
    public static int subarraySumBruteForce(int[] nums, int k) {
        int count = 0;
        
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                
                if (sum == k) {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    // Approach 2: Prefix Sum with HashMap - O(n)
    public static int subarraySum(int[] nums, int k) {
        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();
        map.put(0, 1); // Base case: sum 0 appears once
        
        int currentSum = 0;
        int count = 0;
        
        for (int num : nums) {
            currentSum += num;
            
            // If (currentSum - k) exists, we found subarrays
            if (map.containsKey(currentSum - k)) {
                count += map.get(currentSum - k);
            }
            
            // Update map with current sum
            map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);
        }
        
        return count;
    }
    
    // Variation: Count Subarrays with Sum Divisible by K
    // LeetCode: 974
    public static int subarraysDivByK(int[] nums, int k) {
        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();
        map.put(0, 1); // Base case
        
        int currentSum = 0;
        int count = 0;
        
        for (int num : nums) {
            currentSum += num;
            
            // Handle negative remainder
            int remainder = ((currentSum % k) + k) % k;
            
            if (map.containsKey(remainder)) {
                count += map.get(remainder);
            }
            
            map.put(remainder, map.getOrDefault(remainder, 0) + 1);
        }
        
        return count;
    }
    
    // Variation: Subarray Sum in Range
    // Count subarrays with sum between lower and upper
    public static int countRangeSum(int[] nums, int lower, int upper) {
        int count = 0;
        int n = nums.length;
        
        // Build prefix sum
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        
        // Count subarrays with sum in range
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                long sum = prefixSum[j + 1] - prefixSum[i];
                if (sum >= lower && sum <= upper) {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        int k = 2;
        
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        System.out.println("k = " + k);
        
        System.out.println("\nCount (Brute Force): " + subarraySumBruteForce(nums, k));
        System.out.println("Count (Prefix Sum): " + subarraySum(nums, k));
        
        // Divisible by K
        int[] nums2 = {4, 5, 0, -2, -3, 1};
        k = 5;
        System.out.println("\nArray: " + java.util.Arrays.toString(nums2));
        System.out.println("k = " + k);
        System.out.println("Subarrays divisible by " + k + ": " + subarraysDivByK(nums2, k));
    }
}
```

---

### Problem 3: Maximum Size Subarray Sum Equals K

**Question:** Given an array nums and a target value k, find the maximum length of a contiguous subarray that sums to k.

```java
/**
 * Problem: Maximum Size Subarray Sum Equals K
 * LeetCode: 325
 * 
 * Visual:
 * nums = [1, -1, 5, -2, 3], k = 3
 * 
 * Prefix Sum with HashMap:
 * 
 * i=0: currentSum=1, need=1-3=-2
 *      map={0:0, 1:1}
 *      maxLen=0
 * 
 * i=1: currentSum=0, need=0-3=-3
 *      map contains -3? No
 *      map={0:0, 1:1, 0:2} (but 0 already exists, keep first)
 *      maxLen=0
 * 
 * i=2: currentSum=5, need=5-3=2
 *      map contains 2? No
 *      map={0:0, 1:1, 5:3}
 *      maxLen=0
 * 
 * i=3: currentSum=3, need=3-3=0
 *      map contains 0? Yes at index 0!
 *      maxLen = max(0, 4-0) = 4
 *      map={0:0, 1:1, 5:3, 3:4}
 * 
 * i=4: currentSum=6, need=6-3=3
 *      map contains 3? Yes at index 4!
 *      maxLen = max(4, 5-4) = 4
 *      map={0:0, 1:1, 5:3, 3:4, 6:5}
 * 
 * Result: 4 (subarray [1, -1, 5, -2])
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class MaxSizeSubarraySumEqualsK {
    
    public static int maxSubArrayLen(int[] nums, int k) {
        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();
        map.put(0, -1); // Base case: sum 0 at index -1
        
        int currentSum = 0;
        int maxLen = 0;
        
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            
            // If (currentSum - k) exists, we found a subarray
            if (map.containsKey(currentSum - k)) {
                maxLen = Math.max(maxLen, i - map.get(currentSum - k));
            }
            
            // Only store first occurrence (for maximum length)
            if (!map.containsKey(currentSum)) {
                map.put(currentSum, i);
            }
        }
        
        return maxLen;
    }
    
    // Variation: Maximum Sum Subarray of Size K
    public static int maxSumSubarraySizeK(int[] nums, int k) {
        if (nums.length < k) {
            return -1;
        }
        
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }
        
        int maxSum = windowSum;
        
        for (int i = k; i < nums.length; i++) {
            windowSum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }
        
        return maxSum;
    }
    
    // Variation: Maximum Sum Subarray (Kadane's Algorithm)
    public static int maxSubArray(int[] nums) {
        int maxSoFar = nums;
        int currentMax = nums;
        
        for (int i = 1; i < nums.length; i++) {
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            maxSoFar = Math.max(maxSoFar, currentMax);
        }
        
        return maxSoFar;
    }
    
    public static void main(String[] args) {
        int[] nums = {1, -1, 5, -2, 3};
        int k = 3;
        
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        System.out.println("k = " + k);
        
        System.out.println("\nMaximum length subarray with sum " + k + ": " + 
                          maxSubArrayLen(nums, k));
        
        // Maximum sum subarray of size k
        int[] nums2 = {2, 1, 5, 1, 3, 2};
        k = 3;
        System.out.println("\nArray: " + java.util.Arrays.toString(nums2));
        System.out.println("k = " + k);
        System.out.println("Maximum sum subarray of size " + k + ": " + 
                          maxSumSubarraySizeK(nums2, k));
        
        // Maximum sum subarray (Kadane's)
        int[] nums3 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("\nArray: " + java.util.Arrays.toString(nums3));
        System.out.println("Maximum sum subarray: " + maxSubArray(nums3));
    }
}
```

---

### Problem 4: Product of Array Except Self

**Question:** Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

```java
/**
 * Problem: Product of Array Except Self
 * LeetCode: 238
 * 
 * Visual:
 * nums =[1][2][3][4]
 * 
 * Prefix Product Approach:
 * 
 * Left Products:
 * left = 1 (no elements to left)
 * left = 1[2]
 * left = 1 × 2 = 2[3]
 * left = 1 × 2 × 3 = 6[4]
 * 
 * Right Products:
 * right = 2 × 3 × 4 = 24
 * right = 3 × 4 = 12[2]
 * right = 4[3]
 * right = 1 (no elements to right)[4]
 * 
 * Result:
 * answer = left × right = 1 × 24 = 24
 * answer = left × right = 1 × 12 = 12[2]
 * answer = left × right = 2 × 4 = 8[3]
 * answer = left × right = 6 × 1 = 6[4]
 * 
 * Result:[6][8][12][24]
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1) excluding output
 */
public class ProductOfArrayExceptSelf {
    
    // Approach 1: Prefix and Suffix Products
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        // Calculate prefix products
        result = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        
        // Calculate suffix products and multiply
        int suffixProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }
        
        return result;
    }
    
    // Approach 2: Using Division (only if no zeros)
    public static int[] productExceptSelfDivision(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        // Calculate total product
        int totalProduct = 1;
        int zeroCount = 0;
        
        for (int num : nums) {
            if (num == 0) {
                zeroCount++;
            } else {
                totalProduct *= num;
            }
        }
        
        // Handle cases with zeros
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                if (zeroCount > 1) {
                    result[i] = 0;
                } else {
                    result[i] = totalProduct;
                }
            } else {
                if (zeroCount > 0) {
                    result[i] = 0;
                } else {
                    result[i] = totalProduct / nums[i];
                }
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        
        int[] result = productExceptSelf(nums);
        System.out.println("Product except self: " + java.util.Arrays.toString(result));
        
        // With zeros
        int[] nums2 = {1, 2, 0, 4};
        System.out.println("\nArray: " + java.util.Arrays.toString(nums2));
        result = productExceptSelf(nums2);
        System.out.println("Product except self: " + java.util.Arrays.toString(result));
    }
}
```

---

### Problem 5: Contiguous Array

**Question:** Given a binary array nums, return the maximum length of a contiguous subarray with equal number of 0 and 1.

```java
/**
 * Problem: Contiguous Array
 * LeetCode: 525
 * 
 * Visual:
 * nums =[0][1]
 * 
 * Prefix Sum Approach (treat 0 as -1):
 * 
 * Convert: 0 → -1, 1 → 1
 * nums = [-1, 1, -1, 1, -1, 1, 1]
 * 
 * i=0: currentSum=-1, map={-1:0}
 *      maxLen=0
 * 
 * i=1: currentSum=0, map contains 0? No
 *      map={-1:0, 0:1}
 *      maxLen=0
 * 
 * i=2: currentSum=-1, map contains -1? Yes at index 0!
 *      maxLen = max(0, 2-0) = 2
 *      (subarray  has equal 0s and 1s)[0][1]
 * 
 * i=3: currentSum=0, map contains 0? Yes at index 1!
 *      maxLen = max(2, 3-1) = 2
 * 
 * i=4: currentSum=-1, map contains -1? Yes at index 0!
 *      maxLen = max(2, 4-0) = 4
 *      (subarray )[0][1]
 * 
 * i=5: currentSum=0, map contains 0? Yes at index 1!
 *      maxLen = max(4, 5-1) = 4
 * 
 * i=6: currentSum=1, map contains 1? No
 *      map={-1:0, 0:1, 1:6}
 *      maxLen=4
 * 
 * Result: 4
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class ContiguousArray {
    
    public static int findMaxLength(int[] nums) {
        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();
        map.put(0, -1); // Base case
        
        int currentSum = 0;
        int maxLen = 0;
        
        for (int i = 0; i < nums.length; i++) {
            // Treat 0 as -1, 1 as +1
            currentSum += (nums[i] == 0) ? -1 : 1;
            
            // If same sum seen before, subarray between has equal 0s and 1s
            if (map.containsKey(currentSum)) {
                maxLen = Math.max(maxLen, i - map.get(currentSum));
            } else {
                map.put(currentSum, i);
            }
        }
        
        return maxLen;
    }
    
    // Variation: Check if Array is Balanced
    // Equal number of 0s and 1s
    public static boolean isBalanced(int[] nums) {
        int count0 = 0, count1 = 0;
        
        for (int num : nums) {
            if (num == 0) {
                count0++;
            } else {
                count1++;
            }
        }
        
        return count0 == count1;
    }
    
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 1, 0, 1, 1};
        
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        
        int maxLen = findMaxLength(nums);
        System.out.println("Maximum length of contiguous array with equal 0s and 1s: " + maxLen);
        
        // More examples
        int[][] testCases = {
            {0, 1},
            {0, 1, 0},
            {0, 1, 0, 1}
        };
        
        System.out.println("\nMore examples:");
        for (int[] test : testCases) {
            System.out.println(java.util.Arrays.toString(test) + " → " + 
                              findMaxLength(test));
        }
    }
}
```

---

## 📝 Practice Problems

### Easy
| Problem | Pattern | Link |
|---------|---------|------|
| Range Sum Query | Prefix Sum | LeetCode 303 |
| Find Pivot Index | Prefix Sum | LeetCode 724 |

### Medium
| Problem | Pattern | Link |
|---------|---------|------|
| Subarray Sum Equals K | Prefix Sum + HashMap | LeetCode 560 |
| Maximum Size Subarray Sum Equals K | Prefix Sum + HashMap | LeetCode 325 |
| Product of Array Except Self | Prefix + Suffix | LeetCode 238 |
| Contiguous Array | Prefix Sum + HashMap | LeetCode 525 |
| Subarray Sum Divisible by K | Prefix Sum + Modulo | LeetCode 974 |

### Hard
| Problem | Pattern | Link |
|---------|---------|------|
| Count of Smaller Numbers After Self | Prefix Sum + BIT | LeetCode 315 |
| Range Sum Query 2D | 2D Prefix Sum | LeetCode 304 |

---

## ✅ Key Takeaways

1. **Prefix Sum** - Precompute cumulative sums for O(1) range queries
2. **HashMap with Prefix Sum** - Find subarrays with given sum in O(n)
3. **Prefix + Suffix** - Product except self without division
4. **Treat 0 as -1** - For equal 0s and 1s problems
5. **2D Prefix Sum** - Extend to matrices for range sum queries

---

**Previous:** [Two Pointers](../two-pointers/01-theory.md)  
**Next:** [Bit Manipulation](../bit-manipulation/01-theory.md)