# 🔗 Union Find (Disjoint Set Union) - Complete Guide

## 📚 Theory

### What is Union Find?

**Union Find** (also called Disjoint Set Union - DSU) is a data structure that keeps track of elements partitioned into disjoint (non-overlapping) subsets. It efficiently supports two operations:
1. **Find** - Determine which subset an element belongs to
2. **Union** - Merge two subsets into one

### When to Use Union Find?

Use union find when:
1. Need to check if two elements are in the **same group**
2. Need to **merge groups** efficiently
3. Finding **connected components** in a graph
4. Detecting **cycles** in a graph
5. **Kruskal's Algorithm** for MST
6. **Dynamic connectivity** problems

### Union Find Operations


### 1. Find(x)

* Returns representative (root) of set containing x

* Elements with same root are in same set

* Uses path compression for optimization

### 2. Union(x, y)

* Merges sets containing x and y

* Uses union by rank/size for optimization

### 3. Connected(x, y)

* Returns true if x and y are in same set

* Find(x) == Find(y)


### Optimizations


### 1. Path Compression

* During Find, make all nodes point directly to root

* Reduces tree height

* Time: O(α(n)) ≈ O(1)

### 2. Union by Rank

* Always attach smaller tree to larger tree

* Keeps tree balanced

* Time: O(log n) without path compression

### 3. Union by Size

* Attach smaller tree to larger tree (by number of nodes)

* Similar to union by rank

* Time: O(log n) without path compression

### Combined (Path Compression + Union by Rank):

* Time: O(α(n)) ≈ O(1) amortized

* α(n) = Inverse Ackermann function (grows very slowly)


### Union Find Visualization
```
Initial:

Each element in its own set

Union(0, 1):
0 ──> 1 (1 is root)

Union(2, 3):
2 ──> 3 (3 is root)

Union(1, 3):
0 ──> 1 ──> 3 (3 is root)
2 ──> 3

Find(0):
0 → 1 → 3 (return 3)
With path compression: 0 → 3

Connected(0, 2):
Find(0) = 3, Find(2) = 3
3 == 3 → true

Union(4, 5):
4 ──> 5 (5 is root)

Final sets:
{0, 1, 2, 3} with root 3
{4, 5} with root 5

```
---

## 💻 Java Code Examples

### Problem 1: Basic Union Find

```java
/**
 * Problem: Implement Union Find (Disjoint Set Union)
 * 
 * Visual:
 * Elements:[0][1][2][3][4]
 * 
 * Operations:
 * union(0, 1): {0,1}, {2}, {3}, {4}
 * union(2, 3): {0,1}, {2,3}, {4}
 * union(1, 3): {0,1,2,3}, {4}
 * 
 * connected(0, 2): true (same set)
 * connected(0, 4): false (different sets)
 * 
 * Time Complexity:
 * - Find: O(α(n)) ≈ O(1)
 * - Union: O(α(n)) ≈ O(1)
 * - Connected: O(α(n)) ≈ O(1)
 * 
 * Space Complexity: O(n)
 */
public class UnionFind {
    
    private int[] parent;
    private int[] rank;
    private int numSets;
    
    // Constructor
    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        numSets = n;
        
        // Initialize: each element is its own parent
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }
    
    // Find with path compression
    public int find(int x) {
        if (parent[x] != x) {
            // Path compression: point directly to root
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    // Union by rank
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        
        if (rootX == rootY) {
            return; // Already in same set
        }
        
        // Union by rank: attach smaller tree to larger tree
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        
        numSets--;
    }
    
    // Check if two elements are connected
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
    
    // Get number of disjoint sets
    public int getNumSets() {
        return numSets;
    }
    
    // Get size of set containing x
    public int getSize(int x) {
        int root = find(x);
        int size = 0;
        
        for (int i = 0; i < parent.length; i++) {
            if (find(i) == root) {
                size++;
            }
        }
        
        return size;
    }
    
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(5);
        
        System.out.println("Initial sets: " + uf.getNumSets()); // 5
        
        uf.union(0, 1);
        System.out.println("After union(0,1): " + uf.getNumSets()); // 4
        System.out.println("connected(0,1): " + uf.connected(0, 1)); // true
        
        uf.union(2, 3);
        System.out.println("After union(2,3): " + uf.getNumSets()); // 3
        System.out.println("connected(2,3): " + uf.connected(2, 3)); // true
        
        uf.union(1, 3);
        System.out.println("After union(1,3): " + uf.getNumSets()); // 2
        System.out.println("connected(0,2): " + uf.connected(0, 2)); // true
        System.out.println("connected(0,4): " + uf.connected(0, 4)); // false
        
        System.out.println("\nSize of set containing 0: " + uf.getSize(0)); // 4
        System.out.println("Size of set containing 4: " + uf.getSize(4)); // 1
    }
}
```

