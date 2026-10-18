# 🚀 DSA Pattern 36 — Topological Sort

> **Goal:** Topological Sort ko product-based company interview level par samajhna — real-world intuition, kyun use hota hai, kaise identify karein, Kahn’s Algorithm, DFS-based Topological Sort, cycle detection, course schedule, build order, templates, dry runs, production-style examples, common mistakes aur important problems.

---

## 1. Topological Sort Kya Hai?

Topological Sort ek **Directed Acyclic Graph — DAG** ke nodes ka linear ordering hai.

Rule:

```text
Agar edge u -> v hai,
to u hamesha v se pehle aayega.
```

Example:

```text
Graph:
A -> B
A -> C
B -> D
C -> D
```

Valid Topological Orders:

```text
A -> B -> C -> D
A -> C -> B -> D
```

Invalid Order:

```text
D -> A -> B -> C
```

Kyunki `D` pehle aa gaya, jabki `B` aur `C` uspe depend karte hain.

---

## 2. Topological Sort Kyun Use Karte Hain?

Main reasons:

1. **Task dependency ordering**
2. **Build system ordering**
3. **Course prerequisite ordering**
4. **Cycle detection**
5. **Workflow execution order**
6. **Dependency resolution**
7. **Compilation order**

### Core Intuition

> Jo kaam pehle complete hona chahiye, wo ordering mein pehle aayega.

Example:

```text
Courses:
0 -> 1
0 -> 2
1 -> 3
2 -> 3

Valid order:
0 -> 1 -> 2 -> 3
```

---

## 3. Real-World Production Examples

| Real System | Topological Sort Use |
|---|---|
| Build System | Compile modules in dependency order |
| Microservices | Service deployment order |
| Package Manager | Install dependencies first |
| Kubernetes | Resource creation order |
| CI/CD Pipeline | Job execution order |
| Task Scheduler | Job dependency order |
| Course Platform | Course prerequisite order |
| Spreadsheet | Formula calculation order |
| Database Migration | Migration script order |
| Data Pipeline | DAG job scheduling |
| Project Management | Task ordering |
| Compiler | Symbol dependency resolution |

### Product-Based Interview Framing

Interviewer puch sakta hai:

> “Microservices mein service A, B par depend karti hai. Deployment order kaise decide karoge?”

Answer:

```text
Services ko nodes maano.
Dependencies ko directed edges maano.

Topological sort karo.

Jo service kisi par depend nahi karti,
wo pehle deploy hogi.
```

Ya:

> “Build system mein modules hain aur modules ke beech dependencies hain. Compile order kaise nikaloge?”

Answer:

```text
Dependency graph banao.
Topological sort karo.

Agar cycle hai:
Build impossible.

Agar cycle nahi hai:
Topological order hi compile order hoga.
```

---

## 4. Kaise Pata Chalega Ki Topological Sort Use Hoga?

Problem mein ye signals dhundo:

| Signal | Possible Topological Sort |
|---|---|
| Directed graph | Topological Sort |
| Dependencies / prerequisites | Topological Sort |
| Task ordering | Topological Sort |
| Build order | Topological Sort |
| Course schedule | Topological Sort |
| Compilation order | Topological Sort |
| DAG ordering | Topological Sort |
| Cycle detection | Kahn’s Algorithm |

### Common Keywords

- Prerequisites
- Dependencies
- Order
- Build order
- Course schedule
- Task scheduling
- Directed graph
- DAG
- Compile order

### Mental Checklist

1. Kya directed graph hai?  
2. Kya dependencies hain?  
3. Kya ordering chahiye?  
4. Kya cycle possible hai?  
5. Kya kuch tasks pehle complete hone chahiye?  

Agar 2–3 answers **yes** hain, to Topological Sort try karo.

---

# 5. Important Condition — DAG

Topological Sort sirf **Directed Acyclic Graph** par possible hai.

```text
DAG = Directed Acyclic Graph
```

