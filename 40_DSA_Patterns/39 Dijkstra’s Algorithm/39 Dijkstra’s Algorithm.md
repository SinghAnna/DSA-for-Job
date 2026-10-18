# 🚀 DSA Pattern 39 — Dijkstra’s Algorithm

> **Goal:** Dijkstra’s Algorithm ko product-based company interview level par samajhna — real-world intuition, kyun use hota hai, kaise identify karein, weighted shortest path, priority queue, relaxation, negative weights, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Dijkstra’s Algorithm Kya Hai?

Dijkstra’s Algorithm ek **single-source shortest path** algorithm hai.

Ye weighted graph mein source node se baaki sab nodes tak shortest distance nikalta hai.

Important condition:

```text
Edge weights must be non-negative
weight >= 0
```

Example:

```text
Graph:
A -> B, weight 4
A -> C, weight 2
C -> B, weight 1
B -> D, weight 5
C -> D, weight 8

Shortest distance from A:
A = 0
C = 2
B = 3
D = 8
```

Path to B:

```text
A -> C -> B
Cost = 2 + 1 = 3
```

---

## 2. Dijkstra Kyun Use Karte Hain?

Main reasons:

1. **Weighted graph shortest path**
2. **Minimum cost / time / distance**
3. **Network routing**
4. **Maps and navigation**
5. **Cheapest flight path**
6. **Delivery route optimization**
7. **Network delay calculation**

### Core Intuition

> Hamesha abhi tak ki sabse kam distance wale node ko process karo, aur uske neighbors ki distance improve karne ki try karo.

Example:

```text
Current distances:
A = 0
B = 10
C = 3

Sabse kam distance C = 3

C process karo.
C se B tak edge weight 1.

New B distance:
3 + 1 = 4

Purana B distance 10 tha.

Update:
B = 4
```

---

## 3. Real-World Production Examples

| Real System | Dijkstra Use |
|---|---|
| Google Maps | Shortest / fastest route |
| Uber / Ola | Driver-to-user fastest route |
| Swiggy / Zomato | Delivery route optimization |
| Flight Booking | Cheapest flight route |
| Network Routing | Minimum latency path |
| Telecom | Lowest cost routing |
| Logistics | Minimum fuel/distance route |
| Delivery System | Fastest delivery path |
| Game AI | Pathfinding with movement cost |
| Robot Navigation | Shortest obstacle-free path |
| CDN | Lowest latency server path |
| Railway System | Minimum travel time route |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Google Maps mein user ko fastest route kaise suggest karoge?”

Answer:

```text
Roads ko weighted graph maano.

Weight = travel time / distance / traffic cost

Dijkstra’s algorithm se source se destination tak minimum cost path nikalo.
```

Ya:

> “Flight booking app mein cheapest route kaise nikaloge?”

Answer:

```text
Airports nodes hain.
Flights weighted edges hain.
Weight = ticket price.

Dijkstra se source airport se destination airport tak minimum cost path nikalo.
```

---

## 4. Kaise Pata Chalega Ki Dijkstra Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Dijkstra |
|---|---|
| Weighted graph | Dijkstra |
| Shortest path | Dijkstra |
| Minimum cost | Dijkstra |
| Minimum time | Dijkstra |
| Cheapest route | Dijkstra |
| Network delay | Dijkstra |
| Non-negative weights | Dijkstra |
| Single source shortest path | Dijkstra |

### Common Keywords

- Shortest path
- Minimum cost
- Minimum time
- Cheapest
- Network delay
- Weighted graph
- Route
- Flight
- Distance

### Mental Checklist

1. Kya weighted graph hai?  
2. Kya edge weights non-negative hain?  
3. Kya shortest/cheapest path chahiye?  
4. Kya ek source se baaki nodes tak distance chahiye?  
5. Kya greedy + priority queue approach fit hai?  

Agar 2–3 answers **yes** hain, to Dijkstra try karo.

---

# 5. Dijkstra Ka Core Idea

Har node ke liye current best distance maintain karo.

```text
distance[node]
```

Initially:

```text
distance[source] = 0
distance[other nodes] = infinity
```

Priority Queue mein:

```text
(node, currentDistance)
```

Har step par:

```text
1. Sabse kam distance wala node pop karo
2. Uske neighbors check karo
3. Agar naya distance chhota ho, update karo
4. Neighbor ko queue mein daalo
```

Is process ko **relaxation** kehte hain.

---

# 6. Relaxation

