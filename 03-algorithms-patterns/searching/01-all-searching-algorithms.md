# 🔍 Searching Algorithms - Complete Guide

## 📚 Theory

### What is Searching?

**Searching** is the process of finding a specific element (target) in a collection of elements (array, list, etc.).

### Types of Searching Algorithms

| Algorithm | Time Complexity | Space Complexity | Requirements |
|-----------|----------------|------------------|--------------|
| Linear Search | O(n) | O(1) | None |
| Binary Search | O(log n) | O(1) iterative, O(log n) recursive | Sorted array |
| Ternary Search | O(log₃ n) | O(1) | Sorted array, unimodal function |
| Jump Search | O(√n) | O(1) | Sorted array |
| Exponential Search | O(log n) | O(1) | Sorted array |
| Interpolation Search | O(log log n) average | O(1) | Sorted, uniformly distributed |

---

## 💻 Java Code Examples

### Algorithm 1: Linear Search

**Question:** Find target element in array using linear search.

```java
/**
 * Algorithm: Linear Search
 * 
 * Visual:
 * Array:[1][2][3][4][5][7][8][9]
 * Target: 7
 * 
 * Dry Run:
 * i=0: arr=5, 5≠7, continue
 * i=1: arr=3, 3≠7, continue[1]
 * i=2: arr=8, 8≠7, continue[2]
 * i=3: arr=4, 4≠7, continue[3]
 * i=4: arr=2, 2≠7, continue[4]
 * i=5: arr=7, 7==7 ✓ Found! Return index 5[5]
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class LinearSearch {
    
    // Approach 1: Using Loop (Iterative)
    public static int linearSearchIterative(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Found, return index
            }
        }
        return -1; // Not found
    }
    
    // Approach 2: Using Recursion
    public static int linearSearchRecursive(int[] arr, int target, int index) {
        // Base case: reached end of array
        if (index >= arr.length) {
            return -1;
        }
        
        // Base case: found target
        if (arr[index] == target) {
            return index;
        }
        
        // Recursive case: check next element
        return linearSearchRecursive(arr, target, index + 1);
    }
    
    // Find all occurrences
    public static java.util.List<Integer> findAllOccurrences(int[] arr, int target) {
        java.util.List<Integer> indices = new java.util.ArrayList<>();
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                indices.add(i);
            }
        }
        
        return indices;
    }
    
    public static void main(String[] args) {
        int[] arr = {5, 3, 8, 4, 2, 7, 1, 9};
        int target = 7;
        
        System.out.println("Array: " + java.util.Arrays.toString(arr));
        System.out.println("Target: " + target);
        
        // Iterative
        int index1 = linearSearchIterative(arr, target);
        System.out.println("\nLinear Search (Iterative):");
        if (index1 != -1) {
            System.out.println("Found at index: " + index1);
        } else {
            System.out.println("Not found");
        }
        
        // Recursive
        int index2 = linearSearchRecursive(arr, target, 0);
        System.out.println("\nLinear Search (Recursive):");
        if (index2 != -1) {
            System.out.println("Found at index: " + index2);
        } else {
            System.out.println("Not found");
        }
        
        // Find all occurrences
        int[] arr2 = {5, 3, 7, 4, 2, 7, 1, 7, 9};
        target = 7;
        System.out.println("\nArray: " + java.util.Arrays.toString(arr2));
        System.out.println("All occurrences of " + target + ": " + findAllOccurrences(arr2, target));
    }
}
```

---

### Algorithm 2: Binary Search

**Question:** Find target element in sorted array using binary search.