Agar cycle hai:

```text
Topological order impossible
```

Example:

```text
A -> B
B -> C
C -> A
```

Cycle:

```text
A -> B -> C -> A
```

Isliye valid topological order nahi ban sakta.

---

# 6. Kahn’s Algorithm — BFS Based

## Idea

```text
1. Har node ka indegree nikalo
2. Indegree 0 wale nodes queue mein daalo
3. Ek node process karo
4. Uske neighbors ka indegree decrease karo
5. Agar neighbor ka indegree 0 ho jaye, queue mein daalo
6. Processed nodes ka order hi topological order hai
```

### Indegree Kya Hai?

Node par aane wali incoming edges ki count.

Example:

```text
A -> B
A -> C
B -> D
C -> D

Indegree:
A = 0
B = 1
C = 1
D = 2
```

---

## Template — Kahn’s Algorithm

```java
public List<Integer> topologicalSort(int n, int[][] edges) {
    List<List<Integer>> adj = new ArrayList<>();

    for (int i = 0; i < n; i++) {
        adj.add(new ArrayList<>());
    }

    int[] indegree = new int[n];

    for (int[] edge : edges) {
        int u = edge;
        int v = edge;

        adj.get(u).add(v);
        indegree[v]++;
    }

    Queue<Integer> queue = new LinkedList<>();

    for (int i = 0; i < n; i++) {
        if (indegree[i] == 0) {
            queue.offer(i);
        }
    }

    List<Integer> order = new ArrayList<>();

    while (!queue.isEmpty()) {
        int node = queue.poll();

        order.add(node);

        for (int neighbor : adj.get(node)) {
            indegree[neighbor]--;

            if (indegree[neighbor] == 0) {
                queue.offer(neighbor);
            }
        }
    }

    if (order.size() != n) {
        return new ArrayList<>();
    }

    return order;
}
```

### Cycle Detection

```java
if (order.size() != n) {
    // Cycle exists
}
```

---

# 7. Example 1: Course Schedule II

## Problem

`numCourses` courses hain.

`prerequisites[i] = [a, b]` matlab course `a` lene se pehle course `b` lena zaroori hai.

Valid course order return karo.

Agar possible nahi, empty array return karo.

```text
numCourses = 4
prerequisites = [,,, ][0][1][2][3]

Output:[1][2][3][0]
```

## Why Topological Sort?

Courses dependencies hain.

Course `0` pehle, phir `1` aur `2`, phir `3`.

## Solution

```java
public int[] findOrder(int numCourses, int[][] prerequisites) {
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

    int[] order = new int[numCourses];

    int index = 0;

    while (!queue.isEmpty()) {
        int course = queue.poll();

        order[index++] = course;

        for (int next : adj.get(course)) {
            indegree[next]--;

            if (indegree[next] == 0) {
                queue.offer(next);
            }
        }
    }

    if (index != numCourses) {
        return new int;
    }

    return order;
}
```

## Dry Run

```text
numCourses = 4
prerequisites = [,,, ][0][1][2][3]

Graph:
0 -> 1
0 -> 2
1 -> 3
2 -> 3

Indegree:
0 = 0
1 = 1
2 = 1
3 = 2

Queue:


Process 0:
order = 

1 indegree -> 0
2 indegree -> 0

Queue:
[1][2]

Process 1:
order =[0][1]

3 indegree -> 1

Process 2:
order =[0][1][2]

3 indegree -> 0

Queue:


Process 3:
order =[0][1][2][3]

Final:
[1][2][3][0]
```

## Complexity

- Time: \(O(V + E)\)
- Space: \(O(V + E)\)

---

# 8. Example 2: Course Schedule — Cycle Detection

## Problem

Check karo ki saare courses complete ho sakte hain ya nahi.

```text
numCourses = 2
prerequisites = [][0][1]

Output: true
```

```text
numCourses = 2
prerequisites = [, ][0][1]

Output: false
```

