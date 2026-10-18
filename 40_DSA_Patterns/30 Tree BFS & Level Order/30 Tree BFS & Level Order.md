# 🚀 DSA Pattern 30 — Tree BFS / Level Order

> **Goal:** Tree BFS / Level Order ko product-based company interview level par samajhna — real-world intuition, kyun use hota hai, kaise identify karein, level order traversal, zigzag traversal, right/left view, minimum depth, next pointers, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Tree BFS / Level Order Kya Hai?

Tree BFS mein tree ke nodes ko **level by level** traverse karte hain.

```text
Level 0: Root
Level 1: Root ke children
Level 2: Grandchildren
Level 3: ...
```

Example:

```text
        1
       / \
      2   3
     / \   \
    4   5   6
```

Level Order:

```text
1
2 3
4 5 6
```

BFS Queue use karta hai:

```text
Queue: FIFO
Jo node pehle aaya, wo pehle process hoga
```

---

## 2. Tree BFS Kyun Use Karte Hain?

Main reasons:

1. **Level-wise processing**
2. **Minimum depth nikalna**
3. **Right / left view nikalna**
4. **Zigzag traversal**
5. **Next pointer connect karna**
6. **Nearest node / shortest depth**
7. **Tree width nikalna**

### Core Intuition

> Queue mein current level ke nodes hote hain. Unhe process karke next level ke nodes queue mein daalte hain.

Example:

```text
Queue:


Process 1:
Queue:
[2][3]

Process 2 and 3:
Queue:
[4][5][6]
```

---

## 3. Real-World Production Examples

| Real System | Tree BFS Use |
|---|---|
| Organization Chart | Level-wise employee hierarchy |
| Category Tree | E-commerce category levels |
| File System | Folder depth-wise traversal |
| DOM Tree | HTML rendering layers |
| Comment System | Nested comments level-wise |
| Menu System | Menu levels render karna |
| Org Analytics | Reporting levels |
| Notification System | Broadcast by hierarchy level |
| Game Tree | Move levels |
| Social Network | Connection degrees |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “E-commerce category menu mein categories ko level-wise render karna hai. Kaise karoge?”

Answer:

```text
Category tree par BFS karo.

Level 0: Main categories
Level 1: Subcategories
Level 2: Sub-subcategories

Queue se ek level process karo,
phir next level ke children queue mein daalo.
```

Ya:

> “Organization chart mein kisi employee ke reporting levels dikhane hain. Kaise karoge?”

Answer:

```text
Employee tree par BFS karo.

Level 0: Employee
Level 1: Direct reports
Level 2: Reports of reports
```

---

## 4. Kaise Pata Chalega Ki Tree BFS Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Tree BFS |
|---|---|
| Level order traversal | Tree BFS |
| Level by level | Tree BFS |
| Right side view | BFS |
| Left side view | BFS |
| Zigzag traversal | BFS |
| Minimum depth | BFS |
| Maximum width | BFS |
| Connect next pointers | BFS |
| Nearest leaf | BFS |

### Common Keywords

- Level
- Level order
- Row
- Layer
- Depth-wise
- Zigzag
- Right view
- Left view
- Minimum depth
- Width

### Mental Checklist

1. Kya tree hai?  
2. Kya level-wise answer chahiye?  
3. Kya minimum depth chahiye?  
4. Kya right/leftmost node chahiye?  
5. Kya same level ke nodes process karne hain?  

Agar 2–3 answers **yes** hain, to Tree BFS try karo.

---

# 5. Tree BFS Ka Core Template

```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> ans = new ArrayList<>();

    if (root == null) {
        return ans;
    }

    Queue<TreeNode> queue = new LinkedList<>();

    queue.offer(root);

    while (!queue.isEmpty()) {
        int size = queue.size();

        List<Integer> level = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();

            level.add(node.val);

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        ans.add(level);
    }

    return ans;
}
```

### Most Important Line

```java
int size = queue.size();
```

Ye current level ke nodes ki count fix karta hai.

Naye nodes next level ke hote hain.

---

# 6. Example 1: Binary Tree Level Order Traversal

## Problem

Binary tree ko level by level traverse karo.

```text
        3
       / \
      9  20
         / \
        15  7

Output:
[
  ,
 ,[9][20]
[7][15]
]
```

## Why BFS?

Level-wise output chahiye.

## Solution

```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> ans = new ArrayList<>();

    if (root == null) {
        return ans;
    }

    Queue<TreeNode> queue = new LinkedList<>();

    queue.offer(root);

    while (!queue.isEmpty()) {
        int size = queue.size();

        List<Integer> level = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();

            level.add(node.val);

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        ans.add(level);
    }

    return ans;
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

Queue:


Level 0:
size = 1
process 3
add 9, 20

ans = []
Queue:
[9][20]

Level 1:
size = 2
process 9
process 20
add 15, 7

ans = [, ][9][20]
Queue:
[7][15]

Level 2:
size = 2
process 15
process 7

ans = [,, ][7][9][15][20]
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(w)\), where `w` = maximum width

---

# 7. Example 2: Binary Tree Zigzag Level Order Traversal

## Problem

Tree ko zigzag order mein traverse karo.

```text
        3
       / \
      9  20
         / \
        15  7

