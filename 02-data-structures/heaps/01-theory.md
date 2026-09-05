# 📊 Heaps (Priority Queue) - Complete Guide

## 📚 Theory

### What is a Heap?

A **Heap** is a complete binary tree data structure that satisfies the **heap property**. It's commonly used to implement priority queues.

### Types of Heaps

1. **Max Heap** - Parent node ≥ Children nodes
   - Root is always the maximum element
   
2. **Min Heap** - Parent node ≤ Children nodes
   - Root is always the minimum element

### Heap Properties
```
Max Heap:

        9 
       /  \
      7    6
     / \  / \
    5  4 3   2

- Root (9) is maximum
- Every parent ≥ its children

Min Heap:

            1 
           /  \
          3    2
         / \  / \
        5   4 6   7

- Root (1) is minimum
- Every parent ≤ its children

```
### Complete Binary Tree

Complete Binary Tree:
      
 ```
             1 
           /  \
          2    3
         / \  /
        4   5 6
```
- All levels filled except possibly last
- Last level filled from left to right

Not Complete:
```
           1 
          / \
         2   3
        /
        4 
```
- Gap in last level - Not allowed in heap



### Array Representation

For a node at index i (0-indexed):

Parent: (i - 1) / 2

Left Child: 2*i + 1

Right Child: 2*i + 2

Example (Min Heap):
Array:
 ```
    Tree:
         1 (i=0)
        /  \
       /    \
      3 (i=1) 2 (i=2)
     / \       \
    /   \       \
  5(i=3) 4(i=4) 6(i=5) 7(i=6)
```
Parent of 4 (i=4): (4-1)/2 = 1 → value 3 ✓
Left child of 3 (i=1): 2*1+1 = 3 → value 5 ✓
Right child of 3 (i=1): 2*1+2 = 4 → value 4 ✓



### Heap Operations Complexity

| Operation | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| Insert | O(log n) | O(1) |
| Delete (root) | O(log n) | O(1) |
| Peek (root) | O(1) | O(1) |
| Search | O(n) | O(1) |
| Build Heap | O(n) | O(1) |

### Java PriorityQueue

```java
// Min Heap (default)
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// Max Heap
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

// Custom Comparator
PriorityQueue<Integer> maxHeap2 = new PriorityQueue<>((a, b) -> b - a);

// Common Operations
pq.add(element);      // Insert - O(log n)
pq.offer(element);    // Insert - O(log n)
pq.poll();            // Remove and return root - O(log n)
pq.peek();            // Return root without removing - O(1)
pq.size();            // Get size - O(1)
pq.isEmpty();         // Check if empty - O(1)
pq.remove(element);   // Remove specific element - O(n)
```

---

## 💻 Java Code Examples

### Problem 1: Implement Min Heap from Scratch

**Question:** Implement a min heap data structure with insert, delete, and peek operations.

