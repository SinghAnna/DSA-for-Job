# 🚀 DSA Pattern 54 — Bellman-Ford Algorithm

> **Goal:** Bellman-Ford Algorithm ko product-based company interview level par samajhna — real-world intuition, kyun Bellman-Ford use hota hai, kaise identify karein, negative weights, negative cycle detection, relaxation, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Bellman-Ford Kya Hai?

Bellman-Ford ek single-source shortest path algorithm hai jo:

```text
1. Negative weights handle karta hai
2. Negative cycle detect karta hai
3. Directed / undirected graph par kaam karta hai
```

### Simple Example

```text
Graph:
A -> B, weight 4
A -> C, weight 2
B -> C, weight -3
C -> D, weight 2

Shortest distance from A:
A = 0
C = 2
B = 4
D = 4
```

### Core Intuition

> Har edge ko repeatedly relax karo, jab tak shortest distance stable na ho jaye.

Example:

```text
Relaxation:
if dist[u] + weight < dist[v]:
    dist[v] = dist[u] + weight
```

---

## 2. Bellman-Ford Kyun Use Karte Hain?

Main reasons:

1. Negative weights
2. Negative cycle detection
3. Currency arbitrage
4. Network routing
5. Flight pricing
6. Delivery cost optimization
7. Graph with negative edges

### Core Intuition

> Dijkstra negative weights handle nahi karta, lekin Bellman-Ford karta hai.

Example:

```text
Dijkstra:
Negative weight -> fail

Bellman-Ford:
Negative weight -> works
```

---

## 3. Real-World Production Examples

| Real System | Bellman-Ford Use |
|---|---|
| Currency Exchange | Arbitrage detection |
| Flight Pricing | Negative discount handling |
| Network Routing | Distance vector routing |
| Delivery System | Negative cost handling |
| Ride Sharing | Promo / discount handling |
| Finance | Arbitrage detection |
| Logistics | Cost optimization |
| Telecom | Network routing |
| Cloud Cost Optimizer | Negative cost handling |
| Recommendation System | Score optimization |
| Game Development | Pathfinding with negative cost |
| Supply Chain | Cost optimization |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Currency exchange rates mein arbitrage opportunity detect karni hai. Kaise karoge?”

Answer:

```text
Currencies = nodes
Exchange rates = edge weights

Bellman-Ford se negative cycle detect karo.

Negative cycle = arbitrage opportunity.
```

Ya:

> “Flight pricing system mein discounts aur surcharges handle karne hain. Kaise approach karoge?”

Answer:

```text
Airports = nodes
Flights = edges
Cost = weight

Bellman-Ford se shortest cost nikal sakte hain.
```

---

## 4. Kaise Pata Chalega Ki Bellman-Ford Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Bellman-Ford |
|---|---|
| Negative weights | Bellman-Ford |
| Negative cycle | Bellman-Ford |
| Single source shortest path | Bellman-Ford |
| Currency arbitrage | Bellman-Ford |
| Network routing | Bellman-Ford |
| Flight pricing | Bellman-Ford |

### Common Keywords

- Negative weight
- Negative cycle
- Shortest path
- Arbitrage
- Currency
- Flight
- Network
- Routing

### Mental Checklist

1. Kya graph hai?  
2. Kya negative weights hain?  
3. Kya negative cycle detect karna hai?  
4. Kya single source shortest path chahiye?  
5. Kya Dijkstra fail hoga?  

Agar 2–3 answers **yes** hain, to Bellman-Ford try karo.

---

# 5. Bellman-Ford Problem Solve Karne Ke 5 Steps

```text
1. Distance array initialize karo
2. Source distance 0 karo
3. V - 1 times relax karo
4. Negative cycle check karo
5. Final answer nikalo
```

Example:

```text
dist[source] = 0
dist[other] = infinity

for i in 1 to V - 1:
    for each edge (u, v, w):
        if dist[u] + w < dist[v]:
            dist[v] = dist[u] + w
```

---

# 6. Bellman-Ford Template

```java
public int[] bellmanFord(
    int n,
    int[][] edges,
    int source
) {

    int[] dist = new int[n];

    Arrays.fill(dist, Integer.MAX_VALUE);

    dist[source] = 0;

    for (int i = 1; i < n; i++) {

        for (int[] edge : edges) {

            int u = edge;

            int v = edge;

            int w = edge;

            if (dist[u] != Integer.MAX_VALUE &&
                dist[u] + w < dist[v]) {

                dist[v] = dist[u] + w;
            }
        }
    }

    for (int[] edge : edges) {

        int u = edge;

        int v = edge;

        int w = edge;

        if (dist[u] != Integer.MAX_VALUE &&
            dist[u] + w < dist[v]) {

            return null; // Negative cycle
        }
    }

    return dist;
}
```

---

# 7. Example 1: Network Delay Time — Bellman-Ford

## Problem

Network delays diye hain.

Source se sabhi nodes tak minimum time nikalo.

```text
times = [
 ,[1][2]
 ,[2][3][1]
[3][4][1]
]
n = 4
k = 2

Output = 2
```

## Why Bellman-Ford?

Single source shortest path chahiye.

## Solution

```java
public int networkDelayTime(int[][] times, int n, int k) {

    int[] dist = bellmanFord(n, times, k - 1);

    if (dist == null) {
        return -1;
    }

    int answer = 0;

    for (int d : dist) {

        if (d == Integer.MAX_VALUE) {
            return -1;
        }

        answer = Math.max(answer, d);
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

dist:
2 = 0
1 = 1
3 = 1
4 = 2

Answer = 2
```

## Complexity