```text
if distance[current] + edgeWeight < distance[neighbor]:
    distance[neighbor] = distance[current] + edgeWeight
```

Example:

```text
distance[A] = 0
A -> C weight = 2

distance[C] = infinity

New distance:
0 + 2 = 2

2 < infinity

Update:
distance[C] = 2
```

---

# 7. Dijkstra Template — Adjacency List + PriorityQueue

```java
public int[] dijkstra(int n,
                      List<List<int[]>> adj,
                      int source) {

    int[] distance = new int[n];

    Arrays.fill(distance, Integer.MAX_VALUE);

    distance[source] = 0;

    PriorityQueue<int[]> minHeap =
        new PriorityQueue<>((a, b) -> a - b);

    minHeap.offer(new int[]{source, 0});

    while (!minHeap.isEmpty()) {
        int[] current = minHeap.poll();

        int node = current;
        int currentDistance = current;

        if (currentDistance > distance[node]) {
            continue;
        }

        for (int[] neighbor : adj.get(node)) {
            int nextNode = neighbor;
            int weight = neighbor;

            int newDistance = currentDistance + weight;

            if (newDistance < distance[nextNode]) {
                distance[nextNode] = newDistance;

                minHeap.offer(new int[]{nextNode, newDistance});
            }
        }
    }

    return distance;
}
```

### Important Line

```java
if (currentDistance > distance[node]) {
    continue;
}
```

Ye stale/outdated entry skip karta hai.

---

# 8. Example 1: Network Delay Time

## Problem

`n` nodes hain.

`times[i] = [u, v, w]` matlab node `u` se node `v` tak signal `w` time mein pahunchta hai.

Source `k` se saare nodes tak signal pahuchne ka minimum time nikalo.

Agar kisi node tak signal nahi pahunchta, `-1` return karo.

```text
times = [
 ,[1][2]
 ,[2][3][1]
[3][4][1]
]

n = 4
k = 2

Output: 2
```

## Why Dijkstra?

Weighted graph hai.

Minimum time chahiye.

## Solution

```java
public int networkDelayTime(int[][] times, int n, int k) {
    List<List<int[]>> adj = new ArrayList<>();

    for (int i = 0; i <= n; i++) {
        adj.add(new ArrayList<>());
    }

    for (int[] time : times) {
        int u = time;
        int v = time;
        int w = time;

        adj.get(u).add(new int[]{v, w});
    }

    int[] distance = new int[n + 1];

    Arrays.fill(distance, Integer.MAX_VALUE);

    distance[k] = 0;

    PriorityQueue<int[]> minHeap =
        new PriorityQueue<>((a, b) -> a - b);

    minHeap.offer(new int[]{k, 0});

    while (!minHeap.isEmpty()) {
        int[] current = minHeap.poll();

        int node = current;
        int currentDistance = current;

        if (currentDistance > distance[node]) {
            continue;
        }

        for (int[] neighbor : adj.get(node)) {
            int nextNode = neighbor;
            int weight = neighbor;

            int newDistance = currentDistance + weight;

            if (newDistance < distance[nextNode]) {
                distance[nextNode] = newDistance;

                minHeap.offer(new int[]{nextNode, newDistance});
            }
        }
    }

    int answer = 0;

    for (int i = 1; i <= n; i++) {
        if (distance[i] == Integer.MAX_VALUE) {
            return -1;
        }

        answer = Math.max(answer, distance[i]);
    }

    return answer;
}
```

## Dry Run

```text
times:
2 -> 1, weight 1
2 -> 3, weight 1
3 -> 4, weight 1

Source = 2

Initial:
distance = 0
others = infinity

Heap:
[0][2]

Process 2:
distance = 1
distance = 1

Heap:
,[1][3]

Process 1:
No outgoing edges

Process 3:
distance = 2

Heap:
[2][4]

Process 4:
No outgoing edges

Distances:
1 = 1
2 = 0
3 = 1
4 = 2

Answer = max distance = 2
```

## Complexity

- Time: \(O((V + E) \log V)\)
- Space: \(O(V + E)\)

---

# 9. Example 2: Cheapest Flights Within K Stops

## Problem

Flights di hain:

```text
flights[i] = [from, to, price]
```

Source se destination tak maximum `k` stops ke andar cheapest price nikalo.

```text
n = 4
flights = [
 ,[0][1][100]
 ,[1][2][100]
 ,[2][100][0]
 ,[3][600][1]
[200][2][3]
]

src = 0
dst = 3
k = 1

Output: 700
Path: 0 -> 1 -> 3
```