```java
/**
 * Problem: Implement Min Heap from Scratch
 * 
 * Visual (Insert 3, 1, 6, 5, 2, 4):
 * 
 * Insert 3:
 *     3
 * 
 * Insert 1:
 *     3
 *    /
 *   1
 *   Swap (1 < 3):
 *     1
 *    /
 *   3
 * 
 * Insert 6:
 *     1
 *    / \
 *   3   6
 * 
 * Insert 5:
 *     1
 *    / \
 *   3   6
 *  /
 * 5
 * 
 * Insert 2:
 *     1
 *    / \
 *   3   6
 *  / \
 * 5   2
 *   Swap (2 < 5):
 *     1
 *    / \
 *   3   6
 *  / \
 * 2   5
 *   Swap (2 < 3):
 *     1
 *    / \
 *   2   6
 *  / \
 * 3   5
 * 
 * Insert 4:
 *     1
 *    / \
 *   2   6
 *  / \ /
 * 3  5 4
 * 
 * Time Complexity:
 * - Insert: O(log n)
 * - Delete: O(log n)
 * - Peek: O(1)
 * 
 * Space Complexity: O(n)
 */
public class MinHeapImplementation {
    
    private int[] heap;
    private int size;
    private int capacity;
    
    public MinHeapImplementation(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }
    
    // Get parent index
    private int parent(int i) {
        return (i - 1) / 2;
    }
    
    // Get left child index
    private int leftChild(int i) {
        return 2 * i + 1;
    }
    
    // Get right child index
    private int rightChild(int i) {
        return 2 * i + 2;
    }
    
    // Swap two elements
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    
    // Heapify up (after insert)
    private void heapifyUp(int i) {
        while (i > 0 && heap[parent(i)] > heap[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }
    
    // Heapify down (after delete)
    private void heapifyDown(int i) {
        int smallest = i;
        int left = leftChild(i);
        int right = rightChild(i);
        
        // Find smallest among node and its children
        if (left < size && heap[left] < heap[smallest]) {
            smallest = left;
        }
        
        if (right < size && heap[right] < heap[smallest]) {
            smallest = right;
        }
        
        // If smallest is not current node, swap and continue
        if (smallest != i) {
            swap(i, smallest);
            heapifyDown(smallest);
        }
    }
    
    // Insert element - O(log n)
    public void insert(int value) {
        if (size == capacity) {
            System.out.println("Heap is full");
            return;
        }
        
        // Add at end
        heap[size] = value;
        size++;
        
        // Heapify up
        heapifyUp(size - 1);
    }
    
    // Delete root (minimum) - O(log n)
    public int deleteMin() {
        if (size == 0) {
            System.out.println("Heap is empty");
            return -1;
        }
        
        int min = heap;
        
        // Move last element to root
        heap = heap[size - 1];
        size--;
        
        // Heapify down
        heapifyDown(0);
        
        return min;
    }
    
    // Peek minimum - O(1)
    public int peekMin() {
        if (size == 0) {
            System.out.println("Heap is empty");
            return -1;
        }
        return heap;
    }
    
    // Get size
    public int size() {
        return size;
    }
    
    // Check if empty
    public boolean isEmpty() {
        return size == 0;
    }
    
    // Display heap (array representation)
    public void display() {
        System.out.print("Heap: ");
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
    
    // Display as tree
    public void displayTree() {
        if (size == 0) {
            System.out.println("Heap is empty");
            return;
        }
        
        int level = 0;
        int nodesInLevel = 1;
        int count = 0;
        
        System.out.print("Level " + level + ": ");
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
            count++;
            
            if (count == nodesInLevel) {
                System.out.println();
                level++;
                nodesInLevel *= 2;
                count = 0;
                if (i < size - 1) {
                    System.out.print("Level " + level + ": ");
                }
            }
        }
    }
    
    public static void main(String[] args) {
        MinHeapImplementation heap = new MinHeapImplementation(20);
        
        int[] values = {3, 1, 6, 5, 2, 4};
        
        System.out.println("Inserting: " + java.util.Arrays.toString(values));
        for (int val : values) {
            heap.insert(val);
        }
        
        System.out.println("\nHeap (array):");
        heap.display();
        
        System.out.println("\nHeap (tree):");
        heap.displayTree();
        
        System.out.println("\nMinimum: " + heap.peekMin());
        
        System.out.println("\nDeleting minimums:");
        while (!heap.isEmpty()) {
            System.out.print(heap.deleteMin() + " ");
        }
        System.out.println();
    }
}
```

---

### Problem 2: Implement Max Heap

**Question:** Implement a max heap data structure.

