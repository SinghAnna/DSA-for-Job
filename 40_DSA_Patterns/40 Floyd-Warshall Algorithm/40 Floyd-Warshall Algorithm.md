# 🚀 DSA Pattern 40 — Floyd-Warshall Algorithm

> **Goal:** Floyd-Warshall ko product-based company interview level par samajhna — real-world intuition, kyun use hota hai, kaise identify karein, all-pairs shortest path, negative weights, negative cycle detection, DP transition, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Floyd-Warshall Kya Hai?

Floyd-Warshall ek **All-Pairs Shortest Path** algorithm hai.

Matlab ek hi run mein:

```text
Har node se har node tak shortest distance
```

Example:

```text
Graph:
A -> B, weight 4
A -> C, weight 2
C -> B, weight 1
B -> D, weight 5
C -> D, weight 8
```

Floyd-Warshall ke baad:

```text
A -> B = 3
A -> C = 2
A -> D = 8
B -> D = 5
C -> B = 1
C -> D = 6
```

### Important

Floyd-Warshall:

```text
Negative edge weights allow karta hai
Negative cycle allow nahi karta
```

Agar negative cycle hai, to shortest distance undefined ho jati hai.

---

## 2. Floyd-Warshall Kyun Use Karte Hain?

Main reasons:

1. **All-pairs shortest path chahiye**
2. **Graph chhota hai**, usually `n <= 500`
3. **Negative edge weights hain**, but negative cycle nahi
4. **Multiple source-destination queries hain**
5. **Transitive closure / reachability nikalna hai**
6. **Dense graph hai**

### Dijkstra vs Floyd-Warshall

| Feature | Dijkstra | Floyd-Warshall |
|---|---|---|
| Path type | Single source | All pairs |
| Negative weights | Not allowed | Allowed |
| Negative cycle | Not handled | Detectable |
| Time | \(O((V + E) \log V)\) | \(O(V^3)\) |
| Space | \(O(V + E)\) | \(O(V^2)\) |
| Best for | Large sparse graph | Small dense graph |

### Core Intuition

> Har node `k` ko intermediate node maan kar check karo:
>
> Kya `i -> k -> j` ka path, direct `i -> j` se better hai?

Formula:

```text
dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])
```

---

## 3. Real-World Production Examples

| Real System | Floyd-Warshall Use |
|---|---|
| Google Maps | Small region all-pairs route analysis |
| Airline System | Airport-to-airport shortest routes |
| Railway System | Station-to-station minimum time |
| Logistics | Warehouse-to-city route matrix |
| Telecom Network | Node-to-node latency matrix |
| Flight Booking | Cheapest flight route matrix |
| Delivery Network | Hub-to-hub distance matrix |
| Social Network | Connection reachability |
| Database | Dependency reachability |
| Game Development | Map node shortest paths |
| Manufacturing | Machine routing matrix |
| Network Monitoring | Latency matrix |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Tumhare delivery network mein 100 hubs hain. Har hub se har hub tak minimum delivery time chahiye. Kaise nikaloge?”

Answer:

```text
Hub graph banao.
Edges = routes with travel time.

Agar n chhota hai, around 100-500:
Floyd-Warshall use karo.

Ek baar O(n^3) run karke
poori distance matrix precompute karo.

Phir har query O(1) mein answer hoga.
```

Ya:

> “Flight booking system mein har airport pair ke liye cheapest route chahiye. Kaise approach karoge?”

Answer:

```text
Airports nodes hain.
Flights weighted edges hain.

Floyd-Warshall se all-pairs minimum cost matrix banao.

Negative prices allowed hain,
but negative cycle detect karna zaroori hai.
```

---

## 4. Kaise Pata Chalega Ki Floyd-Warshall Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Floyd-Warshall |
|---|---|
| All pairs shortest path | Floyd-Warshall |
| Har node se har node distance | Floyd-Warshall |
| Small graph, dense edges | Floyd-Warshall |
| Negative edge weights | Floyd-Warshall |
| Multiple shortest path queries | Floyd-Warshall |
| Reachability between all pairs | Floyd-Warshall |
| Transitive closure | Floyd-Warshall |

