# 📊 Arrays & Strings - Complete Guide

## 📚 Theory

### What is an Array?

An **array** is a linear data structure that stores elements of the same type in **contiguous memory locations**.

### Properties of Arrays

- **Fixed size** (in Java, basic arrays)
- **Random access** - O(1) time to access any element
- **Index-based** - 0-indexed in Java
- **Homogeneous** - all elements same type

### Array Operations & Complexity

| Operation | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| Access | O(1) | O(1) |
| Search (Linear) | O(n) | O(1) |
| Search (Binary) | O(log n) | O(1) |
| Insert at end | O(1)* | O(1) |
| Insert at beginning | O(n) | O(1) |
| Insert at index i | O(n) | O(1) |
| Delete at end | O(1) | O(1) |
| Delete at beginning | O(n) | O(1) |
| Delete at index i | O(n) | O(1) |

*Amortized for ArrayList

### Types of Array Problems

1. **Searching Problems** - Find element, find index
2. **Sorting Problems** - Sort array, kth largest/smallest
3. **Two Pointer Problems** - Pair sum, triplet sum
4. **Sliding Window Problems** - Subarray/substring problems
5. **Prefix Sum Problems** - Range sum queries
6. **Rotation Problems** - Rotate array, cyclic shifts
7. **Merge/Interval Problems** - Merge intervals, overlap

---

## 💻 Java Code Examples

### Example 1: Basic Array Operations

```java
import java.util.Arrays;

/**
 * Basic Array Operations
 */
public class BasicArrayOperations {
    
    public static void main(String[] args) {
        // Declare and initialize
        int[] arr = {5, 2, 8, 1, 9, 3};
        
        // Access element
        System.out.println("Element at index 2: " + arr);[1]
        
        // Update element
        arr = 100;[1]
        System.out.println("After update: " + Arrays.toString(arr));
        
        // Length
        System.out.println("Length: " + arr.length);
        
        // Iterate
        System.out.print("Elements: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        
        // For-each loop
        System.out.print("For-each: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        // Sort
        Arrays.sort(arr);
        System.out.println("Sorted: " + Arrays.toString(arr));
        
        // Binary search (array must be sorted)
        int index = Arrays.binarySearch(arr, 8);
        System.out.println("Index of 8: " + index);
        
        // Fill array
        int[] filled = new int;[2]
        Arrays.fill(filled, 7);
        System.out.println("Filled array: " + Arrays.toString(filled));
        
        // Copy array
        int[] copied = Arrays.copyOf(arr, arr.length);
        System.out.println("Copied array: " + Arrays.toString(copied));
        
        // Compare arrays
        System.out.println("Arrays equal: " + Arrays.equals(arr, copied));
    }
}
```

### Example 2: Find Maximum and Minimum

```java
/**
 * Find Maximum and Minimum in Array
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class FindMaxMin {
    
    public static int findMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        
        int max = arr;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
    
    public static int findMin(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        
        int min = arr;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }
    
    // Find both in single pass
    public static int[] findMaxAndMin(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        
        int max = arr;
        int min = arr;
        
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        
        return new int[]{min, max};
    }
    
    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 1, 9, 3, 7, 4, 6};
        
        System.out.println("Array: " + java.util.Arrays.toString(arr));
        System.out.println("Maximum: " + findMax(arr));
        System.out.println("Minimum: " + findMin(arr));
        
        int[] result = findMaxAndMin(arr);
        System.out.println("Min: " + result + ", Max: " + result);
    }
}
```

### Example 3: Reverse Array

```java
import java.util.Arrays;

/**
 * Reverse Array
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class ReverseArray {
    
    // Using two pointers
    public static void reverse(int[] arr) {
        int left = 0, right = arr.length - 1;
        
        while (left < right) {
            // Swap
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            
            left++;
            right--;
        }
    }
    
    // Return reversed array (creates new array)
    public static int[] reverseCopy(int[] arr) {
        int[] result = new int[arr.length];
        
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[arr.length - 1 - i];
        }
        
        return result;
    }
    
    // Reverse using recursion
    public static void reverseRecursive(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        
        // Swap
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
        
        reverseRecursive(arr, left + 1, right - 1);
    }
    
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        
        System.out.println("Original: " + Arrays.toString(arr));
        
        reverse(arr);
        System.out.println("Reversed: " + Arrays.toString(arr));
        
        int[] arr2 = {10, 20, 30, 40, 50};
        int[] reversed = reverseCopy(arr2);
        System.out.println("Original: " + Arrays.toString(arr2));
        System.out.println("Reversed copy: " + Arrays.toString(reversed));
        
        // Recursive
        int[] arr3 = {1, 2, 3, 4, 5};
        reverseRecursive(arr3, 0, arr3.length - 1);
        System.out.println("Recursive reverse: " + Arrays.toString(arr3));
    }
}
```

