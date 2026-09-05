# 🌳 Trees - Complete Guide

## 📚 Theory

### What is a Tree?

A **Tree** is a hierarchical data structure consisting of nodes connected by edges. It's a non-linear data structure.

### Tree Terminology
```
Root (A)
     /  \
    B     C <- Internal Nodes
   / \   / \
  D   E F   G <- Leaf Nodes (Terminal)

```


- Root: Topmost node (A)
- Parent: Node above another (B is parent of D, E)
- Child: Node below another (D, E are children of B)
- Siblings: Nodes with same parent (D, E are siblings)
- Leaf: Node with no children (D, E, F, G)
- Internal: Node with at least one child (A, B, C)
- Height: Longest path from root to leaf
- Depth: Distance from root to node
- Level: Depth + 1



### Properties of Trees

- **Number of edges**: n-1 (for n nodes)
- **Height of tree with n nodes**: 
  - Minimum: ⌊log₂n⌋ (balanced tree)
  - Maximum: n-1 (skewed tree)
- **Maximum nodes at level l**: 2^(l-1)
- **Maximum nodes in tree of height h**: 2^h - 1

### Types of Trees

1. **Binary Tree** - Each node has at most 2 children
2. **Binary Search Tree (BST)** - Left < Root < Right
3. **AVL Tree** - Self-balancing BST
4. **Red-Black Tree** - Self-balancing BST
5. **Heap** - Complete binary tree
6. **Trie** - Prefix tree
7. **Segment Tree** - Range query tree

### Binary Tree Node Structure

```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int val) {
        this.val = val;
    }
    
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
```

### Tree Traversals
```
          1
         / \
       2     3    
      / \   / \
     4   5 6

```

Preorder (Root, Left, Right): 1, 2, 4, 5, 3, 6

Inorder (Left, Root, Right): 4, 2, 5, 1, 3, 6

Postorder (Left, Right, Root): 4, 5, 2, 6, 3, 1

Level Order (BFS): 1, 2, 3, 4, 5, 6


### Traversal Complexity

| Traversal | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| Preorder | O(n) | O(h) |
| Inorder | O(n) | O(h) |
| Postorder | O(n) | O(h) |
| Level Order | O(n) | O(w) |

h = height, w = maximum width

---

## 💻 Java Code Examples

### Problem 1: Create Binary Tree and Display

**Question:** Create a binary tree and display it using different traversal methods.

