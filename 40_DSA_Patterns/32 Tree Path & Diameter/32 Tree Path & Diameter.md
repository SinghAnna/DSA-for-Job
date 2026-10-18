# 🚀 DSA Pattern 32 — Tree Path / Diameter

> **Goal:** Tree Path / Diameter pattern ko product-based company interview level par samajhna — real-world intuition, kyun use hota hai, kaise identify karein, diameter, maximum path sum, path sum, path sum II, path sum III, longest zigzag, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Tree Path / Diameter Kya Hai?

Tree Path ka matlab hai tree ke nodes ka ek sequence jisme har consecutive node parent-child relationship mein ho.

```text
Path:
A -> B -> C
```

Diameter ka matlab hai tree ke do nodes ke beech **longest path**.

Usually path mein edges count hote hain.

Example:

```text
        1
       / \
      2   3
     / \
    4   5
```

Longest path:

```text
4 -> 2 -> 1 -> 3
```

Edges:

```text
4-2, 2-1, 1-3
```

Diameter:

```text
3
```

---

## 2. Tree Path / Diameter Kyun Use Karte Hain?

Main reasons:

1. **Longest path nikalna**
2. **Maximum path sum nikalna**
3. **Root-to-leaf path sum**
4. **Path sum count**
5. **Subtree contribution calculate karna**
6. **Bottom-up DP on trees**
7. **Network / hierarchy analysis**

### Core Intuition

> Har node ke liye left subtree aur right subtree se best path nikalo, phir dono ko combine karo.

Example:

```text
        1
       / \
      2   3
     / \
    4   5

Node 2:
leftHeight = 1
rightHeight = 1

Path through 2:
4 -> 2 -> 5

Edges = 2
```

---

## 3. Real-World Production Examples

| Real System | Tree Path / Diameter Use |
|---|---|
| Organization Chart | Longest reporting chain |
| File System | Deepest nested folder path |
| Network Design | Longest network route |
| CDN / Cache | Longest cache hierarchy path |
| Org Analytics | Longest manager-report chain |
| Comment System | Longest comment thread |
| Menu System | Deepest menu nesting |
| Decision Tree | Longest decision path |
| Game AI | Longest move sequence |
| Compiler | Longest expression tree path |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Company organization chart mein sabse lambi reporting chain kaise nikaloge?”

Answer:

```text
Employee tree par DFS karo.

Har manager ke liye:
leftDepth = deepest reporting chain in one subtree
rightDepth = deepest reporting chain in another subtree

Path through manager =
leftDepth + rightDepth

Maximum answer track karo.
```

Ya:

> “File storage system mein sabse deep nested folder path kaise nikaloge?”

Answer:

```text
Folder tree par DFS karo.

Har folder ka depth =
1 + max(child folder depths)

Maximum depth track karo.
```

---

## 4. Kaise Pata Chalega Ki Tree Path / Diameter Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Tree Path / Diameter |
|---|---|
| Longest path in tree | Diameter |
| Maximum path sum | Max Path Sum |
| Root to leaf path sum | Path Sum |
| All root-to-leaf paths | Path Sum II |
| Count paths with target sum | Path Sum III |
| Longest zigzag path | Zigzag DFS |
| Deepest node | Tree DFS |
| Longest chain | Tree DFS |

### Common Keywords

- Diameter
- Longest path
- Maximum path sum
- Path sum
- Root to leaf
- Zigzag
- Depth
- Height
- Chain

### Mental Checklist

1. Kya tree hai?  
2. Kya path / chain chahiye?  
3. Kya path root se start hota hai?  
4. Kya path kisi bhi node se start ho sakta hai?  
5. Kya subtree information chahiye?  

Agar 2–3 answers **yes** hain, to Tree Path / Diameter try karo.

---

# 5. Diameter Ka Core Idea

Har node ke liye:

```text
leftHeight = height of left subtree
rightHeight = height of right subtree
```

Path passing through current node:

```text
leftHeight + rightHeight
```

Height of current node:

```text
1 + max(leftHeight, rightHeight)
```

Example:

```text
        1
       / \
      2   3
     / \
    4   5

Node 2:
leftHeight = 1
rightHeight = 1

Path through 2:
4 -> 2 -> 5

Diameter contribution = 2
```

---

# 6. Template — Diameter of Binary Tree

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

### Why Array for Diameter?

Java mein primitive values pass by value hoti hain.

