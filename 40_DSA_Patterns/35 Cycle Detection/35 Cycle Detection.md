# 🚀 DSA Pattern 35 — Cycle Detection

> **Goal:** Cycle Detection ko product-based company interview level par samajhna — real-world intuition, kyun use hota hai, kaise identify karein, undirected graph cycle, directed graph cycle, DFS colors, Kahn’s algorithm, Union-Find, linked list cycle, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Cycle Detection Kya Hai?

Cycle Detection ka matlab hai check karna ki graph ya linked list mein koi **loop** hai ya nahi.

Example — Directed Graph Cycle:

```text
0 -> 1
1 -> 2
2 -> 0
```

Path:

```text
0 -> 1 -> 2 -> 0
```

Ye cycle hai.

Example — Undirected Graph Cycle:

```text
0 - 1
1 - 2
2 - 0
```

Path:

```text
0 -> 1 -> 2 -> 0
```

Ye bhi cycle hai.

Example — Linked List Cycle:

```text
1 -> 2 -> 3 -> 2
          ↑____|
```

Node `2` dobara visit ho raha hai, isliye cycle hai.

---

## 2. Cycle Detection Kyun Use Karte Hain?

Main reasons:

1. **Deadlock detection**
2. **Circular dependency detection**
3. **Infinite loop prevention**
4. **Valid task ordering check**
5. **Graph validity check**
6. **Linked list corruption detection**
7. **State machine loop detection**

### Core Intuition

> Agar traversal ke dauran kisi already visiting node par wapas aa jayein, to cycle exist karti hai.

---

## 3. Real-World Production Examples

| Real System | Cycle Detection Use |
|---|---|
| Build System | Circular dependency detection |
| Microservices | Service dependency cycle |
| Kubernetes | Resource dependency cycle |
| Package Manager | Circular package dependency |
| Task Scheduler | Circular job dependency |
| Database | Foreign key cycle detection |
| Workflow Engine | Infinite workflow loop |
| State Machine | Invalid state cycle |
| Blockchain | Transaction loop detection |
| Garbage Collector | Reference cycle detection |
| Distributed Systems | Deadlock detection |
| Git-like Systems | Commit graph cycle |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Microservices mein service A, B par depend karti hai, B, C par, aur C wapas A par. Deployment order kaise decide karoge?”

Answer:

```text
Services ko nodes maano.
Dependencies ko directed edges maano.

Cycle detection karo.

Agar cycle hai:
Deployment order possible nahi.

Agar cycle nahi hai:
Topological sort se order nikalo.
```

Ya:

> “Job scheduler mein jobs circularly depend kar rahi hain. Infinite loop kaise prevent karoge?”

Answer:

```text
Job dependency graph banao.
DFS colors ya Kahn’s algorithm se cycle detect karo.
Cycle hone par scheduler ko fail/error state mein daalo.
```

---

## 4. Kaise Pata Chalega Ki Cycle Detection Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Cycle Detection |
|---|---|
| Directed graph cycle | DFS Colors / Kahn’s |
| Undirected graph cycle | DFS Parent / Union-Find |
| Course schedule possible? | Cycle Detection |
| Task ordering possible? | Cycle Detection |
| Deadlock detection | Cycle Detection |
| Linked list cycle | Fast & Slow |
| Circular dependency | Cycle Detection |
| Valid tree check | Cycle Detection |

### Common Keywords

- Cycle
- Loop
- Circular
- Dependency
- Deadlock
- Prerequisite
- Course schedule
- Valid tree
- Infinite loop

### Mental Checklist

1. Kya graph / dependency structure hai?  
2. Kya directed graph hai?  
3. Kya undirected graph hai?  
4. Kya linked list hai?  
5. Kya task ordering possible hai?  

Agar 2–3 answers **yes** hain, to Cycle Detection try karo.

---

# 5. Cycle Detection Types

| Graph Type | Best Approach |
|---|---|
| Directed Graph | DFS with 3 colors / Kahn’s Algorithm |
| Undirected Graph | DFS with parent / Union-Find |
| Linked List | Fast & Slow Pointers |
| Dependency Graph | Topological Sort |
| Dynamic Connectivity | Union-Find |

---

# 6. Directed Graph Cycle — DFS with Colors

## Color States

```text
0 = Unvisited
1 = Visiting
2 = Visited
```

### Rule

```text
Agar DFS ke dauran kisi node par aaye
jiska state = 1 (Visiting),
to cycle hai.
```

### Why?

`Visiting` matlab us node ka DFS abhi complete nahi hua.

Agar wapas usi node par aa gaye, to loop ban gaya.

---

