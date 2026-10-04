# 📊 Sorting Algorithms - Complete Guide (Part 2: Advanced Sorting)

---

## 💻 Java Code Examples

### Algorithm 5: Merge Sort

```java
/**
 * Algorithm: Merge Sort
 * 
 * Visual:
 * Array:[3][9][10][27][38][43][82]
 * 
 * Divide Phase:
 *[3][9][10][27][38][43][82]
 *           ↓
 *[3][9][10][27][38][43][82]
 *           ↓                 ↓
 *[3][9][27][38][43][82]
 *      ↓         ↓         ↓        ↓
 *           
 * 
 * Merge Phase:
 *[3][9][27][38][43][82]
 *      ↓         ↓         ↓        ↓
 *[3][9][10][27][38][43][82]
 *           ↓                 ↓
 *[3][9][10][27][38][43][82]
 * 
 * Time Complexity:
 * - Best: O(n log n)
 * - Average: O(n log n)
 * - Worst: O(n log n)
 * 
 * Space Complexity: O(n)
 * 
 * Stable: Yes
 * In-Place: No
 */
public class MergeSort {
    
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            // Find middle point
            int mid = left + (right - left) / 2;
            
            // Sort first half
            mergeSort(arr, left, mid);
            
            // Sort second half
            mergeSort(arr, mid + 1, right);
            
            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        // Find sizes of two subarrays
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        // Create temp arrays
        int[] L = new int[n1];
        int[] R = new int[n2];
        
        // Copy data to temp arrays
        for (int i = 0; i < n1; i++) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[mid + 1 + j];
        }
        
        // Merge the temp arrays
        int i = 0, j = 0;
        int k = left;
        
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        
        // Copy remaining elements of L[]
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        
        // Copy remaining elements of R[]
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    
    // Iterative Merge Sort
    public static void mergeSortIterative(int[] arr) {
        int n = arr.length;
        
        // For subarray size 1, 2, 4, 8, ...
        for (int size = 1; size < n; size *= 2) {
            // Pick starting point of different subarrays
            for (int left = 0; left < n - 1; left += 2 * size) {
                // Find ending point
                int mid = Math.min(left + size - 1, n - 1);
                int right = Math.min(left + 2 * size - 1, n - 1);
                
                // Merge subarrays
                merge(arr, left, mid, right);
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
        int[] arr = {38, 27, 43, 3, 9, 82, 10};
        
        System.out.println("Original array:");
        printArray(arr);
        
        mergeSort(arr, 0, arr.length - 1);
        
        System.out.println("Sorted array:");
        printArray(arr);
        
        // Test iterative version
        int[] arr2 = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("\nIterative merge sort:");
        mergeSortIterative(arr2);
        printArray(arr2);
    }
}
```

---

### Algorithm 6: Quick Sort

