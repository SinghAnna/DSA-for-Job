# 🚀 DSA Pattern 31 — BST Property

> **Goal:** BST Property ko product-based company interview level par samajhna — real-world intuition, kyun use hota hai, kaise identify karein, BST search, insert, delete, validate, kth smallest/largest, LCA, inorder traversal, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. BST Property Kya Hai?

BST ka full form hai:

```text
Binary Search Tree
```

BST ek binary tree hai jisme har node ke liye rule hota hai:

```text
Left subtree < Node < Right subtree
```

Example:

```text
        8
       / \
      3   10
     / \    \
    1   6   14
       / \   /
      4  7  13
```

Yahan:

```text
Left subtree of 8: 1, 3, 4, 6, 7 -> all less than 8
Right subtree of 8: 10, 13, 14 -> all greater than 8
```

### Most Important Property

```text
BST ka inorder traversal sorted order deta hai.
```

Example:

```text
Inorder:
1 3 4 6 7 8 10 13 14
```

---

## 2. BST Property Kyun Use Karte Hain?

Main reasons:

1. **Fast search** — \(O(h)\)
2. **Fast insert/delete** — \(O(h)\)
3. **Sorted order maintain karna**
4. **Range queries**
5. **Kth smallest/largest element**
6. **Floor / Ceil**
7. **Lowest Common Ancestor**
8. **Database indexing concepts**

### Time Complexity

| Operation | Balanced BST | Skewed BST |
|---|---|---|
| Search | \(O(\log n)\) | \(O(n)\) |
| Insert | \(O(\log n)\) | \(O(n)\) |
| Delete | \(O(\log n)\) | \(O(n)\) |
| Inorder traversal | \(O(n)\) | \(O(n)\) |

### Core Intuition

> Har comparison ke baad aadha tree discard kar sakte ho, kyunki BST ordered hota hai.

---

## 3. Real-World Production Examples

| Real System | BST Use |
|---|---|
| Database Index | Sorted key lookup |
| Search Engine | Sorted term dictionary |
| E-commerce | Price range search |
| Stock Market | Price-time ordered data |
| File System | Sorted file names |
| Leaderboard | Score-based ranking |
| Scheduler | Event time ordering |
| Autocomplete | Prefix-based dictionary |
| Range Query System | Query between min and max |
| Caching | Ordered key eviction |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “E-commerce website mein user price range select karta hai, jaise ₹10,000 se ₹20,000. Products efficiently kaise nikaloge?”

Answer:

```text
Products ko BST / balanced BST mein price key ke saath store karo.

Range search:
- `lowerBound(10000)`
- `upperBound(20000)`

Inorder traversal se sorted products milenge.
```

Ya:

> “Leaderboard mein kisi player ka rank aur top K players chahiye. Kaise maintain karoge?”

Answer:

```text
Scores ko BST / balanced BST mein store karo.

Kth largest:
Reverse inorder traversal

Rank:
Left subtree size + 1
```

---

## 4. Kaise Pata Chalega Ki BST Property Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible BST Property |
|---|---|
| Binary Search Tree | BST Property |
| Sorted order traversal | Inorder BST |
| Kth smallest/largest | BST inorder |
| Range sum / range count | BST range search |
| Validate BST | BST property |
| Lowest Common Ancestor in BST | BST property |
| Insert / delete in BST | BST property |
| Floor / Ceil | BST search |

### Common Keywords

- BST
- Binary Search Tree
- Sorted
- Kth smallest
- Kth largest
- Range
- Floor
- Ceil
- Ancestor
- Validate

### Mental Checklist

1. Kya tree BST hai?  
2. Kya sorted order chahiye?  
3. Kya search / insert / delete karna hai?  
4. Kya range query chahiye?  
5. Kya kth smallest/largest chahiye?  

Agar 2–3 answers **yes** hain, to BST Property try karo.

---

# 5. BST Search Template

## Iterative Search

