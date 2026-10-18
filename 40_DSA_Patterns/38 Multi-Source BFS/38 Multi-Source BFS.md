# 🚀 DSA Pattern 38 — Multi-Source BFS

> **Goal:** Multi-Source BFS ko product-based company interview level par samajhna — real-world intuition, kyun use hota hai, kaise identify karein, nearest distance, simultaneous spread, Rotting Oranges, 01 Matrix, Walls and Gates, fire spread, nearest driver, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Multi-Source BFS Kya Hai?

Normal BFS mein ek source se traversal start hota hai.

```text
Single Source BFS:
Source -> Level 1 -> Level 2 -> Level 3
```

Multi-Source BFS mein **multiple sources ek saath** queue mein daale jate hain.

```text
Sources: A, B, C

Queue initially:
[A, B, C]

Sab sources se simultaneous expansion hoti hai.
```

Example:

```text
Grid:
0 0 0
0 1 0
1 1 1

Zeros = sources
Ones = need nearest zero distance
```

Saare zeros se BFS start karo.

Har `1` ko nearest `0` ki distance milegi.

---

## 2. Multi-Source BFS Kyun Use Karte Hain?

Main reasons:

1. **Nearest source distance nikalna**
2. **Simultaneous spread simulate karna**
3. **Multiple starting points handle karna**
4. **Shortest distance from any source**
5. **Fire/water/infection spread modeling**
6. **Nearest driver/hospital/warehouse finding**

### Core Intuition

> Saare sources ko initially queue mein daalo. BFS level by level expand karega. Jaise hi kisi cell par pehli baar pahunchenge, wahi uska nearest source distance hoga.

Example:

```text
Grid:
0 0 0
0 1 0
1 1 1

Nearest zero distance:
0 0 0
0 1 0
1 2 1
```

---

## 3. Real-World Production Examples

| Real System | Multi-Source BFS Use |
|---|---|
| Uber / Ola | Nearest driver from multiple drivers |
| Swiggy / Zomato | Nearest restaurant / delivery partner |
| Google Maps | Nearest hospital / fuel station |
| Amazon Logistics | Nearest warehouse |
| Healthcare System | Nearest hospital / ambulance |
| Fire Safety System | Fire spread simulation |
| Water Supply System | Water source coverage |
| Telecom | Nearest tower coverage |
| Game Development | Nearest enemy / resource |
| Warehouse Robotics | Nearest charging station |
| Delivery Network | Nearest dark store |
| Emergency System | Nearest emergency service |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Uber mein user ke nearest driver ka ETA kaise nikaloge?”

Answer:

```text
Saare available drivers ko multi-source BFS queue mein daalo.

Road graph / grid par BFS expand karo.

User cell par pehli baar pahunchne wala driver nearest hoga.

Distance = ETA estimate.
```

Ya:

> “Swiggy mein user ke nearest dark store se delivery time kaise nikaloge?”

Answer:

```text
Saare dark stores sources hain.

Grid/road graph par multi-source BFS karo.

Har user location ka nearest store distance nikal jayega.
```

---

## 4. Kaise Pata Chalega Ki Multi-Source BFS Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Multi-Source BFS |
|---|---|
| Multiple starting points | Multi-Source BFS |
| Nearest source distance | Multi-Source BFS |
| Simultaneous spread | Multi-Source BFS |
| Minimum time to spread | Multi-Source BFS |
| Fire / water / infection spread | Multi-Source BFS |
| Nearest hospital / driver / warehouse | Multi-Source BFS |
| Distance from any zero/source | Multi-Source BFS |

### Common Keywords

- Nearest
- Minimum time
- Spread
- Simultaneously
- Multiple sources
- All sources
- Distance from any
- Infection
- Fire
- Rotting

### Mental Checklist

1. Kya multiple starting points hain?  
2. Kya har point se simultaneous expansion ho rahi hai?  
3. Kya nearest distance chahiye?  
4. Kya har move ka cost same hai?  
5. Kya grid/graph hai?  

Agar 2–3 answers **yes** hain, to Multi-Source BFS try karo.

---

# 5. Multi-Source BFS Ka Core Idea

### Step 1: Saare Sources Identify Karo

```text
Sources = [A, B, C]
```

### Step 2: Saare Sources Queue Mein Daalo

```java
for (int[] source : sources) {
    queue.offer(source);
}
```

### Step 3: Normal BFS Chalao

```text
Level 0: A, B, C
Level 1: Neighbors of A, B, C
Level 2: Next layer
```

### Core Rule

```text
Jaise hi kisi node par pehli baar pahunchte ho,
wahi uska nearest source distance hota hai.
```

---

# 6. Template — Multi-Source BFS on Grid

