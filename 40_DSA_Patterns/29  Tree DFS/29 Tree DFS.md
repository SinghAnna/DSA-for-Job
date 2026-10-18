# 🚀 DSA Pattern 29 — Tree DFS

> **Goal:** Tree DFS ko product-based company interview level par samajhna — real-world intuition, kyun use hota hai, kaise identify karein, preorder/inorder/postorder, height/depth, path problems, subtree problems, templates, recursion tree, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Tree DFS Kya Hai?

DFS ka full form hai:

```text
Depth First Search
```

Tree DFS mein hum ek node se start karke uske ek branch ko **depth tak** explore karte hain.

Pehle left subtree poori tarah explore karte hain, phir right subtree.

Example:

```text
        1
       / \
      2   3
     / \
    4   5
```

DFS traversal:

```text
1 -> 2 -> 4 -> 5 -> 3
```

---

## 2. Tree DFS Kyun Use Karte Hain?

Main reasons:

1. **Tree traversal**
2. **Height / depth nikalna**
3. **Path sum problems**
4. **Subtree information nikalna**
5. **Tree validation**
6. **Lowest Common Ancestor**
7. **Serialize / deserialize tree**
8. **Recursive structure naturally handle karna**

### Core Intuition

> Har node ke liye left subtree aur right subtree ka result nikalo, phir current node ka answer banao.

Example:

```text
Maximum Depth:

depth(node) = 1 + max(depth(left), depth(right))
```

---

## 3. Real-World Production Examples

| Real System | Tree DFS Use |
|---|---|
| File System | Folder/subfolder traversal |
| Organization Chart | Manager hierarchy traversal |
| DOM Tree | HTML element traversal |
| JSON Parser | Nested JSON traversal |
| Category Tree | E-commerce category traversal |
| Comment System | Nested comments traversal |
| Menu System | Nested menu rendering |
| Compiler | Abstract Syntax Tree traversal |
| File Search | Recursive file search |
| Org Analytics | Employee hierarchy analysis |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “File storage system mein kisi folder ka total size nikalna hai. Folders ke andar folders ho sakte hain. Kaise karoge?”

Answer:

```text
Har folder ka DFS karo.

Folder size =
Direct files ka size
+ Left/child folders ka recursive size

Base case:
File node ka size directly return karo.
```

Ya:

> “E-commerce category tree mein ek category ke saare nested products count karne hain. Kaise karoge?”

Answer:

```text
DFS traversal karo.

Current category ka count =
Direct products
+ Har child category ka recursive count
```

---

## 4. Kaise Pata Chalega Ki Tree DFS Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Tree DFS |
|---|---|
| Tree height / depth | DFS |
| Path sum | DFS |
| Subtree sum | DFS |
| Lowest Common Ancestor | DFS |
| Validate BST | DFS |
| Tree diameter | DFS |
| Serialize tree | DFS |
| Count nodes | DFS |
| Nested structure | DFS |

### Common Keywords

- Depth
- Height
- Path
- Subtree
- Ancestor
- Descendant
- Leaf
- Root
- Validate
- Traverse

### Mental Checklist

1. Kya tree / nested structure hai?  
2. Kya root-to-leaf path chahiye?  
3. Kya subtree information chahiye?  
4. Kya parent-child relationship matter karti hai?  
5. Kya recursion natural lag rahi hai?  

Agar 2–3 answers **yes** hain, to Tree DFS try karo.

---

# 5. DFS Ke 3 Main Traversals

## 1. Preorder

```text
Root -> Left -> Right
```

Example:

```text
        1
       / \
      2   3

Preorder:
1 2 3
```

Use:

```text
Copy tree
Serialize tree
Process node before children
```

## 2. Inorder

```text
Left -> Root -> Right
```

Example:

```text
        2
       / \
      1   3

Inorder:
1 2 3
```

Use:

```text
BST sorted order
Validate BST
Kth smallest in BST
```

## 3. Postorder

```text
Left -> Right -> Root
```

Example:

```text
        1
       / \
      2   3

Postorder:
2 3 1
```

Use:

```text
Delete tree
Calculate subtree size
Calculate height
Bottom-up DP
```

---

# 6. DFS Template — Basic

```java
public void dfs(TreeNode root) {
    if (root == null) {
        return;
    }

    // Process current node
    System.out.println(root.val);

    dfs(root.left);
    dfs(root.right);
}
```

### Preorder

```java
process(root);
dfs(root.left);
dfs(root.right);
```