```java
/**
 * Problem: Implement Max Heap from Scratch
 * 
 * Visual:
 * Insert 10, 20, 15, 30, 40
 * 
 * Final Max Heap:
 *        40
 *       /  \
 *     30    20
 *    /  \
 *   10  15
 * 
 * Array:[10][15][20][30][40]
 * 
 * Time Complexity: Same as Min Heap
 * Space Complexity: O(n)
 */
public class MaxHeapImplementation {
    
    private int[] heap;
    private int size;
    private int capacity;
    
    public MaxHeapImplementation(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }
    
    private int parent(int i) {
        return (i - 1) / 2;
    }
    
    private int leftChild(int i) {
        return 2 * i + 1;
    }
    
    private int rightChild(int i) {
        return 2 * i + 2;
    }
    
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    
    // Heapify up for max heap (parent > child)
    private void heapifyUp(int i) {
        while (i > 0 && heap[parent(i)] < heap[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }
    
    // Heapify down for max heap
    private void heapifyDown(int i) {
        int largest = i;
        int left = leftChild(i);
        int right = rightChild(i);
        
        // Find largest among node and its children
        if (left < size && heap[left] > heap[largest]) {
            largest = left;
        }
        
        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }
        
        if (largest != i) {
            swap(i, largest);
            heapifyDown(largest);
        }
    }
    
    // Insert - O(log n)
    public void insert(int value) {
        if (size == capacity) {
            System.out.println("Heap is full");
            return;
        }
        
        heap[size] = value;
        size++;
        heapifyUp(size - 1);
    }
    
    // Delete maximum - O(log n)
    public int deleteMax() {
        if (size == 0) {
            System.out.println("Heap is empty");
            return -1;
        }
        
        int max = heap;
        heap = heap[size - 1];
        size--;
        heapifyDown(0);
        
        return max;
    }
    
    // Peek maximum - O(1)
    public int peekMax() {
        if (size == 0) {
            System.out.println("Heap is empty");
            return -1;
        }
        return heap;
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public void display() {
        System.out.print("Max Heap: ");
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        MaxHeapImplementation heap = new MaxHeapImplementation(20);
        
        int[] values = {10, 20, 15, 30, 40};
        
        System.out.println("Inserting: " + java.util.Arrays.toString(values));
        for (int val : values) {
            heap.insert(val);
        }
        
        heap.display();
        
        System.out.println("Maximum: " + heap.peekMax());
        
        System.out.print("Deleting maximums: ");
        while (!heap.isEmpty()) {
            System.out.print(heap.deleteMax() + " ");
        }
        System.out.println();
    }
}
```

---

### Problem 3: Heap Sort

**Question:** Sort an array using heap sort algorithm.

```java
/**
 * Problem: Heap Sort
 * LeetCode: Sort Colors (similar concept)
 * 
 * Visual (Max Heap Sort):
 * Array:[1][3][4][5][10]
 * 
 * Step 1: Build Max Heap
 *        10
 *       /  \
 *      5    3
 *     / \
 *    4   1
 * Array:[1][3][4][5][10]
 * 
 * Step 2: Swap root with last, reduce heap size
 * Swap 10 and 1:
 *        1
 *       /  \
 *      5    3
 *     / 
 *    4   
 * Heapify:
 *        5
 *       /  \
 *      4    3
 *     / 
 *    1   
 * Array:  (10 is sorted)[1][3][4][5][10]
 * 
 * Step 3: Repeat
 * Swap 5 and 1:
 *        1
 *       /  \
 *      4    3
 * 
 * Heapify:
 *        4
 *       /  \
 *      1    3
 * Array:[1][3][4][5][10]
 * 
 * Continue until sorted:[1][3][4][5][10]
 * 
 * Time Complexity: O(n log n)
 * Space Complexity: O(1)
 */
public class HeapSort {
    
    // Heap Sort using Max Heap
    public static void heapSort(int[] arr) {
        int n = arr.length;
        
        // Step 1: Build Max Heap - O(n)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        
        // Step 2: Extract elements one by one - O(n log n)
        for (int i = n - 1; i > 0; i--) {
            // Swap root (maximum) with last element
            swap(arr, 0, i);
            
            // Heapify reduced heap
            heapify(arr, i, 0);
        }
    }
    
    // Heapify subtree rooted at index i
    // n is size of heap
    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        // Find largest among root, left, right
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        
        // If largest is not root
        if (largest != i) {
            swap(arr, i, largest);
            
            // Recursively heapify affected subtree
            heapify(arr, n, largest);
        }
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    // Min Heap Sort (for descending order)
    public static void heapSortDescending(int[] arr) {
        int n = arr.length;
        
        // Build Min Heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            minHeapify(arr, n, i);
        }
        
        // Extract elements
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
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
            swap(arr, i, smallest);
            minHeapify(arr, n, smallest);
        }
    }
    
    public static void main(String[] args) {
        int[] arr1 = {4, 10, 3, 5, 1};
        System.out.println("Original: " + java.util.Arrays.toString(arr1));
        
        heapSort(arr1);
        System.out.println("Sorted (Ascending): " + java.util.Arrays.toString(arr1));
        
        int[] arr2 = {12, 11, 13, 5, 6, 7};
        System.out.println("\nOriginal: " + java.util.Arrays.toString(arr2));
        
        heapSort(arr2);
        System.out.println("Sorted: " + java.util.Arrays.toString(arr2));
    }
}
```