### Example 4: Rotate Array

```java
import java.util.Arrays;

/**
 * Rotate Array by K positions
 * Multiple approaches
 */
public class RotateArray {
    
    // Approach 1: Using extra space - O(n) time, O(n) space
    public static void rotateWithExtraSpace(int[] arr, int k) {
        int n = arr.length;
        k = k % n; // Handle k > n
        
        int[] result = new int[n];
        
        for (int i = 0; i < n; i++) {
            result[(i + k) % n] = arr[i];
        }
        
        // Copy back
        for (int i = 0; i < n; i++) {
            arr[i] = result[i];
        }
    }
    
    // Approach 2: Reverse method - O(n) time, O(1) space
    public static void rotateWithReverse(int[] arr, int k) {
        int n = arr.length;
        k = k % n;
        
        // Reverse entire array
        reverse(arr, 0, n - 1);
        
        // Reverse first k elements
        reverse(arr, 0, k - 1);
        
        // Reverse remaining elements
        reverse(arr, k, n - 1);
    }
    
    // Helper method to reverse portion of array
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
    
    // Approach 3: Rotate left
    public static void rotateLeft(int[] arr, int k) {
        int n = arr.length;
        k = k % n;
        
        reverse(arr, 0, k - 1);
        reverse(arr, k, n - 1);
        reverse(arr, 0, n - 1);
    }
    
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        
        System.out.println("Original: " + Arrays.toString(arr1));
        System.out.println("Rotate right by " + k);
        
        rotateWithReverse(arr1, k);
        System.out.println("Result: " + Arrays.toString(arr1));
        // Expected:[1][2][3][4][5][6][7]
        
        // Test left rotation
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};
        rotateLeft(arr2, k);
        System.out.println("Left rotation: " + Arrays.toString(arr2));
        // Expected:[1][2][3][4][5][6][7]
    }
}
```

### Example 5: Remove Duplicates from Sorted Array

```java
import java.util.Arrays;

/**
 * Remove Duplicates from Sorted Array
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class RemoveDuplicates {
    
    public static int removeDuplicates(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        
        int i = 0; // Pointer for unique elements
        
        for (int j = 1; j < arr.length; j++) {
            if (arr[j] != arr[i]) {
                i++;
                arr[i] = arr[j];
            }
        }
        
        return i + 1; // Return new length
    }
    
    // Remove duplicates allowing at most 2 occurrences
    public static int removeDuplicatesAllowTwo(int[] arr) {
        if (arr.length <= 2) {
            return arr.length;
        }
        
        int i = 2; // Start from index 2
        
        for (int j = 2; j < arr.length; j++) {
            // If current element is different from element at i-2
            if (arr[j] != arr[i - 2]) {
                arr[i] = arr[j];
                i++;
            }
        }
        
        return i;
    }
    
    public static void main(String[] args) {
        int[] arr1 = {1, 1, 2, 2, 2, 3, 4, 4, 4, 4};
        
        System.out.println("Original: " + Arrays.toString(arr1));
        int newLength = removeDuplicates(arr1);
        System.out.println("After removing duplicates: " + 
                          Arrays.toString(Arrays.copyOf(arr1, newLength)));
        System.out.println("New length: " + newLength);
        
        // Test allow two
        int[] arr2 = {1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4};
        System.out.println("\nOriginal: " + Arrays.toString(arr2));
        newLength = removeDuplicatesAllowTwo(arr2);
        System.out.println("Allow 2: " + Arrays.toString(Arrays.copyOf(arr2, newLength)));
    }
}
```

### Example 6: Move Zeroes to End

```java
import java.util.Arrays;

/**
 * Move All Zeroes to End
 * Maintain relative order of non-zero elements
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class MoveZeroes {
    
    // Approach 1: Two passes
    public static void moveZeroesTwoPass(int[] arr) {
        int insertPos = 0;
        
        // First pass: move all non-zeroes
        for (int num : arr) {
            if (num != 0) {
                arr[insertPos++] = num;
            }
        }
        
        // Second pass: fill remaining with zeroes
        while (insertPos < arr.length) {
            arr[insertPos++] = 0;
        }
    }
    
    // Approach 2: Single pass with swap
    public static void moveZeroesOnePass(int[] arr) {
        int lastNonZero = 0;
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                // Swap
                int temp = arr[lastNonZero];
                arr[lastNonZero] = arr[i];
                arr[i] = temp;
                
                lastNonZero++;
            }
        }
    }
    
    public static void main(String[] args) {
        int[] arr1 = {0, 1, 0, 3, 12};
        System.out.println("Original: " + Arrays.toString(arr1));
        
        moveZeroesTwoPass(arr1);
        System.out.println("After move zeroes: " + Arrays.toString(arr1));
        
        int[] arr2 = {0, 0, 1, 0, 3, 0, 12};
        moveZeroesOnePass(arr2);
        System.out.println("One pass: " + Arrays.toString(arr2));
    }
}
```