### Inorder

```java
dfs(root.left);
process(root);
dfs(root.right);
```

### Postorder

```java
dfs(root.left);
dfs(root.right);
process(root);
```

---

# 7. Example 1: Maximum Depth of Binary Tree

## Problem

Binary tree ki maximum depth nikalo.

```text
        3
       / \
      9  20
         / \
        15  7

Output: 3
```

## Why DFS?

Har node ki depth:

```text
1 + max(left depth, right depth)
```

Ye naturally recursive hai.

## Solution

```java
public int maxDepth(TreeNode root) {
    if (root == null) {
        return 0;
    }

    int leftDepth = maxDepth(root.left);
    int rightDepth = maxDepth(root.right);

    return 1 + Math.max(leftDepth, rightDepth);
}
```

## Dry Run

```text
Tree:
        3
       / \
      9  20
         / \
        15  7

maxDepth(3)
= 1 + max(maxDepth(9), maxDepth(20))

maxDepth(9)
= 1 + max(0, 0)
= 1

maxDepth(20)
= 1 + max(maxDepth(15), maxDepth(7))
= 1 + max(1, 1)
= 2

maxDepth(3)
= 1 + max(1, 2)
= 3

Answer = 3
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(h)\), where `h` = tree height

---

# 8. Example 2: Same Tree

## Problem

Do binary trees identical hain ya nahi check karo.

```text
Tree 1:
    1
   / \
  2   3

Tree 2:
    1
   / \
  2   3

Output: true
```

## Why DFS?

Dono trees ko simultaneously traverse karo.

Har node par:

```text
Values equal?
Left subtrees same?
Right subtrees same?
```

## Solution

```java
public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) {
        return true;
    }

    if (p == null || q == null) {
        return false;
    }

    if (p.val != q.val) {
        return false;
    }

    return isSameTree(p.left, q.left) &&
           isSameTree(p.right, q.right);
}
```

## Dry Run

```text
Compare root:
1 == 1

Compare left:
2 == 2

Compare right:
3 == 3

Answer = true
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(h)\)

---

# 9. Example 3: Symmetric Tree

## Problem

Tree apne mirror image jaisa hai ya nahi check karo.

```text
        1
       / \
      2   2
     / \ / \
    3  4 4  3

Output: true
```

## Why DFS?

Left subtree ka left, right subtree ke right se compare hota hai.

```text
left.left vs right.right
left.right vs right.left
```

## Solution

```java
public boolean isSymmetric(TreeNode root) {
    if (root == null) {
        return true;
    }

    return isMirror(root.left, root.right);
}

private boolean isMirror(TreeNode left, TreeNode right) {
    if (left == null && right == null) {
        return true;
    }

    if (left == null || right == null) {
        return false;
    }

    return left.val == right.val &&
           isMirror(left.left, right.right) &&
           isMirror(left.right, right.left);
}
```

## Dry Run

```text
Root: 1

Compare:
left = 2
right = 2

Compare:
left.left = 3
right.right = 3

Compare:
left.right = 4
right.left = 4

Symmetric = true
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(h)\)

---

# 10. Example 4: Path Sum

## Problem

Root se leaf tak path ka sum `targetSum` ke equal hai ya nahi check karo.

```text
        5
       / \
      4   8
     /   / \
    11  13  4
   /  \      \
  7    2      1

targetSum = 22

Output: true
Path: 5 -> 4 -> 11 -> 2
```

## Why DFS?

Root se leaf tak har path explore karna hai.

## Solution

```java
public boolean hasPathSum(TreeNode root, int targetSum) {
    if (root == null) {
        return false;
    }

    if (root.left == null && root.right == null) {
        return targetSum == root.val;
    }

    int remaining = targetSum - root.val;

    return hasPathSum(root.left, remaining) ||
           hasPathSum(root.right, remaining);
}
```

## Dry Run

```text
targetSum = 22

Node 5:
remaining = 17

Node 4:
remaining = 13

Node 11:
remaining = 2

Leaf 7:
7 != 2
false

Leaf 2:
2 == 2
true

Answer = true
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(h)\)

---

# 11. Example 5: Binary Tree Maximum Path Sum

## Problem

Tree mein kisi bhi node se kisi bhi node tak path ka maximum sum nikalo.

Path mein har node ek baar aa sakta hai.

```text
        -10
        /  \
       9   20
           / \
          15  7

Output: 42
Path: 15 -> 20 -> 7
```

