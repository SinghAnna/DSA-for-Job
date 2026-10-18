# 🚀 DSA Pattern 48 — DP on Trees

> **Goal:** DP on Trees ko product-based company interview level par samajhna — real-world intuition, kyun tree DP use hota hai, kaise identify karein, include/exclude state, post-order traversal, state design, transition, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. DP on Trees Kya Hai?

DP on Trees mein hum har node par ek DP value calculate karte hain.

```text
dp[node] = answer for subtree rooted at node
```

### Simple Intuition

> Har node ka answer uske children ke answers par depend karta hai.

Example:

```text
Tree:
    3
   / \
  2   5
 / \
1   4

dp = 1
dp = 4
dp = max(2, 1 + 4)
dp = 5
dp = max(3, dp + dp)
```

---

## 2. DP on Trees Kyun Use Karte Hain?

Main reasons:

1. House Robber III
2. Binary Tree Maximum Path Sum
3. Longest Univalue Path
4. Diameter of Binary Tree
5. Maximum Path Sum in Tree
6. Tree Coloring Problems
7. Tree Matching Problems
8. Subtree Sum Problems

### Core Intuition

> Post-order traversal mein children solve karo, phir parent ka answer nikalo.

Example:

```text
dfs(node):
    dfs(left)
    dfs(right)
    dp[node] = f(dp[left], dp[right])
```

---

## 3. Real-World Production Examples

| Real System | DP on Trees Use |
|---|---|
| Org Chart | Best team selection |
| File System | Largest folder size |
| Category Tree | Best category selection |
| Menu System | Best menu selection |
| Game Development | Best move in game tree |
| Compiler | AST optimization |
| Network Topology | Best node selection |
| Recommendation System | Best category path |
| Cloud Infrastructure | Best resource selection |
| E-commerce | Best product category |
| Social Network | Best influencer selection |
| Decision Tree | Best decision path |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Company ke org chart mein best team select karni hai jisme direct manager aur uske direct reports ek saath na ho. Kaise karoge?”

Answer:

```text
Tree DP use karenge.

Har node ke liye do states:
1. Node select karo
2. Node skip karo

dp[node] = max value if node not selected
dp[node] = max value if node selected
```

Ya:

> “File system mein largest folder size nikalna hai jisme folder aur uske subfolders included ho. Kaise approach karoge?”

Answer:

```text
Tree DP use karenge.

dp[node] = node size + sum of children sizes.
```

---

## 4. Kaise Pata Chalega Ki DP on Trees Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Tree DP |
|---|---|
| Tree / binary tree | Tree DP |
| Subtree | Tree DP |
| Parent-child constraint | Tree DP |
| Include / exclude node | Tree DP |
| Maximum path in tree | Tree DP |
| Best selection in tree | Tree DP |
| Tree coloring | Tree DP |
| Tree matching | Tree DP |

### Common Keywords

- Tree
- Binary tree
- Subtree
- Parent
- Child
- Include
- Exclude
- Maximum path
- Best selection
- Coloring
- Matching

### Mental Checklist

1. Kya tree hai?  
2. Kya parent-child constraint hai?  
3. Kya include/exclude choice hai?  
4. Kya subtree answer chahiye?  
5. Kya path / chain chahiye?  

Agar 2–3 answers **yes** hain, to Tree DP try karo.

---

# 5. Tree DP Problem Solve Karne Ke 5 Steps

```text
1. State define karo
2. Transition likho
3. Base case likho
4. Traversal order decide karo
5. Final answer nikalo
```

Example — House Robber III:

```text
State:
dp[node] = max value if node not selected
dp[node] = max value if node selected

Transition:
dp[node] = max(left, left) + max(right, right)
dp[node] = node.val + left + right

Base case:
null node ->[0]

Final answer:
max(dp[root], dp[root])
```

---

# 6. Example 1: House Robber III

## Problem

Binary tree mein houses hain.

Agar kisi node ko rob kiya, to uske direct children rob nahi kar sakte.

Maximum money nikalo.

```text
Tree:
    3
   / \
  2   3
   \   \
    3   1

Output = 7
Explanation: 3 + 3 + 1
```

## Why Tree DP?

Har node ke liye do choices:

```text
1. Node select karo
2. Node skip karo
```

## State

```text
dp[node] = max value if node not selected
dp[node] = max value if node selected
```

## Transition

```text
dp[node] = max(left, left) + max(right, right)
dp[node] = node.val + left + right
```

## Solution