```java
/**
 * Problem: Create Binary Tree and Display
 * 
 * Visual:
 *         1
 *       /   \
 *      2     3
 *     / \   / \
 *    4   5 6   7
 * 
 * Time Complexity: O(n) for all traversals
 * Space Complexity: O(h) for recursive, O(w) for level order
 */
public class BinaryTreeBasic {
    
    static class TreeNode {
        int val;
        TreeNode left, right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    // Preorder Traversal (Recursive)
    // Root -> Left -> Right
    public static void preorderRecursive(TreeNode root) {
        if (root == null) return;
        
        System.out.print(root.val + " ");
        preorderRecursive(root.left);
        preorderRecursive(root.right);
    }
    
    // Preorder Traversal (Iterative)
    public static void preorderIterative(TreeNode root) {
        if (root == null) return;
        
        java.util.Stack<TreeNode> stack = new java.util.Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");
            
            // Push right first, then left (so left is processed first)
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
    }
    
    // Inorder Traversal (Recursive)
    // Left -> Root -> Right
    public static void inorderRecursive(TreeNode root) {
        if (root == null) return;
        
        inorderRecursive(root.left);
        System.out.print(root.val + " ");
        inorderRecursive(root.right);
    }
    
    // Inorder Traversal (Iterative)
    public static void inorderIterative(TreeNode root) {
        java.util.Stack<TreeNode> stack = new java.util.Stack<>();
        TreeNode current = root;
        
        while (current != null || !stack.isEmpty()) {
            // Go to leftmost node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            // Process node
            current = stack.pop();
            System.out.print(current.val + " ");
            
            // Go to right
            current = current.right;
        }
    }
    
    // Postorder Traversal (Recursive)
    // Left -> Right -> Root
    public static void postorderRecursive(TreeNode root) {
        if (root == null) return;
        
        postorderRecursive(root.left);
        postorderRecursive(root.right);
        System.out.print(root.val + " ");
    }
    
    // Postorder Traversal (Iterative)
    public static void postorderIterative(TreeNode root) {
        if (root == null) return;
        
        java.util.Stack<TreeNode> stack1 = new java.util.Stack<>();
        java.util.Stack<TreeNode> stack2 = new java.util.Stack<>();
        
        stack1.push(root);
        
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);
            
            if (node.left != null) stack1.push(node.left);
            if (node.right != null) stack1.push(node.right);
        }
        
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().val + " ");
        }
    }
    
    // Level Order Traversal (BFS)
    public static void levelOrder(TreeNode root) {
        if (root == null) return;
        
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");
            
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
    }
    
    // Level Order Traversal with Levels
    public static void levelOrderWithLevels(TreeNode root) {
        if (root == null) return;
        
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.add(root);
        
        int level = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            System.out.print("Level " + level + ": ");
            
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.val + " ");
                
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            
            System.out.println();
            level++;
        }
    }
    
    // Helper to create tree
    public static TreeNode createTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        return root;
    }
    
    public static void main(String[] args) {
        TreeNode root = createTree();
        
        System.out.print("Preorder (Recursive):  ");
        preorderRecursive(root);
        System.out.println();
        
        System.out.print("Preorder (Iterative):  ");
        preorderIterative(root);
        System.out.println();
        
        System.out.print("Inorder (Recursive):   ");
        inorderRecursive(root);
        System.out.println();
        
        System.out.print("Inorder (Iterative):   ");
        inorderIterative(root);
        System.out.println();
        
        System.out.print("Postorder (Recursive): ");
        postorderRecursive(root);
        System.out.println();
        
        System.out.print("Postorder (Iterative): ");
        postorderIterative(root);
        System.out.println();
        
        System.out.print("Level Order:           ");
        levelOrder(root);
        System.out.println();
        
        System.out.println("\nLevel Order with Levels:");
        levelOrderWithLevels(root);
    }
}
```

---

### Problem 2: Height and Diameter of Binary Tree

**Question:** Find the height and diameter of a binary tree.

```java
/**
 * Problem: Height and Diameter of Binary Tree
 * LeetCode: 104 (Height), 543 (Diameter)
 * 
 * Visual (Height):
 *         1        <- Level 1
 *       /   \
 *      2     3     <- Level 2
 *     / \
 *    4   5         <- Level 3
 * 
 * Height = 3 (or 2, depending on definition)
 * 
 * Visual (Diameter):
 *         1
 *       /   \
 *      2     3
 *     / \     
 *    4   5    
 * 
 * Diameter = 4 (path: 4-2-1-3 or 5-2-1-3)
 * Longest path between any two nodes
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */
public class TreeHeightDiameter {
    
    static class TreeNode {
        int val;
        TreeNode left, right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    // Height of tree (number of edges from root to deepest leaf)
    public static int height(TreeNode root) {
        if (root == null) return -1; // or 0 if counting nodes
        
        return 1 + Math.max(height(root.left), height(root.right));
    }
    
    // Maximum depth (LeetCode style - counting nodes)
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
    
    // Diameter of binary tree
    // Approach 1: O(n²) - Calculate height for each node
    public static int diameterBruteForce(TreeNode root) {
        if (root == null) return 0;
        
        // Diameter through current node
        int currentDiameter = height(root.left) + height(root.right) + 2;
        
        // Maximum diameter in left and right subtrees
        int leftDiameter = diameterBruteForce(root.left);
        int rightDiameter = diameterBruteForce(root.right);
        
        return Math.max(currentDiameter, Math.max(leftDiameter, rightDiameter));
    }
    
    // Approach 2: O(n) - Calculate height and diameter together
    static class DiameterResult {
        int height;
        int diameter;
        
        DiameterResult(int h, int d) {
            height = h;
            diameter = d;
        }
    }
    
    public static int diameterOptimized(TreeNode root) {
        return diameterHelper(root).diameter;
    }
    
    private static DiameterResult diameterHelper(TreeNode root) {
        if (root == null) {
            return new DiameterResult(-1, 0);
        }
        
        DiameterResult left = diameterHelper(root.left);
        DiameterResult right = diameterHelper(root.right);
        
        // Height of current node
        int currentHeight = 1 + Math.max(left.height, right.height);
        
        // Diameter through current node
        int currentDiameter = left.height + right.height + 2;
        
        // Maximum diameter
        int maxDiameter = Math.max(currentDiameter, 
                          Math.max(left.diameter, right.diameter));
        
        return new DiameterResult(currentHeight, maxDiameter);
    }
    
    // Approach 3: O(n) - Using global variable
    static int maxDiameter = 0;
    
    public static int diameterWithGlobal(TreeNode root) {
        maxDiameter = 0;
        heightForDiameter(root);
        return maxDiameter;
    }
    
    private static int heightForDiameter(TreeNode root) {
        if (root == null) return -1;
        
        int leftHeight = heightForDiameter(root.left);
        int rightHeight = heightForDiameter(root.right);
        
        // Update diameter
        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight + 2);
        
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    // Helper to create tree
    public static TreeNode createTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        return root;
    }
    
    public static void main(String[] args) {
        TreeNode root = createTree();
        
        System.out.println("Height: " + height(root));
        System.out.println("Max Depth: " + maxDepth(root));
        
        System.out.println("Diameter (Brute Force): " + diameterBruteForce(root));
        System.out.println("Diameter (Optimized): " + diameterOptimized(root));
        System.out.println("Diameter (Global): " + diameterWithGlobal(root));
    }
}
```