### Common Keywords

- All pairs
- Every pair
- Shortest distance matrix
- Minimum distance between all nodes
- Reachability
- Transitive closure
- Flight route matrix
- Network latency matrix

### Mental Checklist

1. Kya all-pairs shortest path chahiye?  
2. Kya graph chhota hai?  
3. Kya negative weights hain?  
4. Kya multiple queries hain?  
5. Kya \(O(n^3)\) acceptable hai?  

Agar 2–3 answers **yes** hain, to Floyd-Warshall try karo.

---

# 5. Floyd-Warshall Ka Core Idea

Three nested loops:

```text
for k:
    for i:
        for j:
            dist[i][j] =
                min(dist[i][j], dist[i][k] + dist[k][j])
```

### Meaning

```text
k = intermediate node
i = source
j = destination
```

Check karo:

```text
Kya i se k jaakar,
phir k se j jaana better hai?
```

Example:

```text
A -> B = 10
A -> C = 2
C -> B = 1

Direct A -> B = 10

Via C:
A -> C -> B = 2 + 1 = 3

Better:
dist[A][B] = 3
```

---

# 6. Initial Distance Matrix

```text
dist[i][i] = 0
dist[i][j] = edge weight, if edge exists
dist[i][j] = infinity, if no direct edge
```

Example:

```text
Graph:
0 -> 1, weight 4
0 -> 2, weight 2
2 -> 1, weight 1
1 -> 3, weight 5
2 -> 3, weight 8

Initial:
      0    1    2    3
0     0    4    2    INF
1     INF  0    INF  5
2     INF  1    0    8
3     INF  INF  INF  0
```

---

# 7. Floyd-Warshall Template

```java
public int[][] floydWarshall(int n, int[][] edges) {
    int[][] dist = new int[n][n];

    for (int i = 0; i < n; i++) {
        Arrays.fill(dist[i], Integer.MAX_VALUE / 2);

        dist[i][i] = 0;
    }

    for (int[] edge : edges) {
        int u = edge;
        int v = edge;
        int w = edge;

        dist[u][v] = Math.min(dist[u][v], w);
    }

    for (int k = 0; k < n; k++) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][k] + dist[k][j] < dist[i][j]) {
                    dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
    }

    return dist;
}
```

### Important

`Integer.MAX_VALUE / 2` use karo, not `Integer.MAX_VALUE`.

Kyunki:

```text
dist[i][k] + dist[k][j]
```

Overflow ho sakta hai.

---

# 8. Example 1: All-Pairs Shortest Path

## Problem

Directed weighted graph diya hai.

Har node se har node tak shortest distance nikalo.

```text
n = 4
edges = [
 ,[0][1][4]
 ,[2][0]
 ,[1][2]
 ,[3][5][1]
[8][2][3]
]
```

## Why Floyd-Warshall?

All-pairs shortest path chahiye.

## Solution

```java
public int[][] shortestDistance(int n, int[][] edges) {
    int[][] dist = new int[n][n];

    for (int i = 0; i < n; i++) {
        Arrays.fill(dist[i], Integer.MAX_VALUE / 2);

        dist[i][i] = 0;
    }

    for (int[] edge : edges) {
        int u = edge;
        int v = edge;
        int w = edge;

        dist[u][v] = Math.min(dist[u][v], w);
    }

    for (int k = 0; k < n; k++) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][k] + dist[k][j] < dist[i][j]) {
                    dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
    }

    return dist;
}
```

## Dry Run

```text
Initial:
      0    1    2    3
0     0    4    2    INF
1     INF  0    INF  5
2     INF  1    0    8
3     INF  INF  INF  0

k = 0:
No improvement because 0 has no incoming useful path.

k = 1:
dist already 5.

k = 2:
dist:
min(4, dist + dist)
= min(4, 2 + 1)
= 3

dist:
min(8, dist + dist)
= min(8, 2 + 8)
= 8

dist:
min(8, dist + dist)
= min(8, 1 + 5)
= 6

k = 3:
No further improvement.

Final:
      0    1    2    3
0     0    3    2    8
1     INF  0    INF  5
2     INF  1    0    6
3     INF  INF  INF  0
```