### Example 7: Kadane's Algorithm - Maximum Subarray Sum

```java
/**
 * Kadane's Algorithm - Maximum Subarray Sum
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 * 
 * Key Idea: At each position, decide whether to:
 * 1. Extend existing subarray
 * 2. Start new subarray from current position
 */
public class KadanesAlgorithm {
    
    // Basic Kadane's - returns maximum sum
    public static int maxSubArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        
        int maxSoFar = arr;
        int currentMax = arr;
        
        for (int i = 1; i < arr.length; i++) {
            // Either extend existing subarray or start new
            currentMax = Math.max(arr[i], currentMax + arr[i]);
            maxSoFar = Math.max(maxSoFar, currentMax);
        }
        
        return maxSoFar;
    }
    
    // Return maximum sum and subarray indices
    public static int[] maxSubArrayWithIndices(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        
        int maxSoFar = arr;
        int currentMax = arr;
        int start = 0, end = 0, tempStart = 0;
        
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > currentMax + arr[i]) {
                currentMax = arr[i];
                tempStart = i;
            } else {
                currentMax = currentMax + arr[i];
            }
            
            if (currentMax > maxSoFar) {
                maxSoFar = currentMax;
                start = tempStart;
                end = i;
            }
        }
        
        return new int[]{maxSoFar, start, end};
    }
    
    // Maximum product subarray
    public static int maxProductSubArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        
        int maxSoFar = arr;
        int maxEndingHere = arr;
        int minEndingHere = arr;
        
        for (int i = 1; i < arr.length; i++) {
            int temp = maxEndingHere;
            
            maxEndingHere = Math.max(Math.max(arr[i] * maxEndingHere, 
                                              arr[i] * minEndingHere), 
                                     arr[i]);
            minEndingHere = Math.min(Math.min(arr[i] * temp, 
                                              arr[i] * minEndingHere), 
                                     arr[i]);
            
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }
    
    public static void main(String[] args) {
        int[] arr1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Array: " + java.util.Arrays.toString(arr1));
        System.out.println("Max subarray sum: " + maxSubArray(arr1));
        // Expected: 6 (subarray [4, -1, 2, 1])
        
        int[] result = maxSubArrayWithIndices(arr1);
        System.out.println("Max sum: " + result + 
                          ", Start: " + result + 
                          ", End: " + result);[1]
        
        // Maximum product
        int[] arr2 = {2, 3, -2, 4};
        System.out.println("\nArray: " + java.util.Arrays.toString(arr2));
        System.out.println("Max product: " + maxProductSubArray(arr2));
        // Expected: 6 (subarray )[2][3]
    }
}
```

### Example 8: Two Sum Problem

```java
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

/**
 * Two Sum Problem
 * Find two numbers that add up to target
 */
public class TwoSum {
    
    // Approach 1: Brute Force - O(n²) time, O(1) space
    public static int[] twoSumBruteForce(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }
    
    // Approach 2: HashMap - O(n) time, O(n) space
    public static int[] twoSumHashMap(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            
            map.put(arr[i], i);
        }
        
        return new int[]{-1, -1};
    }
    
    // Approach 3: Two Pointers (if array is sorted) - O(n) time, O(1) space
    public static int[] twoSumTwoPointers(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        
        while (left < right) {
            int sum = arr[left] + arr[right];
            
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        
        return new int[]{-1, -1};
    }
    
    // Return all pairs (for sorted array)
    public static int[][] allPairsWithSum(int[] arr, int target) {
        java.util.List<int[]> result = new java.util.ArrayList<>();
        int left = 0, right = arr.length - 1;
        
        while (left < right) {
            int sum = arr[left] + arr[right];
            
            if (sum == target) {
                result.add(new int[]{left, right});
                left++;
                right--;
                
                // Skip duplicates
                while (left < right && arr[left] == arr[left - 1]) {
                    left++;
                }
                while (left < right && arr[right] == arr[right + 1]) {
                    right--;
                }
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        
        return result.toArray(new int[]);
    }
    
    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        int target = 9;
        
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Target: " + target);
        
        int[] result1 = twoSumBruteForce(arr, target);
        System.out.println("Brute Force: [" + result1 + ", " + result1 + "]");
        
        int[] result2 = twoSumHashMap(arr, target);
        System.out.println("HashMap: [" + result2 + ", " + result2 + "]");
        
        // Two pointers (array must be sorted)
        int[] sortedArr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        target = 10;
        System.out.println("\nSorted Array: " + Arrays.toString(sortedArr));
        System.out.println("Target: " + target);
        
        int[] result3 = twoSumTwoPointers(sortedArr, target);
        System.out.println("Two Pointers: [" + result3 + ", " + result3 + "]");
        
        // All pairs
        int[][] allPairs = allPairsWithSum(sortedArr, target);
        System.out.println("All pairs:");
        for (int[] pair : allPairs) {
            System.out.println("[" + pair + ", " + pair + "] = " + 
                             "(" + sortedArr[pair] + ", " + sortedArr[pair] + ")");
        }
    }
}
```