```java
public int[][] multiSourceBFS(int[][] grid) {
    int rows = grid.length;
    int cols = grid.length;

    int[][] distance = new int[rows][cols];

    Queue<int[]> queue = new LinkedList<>();

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (grid[i][j] == 0) {
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

### Important

`distance = -1` ka matlab:

```text
Cell abhi unvisited hai
```

---

# 7. Example 1: 01 Matrix — Nearest Zero Distance

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

## Dry Run

```text
mat = [
 ,[0]
 ,[1][0]
[1]
]

Initial sources:
(0,0), (0,1), (0,2), (1,0), (1,2)

distance:
0 0 0
0 -1 0
-1 -1 -1

BFS Level 1:
(1,1) -> distance 1
(2,0) -> distance 1
(2,2) -> distance 1

distance:
0 0 0
0 1 0
1 -1 1

BFS Level 2:
(2,1) -> distance 2

Final:
0 0 0
0 1 0
1 2 1
```

## Complexity

- Time: \(O(rows \times cols)\)
- Space: \(O(rows \times cols)\)

---

# 8. Example 2: Rotting Oranges

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
[0][1]
]

Output: 4
```

## Why Multi-Source BFS?

Multiple rotten oranges ek saath spread karte hain.

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

# 9. Example 3: Walls and Gates

## Problem

Grid mein:

```text
-1 = wall
0 = gate
INF = empty room
```

Har empty room ke liye nearest gate distance nikalo.

```text
rooms = [
  [INF, -1, 0, INF],
  [INF, INF, INF, -1],
  [INF, -1, INF, -1],
  [0, -1, INF, INF]
]

Output:
[
  [3, -1, 0, 1],
  [2, 2, 1, -1],
  [1, -1, 2, -1],
  [0, -1, 3, 4]
]
```

## Why Multi-Source BFS?

Saare gates sources hain.

Gates se BFS expand karo.

## Solution

```java
public void wallsAndGates(int[][] rooms) {
    int rows = rooms.length;
    int cols = rooms.length;

    Queue<int[]> queue = new LinkedList<>();

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (rooms[i][j] == 0) {
                queue.offer(new int[]{i, j});
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
                rooms[newRow][newCol] == Integer.MAX_VALUE) {

                rooms[newRow][newCol] =
                    rooms[cell][cell] + 1;

                queue.offer(new int[]{newRow, newCol});
            }
        }
    }
}
```

## Complexity

- Time: \(O(rows \times cols)\)
- Space: \(O(rows \times cols)\)

---

# 10. Example 4: Nearest Driver — Uber Style

## Problem

City grid mein drivers hain.

User location se nearest driver ka distance nikalo.

```text
Grid:
0 = empty road
1 = driver location
2 = user location
```

## Why Multi-Source BFS?

Saare drivers sources hain.

User tak pehli baar pahunchne wala driver nearest hai.

## Solution

```java
public int nearestDriver(int[][] grid) {
    int rows = grid.length;
    int cols = grid.length;

    Queue<int[]> queue = new LinkedList<>();

    boolean[][] visited = new boolean[rows][cols];

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (grid[i][j] == 1) {
                queue.offer(new int[]{i, j, 0});
                visited[i][j] = true;
            }
        }
    }

    int[][] directions = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    while (!queue.isEmpty()) {
        int[] cell = queue.poll();

        int row = cell;
        int col = cell;
        int distance = cell;

        if (grid[row][col] == 2) {
            return distance;
        }

        for (int[] dir : directions) {
            int newRow = row + dir;
            int newCol = col + dir;

            if (newRow >= 0 && newRow < rows &&
                newCol >= 0 && newCol < cols &&
                !visited[newRow][newCol] &&
                grid[newRow][newCol] != -1) {

                visited[newRow][newCol] = true;

                queue.offer(new int[]{newRow, newCol, distance + 1});
            }
        }
    }

    return -1;
}
```

## Production Consideration

Real Uber-style system mein:

```text
1. Grid / road graph spatial index hota hai
2. Drivers dynamically move karte hain
3. ETA road distance + traffic se calculate hota hai
4. Multi-source BFS ya Dijkstra use hota hai
5. Large scale par geospatial indexing use hota hai
```

## Complexity

- Time: \(O(rows \times cols)\)
- Space: \(O(rows \times cols)\)

---

# 11. Example 5: As Far from Land as Possible

## Problem

Grid mein land `1` hai aur water `0` hai.

Aisi water cell dhundo jo kisi land cell se maximum distance par ho.

```text
grid = [
 ,[0][1]
 ,[0]
[1][0]
]

Output: 2
```

## Why Multi-Source BFS?

Saare land cells sources hain.

BFS se har water cell ki nearest land distance nikalo.

Maximum distance answer hai.

## Solution