- Time: \(O(V \times E)\)
- Space: \(O(V)\)

---

# 8. Example 2: Cheapest Flights Within K Stops

## Problem

Flights diye hain.

Source se destination tak minimum cost nikalo with at most K stops.

```text
flights = [
 ,[0][1][100]
 ,[1][2][100]
[0][2][500]
]
src = 0
dst = 2
k = 1

Output = 200
```

## Why Bellman-Ford?

Limited stops ke saath shortest path chahiye.

## Solution

```java
public int findCheapestPrice(
    int n,
    int[][] flights,
    int src,
    int dst,
    int k
) {

    int[] dist = new int[n];

    Arrays.fill(dist, Integer.MAX_VALUE);

    dist[src] = 0;

    for (int i = 0; i <= k; i++) {

        int[] temp = Arrays.copyOf(dist, n);

        for (int[] flight : flights) {

            int u = flight;

            int v = flight;

            int w = flight;

            if (dist[u] != Integer.MAX_VALUE &&
                dist[u] + w < temp[v]) {

                temp[v] = dist[u] + w;
            }
        }

        dist = temp;
    }

    return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
}
```

## Dry Run

```text
flights:
0 -> 1, cost 100
1 -> 2, cost 100
0 -> 2, cost 500

src = 0
dst = 2
k = 1

Iteration 1:
dist = 100
dist = 500

Iteration 2:
dist = 200

Answer = 200
```

## Complexity

- Time: \(O(k \times E)\)
- Space: \(O(V)\)

---

# 9. Example 3: Currency Arbitrage Detection

## Problem

Currency exchange rates diye hain.

Arbitrage opportunity detect karo.

```text
rates = [
  [1.0, 2.0, 0.5],
  [0.5, 1.0, 0.25],
  [2.0, 4.0, 1.0]
]
```

## Why Bellman-Ford?

Negative cycle detect karna hai.

## Solution

```java
public boolean hasArbitrage(
    int n,
    double[][] rates
) {

    double[] dist = new double[n];

    Arrays.fill(dist, 0.0);

    for (int i = 0; i < n; i++) {

        for (int u = 0; u < n; u++) {

            for (int v = 0; v < n; v++) {

                double weight = -Math.log(rates[u][v]);

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }
    }

    for (int u = 0; u < n; u++) {

        for (int v = 0; v < n; v++) {

            double weight = -Math.log(rates[u][v]);

            if (dist[u] + weight < dist[v]) {
                return true;
            }
        }
    }

    return false;
}
```

## Dry Run

```text
rates:
1 -> 2 = 2.0
2 -> 3 = 4.0
3 -> 1 = 0.5

Cycle:
1 -> 2 -> 3 -> 1

Product = 2.0 * 4.0 * 0.5 = 4.0

Arbitrage possible.

Answer = true
```

## Complexity

- Time: \(O(V^3)\)
- Space: \(O(V)\)

---

# 10. Bellman-Ford vs Dijkstra

| Feature | Bellman-Ford | Dijkstra |
|---|---|---|
| Negative weights | Yes | No |
| Negative cycle detection | Yes | No |
| Time | O(V * E) | O(E log V) |
| Best for | Negative weights | Non-negative weights |
| Example | Currency arbitrage | Google Maps |

### Rule

```text
Negative weights -> Bellman-Ford
Non-negative weights -> Dijkstra
```

---

# 11. Bellman-Ford Patterns

| Pattern | Use |
|---|---|
| Single Source Shortest Path | Distance array |
| Negative Cycle Detection | Extra relaxation |
| Limited Stops | Limited iterations |
| Currency Arbitrage | Log transform |
| Network Routing | Distance vector |

---

# 12. Common Mistakes

## Mistake 1: Negative Cycle Check Bhoolna

```java
for (int[] edge : edges) {

    if (dist[u] + w < dist[v]) {
        return null;
    }
}
```

---

## Mistake 2: Infinity Check Bhoolna

```java
if (dist[u] != Integer.MAX_VALUE) {
    // relax
}
```

---

## Mistake 3: Iteration Count Galat Karna

```java
for (int i = 1; i < n; i++) {
    // relax
}
```

---

## Mistake 4: Limited Stops Mein Temp Array Bhoolna

```java
int[] temp = Arrays.copyOf(dist, n);
```

---

## Mistake 5: Currency Arbitrage Mein Log Transform Bhoolna

```java
double weight = -Math.log(rates[u][v]);
```

---

# 13. Bellman-Ford Decision Flow

```text
Problem padho
      |
      v
Kya negative weights hain?
      |
      +-- Yes --> Bellman-Ford
      |
      +-- Kya negative cycle detect karna hai?
              |
              +-- Yes --> Bellman-Ford
              |
              +-- Kya limited stops hain?
                      |
                      +-- Yes --> Bellman-Ford with limited iterations
                      |
                      +-- Kya non-negative weights hain?
                              |
                              +-- Yes --> Dijkstra
```

---

# 14. Important Problems

## Medium

- Network Delay Time
- Cheapest Flights Within K Stops
- Path With Maximum Minimum Value
- Find the City With the Smallest Number of Neighbors at a Threshold Distance

## Hard

- Currency Arbitrage Detection
- Critical Connections in a Network
- Minimum Cost to Connect All Points
- Optimize Water Distribution in a Village

---

# 15. One-Line Recognition

> **Agar problem negative weights, negative cycle, currency arbitrage, ya limited stops maang rahi hai — Bellman-Ford try karo.**

Bellman-Ford ka main power ye hai ki negative weights aur negative cycles ko handle karta hai — jisse real-world cost optimization problems efficiently solve hoti hain.