---

### Problem 4: Kth Largest Element in Array

**Question:** Find the kth largest element in an unsorted array.

```java
/**
 * Problem: Kth Largest Element in an Array
 * LeetCode: 215
 * 
 * Visual:
 * nums =, k = 2[1][2][3][4][5][6]
 * 
 * Approach 1: Min Heap of size k
 * 
 * Process elements:
 * Add 3:[1]
 * Add 2:  -> heapify ->[2][3]
 * Add 1:  -> heapify ->[1][2][3]
 * Add 5:  -> size > k, remove min ->[1][2][3][5]
 * Add 6:  -> remove min ->[2][3][5][6]
 * Add 4:  -> remove min ->[3][4][5][6]
 * 
 * Result: Root of min heap = 4 (2nd largest)
 * 
 * Approach 2: Max Heap
 * Build max heap of all elements, poll k-1 times
 * 
 * Time Complexity:
 * - Min Heap: O(n log k)
 * - Max Heap: O(n + k log n)
 * - Quick Select: O(n) average
 * 
 * Space Complexity: O(k) or O(n)
 */
public class KthLargestElement {
    
    // Approach 1: Min Heap - O(n log k)
    public static int findKthLargestMinHeap(int[] nums, int k) {
        java.util.PriorityQueue<Integer> minHeap = 
            new java.util.PriorityQueue<>(k);
        
        for (int num : nums) {
            minHeap.add(num);
            
            // If heap size exceeds k, remove smallest
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        // Root is kth largest
        return minHeap.peek();
    }
    
    // Approach 2: Max Heap - O(n + k log n)
    public static int findKthLargestMaxHeap(int[] nums, int k) {
        java.util.PriorityQueue<Integer> maxHeap = 
            new java.util.PriorityQueue<>(Collections.reverseOrder());
        
        // Add all elements - O(n)
        for (int num : nums) {
            maxHeap.add(num);
        }
        
        // Remove k-1 largest elements
        for (int i = 0; i < k - 1; i++) {
            maxHeap.poll();
        }
        
        // kth largest is now at root
        return maxHeap.peek();
    }
    
    // Approach 3: Sorting - O(n log n)
    public static int findKthLargestSorting(int[] nums, int k) {
        java.util.Arrays.sort(nums);
        return nums[nums.length - k];
    }
    
    // Approach 4: Quick Select - O(n) average, O(n²) worst
    public static int findKthLargestQuickSelect(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }
    
    private static int quickSelect(int[] arr, int left, int right, int kSmallest) {
        if (left == right) {
            return arr[left];
        }
        
        int pivotIndex = partition(arr, left, right);
        
        if (kSmallest == pivotIndex) {
            return arr[kSmallest];
        } else if (kSmallest < pivotIndex) {
            return quickSelect(arr, left, pivotIndex - 1, kSmallest);
        } else {
            return quickSelect(arr, pivotIndex + 1, right, kSmallest);
        }
    }
    
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;
        
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        
        swap(arr, i, right);
        return i;
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        System.out.println(k + "th largest element:");
        System.out.println("Min Heap: " + findKthLargestMinHeap(nums, k));
        System.out.println("Max Heap: " + findKthLargestMaxHeap(nums, k));
        System.out.println("Sorting: " + findKthLargestSorting(nums.clone(), k));
        System.out.println("Quick Select: " + findKthLargestQuickSelect(nums.clone(), k));
    }
}
```