---

### Problem 3: Check if Binary Tree is Balanced

**Question:** A binary tree is height-balanced if the depth of the two subtrees of every node never differs by more than one.

```java
/**
 * Problem: Balanced Binary Tree
 * LeetCode: 110
 * 
 * Visual (Balanced):
 *         1
 *       /   \
 *      2     3     <- Height difference: 0
 *     / \
 *    4   5
 * 
 * Visual (Not Balanced):
 *         1
 *       /
 *      2
 *     /
 *    3       <- Height difference: 2 > 1
 *   /
 *  4
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */
public class BalancedBinaryTree {
    
    static class TreeNode {
        int val;
        TreeNode left, right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    // Approach 1: O(n²) - Check balance for each node
    public static boolean isBalancedBruteForce(TreeNode root) {
        if (root == null) return true;
        
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        
        // Check current node and recursively check subtrees
        return Math.abs(leftHeight - rightHeight) <= 1 &&
               isBalancedBruteForce(root.left) &&
               isBalancedBruteForce(root.right);
    }
    
    private static int height(TreeNode root) {
        if (root == null) return -1;
        return 1 + Math.max(height(root.left), height(root.right));
    }
    
    // Approach 2: O(n) - Calculate height and check balance together
    public static boolean isBalancedOptimized(TreeNode root) {
        return checkBalance(root) != -1;
    }
    
    // Returns height if balanced, -1 if not balanced
    private static int checkBalance(TreeNode root) {
        if (root == null) return -1;
        
        int leftHeight = checkBalance(root.left);
        if (leftHeight == -1) return -1; // Left subtree not balanced
        
        int rightHeight = checkBalance(root.right);
        if (rightHeight == -1) return -1; // Right subtree not balanced
        
        // Check if current node is balanced
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    // Helper to create trees
    public static TreeNode createBalancedTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        return root;
    }
    
    public static TreeNode createUnbalancedTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        return root;
    }
    
    public static void main(String[] args) {
        TreeNode balanced = createBalancedTree();
        TreeNode unbalanced = createUnbalancedTree();
        
        System.out.println("Balanced tree is balanced: " + 
                          isBalancedOptimized(balanced));
        System.out.println("Unbalanced tree is balanced: " + 
                          isBalancedOptimized(unbalanced));
    }
}
```

---

### Problem 4: Lowest Common Ancestor (LCA)

**Question:** Given a binary tree and two nodes, find their lowest common ancestor.

