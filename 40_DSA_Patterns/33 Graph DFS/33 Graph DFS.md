# 🚀 DSA Pattern 33 — Graph DFS

> **Goal:** Graph DFS ko product-based company interview level par samajhna — real-world intuition, kyun use hota hai, kaise identify karein, connected components, flood fill, cycle detection, path finding, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Graph DFS Kya Hai?

DFS ka full form hai:

```text
Depth First Search
```

Graph DFS mein hum ek node se start karke uske ek neighbor ko depth tak explore karte hain.

Phir wapas aakar next unvisited neighbor explore karte hain.

Example:

```text
Graph:
1 - 2
|   |
3 - 4
```

DFS from `1`:

```text
1 -> 2 -> 4 -> 3
```

---

## 2. Graph DFS Kyun Use Karte Hain?

Main reasons:

1. **Connected components dhundhna**
2. **Flood fill**
3. **Cycle detection**
4. **Path existence check**
5. **All reachable nodes explore karna**
6. **Topological sort**
7. **Backtracking on graphs**
8. **Island / region problems**

### Core Intuition

> Ek node se start karo, uske neighbors ko recursively explore karo, aur visited nodes ko mark karte jao.

Example:

```text
Grid:
1 1 0
1 0 0
0 0 1

DFS from (0,0):
(0,0) -> (0,1) -> (1,0)

Connected component:
(0,0), (0,1), (1,0)
```

---

## 3. Real-World Production Examples

| Real System | Graph DFS Use |
|---|---|
| Social Network | Friend circle / connected users |
| LinkedIn | Network reachability |
| Google Maps | Region / area detection |
| Uber / Ola | Service zone detection |
| Image Editing | Flood fill / bucket fill |
| Game Development | Map region detection |
| Compiler | Dependency cycle detection |
| Kubernetes | Service dependency graph |
| Fraud Detection | Connected suspicious accounts |
| Recommendation System | Similar user groups |
| Web Crawler | Reachable pages |
| Network Monitoring | Connected devices |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Social media app mein kitne separate friend circles hain, ye kaise nikaloge?”

Answer:

```text
Users ko nodes maano.
Friendship ko edge maano.

Har unvisited user se DFS chalao.
Ek DFS call ek connected component / friend circle count karega.
```

Ya:

> “Image editor mein user kisi pixel par click kare aur same color wala connected area change karna ho. Kaise karoge?”

Answer:

```text
Flood Fill DFS use karo.

Starting pixel se same color wale connected pixels traverse karo.
Unka color change karo.
```

---

## 4. Kaise Pata Chalega Ki Graph DFS Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Graph DFS |
|---|---|
| Connected components | DFS |
| Number of islands | DFS |
| Flood fill | DFS |
| Cycle detection | DFS |
| Path existence | DFS |
| All reachable nodes | DFS |
| Topological sort | DFS |
| Graph coloring | DFS |
| Region count | DFS |

### Common Keywords

- Connected
- Component
- Island
- Region
- Flood fill
- Reachable
- Cycle
- Path
- Visit
- Explore

### Mental Checklist

1. Kya graph / grid / network hai?  
2. Kya connected groups chahiye?  
3. Kya cycle detect karna hai?  
4. Kya ek node se doosre node tak pahunch sakte hain?  
5. Kya region fill/change karna hai?  

Agar 2–3 answers **yes** hain, to Graph DFS try karo.

---

# 5. Graph Representation

## Adjacency List

```java
List<List<Integer>> adj = new ArrayList<>();

for (int i = 0; i < n; i++) {
    adj.add(new ArrayList<>());
}

adj.get(u).add(v);
adj.get(v).add(u);
```

Example:

```text
Graph:
0 - 1
0 - 2
1 - 3

adj:
0 ->[1][2]
1 ->[0][3]
2 -> 
3 -> 
```

## Adjacency Matrix

```java
int[][] graph = new int[n][n];

graph[u][v] = 1;
graph[v][u] = 1;
```

