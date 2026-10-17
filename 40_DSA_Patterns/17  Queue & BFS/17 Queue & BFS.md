# 🚀 DSA Pattern 17 — Queue / BFS

> **Goal:** Queue aur BFS ko product-based company interview level par samajhna — real-world intuition, kyun use hota hai, kaise identify karein, templates, dry runs, multi-source BFS, shortest path, production-style examples, common mistakes aur important problems.

---

## 1. Queue / BFS Kya Hai?

### Queue

Queue ek **FIFO — First In First Out** data structure hai.

```text
Enqueue -> Rear par add
Dequeue -> Front se remove
```

Example:

```text
Queue: [A, B, C]

A enqueue hua pehla
A dequeue hoga pehla
```

Java:

```java
Queue<Integer> queue = new LinkedList<>();

queue.offer(1);   // add
queue.poll();     // remove front
queue.peek();     // see front
```

### BFS

BFS ka full form hai:

```text
Breadth First Search
```

BFS graph/tree/matrix ko **level by level** explore karta hai.

```text
Level 0: Source
Level 1: Direct neighbors
Level 2: Neighbors of neighbors
Level 3: ...
```

Example:

```text
        A
       / \
      B   C
     / \   \
    D   E   F
```

BFS from A:

```text
A -> B -> C -> D -> E -> F
```

---

## 2. Queue / BFS Kyun Use Karte Hain?

Main reason: **unweighted graph ya grid mein shortest path nikalna.**

BFS pehle distance `1` wale nodes explore karta hai, phir distance `2`, phir distance `3`.

Isliye jab tak target milta hai, wo guaranteed **shortest distance** hota hai.

### Real-World Production Examples

| Real System | BFS / Queue Use |
|---|---|
| Google Maps | Shortest route in unweighted road graph |
| Social Network | Friends suggestion, degrees of connection |
| LinkedIn | 1st degree, 2nd degree connections |
| Uber / Ola | Nearest driver from multiple driver locations |
| Swiggy / Zomato | Nearest restaurant/delivery partner |
| Kafka Consumer | Message queue processing in FIFO order |
| Job Scheduler | Background jobs process queue se |
| Web Crawler | URLs level-by-level crawl karna |
| Multiplayer Game | Nearest enemy/player search |
| Notification System | Pending notifications queue se process |

### Product-Based Interview Framing

Interviewer aksar puchta hai:

> “Tumhare system mein nearest driver kaise dhundhoge?”

Answer:

```text
Driver locations ko multi-source BFS queue mein daalenge.
User location se BFS expand karenge.
Jis driver tak pehli baar pahunchenge, wo nearest driver hoga.
```

Ya:

> “Social network mein 2nd degree connection kaise nikaloge?”

Answer:

```text
User se BFS start karo.
Level 1 = direct friends.
Level 2 = friends of friends.
Level 2 nodes hi 2nd degree connections hain.
```

---

## 3. Kaise Pata Chalega Ki Queue / BFS Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible BFS |
|---|---|
| Shortest path in unweighted graph | BFS |
| Minimum moves / steps | BFS |
| Minimum time to spread | Multi-source BFS |
| Nearest source | Multi-source BFS |
| Level order traversal | BFS |
| Word transformation | BFS |
| Grid shortest path | BFS |
| Social network degrees | BFS |
| Web crawling | BFS |
| Rotting oranges | Multi-source BFS |

### Common Keywords

- Shortest path
- Minimum steps
- Minimum moves
- Minimum time
- Nearest
- Level by level
- Spread
- Layers
- Connections
- Reach

### Mental Checklist

1. Kya graph, tree, grid, ya matrix hai?  
2. Kya har move ka cost same hai?  
3. Kya shortest/minimum steps chahiye?  
4. Kya multiple starting points hain?  
5. Kya level-wise processing chahiye?  

Agar 3–4 answers **yes** hain, to BFS try karo.

---

# 4. BFS Ka Core Intuition

