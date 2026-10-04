# 📊 Sorting Algorithms - Complete Guide (Part 1: Basic Sorting)

## 📚 Theory

### What is Sorting?

**Sorting** is the process of arranging elements in a specific order (usually ascending or descending).

### Types of Sorting Algorithms

| Algorithm | Time (Best) | Time (Average) | Time (Worst) | Space | Stable | In-Place |
|-----------|-------------|----------------|--------------|-------|--------|----------|
| Bubble Sort | O(n) | O(n²) | O(n²) | O(1) | Yes | Yes |
| Selection Sort | O(n²) | O(n²) | O(n²) | O(1) | No | Yes |
| Insertion Sort | O(n) | O(n²) | O(n²) | O(1) | Yes | Yes |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | O(n) | Yes | No |
| Quick Sort | O(n log n) | O(n log n) | O(n²) | O(log n) | No | Yes |
| Heap Sort | O(n log n) | O(n log n) | O(n log n) | O(1) | No | Yes |
| Counting Sort | O(n+k) | O(n+k) | O(n+k) | O(k) | Yes | No |
| Radix Sort | O(nk) | O(nk) | O(nk) | O(n+k) | Yes | No |

### Stability in Sorting


#### Stable Sort:

* Maintains relative order of equal elements

Example: [(A,2), (B,1), (C,2)] sorted by number
Result: [(B,1), (A,2), (C,2)] - A comes before C (stable)

#### Unstable Sort:

* May change relative order of equal elements

Result: [(B,1), (C,2), (A,2)] - C comes before A (unstable)



### In-Place Sorting



### In-Place Sorting



---

## 💻 Java Code Examples

### Algorithm 1: Bubble Sort

```java
/**
 * Algorithm: Bubble Sort
 * 
 * Visual:
 * Array:[1][2][4][5][8]
 * 
 * Pass 1:
 *  → Compare 5,1 → Swap →[1][2][4][5][8]
 *  → Compare 5,4 → Swap →[1][2][4][5][8]
 *  → Compare 5,2 → Swap →[1][2][4][5][8]
 *  → Compare 5,8 → No swap →[1][2][4][5][8]
 * Largest element (8) bubbled to end
 * 
 * Pass 2:
 *  → Compare 1,4 → No swap[1][2][4][5][8]
 *  → Compare 4,2 → Swap →[1][2][4][5][8]
 *  → Compare 4,5 → No swap[1][2][4][5][8]
 * Second largest (5) in position
 * 
 * Pass 3:
 *  → Compare 1,2 → No swap[1][2][4][5][8]
 *  → Compare 2,4 → No swap[1][2][4][5][8]
 * Array sorted!
 * 
 * Time Complexity:
 * - Best: O(n) (already sorted)
 * - Average: O(n²)
 * - Worst: O(n²)
 * 
 * Space Complexity: O(1)
 * 
 * Stable: Yes
 * In-Place: Yes
 */
public class BubbleSort {
    
    // Basic Bubble Sort
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    
    // Optimized Bubble Sort (with early termination)
    public static void bubbleSortOptimized(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            
            // If no swapping occurred, array is sorted
            if (!swapped) {
                break;
            }
        }
    }
    
    // Print array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int[] arr = {5, 1, 4, 2, 8};
        
        System.out.println("Original array:");
        printArray(arr);
        
        bubbleSortOptimized(arr);
        
        System.out.println("Sorted array:");
        printArray(arr);
        
        // Test with already sorted array
        int[] arr2 = {1, 2, 3, 4, 5};
        System.out.println("\nAlready sorted array:");
        printArray(arr2);
        
        bubbleSortOptimized(arr2);
        System.out.println("After sorting (should be fast):");
        printArray(arr2);
    }
}
```

---

### Algorithm 2: Selection Sort