```java
/**
 * Algorithm: Binary Search
 * 
 * Visual:
 * Array:[1][3][5][7][9][11][13][15][17][19]
 *        0  1  2  3  4  5   6   7   8   9   (indices)
 * Target: 11
 * 
 * Dry Run (Iterative):
 * 
 * Iteration 1:
 * left=0, right=9
 * mid = 0 + (9-0)/2 = 4
 * arr = 9[4]
 * 9 < 11, so search right half
 * left = mid + 1 = 5
 * 
 * Iteration 2:
 * left=5, right=9
 * mid = 5 + (9-5)/2 = 7
 * arr = 15[6]
 * 15 > 11, so search left half
 * right = mid - 1 = 6
 * 
 * Iteration 3:
 * left=5, right=6
 * mid = 5 + (6-5)/2 = 5
 * arr = 11[5]
 * 11 == 11 ✓ Found! Return index 5
 * 
 * Time Complexity: O(log n)
 * Space Complexity: O(1) iterative, O(log n) recursive
 */
public class BinarySearch {
    
    // Approach 1: Using Loop (Iterative)
    public static int binarySearchIterative(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            // Calculate mid (prevent overflow)
            int mid = left + (right - left) / 2;
            
            // Check if target is present at mid
            if (arr[mid] == target) {
                return mid;
            }
            
            // If target is greater, ignore left half
            if (arr[mid] < target) {
                left = mid + 1;
            }
            // If target is smaller, ignore right half
            else {
                right = mid - 1;
            }
        }
        
        // Target not found
        return -1;
    }
    
    // Approach 2: Using Recursion
    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        // Base case: element not found
        if (left > right) {
            return -1;
        }
        
        // Calculate mid
        int mid = left + (right - left) / 2;
        
        // Check if target is present at mid
        if (arr[mid] == target) {
            return mid;
        }
        
        // If target is greater, search right half
        if (arr[mid] < target) {
            return binarySearchRecursive(arr, target, mid + 1, right);
        }
        // If target is smaller, search left half
        else {
            return binarySearchRecursive(arr, target, left, mid - 1);
        }
    }
    
    // Find first occurrence (for duplicate elements)
    public static int findFirstOccurrence(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                result = mid;
                right = mid - 1; // Continue searching in left half
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    // Find last occurrence
    public static int findLastOccurrence(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                result = mid;
                left = mid + 1; // Continue searching in right half
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    // Count occurrences
    public static int countOccurrences(int[] arr, int target) {
        int first = findFirstOccurrence(arr, target);
        
        if (first == -1) {
            return 0;
        }
        
        int last = findLastOccurrence(arr, target);
        
        return last - first + 1;
    }
    
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int target = 11;
        
        System.out.println("Array: " + java.util.Arrays.toString(arr));
        System.out.println("Target: " + target);
        
        // Iterative
        int index1 = binarySearchIterative(arr, target);
        System.out.println("\nBinary Search (Iterative):");
        System.out.println("Found at index: " + index1);
        
        // Recursive
        int index2 = binarySearchRecursive(arr, target, 0, arr.length - 1);
        System.out.println("\nBinary Search (Recursive):");
        System.out.println("Found at index: " + index2);
        
        // With duplicates
        int[] arr2 = {1, 3, 5, 7, 7, 7, 9, 11, 13};
        target = 7;
        System.out.println("\nArray with duplicates: " + java.util.Arrays.toString(arr2));
        System.out.println("Target: " + target);
        System.out.println("First occurrence: " + findFirstOccurrence(arr2, target));
        System.out.println("Last occurrence: " + findLastOccurrence(arr2, target));
        System.out.println("Total occurrences: " + countOccurrences(arr2, target));
    }
}
```

---

### Algorithm 3: Ternary Search

**Question:** Find target element using ternary search (divides array into 3 parts).