## Grid as Graph

```text
Grid:
1 1 0
1 0 0
0 0 1
```

Har cell ek node hai.

Neighbors:

```text
Up, Down, Left, Right
```

---

# 6. DFS Template — Adjacency List

```java
public void dfs(int node,
                List<List<Integer>> adj,
                boolean[] visited) {

    visited[node] = true;

    for (int neighbor : adj.get(node)) {
        if (!visited[neighbor]) {
            dfs(neighbor, adj, visited);
        }
    }
}
```

### Important

Visited mark karna recursion call se pehle ya turant baad karna chahiye.

Warna same node multiple baar visit ho sakta hai.

---

# 7. Example 1: Number of Islands

## Problem

Grid mein `1` land hai aur `0` water hai.

Connected land areas = islands.

Number of islands return karo.

```text
grid = [
  ["1", "1", "0", "0", "0"],
  ["1", "1", "0", "0", "0"],
  ["0", "0", "1", "0", "0"],
  ["0", "0", "0", "1", "1"]
]

Output: 3
```

## Why DFS?

Har `1` se DFS chala kar poora connected land mark kar do.

Har naya unvisited `1` ek naya island hai.

## Solution

```java
public int numIslands(char[][] grid) {
    int rows = grid.length;
    int cols = grid.length;

    int count = 0;

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (grid[i][j] == '1') {
                count++;
                dfs(grid, i, j);
            }
        }
    }

    return count;
}

private void dfs(char[][] grid, int row, int col) {
    if (row < 0 || row >= grid.length ||
        col < 0 || col >= grid.length ||
        grid[row][col] != '1') {

        return;
    }

    grid[row][col] = '0';

    dfs(grid, row + 1, col);
    dfs(grid, row - 1, col);
    dfs(grid, row, col + 1);
    dfs(grid, row, col - 1);
}
```

## Dry Run

```text
grid = [
 ,[0][1]
 ,[1][0]
[0][1]
]

Start at (0,0):
DFS marks:
(0,0), (0,1), (1,0)

Island 1 counted

Start at (2,2):
DFS marks:
(2,2)

Island 2 counted

Answer = 2
```

## Complexity

- Time: \(O(rows \times cols)\)
- Space: \(O(rows \times cols)\)

---

# 8. Example 2: Flood Fill

## Problem

Starting pixel se connected same-color pixels ko new color se fill karo.

```text
image = [
 ,[1]
 ,[0][1]
[0][1]
]

sr = 1
sc = 1
newColor = 2

Output:
[
 ,[2]
 ,[0][2]
[1][0][2]
]
```

## Why DFS?

Starting pixel se connected same-color region traverse karna hai.

## Solution

```java
public int[][] floodFill(int[][] image, int sr, int sc, int color) {
    int originalColor = image[sr][sc];

    if (originalColor != color) {
        dfs(image, sr, sc, originalColor, color);
    }

    return image;
}

private void dfs(int[][] image, int row, int col, int originalColor, int color) {
    if (row < 0 || row >= image.length ||
        col < 0 || col >= image.length ||
        image[row][col] != originalColor) {

        return;
    }

    image[row][col] = color;

    dfs(image, row + 1, col, originalColor, color);
    dfs(image, row - 1, col, originalColor, color);
    dfs(image, row, col + 1, originalColor, color);
    dfs(image, row, col - 1, originalColor, color);
}
```

## Dry Run

```text
Start: (1,1)
Original color = 1
New color = 2

DFS:
(1,1) -> (0,1) -> (0,0) -> (1,0) -> (2,0)

All connected 1s become 2.

Final:
[
 ,[2]
 ,[0][2]
[0][1][2]
]
```

## Complexity

- Time: \(O(rows \times cols)\)
- Space: \(O(rows \times cols)\)

---

# 9. Example 3: Number of Provinces

## Problem

`n` cities hain.

`isConnected[i][j] = 1` matlab city `i` aur city `j` directly connected hain.

