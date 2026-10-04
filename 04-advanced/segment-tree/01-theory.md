# 🌳 Segment Tree - Complete Guide

## 📚 Theory

### What is Segment Tree?

A **Segment Tree** is a tree-based data structure used for storing information about intervals or segments. It allows querying which of the stored segments contain a given point, or finding the minimum/maximum/sum in a range efficiently.

### When to Use Segment Tree?

Use segment tree when:
1. Need to perform **range queries** (sum, min, max, etc.)
2. Need to perform **point updates** or **range updates**
3. Array is **static or dynamic**
4. Need **O(log n)** query and update time

### Segment Tree Properties


Perfect! Chalte hain Segment Tree ke saath - one of the most powerful data structures for range queries!

File 26: 04-advanced/segment-tree/01-theory.md
text
# 🌳 Segment Tree - Complete Guide

## 📚 Theory

### What is Segment Tree?

A **Segment Tree** is a tree-based data structure used for storing information about intervals or segments. It allows querying which of the stored segments contain a given point, or finding the minimum/maximum/sum in a range efficiently.

### When to Use Segment Tree?

Use segment tree when:
1. Need to perform **range queries** (sum, min, max, etc.)
2. Need to perform **point updates** or **range updates**
3. Array is **static or dynamic**
4. Need **O(log n)** query and update time

### Segment Tree Properties
### 1. Binary Tree Structure

* Each node represents a segment [L, R]

* Root represents entire array [0, n-1]

* Leaf nodes represent single elements

### 2. Node Representation

* Node i covers range [L, R]

* Left child: 2*i + 1, covers [L, mid]

* Right child: 2*i + 2, covers [mid+1, R]

* mid = (L + R) / 2

### 3. Tree Size

* For array of size n

* Tree height: ⌈log₂n⌉

* Total nodes: 2^(⌈log₂n⌉+1) - 1 ≈ 4n

### 4. Time Complexity

* Build: O(n)

* Query: O(log n)

* Update: O(log n)

### 5. Space Complexity: O(n)


### Segment Tree Visualization
---
``` 
Array:

Indices: 0 1 2 3 4 5

Segment Tree (Sum):

[0-5, sum=36]
/
[0-2, sum=9] [3-5, sum=27]
/ \ /
[0-1, sum=4] [2-2, sum=5] [3-4, sum=16] [5-5, sum=11]
/ \ /
[0-0,1] [1-1,3] [3-3,7] [4-4,9]

Query: Sum from index 1 to 4
= tree[node_for_1-2] + tree[node_for_3-4]
= (3+5) + (7+9) = 24

Update: Index 2 to value 10
Update path: [2-2] → [0-2] → [0-5]
New sum at [2-2]: 10
New sum at [0-2]: 1+3+10 = 14
New sum at [0-5]: 14+27 = 41


```
---
### Types of Segment Trees

1. **Sum Segment Tree** - Range sum queries
2. **Min/Max Segment Tree** - Range minimum/maximum queries
3. **Lazy Propagation** - Range updates
4. **Persistent Segment Tree** - Historical versions
5. **2D Segment Tree** - 2D range queries

---

## 💻 Java Code Examples

### Problem 1: Segment Tree - Range Sum Query