## Why Normal Dijkstra Fails?

Stop constraint hai.

Kabhi-kabhi longer/cheaper path ko baad mein explore karna padta hai.

Isliye Bellman-Ford-style level-by-level relaxation better hai.

## Solution

```java
public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    int[] distance = new int[n];

    Arrays.fill(distance, Integer.MAX_VALUE);

    distance[src] = 0;

    for (int i = 0; i <= k; i++) {
        int[] temp = Arrays.copyOf(distance, n);

        for (int[] flight : flights) {
            int from = flight;
            int to = flight;
            int price = flight;

            if (distance[from] != Integer.MAX_VALUE &&
                distance[from] + price < temp[to]) {

                temp[to] = distance[from] + price;
            }
        }

        distance = temp;
    }

    return distance[dst] == Integer.MAX_VALUE ? -1 : distance[dst];
}
```

## Dry Run

```text
flights:
0 -> 1, 100
1 -> 3, 600
2 -> 3, 200

src = 0
dst = 3
k = 1

Initial:
distance = 0

Iteration 1:
0 -> 1:
distance = 100

1 -> 3:
distance = 700

Answer = 700
```

## Complexity

- Time: \(O(k \times E)\)
- Space: \(O(V)\)

---

# 10. Example 3: Path with Minimum Effort

## Problem

Grid mein har cell se adjacent cell jaane ka effort height difference hai.

Left-top se right-bottom tak minimum maximum effort path nikalo.

```text
heights = [
 ,[1][2]
 ,[2][3][8]
[3][5]
]

Output: 2
```

## Why Modified Dijkstra?

Path cost:

```text
Maximum edge effort
```

Normal Dijkstra sum minimize karta hai.

Yahan maximum edge effort minimize karna hai.

## Solution

```java
public int minimumEffortPath(int[][] heights) {
    int rows = heights.length;
    int cols = heights.length;

    int[][] effort = new int[rows][cols];

    for (int[] row : effort) {
        Arrays.fill(row, Integer.MAX_VALUE);
    }

    effort = 0;

    PriorityQueue<int[]> minHeap =
        new PriorityQueue<>((a, b) -> a - b);

    minHeap.offer(new int[]{0, 0, 0});

    int[][] directions = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    while (!minHeap.isEmpty()) {
        int[] current = minHeap.poll();

        int row = current;
        int col = current;
        int currentEffort = current;

        if (row == rows - 1 && col == cols - 1) {
            return currentEffort;
        }

        for (int[] dir : directions) {
            int newRow = row + dir;
            int newCol = col + dir;

            if (newRow >= 0 && newRow < rows &&
                newCol >= 0 && newCol < cols) {

                int nextEffort = Math.max(
                    currentEffort,
                    Math.abs(heights[newRow][newCol] - heights[row][col])
                );

                if (nextEffort < effort[newRow][newCol]) {
                    effort[newRow][newCol] = nextEffort;

                    minHeap.offer(new int[]{newRow, newCol, nextEffort});
                }
            }
        }
    }

    return 0;
}
```

## Complexity

- Time: \(O(rows \times cols \log(rows \times cols))\)
- Space: \(O(rows \times cols)\)

---

# 11. Example 4: Path with Maximum Probability

## Problem

Graph mein edges ki success probability di hai.

Source se destination tak maximum probability path nikalo.

```text
n = 3
edges = [
 ,[0][1]
 ,[1][2]
[2][0]
]

succProb = [0.5, 0.5, 0.2]

start = 0
end = 2

Output: 0.25
Path: 0 -> 1 -> 2
```

## Why Modified Dijkstra?

Normal Dijkstra minimum distance dhundhta hai.

Yahan maximum probability dhundhna hai.

Isliye **Max-Heap** use karenge.

## Solution