```java
/**
 * Algorithm: Selection Sort
 * 
 * Visual:
 * Array:[11][12][22][25][64]
 * 
 * Pass 1:
 * Find minimum in  → 11 at index 4[11][12][22][25][64]
 * Swap 64 and 11 →[11][12][22][25][64]
 * 
 * Pass 2:
 * Find minimum in  → 12 at index 2[12][22][25][64]
 * Swap 25 and 12 →[11][12][22][25][64]
 * 
 * Pass 3:
 * Find minimum in  → 22 at index 3[22][25][64]
 * Swap 25 and 22 →[11][12][22][25][64]
 * 
 * Pass 4:
 * Find minimum in  → 25 at index 3[25][64]
 * No swap needed →[11][12][22][25][64]
 * 
 * Array sorted!
 * 
 * Time Complexity:
 * - Best: O(n²)
 * - Average: O(n²)
 * - Worst: O(n²)
 * 
 * Space Complexity: O(1)
 * 
 * Stable: No
 * In-Place: Yes
 */
public class SelectionSort {
    
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            // Find minimum element in unsorted part
            int minIndex = i;
            
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            
            // Swap minimum with first element of unsorted part
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }
    
    // Variation: Selection Sort (Maximum element)
    public static void selectionSortMax(int[] arr) {
        int n = arr.length;
        
        for (int i = n - 1; i > 0; i--) {
            // Find maximum element in unsorted part
            int maxIndex = 0;
            
            for (int j = 1; j <= i; j++) {
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }
            
            // Swap maximum with last element of unsorted part
            if (maxIndex != i) {
                int temp = arr[i];
                arr[i] = arr[maxIndex];
                arr[maxIndex] = temp;
            }
        }
    }
    
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        
        System.out.println("Original array:");
        printArray(arr);
        
        selectionSort(arr);
        
        System.out.println("Sorted array:");
        printArray(arr);
        
        // Test with maximum selection
        int[] arr2 = {64, 25, 12, 22, 11};
        System.out.println("\nUsing maximum selection:");
        selectionSortMax(arr2);
        printArray(arr2);
    }
}
```

---

### Algorithm 3: Insertion Sort

```java
/**
 * Algorithm: Insertion Sort
 * 
 * Visual:
 * Array:[5][6][11][12][13]
 * 
 * Pass 1 (i=1):
 * Key = 11
 * Compare with 12: 11 < 12, shift 12
 * Insert 11 at position 0
 *[5][6][11][12][13]
 * 
 * Pass 2 (i=2):
 * Key = 13
 * Compare with 12: 13 > 12, no shift
 *[5][6][11][12][13]
 * 
 * Pass 3 (i=3):
 * Key = 5
 * Compare with 13: 5 < 13, shift 13
 * Compare with 12: 5 < 12, shift 12
 * Compare with 11: 5 < 11, shift 11
 * Insert 5 at position 0
 *[5][6][11][12][13]
 * 
 * Pass 4 (i=4):
 * Key = 6
 * Compare with 13: 6 < 13, shift 13
 * Compare with 12: 6 < 12, shift 12
 * Compare with 11: 6 < 11, shift 11
 * Compare with 5: 6 > 5, stop
 * Insert 6 at position 1
 *[5][6][11][12][13]
 * 
 * Array sorted!
 * 
 * Time Complexity:
 * - Best: O(n) (already sorted)
 * - Average: O(n²)
 * - Worst: O(n²)
 * 
 * Space Complexity: O(1)
 * 
 * Stable: Yes
 * In-Place: Yes
 */
public class InsertionSort {
    
    // Basic Insertion Sort
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            
            // Move elements greater than key to one position ahead
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            
            // Insert key at correct position
            arr[j + 1] = key;
        }
    }
    
    // Binary Insertion Sort (optimized comparisons)
    public static void binaryInsertionSort(int[] arr) {
        int n = arr.length;
        
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            
            // Find position using binary search
            int pos = binarySearch(arr, key, 0, i - 1);
            
            // Shift elements to make room
            for (int j = i - 1; j >= pos; j--) {
                arr[j + 1] = arr[j];
            }
            
            // Insert key
            arr[pos] = key;
        }
    }
    
    // Binary search to find insertion position
    private static int binarySearch(int[] arr, int key, int low, int high) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (arr[mid] == key) {
                return mid + 1;
            } else if (arr[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return low;
    }
    
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6};
        
        System.out.println("Original array:");
        printArray(arr);
        
        insertionSort(arr);
        
        System.out.println("Sorted array:");
        printArray(arr);
        
        // Test with binary insertion sort
        int[] arr2 = {12, 11, 13, 5, 6};
        System.out.println("\nUsing binary insertion sort:");
        binaryInsertionSort(arr2);
        printArray(arr2);
    }
}
```