## Why Kahn’s?

Agar cycle hai, to kuch courses kabhi process nahi honge.

```text
processed != numCourses
```

## Solution

```java
public boolean canFinish(int numCourses, int[][] prerequisites) {
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
0 = 1
1 = 1

No indegree 0

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

# 9. DFS-Based Topological Sort

## Idea

DFS postorder use karte hain.

```text
1. Node ko visiting mark karo
2. Neighbors DFS karo
3. Node ko visited mark karo
4. Node ko answer list mein add karo
5. End mein answer reverse karo
```

### Why Reverse?

Postorder mein dependency baad mein add hoti hai.

Example:

```text
A -> B

DFS:
B finish first
A finish later

Postorder:
B, A

Topological order:
A, B
```

---

## Template — DFS Topological Sort

```java
public List<Integer> topologicalSortDFS(int n, int[][] edges) {
    List<List<Integer>> adj = new ArrayList<>();

    for (int i = 0; i < n; i++) {
        adj.add(new ArrayList<>());
    }

    for (int[] edge : edges) {
        adj.get(edge).add(edge);
    }

    int[] state = new int[n];

    List<Integer> order = new ArrayList<>();

    for (int i = 0; i < n; i++) {
        if (state[i] == 0) {
            if (dfs(i, adj, state, order)) {
                return new ArrayList<>();
            }
        }
    }

    Collections.reverse(order);

    return order;
}

private boolean dfs(int node,
                    List<List<Integer>> adj,
                    int[] state,
                    List<Integer> order) {

    state[node] = 1;

    for (int neighbor : adj.get(node)) {
        if (state[neighbor] == 1) {
            return true;
        }

        if (state[neighbor] == 0) {
            if (dfs(neighbor, adj, state, order)) {
                return true;
            }
        }
    }

    state[node] = 2;

    order.add(node);

    return false;
}
```

## Dry Run

```text
Graph:
0 -> 1
0 -> 2
1 -> 3
2 -> 3

DFS 0:
DFS 1:
DFS 3:
Postorder add 3

Postorder add 1

DFS 2:
DFS 3 already visited
Postorder add 2

Postorder add 0

Postorder:
[0][1][2][3]

Reverse:
[0][1][2][3]
```

Ye bhi valid topological order hai.

## Complexity

- Time: \(O(V + E)\)
- Space: \(O(V + E)\)

---

# 10. Example 3: Alien Dictionary

## Problem

Alien language ke words diye hain.

Unse character ordering nikalo.

```text
words = ["wrt", "wrf", "er", "ett", "rftt"]

Output: "wertf"
```

## Why Topological Sort?

Words se character dependencies nikalti hain.

```text
w -> r
r -> t
t -> f
e -> r
```

Phir topological sort.

## Solution

```java
public String alienOrder(String[] words) {
    Map<Character, Set<Character>> adj = new HashMap<>();

    Map<Character, Integer> indegree = new HashMap<>();

    for (String word : words) {
        for (char c : word.toCharArray()) {
            adj.putIfAbsent(c, new HashSet<>());
            indegree.putIfAbsent(c, 0);
        }
    }

    for (int i = 0; i < words.length - 1; i++) {
        String word1 = words[i];
        String word2 = words[i + 1];

        if (word1.length() > word2.length() &&
            word1.startsWith(word2)) {

            return "";
        }

        int length = Math.min(word1.length(), word2.length());

        for (int j = 0; j < length; j++) {
            char first = word1.charAt(j);
            char second = word2.charAt(j);

            if (first != second) {
                if (!adj.get(first).contains(second)) {
                    adj.get(first).add(second);
                    indegree.put(second, indegree.get(second) + 1);
                }

                break;
            }
        }
    }

    Queue<Character> queue = new LinkedList<>();

    for (char c : indegree.keySet()) {
        if (indegree.get(c) == 0) {
            queue.offer(c);
        }
    }

    StringBuilder sb = new StringBuilder();

    while (!queue.isEmpty()) {
        char current = queue.poll();

        sb.append(current);

        for (char neighbor : adj.get(current)) {
            indegree.put(neighbor, indegree.get(neighbor) - 1);

            if (indegree.get(neighbor) == 0) {
                queue.offer(neighbor);
            }
        }
    }

    return sb.length() == indegree.size()
        ? sb.toString()
        : "";
}
```

## Complexity

Let:

```text
n = number of words
L = average word length
```

- Time: \(O(n \times L)\)
- Space: \(O(1)\), because only 26 lowercase letters

---

# 11. Example 4: Build Order

## Problem

Projects aur dependencies diye hain.

Valid build order nikalo.

```text
projects = [A, B, C, D]
dependencies = [
  [B, A],
  [C, A],
  [D, B],
  [D, C]
]