```java
/**
 * Algorithm: Quick Sort
 * 
 * Visual:
 * Array:[10][30][40][50][70][80][90]
 * 
 * Partition (pivot = last element = 70):
 * 
 * i = -1 (smaller element index)
 * j = 0 (current element index)
 * 
 * j=0: 10 < 70 → i=0, swap(0,0) →[10][30][40][50][70][80][90]
 * j=1: 80 >= 70 → no swap
 * j=2: 30 < 70 → i=1, swap(1,2) →[10][30][40][50][70][80][90]
 * j=3: 90 >= 70 → no swap
 * j=4: 40 < 70 → i=2, swap(2,4) →[10][30][40][50][70][80][90]
 * j=5: 50 < 70 → i=3, swap(3,5) →[10][30][40][50][70][80][90]
 * j=6: End → swap(i+1, 6) →[10][30][40][50][70][80][90]
 * 
 * Pivot 70 is now at correct position (index 4)
 * 
 * Recursively sort:
 * Left:[10][30][40][50]
 * Right:[80][90]
 * 
 * Time Complexity:
 * - Best: O(n log n)
 * - Average: O(n log n)
 * - Worst: O(n²) (already sorted, bad pivot)
 * 
 * Space Complexity: O(log n) (recursion stack)
 * 
 * Stable: No
 * In-Place: Yes
 */
public class QuickSort {
    
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Find partition index
            int pi = partition(arr, low, high);
            
            // Recursively sort elements before and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    
    // Lomuto Partition Scheme (pivot = last element)
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1; // Index of smaller element
        
        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++;
                
                // Swap
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        // Swap pivot with element at i+1
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        
        return i + 1;
    }
    
    // Hoare Partition Scheme (pivot = first element)
    public static void quickSortHoare(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionHoare(arr, low, high);
            
            quickSortHoare(arr, low, pi);
            quickSortHoare(arr, pi + 1, high);
        }
    }
    
    private static int partitionHoare(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low - 1;
        int j = high + 1;
        
        while (true) {
            // Find leftmost element greater than or equal to pivot
            do {
                i++;
            } while (arr[i] < pivot);
            
            // Find rightmost element less than or equal to pivot
            do {
                j--;
            } while (arr[j] > pivot);
            
            // If two pointers met
            if (i >= j) {
                return j;
            }
            
            // Swap
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    
    // Randomized Quick Sort (avoid worst case)
    public static void quickSortRandomized(int[] arr, int low, int high) {
        if (low < high) {
            // Generate random pivot index
            int randomPivot = low + (int)(Math.random() * (high - low));
            
            // Swap with last element
            int temp = arr[randomPivot];
            arr[randomPivot] = arr[high];
            arr[high] = temp;
            
            // Partition
            int pi = partition(arr, low, high);
            
            // Recursively sort
            quickSortRandomized(arr, low, pi - 1);
            quickSortRandomized(arr, pi + 1, high);
        }
    }
    
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int[] arr = {10, 80, 30, 90, 40, 50, 70};
        
        System.out.println("Original array:");
        printArray(arr);
        
        quickSort(arr, 0, arr.length - 1);
        
        System.out.println("Sorted array:");
        printArray(arr);
        
        // Test Hoare partition
        int[] arr2 = {10, 80, 30, 90, 40, 50, 70};
        System.out.println("\nHoare partition:");
        quickSortHoare(arr2, 0, arr2.length - 1);
        printArray(arr2);
        
        // Test randomized
        int[] arr3 = {10, 80, 30, 90, 40, 50, 70};
        System.out.println("\nRandomized quick sort:");
        quickSortRandomized(arr3, 0, arr3.length - 1);
        printArray(arr3);
    }
}
```

---

### Algorithm 7: Heap Sort