---

### Problem 2: Union Find with Size

```java
/**
 * Problem: Union Find with Size Tracking
 * 
 * Visual:
 * Elements:[0][1][2][3][4]
 * Size:[1]
 * 
 * Union(0, 1):
 * parent:[1][2][3][4]
 * size:[2][1]
 * 
 * Union(2, 3):
 * parent:[1][3][4]
 * size:[1][2]
 * 
 * Union(1, 3):
 * parent:[1][3][4]
 * size:[2][4][1]
 * 
 * Set {0,1,2,3} has size 4
 * Set {4} has size 1
 * 
 * Time Complexity: O(α(n))
 * Space Complexity: O(n)
 */
public class UnionFindWithSize {
    
    private int[] parent;
    private int[] size;
    private int numSets;
    
    public UnionFindWithSize(int n) {
        parent = new int[n];
        size = new int[n];
        numSets = n;
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }
    
    // Find with path compression
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    // Union by size
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        
        if (rootX == rootY) {
            return;
        }
        
        // Union by size: attach smaller to larger
        if (size[rootX] < size[rootY]) {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        } else {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        }
        
        numSets--;
    }
    
    // Get size of set containing x
    public int getSize(int x) {
        return size[find(x)];
    }
    
    // Check if connected
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
    
    // Get number of sets
    public int getNumSets() {
        return numSets;
    }
    
    public static void main(String[] args) {
        UnionFindWithSize uf = new UnionFindWithSize(5);
        
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(1, 3);
        
        System.out.println("Size of set containing 0: " + uf.getSize(0)); // 4
        System.out.println("Size of set containing 4: " + uf.getSize(4)); // 1
        System.out.println("Number of sets: " + uf.getNumSets()); // 2
    }
}
```

---

### Problem 3: Number of Connected Components

```java
/**
 * Problem: Number of Connected Components
 * LeetCode: 323
 * 
 * Visual:
 * n = 5, edges = [,, ][0][1][2][3][4]
 * 
 * Graph:
 * 0 ── 1 ── 2    3 ── 4
 * 
 * Components: {0,1,2}, {3,4}
 * Answer: 2
 * 
 * Union Find Approach:
 * 1. Start with n components
 * 2. For each edge, union the two vertices
 * 3. If already connected, no change
 * 4. If not connected, decrement count
 * 
 * Time Complexity: O(n + m × α(n))
 * Space Complexity: O(n)
 */
public class NumberOfConnectedComponents {
    
    public int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        
        for (int[] edge : edges) {
            uf.union(edge, edge);[1]
        }
        
        return uf.getNumSets();
    }
    
    // Union Find class
    static class UnionFind {
        private int[] parent;
        private int[] rank;
        private int numSets;
        
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            numSets = n;
            
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }
        
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            if (rootX == rootY) {
                return;
            }
            
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            
            numSets--;
        }
        
        public int getNumSets() {
            return numSets;
        }
    }
    
    public static void main(String[] args) {
        NumberOfConnectedComponents solver = new NumberOfConnectedComponents();
        
        // Test 1
        int n1 = 5;
        int[][] edges1 = {{0, 1}, {1, 2}, {3, 4}};
        System.out.println("Test 1: " + solver.countComponents(n1, edges1)); // 2
        
        // Test 2
        int n2 = 5;
        int[][] edges2 = {{0, 1}, {2, 3}, {3, 4}};
        System.out.println("Test 2: " + solver.countComponents(n2, edges2)); // 2
        
        // Test 3
        int n3 = 5;
        int[][] edges3 = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        System.out.println("Test 3: " + solver.countComponents(n3, edges3)); // 1
        
        // Test 4 (no edges)
        int n4 = 5;
        int[][] edges4 = {};
        System.out.println("Test 4: " + solver.countComponents(n4, edges4)); // 5
    }
}
```

---

### Problem 4: Graph Valid Tree