```java
public TreeNode searchBST(TreeNode root, int val) {
    TreeNode current = root;

    while (current != null) {
        if (val == current.val) {
            return current;
        } else if (val < current.val) {
            current = current.left;
        } else {
            current = current.right;
        }
    }

    return null;
}
```

## Recursive Search

```java
public TreeNode searchBSTRecursive(TreeNode root, int val) {
    if (root == null || root.val == val) {
        return root;
    }

    if (val < root.val) {
        return searchBSTRecursive(root.left, val);
    }

    return searchBSTRecursive(root.right, val);
}
```

### Why Fast?

Har step par ek side discard hoti hai.

```text
8
/ \
3 10

Search 10:
8 < 10 -> right
Found 10
```

---

# 6. Example 1: Search in a BST

## Problem

BST mein value search karo.

```text
BST:
        8
       / \
      3   10
     / \    \
    1   6   14

target = 6

Output: Node 6
```

## Why BST Property?

```text
6 < 8 -> left
6 > 3 -> right
Found 6
```

## Solution

```java
public TreeNode searchBST(TreeNode root, int val) {
    while (root != null && root.val != val) {
        root = val < root.val ? root.left : root.right;
    }

    return root;
}
```

## Dry Run

```text
root = 8
target = 6

8 > 6 -> left

root = 3
6 > 3 -> right

root = 6
Found

Return node 6
```

## Complexity

- Time: \(O(h)\)
- Space: \(O(1)\)

---

# 7. Example 2: Insert into a BST

## Problem

BST mein new value insert karo.

```text
BST:
        8
       / \
      3   10

Insert 5

Output:
        8
       / \
      3   10
       \
        5
```

## Why BST Property?

```text
5 < 8 -> left
5 > 3 -> right
Insert at right of 3
```

## Solution

```java
public TreeNode insertIntoBST(TreeNode root, int val) {
    if (root == null) {
        return new TreeNode(val);
    }

    if (val < root.val) {
        root.left = insertIntoBST(root.left, val);
    } else {
        root.right = insertIntoBST(root.right, val);
    }

    return root;
}
```

## Iterative Solution

```java
public TreeNode insertIntoBSTIterative(TreeNode root, int val) {
    if (root == null) {
        return new TreeNode(val);
    }

    TreeNode current = root;

    while (true) {
        if (val < current.val) {
            if (current.left == null) {
                current.left = new TreeNode(val);
                break;
            }

            current = current.left;
        } else {
            if (current.right == null) {
                current.right = new TreeNode(val);
                break;
            }

            current = current.right;
        }
    }

    return root;
}
```

## Complexity

- Time: \(O(h)\)
- Space: Recursive \(O(h)\), Iterative \(O(1)\)

---

# 8. Example 3: Delete Node in a BST

## Problem

BST se node delete karo.

```text
BST:
        8
       / \
      3   10
     / \    \
    1   6   14

Delete 3
```

## Delete Cases

### Case 1: Leaf Node

```text
Delete 1
Simply remove it.
```

### Case 2: One Child

```text
Delete 10
Replace 10 with 14
```

### Case 3: Two Children

```text
Delete 8
Replace with inorder successor / predecessor
```

Inorder successor:

```text
Right subtree ka smallest node
```

## Solution

```java
public TreeNode deleteNode(TreeNode root, int key) {
    if (root == null) {
        return null;
    }

    if (key < root.val) {
        root.left = deleteNode(root.left, key);
    } else if (key > root.val) {
        root.right = deleteNode(root.right, key);
    } else {
        if (root.left == null) {
            return root.right;
        }

        if (root.right == null) {
            return root.left;
        }

        TreeNode successor = root.right;

        while (successor.left != null) {
            successor = successor.left;
        }

        root.val = successor.val;

        root.right = deleteNode(root.right, successor.val);
    }

    return root;
}
```

## Dry Run

```text
Delete 8

Root found: 8

Left and right both exist

Find inorder successor:
Right subtree smallest = 10

Replace:
root.val = 10

Delete 10 from right subtree

Final BST:
        10
       /  \
      3    14
     / \
    1   6
```

## Complexity

- Time: \(O(h)\)
- Space: \(O(h)\)