```java
/**
 * Algorithm: Heap Sort
 * 
 * Visual:
 * Array:[5][6][7][11][12][13]
 * 
 * Step 1: Build Max Heap
 * 
 *       12
 *      /  \
 *    11    13
 *   / \    /
 *  5   6  7
 * 
 * Heapify from bottom:
 * 
 *       13
 *      /  \
 *    11    12
 *   / \    /
 *  5   6  7
 * 
 * Step 2: Extract elements one by one
 * 
 * Swap root (13) with last (7):
 *       7              12
 *      / \            /  \
 *    11   12   →    11    7
 *   / \            / \
 *  5   6          5   6
 * 
 * Heapify:
 * 
 * Sorted: [_, _, _, _, _, 13]
 * 
 * Continue...
 * 
 * Final:[5][6][7][11][12][13]
 * 
 * Time Complexity:
 * - Best: O(n log n)
 * - Average: O(n log n)
 * - Worst: O(n log n)
 * 
 * Space Complexity: O(1)
 * 
 * Stable: No
 * In-Place: Yes
 */
public class HeapSort {
    
    public static void heapSort(int[] arr) {
        int n = arr.length;
        
        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        
        // Extract elements one by one
        for (int i = n - 1; i > 0; i--) {
            // Swap root with last element
            int temp = arr;
            arr = arr[i];
            arr[i] = temp;
            
            // Heapify reduced heap
            heapify(arr, i, 0);
        }
    }
    
    // Heapify subtree rooted at index i
    // n is size of heap
    private static void heapify(int[] arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // Left child
        int right = 2 * i + 2; // Right child
        
        // If left child is larger than root
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        
        // If right child is larger than largest so far
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        
        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            
            // Recursively heapify affected subtree
            heapify(arr, n, largest);
        }
    }
    
    // Variation: Min Heap Sort (for descending order)
    public static void heapSortDescending(int[] arr) {
        int n = arr.length;
        
        // Build min heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            minHeapify(arr, n, i);
        }
        
        // Extract elements
        for (int i = n - 1; i > 0; i--) {
            int temp = arr;
            arr = arr[i];
            arr[i] = temp;
            
            minHeapify(arr, i, 0);
        }
    }
    
    private static void minHeapify(int[] arr, int n, int i) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (left < n && arr[left] < arr[smallest]) {
            smallest = left;
        }
        
        if (right < n && arr[right] < arr[smallest]) {
            smallest = right;
        }
        
        if (smallest != i) {
            int swap = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = swap;
            
            minHeapify(arr, n, smallest);
        }
    }
    
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        
        System.out.println("Original array:");
        printArray(arr);
        
        heapSort(arr);
        
        System.out.println("Sorted array (ascending):");
        printArray(arr);
        
        // Descending
        int[] arr2 = {12, 11, 13, 5, 6, 7};
        System.out.println("\nSorted array (descending):");
        heapSortDescending(arr2);
        printArray(arr2);
    }
}
```

---

### Algorithm 8: Radix Sort

```java
/**
 * Algorithm: Radix Sort
 * 
 * Visual:
 * Array:[2][24][45][66][75][90][170][802]
 * 
 * Sort by ones place:
 *[2][24][45][66][75][90][170][802]
 * 
 * Sort by tens place:
 *[2][24][45][66][75][90][170][802]
 * 
 * Sort by hundreds place:
 *[2][24][45][66][75][90][170][802]
 * 
 * Sorted!
 * 
 * Time Complexity: O(d × (n + b))
 * where d = number of digits, b = base (10 for decimal)
 * 
 * Space Complexity: O(n + b)
 * 
 * Stable: Yes
 * In-Place: No
 */
public class RadixSort {
    
    // Get maximum value in array
    private static int getMax(int[] arr) {
        int max = arr;
        for (int num : arr) {
            max = Math.max(max, num);
        }
        return max;
    }
    
    // Counting sort based on digit represented by exp
    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int; // 10 digits (0-9)
        
        // Initialize count array
        for (int i = 0; i < 10; i++) {
            count[i] = 0;
        }
        
        // Count occurrences of each digit
        for (int num : arr) {
            int digit = (num / exp) % 10;
            count[digit]++;
        }
        
        // Calculate cumulative count
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        
        // Build output array (from end for stability)
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }
        
        // Copy output to original array
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }
    
    public static void radixSort(int[] arr) {
        // Find maximum number to know number of digits
        int max = getMax(arr);
        
        // Do counting sort for every digit
        // exp is 10^i (i is current digit position)
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }
    
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
        
        System.out.println("Original array:");
        printArray(arr);
        
        radixSort(arr);
        
        System.out.println("Sorted array:");
        printArray(arr);
    }
}
```

---

## 📊 Comparison Table