```java
/**
 * Problem: Lowest Common Ancestor of Binary Tree
 * LeetCode: 236
 * 
 * Visual:
 *           3
 *         /   \
 *        5     1
 *       / \   / \
 *      6   2 0   8
 *         / \
 *        7   4
 * 
 * LCA(5, 1) = 3
 * LCA(5, 4) = 5
 * LCA(7, 4) = 2
 * 
 * Approach:
 * - If root is null or equals p or q, return root
 * - Recursively find LCA in left and right subtrees
 * - If both left and right return non-null, root is LCA
 * - Otherwise, return the non-null result
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */
public class LowestCommonAncestor {
    
    static class TreeNode {
        int val;
        TreeNode left, right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    // LCA in Binary Tree (not necessarily BST)
    public static TreeNode lowestCommonAncestor(TreeNode root, 
                                               TreeNode p, 
                                               TreeNode q) {
        // Base case
        if (root == null || root == p || root == q) {
            return root;
        }
        
        // Search in left and right subtrees
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        // If both found, current root is LCA
        if (left != null && right != null) {
            return root;
        }
        
        // Return whichever is not null
        return (left != null) ? left : right;
    }
    
    // LCA in Binary Search Tree (optimized)
    // LeetCode: 235
    public static TreeNode lowestCommonAncestorBST(TreeNode root, 
                                                   TreeNode p, 
                                                   TreeNode q) {
        if (root == null) return null;
        
        // If both p and q are smaller, LCA is in left subtree
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestorBST(root.left, p, q);
        }
        
        // If both p and q are greater, LCA is in right subtree
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestorBST(root.right, p, q);
        }
        
        // Otherwise, current root is LCA
        return root;
    }
    
    // LCA in BST (iterative)
    public static TreeNode lowestCommonAncestorBSTIterative(TreeNode root, 
                                                           TreeNode p, 
                                                           TreeNode q) {
        while (root != null) {
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }
    
    // Helper to create tree
    public static TreeNode createTree() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        return root;
    }
    
    public static void main(String[] args) {
        TreeNode root = createTree();
        TreeNode p = root.left;      // 5
        TreeNode q = root.right;     // 1
        TreeNode r = root.left.right.left;  // 7
        TreeNode s = root.left.right.right; // 4
        
        TreeNode lca1 = lowestCommonAncestor(root, p, q);
        System.out.println("LCA(5, 1) = " + lca1.val); // 3
        
        TreeNode lca2 = lowestCommonAncestor(root, p, r);
        System.out.println("LCA(5, 7) = " + lca2.val); // 5
        
        TreeNode lca3 = lowestCommonAncestor(root, r, s);
        System.out.println("LCA(7, 4) = " + lca3.val); // 2
    }
}
```

---

### Problem 5: Binary Tree Level Order Traversal

**Question:** Given the root of a binary tree, return the level order traversal of its nodes' values.

```java
/**
 * Problem: Binary Tree Level Order Traversal
 * LeetCode: 102
 * 
 * Visual:
 *         3
 *       /   \
 *      9    20
 *          /  \
 *         15   7
 * 
 * Level Order: [,, ][1][7][9][15][20]
 * 
 * Approach: BFS using Queue
 * - Add root to queue
 * - For each level, process all nodes in queue
 * - Add children to queue for next level
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(w) where w is maximum width
 */
public class BinaryTreeLevelOrder {
    
    static class TreeNode {
        int val;
        TreeNode left, right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    // Standard Level Order Traversal
    public static java.util.List<java.util.List<Integer>> levelOrder(TreeNode root) {
        java.util.List<java.util.List<Integer>> result = 
            new java.util.ArrayList<>();
        
        if (root == null) return result;
        
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            java.util.List<Integer> level = new java.util.ArrayList<>();
            
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            
            result.add(level);
        }
        
        return result;
    }
    
    // Level Order Traversal (Zigzag)
    // LeetCode: 103
    public static java.util.List<java.util.List<Integer>> zigzagLevelOrder(TreeNode root) {
        java.util.List<java.util.List<Integer>> result = 
            new java.util.ArrayList<>();
        
        if (root == null) return result;
        
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.add(root);
        boolean leftToRight = true;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            java.util.List<Integer> level = new java.util.ArrayList<>();
            
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                
                // Add at end or beginning based on direction
                if (leftToRight) {
                    level.add(node.val);
                } else {
                    level.add(0, node.val);
                }
                
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            
            result.add(level);
            leftToRight = !leftToRight; // Toggle direction
        }
        
        return result;
    }
    
    // Right Side View
    // LeetCode: 199
    public static java.util.List<Integer> rightSideView(TreeNode root) {
        java.util.List<Integer> result = new java.util.ArrayList<>();
        
        if (root == null) return result;
        
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                
                // Add rightmost node of each level
                if (i == size - 1) {
                    result.add(node.val);
                }
                
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        
        return result;
    }
    
    // Helper to create tree
    public static TreeNode createTree() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        return root;
    }
    
    public static void main(String[] args) {
        TreeNode root = createTree();
        
        System.out.println("Level Order: " + levelOrder(root));
        // [,, ][1][7][9][15][20]
        
        System.out.println("Zigzag Level Order: " + zigzagLevelOrder(root));
        // [,, ][1][7][9][15][20]
        
        System.out.println("Right Side View: " + rightSideView(root));
        //[3][7][20]
    }
}
```