Number of provinces / connected components return karo.

```text
isConnected = [
 ,[0][1]
 ,[1][0]
[0][1]
]

Output: 2
```

## Why DFS?

Har unvisited city se DFS chalao.

Ek DFS call ek province cover karega.

## Solution

```java
public int findCircleNum(int[][] isConnected) {
    int n = isConnected.length;

    boolean[] visited = new boolean[n];

    int provinces = 0;

    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            provinces++;
            dfs(isConnected, i, visited);
        }
    }

    return provinces;
}

private void dfs(int[][] isConnected, int node, boolean[] visited) {
    visited[node] = true;

    for (int neighbor = 0; neighbor < isConnected.length; neighbor++) {
        if (isConnected[node][neighbor] == 1 && !visited[neighbor]) {
            dfs(isConnected, neighbor, visited);
        }
    }
}
```

## Dry Run

```text
Cities:
0 - 1
2 separate

DFS from 0:
Visits 0, 1

Province 1

DFS from 2:
Visits 2

Province 2

Answer = 2
```

## Complexity

- Time: \(O(n^2)\)
- Space: \(O(n)\)

---

# 10. Example 4: Cycle Detection in Undirected Graph

## Problem

Undirected graph mein cycle hai ya nahi check karo.

```text
Graph:
0 - 1
1 - 2
2 - 0

Cycle exists
```

## Why DFS + Parent?

DFS traversal mein agar kisi visited neighbor par pahunchte hain, but wo parent nahi hai, to cycle hai.

## Solution

```java
public boolean hasCycle(int n, List<List<Integer>> adj) {
    boolean[] visited = new boolean[n];

    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            if (dfs(i, -1, adj, visited)) {
                return true;
            }
        }
    }

    return false;
}

private boolean dfs(int node,
                    int parent,
                    List<List<Integer>> adj,
                    boolean[] visited) {

    visited[node] = true;

    for (int neighbor : adj.get(node)) {
        if (!visited[neighbor]) {
            if (dfs(neighbor, node, adj, visited)) {
                return true;
            }
        } else if (neighbor != parent) {
            return true;
        }
    }

    return false;
}
```

## Dry Run

```text
Graph:
0 - 1
1 - 2
2 - 0

DFS from 0:
parent = -1

Visit 0
Visit 1 from 0
Visit 2 from 1

2 ka neighbor 0 visited hai
0 != parent 1

Cycle found
```

## Complexity

- Time: \(O(V + E)\)
- Space: \(O(V)\)

---

# 11. Example 5: Cycle Detection in Directed Graph

## Problem

Directed graph mein cycle hai ya nahi check karo.

```text
Graph:
0 -> 1
1 -> 2
2 -> 0

Cycle exists
```

## Why Three States?

```text
0 = Unvisited
1 = Visiting
2 = Visited
```

Agar DFS ke dauran kisi `Visiting` node par wapas aaye, to cycle hai.

## Solution

```java
public boolean hasCycle(int n, List<List<Integer>> adj) {
    int[] state = new int[n];

    for (int i = 0; i < n; i++) {
        if (state[i] == 0) {
            if (dfs(i, adj, state)) {
                return true;
            }
        }
    }

    return false;
}

private boolean dfs(int node, List<List<Integer>> adj, int[] state) {
    state[node] = 1;

    for (int neighbor : adj.get(node)) {
        if (state[neighbor] == 1) {
            return true;
        }

        if (state[neighbor] == 0 && dfs(neighbor, adj, state)) {
            return true;
        }
    }

    state[node] = 2;

    return false;
}
```

## Dry Run

```text
0 -> 1
1 -> 2
2 -> 0

DFS 0:
state = 1

DFS 1:
state = 1

DFS 2:
state = 1

Neighbor 0:
state == 1

Cycle found
```

## Complexity

- Time: \(O(V + E)\)
- Space: \(O(V)\)

---

# 12. Example 6: Path Existence

## Problem