```java
/**
 * Problem: Range Sum Query using Segment Tree
 * LeetCode: 307 (similar)
 * 
 * Visual:
 * Array:[1][3][5][7][9][11]
 * 
 * Build Tree:
 * 
 * Step 1: Create tree array of size 4*n
 * tree = new int
 * 
 * Step 2: Build recursively
 * build(0, 0, 5)
 *   build(1, 0, 2)
 *     build(3, 0, 1)
 *       build(7, 0, 0) → tree = 1[1]
 *       build(8, 1, 1) → tree = 3
 *       tree = 1 + 3 = 4
 *     build(4, 2, 2) → tree = 5
 *     tree = 4 + 5 = 9
 *   build(2, 3, 5)
 *     ...
 * 
 * Query: sumRange(1, 4)
 * = query(0, 0, 5, 1, 4)
 * = query(1, 0, 2, 1, 4) + query(2, 3, 5, 1, 4)
 * = (query(3, 0, 1, 1, 4) + query(4, 2, 2, 1, 4)) + ...
 * = (3 + 5) + (7 + 9) = 24
 * 
 * Time Complexity:
 * - Build: O(n)
 * - Query: O(log n)
 * - Update: O(log n)
 * 
 * Space Complexity: O(n)
 */
public class SegmentTreeSum {
    
    private int[] tree;
    private int[] arr;
    private int n;
    
    // Constructor
    public SegmentTreeSum(int[] arr) {
        this.arr = arr.clone();
        this.n = arr.length;
        this.tree = new int[4 * n]; // Size 4*n is safe upper bound
        
        if (n > 0) {
            build(0, 0, n - 1);
        }
    }
    
    // Build segment tree
    private void build(int node, int start, int end) {
        if (start == end) {
            // Leaf node
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            int leftChild = 2 * node + 1;
            int rightChild = 2 * node + 2;
            
            // Build left subtree
            build(leftChild, start, mid);
            
            // Build right subtree
            build(rightChild, mid + 1, end);
            
            // Internal node = sum of children
            tree[node] = tree[leftChild] + tree[rightChild];
        }
    }
    
    // Range sum query
    public int sumRange(int left, int right) {
        if (left < 0 || right >= n || left > right) {
            return 0;
        }
        return query(0, 0, n - 1, left, right);
    }
    
    private int query(int node, int start, int end, int left, int right) {
        // Case 1: Current segment is completely outside query range
        if (right < start || end < left) {
            return 0;
        }
        
        // Case 2: Current segment is completely inside query range
        if (left <= start && end <= right) {
            return tree[node];
        }
        
        // Case 3: Current segment partially overlaps with query range
        int mid = (start + end) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;
        
        int leftSum = query(leftChild, start, mid, left, right);
        int rightSum = query(rightChild, mid + 1, end, left, right);
        
        return leftSum + rightSum;
    }
    
    // Point update
    public void update(int index, int value) {
        if (index < 0 || index >= n) {
            return;
        }
        update(0, 0, n - 1, index, value);
    }
    
    private void update(int node, int start, int end, int index, int value) {
        if (start == end) {
            // Leaf node
            tree[node] = value;
            arr[index] = value;
        } else {
            int mid = (start + end) / 2;
            int leftChild = 2 * node + 1;
            int rightChild = 2 * node + 2;
            
            if (start <= index && index <= mid) {
                // Index is in left subtree
                update(leftChild, start, mid, index, value);
            } else {
                // Index is in right subtree
                update(rightChild, mid + 1, end, index, value);
            }
            
            // Update current node
            tree[node] = tree[leftChild] + tree[rightChild];
        }
    }
    
    // Print tree (for debugging)
    public void printTree() {
        System.out.println("Segment Tree:");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != 0) {
                System.out.println("Node " + i + ": " + tree[i]);
            }
        }
    }
    
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        
        System.out.println("Array: " + java.util.Arrays.toString(arr));
        
        SegmentTreeSum segTree = new SegmentTreeSum(arr);
        
        System.out.println("\nSegment Tree:");
        segTree.printTree();
        
        // Range sum queries
        System.out.println("\nRange Sum Queries:");
        System.out.println("Sum: " + segTree.sumRange(0, 5)); // 36[0][5]
        System.out.println("Sum: " + segTree.sumRange(1, 4)); // 24[1][4]
        System.out.println("Sum: " + segTree.sumRange(0, 2)); // 9[0][2]
        System.out.println("Sum: " + segTree.sumRange(3, 5)); // 27[3][5]
        
        // Update
        System.out.println("\nUpdate index 2 to 10:");
        segTree.update(2, 10);
        System.out.println("Sum: " + segTree.sumRange(0, 2)); // 14[0][2]
        System.out.println("Sum: " + segTree.sumRange(0, 5)); // 41[0][5]
    }
}
```