---

### Problem 6: Validate Binary Search Tree

**Question:** Given the root of a binary tree, determine if it is a valid binary search tree (BST).

```java
/**
 * Problem: Validate Binary Search Tree
 * LeetCode: 98
 * 
 * Visual (Valid BST):
 *           5
 *         /   \
 *        3     7
 *       / \   / \
 *      1   4 6   8
 * 
 * Inorder: 1, 3, 4, 5, 6, 7, 8 (sorted)
 * 
 * Visual (Invalid BST):
 *           5
 *         /   \
 *        3     7
 *       / \   / \
 *      1   6 4   8
 * 
 * 6 > 5 but in left subtree - Invalid!
 * 
 * Approach 1: Inorder traversal should be sorted
 * Approach 2: Check range for each node
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */
public class ValidateBST {
    
    static class TreeNode {
        int val;
        TreeNode left, right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    // Approach 1: Inorder Traversal (should be sorted)
    static TreeNode prev = null;
    
    public static boolean isValidBSTInorder(TreeNode root) {
        prev = null;
        return inorderCheck(root);
    }
    
    private static boolean inorderCheck(TreeNode root) {
        if (root == null) return true;
        
        // Check left subtree
        if (!inorderCheck(root.left)) return false;
        
        // Check current node
        if (prev != null && root.val <= prev.val) {
            return false;
        }
        prev = root;
        
        // Check right subtree
        return inorderCheck(root.right);
    }
    
    // Approach 2: Range Checking
    public static boolean isValidBSTRange(TreeNode root) {
        return validate(root, null, null);
    }
    
    private static boolean validate(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        
        // Check if current node violates range
        if ((min != null && root.val <= min) ||
            (max != null && root.val >= max)) {
            return false;
        }
        
        // Recursively validate left and right subtrees
        // Left subtree: all values must be < root.val
        // Right subtree: all values must be > root.val
        return validate(root.left, min, root.val) &&
               validate(root.right, root.val, max);
    }
    
    // Approach 3: Iterative Inorder
    public static boolean isValidBSTIterative(TreeNode root) {
        java.util.Stack<TreeNode> stack = new java.util.Stack<>();
        TreeNode prev = null;
        TreeNode current = root;
        
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            current = stack.pop();
            
            if (prev != null && current.val <= prev.val) {
                return false;
            }
            prev = current;
            
            current = current.right;
        }
        
        return true;
    }
    
    // Helper to create trees
    public static TreeNode createValidBST() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(8);
        return root;
    }
    
    public static TreeNode createInvalidBST() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6); // Invalid: 6 > 5
        root.right.left = new TreeNode(4); // Invalid: 4 < 5
        root.right.right = new TreeNode(8);
        return root;
    }
    
    public static void main(String[] args) {
        TreeNode validBST = createValidBST();
        TreeNode invalidBST = createInvalidBST();
        
        System.out.println("Valid BST (Inorder): " + 
                          isValidBSTInorder(validBST));
        System.out.println("Invalid BST (Inorder): " + 
                          isValidBSTInorder(invalidBST));
        
        System.out.println("Valid BST (Range): " + 
                          isValidBSTRange(validBST));
        System.out.println("Invalid BST (Range): " + 
                          isValidBSTRange(invalidBST));
    }
}
```

---

### Problem 7: Construct Binary Tree from Inorder and Preorder

**Question:** Given two integer arrays inorder and preorder that represent the inorder and preorder traversals of a binary tree, construct and return the binary tree.