```java
/**
 * Algorithm: Ternary Search
 * 
 * Visual:
 * Array:[1][3][5][7][9][11][13][15][17][19][21][23][25][27][29]
 *        0  1  2  3  4  5   6   7   8   9   10  11  12  13  14
 * Target: 17
 * 
 * Dry Run:
 * 
 * Iteration 1:
 * left=0, right=14
 * mid1 = 0 + (14-0)/3 = 4
 * mid2 = 14 - (14-0)/3 = 10
 * arr = 9, arr = 21[4][7]
 * 9 < 17 < 21, so search middle third
 * left = mid1 + 1 = 5, right = mid2 - 1 = 9
 * 
 * Iteration 2:
 * left=5, right=9
 * mid1 = 5 + (9-5)/3 = 6
 * mid2 = 9 - (9-5)/3 = 8
 * arr = 13, arr = 17
 * 13 < 17, 17 == 17 ✓ Found at index 8!
 * 
 * Time Complexity: O(log₃ n)
 * Space Complexity: O(1) iterative, O(log n) recursive
 */
public class TernarySearch {
    
    // Approach 1: Using Loop (Iterative)
    public static int ternarySearchIterative(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            // Divide array into 3 parts
            int mid1 = left + (right - left) / 3;
            int mid2 = right - (right - left) / 3;
            
            // Check if target is at mid1 or mid2
            if (arr[mid1] == target) {
                return mid1;
            }
            if (arr[mid2] == target) {
                return mid2;
            }
            
            // Determine which third to search
            if (target < arr[mid1]) {
                // Search left third
                right = mid1 - 1;
            } else if (target > arr[mid2]) {
                // Search right third
                left = mid2 + 1;
            } else {
                // Search middle third
                left = mid1 + 1;
                right = mid2 - 1;
            }
        }
        
        // Target not found
        return -1;
    }
    
    // Approach 2: Using Recursion
    public static int ternarySearchRecursive(int[] arr, int target, int left, int right) {
        // Base case: element not found
        if (left > right) {
            return -1;
        }
        
        // Divide array into 3 parts
        int mid1 = left + (right - left) / 3;
        int mid2 = right - (right - left) / 3;
        
        // Check if target is at mid1 or mid2
        if (arr[mid1] == target) {
            return mid1;
        }
        if (arr[mid2] == target) {
            return mid2;
        }
        
        // Determine which third to search
        if (target < arr[mid1]) {
            // Search left third
            return ternarySearchRecursive(arr, target, left, mid1 - 1);
        } else if (target > arr[mid2]) {
            // Search right third
            return ternarySearchRecursive(arr, target, mid2 + 1, right);
        } else {
            // Search middle third
            return ternarySearchRecursive(arr, target, mid1 + 1, mid2 - 1);
        }
    }
    
    // Find peak element using ternary search
    public static int findPeakElement(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left < right) {
            int mid1 = left + (right - left) / 3;
            int mid2 = right - (right - left) / 3;
            
            if (arr[mid1] < arr[mid2]) {
                left = mid1 + 1;
            } else {
                right = mid2 - 1;
            }
        }
        
        return left;
    }
    
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29};
        int target = 17;
        
        System.out.println("Array: " + java.util.Arrays.toString(arr));
        System.out.println("Target: " + target);
        
        // Iterative
        int index1 = ternarySearchIterative(arr, target);
        System.out.println("\nTernary Search (Iterative):");
        System.out.println("Found at index: " + index1);
        
        // Recursive
        int index2 = ternarySearchRecursive(arr, target, 0, arr.length - 1);
        System.out.println("\nTernary Search (Recursive):");
        System.out.println("Found at index: " + index2);
        
        // Find peak
        int[] arr2 = {1, 3, 5, 7, 9, 8, 6, 4, 2};
        System.out.println("\nArray: " + java.util.Arrays.toString(arr2));
        System.out.println("Peak element index: " + findPeakElement(arr2));
        System.out.println("Peak element value: " + arr2[findPeakElement(arr2)]);
    }
}
```

---

### Algorithm 4: Jump Search

**Question:** Find target element using jump search (jumps √n steps).