### Example 9: Three Sum Problem

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Three Sum Problem
 * Find all triplets that add up to target
 * Time Complexity: O(n²)
 * Space Complexity: O(1) excluding result
 */
public class ThreeSum {
    
    public static List<List<Integer>> threeSum(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (arr == null || arr.length < 3) {
            return result;
        }
        
        // Sort the array
        Arrays.sort(arr);
        
        for (int i = 0; i < arr.length - 2; i++) {
            // Skip duplicates
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }
            
            // Two pointer approach for remaining array
            int left = i + 1;
            int right = arr.length - 1;
            
            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                
                if (sum == target) {
                    result.add(Arrays.asList(arr[i], arr[left], arr[right]));
                    
                    // Skip duplicates
                    while (left < right && arr[left] == arr[left + 1]) {
                        left++;
                    }
                    while (left < right && arr[right] == arr[right - 1]) {
                        right--;
                    }
                    
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return result;
    }
    
    // Count triplets with sum less than target
    public static int countTripletsLessThan(int[] arr, int target) {
        Arrays.sort(arr);
        int count = 0;
        
        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;
            
            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                
                if (sum < target) {
                    // All elements from left to right-1 will also satisfy
                    count += (right - left);
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, -1, -4};
        int target = 0;
        
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Target: " + target);
        
        List<List<Integer>> result = threeSum(arr, target);
        
        System.out.println("Triplets:");
        for (List<Integer> triplet : result) {
            System.out.println(triplet);
        }
        // Expected: [-1, -1, 2], [-1, 0, 1]
        
        // Count triplets
        int[] arr2 = {5, 1, 3, 4, 7};
        target = 12;
        System.out.println("\nCount triplets < " + target + ": " + 
                          countTripletsLessThan(arr2, target));
    }
}
```

### Example 10: Container With Most Water

```java
/**
 * Container With Most Water
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 * 
 * Approach: Two pointers from both ends
 * Move the pointer with smaller height
 */
public class ContainerWithMostWater {
    
    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        
        while (left < right) {
            // Calculate area
            int h = Math.min(height[left], height[right]);
            int w = right - left;
            int area = h * w;
            
            maxArea = Math.max(maxArea, area);
            
            // Move pointer with smaller height
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxArea;
    }
    
    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        
        System.out.println("Heights: " + java.util.Arrays.toString(height));
        System.out.println("Max Area: " + maxArea(height));
        // Expected: 49 (between indices 1 and 8)
    }
}
```

---

## 📝 Practice Problems

### Easy
| Problem | Pattern | Link |
|---------|---------|------|
| Two Sum | HashMap/Two Pointers | LeetCode 1 |
| Remove Duplicates | Two Pointers | LeetCode 26 |
| Move Zeroes | Two Pointers | LeetCode 283 |
| Max Subarray | Kadane's | LeetCode 53 |
| Plus One | Array Manipulation | LeetCode 66 |
| Contains Duplicate | HashSet | LeetCode 217 |

### Medium
| Problem | Pattern | Link |
|---------|---------|------|
| 3Sum | Two Pointers | LeetCode 15 |
| Container With Most Water | Two Pointers | LeetCode 11 |
| Product of Array Except Self | Prefix/Suffix | LeetCode 238 |
| Jump Game | Greedy | LeetCode 55 |
| Merge Intervals | Sorting | LeetCode 56 |
| Insert Interval | Array | LeetCode 57 |
| Set Matrix Zeroes | In-place | LeetCode 73 |

### Hard
| Problem | Pattern | Link |
|---------|---------|------|
| Trapping Rain Water | Two Pointers | LeetCode 42 |
| Merge k Sorted Arrays | Heap | Practice |
| First Missing Positive | In-place | LeetCode 41 |
| Maximum Product Subarray | DP | LeetCode 152 |

---

## ✅ Key Takeaways

1. **Two Pointers** - Use for sorted arrays, pair/triplet problems
2. **Sliding Window** - Use for contiguous subarray problems
3. **Prefix Sum** - Use for range sum queries
4. **Kadane's Algorithm** - Maximum subarray sum
5. **In-place operations** - Save space when possible
6. **Sort first** - Many problems become easier after sorting

---

**Next:** [String Problems](./02-string-problems.md)