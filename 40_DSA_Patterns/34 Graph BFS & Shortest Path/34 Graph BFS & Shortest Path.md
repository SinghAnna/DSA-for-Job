# 🚀 DSA Pattern 34 — Graph BFS / Shortest Path

> **Goal:** Graph BFS / Shortest Path ko product-based company interview level par samajhna — real-world intuition, kyun use hota hai, kaise identify karein, unweighted shortest path, multi-source BFS, 0-1 BFS, word ladder, grid shortest path, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Graph BFS / Shortest Path Kya Hai?

BFS ka full form hai:

```text
Breadth First Search
```

BFS graph ko **level by level** explore karta hai.

```text
Distance 0: Source
Distance 1: Direct neighbors
Distance 2: Neighbors of neighbors
Distance 3: ...
```

Example:

```text
Graph:
A - B
A - C
B - D
C - D

BFS from A:
A -> B -> C -> D
```

Shortest distance:

```text
A to D = 2
Path: A -> B -> D
   ya A -> C -> D
```

---

## 2. Graph BFS Kyun Use Karte Hain?

Main reason: **unweighted graph mein shortest path nikalna.**

Agar har edge ka weight same ho, usually `1`, to BFS shortest path guarantee karta hai.

### Why BFS Gives Shortest Path?

BFS pehle distance `1` wale nodes explore karta hai, phir distance `2`, phir distance `3`.

```text
Queue FIFO hoti hai.

Isliye nodes distance order mein process hote hain.
```

### Core Intuition

> Jaise hi target node pehli baar mile, wahi shortest distance hota hai.

---

## 3. Real-World Production Examples

| Real System | BFS / Shortest Path Use |
|---|---|
| Google Maps | Shortest route on unweighted road graph |
| Uber / Ola | Nearest driver |
| Swiggy / Zomato | Nearest restaurant / delivery partner |
| LinkedIn | Degrees of connection |
| Social Network | Friend suggestion |
| Multiplayer Game | Shortest move sequence |
| Web Crawler | URL crawl levels |
| Network Routing | Minimum hops |
| Recommendation System | Nearby users/items |
| Warehouse Robot | Shortest path on floor grid |
| Puzzle Game | Minimum moves |
| Chat System | Message propagation levels |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Uber mein user ke nearest 5 drivers kaise dhundhoge?”

Answer:

```text
User location se BFS start karo.

Level 1: 1 km radius
Level 2: 2 km radius
Level 3: 3 km radius

Jaise-jaise drivers milenge, unhe collect karo.
Jab 5 drivers mil jayein, ruk jao.
```

Ya:

> “LinkedIn mein user aur kisi person ke beech connection degree kaise nikaloge?”

Answer:

```text
User se BFS start karo.

Level 1 = direct connections
Level 2 = friends of friends
Level 3 = connections of connections

Target person jis level par mile,
wahi connection degree hai.
```

---

## 4. Kaise Pata Chalega Ki BFS Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible BFS |
|---|---|
| Shortest path | BFS |
| Minimum steps | BFS |
| Minimum moves | BFS |
| Minimum time | BFS, if uniform cost |
| Nearest source | Multi-source BFS |
| Level-wise traversal | BFS |
| Word transformation | BFS |
| Grid shortest path | BFS |
| Minimum hops | BFS |

### Common Keywords

- Shortest
- Minimum
- Nearest
- Fewest
- Minimum steps
- Minimum moves
- Minimum time
- Level
- Reach
- Spread

### Mental Checklist

1. Kya graph/grid/tree hai?  
2. Kya shortest/minimum steps chahiye?  
3. Kya har move ka cost same hai?  
4. Kya multiple sources hain?  
5. Kya level-wise processing chahiye?  

Agar 2–3 answers **yes** hain, to BFS try karo.

---

# 5. BFS Template — Unweighted Graph

```java
public int shortestPath(int n,
                        List<List<Integer>> adj,
                        int source,
                        int destination) {

    int[] distance = new int[n];

    Arrays.fill(distance, -1);

    Queue<Integer> queue = new LinkedList<>();

    queue.offer(source);
    distance[source] = 0;

    while (!queue.isEmpty()) {
        int node = queue.poll();

        for (int neighbor : adj.get(node)) {
            if (distance[neighbor] == -1) {
                distance[neighbor] = distance[node] + 1;
                queue.offer(neighbor);
            }
        }
    }

    return distance[destination];
}
```

### Important

`distance` array hi visited array ka kaam karta hai.

```text
distance[neighbor] == -1 -> unvisited
```