## Why DFS?

Har node ke liye:

```text
Left subtree ka best downward path
Right subtree ka best downward path
```

Current node ko center maan kar path ban sakta hai:

```text
leftGain + node.val + rightGain
```

But parent ko sirf ek side ka path return karna hota hai:

```text
node.val + max(leftGain, rightGain)
```

## Solution

```java
public int maxPathSum(TreeNode root) {
    int[] maxSum = new int;

    maxSum = Integer.MIN_VALUE;

    dfs(root, maxSum);

    return maxSum;
}

private int dfs(TreeNode node, int[] maxSum) {
    if (node == null) {
        return 0;
    }

    int leftGain = Math.max(0, dfs(node.left, maxSum));
    int rightGain = Math.max(0, dfs(node.right, maxSum));

    int currentPathSum = node.val + leftGain + rightGain;

    maxSum = Math.max(maxSum, currentPathSum);

    return node.val + Math.max(leftGain, rightGain);
}
```

## Dry Run

```text
Tree:
        -10
        /  \
       9   20
           / \
          15  7

dfs(9):
left = 0
right = 0
currentPath = 9
maxSum = 9
return 9

dfs(15):
return 15

dfs(7):
return 7

dfs(20):
leftGain = 15
rightGain = 7
currentPath = 20 + 15 + 7 = 42
maxSum = 42
return 20 + max(15, 7) = 35

dfs(-10):
leftGain = max(0, 9) = 9
rightGain = max(0, 35) = 35
currentPath = -10 + 9 + 35 = 34
maxSum remains 42

Answer = 42
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(h)\)

---

# 12. Example 6: Diameter of Binary Tree

## Problem

Tree ke do nodes ke beech longest path ki length nikalo.

Path mein edges count hote hain.

```text
        1
       / \
      2   3
     / \
    4   5

Output: 3
Path: 4 -> 2 -> 1 -> 3
```

## Why DFS?

Har node ke liye:

```text
leftHeight + rightHeight
```

Maximum possible diameter hai.

## Solution

```java
public int diameterOfBinaryTree(TreeNode root) {
    int[] diameter = new int;

    height(root, diameter);

    return diameter;
}

private int height(TreeNode node, int[] diameter) {
    if (node == null) {
        return 0;
    }

    int leftHeight = height(node.left, diameter);
    int rightHeight = height(node.right, diameter);

    diameter = Math.max(
        diameter,
        leftHeight + rightHeight
    );

    return 1 + Math.max(leftHeight, rightHeight);
}
```

## Dry Run

```text
Tree:
        1
       / \
      2   3
     / \
    4   5

height(4) = 1
height(5) = 1

height(2):
= 1 + max(1, 1)
= 2

diameter at 2:
1 + 1 = 2

height(3) = 1

height(1):
= 1 + max(2, 1)
= 3

diameter at 1:
2 + 1 = 3

Answer = 3
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(h)\)

---

# 13. Example 7: Validate Binary Search Tree

## Problem

Binary tree valid BST hai ya nahi check karo.

```text
Valid BST:
    2
   / \
  1   3

Output: true
```

```text
Invalid BST:
    5
   / \
  1   4
     / \
    3   6

Output: false
```

## Why DFS?

Har node ke liye valid range maintain karni hoti hai.

```text
Left subtree values < node value
Right subtree values > node value
```

## Solution

```java
public boolean isValidBST(TreeNode root) {
    return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
}

private boolean validate(TreeNode node, long min, long max) {
    if (node == null) {
        return true;
    }

    if (node.val <= min || node.val >= max) {
        return false;
    }

    return validate(node.left, min, node.val) &&
           validate(node.right, node.val, max);
}
```

## Dry Run

```text
Tree:
    5
   / \
  1   4
     / \
    3   6

Node 5:
range = (-inf, +inf)

Node 1:
range = (-inf, 5)
Valid

Node 4:
range = (5, +inf)
4 <= 5
Invalid

Answer = false
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(h)\)

---

# 14. Example 8: Lowest Common Ancestor

## Problem

Do nodes ka Lowest Common Ancestor nikalo.

```text
        3
       / \
      5   1
     / \ / \
    6  2 0  8
      / \
     7   4

LCA(5, 1) = 3
LCA(5, 4) = 5
```

## Why DFS?

Har node se check karo:

```text
Kya left subtree mein p ya q hai?
Kya right subtree mein p ya q hai?
```

## Solution

```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) {
        return root;
    }

    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);

    if (left != null && right != null) {
        return root;
    }

    return left != null ? left : right;
}
```

## Dry Run

```text
LCA(5, 4)