## Template — Directed Cycle Detection

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

        if (state[neighbor] == 0) {
            if (dfs(neighbor, adj, state)) {
                return true;
            }
        }
    }

    state[node] = 2;

    return false;
}
```

---

## Example: Course Schedule

## Problem

`numCourses` courses hain.

`prerequisites[i] = [a, b]` matlab course `a` lene se pehle course `b` lena zaroori hai.

Check karo ki saare courses complete ho sakte hain ya nahi.

```text
numCourses = 2
prerequisites = [][0][1]

Output: true
Explanation: 0 -> 1
```

```text
numCourses = 2
prerequisites = [, ][0][1]

Output: false
Explanation: 0 -> 1 -> 0 cycle
```

## Why Cycle Detection?

Agar prerequisite graph mein cycle hai, to koi valid course order possible nahi.

## Solution

```java
public boolean canFinish(int numCourses, int[][] prerequisites) {
    List<List<Integer>> adj = new ArrayList<>();

    for (int i = 0; i < numCourses; i++) {
        adj.add(new ArrayList<>());
    }

    for (int[] prerequisite : prerequisites) {
        int course = prerequisite;
        int prereq = prerequisite;

        adj.get(prereq).add(course);
    }

    int[] state = new int[numCourses];

    for (int i = 0; i < numCourses; i++) {
        if (state[i] == 0) {
            if (dfs(i, adj, state)) {
                return false;
            }
        }
    }

    return true;
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
prerequisites = [, ][0][1]

Graph:
0 -> 1
1 -> 0

DFS 0:
state = 1

DFS 1:
state = 1

Neighbor 0:
state == 1

Cycle found

Answer = false
```

## Complexity

- Time: \(O(V + E)\)
- Space: \(O(V + E)\)

---

# 7. Directed Graph Cycle — Kahn’s Algorithm

Kahn’s Algorithm **Topological Sort** use karta hai.

### Idea

```text
1. Har node ka indegree nikalo
2. Indegree 0 wale nodes queue mein daalo
3. Ek node process karo
4. Uske neighbors ka indegree decrease karo
5. Agar neighbor ka indegree 0 ho jaye, queue mein daalo
6. End mein agar saare nodes process ho gaye, to cycle nahi hai
```

### Core Intuition

> Agar cycle hai, to cycle ke nodes ka indegree kabhi 0 nahi hoga.

---

## Template — Kahn’s Cycle Detection

```java
public boolean canFinishKahn(int numCourses, int[][] prerequisites) {
    List<List<Integer>> adj = new ArrayList<>();

    for (int i = 0; i < numCourses; i++) {
        adj.add(new ArrayList<>());
    }

    int[] indegree = new int[numCourses];

    for (int[] prerequisite : prerequisites) {
        int course = prerequisite;
        int prereq = prerequisite;

        adj.get(prereq).add(course);
        indegree[course]++;
    }

    Queue<Integer> queue = new LinkedList<>();

    for (int i = 0; i < numCourses; i++) {
        if (indegree[i] == 0) {
            queue.offer(i);
        }
    }

    int processed = 0;

    while (!queue.isEmpty()) {
        int course = queue.poll();

        processed++;

        for (int next : adj.get(course)) {
            indegree[next]--;

            if (indegree[next] == 0) {
                queue.offer(next);
            }
        }
    }

    return processed == numCourses;
}
```

## Dry Run

```text
prerequisites = [, ][0][1]

Graph:
0 -> 1
1 -> 0

Indegree:
0 -> 1
1 -> 1

No node has indegree 0

Queue empty

processed = 0
numCourses = 2

0 != 2

Cycle exists

Answer = false
```

## Complexity

- Time: \(O(V + E)\)
- Space: \(O(V + E)\)

---

# 8. Undirected Graph Cycle — DFS with Parent

## Idea

DFS traversal mein:

```text
Agar visited neighbor mila,
but wo current node ka parent nahi hai,
to cycle hai.
```

### Why Parent Check?

Undirected graph mein:

```text
0 - 1
```

DFS from `0`:

```text
0 -> 1
```

Ab `1` ka neighbor `0` visited hai.

But `0` parent hai.

Ye cycle nahi hai.

---

## Template — Undirected Cycle Detection

```java
public boolean hasCycleUndirected(int n, List<List<Integer>> adj) {
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

DFS 0:
parent = -1
visited = true

DFS 1:
parent = 0
visited = true

DFS 2:
parent = 1
visited = true

Neighbor 0:
visited true
0 != parent 1

Cycle found
```

## Complexity

- Time: \(O(V + E)\)
- Space: \(O(V)\)

---

# 9. Undirected Graph Cycle — Union-Find

## Idea

Har edge `(u, v)` ke liye:

```text
Agar find(u) == find(v):
Cycle exists

Else:
union(u, v)
```

### Core Intuition

> Agar do nodes already same component mein hain aur unke beech edge add kar rahe ho, to cycle banegi.

---

## Template — Union-Find Cycle Detection

```java
class DSU {
    int[] parent;
    int[] rank;

    public DSU(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return false;
        }

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }

        return true;
    }
}