---

# 6. Example 1: Shortest Path in Unweighted Graph

## Problem

`n` nodes aur undirected edges diye hain.

Source se destination tak shortest path ki length nikalo.

```text
n = 6
edges = [
 ,[0][1]
 ,[2][0]
 ,[1][3]
 ,[3][2]
 ,[4][3]
[5][4]
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

    int[] distance = new int[n];

    Arrays.fill(distance, -1);

    Queue<Integer> queue = new LinkedList<>();

    queue.offer(source);
    distance[source] = 0;

    while (!queue.isEmpty()) {
        int node = queue.poll();

        for (int neighbor : adj.get(node)) {
            if (distance[neighbor] == -1) {
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

BFS from 0:

Queue:

distance = 0

Process 0:
distance = 1
distance = 1

Queue:
[1][2]

Process 1:
distance = 2

Queue:
[2][3]

Process 2:
3 already visited

Queue:


Process 3:
distance = 3

Queue:


Process 4:
distance = 4

Queue:


Answer:
distance = 4
```

## Complexity

- Time: \(O(V + E)\)
- Space: \(O(V + E)\)

---

# 7. Example 2: Shortest Path in Binary Matrix

## Problem

`n x n` binary matrix mein top-left se bottom-right tak shortest path nikalo.

Rules:

```text
0 = clear cell
1 = blocked cell
Movement: 8 directions allowed
```

```text
grid = [
 ,[0][1]
[1][0]
]

Output: 2
Path: (0,0) -> (1,1)
```

## Why BFS?

Har move ka cost `1` hai.

Isliye BFS shortest path dega.

## Solution

```java
public int shortestPathBinaryMatrix(int[][] grid) {
    int n = grid.length;

    if (grid != 0 || grid[n - 1][n - 1] != 0) {
        return -1;
    }

    Queue<int[]> queue = new LinkedList<>();

    queue.offer(new int[]{0, 0, 1});

    grid = 1;

    int[][] directions = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1},
        {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };

    while (!queue.isEmpty()) {
        int[] cell = queue.poll();

        int row = cell;
        int col = cell;
        int distance = cell;

        if (row == n - 1 && col == n - 1) {
            return distance;
        }

        for (int[] dir : directions) {
            int newRow = row + dir;
            int newCol = col + dir;

            if (newRow >= 0 && newRow < n &&
                newCol >= 0 && newCol < n &&
                grid[newRow][newCol] == 0) {

                grid[newRow][newCol] = 1;

                queue.offer(new int[]{newRow, newCol, distance + 1});
            }
        }
    }

    return -1;
}
```

## Dry Run

```text
grid = [
 ,[0][1]
[1][0]
]

Start:
(0,0), distance = 1

Neighbors:
(1,1) is clear

Mark visited
Queue:
[(1,1), 2]

Poll (1,1):
Destination reached

Answer = 2
```

## Complexity

- Time: \(O(n^2)\)
- Space: \(O(n^2)\)

---

# 8. Example 3: Rotting Oranges — Multi-Source BFS

## Problem

Grid mein:

```text
0 = empty
1 = fresh orange
2 = rotten orange
```

Har minute rotten orange apne 4-directional fresh neighbors ko rotten karta hai.

Minimum minutes nikalo jisme saare fresh oranges rotten ho jayein.

```text
grid = [
 ,[1][2]
 ,[0][1]
[1][0]
]

Output: 4
```

## Why Multi-Source BFS?

Multiple rotten oranges ek saath spread karte hain.

Saare rotten oranges initially queue mein daalo.

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
Initial rotten:
(0,0)

fresh = 6

Minute 1:
(0,1), (1,0) rotten
fresh = 4

Minute 2:
(0,2), (1,1) rotten
fresh = 2

Minute 3:
(2,1) rotten
fresh = 1

Minute 4:
(2,2) rotten
fresh = 0

Answer = 4
```

## Complexity

- Time: \(O(rows \times cols)\)
- Space: \(O(rows \times cols)\)

---

# 9. Example 4: 01 Matrix — Nearest Zero

## Problem

Binary matrix mein har `1` ke liye nearest `0` ki distance nikalo.

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
[2][1]
]
```

## Why Multi-Source BFS?

Saare zeros sources hain.

Zeros se BFS expand karo.

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

## Complexity

- Time: \(O(rows \times cols)\)
- Space: \(O(rows \times cols)\)

---

# 10. Example 5: Word Ladder

## Problem

`beginWord` se `endWord` tak shortest transformation sequence nikalo.