dfs(3):
left = dfs(5)
right = dfs(1)

dfs(5):
left = null
right = dfs(2)

dfs(2):
left = dfs(7) = null
right = dfs(4) = 4

dfs(2) returns 4

dfs(5):
left = null
right = 4
return 4

dfs(1):
returns null

dfs(3):
left = 4
right = null
return 4

Answer = 4? 
```

Wait — LCA(5, 4) should be `5`.

Correct dry run:

```text
dfs(5):
left = null
right = dfs(2)

dfs(2):
left = dfs(7) = null
right = dfs(4) = 4

dfs(2) returns 4

dfs(5):
left = null
right = 4
return 4
```

But node `5` khud `p` hai.

Base case:

```java
if (root == p || root == q) {
    return root;
}
```

Isliye:

```text
dfs(5) immediately returns 5
```

Then:

```text
dfs(3):
left = 5
right = null

return 5
```

Answer = `5`.

## Complexity

- Time: \(O(n)\)
- Space: \(O(h)\)

---

# 15. DFS vs BFS

| Feature | DFS | BFS |
|---|---|---|
| Data structure | Recursion / Stack | Queue |
| Traversal | Depth-wise | Level-wise |
| Memory | \(O(h)\) | \(O(w)\) |
| Shortest path unweighted | No | Yes |
| Path problems | Best | Possible |
| Subtree problems | Best | Difficult |
| Example | Max Depth | Level Order |

### Rule

```text
Path / subtree / depth -> DFS
Level / shortest / nearest -> BFS
```

---

# 16. Common Mistakes

## Mistake 1: Null Check Bhoolna

```java
if (root == null) {
    return 0;
}
```

Ye base case zaroori hai.

---

## Mistake 2: Leaf Node Check Galat Karna

Leaf node:

```java
root.left == null && root.right == null
```

Sirf `root == null` leaf nahi hota.

---

## Mistake 3: Path Sum Mein Negative Values Ignore Karna

Maximum Path Sum mein negative subtree ko `0` treat karo:

```java
Math.max(0, dfs(node.left))
```

---

## Mistake 4: BST Validation Mein Sirf Immediate Child Check Karna

Galat:

```java
root.left.val < root.val
```

Poori subtree ki range check karni hoti hai.

---

## Mistake 5: Global Answer Ke Liye Local Variable Use Karna

Diameter / Max Path Sum mein answer array ya class-level variable use karo:

```java
int[] maxSum = new int;
```

---

## Mistake 6: Deep Tree Mein Stack Overflow

Skewed tree mein recursion depth `n` ho sakti hai.

Large tree ke liye iterative DFS use kar sakte ho.

---

# 17. Tree DFS Decision Flow

```text
Problem padho
      |
      v
Kya tree / nested structure hai?
      |
      +-- No --> DFS probably nahi
      |
      +-- Yes
          |
          v
Kya depth/height chahiye?
      |
      +-- Yes --> Postorder DFS
      |
      +-- Kya root-to-leaf path chahiye?
              |
              +-- Yes --> Preorder DFS
              |
              +-- Kya subtree answer chahiye?
                      |
                      +-- Yes --> Postorder DFS
                      |
                      +-- Kya BST property chahiye?
                              |
                              +-- Yes --> Inorder / Range DFS
```

---

# 18. Important Problems

## Easy

- Maximum Depth of Binary Tree
- Same Tree
- Symmetric Tree
- Path Sum
- Merge Two Binary Trees
- Invert Binary Tree

## Medium

- Binary Tree Maximum Path Sum
- Diameter of Binary Tree
- Validate Binary Search Tree
- Lowest Common Ancestor
- Path Sum II
- Count Good Nodes
- Construct Binary Tree from Preorder and Inorder

## Hard

- Binary Tree Maximum Path Sum
- Serialize and Deserialize Binary Tree
- Binary Tree Cameras
- Binary Tree Postorder Traversal
- Recover Binary Search Tree

---

# 19. One-Line Recognition

> **Agar problem tree/nested structure ki depth, path, subtree, ancestor, ya validation maang rahi hai — Tree DFS try karo.**

Tree DFS ka main power ye hai ki har node ke left/right subtree se result nikal kar current node ka answer banata hai. Isliye path, height, subtree aur validation