Output:
[
  ,
 ,[9][20]
[7][15]
]
```

## Why BFS?

Level-wise traverse karna hai.

Har alternate level ka order reverse karna hai.

## Solution

```java
public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> ans = new ArrayList<>();

    if (root == null) {
        return ans;
    }

    Queue<TreeNode> queue = new LinkedList<>();

    queue.offer(root);

    boolean leftToRight = true;

    while (!queue.isEmpty()) {
        int size = queue.size();

        LinkedList<Integer> level = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();

            if (leftToRight) {
                level.addLast(node.val);
            } else {
                level.addFirst(node.val);
            }

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        ans.add(level);

        leftToRight = !leftToRight;
    }

    return ans;
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

Level 0:
leftToRight = true
level = 

Level 1:
leftToRight = false
Process 9, 20

addFirst 9 -> 
addFirst 20 ->[9][20]

level =[20][9]

Level 2:
leftToRight = true
Process 15, 7

level =[7][15]

Final:
[
  ,
 ,[9][20]
[7][15]
]
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(w)\)

---

# 8. Example 3: Binary Tree Right Side View

## Problem

Tree ko right side se dekhne par kaunse nodes dikhenge, wo return karo.

```text
        1
       / \
      2   3
       \   \
        5   4

Output:[1][3][4]
```

## Why BFS?

Har level ka last node right side view hota hai.

## Solution

```java
public List<Integer> rightSideView(TreeNode root) {
    List<Integer> ans = new ArrayList<>();

    if (root == null) {
        return ans;
    }

    Queue<TreeNode> queue = new LinkedList<>();

    queue.offer(root);

    while (!queue.isEmpty()) {
        int size = queue.size();

        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();

            if (i == size - 1) {
                ans.add(node.val);
            }

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    return ans;
}
```

## Dry Run

```text
Tree:
        1
       / \
      2   3
       \   \
        5   4

Level 0:

Last node = 1
ans = 

Level 1:
[2][3]
Last node = 3
ans =[1][3]

Level 2:
[4][5]
Last node = 4
ans =[1][3][4]

Final:
[1][3][4]
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(w)\)

---

# 9. Example 4: Binary Tree Left Side View

## Problem

Tree ko left side se dekhne par kaunse nodes dikhenge, wo return karo.

```text
        1
       / \
      2   3
     /     \
    4       5

Output:[1][2][4]
```

## Why BFS?

Har level ka first node left side view hota hai.

## Solution

```java
public List<Integer> leftSideView(TreeNode root) {
    List<Integer> ans = new ArrayList<>();

    if (root == null) {
        return ans;
    }

    Queue<TreeNode> queue = new LinkedList<>();

    queue.offer(root);

    while (!queue.isEmpty()) {
        int size = queue.size();

        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();

            if (i == 0) {
                ans.add(node.val);
            }

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    return ans;
}
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(w)\)

---

# 10. Example 5: Minimum Depth of Binary Tree

## Problem

Root se nearest leaf node tak ki minimum depth nikalo.

```text
        1
       / \
      2   3
     /
    4

Output: 2
Explanation: 1 -> 3
```

## Why BFS?

BFS level by level jata hai.

Jaise hi pehla leaf node mile, wahi minimum depth hoga.

## Solution

```java
public int minDepth(TreeNode root) {
    if (root == null) {
        return 0;
    }

    Queue<TreeNode> queue = new LinkedList<>();

    queue.offer(root);

    int depth = 1;

    while (!queue.isEmpty()) {
        int size = queue.size();

        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();

            if (node.left == null && node.right == null) {
                return depth;
            }

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        depth++;
    }

    return depth;
}
```

## Dry Run

```text
Tree:
        1
       / \
      2   3
     /
    4

Level 1:
Node 1
Not leaf
Add 2, 3

Level 2:
Node 2 -> not leaf
Node 3 -> leaf

Return depth = 2
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(w)\)

---

# 11. Example 6: Maximum Width of Binary Tree

## Problem

Tree ki maximum width nikalo.

Width = kisi level par first non-null node se last non-null node tak ke nodes ki count.

```text
        1
       / \
      3   2
     /     \
    5       9

Output: 4
Explanation: Level 2 has width 4
```

## Why BFS with Indexing?

Har node ko index do:

```text
Root index = 0

