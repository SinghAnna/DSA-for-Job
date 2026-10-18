# 🚀 DSA Pattern 55 — A* Search Algorithm

> **Goal:** A* Search Algorithm ko product-based company interview level par samajhna — real-world intuition, kyun A* use hota hai, kaise identify karein, heuristic function, f = g + h, priority queue, grid pathfinding, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. A* Search Kya Hai?

A* Search ek pathfinding algorithm hai jo:

```text
1. Dijkstra ki accuracy
2. Greedy Best-First Search ki speed
```

combine karta hai.

### Core Formula

```text
f(n) = g(n) + h(n)
```

Where:

```text
g(n) = actual cost from start to node n
h(n) = estimated cost from node n to goal
f(n) = estimated total cost
```

### Simple Intuition

> A* sirf cost nahi dekhta, balki goal se distance bhi estimate karta hai.

Example:

```text
Grid:
S . . .
. # . .
. . . G

A*:
f(n) = g(n) + h(n)
```

---

## 2. A* Kyun Use Karte Hain?

Main reasons:

1. Grid pathfinding
2. Game AI
3. Robotics
4. Maps / Navigation
5. Delivery routing
6. Puzzle solving
7. Maze solving
8. Optimal path with heuristic

### Core Intuition

> A* Dijkstra se faster hota hai kyunki heuristic use karta hai.

Example:

```text
Dijkstra:
Explore all directions

A*:
Goal ke direction mein explore karo
```

---

## 3. Real-World Production Examples

| Real System | A* Use |
|---|---|
| Google Maps | Route finding |
| Uber / Ola | Driver routing |
| Delivery System | Route optimization |
| Game Development | NPC pathfinding |
| Robotics | Robot navigation |
| Warehouse Robots | Path planning |
| Drone Navigation | Path planning |
| Maze Solver | Path finding |
| Puzzle Solver | Optimal moves |
| Network Routing | Packet routing |
| Recommendation System | Best path |
| Cloud Infrastructure | Resource routing |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Delivery rider ko grid ke ek corner se dusre corner tak optimal path se pahunchana hai. Kaise karoge?”

Answer:

```text
A* Search use karenge.

f(n) = g(n) + h(n)

g(n) = cost from start
h(n) = Manhattan distance to goal
```

Ya:

> “Game mein NPC ko player tak optimal path se pahunchana hai. Kaise approach karoge?”

Answer:

```text
A* Search use karenge.

Grid cells = nodes
Obstacles = blocked cells
Heuristic = Manhattan / Euclidean distance
```

---

## 4. Kaise Pata Chalega Ki A* Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible A* |
|---|---|
| Grid pathfinding | A* |
| Maze solving | A* |
| Optimal path | A* |
| Heuristic available | A* |
| Game AI | A* |
| Robotics | A* |
| Maps / Navigation | A* |

### Common Keywords

- Path
- Grid
- Maze
- Optimal
- Heuristic
- Navigation
- Robot
- Game
- Shortest path

### Mental Checklist

1. Kya grid / maze hai?  
2. Kya optimal path chahiye?  
3. Kya heuristic available hai?  
4. Kya obstacles hain?  
5. Kya Dijkstra slow lagega?  

Agar 2–3 answers **yes** hain, to A* try karo.

---

# 5. A* Problem Solve Karne Ke 5 Steps

```text
1. State define karo
2. Heuristic function define karo
3. Priority queue use karo
4. f = g + h calculate karo
5. Goal tak pahuncho
```

Example — Grid Pathfinding:

```text
State:
(row, col)

Heuristic:
Manhattan distance

Priority:
f = g + h
```

---

# 6. A* Template — Grid Pathfinding