## Complexity

- Time: \(O(n^3)\)
- Space: \(O(n^2)\)

---

# 9. Example 2: Negative Cycle Detection

## Problem

Check karo ki graph mein negative cycle hai ya nahi.

```text
edges = [
 ,[0][1]
  [1, 2, -1],
  [2, 0, -1]
]
```

Total cycle weight:

```text
1 + (-1) + (-1) = -1
```

Negative cycle exists.

## Rule

Floyd-Warshall ke baad:

```text
Agar dist[i][i] < 0
```

To node `i` negative cycle ka part hai.

## Solution

```java
public boolean hasNegativeCycle(int n, int[][] edges) {
    int[][] dist = new int[n][n];

    for (int i = 0; i < n; i++) {
        Arrays.fill(dist[i], Integer.MAX_VALUE / 2);

        dist[i][i] = 0;
    }

    for (int[] edge : edges) {
        int u = edge;
        int v = edge;
        int w = edge;

        dist[u][v] = Math.min(dist[u][v], w);
    }

    for (int k = 0; k < n; k++) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][k] + dist[k][j] < dist[i][j]) {
                    dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
    }

    for (int i = 0; i < n; i++) {
        if (dist[i][i] < 0) {
            return true;
        }
    }

    return false;
}
```

## Dry Run

```text
Cycle:
0 -> 1 -> 2 -> 0

Weights:
1 + (-1) + (-1) = -1

After Floyd-Warshall:
dist < 0

Negative cycle exists
```

## Complexity

- Time: \(O(n^3)\)
- Space: \(O(n^2)\)

---

# 10. Example 3: Transitive Closure / Reachability

## Problem

Har node se har node tak reachable hai ya nahi, ye matrix nikalo.

```text
Graph:
0 -> 1
1 -> 2
```

Reachability:

```text
0 can reach 0, 1, 2
1 can reach 1, 2
2 can reach 2
```

## Solution

```java
public boolean[][] transitiveClosure(int n, int[][] edges) {
    boolean[][] reach = new boolean[n][n];

    for (int i = 0; i < n; i++) {
        reach[i][i] = true;
    }

    for (int[] edge : edges) {
        reach[edge][edge] = true;
    }

    for (int k = 0; k < n; k++) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (reach[i][k] && reach[k][j]) {
                    reach[i][j] = true;
                }
            }
        }
    }

    return reach;
}
```

## Dry Run

```text
Edges:
0 -> 1
1 -> 2

Initial:
0 -> 1 true
1 -> 2 true

k = 1:
0 -> 1 and 1 -> 2
So 0 -> 2 true

Final:
0 can reach 0, 1, 2
1 can reach 1, 2
2 can reach 2
```

## Complexity

- Time: \(O(n^3)\)
- Space: \(O(n^2)\)

---

# 11. Example 4: Find the City With Smallest Number of Neighbors

## Problem

Cities aur weighted edges diye hain.

Har city ke liye `distanceThreshold` ke andar kitne cities reachable hain.

Aisi city dhundo jiske paas sabse kam reachable cities hon.

```text
n = 4
edges = [
 ,[0][1][3]
 ,[1][2]
 ,[3][4][1]
[2][1][3]
]

distanceThreshold = 4

Output: 3
```

## Why Floyd-Warshall?

Har city se har city distance chahiye.

## Solution

```java
public int findTheCity(int n, int[][] edges, int distanceThreshold) {
    int[][] dist = new int[n][n];

    for (int i = 0; i < n; i++) {
        Arrays.fill(dist[i], Integer.MAX_VALUE / 2);

        dist[i][i] = 0;
    }

    for (int[] edge : edges) {
        int u = edge;
        int v = edge;
        int w = edge;

        dist[u][v] = w;
        dist[v][u] = w;
    }

    for (int k = 0; k < n; k++) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][k] + dist[k][j] < dist[i][j]) {
                    dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
    }

    int answerCity = -1;
    int minCount = Integer.MAX_VALUE;

    for (int i = 0; i < n; i++) {
        int count = 0;

        for (int j = 0; j < n; j++) {
            if (i != j && dist[i][j] <= distanceThreshold) {
                count++;
            }
        }

        if (count <= minCount) {
            minCount = count;
            answerCity = i;
        }
    }

    return answerCity;
}
```