BFS ek queue use karta hai.

```text
Queue FIFO hoti hai.

Isliye jo node pehle aaya,
wo pehle process hoga.
```

Example:

```text
Source: A

Queue:
[A]

Process A:
Neighbors: B, C

Queue:
[B, C]

Process B:
Neighbors: D, E

Queue:
[C, D, E]

Process C:
Neighbors: F

Queue:
[D, E, F]
```

BFS order:

```text
A -> B -> C -> D -> E -> F
```

### Why Shortest Path?

BFS distance order mein explore karta hai:

```text
Distance 0 -> Source
Distance 1 -> Direct neighbors
Distance 2 -> Next layer
```

Jaise hi target milta hai, wo shortest distance hota hai.

---

# 5. BFS Template — Graph

```java
public int bfs(int n, List<List<Integer>> adj, int source, int destination) {
    boolean[] visited = new boolean[n];
    int[] distance = new int[n];

    Arrays.fill(distance, -1);

    Queue<Integer> queue = new LinkedList<>();

    queue.offer(source);
    visited[source] = true;
    distance[source] = 0;

    while (!queue.isEmpty()) {
        int node = queue.poll();

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                distance[neighbor] = distance[node] + 1;
                queue.offer(neighbor);
            }
        }
    }

    return distance[destination];
}
```

### Important

`visited` mark karna queue mein daalte waqt zaroori hai.

Agar pop karte waqt mark karoge, to same node queue mein multiple baar aa sakta hai.

---

# 6. Example 1: Shortest Path in Unweighted Graph

## Problem

`n` nodes hain aur undirected edges hain.

Source se destination tak shortest path ki length nikalo.

```text
n = 6
edges = [
 ,[0][1]
 ,[2][0]
 ,[1][3]
 ,[2][3]
 ,[3][4]
[4][5]
]

source = 0
destination = 5

Output: 4
Path: 0 -> 1 -> 3 -> 4 -> 5
```

## Why BFS?

Har edge ka weight `1` hai.

Isliye BFS shortest path dega.

## Solution

```java
public int shortestPath(int n, int[][] edges, int source, int destination) {
    List<List<Integer>> adj = new ArrayList<>();

    for (int i = 0; i < n; i++) {
        adj.add(new ArrayList<>());
    }

    for (int[] edge : edges) {
        int u = edge;
        int v = edge;

        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    boolean[] visited = new boolean[n];
    int[] distance = new int[n];

    Arrays.fill(distance, -1);

    Queue<Integer> queue = new LinkedList<>();

    queue.offer(source);
    visited[source] = true;
    distance[source] = 0;

    while (!queue.isEmpty()) {
        int node = queue.poll();

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                distance[neighbor] = distance[node] + 1;
                queue.offer(neighbor);
            }
        }
    }

    return distance[destination];
}
```

## Dry Run

```text
Graph:
0 - 1
0 - 2
1 - 3
2 - 3
3 - 4
4 - 5

Start BFS from 0.

Queue:

distance = 0

Process 0:
Neighbors: 1, 2

distance = 1
distance = 1

Queue:
[1][2]

Process 1:
Neighbors: 0, 3

distance = 2

Queue:
[2][3]

Process 2:
Neighbors: 0, 3

3 already visited

Queue:


Process 3:
Neighbors: 1, 2, 4

distance = 3

Queue:


Process 4:
Neighbors: 3, 5

distance = 4

Queue:


Process 5:
No unvisited neighbor

Answer:
distance = 4
```

## Complexity

- Time: \(O(V + E)\)
- Space: \(O(V + E)\)

---

# 7. Example 2: Binary Tree Level Order Traversal

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

Level-wise traversal ka direct matlab hai BFS.

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

## Why `int size = queue.size()`?

Current level ke nodes ki count fix karne ke liye.