```java
int diameter
```

Change hoga, but parent call ko reflect nahi karega.

Isliye:

```java
int[] diameter = new int;
```

Use karte hain.

---

# 7. Example 1: Diameter of Binary Tree

## Problem

Binary tree ka diameter nikalo.

Diameter = longest path between any two nodes, counted in edges.

```text
        1
       / \
      2   3
     / \
    4   5

Output: 3
```

## Why DFS?

Har node se left/right height nikalni hai.

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

Diameter at 2:
1 + 1 = 2

height(3) = 1

height(1):
= 1 + max(2, 1)
= 3

Diameter at 1:
2 + 1 = 3

Answer = 3
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(h)\)

---

# 8. Example 2: Binary Tree Maximum Path Sum

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
Left subtree best path
Right subtree best path
```

Current node ko center maan kar:

```text
leftGain + node.val + rightGain
```

Parent ko sirf ek side return karni hoti hai:

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

# 9. Example 3: Path Sum

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

# 10. Example 4: Path Sum II

## Problem

Root se leaf tak ke saare paths return karo jinka sum `targetSum` ho.

```text
        5
       / \
      4   8
     /   / \
    11  13  4
   /  \    / \
  7    2  5   1

targetSum = 22

Output:
[
 ,[2][4][5][11]
[4][5][8]
]
```

## Why Backtracking + DFS?

Path build karte jao.

Leaf par check karo.

Agar valid path hai, to copy karke answer mein add karo.

Phir backtrack karo.

## Solution

```java
public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    List<List<Integer>> ans = new ArrayList<>();

    dfs(root, targetSum, new ArrayList<>(), ans);

    return ans;
}

private void dfs(TreeNode node,
                 int remaining,
                 List<Integer> current,
                 List<List<Integer>> ans) {

    if (node == null) {
        return;
    }

    current.add(node.val);
    remaining -= node.val;

    if (node.left == null && node.right == null && remaining == 0) {
        ans.add(new ArrayList<>(current));
    }

    dfs(node.left, remaining, current, ans);
    dfs(node.right, remaining, current, ans);

    current.remove(current.size() - 1);
}
```

## Dry Run

```text
targetSum = 22

Path:
5 -> remaining 17
4 -> remaining 13
11 -> remaining 2
2 -> remaining 0

Leaf reached
Save[2][4][5][11]

Backtrack:
Remove 2
Remove 11
Remove 4

Path:
5 -> remaining 17
8 -> remaining 9
4 -> remaining 5
5 -> remaining 0

Leaf reached
Save[4][5][8]
```

## Complexity

- Time: \(O(n^2)\) worst case
- Space: \(O(h)\)

---

# 11. Example 5: Path Sum III

## Problem

Tree mein kitne paths ka sum `targetSum` hai, ye count karo.

Path upward/downward direction mein hona chahiye, but parent-child edges follow kare.

```text
        10
       /  \
      5   -3
     / \    \
    3   2   11
   / \   \
  3  -2   1

targetSum = 8

Output: 3
Paths:
5 -> 3
5 -> 2 -> 1
-3 -> 11
```

## Why Prefix Sum + DFS?

Current path ka prefix sum maintain karo.

Agar:

```text
currentPrefix - targetSum
```

Pehle aaya hai, to ek valid path exist karta hai.

## Solution

```java
public int pathSum(TreeNode root, int targetSum) {
    Map<Long, Integer> prefixCount = new HashMap<>();

    prefixCount.put(0L, 1);

    return dfs(root, 0L, targetSum, prefixCount);
}

private int dfs(TreeNode node,
                long currentSum,
                int targetSum,
                Map<Long, Integer> prefixCount) {

    if (node == null) {
        return 0;
    }

    currentSum += node.val;

    int count = prefixCount.getOrDefault(currentSum - targetSum, 0);

    prefixCount.put(currentSum,
        prefixCount.getOrDefault(currentSum, 0) + 1);

    count += dfs(node.left, currentSum, targetSum, prefixCount);
    count += dfs(node.right, currentSum, targetSum, prefixCount);

    prefixCount.put(currentSum, prefixCount.get(currentSum) - 1);

    return count;
}
```

## Why Remove Prefix After Recursion?

Kyunki prefix sum sirf **current root-to-node path** ke liye valid hona chahiye.

Left subtree ke answers right subtree mein leak nahi hone chahiye.

## Complexity

- Time: \(O(n)\)
- Space: \(O(n)\)

---

# 12. Example 6: Longest ZigZag Path in a Binary Tree

## Problem

Longest zigzag path nikalo.

Zigzag path mein direction har step par change hoti hai:

```text
Left -> Right -> Left -> Right
```

```text
        1
       / \
      2   3
     / \
    4   5
   / \
  6   7