left child index = 2 * parentIndex
right child index = 2 * parentIndex + 1
```

Har level mein:

```text
width = lastIndex - firstIndex + 1
```

## Solution

```java
public int widthOfBinaryTree(TreeNode root) {
    if (root == null) {
        return 0;
    }

    Queue<TreeNode> queue = new LinkedList<>();

    Queue<Long> indexQueue = new LinkedList<>();

    queue.offer(root);
    indexQueue.offer(0L);

    int maxWidth = 0;

    while (!queue.isEmpty()) {
        int size = queue.size();

        long firstIndex = indexQueue.peek();
        long lastIndex = firstIndex;

        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();

            long index = indexQueue.poll();

            lastIndex = index;

            if (node.left != null) {
                queue.offer(node.left);
                indexQueue.offer(2 * index);
            }

            if (node.right != null) {
                queue.offer(node.right);
                indexQueue.offer(2 * index + 1);
            }
        }

        maxWidth = Math.max(
            maxWidth,
            (int) (lastIndex - firstIndex + 1)
        );
    }

    return maxWidth;
}
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(w)\)

---

# 12. Example 7: Populating Next Right Pointers

## Problem

Perfect binary tree ke nodes mein `next` pointer set karo.

`next` pointer same level ka next node point karega.

```text
        1
       / \
      2   3
     / \ / \
    4  5 6  7

After:
2 -> 3
4 -> 5 -> 6 -> 7
```

## Why BFS?

Same level ke nodes ko connect karna hai.

## Solution

```java
public TreeNode connect(TreeNode root) {
    if (root == null) {
        return null;
    }

    Queue<TreeNode> queue = new LinkedList<>();

    queue.offer(root);

    while (!queue.isEmpty()) {
        int size = queue.size();

        TreeNode prev = null;

        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();

            if (prev != null) {
                prev.next = node;
            }

            prev = node;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    return root;
}
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(w)\)

---

# 13. Tree BFS vs Tree DFS

| Feature | Tree BFS | Tree DFS |
|---|---|---|
| Data structure | Queue | Recursion / Stack |
| Traversal | Level-wise | Depth-wise |
| Memory | \(O(w)\) | \(O(h)\) |
| Minimum depth | Best | Possible |
| Level order | Best | Difficult |
| Path problems | Possible | Best |
| Subtree problems | Difficult | Best |
| Example | Level Order | Max Depth |

### Rule

```text
Level / nearest / minimum depth -> BFS
Path / subtree / height -> DFS
```

---

# 14. Common Mistakes

## Mistake 1: `queue.size()` Ko Loop Ke Andar Direct Use Karna

Galat:

```java
for (int i = 0; i < queue.size(); i++) {
    // queue.size() change ho raha hai
}
```

Correct:

```java
int size = queue.size();

for (int i = 0; i < size; i++) {
    // process current level
}
```

---

## Mistake 2: Null Node Queue Mein Dalna

```java
if (node.left != null) {
    queue.offer(node.left);
}
```

Null check zaroori hai.

---

## Mistake 3: Minimum Depth Mein Wrong Leaf Check

Leaf node:

```java
node.left == null && node.right == null
```

Sirf ek child null hona leaf nahi hota.

---

## Mistake 4: Zigzag Mein Har Level Sort Karna

Efficient approach:

```text
LinkedList addFirst / addLast
```

---

## Mistake 5: Maximum Width Mein Integer Overflow

Deep tree mein index bada ho sakta hai.

Isliye:

```java
long index
```

Use karo.

---

# 15. Tree BFS Decision Flow

```text
Problem padho
      |
      v
Kya tree hai?
      |
      +-- No --> Tree BFS probably nahi
      |
      +-- Yes
          |
          v
Kya level-wise output chahiye?
      |
      +-- Yes --> Level Order BFS
      |
      +-- Kya right/left view chahiye?
              |
              +-- Yes --> BFS + First/Last node
              |
              +-- Kya minimum depth chahiye?
                      |
                      +-- Yes --> BFS + Leaf check
                      |
                      +-- Kya zigzag chahiye?
                              |
                              +-- Yes --> BFS + Alternate direction
```

---

# 16. Important Problems

## Easy

- Binary Tree Level Order Traversal
- Minimum Depth of Binary Tree
- Same Tree
- Symmetric Tree
- Average of Levels in Binary Tree

## Medium

- Binary Tree Zigzag Level Order Traversal
- Binary Tree Right Side View
- Binary Tree Left Side View
- Populating Next Right Pointers
- Maximum Width of Binary Tree
- Find Largest Value in Each Tree Row

## Hard

- Binary Tree Zigzag Level Order Traversal
- Maximum Width of Binary Tree
- Populating Next Right Pointers in Each Node II
- All Nodes Distance K in Binary Tree

---

# 17. One-Line Recognition

> **Agar problem tree ko level-wise process karne, minimum depth, right/left view, zigzag traversal, ya same-level connections maang rahi hai — Tree BFS / Level Order try karo.**

Tree BFS ka main power ye hai ki queue FIFO order mein nodes process karta hai, isliye tree naturally levels mein traverse hota hai.