```java
class Node {

    int row;

    int col;

    int g;

    int h;

    Node(int row, int col, int g, int h) {

        this.row = row;

        this.col = col;

        this.g = g;

        this.h = h;
    }

    int f() {
        return g + h;
    }
}

public int aStarSearch(
    int[][] grid,
    int[] start,
    int[] goal
) {

    int rows = grid.length;

    int cols = grid.length;

    PriorityQueue<Node> minHeap =
        new PriorityQueue<>((a, b) ->
            Integer.compare(a.f(), b.f())
        );

    boolean[][] visited = new boolean[rows][cols];

    int startH =
        Math.abs(start - goal) +
        Math.abs(start - goal);

    minHeap.offer(new Node(start, start, 0, startH));

    int[][] directions = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };

    while (!minHeap.isEmpty()) {

        Node current = minHeap.poll();

        int row = current.row;

        int col = current.col;

        if (visited[row][col]) {
            continue;
        }

        visited[row][col] = true;

        if (row == goal && col == goal) {
            return current.g;
        }

        for (int[] dir : directions) {

            int newRow = row + dir;

            int newCol = col + dir;

            if (newRow >= 0 &&
                newRow < rows &&
                newCol >= 0 &&
                newCol < cols &&
                grid[newRow][newCol] == 0 &&
                !visited[newRow][newCol]) {

                int newG = current.g + 1;

                int newH =
                    Math.abs(newRow - goal) +
                    Math.abs(newCol - goal);

                minHeap.offer(
                    new Node(newRow, newCol, newG, newH)
                );
            }
        }
    }

    return -1;
}
```

---

# 7. Example 1: Shortest Path in a Grid with Obstacles

## Problem

Grid diya hai.

`0` = free cell  
`1` = obstacle

Start se goal tak shortest path nikalo.

```text
grid = [
 ,[0]
 ,[1][0]
[0]
]

start =[0]
goal =[2]

Output = 4
```

## Why A*?

Grid pathfinding + heuristic available.

## Solution

```java
public int shortestPath(int[][] grid, int[] start, int[] goal) {
    return aStarSearch(grid, start, goal);
}
```

## Dry Run

```text
grid:
S . .
# .
. . G

A*:
f = g + h

Start:
g = 0
h = 4
f = 4

Goal:
g = 4
h = 0
f = 4

Answer = 4
```

## Complexity

- Time: \(O(E \log V)\)
- Space: \(O(V)\)

---

# 8. Example 2: Maze Solver

## Problem

Maze diya hai.

Start se exit tak shortest path nikalo.

```text
maze = [
  ['S', '.', '#', '.'],
  ['.', '#', '.', '.'],
  ['.', '.', '.', 'E']
]
```

## Why A*?

Maze pathfinding + heuristic available.

## Solution

```java
public int solveMaze(char[][] maze) {

    int rows = maze.length;

    int cols = maze.length;

    int[] start = new int;

    int[] goal = new int;

    for (int i = 0; i < rows; i++) {

        for (int j = 0; j < cols; j++) {

            if (maze[i][j] == 'S') {
                start = i;
                start = j;
            }

            if (maze[i][j] == 'E') {
                goal = i;
                goal = j;
            }
        }
    }

    int[][] grid = new int[rows][cols];

    for (int i = 0; i < rows; i++) {

        for (int j = 0; j < cols; j++) {

            grid[i][j] = maze[i][j] == '#' ? 1 : 0;
        }
    }

    return aStarSearch(grid, start, goal);
}
```

## Dry Run

```text
maze:
S . # .
. # . .
. . . E

A*:
f = g + h

Start:
g = 0
h = 4

Goal:
g = 4
h = 0

Answer = 4
```

## Complexity

- Time: \(O(E \log V)\)
- Space: \(O(V)\)

---

# 9. Example 3: 8-Puzzle Solver

## Problem

8-Puzzle solve karna hai.

Minimum moves nikalo.

```text
Initial:
1 2 3
4 0 6
7 5 8

Goal:
1 2 3
4 5 6
7 8 0
```

## Why A*?

Puzzle solving + heuristic available.

## Heuristic

```text
h(n) = number of misplaced tiles
```

## Solution