public boolean hasCycleUnionFind(int n, int[][] edges) {
    DSU dsu = new DSU(n);

    for (int[] edge : edges) {
        int u = edge;
        int v = edge;

        if (!dsu.union(u, v)) {
            return true;
        }
    }

    return false;
}
```

## Dry Run

```text
Edges:
[0][1]
[1][2]
[2][0]

Edge:[0][1]
Different roots
Union

Edge:[1][2]
Different roots
Union

Edge:[0][2]
find(2) == find(0)
Cycle found
```

## Complexity

- Time: Nearly \(O(E \alpha(V))\)
- Space: \(O(V)\)

---

# 10. Linked List Cycle — Fast & Slow Pointers

## Idea

```text
slow -> 1 step
fast -> 2 steps
```

Agar cycle hai, dono meet karenge.

Agar cycle nahi hai, fast `null` par pahunch jayega.

---

## Template — Linked List Cycle

```java
public boolean hasCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;

        if (slow == fast) {
            return true;
        }
    }

    return false;
}
```

## Dry Run

```text
1 -> 2 -> 3 -> 4 -> 2

slow = 1
fast = 1

Step 1:
slow = 2
fast = 3

Step 2:
slow = 3
fast = 2

Step 3:
slow = 4
fast = 4

Meet

Cycle found
```

## Complexity

- Time: \(O(n)\)
- Space: \(O(1)\)

---

# 11. Cycle Detection Approaches Comparison

| Approach | Graph Type | Time | Space | Best For |
|---|---|---|---|---|
| DFS Colors | Directed | \(O(V + E)\) | \(O(V)\) | Cycle + Topological order |
| Kahn’s Algorithm | Directed | \(O(V + E)\) | \(O(V + E)\) | Dependency ordering |
| DFS + Parent | Undirected | \(O(V + E)\) | \(O(V)\) | Simple undirected cycle |
| Union-Find | Undirected | Nearly \(O(E)\) | \(O(V)\) | Dynamic edges |
| Fast & Slow | Linked List | \(O(n)\) | \(O(1)\) | Linked list cycle |

---

# 12. Common Mistakes

## Mistake 1: Directed Graph Mein Parent Check Use Karna

Directed graph mein parent check insufficient hai.

Correct:

```text
DFS Colors:
0 = Unvisited
1 = Visiting
2 = Visited
```

---

## Mistake 2: Undirected Graph Mein Parent Check Bhoolna

```java
else if (neighbor != parent) {
    return true;
}
```

Ye zaroori hai.

---

## Mistake 3: Visited Mark Late Karna

Galat:

```java
int node = queue.poll();
visited[node] = true;
```

BFS mein visited mark enqueue karte waqt karo.

---

## Mistake 4: Kahn’s Mein Processed Count Bhoolna

```java
return processed == numCourses;
```

Ye cycle detection ka main check hai.

---

## Mistake 5: Union-Find Mein Root Compare Bhoolna

```java
if (find(u) == find(v)) {
    return true;
}
```

---

## Mistake 6: Linked List Cycle Mein Null Check Bhoolna

```java
while (fast != null && fast.next != null)
```

---

# 13. Cycle Detection Decision Flow

```text
Problem padho
      |
      v
Kya graph / dependency / linked list hai?
      |
      +-- No --> Cycle Detection probably nahi
      |
      +-- Yes
          |
          v
Kya directed graph hai?
      |
      +-- Yes --> DFS Colors / Kahn’s Algorithm
      |
      +-- Kya undirected graph hai?
              |
              +-- Yes --> DFS + Parent / Union-Find
              |
              +-- Kya linked list hai?
                      |
                      +-- Yes --> Fast & Slow Pointers
```

---

# 14. Important Problems

## Easy

- Linked List Cycle
- Find if Path Exists in Graph

## Medium

- Course Schedule
- Course Schedule II
- Graph Valid Tree
- Redundant Connection
- Find Eventual Safe States
- Detect Cycle in Undirected Graph

## Hard

- Course Schedule III
- Critical Connections in a Network
- Minimum Number of Days to Disconnect Island
- Most Stones Removed with Same Row or Column

---

# 15. One-Line Recognition

> **Agar problem circular dependency, deadlock, course prerequisites, valid tree, linked list loop, ya infinite process maang rahi hai — Cycle Detection try karo.**

Cycle Detection ka main power ye hai ki invalid dependency loops ko early detect karta hai, taki system infinite loop, deadlock ya invalid ordering se bach sake.