---

### Problem 5: Top K Frequent Elements

**Question:** Given an integer array nums and an integer k, return the k most frequent elements.

```java
/**
 * Problem: Top K Frequent Elements
 * LeetCode: 347
 * 
 * Visual:
 * nums =, k = 2[1][2][3]
 * 
 * Frequency Map:
 * 1 -> 3
 * 2 -> 2
 * 3 -> 1
 * 
 * Approach 1: Min Heap of size k
 * 
 * Add (1, freq=3): [(1, 3)]
 * Add (2, freq=2): [(1, 3), (2, 2)]
 * Add (3, freq=1): [(1, 3), (2, 2), (3, 1)] -> size > k
 *                    Remove min freq: [(1, 3), (2, 2)]
 * 
 * Result:[1][2]
 * 
 * Approach 2: Bucket Sort
 * Index (frequency): 0    1    2    3
 *                    [][1][2][3]
 * 
 * Read from right:[1][2]
 * 
 * Time Complexity:
 * - Min Heap: O(n log k)
 * - Bucket Sort: O(n)
 * 
 * Space Complexity: O(n)
 */
public class TopKFrequentElements {
    
    // Approach 1: Min Heap - O(n log k)
    public static int[] topKFrequentHeap(int[] nums, int k) {
        // Count frequencies - O(n)
        java.util.Map<Integer, Integer> freqMap = new java.util.HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        // Min heap based on frequency - O(n log k)
        java.util.PriorityQueue<Integer> minHeap = 
            new java.util.PriorityQueue<>((a, b) -> freqMap.get(a) - freqMap.get(b));
        
        for (int num : freqMap.keySet()) {
            minHeap.add(num);
            
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        // Build result
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll();
        }
        
        return result;
    }
    
    // Approach 2: Bucket Sort - O(n)
    public static int[] topKFrequentBucket(int[] nums, int k) {
        // Count frequencies - O(n)
        java.util.Map<Integer, Integer> freqMap = new java.util.HashMap<>();
        int maxFreq = 0;
        
        for (int num : nums) {
            int freq = freqMap.getOrDefault(num, 0) + 1;
            freqMap.put(num, freq);
            maxFreq = Math.max(maxFreq, freq);
        }
        
        // Create buckets - O(n)
        java.util.List<Integer>[] buckets = new java.util.ArrayList[maxFreq + 1];
        for (int i = 0; i <= maxFreq; i++) {
            buckets[i] = new java.util.ArrayList<>();
        }
        
        for (int num : freqMap.keySet()) {
            int freq = freqMap.get(num);
            buckets[freq].add(num);
        }
        
        // Collect top k frequent elements - O(n)
        int[] result = new int[k];
        int idx = 0;
        
        for (int i = maxFreq; i >= 1 && idx < k; i--) {
            for (int num : buckets[i]) {
                result[idx++] = num;
                if (idx == k) break;
            }
        }
        
        return result;
    }
    
    // Approach 3: Sorting - O(n log n)
    public static int[] topKFrequentSorting(int[] nums, int k) {
        java.util.Map<Integer, Integer> freqMap = new java.util.HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        // Sort by frequency (descending)
        java.util.List<Integer> list = new java.util.ArrayList<>(freqMap.keySet());
        list.sort((a, b) -> freqMap.get(b) - freqMap.get(a));
        
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = list.get(i);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        System.out.println("Top " + k + " frequent elements:");
        System.out.println("Heap: " + java.util.Arrays.toString(topKFrequentHeap(nums, k)));
        System.out.println("Bucket: " + java.util.Arrays.toString(topKFrequentBucket(nums, k)));
        System.out.println("Sorting: " + java.util.Arrays.toString(topKFrequentSorting(nums.clone(), k)));
    }
}
```