---

# 9. Example 4: Validate Binary Search Tree

## Problem

Binary tree valid BST hai ya nahi check karo.

```text
Valid:
    2
   / \
  1   3

Output: true
```

```text
Invalid:
    5
   / \
  1   4
     / \
    3   6

Output: false
```

## Why Range-Based DFS?

Sirf immediate child check karna enough nahi hai.

Poori subtree ki range maintain karni hoti hai.

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

## Inorder Alternative

```java
public boolean isValidBSTInorder(TreeNode root) {
    long[] prev = new long;

    prev = Long.MIN_VALUE;

    return inorder(root, prev);
}

private boolean inorder(TreeNode node, long[] prev) {
    if (node == null) {
        return true;
    }

    if (!inorder(node.left, prev)) {
        return false;
    }

    if (node.val <= prev) {
        return false;
    }

    prev = node.val;

    return inorder(node.right, prev);
}
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(h)\)

---

# 10. Example 5: Kth Smallest Element in a BST

## Problem

BST mein `k`th smallest element nikalo.

```text
BST:
        5
       / \
      3   6
     / \
    2   4
   /
  1

k = 3

Output: 3
```

## Why Inorder?

BST inorder sorted order deta hai:

```text
1 2 3 4 5 6
```

3rd smallest = `3`.

## Solution

```java
public int kthSmallest(TreeNode root, int k) {
    Deque<TreeNode> stack = new ArrayDeque<>();

    TreeNode current = root;

    while (current != null || !stack.isEmpty()) {

        while (current != null) {
            stack.push(current);
            current = current.left;
        }

        current = stack.pop();

        k--;

        if (k == 0) {
            return current.val;
        }

        current = current.right;
    }

    return -1;
}
```

## Dry Run

```text
BST:
        5
       / \
      3   6
     / \
    2   4
   /
  1

Inorder:
1 2 3 4 5 6

k = 3

1 -> k = 2
2 -> k = 1
3 -> k = 0

Answer = 3
```

## Complexity

- Time: \(O(h + k)\)
- Space: \(O(h)\)

---

# 11. Example 6: Kth Largest Element in a BST

## Problem

BST mein `k`th largest element nikalo.

```text
BST:
        5
       / \
      3   6
     / \
    2   4

k = 2

Output: 5
```

## Why Reverse Inorder?

Normal inorder:

```text
2 3 4 5 6
```

Reverse inorder:

```text
6 5 4 3 2
```

2nd largest = `5`.

## Solution

```java
public int kthLargest(TreeNode root, int k) {
    Deque<TreeNode> stack = new ArrayDeque<>();

    TreeNode current = root;

    while (current != null || !stack.isEmpty()) {

        while (current != null) {
            stack.push(current);
            current = current.right;
        }

        current = stack.pop();

        k--;

        if (k == 0) {
            return current.val;
        }

        current = current.left;
    }

    return -1;
}
```

## Complexity

- Time: \(O(h + k)\)
- Space: \(O(h)\)

---

# 12. Example 7: Lowest Common Ancestor of a BST

## Problem

BST mein do nodes ka Lowest Common Ancestor nikalo.

```text
BST:
        6
       / \
      2   8
     / \ / \
    0  4 7  9
      / \
     3   5

LCA(2, 8) = 6
LCA(2, 4) = 2
```

## Why BST Property?

```text
Agar p aur q dono root se chhote hain -> left jao
Agar p aur q dono root se bade hain -> right jao
Warna root hi LCA hai
```

## Solution

```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    TreeNode current = root;

    while (current != null) {
        if (p.val < current.val && q.val < current.val) {
            current = current.left;
        } else if (p.val > current.val && q.val > current.val) {
            current = current.right;
        } else {
            return current;
        }
    }

    return null;
}
```

## Dry Run

```text
LCA(2, 8)

current = 6

2 < 6 and 8 > 6

Return 6
```

```text
LCA(2, 4)

current = 6

2 < 6 and 4 < 6
current = 2

2 == 2 or 4 > 2