```java
/**
 * Algorithm: Jump Search
 * 
 * Visual:
 * Array:[0][1][2][3][5][8][13][21][34][55][89][144][233][377][610]
 *        0  1  2  3  4  5  6  7   8   9   10  11  12   13   14   15
 * Length: 16, Block size: √16 = 4
 * Target: 55
 * 
 * Dry Run:
 * 
 * Step 1: Jump to index 4
 * arr = 3[4]
 * 3 < 55, continue jumping
 * 
 * Step 2: Jump to index 8
 * arr = 21
 * 21 < 55, continue jumping
 * 
 * Step 3: Jump to index 12
 * arr = 144[8]
 * 144 > 55, target is between index 8 and 12
 * Perform linear search from index 8
 * 
 * Linear Search:
 * arr = 21, 21 ≠ 55
 * arr = 34, 34 ≠ 55
 * arr = 55, 55 == 55 ✓ Found at index 10![7]
 * 
 * Time Complexity: O(√n)
 * Space Complexity: O(1)
 */
public class JumpSearch {
    
    // Approach 1: Using Loop (Iterative)
    public static int jumpSearchIterative(int[] arr, int target) {
        int n = arr.length;
        
        // Block size to jump
        int blockSize = (int) Math.sqrt(n);
        
        int prev = 0;
        int curr = blockSize;
        
        // Find the block where target may be present
        while (curr < n && arr[curr] < target) {
            prev = curr;
            curr += blockSize;
            
            // Prevent going out of bounds
            if (curr >= n) {
                curr = n - 1;
            }
        }
        
        // Linear search in the block
        for (int i = prev; i <= curr; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        
        // Target not found
        return -1;
    }
    
    // Approach 2: Using Recursion
    public static int jumpSearchRecursive(int[] arr, int target, int prev, int blockSize) {
        int n = arr.length;
        int curr = prev + blockSize;
        
        // Base case: went out of bounds
        if (prev >= n) {
            return -1;
        }
        
        // Adjust curr if it goes out of bounds
        if (curr >= n) {
            curr = n - 1;
        }
        
        // If we've passed the target or reached end
        if (arr[curr] >= target) {
            // Linear search in current block
            for (int i = prev; i <= curr; i++) {
                if (arr[i] == target) {
                    return i;
                }
            }
            return -1;
        }
        
        // Jump to next block
        return jumpSearchRecursive(arr, target, curr, blockSize);
    }
    
    public static void main(String[] args) {
        int[] arr = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610};
        int target = 55;
        
        System.out.println("Array: " + java.util.Arrays.toString(arr));
        System.out.println("Array length: " + arr.length);
        System.out.println("Block size (√n): " + (int) Math.sqrt(arr.length));
        System.out.println("Target: " + target);
        
        // Iterative
        int index1 = jumpSearchIterative(arr, target);
        System.out.println("\nJump Search (Iterative):");
        if (index1 != -1) {
            System.out.println("Found at index: " + index1);
        } else {
            System.out.println("Not found");
        }
        
        // Recursive
        int blockSize = (int) Math.sqrt(arr.length);
        int index2 = jumpSearchRecursive(arr, target, 0, blockSize);
        System.out.println("\nJump Search (Recursive):");
        if (index2 != -1) {
            System.out.println("Found at index: " + index2);
        } else {
            System.out.println("Not found");
        }
        
        // Test with different targets
        int[] targets = {55, 1, 610, 100};
        System.out.println("\nTesting multiple targets:");
        for (int t : targets) {
            int idx = jumpSearchIterative(arr, t);
            System.out.println("Target " + t + ": " + (idx != -1 ? "Index " + idx : "Not found"));
        }
    }
}
```

---

### Algorithm 5: Exponential Search

**Question:** Find target element using exponential search (also called Galloping Search).