---

### Problem 6: Merge K Sorted Lists

**Question:** You are given an array of k linked-lists, each sorted in ascending order. Merge all the linked-lists into one sorted linked-list.

```java
/**
 * Problem: Merge K Sorted Linked Lists
 * LeetCode: 23
 * 
 * Visual:
 * lists = [
 *   1 -> 4 -> 5,
 *   1 -> 3 -> 4,
 *   2 -> 6
 * ]
 * 
 * Approach 1: Min Heap
 * 
 * Initial heap: [1 (list0), 1 (list1), 2 (list2)]
 * 
 * Step 1: Pop 1 (list0), add 4 (list0)
 * Result: 1
 * Heap: [1 (list1), 2 (list2), 4 (list0)]
 * 
 * Step 2: Pop 1 (list1), add 3 (list1)
 * Result: 1 -> 1
 * Heap: [2 (list2), 3 (list1), 4 (list0)]
 * 
 * Step 3: Pop 2 (list2), add 6 (list2)
 * Result: 1 -> 1 -> 2
 * Heap: [3 (list1), 4 (list0), 6 (list2)]
 * 
 * Continue...
 * Final: 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6
 * 
 * Time Complexity: O(N log k)
 * where N = total number of nodes
 * 
 * Space Complexity: O(k) for heap
 */
public class MergeKSortedLists {
    
    static class ListNode {
        int val;
        ListNode next;
        
        ListNode(int val) {
            this.val = val;
        }
    }
    
    // Approach 1: Min Heap - O(N log k)
    public static ListNode mergeKListsHeap(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        // Min heap based on node value
        java.util.PriorityQueue<ListNode> minHeap = 
            new java.util.PriorityQueue<>((a, b) -> a.val - b.val);
        
        // Add head of each list - O(k)
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.add(node);
            }
        }
        
        // Dummy head
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        // Process all nodes - O(N log k)
        while (!minHeap.isEmpty()) {
            ListNode smallest = minHeap.poll();
            current.next = smallest;
            current = current.next;
            
            // Add next node from same list
            if (smallest.next != null) {
                minHeap.add(smallest.next);
            }
        }
        
        return dummy.next;
    }
    
    // Approach 2: Divide and Conquer - O(N log k)
    public static ListNode mergeKListsDivideConquer(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        return mergeRange(lists, 0, lists.length - 1);
    }
    
    private static ListNode mergeRange(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        
        int mid = (left + right) / 2;
        
        ListNode l1 = mergeRange(lists, left, mid);
        ListNode l2 = mergeRange(lists, mid + 1, right);
        
        return mergeTwoLists(l1, l2);
    }
    
    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        
        current.next = (l1 != null) ? l1 : l2;
        
        return dummy.next;
    }
    
    // Helper to create list
    public static ListNode createList(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        ListNode head = new ListNode(arr);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }
    
    // Helper to display
    public static void display(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        ListNode[] lists = {
            createList(new int[]{1, 4, 5}),
            createList(new int[]{1, 3, 4}),
            createList(new int[]{2, 6})
        };
        
        System.out.println("List 1: ");
        display(lists);
        
        System.out.println("List 2: ");
        display(lists);[3]
        
        System.out.println("List 3: ");
        display(lists);[2]
        
        System.out.println("\nMerged (Heap):");
        ListNode merged1 = mergeKListsHeap(lists);
        display(merged1);
        
        System.out.println("Merged (Divide & Conquer):");
        ListNode merged2 = mergeKListsDivideConquer(lists);
        display(merged2);
    }
}
```

---

### Problem 7: Find Median from Data Stream

**Question:** Design a data structure that finds the median from a data stream.