Har step mein sirf ek letter change kar sakte ho.

Intermediate words `wordList` mein hone chahiye.

```text
beginWord = "hit"
endWord = "cog"
wordList = ["hot", "dot", "dog", "lot", "log", "cog"]

Output: 5
Path:
hit -> hot -> dot -> dog -> cog
```

## Why BFS?

Har transformation ek edge hai.

Har edge ka weight `1` hai.

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

# 11. Example 6: 0-1 BFS

## Problem

Graph mein edge weights sirf `0` ya `1` hain.

Source se destination tak shortest path nikalo.

## Why 0-1 BFS?

Normal BFS tab kaam karta hai jab sab edges ka weight same ho.

Yahan do weights hain:

```text
0 and 1
```

Isliye **Deque** use karte hain:

```text
Weight 0 edge -> front se add
Weight 1 edge -> back se add
```

## Solution

```java
public int zeroOneBFS(int n, List<List<int[]>> adj, int source, int destination) {
    int[] distance = new int[n];

    Arrays.fill(distance, Integer.MAX_VALUE);

    distance[source] = 0;

    Deque<Integer> deque = new ArrayDeque<>();

    deque.offerFirst(source);

    while (!deque.isEmpty()) {
        int node = deque.pollFirst();

        for (int[] edge : adj.get(node)) {
            int neighbor = edge;
            int weight = edge;

            int newDistance = distance[node] + weight;

            if (newDistance < distance[neighbor]) {
                distance[neighbor] = newDistance;

                if (weight == 0) {
                    deque.offerFirst(neighbor);
                } else {
                    deque.offerLast(neighbor);
                }
            }
        }
    }

    return distance[destination];
}
```

## Complexity

- Time: \(O(V + E)\)
- Space: \(O(V + E)\)

---

# 12. BFS vs DFS

| Feature | BFS | DFS |
|---|---|---|
| Data structure | Queue | Stack / Recursion |
| Traversal | Level-wise | Depth-wise |
| Shortest path unweighted | Yes | No |
| Memory | Usually more | Usually less |
| Cycle detection | Possible | Yes |
| Use case | Nearest / shortest | Components / paths |
| Example | Rotting Oranges | Number of Islands |

### Rule

```text
Shortest / nearest / minimum steps -> BFS
Connected components / cycle / all paths -> DFS
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

## Mistake 2: Weighted Graph Mein Normal BFS Lagana

BFS sirf unweighted graph ke liye shortest path deta hai.

Weighted graph ke liye:

```text
Dijkstra
Bellman-Ford
0-1 BFS
A*
```

---

## Mistake 3: Grid Bounds Check Bhoolna

```java
if (newRow >= 0 && newRow < rows &&
    newCol >= 0 && newCol < cols)
```

---

## Mistake 4: Multi-Source BFS Mein Sirf Ek Source Push Karna

Saare sources initially queue mein daalo:

```java
for (int[] source : sources) {
    queue.offer(source);
}
```

---

## Mistake 5: Level Size Capture Na Karna

Level-wise answer chahiye to:

```java
int size = queue.size();

for (int i = 0; i < size; i++) {
    // process current level
}
```

---

## Mistake 6: Large Graph Mein Memory Ignore Karna

BFS poori level memory mein rakh sakta hai.

Large graph mein:

```text
Bidirectional BFS
Level-wise batching
External queue
```

Use karo.

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
      +-- No --> Dijkstra / Bellman-Ford / 0-1 BFS
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
              +-- Kya word transformation hai?
                      |
                      +-- Yes --> BFS on word graph
```

---

# 15. Important Problems

## Easy

- Binary Tree Level Order Traversal
- Minimum Depth of Binary Tree
- Find if Path Exists in Graph

## Medium

- Rotting Oranges
- 01 Matrix
- Shortest Path in Binary Matrix
- Word Ladder
- Open the Lock
- Nearest Exit from Entrance in Maze
- Walls and Gates
- Number of Islands

## Hard

- Word Ladder II
- Shortest Path in a Grid with Obstacles Elimination
- Minimum Moves to Reach Target with Rotations
- Shortest Path Visiting All Nodes

---

# 16. One-Line Recognition

> **Agar problem shortest path, minimum steps, nearest source, level-wise traversal, ya simultaneous spread maang rahi hai — Graph BFS try karo.**

Graph BFS ka main power ye hai ki queue FIFO order mein nodes process karta hai, isliye unweighted graph mein pehli baar target milne par wo guaranteed shortest path hota hai.