```java
/**
 * Algorithm: Exponential Search
 * 
 * Visual:
 * Array:[2][3][4][10][40][50][80][100][150][200][250][300][350][400][450][500]
 *        0  1  2  3   4   5   6   7    8    9    10   11   12   13   14   15
 * Target: 100
 * 
 * Dry Run:
 * 
 * Phase 1: Find range (exponential growth)
 * i=1: arr=3, 3 < 100, continue[1]
 * i=2: arr=4, 4 < 100, continue[2]
 * i=4: arr=40, 40 < 100, continue[4]
 * i=8: arr=150, 150 > 100, stop!
 * Target is in range[4][8]
 * 
 * Phase 2: Binary search in range[4][8]
 * left=4, right=8
 * mid = 4 + (8-4)/2 = 6
 * arr = 80, 80 < 100
 * left = 7
 * 
 * left=7, right=8
 * mid = 7 + (8-7)/2 = 7
 * arr = 100, 100 == 100 ✓ Found at index 7![6]
 * 
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
public class ExponentialSearch {
    
    // Approach 1: Using Loop (Iterative)
    public static int exponentialSearchIterative(int[] arr, int target) {
        int n = arr.length;
        
        // Base case: check first element
        if (arr == target) {
            return 0;
        }
        
        // Find range for binary search
        // Start with i=1 and keep doubling
        int i = 1;
        while (i < n && arr[i] < target) {
            i *= 2;
        }
        
        // Perform binary search in found range
        int left = i / 2;
        int right = Math.min(i, n - 1);
        
        return binarySearchInRange(arr, target, left, right);
    }
    
    // Helper method: Binary search in given range
    private static int binarySearchInRange(int[] arr, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                return mid;
            }
            
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }
    
    // Approach 2: Using Recursion
    public static int exponentialSearchRecursive(int[] arr, int target) {
        int n = arr.length;
        
        // Base case
        if (arr == target) {
            return 0;
        }
        
        // Find range recursively
        int range = findRangeRecursive(arr, target, 1);
        
        // Perform binary search in range
        int left = range / 2;
        int right = Math.min(range, n - 1);
        
        return binarySearchRecursiveInRange(arr, target, left, right);
    }
    
    // Recursively find the range
    private static int findRangeRecursive(int[] arr, int target, int i) {
        int n = arr.length;
        
        // Base case: went out of bounds or found range
        if (i >= n || arr[i] >= target) {
            return i;
        }
        
        // Double the index
        return findRangeRecursive(arr, target, i * 2);
    }
    
    // Recursive binary search in range
    private static int binarySearchRecursiveInRange(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == target) {
            return mid;
        }
        
        if (arr[mid] < target) {
            return binarySearchRecursiveInRange(arr, target, mid + 1, right);
        } else {
            return binarySearchRecursiveInRange(arr, target, left, mid - 1);
        }
    }
    
    // Find position where element should be inserted
    public static int findInsertPosition(int[] arr, int target) {
        int n = arr.length;
        
        if (target <= arr) {
            return 0;
        }
        
        int i = 1;
        while (i < n && arr[i] < target) {
            i *= 2;
        }
        
        // Binary search to find exact position
        int left = i / 2;
        int right = Math.min(i, n - 1);
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                return mid;
            }
            
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return left; // Insert position
    }
    
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 10, 40, 50, 80, 100, 150, 200, 250, 300, 350, 400, 450, 500};
        int target = 100;
        
        System.out.println("Array: " + java.util.Arrays.toString(arr));
        System.out.println("Target: " + target);
        
        // Iterative
        int index1 = exponentialSearchIterative(arr, target);
        System.out.println("\nExponential Search (Iterative):");
        if (index1 != -1) {
            System.out.println("Found at index: " + index1);
        } else {
            System.out.println("Not found");
        }
        
        // Recursive
        int index2 = exponentialSearchRecursive(arr, target);
        System.out.println("\nExponential Search (Recursive):");
        if (index2 != -1) {
            System.out.println("Found at index: " + index2);
        } else {
            System.out.println("Not found");
        }
        
        // Find insert position
        int[] targets = {100, 45, 500, 600};
        System.out.println("\nInsert positions:");
        for (int t : targets) {
            int pos = findInsertPosition(arr, t);
            System.out.println("Target " + t + " should be inserted at index: " + pos);
        }
    }
}
```

---

### Algorithm 6: Interpolation Search

**Question:** Find target element using interpolation search (for uniformly distributed sorted arrays).

```java
/**
 * Algorithm: Interpolation Search
 * 
 * Visual:
 * Array:[10][20][30][40][50][60][70][80][90][100]
 *        0   1   2   3   4   5   6   7   8   9
 * Target: 70
 * 
 * Formula:
 * pos = lo + ((target - arr[lo]) * (hi - lo)) / (arr[hi] - arr[lo])
 * 
 * Dry Run:
 * 
 * Iteration 1:
 * lo=0, hi=9
 * arr[lo]=10, arr[hi]=100
 * pos = 0 + ((70-10) * (9-0)) / (100-10)
 *     = 0 + (60 * 9) / 90
 *     = 0 + 540 / 90
 *     = 0 + 6
 *     = 6
 * arr = 70, 70 == 70 ✓ Found at index 6!
 * 
 * Time Complexity:
 * - Average: O(log log n)
 * - Worst: O(n)
 * Space Complexity: O(1)
 */
public class InterpolationSearch {
    
    // Approach 1: Using Loop (Iterative)
    public static int interpolationSearchIterative(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length - 1;
        
        while (lo <= hi && target >= arr[lo] && target <= arr[hi]) {
            // Calculate position using interpolation formula
            int pos = lo + ((target - arr[lo]) * (hi - lo)) / (arr[hi] - arr[lo]);
            
            // Check if target is found
            if (arr[pos] == target) {
                return pos;
            }
            
            // If target is larger, search right subarray
            if (arr[pos] < target) {
                lo = pos + 1;
            }
            // If target is smaller, search left subarray
            else {
                hi = pos - 1;
            }
        }
        
        // Target not found
        return -1;
    }
    
    // Approach 2: Using Recursion
    public static int interpolationSearchRecursive(int[] arr, int target, int lo, int hi) {
        // Base case: element not found
        if (lo > hi || target < arr[lo] || target > arr[hi]) {
            return -1;
        }
        
        // Calculate position using interpolation formula
        int pos = lo + ((target - arr[lo]) * (hi - lo)) / (arr[hi] - arr[lo]);
        
        // Check if target is found
        if (arr[pos] == target) {
            return pos;
        }
        
        // If target is larger, search right subarray
        if (arr[pos] < target) {
            return interpolationSearchRecursive(arr, target, pos + 1, hi);
        }
        // If target is smaller, search left subarray
        else {
            return interpolationSearchRecursive(arr, target, lo, pos - 1);
        }
    }
    
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int target = 70;
        
        System.out.println("Array: " + java.util.Arrays.toString(arr));
        System.out.println("Target: " + target);
        
        // Iterative
        int index1 = interpolationSearchIterative(arr, target);
        System.out.println("\nInterpolation Search (Iterative):");
        if (index1 != -1) {
            System.out.println("Found at index: " + index1);
        } else {
            System.out.println("Not found");
        }
        
        // Recursive
        int index2 = interpolationSearchRecursive(arr, target, 0, arr.length - 1);
        System.out.println("\nInterpolation Search (Recursive):");
        if (index2 != -1) {
            System.out.println("Found at index: " + index2);
        } else {
            System.out.println("Not found");
        }
        
        // Test with different targets
        int[] targets = {70, 10, 100, 45, 105};
        System.out.println("\nTesting multiple targets:");
        for (int t : targets) {
            int idx = interpolationSearchIterative(arr, t);
            System.out.println("Target " + t + ": " + (idx != -1 ? "Index " + idx : "Not found"));
        }
    }
}
```