---

### Problem 2: Segment Tree - Range Minimum Query

```java
/**
 * Problem: Range Minimum Query using Segment Tree
 * 
 * Visual:
 * Array:[1][2][3][4][5][6]
 * 
 * Segment Tree (Min):
 * 
 *                    [0-5, min=1]
 *                   /           \
 *          [0-2, min=2]      [3-5, min=1]
 *          /        \          /        \
 *    [0-1, min=2] [2-2, min=6]  [3-4, min=1]  [5-5, min=3]
 *    /      \                    /      \
 *[0-0,4] [1-1,2]            [3-3,1] [4-4,5]
 * 
 * Query: minRange(1, 4)
 * = min(tree[node_for_1-2], tree[node_for_3-4])
 * = min(2, 1) = 1
 * 
 * Time Complexity:
 * - Build: O(n)
 * - Query: O(log n)
 * - Update: O(log n)
 * 
 * Space Complexity: O(n)
 */
public class SegmentTreeMin {
    
    private int[] tree;
    private int[] arr;
    private int n;
    private static final int INF = Integer.MAX_VALUE;
    
    public SegmentTreeMin(int[] arr) {
        this.arr = arr.clone();
        this.n = arr.length;
        this.tree = new int[4 * n];
        
        if (n > 0) {
            build(0, 0, n - 1);
        }
    }
    
    // Build segment tree for minimum
    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            int leftChild = 2 * node + 1;
            int rightChild = 2 * node + 2;
            
            build(leftChild, start, mid);
            build(rightChild, mid + 1, end);
            
            tree[node] = Math.min(tree[leftChild], tree[rightChild]);
        }
    }
    
    // Range minimum query
    public int minRange(int left, int right) {
        if (left < 0 || right >= n || left > right) {
            return INF;
        }
        return query(0, 0, n - 1, left, right);
    }
    
    private int query(int node, int start, int end, int left, int right) {
        // Completely outside
        if (right < start || end < left) {
            return INF;
        }
        
        // Completely inside
        if (left <= start && end <= right) {
            return tree[node];
        }
        
        // Partially overlaps
        int mid = (start + end) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;
        
        int leftMin = query(leftChild, start, mid, left, right);
        int rightMin = query(rightChild, mid + 1, end, left, right);
        
        return Math.min(leftMin, rightMin);
    }
    
    // Point update
    public void update(int index, int value) {
        if (index < 0 || index >= n) {
            return;
        }
        update(0, 0, n - 1, index, value);
    }
    
    private void update(int node, int start, int end, int index, int value) {
        if (start == end) {
            tree[node] = value;
            arr[index] = value;
        } else {
            int mid = (start + end) / 2;
            int leftChild = 2 * node + 1;
            int rightChild = 2 * node + 2;
            
            if (index <= mid) {
                update(leftChild, start, mid, index, value);
            } else {
                update(rightChild, mid + 1, end, index, value);
            }
            
            tree[node] = Math.min(tree[leftChild], tree[rightChild]);
        }
    }
    
    public static void main(String[] args) {
        int[] arr = {4, 2, 6, 1, 5, 3};
        
        System.out.println("Array: " + java.util.Arrays.toString(arr));
        
        SegmentTreeMin segTree = new SegmentTreeMin(arr);
        
        System.out.println("\nRange Minimum Queries:");
        System.out.println("Min: " + segTree.minRange(0, 5)); // 1[0][5]
        System.out.println("Min: " + segTree.minRange(1, 4)); // 1[1][4]
        System.out.println("Min: " + segTree.minRange(0, 2)); // 2[0][2]
        System.out.println("Min: " + segTree.minRange(3, 5)); // 1[3][5]
        
        // Update
        System.out.println("\nUpdate index 3 to 0:");
        segTree.update(3, 0);
        System.out.println("Min: " + segTree.minRange(0, 5)); // 0[0][5]
    }
}
```