```java
public int rob(TreeNode root) {
    int[] result = dfs(root);

    return Math.max(result, result);
}

private int[] dfs(TreeNode node) {

    if (node == null) {
        return new int[]{0, 0};
    }

    int[] left = dfs(node.left);
    int[] right = dfs(node.right);

    int notSelected =
        Math.max(left, left) +
        Math.max(right, right);

    int selected =
        node.val + left + right;

    return new int[]{notSelected, selected};
}
```

## Dry Run

```text
Tree:
    3
   / \
  2   3
   \   \
    3   1

dfs(3 left child):
notSelected = max(0, 3) = 3
selected = 2 + 0 + 0 = 2

dfs(3 right child):
notSelected = max(0, 1) = 1
selected = 3 + 0 + 0 = 3

dfs(root):
notSelected = max(3, 2) + max(1, 3) = 6
selected = 3 + 3 + 1 = 7

Answer = 7
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(h)\)

---

# 7. Example 2: Binary Tree Maximum Path Sum

## Problem

Binary tree mein maximum path sum nikalo.

Path kisi bhi node se start ho kar kisi bhi node par end ho sakta hai.

```text
Tree:
    1
   / \
  2   3

Output = 6
Explanation: 2 + 1 + 3
```

## Why Tree DP?

Har node ke liye:

```text
1. Left subtree best path
2. Right subtree best path
3. Node ke through path
```

## State

```text
dfs(node) = max path sum starting from node going downward
```

## Transition

```text
leftGain = max(0, dfs(node.left))
rightGain = max(0, dfs(node.right))

currentPath = node.val + leftGain + rightGain

answer = max(answer, currentPath)

return node.val + max(leftGain, rightGain)
```

## Solution

```java
private int answer = Integer.MIN_VALUE;

public int maxPathSum(TreeNode root) {
    dfs(root);

    return answer;
}

private int dfs(TreeNode node) {

    if (node == null) {
        return 0;
    }

    int leftGain = Math.max(0, dfs(node.left));
    int rightGain = Math.max(0, dfs(node.right));

    int currentPath =
        node.val + leftGain + rightGain;

    answer = Math.max(answer, currentPath);

    return node.val + Math.max(leftGain, rightGain);
}
```

## Dry Run

```text
Tree:
    1
   / \
  2   3

dfs(2) = 2
dfs(3) = 3

currentPath at root = 1 + 2 + 3 = 6

Answer = 6
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(h)\)

---

# 8. Example 3: Longest Univalue Path

## Problem

Binary tree mein longest path nikalo jisme saare nodes same value ho.

```text
Tree:
    5
   / \
  4   5
 / \   \
1   1   5

Output = 2
Explanation: 1 -> 1
```

## Why Tree DP?

Har node ke liye:

```text
1. Left child same value?
2. Right child same value?
```

## Solution

```java
private int answer = 0;

public int longestUnivaluePath(TreeNode root) {
    dfs(root);

    return answer;
}

private int dfs(TreeNode node) {

    if (node == null) {
        return 0;
    }

    int left = dfs(node.left);
    int right = dfs(node.right);

    int leftPath = 0;
    int rightPath = 0;

    if (node.left != null && node.left.val == node.val) {
        leftPath = left + 1;
    }

    if (node.right != null && node.right.val == node.val) {
        rightPath = right + 1;
    }

    answer = Math.max(answer, leftPath + rightPath);

    return Math.max(leftPath, rightPath);
}
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(h)\)

---

# 9. Example 4: Diameter of Binary Tree

## Problem

Binary tree ka diameter nikalo.

Diameter = longest path between any two nodes.

```text
Tree:
    1
   / \
  2   3
 / \
4   5

Output = 3
Explanation: 4 -> 2 -> 1 -> 3
```

## Why Tree DP?

Har node ke liye:

```text
1. Left subtree height
2. Right subtree height
3. Path through node
```

## Solution

```java
private int answer = 0;

public int diameterOfBinaryTree(TreeNode root) {
    dfs(root);

    return answer;
}

private int dfs(TreeNode node) {

    if (node == null) {
        return 0;
    }

    int left = dfs(node.left);
    int right = dfs(node.right);

    answer = Math.max(answer, left + right);

    return Math.max(left, right) + 1;
}
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(h)\)

---

# 10. Example 5: Tree Coloring — Minimum Cost to Paint Tree

## Problem

Tree ke nodes ko 3 colors mein paint karna hai.

Adjacent nodes same color nahi ho sakte.

Har node ka cost diya hai.

Minimum cost nikalo.

```text
Tree:
    1
   / \
  2   3

cost = [
 ,[1][2][3]
 ,[4][5][6]
[7][8][9]
]

Output = 1 + 5 + 9 = 15? 
```