```java
/**
 * Sorting Algorithms Comparison
 */
public class SortingComparison {
    
    public static void printComparison() {
        System.out.println("=".repeat(100));
        System.out.println("SORTING ALGORITHMS COMPARISON");
        System.out.println("=".repeat(100));
        
        System.out.println("\n1. BUBBLE SORT");
        System.out.println("   - Time: O(n²) average/worst, O(n) best");
        System.out.println("   - Space: O(1)");
        System.out.println("   - Stable: Yes");
        System.out.println("   - In-Place: Yes");
        System.out.println("   - Use: Educational, small datasets");
        
        System.out.println("\n2. SELECTION SORT");
        System.out.println("   - Time: O(n²) always");
        System.out.println("   - Space: O(1)");
        System.out.println("   - Stable: No");
        System.out.println("   - In-Place: Yes");
        System.out.println("   - Use: When swaps are expensive");
        
        System.out.println("\n3. INSERTION SORT");
        System.out.println("   - Time: O(n²) average/worst, O(n) best");
        System.out.println("   - Space: O(1)");
        System.out.println("   - Stable: Yes");
        System.out.println("   - In-Place: Yes");
        System.out.println("   - Use: Small or nearly sorted datasets");
        
        System.out.println("\n4. MERGE SORT");
        System.out.println("   - Time: O(n log n) always");
        System.out.println("   - Space: O(n)");
        System.out.println("   - Stable: Yes");
        System.out.println("   - In-Place: No");
        System.out.println("   - Use: When stability required, external sorting");
        
        System.out.println("\n5. QUICK SORT");
        System.out.println("   - Time: O(n log n) average, O(n²) worst");
        System.out.println("   - Space: O(log n)");
        System.out.println("   - Stable: No");
        System.out.println("   - In-Place: Yes");
        System.out.println("   - Use: General purpose, cache-friendly");
        
        System.out.println("\n6. HEAP SORT");
        System.out.println("   - Time: O(n log n) always");
        System.out.println("   - Space: O(1)");
        System.out.println("   - Stable: No");
        System.out.println("   - In-Place: Yes");
        System.out.println("   - Use: When guaranteed O(n log n) needed");
        
        System.out.println("\n7. COUNTING SORT");
        System.out.println("   - Time: O(n + k)");
        System.out.println("   - Space: O(k)");
        System.out.println("   - Stable: Yes");
        System.out.println("   - In-Place: No");
        System.out.println("   - Use: When range k is small");
        
        System.out.println("\n8. RADIX SORT");
        System.out.println("   - Time: O(d × (n + b))");
        System.out.println("   - Space: O(n + b)");
        System.out.println("   - Stable: Yes");
        System.out.println("   - In-Place: No");
        System.out.println("   - Use: When numbers have fixed digits");
        
        System.out.println("\n" + "=".repeat(100));
    }
    
    public static void main(String[] args) {
        printComparison();
    }
}
```

---

## 📝 Practice Problems

### Easy
| Problem | Algorithm | Link |
|---------|-----------|------|
| Sort an Array | Any | LeetCode 912 |
| Kth Largest Element | Quick Select | LeetCode 215 |

### Medium
| Problem | Algorithm | Link |
|---------|-----------|------|
| Merge Intervals | Merge Sort concept | LeetCode 56 |
| Sort Colors | Counting Sort | LeetCode 75 |
| Top K Frequent | Heap Sort concept | LeetCode 347 |

### Hard
| Problem | Algorithm | Link |
|---------|-----------|------|
| Merge k Sorted Lists | Merge Sort concept | LeetCode 23 |
| Find Median from Data Stream | Heap/Insertion | LeetCode 295 |

---

## ✅ Key Takeaways (Part 2)

1. **Merge Sort** - Divide & conquer, stable, O(n log n) guaranteed
2. **Quick Sort** - Fastest in practice, use randomized pivot
3. **Heap Sort** - O(n log n) guaranteed, in-place, not stable
4. **Radix Sort** - Non-comparison based, O(nk) for k-digit numbers
5. **Stability matters** - When sorting objects with multiple fields
6. **Choose wisely** - Different sorts for different scenarios

---

**Previous:** [Part 1 - Basic Sorting](./01-basic-sorting.md)  
**Next:** [Bit Manipulation](../bit-manipulation/01-theory.md)