---

### Problem 3: Segment Tree with Lazy Propagation

```java
/**
 * Problem: Segment Tree with Lazy Propagation
 * (Range Update + Range Query)
 * 
 * Visual:
 * Array:[1][2][3][4][5]
 * 
 * Range Update: Add 2 to[1][3]
 * Array becomes:[4][5][6][1]
 * 
 * Lazy Propagation:
 * - Store pending updates in lazy array
 * - Apply update when visiting node
 * - Push lazy value to children
 * 
 * Time Complexity:
 * - Build: O(n)
 * - Query: O(log n)
 * - Range Update: O(log n)
 * 
 * Space Complexity: O(n)
 */
public class SegmentTreeLazy {
    
    private int[] tree;
    private int[] lazy;
    private int[] arr;
    private int n;
    
    public SegmentTreeLazy(int[] arr) {
        this.arr = arr.clone();
        this.n = arr.length;
        this.tree = new int[4 * n];
        this.lazy = new int[4 * n];
        
        if (n > 0) {
            build(0, 0, n - 1);
        }
    }
    
    // Build tree
    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(2 * node + 1, start, mid);
            build(2 * node + 2, mid + 1, end);
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }
    
    // Push lazy value to children
    private void push(int node, int start, int end) {
        if (lazy[node] != 0) {
            // Apply lazy value to current node
            tree[node] += lazy[node] * (end - start + 1);
            
            // If not leaf, push lazy to children
            if (start != end) {
                lazy[2 * node + 1] += lazy[node];
                lazy[2 * node + 2] += lazy[node];
            }
            
            // Clear lazy value
            lazy[node] = 0;
        }
    }
    
    // Range update: add value to [left, right]
    public void rangeUpdate(int left, int right, int value) {
        update(0, 0, n - 1, left, right, value);
    }
    
    private void update(int node, int start, int end, int left, int right, int value) {
        // Push lazy value
        push(node, start, end);
        
        // Completely outside
        if (right < start || end < left) {
            return;
        }
        
        // Completely inside
        if (left <= start && end <= right) {
            lazy[node] += value;
            push(node, start, end);
            return;
        }
        
        // Partially overlaps
        int mid = (start + end) / 2;
        update(2 * node + 1, start, mid, left, right, value);
        update(2 * node + 2, mid + 1, end, left, right, value);
        
        // Update current node
        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }
    
    // Range sum query
    public int sumRange(int left, int right) {
        return query(0, 0, n - 1, left, right);
    }
    
    private int query(int node, int start, int end, int left, int right) {
        // Push lazy value
        push(node, start, end);
        
        // Completely outside
        if (right < start || end < left) {
            return 0;
        }
        
        // Completely inside
        if (left <= start && end <= right) {
            return tree[node];
        }
        
        // Partially overlaps
        int mid = (start + end) / 2;
        int leftSum = query(2 * node + 1, start, mid, left, right);
        int rightSum = query(2 * node + 2, mid + 1, end, left, right);
        
        return leftSum + rightSum;
    }
    
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        
        System.out.println("Array: " + java.util.Arrays.toString(arr));
        
        SegmentTreeLazy segTree = new SegmentTreeLazy(arr);
        
        System.out.println("\nInitial sum: " + segTree.sumRange(0, 4)); // 15[0][4]
        
        // Range update: add 2 to[1][3]
        System.out.println("\nRange update: add 2 to ");[1][3]
        segTree.rangeUpdate(1, 3, 2);
        
        System.out.println("Sum: " + segTree.sumRange(0, 4)); // 21[0][4]
        System.out.println("Sum: " + segTree.sumRange(1, 3)); // 15[1][3]
        
        // Another update
        System.out.println("\nRange update: add 1 to ");[0][2]
        segTree.rangeUpdate(0, 2, 1);
        
        System.out.println("Sum: " + segTree.sumRange(0, 4)); // 24[0][4]
        System.out.println("Sum: " + segTree.sumRange(0, 2)); // 12[0][2]
    }
}
```