```text
Queue mein current level ke nodes hain.
Unhe process karo.
Naye nodes next level ke hain.
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


Level 1:
size = 1
process 3
add 9, 20

ans = []
Queue:
[9][20]

Level 2:
size = 2
process 9
process 20
add 15, 7

ans = [, ][9][20]
Queue:
[7][15]

Level 3:
size = 2
process 15
process 7

ans = [,, ][7][9][15][20]
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(w)\), where `w` = maximum width

---

# 8. Example 3: Rotting Oranges — Multi-Source BFS

## Problem

Grid mein:

```text
0 = empty cell
1 = fresh orange
2 = rotten orange
```

Har minute rotten orange apne 4-directional fresh neighbors ko rotten kar deta hai.

Minimum minutes nikalo jisme saare fresh oranges rotten ho jayein.

Agar possible na ho, `-1` return karo.

```text
grid = [
 ,[1][2]
 ,[0][1]
[0][1]
]

Output: 4
```

## Why Multi-Source BFS?

Multiple rotten oranges ek saath spread karte hain.

To saare rotten oranges ko initially queue mein daalo.

Ye multi-source BFS hai.

### Production Example

Socho:

```text
Multiple delivery partners city mein hain.
User se nearest delivery partner dhundhna hai.

Saare delivery partners queue mein daalo.
BFS expand karo.
Jis user cell par pehli baar pahuncho,
wo nearest delivery partner ka distance hoga.
```

## Solution

```java
public int orangesRotting(int[][] grid) {
    int rows = grid.length;
    int cols = grid.length;

    Queue<int[]> queue = new LinkedList<>();
    int fresh = 0;

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (grid[i][j] == 2) {
                queue.offer(new int[]{i, j});
            } else if (grid[i][j] == 1) {
                fresh++;
            }
        }
    }

    if (fresh == 0) {
        return 0;
    }

    int minutes = 0;
    int[][] directions = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    while (!queue.isEmpty() && fresh > 0) {
        int size = queue.size();
        minutes++;

        for (int i = 0; i < size; i++) {
            int[] cell = queue.poll();

            for (int[] dir : directions) {
                int newRow = cell + dir;
                int newCol = cell + dir;

                if (newRow >= 0 && newRow < rows &&
                    newCol >= 0 && newCol < cols &&
                    grid[newRow][newCol] == 1) {

                    grid[newRow][newCol] = 2;
                    fresh--;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }
    }

    return fresh == 0 ? minutes : -1;
}
```

## Dry Run

```text
grid = [
 ,[1][2]
 ,[0][1]
[0][1]
]

Initial rotten:
(0,0)

fresh = 6

Minute 1:
(0,0) rots (0,1), (1,0)

fresh = 4
Queue:
[(0,1), (1,0)]

Minute 2:
(0,1) rots (0,2)
(1,0) rots (1,1)

fresh = 2
Queue:
[(0,2), (1,1)]

Minute 3:
(1,1) rots (2,1)

fresh = 1
Queue:
[(0,2), (2,1)]

Minute 4:
(2,1) rots (2,2)

fresh = 0

Answer = 4
```

## Complexity

- Time: \(O(rows \times cols)\)
- Space: \(O(rows \times cols)\)

---

# 9. Example 4: 01 Matrix — Nearest Zero Distance

## Problem

Binary matrix mein har cell ke liye nearest `0` ki distance nikalo.

```text
mat = [
 ,[0]
 ,[1][0]
[1]
]

Output:
[
 ,[0]
 ,[1][0]
[1][2]
]
```

## Why Multi-Source BFS?

Saare zeros sources hain.

Hum zeros se BFS expand karenge.

Jaise hi kisi `1` tak pehli baar pahunchenge, wo uska nearest zero distance hoga.

### Production Example

Socho:

```text
City map mein hospitals hain.
Har location ke liye nearest hospital distance nikalna hai.

Saare hospitals multi-source BFS queue mein daalo.
Har location ka nearest hospital distance nikal jayega.
```

## Solution

```java
public int[][] updateMatrix(int[][] mat) {
    int rows = mat.length;
    int cols = mat.length;

    int[][] distance = new int[rows][cols];
    Queue<int[]> queue = new LinkedList<>();

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (mat[i][j] == 0) {
                queue.offer(new int[]{i, j});
            } else {
                distance[i][j] = -1;
            }
        }
    }

    int[][] directions = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    while (!queue.isEmpty()) {
        int[] cell = queue.poll();

        for (int[] dir : directions) {
            int newRow = cell + dir;
            int newCol = cell + dir;

            if (newRow >= 0 && newRow < rows &&
                newCol >= 0 && newCol < cols &&
                distance[newRow][newCol] == -1) {

                distance[newRow][newCol] =
                    distance[cell][cell] + 1;

                queue.offer(new int[]{newRow, newCol});
            }
        }
    }

    return distance;
}
```

## Dry Run

```text
mat = [
 ,[0]
 ,[1][0]
[1]
]