Source se destination tak path exist karta hai ya nahi check karo.

```text
n = 4
edges = [
 ,[0][1]
 ,[1][2]
[2][3]
]

source = 0
destination = 3

Output: true
```

## Solution

```java
public boolean validPath(int n, int[][] edges, int source, int destination) {
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

    return dfs(source, destination, adj, visited);
}

private boolean dfs(int node,
                    int destination,
                    List<List<Integer>> adj,
                    boolean[] visited) {

    if (node == destination) {
        return true;
    }

    visited[node] = true;

    for (int neighbor : adj.get(node)) {
        if (!visited[neighbor]) {
            if (dfs(neighbor, destination, adj, visited)) {
                return true;
            }
        }
    }

    return false;
}
```

## Complexity

- Time: \(O(V + E)\)
- Space: \(O(V + E)\)

---

# 13. DFS vs BFS

| Feature | DFS | BFS |
|---|---|---|
| Data structure | Recursion / Stack | Queue |
| Traversal | Depth-wise | Level-wise |
| Shortest path unweighted | No | Yes |
| Connected components | Yes | Yes |
| Cycle detection | Yes | Possible |
| Memory | Usually less | Usually more |
| Example | Number of Islands | Rotting Oranges |

### Rule

```text
Connected components / cycle / path existence -> DFS
Shortest path / nearest source -> BFS
```

---

# 14. Common Mistakes

## Mistake 1: Visited Mark Na Karna

```java
visited[node] = true;
```

Ye zaroori hai, warna infinite recursion ho sakti hai.

---

## Mistake 2: Undirected Graph Mein Parent Check Bhoolna

```java
else if (neighbor != parent) {
    return true;
}
```

Ye cycle detection ke liye zaroori hai.

---

## Mistake 3: Grid Bounds Check Bhoolna

```java
if (row < 0 || row >= rows ||
    col < 0 || col >= cols)
```

---

## Mistake 4: Directed Graph Mein Parent Logic Use Karna

Directed graph mein parent check kaafi nahi hota.

Three-state DFS use karo:

```text
0 = Unvisited
1 = Visiting
2 = Visited
```

---

## Mistake 5: Original Grid Modify Na Karna Jab Required Ho

Kabhi-kabhi original grid preserve karna hota hai.

Us case mein separate visited array use karo.

---

## Mistake 6: Recursion Depth Ignore Karna

Bade grid/graph mein stack overflow ho sakta hai.

Iterative DFS stack use kar sakte ho.

---

# 15. Graph DFS Decision Flow

```text
Problem padho
      |
      v
Kya graph/grid/network hai?
      |
      +-- No --> Graph DFS probably nahi
      |
      +-- Yes
          |
          v
Kya connected components chahiye?
      |
      +-- Yes --> DFS from unvisited nodes
      |
      +-- Kya cycle detect karna hai?
              |
              +-- Undirected -> DFS + Parent
              |
              +-- Directed -> DFS + 3 States
              |
              +-- Kya path existence chahiye?
                      |
                      +-- Yes --> DFS from source
                      |
                      +-- Kya region fill karna hai?
                              |
                              +-- Yes --> Flood Fill DFS
```

---

# 16. Important Problems

## Easy

- Flood Fill
- Find if Path Exists in Graph
- Number of Provinces
- Find Center of Star Graph

## Medium

- Number of Islands
- Max Area of Island
- Surrounded Regions
- Pacific Atlantic Water Flow
- Course Schedule
- Course Schedule II
- Clone Graph
- Graph Valid Tree

## Hard

- Word Search II
- Longest Increasing Path in a Matrix
- Reconstruct Itinerary
- Critical Connections in a Network

---

# 17. One-Line Recognition

> **Agar problem connected components, islands, regions, flood fill, cycle detection, ya reachability maang rahi hai — Graph DFS try karo.**

Graph DFS ka main power ye hai ki ek node se start karke poora connected region explore kar leta hai, aur visited marking se infinite loop avoid hota hai.