```java
class PuzzleNode {

    String state;

    int g;

    int h;

    PuzzleNode(String state, int g, int h) {

        this.state = state;

        this.g = g;

        this.h = h;
    }

    int f() {
        return g + h;
    }
}

public int solvePuzzle(String start, String goal) {

    PriorityQueue<PuzzleNode> minHeap =
        new PriorityQueue<>((a, b) ->
            Integer.compare(a.f(), b.f())
        );

    Set<String> visited = new HashSet<>();

    int h = heuristic(start, goal);

    minHeap.offer(new PuzzleNode(start, 0, h));

    while (!minHeap.isEmpty()) {

        PuzzleNode current = minHeap.poll();

        if (current.state.equals(goal)) {
            return current.g;
        }

        if (visited.contains(current.state)) {
            continue;
        }

        visited.add(current.state);

        for (String next : neighbors(current.state)) {

            if (!visited.contains(next)) {

                int newG = current.g + 1;

                int newH = heuristic(next, goal);

                minHeap.offer(
                    new PuzzleNode(next, newG, newH)
                );
            }
        }
    }

    return -1;
}

private int heuristic(String state, String goal) {

    int count = 0;

    for (int i = 0; i < state.length(); i++) {

        if (state.charAt(i) != goal.charAt(i)) {
            count++;
        }
    }

    return count;
}

private List<String> neighbors(String state) {

    List<String> result = new ArrayList<>();

    int zeroIndex = state.indexOf('0');

    int row = zeroIndex / 3;

    int col = zeroIndex % 3;

    int[][] directions = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };

    for (int[] dir : directions) {

        int newRow = row + dir;

        int newCol = col + dir;

        if (newRow >= 0 &&
            newRow < 3 &&
            newCol >= 0 &&
            newCol < 3) {

            char[] chars = state.toCharArray();

            int newIndex = newRow * 3 + newCol;

            chars[zeroIndex] = chars[newIndex];

            chars[newIndex] = '0';

            result.add(new String(chars));
        }
    }

    return result;
}
```

## Dry Run

```text
Initial:
1 2 3
4 0 6
7 5 8

Goal:
1 2 3
4 5 6
7 8 0

A*:
f = g + h

Answer = minimum moves
```

## Complexity

- Time: \(O(b^d)\)
- Space: \(O(b^d)\)

---

# 10. A* vs Dijkstra

| Feature | A* | Dijkstra |
|---|---|---|
| Heuristic | Yes | No |
| Speed | Faster | Slower |
| Accuracy | Optimal | Optimal |
| Best for | Grid / Maze | General graph |
| Example | Game AI | Google Maps |

### Rule

```text
Heuristic available -> A*
No heuristic -> Dijkstra
```

---

# 11. A* Patterns

| Pattern | Heuristic | Use |
|---|---|---|
| Grid Pathfinding | Manhattan | 4-direction movement |
| Grid Pathfinding | Euclidean | Any-angle movement |
| Maze Solver | Manhattan | Maze |
| Puzzle Solver | Misplaced tiles | 8-Puzzle |
| Game AI | Custom | NPC movement |
| Robotics | Custom | Robot navigation |

---

# 12. Common Mistakes

## Mistake 1: Heuristic Galat Karna

```text
Manhattan distance for 4-direction movement
Euclidean distance for any-angle movement
```

---

## Mistake 2: f = g + h Formula Bhoolna

```java
int f() {
    return g + h;
}
```

---

## Mistake 3: Visited Check Bhoolna

```java
if (visited[row][col]) {
    continue;
}
```

---

## Mistake 4: Obstacle Check Bhoolna

```java
if (grid[newRow][newCol] == 0) {
    // valid
}
```

---

## Mistake 5: Priority Queue Comparator Galat Karna

```java
new PriorityQueue<>((a, b) ->
    Integer.compare(a.f(), b.f())
);
```

---

# 13. A* Decision Flow

```text
Problem padho
      |
      v
Kya grid / maze hai?
      |
      +-- Yes
          |
          v
Kya heuristic available hai?
      |
      +-- Yes --> A*
      |
      +-- No --> Dijkstra / BFS
      |
      +-- Kya puzzle hai?
              |
              +-- Yes --> A* with custom heuristic
              |
              +-- Kya game AI hai?
                      |
                      +-- Yes --> A* with custom heuristic
```

---

# 14. Important Problems

## Medium

- Shortest Path in a Grid with Obstacles Elimination
- Minimum Moves to Reach Target with Rotations
- Maze Solver
- 8-Puzzle Solver
- Shortest Path in Binary Matrix

## Hard

- Sliding Puzzle
- Minimum Moves to Reach Target with Rotations
- Shortest Path in a Grid with Obstacles Elimination
- Escape a Large Maze

---

# 15. One-Line Recognition

> **Agar problem grid pathfinding, maze solving, puzzle solving, ya game AI maang rahi hai aur heuristic available hai —