```java
/**
 * Problem: Find Median from Data Stream
 * LeetCode: 295
 * 
 * Visual:
 * Add numbers: 1, 2, 3, 4, 5
 * 
 * Two Heaps Approach:
 * 
 * Max Heap (left): stores smaller half
 * Min Heap (right): stores larger half
 * 
 * After adding 1, 2, 3:
 * Max Heap:   (root = 2)[1][2]
 * Min Heap:      (root = 3)[1]
 * Median: 2
 * 
 * After adding 4:
 * Max Heap:[1][2]
 * Min Heap:[3][4]
 * Median: (2 + 3) / 2 = 2.5
 * 
 * After adding 5:
 * Max Heap:[1][2][3]
 * Min Heap:[4][5]
 * Median: 3
 * 
 * Time Complexity:
 * - addNum: O(log n)
 * - findMedian: O(1)
 * 
 * Space Complexity: O(n)
 */
public class MedianFinder {
    
    static class MedianFinderClass {
        private java.util.PriorityQueue<Integer> maxHeap; // Left half
        private java.util.PriorityQueue<Integer> minHeap; // Right half
        
        public MedianFinderClass() {
            // Max heap for left half
            maxHeap = new java.util.PriorityQueue<>(Collections.reverseOrder());
            // Min heap for right half
            minHeap = new java.util.PriorityQueue<>();
        }
        
        // Add number - O(log n)
        public void addNum(int num) {
            // Add to max heap first
            maxHeap.add(num);
            
            // Balance: max heap's max should be <= min heap's min
            if (!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                minHeap.add(maxHeap.poll());
            }
            
            // Rebalance sizes (max heap can have at most 1 more element)
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.poll());
            }
        }
        
        // Find median - O(1)
        public double findMedian() {
            if (maxHeap.size() == minHeap.size()) {
                // Even number of elements
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            } else {
                // Odd number of elements (max heap has 1 more)
                return maxHeap.peek();
            }
        }
    }
    
    public static void main(String[] args) {
        MedianFinderClass mf = new MedianFinderClass();
        
        mf.addNum(1);
        System.out.println("Median after adding 1: " + mf.findMedian()); // 1.0
        
        mf.addNum(2);
        System.out.println("Median after adding 2: " + mf.findMedian()); // 1.5
        
        mf.addNum(3);
        System.out.println("Median after adding 3: " + mf.findMedian()); // 2.0
        
        mf.addNum(4);
        System.out.println("Median after adding 4: " + mf.findMedian()); // 2.5
        
        mf.addNum(5);
        System.out.println("Median after adding 5: " + mf.findMedian()); // 3.0
    }
}
```

---

### Problem 8: K Closest Points to Origin

**Question:** Given an array of points where points[i] = [x, y] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).