---

### Problem 4: Count of Smaller Numbers After Self

```java
/**
 * Problem: Count of Smaller Numbers After Self
 * LeetCode: 315
 * 
 * Visual:
 * nums =[1][2][5][6]
 * 
 * For each element, count smaller elements to its right:
 * 
 * i=0, nums=5:  → smaller: 2, 1 → count=2[1][2][6]
 * i=1, nums=2:  → smaller: 1 → count=1[1][6]
 * i=2, nums=6:  → smaller: 1 → count=1
 * i=3, nums=1: [] → smaller: none → count=0
 * 
 * Result:[0][1][2]
 * 
 * Segment Tree Approach:
 * 1. Process from right to left
 * 2. For each element:
 *    - Query count of numbers < current
 *    - Add current to segment tree
 * 
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */
public class CountSmallerNumbers {
    
    private int[] tree;
    private int size;
    
    // Update: add 1 at position idx
    private void update(int idx) {
        idx++; // 1-indexed
        while (idx <= size) {
            tree[idx]++;
            idx += idx & (-idx);
        }
    }
    
    // Query: sum from 1 to idx
    private int query(int idx) {
        idx++; // 1-indexed
        int sum = 0;
        while (idx > 0) {
            sum += tree[idx];
            idx -= idx & (-idx);
        }
        return sum;
    }
    
    public java.util.List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new java.util.ArrayList<>();
        }
        
        int n = nums.length;
        java.util.List<Integer> result = new java.util.ArrayList<>();
        
        // Find min and max for coordinate compression
        int min = nums, max = nums;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        // Coordinate compression
        size = max - min + 1;
        tree = new int[size + 1];
        
        // Process from right to left
        for (int i = n - 1; i >= 0; i--) {
            int num = nums[i] - min;
            
            // Query count of numbers smaller than current
            int count = query(num - 1);
            result.add(count);
            
            // Add current number to tree
            update(num);
        }
        
        // Reverse result
        java.util.Collections.reverse(result);
        return result;
    }
    
    public static void main(String[] args) {
        int[] nums = {5, 2, 6, 1};
        
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        
        CountSmallerNumbers solver = new CountSmallerNumbers();
        java.util.List<Integer> result = solver.countSmaller(nums);
        
        System.out.println("Count of smaller numbers after self: " + result);
        // Expected:[0][1][2]
    }
}
```

---

## 📝 Practice Problems

### Medium
| Problem | Pattern | Link |
|---------|---------|------|
| Range Sum Query | Segment Tree | LeetCode 307 |
| Count of Smaller Numbers After Self | Segment Tree | LeetCode 315 |
| Reverse Pairs | Segment Tree | LeetCode 493 |

### Hard
| Problem | Pattern | Link |
|---------|---------|------|
| Count of Smaller Numbers After Self | Segment Tree | LeetCode 315 |
| Range Sum Query - Mutable | Segment Tree | LeetCode 307 |
| My Calendar III | Segment Tree | LeetCode 732 |

---

## ✅ Key Takeaways

1. **Segment Tree** - Tree for range queries in O(log n)
2. **Build** - O(n), recursively build tree
3. **Query** - O(log n), traverse tree
4. **Update** - O(log n), update path to root
5. **Lazy Propagation** - Defer updates for range updates
6. **Size** - 4*n is safe upper bound
7. **Indexing** - Node i: left=2i+1, right=2i+2
8. **Applications** - Range sum, min, max, count inversions

---

**Previous:** [Matrix Problems](../matrix/01-theory.md)  
**Next:** [Trie](../trie/01-theory.md)