## Complexity

- Time: \(O(n^3)\)
- Space: \(O(n^2)\)

---

# 12. Floyd-Warshall vs Dijkstra

| Feature | Floyd-Warshall | Dijkstra |
|---|---|---|
| Path type | All pairs | Single source |
| Negative weights | Allowed | Not allowed |
| Negative cycle | Detectable | Not handled |
| Time | \(O(n^3)\) | \(O((V + E) \log V)\) |
| Space | \(O(n^2)\) | \(O(V + E)\) |
| Best graph | Small dense graph | Large sparse graph |
| Queries | Many source-destination queries | One source |

### Rule

```text
Single source -> Dijkstra
All pairs + small graph -> Floyd-Warshall
Negative weights -> Floyd-Warshall / Bellman-Ford
```

---

# 13. Floyd-Warshall vs Bellman-Ford

| Feature | Floyd-Warshall | Bellman-Ford |
|---|---|---|
| Path type | All pairs | Single source |
| Negative weights | Allowed | Allowed |
| Negative cycle | Detectable | Detectable |
| Time | \(O(n^3)\) | \(O(V \times E)\) |
| Space | \(O(n^2)\) | \(O(V)\) |
| Best for | Dense small graph | Sparse graph |

---

# 14. Common Mistakes

## Mistake 1: Loop Order Galat Karna

Correct order:

```java
for k:
    for i:
        for j:
```

`k` bahar wala loop hona chahiye.

---

## Mistake 2: `Integer.MAX_VALUE` Direct Use Karna

```java
dist[i][k] + dist[k][j]
```

Overflow ho sakta hai.

Use:

```java
Integer.MAX_VALUE / 2
```

---

## Mistake 3: Self Distance Bhoolna

```java
dist[i][i] = 0;
```

Ye zaroori hai.

---

## Mistake 4: Negative Cycle Check Bhoolna

```java
if (dist[i][i] < 0)
```

---

## Mistake 5: Undirected Graph Mein Sirf Ek Direction Add Karna

Undirected edge:

```java
dist[u][v] = w;
dist[v][u] = w;
```

---

## Mistake 6: Large Graph Par Floyd-Warshall Use Karna

```text
n = 100000
```

To \(O(n^3)\) impossible hai.

Large graph ke liye:

```text
Dijkstra
Bellman-Ford
Johnson’s Algorithm
```

---

# 15. Floyd-Warshall Decision Flow

```text
Problem padho
      |
      v
Kya all-pairs shortest path chahiye?
      |
      +-- No --> Dijkstra / BFS
      |
      +-- Yes
          |
          v
Kya graph chhota hai?
      |
      +-- No --> Dijkstra from every node / Johnson’s
      |
      +-- Yes
          |
          v
Kya negative weights hain?
      |
      +-- Yes --> Floyd-Warshall
      |
      +-- No
          |
          v
Kya dense graph hai?
      |
      +-- Yes --> Floyd-Warshall
      |
      +-- Kya reachability chahiye?
              |
              +-- Yes --> Floyd-Warshall / Transitive Closure
```

---

# 16. Important Problems

## Medium

- Find the City With the Smallest Number of Neighbors
- All Pairs Shortest Path
- Network Delay Time Matrix
- Course Schedule Reachability

## Hard

- Find the City With the Smallest Number of Neighbors
- All Pairs Shortest Path
- Minimum Cost to Connect Two Groups of Points
- Shortest Path Visiting All Nodes

---

# 17. One-Line Recognition

> **Agar problem har node se har node tak shortest distance, reachability matrix, ya negative weights wale small graph maang rahi hai — Floyd-Warshall try karo.**

Floyd-Warshall ka main power ye hai ki ek hi \(O(n^3)\) run mein poori graph ki shortest-distance matrix precompute kar deta hai, jisse baad ki saari source-destination queries \(O(1)\) ho jati hain.