```java
/**
 * Problem: Graph Valid Tree
 * LeetCode: 261
 * 
 * Visual:
 * n = 5, edges = [,,, ][0][1][2][3][4]
 * 
 * Valid Tree:
 *     0
 *    /|\
 *   1 2 3
 *   |
 *   4
 * 
 * Conditions for valid tree:
 * 1. Has exactly n-1 edges
 * 2. All nodes are connected
 * 3. No cycles
 * 
 * Union Find Approach:
 * 1. Check if edges == n-1
 * 2. For each edge, union vertices
 * 3. If already connected, cycle detected
 * 4. Check if all nodes connected
 * 
 * Time Complexity: O(n × α(n))
 * Space Complexity: O(n)
 */
public class GraphValidTree {
    
    public boolean validTree(int n, int[][] edges) {
        // A tree must have exactly n-1 edges
        if (edges.length != n - 1) {
            return false;
        }
        
        UnionFind uf = new UnionFind(n);
        
        for (int[] edge : edges) {
            // If already connected, adding this edge creates cycle
            if (uf.connected(edge, edge)) {[1]
                return false;
            }
            uf.union(edge, edge);[1]
        }
        
        // All nodes should be connected
        return uf.getNumSets() == 1;
    }
    
    static class UnionFind {
        private int[] parent;
        private int numSets;
        
        public UnionFind(int n) {
            parent = new int[n];
            numSets = n;
            
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            if (rootX != rootY) {
                parent[rootX] = rootY;
                numSets--;
            }
        }
        
        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
        
        public int getNumSets() {
            return numSets;
        }
    }
    
    public static void main(String[] args) {
        GraphValidTree solver = new GraphValidTree();
        
        // Test 1: Valid tree
        int n1 = 5;
        int[][] edges1 = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        System.out.println("Test 1 (valid): " + solver.validTree(n1, edges1)); // true
        
        // Test 2: Cycle
        int n2 = 5;
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
        System.out.println("Test 2 (cycle): " + solver.validTree(n2, edges2)); // false
        
        // Test 3: Disconnected
        int n3 = 5;
        int[][] edges3 = {{0, 1}, {1, 2}};
        System.out.println("Test 3 (disconnected): " + solver.validTree(n3, edges3)); // false
        
        // Test 4: Single node
        int n4 = 1;
        int[][] edges4 = {};
        System.out.println("Test 4 (single node): " + solver.validTree(n4, edges4)); // true
    }
}
```

---

### Problem 5: Redundant Connection

```java
/**
 * Problem: Redundant Connection
 * LeetCode: 684
 * 
 * Visual:
 * edges = [,, ][1][2][3]
 * 
 * Graph:
 *   1
 *  / \
 * 2 - 3
 * 
 * Edge  creates a cycle[2][3]
 * Answer:[3][2]
 * 
 * Union Find Approach:
 * 1. Process edges one by one
 * 2. If vertices already connected, this edge creates cycle
 * 3. Return first edge that creates cycle
 * 
 * Time Complexity: O(n × α(n))
 * Space Complexity: O(n)
 */
public class RedundantConnection {
    
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        UnionFind uf = new UnionFind(n + 1); // 1-indexed
        
        for (int[] edge : edges) {
            // If already connected, this edge creates cycle
            if (uf.connected(edge, edge)) {[1]
                return edge;
            }
            uf.union(edge, edge);[1]
        }
        
        return new int; // Should never reach here
    }
    
    static class UnionFind {
        private int[] parent;
        private int[] rank;
        
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }
        
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            if (rootX == rootY) {
                return;
            }
            
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
        
        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
    }
    
    public static void main(String[] args) {
        RedundantConnection solver = new RedundantConnection();
        
        // Test 1
        int[][] edges1 = {{1, 2}, {1, 3}, {2, 3}};
        int[] result1 = solver.findRedundantConnection(edges1);
        System.out.println("Test 1: [" + result1 + ", " + result1 + "]"); //[1][2][3]
        
        // Test 2
        int[][] edges2 = {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
        int[] result2 = solver.findRedundantConnection(edges2);
        System.out.println("Test 2: [" + result2 + ", " + result2 + "]"); //[1][4]
    }
}
```

---

## 📝 Practice Problems

### Medium
| Problem | Pattern | Link |
|---------|---------|------|
| Number of Connected Components | Union Find | LeetCode 323 |
| Graph Valid Tree | Union Find | LeetCode 261 |
| Redundant Connection | Union Find | LeetCode 684 |
| Friend Circles | Union Find | LeetCode 547 |

### Hard
| Problem | Pattern | Link |
|---------|---------|------|
| Redundant Connection II | Union Find | LeetCode 685 |
| Most Stones Removed | Union Find | LeetCode 947 |
| Accounts Merge | Union Find | LeetCode 721 |
| Critical Connections | Union Find + DFS | LeetCode 1192 |

---

## ✅ Key Takeaways

1. **Union Find** - Efficient for disjoint set operations
2. **Find** - Returns representative with path compression
3. **Union** - Merges sets with union by rank/size
4. **Time Complexity** - O(α(n)) ≈ O(1) amortized
5. **Applications** - Connected components, cycle detection, MST
6. **Path Compression** - Makes tree flat
7. **Union by Rank** - Keeps tree balanced
8. **Connected** - Check if two elements in same set

---

**Previous:** [Trie](../trie/01-theory.md)  
**Next:** [String Algorithms](../string-algorithms/01-theory.md)