```java
public double maxProbability(int n,
                             int[][] edges,
                             double[] succProb,
                             int start,
                             int end) {

    List<List<int[]>> adj = new ArrayList<>();

    for (int i = 0; i < n; i++) {
        adj.add(new ArrayList<>());
    }

    for (int i = 0; i < edges.length; i++) {
        int u = edges[i];
        int v = edges[i];

        int probabilityIndex = i;

        adj.get(u).add(new int[]{v, probabilityIndex});
        adj.get(v).add(new int[]{u, probabilityIndex});
    }

    double[] probability = new double[n];

    probability[start] = 1.0;

    PriorityQueue<double[]> maxHeap =
        new PriorityQueue<>((a, b) -> Double.compare(b, a));

    maxHeap.offer(new double[]{start, 1.0});

    while (!maxHeap.isEmpty()) {
        double[] current = maxHeap.poll();

        int node = (int) current;
        double currentProbability = current;

        if (node == end) {
            return currentProbability;
        }

        if (currentProbability < probability[node]) {
            continue;
        }

        for (int[] neighbor : adj.get(node)) {
            int nextNode = neighbor;
            int edgeIndex = neighbor;

            double newProbability =
                currentProbability * succProb[edgeIndex];

            if (newProbability > probability[nextNode]) {
                probability[nextNode] = newProbability;

                maxHeap.offer(new double[]{nextNode, newProbability});
            }
        }
    }

    return 0.0;
}
```

## Complexity

- Time: \(O(E \log V)\)
- Space: \(O(V + E)\)

---

# 12. Dijkstra vs BFS

| Feature | BFS | Dijkstra |
|---|---|---|
| Graph type | Unweighted | Weighted |
| Edge weights | Same / 1 | Non-negative |
| Shortest path | Yes | Yes |
| Data structure | Queue | Priority Queue |
| Time | \(O(V + E)\) | \(O((V + E) \log V)\) |
| Example | Maze shortest path | Google Maps route |

### Rule

```text
Same edge cost -> BFS
Different non-negative edge costs -> Dijkstra
```

---

# 13. Dijkstra vs Bellman-Ford

| Feature | Dijkstra | Bellman-Ford |
|---|---|---|
| Negative weights | Not allowed | Allowed |
| Negative cycle detection | No | Yes |
| Time | \(O((V + E) \log V)\) | \(O(V \times E)\) |
| Best for | Fast shortest path | Negative weights |
| Example | Maps | Currency arbitrage |

### Rule

```text
Non-negative weights -> Dijkstra
Negative weights -> Bellman-Ford
```

---

# 14. Common Mistakes

## Mistake 1: Negative Weight Ke Saath Dijkstra Use Karna

Dijkstra negative weights par galat answer de sakta hai.

```text
Negative weights -> Bellman-Ford
```

---

## Mistake 2: Stale Heap Entry Skip Na Karna

```java
if (currentDistance > distance[node]) {
    continue;
}
```

Ye optimization zaroori hai.

---

## Mistake 3: Visited Array Galat Use Karna

Dijkstra mein strict visited array optional hai.

Distance-based stale check better hai:

```java
if (currentDistance > distance[node]) continue;
```

---

## Mistake 4: Priority Queue Comparator Ulta Karna

Min-Heap:

```java
(a, b) -> a - b
```

Max-Heap:

```java
(a, b) -> b - a
```

---

## Mistake 5: Integer Overflow

Bade weights mein:

```java
long distance
```

Use karo.

---

## Mistake 6: Undirected Graph Mein Sirf Ek Direction Add Karna

Undirected edge:

```java
adj.get(u).add(new int[]{v, w});
adj.get(v).add(new int[]{u, w});
```

---

# 15. Dijkstra Decision Flow

```text
Problem padho
      |
      v
Kya shortest/cheapest path chahiye?
      |
      +-- No --> Dijkstra probably nahi
      |
      +-- Yes
          |
          v
Kya weighted graph hai?
      |
      +-- No --> BFS
      |
      +-- Yes
          |
          v
Kya negative weights hain?
      |
      +-- Yes --> Bellman-Ford
      |
      +-- No
          |
          v
Kya normal sum-based shortest path hai?
      |
      +-- Yes --> Dijkstra
      |
      +-- Kya maximum edge minimize karna hai?
              |
              +-- Yes --> Modified Dijkstra
```

---

# 16. Important Problems

## Medium

- Network Delay Time
- Path with Minimum Effort
- Path with Maximum Probability
- Cheapest Flights Within K Stops
- Find the City With the Smallest Number of Neighbors
- Number of Ways to Arrive at Destination

## Hard

- Cheapest Flights Within K Stops
- Path with Maximum Probability
- Minimum Cost to Make at Least One Valid Path in a Grid
- Reachable Nodes in Subdivided Graph

---

# 17. One-Line Recognition

> **Agar problem weighted graph mein shortest, cheapest, fastest, ya minimum cost path maang rahi hai aur weights non-negative hain — Dijkstra try karo.**

Dijkstra ka main power ye hai ki priority queue se hamesha current best node process karta hai aur relaxation se distances improve karta hai, isliye weighted shortest path efficiently nikalta hai.