Output: 4
Path: 4 -> 2 -> 5 -> 7? 
```

Actual longest zigzag depends on directions, but concept same hai.

## Solution

```java
public int longestZigZag(TreeNode root) {
    int[] maxLength = new int;

    dfs(root, true, 0, maxLength);
    dfs(root, false, 0, maxLength);

    return maxLength;
}

private void dfs(TreeNode node,
                 boolean isLeft,
                 int length,
                 int[] maxLength) {

    if (node == null) {
        return;
    }

    maxLength = Math.max(maxLength, length);

    if (isLeft) {
        dfs(node.right, false, length + 1, maxLength);
        dfs(node.left, true, 1, maxLength);
    } else {
        dfs(node.left, true, length + 1, maxLength);
        dfs(node.right, false, 1, maxLength);
    }
}
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(h)\)

---

# 13. Path Types

| Path Type | Start | End | Example Problem |
|---|---|---|---|
| Root to leaf | Root | Leaf | Path Sum |
| Any node to any node | Any | Any | Maximum Path Sum |
| Downward path | Parent | Descendant | Path Sum III |
| Zigzag path | Any | Any | Longest ZigZag |
| Diameter | Any | Any | Diameter of Binary Tree |

---

# 14. Common Mistakes

## Mistake 1: Diameter Mein Nodes Count Karna

Diameter usually **edges** count karta hai.

```text
Path: 4 -> 2 -> 1 -> 3
Edges = 3
Nodes = 4
```

---

## Mistake 2: Maximum Path Sum Mein Negative Subtree Include Karna

Negative subtree path answer kharab karta hai.

Isliye:

```java
Math.max(0, dfs(node.left))
```

---

## Mistake 3: Parent Ko Dono Sides Return Karna

Parent ko sirf ek side return karni chahiye:

```java
return node.val + Math.max(leftGain, rightGain);
```

---

## Mistake 4: Path Sum III Mein Prefix Remove Na Karna

Backtrack ke baad:

```java
prefixCount.put(currentSum, prefixCount.get(currentSum) - 1);
```

Ye zaroori hai.

---

## Mistake 5: Leaf Node Check Galat Karna

Leaf:

```java
node.left == null && node.right == null
```

---

## Mistake 6: Global Answer Ke Liye Local Variable Use Karna

Diameter / Max Path Sum mein:

```java
int[] answer = new int;
```

Use karo.

---

# 15. Tree Path Decision Flow

```text
Problem padho
      |
      v
Kya tree path / longest chain chahiye?
      |
      +-- No --> Tree Path pattern probably nahi
      |
      +-- Yes
          |
          v
Kya longest path chahiye?
      |
      +-- Yes --> Diameter DFS
      |
      +-- Kya maximum sum path chahiye?
              |
              +-- Yes --> Max Path Sum DFS
              |
              +-- Kya root-to-leaf path chahiye?
                      |
                      +-- Yes --> Path Sum DFS
                      |
                      +-- Kya downward path count chahiye?
                              |
                              +-- Yes --> Prefix Sum + DFS
```

---

# 16. Important Problems

## Easy

- Diameter of Binary Tree
- Path Sum
- Maximum Depth of Binary Tree
- Minimum Depth of Binary Tree
- Binary Tree Paths

## Medium

- Binary Tree Maximum Path Sum
- Path Sum II
- Path Sum III
- Longest ZigZag Path in a Binary Tree
- Count Good Nodes in Binary Tree
- Sum Root to Leaf Numbers

## Hard

- Binary Tree Maximum Path Sum
- Path Sum III
- Binary Tree Cameras
- Binary Tree Maximum Path Sum
- All Nodes Distance K in Binary Tree

---

# 17. One-Line Recognition

> **Agar problem tree mein longest path, maximum path sum, root-to-leaf path, downward path count, ya diameter maang rahi hai — Tree Path / Diameter DFS try karo.**

Tree Path / Diameter ka main power ye hai ki har node ke left/right subtree se best path nikal kar current node ke through