```java
public int maxDistance(int[][] grid) {
    int rows = grid.length;
    int cols = grid.length;

    Queue<int[]> queue = new LinkedList<>();

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (grid[i][j] == 1) {
                queue.offer(new int[]{i, j});
            }
        }
    }

    if (queue.isEmpty() || queue.size() == rows * cols) {
        return -1;
    }

    int distance = -1;

    int[][] directions = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    while (!queue.isEmpty()) {
        int size = queue.size();

        distance++;

        for (int i = 0; i < size; i++) {
            int[] cell = queue.poll();

            for (int[] dir : directions) {
                int newRow = cell + dir;
                int newCol = cell + dir;

                if (newRow >= 0 && newRow < rows &&
                    newCol >= 0 && newCol < cols &&
                    grid[newRow][newCol] == 0) {

                    grid[newRow][newCol] = 1;

                    queue.offer(new int[]{newRow, newCol});
                }
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

# 12. Example 6: Map of Highest Peak

## Problem

Water cells di hain.

Har land cell ko highest possible height assign karo such that adjacent cells ka height difference maximum `1` ho.

```text
isWater = [
 ,[0][1]
[0]
]

Output:
[
 ,[1][0]
[1][2]
]
```

## Why Multi-Source BFS?

Water cells height `0` hain.

Unse BFS expand karo.

## Solution

```java
public int[][] highestPeak(int[][] isWater) {
    int rows = isWater.length;
    int cols = isWater.length;

    int[][] height = new int[rows][cols];

    Queue<int[]> queue = new LinkedList<>();

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (isWater[i][j] == 1) {
                height[i][j] = 0;
                queue.offer(new int[]{i, j});
            } else {
                height[i][j] = -1;
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
                height[newRow][newCol] == -1) {

                height[newRow][newCol] =
                    height[cell][cell] + 1;

                queue.offer(new int[]{newRow, newCol});
            }
        }
    }

    return height;
}
```

## Complexity

- Time: \(O(rows \times cols)\)
- Space: \(O(rows \times cols)\)

---

# 13. Multi-Source BFS vs Single-Source BFS

| Feature | Single-Source BFS | Multi-Source BFS |
|---|---|---|
| Sources | 1 | Multiple |
| Initial queue | One source | All sources |
| Use case | Shortest path from one point | Nearest source distance |
| Example | Maze shortest path | Rotting Oranges |
| Spread | One point se | Saare points se simultaneously |

### Rule

```text
Ek source -> Single-Source BFS
Multiple sources -> Multi-Source BFS
```

---

# 14. Common Mistakes

## Mistake 1: Sirf Ek Source Push Karna

Galat:

```java
queue.offer(firstSource);
```

Correct:

```java
for (int[] source : sources) {
    queue.offer(source);
}
```

---

## Mistake 2: Visited Mark Late Karna

Correct:

```java
visited[newRow][newCol] = true;
queue.offer(new int[]{newRow, newCol});
```

---

## Mistake 3: Grid Bounds Check Bhoolna

```java
if (newRow >= 0 && newRow < rows &&
    newCol >= 0 && newCol < cols)
```

---

## Mistake 4: Level Size Capture Na Karna

Rotting Oranges jaise problems mein:

```java
int size = queue.size();

for (int i = 0; i < size; i++) {
    // process one minute
}
```

---

## Mistake 5: Weighted Graph Mein Multi-Source BFS Lagana

Agar edge weights different hain:

```text
Dijkstra with multiple sources
```

Use karo.

---

## Mistake 6: Original Grid Modify Karna Jab Preserve Karna Ho

Separate `visited` / `distance` array use karo.

---

# 15. Multi-Source BFS Decision Flow

```text
Problem padho
      |
      v
Kya multiple sources hain?
      |
      +-- No --> Single-Source BFS
      |
      +-- Yes
          |
          v
Kya nearest distance chahiye?
      |
      +-- Yes --> Multi-Source BFS
      |
      +-- Kya spread simulate karna hai?
              |
              +-- Yes --> Level-wise Multi-Source BFS
              |
              +-- Kya weighted edges hain?
                      |
                      +-- Yes --> Multi-Source Dijkstra
```

---

# 16. Important Problems

## Medium

- Rotting Oranges
- 01 Matrix
- Walls and Gates
- As Far from Land as Possible
- Map of Highest Peak
- Nearest Exit from Entrance in Maze

## Hard

- As Far from Land as Possible
- Shortest Path in a Grid with Obstacles Elimination
- Minimum Moves to Reach Target with Rotations
- Escape a Large Maze

---

# 17. One-Line Recognition

> **Agar problem multiple starting points se nearest distance, simultaneous spread, fire/water/infection spread, ya nearest driver/hospital maang rahi hai — Multi-Source BFS try karo.**

Multi-Source BFS ka main power ye hai ki saare sources ko ek hi BFS wave mein process karta hai, isliye har cell ko uske nearest source ka distance efficiently mil jata hai.