```java
/**
 * Problem: Construct Binary Tree from Preorder and Inorder Traversal
 * LeetCode: 105
 * 
 * Visual:
 * Preorder:   (Root, Left, Right)[3][7][9][15][20]
 * Inorder:    (Left, Root, Right)[3][7][9][15][20]
 * 
 * Step-by-step:
 * 1. First element in preorder is root: 3
 * 2. Find 3 in inorder:  3[2][7][15][20]
 * 3. Left subtree inorder:, Right subtree inorder:[2][7][15][20]
 * 4. Left subtree preorder:, Right subtree preorder:[2][7][15][20]
 * 5. Recursively build
 * 
 * Result:
 *       3
 *      / \
 *     9  20
 *       /  \
 *      15   7
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class ConstructTreeFromPreorderInorder {
    
    static class TreeNode {
        int val;
        TreeNode left, right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    // Approach 1: Recursive with HashMap
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || 
            preorder.length != inorder.length) {
            return null;
        }
        
        // Create map for quick lookup of inorder indices
        java.util.Map<Integer, Integer> inorderMap = 
            new java.util.HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        
        return buildTreeHelper(preorder, 0, preorder.length - 1,
                              inorder, 0, inorder.length - 1,
                              inorderMap);
    }
    
    private static TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd,
                                           int[] inorder, int inStart, int inEnd,
                                           java.util.Map<Integer, Integer> inorderMap) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        
        // Root is first element in preorder
        TreeNode root = new TreeNode(preorder[preStart]);
        
        // Find root in inorder
        int inorderRootIndex = inorderMap.get(root.val);
        
        // Calculate number of nodes in left subtree
        int leftSubtreeSize = inorderRootIndex - inStart;
        
        // Build left subtree
        root.left = buildTreeHelper(preorder, preStart + 1, 
                                   preStart + leftSubtreeSize,
                                   inorder, inStart, 
                                   inorderRootIndex - 1,
                                   inorderMap);
        
        // Build right subtree
        root.right = buildTreeHelper(preorder, 
                                    preStart + leftSubtreeSize + 1, preEnd,
                                    inorder, inorderRootIndex + 1, inEnd,
                                    inorderMap);
        
        return root;
    }
    
    // Helper to display tree (level order)
    public static void displayLevelOrder(TreeNode root) {
        if (root == null) return;
        
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                System.out.print(node.val + " ");
                queue.add(node.left);
                queue.add(node.right);
            } else {
                System.out.print("null ");
            }
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        
        TreeNode root = buildTree(preorder, inorder);
        
        System.out.print("Preorder: ");
        for (int val : preorder) System.out.print(val + " ");
        System.out.println();
        
        System.out.print("Inorder: ");
        for (int val : inorder) System.out.print(val + " ");
        System.out.println();
        
        System.out.print("Constructed Tree (Level Order): ");
        displayLevelOrder(root);
    }
}
```

---

### Problem 8: Serialize and Deserialize Binary Tree

**Question:** Design an algorithm to serialize and deserialize a binary tree.