```java
/**
 * Problem: K Closest Points to Origin
 * LeetCode: 973
 * 
 * Visual:
 * points = [, [-2, 2], [2, -2]], k = 2[1][3]
 * 
 * Distances:
 *:   √(1² + 3²) = √10 ≈ 3.16[1][3]
 * [-2, 2]:  √((-2)² + 2²) = √8 ≈ 2.83
 * [2, -2]:  √(2² + (-2)²) = √8 ≈ 2.83
 * 
 * Approach 1: Max Heap of size k
 * 
 * Add:   [(1, 3)] dist=10[1][3]
 * Add [-2, 2]:  [(1, 3), (-2, 2)] dist=[8][10]
 * Add [2, -2]:  [(1, 3), (-2, 2), (2, -2)] -> size > k
 *               Remove max dist: [(-2, 2), (2, -2)]
 * 
 * Result: [[-2, 2], [2, -2]]
 * 
 * Time Complexity: O(n log k)
 * Space Complexity: O(k)
 */
public class KClosestPoints {
    
    // Approach 1: Max Heap - O(n log k)
    public static int[][] kClosestMaxHeap(int[][] points, int k) {
        // Max heap based on distance (compare by distance squared)
        java.util.PriorityQueue<int[]> maxHeap = 
            new java.util.PriorityQueue<>((a, b) -> 
                distanceSquared(b) - distanceSquared(a));
        
        for (int[] point : points) {
            maxHeap.add(point);
            
            if (maxHeap.size() > k) {
                maxHeap.poll(); // Remove farthest point
            }
        }
        
        // Build result
        int[][] result = new int[k];[2]
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }
        
        return result;
    }
    
    // Approach 2: Quick Select - O(n) average
    public static int[][] kClosestQuickSelect(int[][] points, int k) {
        quickSelect(points, 0, points.length - 1, k);
        
        int[][] result = new int[k];[2]
        for (int i = 0; i < k; i++) {
            result[i] = points[i];
        }
        
        return result;
    }
    
    private static void quickSelect(int[][] points, int left, int right, int k) {
        if (left >= right) return;
        
        int pivotIndex = partition(points, left, right);
        
        if (pivotIndex == k) {
            return;
        } else if (pivotIndex < k) {
            quickSelect(points, pivotIndex + 1, right, k);
        } else {
            quickSelect(points, left, pivotIndex - 1, k);
        }
    }
    
    private static int partition(int[][] points, int left, int right) {
        int[] pivot = points[right];
        int pivotDist = distanceSquared(pivot);
        int i = left;
        
        for (int j = left; j < right; j++) {
            if (distanceSquared(points[j]) <= pivotDist) {
                swap(points, i, j);
                i++;
            }
        }
        
        swap(points, i, right);
        return i;
    }
    
    private static int distanceSquared(int[] point) {
        return point * point + point * point;[3]
    }
    
    private static void swap(int[][] points, int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }
    
    public static void main(String[] args) {
        int[][] points = {{1, 3}, {-2, 2}, {2, -2}};
        int k = 2;
        
        System.out.println("Points: " + java.util.Arrays.deepToString(points));
        System.out.println("K = " + k);
        
        int[][] result1 = kClosestMaxHeap(points.clone(), k);
        System.out.println("K Closest (Max Heap): " + 
                          java.util.Arrays.deepToString(result1));
        
        int[][] result2 = kClosestQuickSelect(points.clone(), k);
        System.out.println("K Closest (Quick Select): " + 
                          java.util.Arrays.deepToString(result2));
    }
}
```

---

## 📝 Practice Problems

### Easy
| Problem | Pattern | Link |
|---------|---------|------|
| Kth Largest Element in a Stream | Min Heap | LeetCode 703 |
| Last Stone Weight | Max Heap | LeetCode 1046 |
| K Closest Points to Origin | Max Heap | LeetCode 973 |

### Medium
| Problem | Pattern | Link |
|---------|---------|------|
| Kth Largest Element in Array | Heap/Quick Select | LeetCode 215 |
| Top K Frequent Elements | Heap/Bucket Sort | LeetCode 347 |
| Merge K Sorted Lists | Heap/Divide & Conquer | LeetCode 23 |
| Find Median from Data Stream | Two Heaps | LeetCode 295 |
| Task Scheduler | Max Heap + Greedy | LeetCode 621 |
| Reorganize String | Max Heap | LeetCode 767 |

### Hard
| Problem | Pattern | Link |
|---------|---------|------|
| Sliding Window Maximum | Monotonic Queue | LeetCode 239 |
| Median of Two Sorted Arrays | Binary Search | LeetCode 4 |
| Find K Pairs with Smallest Sums | Min Heap | LeetCode 373 |
| Design Twitter | Heap + HashMap | LeetCode 355 |

---

## ✅ Key Takeaways

1. **Min Heap** - Use for kth largest, top k frequent
2. **Max Heap** - Use for kth smallest, k closest points
3. **Two Heaps** - Use for median finding problems
4. **Heap + HashMap** - Use for frequency-based problems
5. **Build Heap** - O(n), not O(n log n)
6. **Heap Sort** - In-place O(n log n) sorting

---

**Previous:** [Trees](../trees/01-theory.md)  
**Next:** [Graphs](../graphs/01-theory.md)