---

### Algorithm 4: Counting Sort

```java
/**
 * Algorithm: Counting Sort
 * 
 * Visual:
 * Array:[1][2][3][4][8]
 * Range: 1 to 8
 * 
 * Step 1: Count occurrences
 * count = 1[1]
 * count = 2[2]
 * count = 2
 * count = 1[3]
 * count = 0[4]
 * count = 0
 * count = 0
 * count = 1[5]
 * 
 * Step 2: Cumulative count
 * count = 1[1]
 * count = 1+2 = 3[2]
 * count = 3+2 = 5
 * count = 5+1 = 6[3]
 * count = 6+0 = 6[4]
 * count = 6+0 = 6
 * count = 6+0 = 6
 * count = 6+1 = 7[5]
 * 
 * Step 3: Place elements (from end for stability)
 * arr=1: count=1, output=1, count=0[1]
 * arr=3: count=5, output=3, count=4[3][4]
 * arr=3: count=4, output=3, count=3[3]
 * arr=8: count=7, output=8, count=6[5]
 * arr=2: count=3, output=2, count=2[2]
 * arr=2: count=2, output=2, count=1[1][2]
 * arr=4: count=6, output=4, count=5[3][4]
 * 
 * Result:[1][2][3][4][8]
 * 
 * Time Complexity: O(n + k)
 * Space Complexity: O(k)
 * 
 * Stable: Yes
 * In-Place: No
 * 
 * where n = array size, k = range of input
 */
public class CountingSort {
    
    public static void countingSort(int[] arr) {
        int n = arr.length;
        
        if (n == 0) {
            return;
        }
        
        // Find maximum element
        int max = arr;
        for (int num : arr) {
            max = Math.max(max, num);
        }
        
        // Create count array
        int[] count = new int[max + 1];
        
        // Count occurrences
        for (int num : arr) {
            count[num]++;
        }
        
        // Calculate cumulative count
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }
        
        // Build output array (from end for stability)
        int[] output = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }
        
        // Copy output to original array
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }
    
    // Variation: Counting Sort with negative numbers
    public static void countingSortWithNegative(int[] arr) {
        int n = arr.length;
        
        if (n == 0) {
            return;
        }
        
        // Find min and max
        int min = arr, max = arr;
        for (int num : arr) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        // Create count array
        int range = max - min + 1;
        int[] count = new int[range];
        
        // Count occurrences
        for (int num : arr) {
            count[num - min]++;
        }
        
        // Calculate cumulative count
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }
        
        // Build output array
        int[] output = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }
        
        // Copy to original
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }
    
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int[] arr = {4, 2, 2, 8, 3, 3, 1};
        
        System.out.println("Original array:");
        printArray(arr);
        
        countingSort(arr);
        
        System.out.println("Sorted array:");
        printArray(arr);
        
        // Test with negative numbers
        int[] arr2 = {-5, -2, 8, -2, 3, 1, 0};
        System.out.println("\nArray with negatives:");
        printArray(arr2);
        
        countingSortWithNegative(arr2);
        System.out.println("Sorted:");
        printArray(arr2);
    }
}
```

---

## 📝 Practice Problems

### Easy
| Problem | Algorithm | Link |
|---------|-----------|------|
| Sort an Array | Any | LeetCode 912 |
| Majority Element | Counting | LeetCode 169 |

### Medium
| Problem | Algorithm | Link |
|---------|-----------|------|
| H-Index | Counting Sort | LeetCode 274 |
| Relative Sort Array | Counting Sort | LeetCode 1122 |

---

## ✅ Key Takeaways (Part 1)

1. **Bubble Sort** - Simple but slow, good for educational purposes
2. **Selection Sort** - Always O(n²), minimizes swaps
3. **Insertion Sort** - Best for small or nearly sorted arrays
4. **Counting Sort** - O(n+k) but requires known range
5. **Stability** - Important when sorting objects with multiple fields
6. **In-Place** - Important when memory is limited

---

**Next:** Part 2 - Advanced Sorting (Merge Sort, Quick Sort, Heap Sort)