```java
/**
 * Problem: Serialize and Deserialize Binary Tree
 * LeetCode: 297
 * 
 * Visual:
 * Tree:       1
 *           /   \
 *          2     3
 *             /   \
 *            4     5
 * 
 * Serialization (Preorder with null markers):
 * "1,2,null,null,3,4,null,null,5,null,null"
 * 
 * Approach 1: Preorder Traversal
 * Approach 2: Level Order Traversal
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class SerializeDeserializeBinaryTree {
    
    static class TreeNode {
        int val;
        TreeNode left, right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    // Approach 1: Preorder Traversal
    static class CodecPreorder {
        
        // Encodes a tree to a single string
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serializeHelper(root, sb);
            return sb.toString();
        }
        
        private void serializeHelper(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append("null,");
                return;
            }
            
            sb.append(root.val).append(",");
            serializeHelper(root.left, sb);
            serializeHelper(root.right, sb);
        }
        
        // Decodes your encoded data to tree
        public TreeNode deserialize(String data) {
            String[] nodes = data.split(",");
            java.util.Queue<String> queue = 
                new java.util.LinkedList<>(java.util.Arrays.asList(nodes));
            return deserializeHelper(queue);
        }
        
        private TreeNode deserializeHelper(java.util.Queue<String> queue) {
            String val = queue.poll();
            
            if (val.equals("null")) {
                return null;
            }
            
            TreeNode root = new TreeNode(Integer.parseInt(val));
            root.left = deserializeHelper(queue);
            root.right = deserializeHelper(queue);
            
            return root;
        }
    }
    
    // Approach 2: Level Order Traversal
    static class CodecLevelOrder {
        
        public String serialize(TreeNode root) {
            if (root == null) return "";
            
            StringBuilder sb = new StringBuilder();
            java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
            queue.add(root);
            
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                
                if (node == null) {
                    sb.append("null,");
                } else {
                    sb.append(node.val).append(",");
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
            
            return sb.toString();
        }
        
        public TreeNode deserialize(String data) {
            if (data == null || data.isEmpty()) return null;
            
            String[] nodes = data.split(",");
            TreeNode root = new TreeNode(Integer.parseInt(nodes));
            
            java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
            queue.add(root);
            
            int i = 1;
            while (!queue.isEmpty() && i < nodes.length) {
                TreeNode node = queue.poll();
                
                // Left child
                if (!nodes[i].equals("null")) {
                    node.left = new TreeNode(Integer.parseInt(nodes[i]));
                    queue.add(node.left);
                }
                i++;
                
                // Right child
                if (i < nodes.length && !nodes[i].equals("null")) {
                    node.right = new TreeNode(Integer.parseInt(nodes[i]));
                    queue.add(node.right);
                }
                i++;
            }
            
            return root;
        }
    }
    
    // Helper to create tree
    public static TreeNode createTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        return root;
    }
    
    public static void main(String[] args) {
        TreeNode root = createTree();
        
        CodecPreorder codec1 = new CodecPreorder();
        String serialized1 = codec1.serialize(root);
        System.out.println("Serialized (Preorder): " + serialized1);
        
        TreeNode deserialized1 = codec1.deserialize(serialized1);
        System.out.println("Deserialized successfully!");
        
        CodecLevelOrder codec2 = new CodecLevelOrder();
        String serialized2 = codec2.serialize(root);
        System.out.println("Serialized (Level Order): " + serialized2);
        
        TreeNode deserialized2 = codec2.deserialize(serialized2);
        System.out.println("Deserialized successfully!");
    }
}
```

---

## 📝 Practice Problems

### Easy
| Problem | Pattern | Link |
|---------|---------|------|
| Maximum Depth of Binary Tree | DFS | LeetCode 104 |
| Same Tree | DFS | LeetCode 100 |
| Invert Binary Tree | DFS | LeetCode 226 |
| Binary Tree Paths | DFS | LeetCode 257 |
| Sum of Left Leaves | DFS | LeetCode 404 |

### Medium
| Problem | Pattern | Link |
|---------|---------|------|
| Binary Tree Level Order Traversal | BFS | LeetCode 102 |
| Binary Tree Zigzag Level Order | BFS | LeetCode 103 |
| Validate Binary Search Tree | Inorder | LeetCode 98 |
| Construct Binary Tree from Preorder and Inorder | Recursion | LeetCode 105 |
| Serialize and Deserialize Binary Tree | DFS/BFS | LeetCode 297 |
| Lowest Common Ancestor | DFS | LeetCode 236 |
| Binary Tree Right Side View | BFS | LeetCode 199 |

### Hard
| Problem | Pattern | Link |
|---------|---------|------|
| Binary Tree Maximum Path Sum | DFS | LeetCode 124 |
| Serialize and Deserialize BST | BST Property | LeetCode 449 |
| Count Complete Tree Nodes | Binary Search | LeetCode 222 |
| Vertical Order Traversal | BFS + Sorting | LeetCode 987 |

---

## ✅ Key Takeaways

1. **DFS Traversals** - Preorder, Inorder, Postorder for different use cases
2. **BFS (Level Order)** - Use queue for level-by-level processing
3. **BST Property** - Left < Root < Right, Inorder gives sorted sequence
4. **Recursion** - Most tree problems solved elegantly with recursion
5. **Divide & Conquer** - Build tree from traversals using recursion
6. **Serialization** - Use null markers to preserve tree structure

---

**Previous:** [Stack & Queue](../stack-queue/01-theory.md)  
**Next:** [Heaps](../heaps/01-theory.md)