Initial queue:
(0,0), (0,1), (0,2), (1,0), (1,2)

distance:
0 0 0
0 -1 0
-1 -1 -1

BFS expands:

(1,1):
distance = 1

(2,0):
distance = 1

(2,2):
distance = 1

(2,1):
distance = 2

Final:
0 0 0
0 1 0
1 2 1
```

## Complexity

- Time: \(O(rows \times cols)\)
- Space: \(O(rows \times cols)\)

---

# 10. Example 5: Word Ladder

## Problem

`beginWord` se `endWord` tak shortest transformation sequence nikalo.

Har step mein sirf ek letter change kar sakte ho.

Har intermediate word `wordList` mein hona chahiye.

```text
beginWord = "hit"
endWord = "cog"
wordList = ["hot", "dot", "dog", "lot", "log", "cog"]

Output: 5
Explanation:
hit -> hot -> dot -> dog -> cog
```

## Why BFS?

Har transformation ek edge hai.

Har edge ka weight `1` hai.

Isliye shortest transformation sequence BFS se milegi.

### Production Example

Socho:

```text
Spell correction / word suggestion system.
Ek word se dusre valid word tak minimum edits nikalna.
```

## Solution

```java
public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Set<String> wordSet = new HashSet<>(wordList);

    if (!wordSet.contains(endWord)) {
        return 0;
    }

    Queue<String> queue = new LinkedList<>();
    queue.offer(beginWord);

    Set<String> visited = new HashSet<>();
    visited.add(beginWord);

    int level = 1;

    while (!queue.isEmpty()) {
        int size = queue.size();

        for (int i = 0; i < size; i++) {
            String word = queue.poll();

            if (word.equals(endWord)) {
                return level;
            }

            for (int pos = 0; pos < word.length(); pos++) {
                char[] chars = word.toCharArray();

                for (char c = 'a'; c <= 'z'; c++) {
                    chars[pos] = c;

                    String nextWord = new String(chars);

                    if (wordSet.contains(nextWord) &&
                        !visited.contains(nextWord)) {

                        visited.add(nextWord);
                        queue.offer(nextWord);
                    }
                }
            }
        }

        level++;
    }

    return 0;
}
```

## Dry Run

```text
beginWord = "hit"
endWord = "cog"

Level 1:
hit

Level 2:
hot

Level 3:
dot, lot

Level 4:
dog, log

Level 5:
cog