---

## 📊 Comparison Table

```java
/**
 * Searching Algorithms Comparison
 */
public class SearchingComparison {
    
    public static void printComparison() {
        System.out.println("=".repeat(80));
        System.out.println("SEARCHING ALGORITHMS COMPARISON");
        System.out.println("=".repeat(80));
        
        System.out.println("\n1. LINEAR SEARCH");
        System.out.println("   - Time: O(n)");
        System.out.println("   - Space: O(1)");
        System.out.println("   - Requirements: None");
        System.out.println("   - Best for: Small arrays, unsorted data");
        
        System.out.println("\n2. BINARY SEARCH");
        System.out.println("   - Time: O(log n)");
        System.out.println("   - Space: O(1) iterative, O(log n) recursive");
        System.out.println("   - Requirements: Sorted array");
        System.out.println("   - Best for: Most searching problems");
        
        System.out.println("\n3. TERNARY SEARCH");
        System.out.println("   - Time: O(log₃ n)");
        System.out.println("   - Space: O(1) iterative, O(log n) recursive");
        System.out.println("   - Requirements: Sorted array");
        System.out.println("   - Best for: Unimodal functions, finding peaks");
        
        System.out.println("\n4. JUMP SEARCH");
        System.out.println("   - Time: O(√n)");
        System.out.println("   - Space: O(1)");
        System.out.println("   - Requirements: Sorted array");
        System.out.println("   - Best for: When binary search is expensive");
        
        System.out.println("\n5. EXPONENTIAL SEARCH");
        System.out.println("   - Time: O(log n)");
        System.out.println("   - Space: O(1)");
        System.out.println("   - Requirements: Sorted array");
        System.out.println("   - Best for: Unbounded/infinite arrays");
        
        System.out.println("\n6. INTERPOLATION SEARCH");
        System.out.println("   - Time: O(log log n) average, O(n) worst");
        System.out.println("   - Space: O(1)");
        System.out.println("   - Requirements: Sorted, uniformly distributed");
        System.out.println("   - Best for: Uniformly distributed data");
        
        System.out.println("\n" + "=".repeat(80));
    }
    
    public static void main(String[] args) {
        printComparison();
    }
}
```

---

## ✅ Key Takeaways

1. **Linear Search** - Simple but slow, use when array is small or unsorted
2. **Binary Search** - Most common, O(log n), requires sorted array
3. **Ternary Search** - Divides into 3 parts, good for unimodal functions
4. **Jump Search** - O(√n), compromise between linear and binary
5. **Exponential Search** - O(log n), good for unbounded arrays
6. **Interpolation Search** - O(log log n), best for uniformly distributed data

---

**Previous:** [Dynamic Programming](../dynamic-programming/02-advanced.md)  
**Next:** [Backtracking](../backtracking/01-theory.md)