Actually best:

```text
Node 1 -> color 0 cost 1
Node 2 -> color 1 cost 5
Node 3 -> color 2 cost 9

Total = 15
```

## Why Tree DP?

```text
dp[node][color] = minimum cost to paint subtree rooted at node
                  if node has given color
```

## Transition

```text
dp[node][color] = cost[node][color] +
                  sum of min cost of children with different colors
```

## Solution

```java
public int minCost(int[][] cost, int[][] edges) {
    int n = cost.length;

    List<List<Integer>> adj = new ArrayList<>();

    for (int i = 0; i < n; i++) {
        adj.add(new ArrayList<>());
    }

    for (int[] edge : edges) {
        adj.get(edge).add(edge);
        adj.get(edge).add(edge);
    }

    boolean[] visited = new boolean[n];

    int[][] dp = new int[n];

    dfs(0, -1, cost, adj, visited, dp);

    return Math.min(
        dp,
        Math.min(dp, dp)
    );
}

private void dfs(
    int node,
    int parent,
    int[][] cost,
    List<List<Integer>> adj,
    boolean[] visited,
    int[][] dp
) {
    visited[node] = true;

    for (int color = 0; color < 3; color++) {
        dp[node][color] = cost[node][color];
    }

    for (int child : adj.get(node)) {

        if (child == parent) {
            continue;
        }

        dfs(child, node, cost, adj, visited, dp);

        for (int color = 0; color < 3; color++) {

            int best = Integer.MAX_VALUE;

            for (int childColor = 0; childColor < 3; childColor++) {

                if (childColor != color) {
                    best = Math.min(best, dp[child][childColor]);
                }
            }

            dp[node][color] += best;
        }
    }
}
```

## Complexity

- Time: \(O(n \times colors^2)\)
- Space: \(O(n \times colors)\)

---

# 11. Tree DP Patterns

| Pattern | State | Transition |
|---|---|---|
| House Robber III | `dp[node][0/1]` | include/exclude |
| Max Path Sum | `dfs(node)` | left + right + node |
| Diameter | `dfs(node)` | left + right |
| Univalue Path | `dfs(node)` | same value path |
| Tree Coloring | `dp[node][color]` | min child cost |
| Subtree Sum | `dp[node]` | node + children |

---

# 12. Post-Order Traversal

Tree DP mein mostly **post-order traversal** use hota hai.

```text
dfs(node):
    dfs(left)
    dfs(right)
    dp[node] = f(dp[left], dp[right])
```

### Why Post-Order?

Kyunki parent ka answer children ke answers par depend karta hai.

---

# 13. Common Mistakes

## Mistake 1: Base Case Bhoolna

```java
if (node == null) {
    return 0;
}
```

---

## Mistake 2: State Galat Define Karna

House Robber III:

```text
dp[node] = not selected
dp[node] = selected
```

---

## Mistake 3: Transition Galat Likna

House Robber III:

```java
dp[node] = node.val + left + right;
```

---

## Mistake 4: Max Path Sum Mein Negative Gain Handle Na Karna

```java
int leftGain = Math.max(0, dfs(node.left));
int rightGain = Math.max(0, dfs(node.right));
```

---

## Mistake 5: Tree Coloring Mein Parent Skip Na Karna

```java
if (child == parent) {
    continue;
}
```

---

# 14. Tree DP Decision Flow

```text
Problem padho
      |
      v
Kya tree hai?
      |
      +-- No --> DP probably nahi
      |
      +-- Yes
          |
          v
Kya include/exclude choice hai?
      |
      +-- Yes --> House Robber III style
      |
      +-- Kya path chahiye?
              |
              +-- Yes --> Max Path Sum style
              |
              +-- Kya coloring / matching hai?
                      |
                      +-- Yes --> Tree Coloring DP
```

---

# 15. Important Problems

## Medium

- House Robber III
- Binary Tree Maximum Path Sum
- Longest Univalue Path
- Diameter of Binary Tree
- Binary Tree Cameras
- Distribute Coins in Binary Tree

## Hard

- Binary Tree Maximum Path Sum
- Binary Tree Cameras
- Distribute Coins in Binary Tree
- Tree Coloring Problems

---

# 16. One-Line Recognition

> **Agar problem tree par hai aur parent-child constraint, include/exclude choice, ya subtree answer maang rahi hai — DP on Trees try karo.**

Tree DP ka main power ye hai ki tree based optimization problems ko simple state + transition + base case structure mein convert kar deta hai — jisse exponential solutions linear time mein solve ho jate hain.