Answer = 5
```

## Complexity

Let:

```text
n = number of words
L = word length
```

- Time: \(O(n \times L^2 \times 26)\)
- Space: \(O(n \times L)\)

---

# 11. BFS vs DFS

| Feature | BFS | DFS |
|---|---|---|
| Data structure | Queue | Stack / Recursion |
| Traversal | Level by level | Depth-wise |
| Shortest path unweighted | Yes | No |
| Memory | Usually more | Usually less |
| Cycle detection | Possible | Possible |
| Use case | Nearest / shortest | Explore all paths |
| Example | Rotting Oranges | Number of Islands |

### Simple Rule

```text
Shortest / nearest / minimum steps -> BFS
Explore all paths / connected components -> DFS
```

---

# 12. BFS Variants

## 1. Simple BFS

Ek source se traversal.

```text
Use:
Graph traversal
Connected component
Reachability
```

## 2. Multi-Source BFS

Multiple sources se simultaneous BFS.

```text
Use:
Rotting Oranges
01 Matrix
Nearest hospital
Nearest driver
```

## 3. Level Order BFS

Har level ko alag process karna.

```text
Use:
Tree level order
Social network degrees
Word Ladder
```

## 4. BFS with State

Queue mein sirf node nahi, state store karna.

```text
Use:
Keys and Doors
Shortest path with obstacles
State-based grid problems
```

---

# 13. Common Mistakes

## Mistake 1: Visited Mark Pop Karte Waqt Karna

Galat:

```java
int node = queue.poll();
visited[node] = true;
```

Isse same node multiple baar queue mein aa sakta hai.

Correct:

```java
visited[neighbor] = true;
queue.offer(neighbor);
```

---

## Mistake 2: Level Size Capture Na Karna

Level-wise answer chahiye to:

```java
int size = queue.size();

for (int i = 0; i < size; i++) {
    // process current level
}
```

---

## Mistake 3: Grid Bounds Check Bhoolna

```java
if (newRow >= 0 && newRow < rows &&
    newCol >= 0 && newCol < cols)
```

Ye check har direction move se pehle karo.

---

## Mistake 4: Weighted Graph Mein BFS Lagana

BFS sirf **unweighted** graph ya equal-cost edges ke liye shortest path deta hai.

Weighted graph ke liye:

```text
Dijkstra
Bellman-Ford
A*
```

---

## Mistake 5: Multi-Source BFS Mein Sirf Ek Source Push Karna

Agar multiple rotten oranges hain:

```java
for all rotten cells:
    queue.offer(cell)
```

Sirf ek source push karna galat hoga.

---

## Mistake 6: Production System Mein Memory Ignore Karna

BFS poori level memory mein rakh sakta hai.

Large graph mein:

```text
Visited set optimized karo
Bidirectional BFS use karo
Level-wise batching karo
External queue / Kafka use karo
```

---

# 14. BFS Decision Flow

```text
Problem padho
      |
      v
Kya shortest path / minimum steps chahiye?
      |
      +-- No --> BFS probably nahi
      |
      +-- Yes
          |
          v
Kya graph unweighted hai?
      |
      +-- No --> Dijkstra / Bellman-Ford
      |
      +-- Yes
          |
          v
Kya multiple sources hain?
      |
      +-- Yes --> Multi-Source BFS
      |
      +-- Kya level-wise answer chahiye?
              |
              +-- Yes --> Level Order BFS
              |
              +-- Kya state bhi matter karti hai?
                      |
                      +-- Yes --> BFS with State
```

---

# 15. Important Problems

## Easy

- Binary Tree Level Order Traversal
- Minimum Depth of Binary Tree
- Same Tree
- Symmetric Tree

## Medium

- Rotting Oranges
- 01 Matrix
- Shortest Path in Binary Matrix
- Word Ladder
- Open the Lock
- Number of Islands
- Walls and Gates
- Nearest Exit from Entrance in Maze

## Hard

- Word Ladder II
- Shortest Path in a Grid with Obstacles Elimination
- Minimum Moves to Reach Target with Rotations
- Shortest Path Visiting All Nodes

---

# 16. One-Line Recognition

> **Agar problem shortest path, minimum steps, nearest source, level-wise traversal, ya simultaneous spread ki baat kar rahi hai — Queue / BFS try karo.**

BFS ka main power ye hai ki queue FIFO order mein nodes process karta hai, isliye graph ko distance layers mein explore karta hai. Unweighted graph mein pehli baar target milne par wo guaranteed shortest path hota hai.