Meaning:
B depends on A
C depends on A
D depends on B and C

Valid order:
A -> B -> C -> D
```

## Why Topological Sort?

Dependency graph ka ordering chahiye.

## Approach

```text
1. Dependency graph banao
2. Indegree calculate karo
3. Indegree 0 projects queue mein daalo
4. Process karo
5. Neighbors ke indegree decrease karo
```

---

# 12. Topological Sort vs DFS

| Feature | Kahn’s Algorithm | DFS Topological Sort |
|---|---|---|
| Approach | BFS / Indegree | DFS Postorder |
| Cycle detection | Easy | Easy with colors |
| Order | Multiple valid orders | Multiple valid orders |
| Best for | Dependency processing | Graph traversal |
| Implementation | Queue | Recursion / Stack |
| Example | Course Schedule | Alien Dictionary |

---

# 13. Common Mistakes

## Mistake 1: Undirected Graph Par Topological Sort Lagana

Topological Sort sirf **directed graph** ke liye hota hai.

---

## Mistake 2: Cycle Wale Graph Par Order Return Karna

Agar:

```java
order.size() != n
```

To cycle hai.

Empty order return karo.

---

## Mistake 3: Edge Direction Ulta Lena

```text
prerequisite = [course, prereq]
```

Matlab:

```text
prereq -> course
```

Edge direction dhyan se banao.

---

## Mistake 4: Indegree Update Bhoolna

```java
indegree[neighbor]--;
```

Ye zaroori hai.

---

## Mistake 5: DFS Topological Sort Mein Reverse Bhoolna

DFS postorder ke baad:

```java
Collections.reverse(order);
```

---

## Mistake 6: Multiple Valid Orders Confuse Karna

Topological sort unique nahi hota.

```text
A -> B
A -> C
```

Dono valid:

```text
A -> B -> C
A -> C -> B
```

---

# 14. Topological Sort Decision Flow

```text
Problem padho
      |
      v
Kya directed graph hai?
      |
      +-- No --> Topological Sort probably nahi
      |
      +-- Yes
          |
          v
Kya dependencies / prerequisites hain?
      |
      +-- Yes --> Topological Sort
      |
      +-- Kya ordering chahiye?
              |
              +-- Yes --> Kahn’s / DFS Topological Sort
              |
              +-- Kya cycle detect karna hai?
                      |
                      +-- Yes --> Kahn’s processed count
```

---

# 15. Important Problems

## Medium

- Course Schedule
- Course Schedule II
- Alien Dictionary
- Minimum Height Trees
- Sequence Reconstruction
- Parallel Courses

## Hard

- Alien Dictionary
- Sequence Reconstruction
- Minimum Height Trees
- Course Schedule III
- Sort Items by Groups Respecting Dependencies

---

# 16. One-Line Recognition

> **Agar problem directed dependencies, prerequisites, task ordering, build order, ya compilation order maang rahi hai — Topological Sort try karo.**

Topological Sort ka main power ye hai ki dependency graph ko valid execution order mein convert karta hai, aur cycle hone par invalid ordering detect karta hai.