Return 2
```

## Complexity

- Time: \(O(h)\)
- Space: \(O(1)\)

---

# 13. Example 8: Range Sum of BST

## Problem

BST mein `[low, high]` range ke nodes ka sum nikalo.

```text
BST:
        10
       /  \
      5    15
     / \     \
    3   7    18

low = 7
high = 15

Output: 32
Explanation: 7 + 10 + 15
```

## Why BST Pruning?

```text
Agar node.val < low:
    Sirf right subtree dekho

Agar node.val > high:
    Sirf left subtree dekho

Warna:
    Node value add karo
    Dono subtrees dekho
```

## Solution

```java
public int rangeSumBST(TreeNode root, int low, int high) {
    if (root == null) {
        return 0;
    }

    if (root.val < low) {
        return rangeSumBST(root.right, low, high);
    }

    if (root.val > high) {
        return rangeSumBST(root.left, low, high);
    }

    return root.val +
           rangeSumBST(root.left, low, high) +
           rangeSumBST(root.right, low, high);
}
```

## Complexity

- Time: \(O(n)\) worst case, often better due to pruning
- Space: \(O(h)\)

---

# 14. BST vs Binary Tree

| Feature | Binary Tree | BST |
|---|---|---|
| Ordering | No rule | Left < Root < Right |
| Search | \(O(n)\) | \(O(h)\) |
| Inorder traversal | Any order | Sorted order |
| Insert position | Anywhere valid | Based on value |
| Use case | General hierarchy | Ordered data |

---

# 15. Common Mistakes

## Mistake 1: Sirf Immediate Child Check Karna

Galat:

```java
root.left.val < root.val &&
root.right.val > root.val
```

Ye sufficient nahi hai.

Correct:

```java
validate(node.left, min, node.val)
validate(node.right, node.val, max)
```

---

## Mistake 2: Duplicate Values Allow Karna

Standard BST mein duplicates usually allow nahi hote.

```text
Left < Node < Right
```

---

## Mistake 3: Inorder Traversal Se Sorted Order Expect Karna Without BST

Inorder sorted sirf **valid BST** ke liye hota hai.

---

## Mistake 4: Delete Case Mein Two Children Handle Na Karna

Two children case mein inorder successor/predecessor use karo.

---

## Mistake 5: Skewed BST Complexity Ignore Karna

Agar BST skewed ho:

```text
1 -> 2 -> 3 -> 4
```

Search/insert/delete:

```text
O(n)
```

Isliye production mein balanced BST use hota hai:

```text
AVL Tree
Red-Black Tree
B-Tree
```

---

# 16. BST Decision Flow

```text
Problem padho
      |
      v
Kya tree BST hai?
      |
      +-- No --> BST Property probably nahi
      |
      +-- Yes
          |
          v
Kya search/insert/delete chahiye?
      |
      +-- Yes --> BST Search / Insert / Delete
      |
      +-- Kya kth smallest/largest chahiye?
              |
              +-- Yes --> Inorder / Reverse Inorder
              |
              +-- Kya range query chahiye?
                      |
                      +-- Yes --> BST Pruning
                      |
                      +-- Kya LCA chahiye?
                              |
                              +-- Yes --> BST LCA
```

---

# 17. Important Problems

## Easy

- Search in a Binary Search Tree
- Insert into a Binary Search Tree
- Range Sum of BST
- Minimum Absolute Difference in BST
- Two Sum IV — Input is a BST

## Medium

- Validate Binary Search Tree
- Kth Smallest Element in a BST
- Kth Largest Element in a BST
- Lowest Common Ancestor of a BST
- Delete Node in a BST
- Convert Sorted Array to BST

## Hard

- Recover Binary Search Tree
- Largest BST in a Binary Tree
- Serialize and Deserialize BST
- Count of Smaller Numbers After Self

---

# 18. One-Line Recognition

> **Agar problem BST search, insert, delete, sorted inorder, kth smallest/largest, range query, ya BST validation maang rahi hai — BST Property try karo.**

BST Property ka main power ye hai ki ordered structure ki wajah se har