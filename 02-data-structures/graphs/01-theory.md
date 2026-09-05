# 🕸️ Graphs - Complete Guide (Part 1)

---

## 📚 Theory

### What is a Graph?
A **Graph** is a non-linear data structure consisting of a finite set of **vertices** (or nodes) and a collection of **edges** that connect pairs of these vertices.

Mathematically, it is denoted as:
$$G = (V, E)$$
Where:
* $V$ is the set of vertices (nodes).
* $E$ is the set of edges (connections).

---

### 🔍 Graph Terminology

```text
    A --------- B
    |  \        |
    |    \      |
    |      \    |
    C --------- D --------- E

- Vertices/Nodes: A, B, C, D, E
- Edges: AB, AC, AD, BC, CD, DE
- Degree: Number of edges connected to a vertex
- Path: Sequence of vertices connected by edges
- Cycle: Path that starts and ends at same vertex


### Types of Graphs

1. **Undirected Graph** - Edges have no direction
2. **Directed Graph (Digraph)** - Edges have direction
3. **Weighted Graph** - Edges have weights/costs
4. **Unweighted Graph** - Edges have no weights
5. **Cyclic Graph** - Contains at least one cycle
6. **Acyclic Graph** - No cycles
7. **Connected Graph** - Path exists between all pairs of vertices
8. **Disconnected Graph** - Some vertices not reachable
9. **Tree** - Connected acyclic undirected graph
10. **DAG (Directed Acyclic Graph)** - Directed graph with no cycles

### Graph Representations

Adjacency Matrix (2D Array) Graph: 0 -- 1
2 -- 3
| |

Matrix (undirected):
0 1 2 3
0

1

2

3

Space: O(V²)

Add edge: O(1)

Remove edge: O(1)

Check if edge exists: O(1)

Find neighbors: O(V)

Adjacency List (Array of Lists) 0:

1:

2:

3:

Space: O(V + E)

Add edge: O(1)

Remove edge: O(degree)

Check if edge exists: O(degree)

Find neighbors: O(1)

Edge List (List of Edges) [(0,1), (0,2), (1,3), (2,3)]

Space: O(E)

Used in Kruskal's algorithm


### Graph Traversal Algorithms

| Algorithm | Time Complexity | Space Complexity | Use Cases |
|-----------|----------------|------------------|-----------|
| BFS | O(V + E) | O(V) | Shortest path (unweighted), Level order |
| DFS | O(V + E) | O(V) | Cycle detection, Topological sort, Path finding |
| Dijkstra | O((V+E) log V) | O(V) | Shortest path (weighted, non-negative) |
| Bellman-Ford | O(V × E) | O(V) | Shortest path (with negative weights) |
| Floyd-Warshall | O(V³) | O(V²) | All pairs shortest path |

### Important Graph Algorithms

1. **BFS (Breadth-First Search)** - Level by level traversal
2. **DFS (Depth-First Search)** - Go deep, then backtrack
3. **Topological Sort** - Linear ordering of DAG
4. **Cycle Detection** - Detect cycles in graph
5. **Dijkstra's Algorithm** - Shortest path (non-negative weights)
6. **Bellman-Ford** - Shortest path (handles negative weights)
7. **Floyd-Warshall** - All pairs shortest path
8. **Kruskal's Algorithm** - Minimum Spanning Tree
9. **Prim's Algorithm** - Minimum Spanning Tree
10. **Union Find** - Connected components

---

## 💻 Java Code Examples

### Problem 1: Graph Representation

**Question:** Implement a graph using adjacency list with methods to add edges and display the graph.

```java
/**
 * Problem: Graph Representation using Adjacency List
 * 
 * Visual:
 * 
 * Undirected Graph:
 *     0 ---- 1
 *     | \    |
 *     |  \   |
 *     |   \  |
 *     2 ---- 3
 * 
 * Adjacency List:
 * 0:[1][2][3]
 * 1:[0][3]
 * 2:[3][0]
 * 3:[1][2][0]
 * 
 * Directed Graph:
 *     0 -> 1
 *     ↓    ↓
 *     2 -> 3
 * 
 * Adjacency List:
 * 0:[1][2]
 * 1:[1]
 * 2:[1]
 * 3: []
 * 
 * Time Complexity:
 * - Add edge: O(1)
 * - Display: O(V + E)
 * 
 * Space Complexity: O(V + E)
 */
public class GraphRepresentation {
    
    // Undirected Graph
    static class GraphUndirected {
        private int vertices;
        private java.util.List<java.util.List<Integer>> adjList;
        
        public GraphUndirected(int vertices) {
            this.vertices = vertices;
            adjList = new java.util.ArrayList<>();
            
            for (int i = 0; i < vertices; i++) {
                adjList.add(new java.util.ArrayList<>());
            }
        }
        
        // Add undirected edge
        public void addEdge(int u, int v) {
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        
        // Add edge with weight (for weighted graph)
        public void addEdgeWeighted(int u, int v, int weight) {
            // Store as pair: [neighbor, weight]
            // For simplicity, using separate weight map
            System.out.println("Edge " + u + "-" + v + " with weight " + weight);
        }
        
        // Display graph
        public void display() {
            for (int i = 0; i < vertices; i++) {
                System.out.print(i + ": ");
                for (int neighbor : adjList.get(i)) {
                    System.out.print(neighbor + " ");
                }
                System.out.println();
            }
        }
        
        // Get neighbors
        public java.util.List<Integer> getNeighbors(int vertex) {
            return adjList.get(vertex);
        }
    }
    
    // Directed Graph
    static class GraphDirected {
        private int vertices;
        private java.util.List<java.util.List<Integer>> adjList;
        
        public GraphDirected(int vertices) {
            this.vertices = vertices;
            adjList = new java.util.ArrayList<>();
            
            for (int i = 0; i < vertices; i++) {
                adjList.add(new java.util.ArrayList<>());
            }
        }
        
        // Add directed edge
        public void addEdge(int u, int v) {
            adjList.get(u).add(v);
        }
        
        public void display() {
            for (int i = 0; i < vertices; i++) {
                System.out.print(i + " -> ");
                for (int neighbor : adjList.get(i)) {
                    System.out.print(neighbor + " ");
                }
                System.out.println();
            }
        }
    }
    
    // Weighted Graph
    static class Edge {
        int to;
        int weight;
        
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    
    static class GraphWeighted {
        private int vertices;
        private java.util.List<java.util.List<Edge>> adjList;
        
        public GraphWeighted(int vertices) {
            this.vertices = vertices;
            adjList = new java.util.ArrayList<>();
            
            for (int i = 0; i < vertices; i++) {
                adjList.add(new java.util.ArrayList<>());
            }
        }
        
        public void addEdge(int u, int v, int weight) {
            adjList.get(u).add(new Edge(v, weight));
        }
        
        public void display() {
            for (int i = 0; i < vertices; i++) {
                System.out.print(i + ": ");
                for (Edge edge : adjList.get(i)) {
                    System.out.print("(" + edge.to + "," + edge.weight + ") ");
                }
                System.out.println();
            }
        }
    }
    
    public static void main(String[] args) {
        // Undirected graph
        System.out.println("Undirected Graph:");
        GraphUndirected graph1 = new GraphUndirected(4);
        graph1.addEdge(0, 1);
        graph1.addEdge(0, 2);
        graph1.addEdge(0, 3);
        graph1.addEdge(1, 3);
        graph1.addEdge(2, 3);
        graph1.display();
        
        // Directed graph
        System.out.println("\nDirected Graph:");
        GraphDirected graph2 = new GraphDirected(4);
        graph2.addEdge(0, 1);
        graph2.addEdge(0, 2);
        graph2.addEdge(1, 3);
        graph2.addEdge(2, 3);
        graph2.display();
        
        // Weighted graph
        System.out.println("\nWeighted Graph:");
        GraphWeighted graph3 = new GraphWeighted(4);
        graph3.addEdge(0, 1, 5);
        graph3.addEdge(0, 2, 3);
        graph3.addEdge(1, 3, 2);
        graph3.addEdge(2, 3, 4);
        graph3.display();
    }
}
```

---

### Problem 2: BFS (Breadth-First Search)

**Question:** Implement BFS traversal of a graph starting from a given vertex.

```java
/**
 * Problem: BFS Traversal of Graph
 * LeetCode: 102 (similar for trees)
 * 
 * Visual:
 * 
 * Graph:
 *     0 ---- 1
 *     | \    |
 *     |  \   |
 *     |   \  |
 *     2 ---- 3 ---- 4
 * 
 * BFS from 0:
 * Queue: 
 *          (after processing 0)[1][2][3]
 *             (after processing 1)[2][3]
 *             (after processing 2)[3][4]
 *                (after processing 3)[2]
 *        []         (after processing 4)
 * 
 * BFS Order: 0, 1, 2, 3, 4
 * 
 * Applications:
 * - Shortest path in unweighted graph
 * - Level order traversal
 * - Check if graph is bipartite
 * - Find connected components
 * 
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 */
public class GraphBFS {
    
    static class Graph {
        private int vertices;
        private java.util.List<java.util.List<Integer>> adjList;
        
        public Graph(int vertices) {
            this.vertices = vertices;
            adjList = new java.util.ArrayList<>();
            
            for (int i = 0; i < vertices; i++) {
                adjList.add(new java.util.ArrayList<>());
            }
        }
        
        public void addEdge(int u, int v) {
            adjList.get(u).add(v);
            adjList.get(v).add(u); // For undirected
        }
    }
    
    // BFS Traversal
    public static java.util.List<Integer> bfs(Graph graph, int start) {
        java.util.List<Integer> result = new java.util.ArrayList<>();
        boolean[] visited = new boolean[graph.vertices];
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        
        // Start from given vertex
        queue.add(start);
        visited[start] = true;
        
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result.add(vertex);
            
            // Visit all neighbors
            for (int neighbor : graph.adjList.get(vertex)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        
        return result;
    }
    
    // BFS with level information
    public static java.util.List<java.util.List<Integer>> bfsWithLevels(Graph graph, int start) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        boolean[] visited = new boolean[graph.vertices];
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        
        queue.add(start);
        visited[start] = true;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            java.util.List<Integer> level = new java.util.ArrayList<>();
            
            for (int i = 0; i < size; i++) {
                int vertex = queue.poll();
                level.add(vertex);
                
                for (int neighbor : graph.adjList.get(vertex)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.add(neighbor);
                    }
                }
            }
            
            result.add(level);
        }
        
        return result;
    }
    
    // Shortest path in unweighted graph using BFS
    public static int shortestPathBFS(Graph graph, int start, int end) {
        if (start == end) return 0;
        
        boolean[] visited = new boolean[graph.vertices];
        int[] distance = new int[graph.vertices];
        java.util.Arrays.fill(distance, -1);
        
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        queue.add(start);
        visited[start] = true;
        distance[start] = 0;
        
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            
            for (int neighbor : graph.adjList.get(vertex)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    distance[neighbor] = distance[vertex] + 1;
                    
                    if (neighbor == end) {
                        return distance[neighbor];
                    }
                    
                    queue.add(neighbor);
                }
            }
        }
        
        return -1; // Not reachable
    }
    
    // Get actual shortest path
    public static java.util.List<Integer> shortestPathWithTrace(Graph graph, int start, int end) {
        java.util.List<Integer> path = new java.util.ArrayList<>();
        
        if (start == end) {
            path.add(start);
            return path;
        }
        
        boolean[] visited = new boolean[graph.vertices];
        int[] distance = new int[graph.vertices];
        int[] parent = new int[graph.vertices];
        java.util.Arrays.fill(distance, -1);
        java.util.Arrays.fill(parent, -1);
        
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        queue.add(start);
        visited[start] = true;
        distance[start] = 0;
        
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            
            for (int neighbor : graph.adjList.get(vertex)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    distance[neighbor] = distance[vertex] + 1;
                    parent[neighbor] = vertex;
                    
                    if (neighbor == end) {
                        // Reconstruct path
                        int current = end;
                        while (current != -1) {
                            path.add(current);
                            current = parent[current];
                        }
                        java.util.Collections.reverse(path);
                        return path;
                    }
                    
                    queue.add(neighbor);
                }
            }
        }
        
        return path; // Empty if not reachable
    }
    
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        
        System.out.println("BFS from 0: " + bfs(graph, 0));
        
        System.out.println("\nBFS with levels:");
        java.util.List<java.util.List<Integer>> levels = bfsWithLevels(graph, 0);
        for (int i = 0; i < levels.size(); i++) {
            System.out.println("Level " + i + ": " + levels.get(i));
        }
        
        System.out.println("\nShortest path from 0 to 4: " + 
                          shortestPathBFS(graph, 0, 4) + " edges");
        
        System.out.println("Path: " + shortestPathWithTrace(graph, 0, 4));
    }
}
```

---

### Problem 3: DFS (Depth-First Search)

**Question:** Implement DFS traversal of a graph.

```java
/**
 * Problem: DFS Traversal of Graph
 * LeetCode: 200 (Number of Islands - similar concept)
 * 
 * Visual:
 * 
 * Graph:
 *     0 ---- 1
 *     | \    |
 *     |  \   |
 *     |   \  |
 *     2 ---- 3 ---- 4
 * 
 * DFS from 0 (recursive):
 * Visit 0
 *   Visit 1
 *     Visit 3
 *       Visit 2 (already visited 0, skip)
 *       Visit 4
 * 
 * DFS Order: 0, 1, 3, 2, 4
 * 
 * Applications:
 * - Cycle detection
 * - Topological sort
 * - Find connected components
 * - Solve mazes
 * - Path finding
 * 
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 */
public class GraphDFS {
    
    static class Graph {
        private int vertices;
        private java.util.List<java.util.List<Integer>> adjList;
        
        public Graph(int vertices) {
            this.vertices = vertices;
            adjList = new java.util.ArrayList<>();
            
            for (int i = 0; i < vertices; i++) {
                adjList.add(new java.util.ArrayList<>());
            }
        }
        
        public void addEdge(int u, int v) {
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
    }
    
    // DFS Recursive
    public static java.util.List<Integer> dfsRecursive(Graph graph, int start) {
        java.util.List<Integer> result = new java.util.ArrayList<>();
        boolean[] visited = new boolean[graph.vertices];
        
        dfsHelper(graph, start, visited, result);
        
        return result;
    }
    
    private static void dfsHelper(Graph graph, int vertex, 
                                  boolean[] visited, 
                                  java.util.List<Integer> result) {
        visited[vertex] = true;
        result.add(vertex);
        
        for (int neighbor : graph.adjList.get(vertex)) {
            if (!visited[neighbor]) {
                dfsHelper(graph, neighbor, visited, result);
            }
        }
    }
    
    // DFS Iterative (using stack)
    public static java.util.List<Integer> dfsIterative(Graph graph, int start) {
        java.util.List<Integer> result = new java.util.ArrayList<>();
        boolean[] visited = new boolean[graph.vertices];
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        
        stack.push(start);
        
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            
            if (!visited[vertex]) {
                visited[vertex] = true;
                result.add(vertex);
                
                // Push neighbors in reverse order for correct traversal
                java.util.List<Integer> neighbors = graph.adjList.get(vertex);
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    if (!visited[neighbors.get(i)]) {
                        stack.push(neighbors.get(i));
                    }
                }
            }
        }
        
        return result;
    }
    
    // DFS for all components (disconnected graph)
    public static java.util.List<java.util.List<Integer>> dfsAllComponents(Graph graph) {
        java.util.List<java.util.List<Integer>> components = new java.util.ArrayList<>();
        boolean[] visited = new boolean[graph.vertices];
        
        for (int i = 0; i < graph.vertices; i++) {
            if (!visited[i]) {
                java.util.List<Integer> component = new java.util.ArrayList<>();
                dfsComponentHelper(graph, i, visited, component);
                components.add(component);
            }
        }
        
        return components;
    }
    
    private static void dfsComponentHelper(Graph graph, int vertex, 
                                          boolean[] visited, 
                                          java.util.List<Integer> component) {
        visited[vertex] = true;
        component.add(vertex);
        
        for (int neighbor : graph.adjList.get(vertex)) {
            if (!visited[neighbor]) {
                dfsComponentHelper(graph, neighbor, visited, component);
            }
        }
    }
    
    // DFS with timing (for advanced algorithms)
    static int time;
    
    public static void dfsWithTiming(Graph graph) {
        time = 0;
        boolean[] visited = new boolean[graph.vertices];
        int[] discoveryTime = new int[graph.vertices];
        int[] finishTime = new int[graph.vertices];
        
        for (int i = 0; i < graph.vertices; i++) {
            if (!visited[i]) {
                dfsTimingHelper(graph, i, visited, discoveryTime, finishTime);
            }
        }
        
        // Display timing
        System.out.println("Vertex\tDiscovery\tFinish");
        for (int i = 0; i < graph.vertices; i++) {
            System.out.println(i + "\t" + discoveryTime[i] + "\t\t" + finishTime[i]);
        }
    }
    
    private static void dfsTimingHelper(Graph graph, int vertex, 
                                       boolean[] visited,
                                       int[] discoveryTime,
                                       int[] finishTime) {
        visited[vertex] = true;
        discoveryTime[vertex] = ++time;
        
        for (int neighbor : graph.adjList.get(vertex)) {
            if (!visited[neighbor]) {
                dfsTimingHelper(graph, neighbor, visited, discoveryTime, finishTime);
            }
        }
        
        finishTime[vertex] = ++time;
    }
    
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        
        System.out.println("DFS Recursive from 0: " + dfsRecursive(graph, 0));
        System.out.println("DFS Iterative from 0: " + dfsIterative(graph, 0));
        
        // Disconnected graph
        Graph graph2 = new Graph(6);
        graph2.addEdge(0, 1);
        graph2.addEdge(0, 2);
        graph2.addEdge(3, 4);
        // 5 is isolated
        
        System.out.println("\nAll components:");
        java.util.List<java.util.List<Integer>> components = dfsAllComponents(graph2);
        for (int i = 0; i < components.size(); i++) {
            System.out.println("Component " + i + ": " + components.get(i));
        }
        
        // DFS with timing
        System.out.println("\nDFS with timing:");
        dfsWithTiming(graph);
    }
}
```

---

### Problem 4: Detect Cycle in Undirected Graph

**Question:** Detect if an undirected graph contains a cycle.

```java
/**
 * Problem: Detect Cycle in Undirected Graph
 * LeetCode: 684 (Redundant Connection - similar)
 * 
 * Visual:
 * 
 * Graph with cycle:
 *     0 ---- 1
 *     | \  / |
 *     |  \/  |
 *     |  /\  |
 *     2 ---- 3
 * 
 * Cycle: 0-1-3-2-0
 * 
 * Approach:
 * - Use DFS
 * - For every visited vertex 'v', if there's an adjacent 'u' that is:
 *   1. Already visited and not parent of v → Cycle detected
 *   2. Not visited → Recursively call DFS for u
 * 
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 */
public class DetectCycleUndirected {
    
    static class Graph {
        private int vertices;
        private java.util.List<java.util.List<Integer>> adjList;
        
        public Graph(int vertices) {
            this.vertices = vertices;
            adjList = new java.util.ArrayList<>();
            
            for (int i = 0; i < vertices; i++) {
                adjList.add(new java.util.ArrayList<>());
            }
        }
        
        public void addEdge(int u, int v) {
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
    }
    
    // Approach 1: DFS
    public static boolean hasCycleDFS(Graph graph) {
        boolean[] visited = new boolean[graph.vertices];
        
        // Check for all components
        for (int i = 0; i < graph.vertices; i++) {
            if (!visited[i]) {
                if (hasCycleDFSHelper(graph, i, visited, -1)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private static boolean hasCycleDFSHelper(Graph graph, int vertex, 
                                             boolean[] visited, 
                                             int parent) {
        visited[vertex] = true;
        
        for (int neighbor : graph.adjList.get(vertex)) {
            if (!visited[neighbor]) {
                if (hasCycleDFSHelper(graph, neighbor, visited, vertex)) {
                    return true;
                }
            } else if (neighbor != parent) {
                // Found a visited vertex that's not parent → Cycle
                return true;
            }
        }
        
        return false;
    }
    
    // Approach 2: Union Find (Disjoint Set)
    public static boolean hasCycleUnionFind(Graph graph) {
        int[] parent = new int[graph.vertices];
        java.util.Arrays.fill(parent, -1);
        
        for (int u = 0; u < graph.vertices; u++) {
            for (int v : graph.adjList.get(u)) {
                if (u < v) { // Avoid processing same edge twice
                    int setU = find(parent, u);
                    int setV = find(parent, v);
                    
                    if (setU == setV) {
                        // Both in same set → Cycle
                        return true;
                    }
                    
                    // Union
                    union(parent, setU, setV);
                }
            }
        }
        
        return false;
    }
    
    private static int find(int[] parent, int i) {
        if (parent[i] == -1) {
            return i;
        }
        return find(parent, parent[i]);
    }
    
    private static void union(int[] parent, int x, int y) {
        parent[x] = y;
    }
    
    // Get cycle path (if exists)
    public static java.util.List<Integer> getCyclePath(Graph graph) {
        boolean[] visited = new boolean[graph.vertices];
        int[] parent = new int[graph.vertices];
        java.util.Arrays.fill(parent, -1);
        
        for (int i = 0; i < graph.vertices; i++) {
            if (!visited[i]) {
                java.util.List<Integer> cycle = new java.util.ArrayList<>();
                if (getCyclePathHelper(graph, i, visited, parent, -1, cycle)) {
                    return cycle;
                }
            }
        }
        
        return new java.util.ArrayList<>();
    }
    
    private static boolean getCyclePathHelper(Graph graph, int vertex, 
                                              boolean[] visited,
                                              int[] parent,
                                              int prev,
                                              java.util.List<Integer> cycle) {
        visited[vertex] = true;
        parent[vertex] = prev;
        
        for (int neighbor : graph.adjList.get(vertex)) {
            if (!visited[neighbor]) {
                if (getCyclePathHelper(graph, neighbor, visited, parent, vertex, cycle)) {
                    cycle.add(vertex);
                    return true;
                }
            } else if (neighbor != prev) {
                // Cycle found
                cycle.add(neighbor);
                int current = vertex;
                while (current != neighbor) {
                    cycle.add(current);
                    current = parent[current];
                }
                cycle.add(neighbor);
                return true;
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        // Graph with cycle
        Graph graph1 = new Graph(4);
        graph1.addEdge(0, 1);
        graph1.addEdge(1, 2);
        graph1.addEdge(2, 3);
        graph1.addEdge(3, 0);
        
        System.out.println("Graph 1 has cycle (DFS): " + hasCycleDFS(graph1));
        System.out.println("Graph 1 has cycle (Union Find): " + hasCycleUnionFind(graph1));
        System.out.println("Cycle path: " + getCyclePath(graph1));
        
        // Graph without cycle (tree)
        Graph graph2 = new Graph(4);
        graph2.addEdge(0, 1);
        graph2.addEdge(0, 2);
        graph2.addEdge(0, 3);
        
        System.out.println("\nGraph 2 has cycle: " + hasCycleDFS(graph2));
    }
}
```

---

### Problem 5: Detect Cycle in Directed Graph

**Question:** Detect if a directed graph contains a cycle.

```java
/**
 * Problem: Detect Cycle in Directed Graph
 * LeetCode: 207 (Course Schedule)
 * 
 * Visual:
 * 
 * Graph with cycle:
 *     0 -> 1
 *     ↓    ↓
 *     3 <- 2
 * 
 * Cycle: 1 -> 2 -> 3 -> 1
 * 
 * Approach:
 * - Use DFS with 3 colors:
 *   WHITE (0): Not visited
 *   GRAY (1): Currently in recursion stack
 *   BLACK (2): Completely processed
 * - If we encounter a GRAY vertex → Back edge → Cycle
 * 
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 */
public class DetectCycleDirected {
    
    static class Graph {
        private int vertices;
        private java.util.List<java.util.List<Integer>> adjList;
        
        public Graph(int vertices) {
            this.vertices = vertices;
            adjList = new java.util.ArrayList<>();
            
            for (int i = 0; i < vertices; i++) {
                adjList.add(new java.util.ArrayList<>());
            }
        }
        
        public void addEdge(int u, int v) {
            adjList.get(u).add(v);
        }
    }
    
    // Colors for DFS
    private static final int WHITE = 0;
    private static final int GRAY = 1;
    private static final int BLACK = 2;
    
    // Approach 1: DFS with 3 colors
    public static boolean hasCycleDFS(Graph graph) {
        int[] color = new int[graph.vertices];
        java.util.Arrays.fill(color, WHITE);
        
        for (int i = 0; i < graph.vertices; i++) {
            if (color[i] == WHITE) {
                if (hasCycleDFSHelper(graph, i, color)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private static boolean hasCycleDFSHelper(Graph graph, int vertex, int[] color) {
        // Mark current vertex as being processed (GRAY)
        color[vertex] = GRAY;
        
        for (int neighbor : graph.adjList.get(vertex)) {
            if (color[neighbor] == GRAY) {
                // Found a back edge → Cycle
                return true;
            }
            
            if (color[neighbor] == WHITE) {
                if (hasCycleDFSHelper(graph, neighbor, color)) {
                    return true;
                }
            }
        }
        
        // Mark as completely processed (BLACK)
        color[vertex] = BLACK;
        
        return false;
    }
    
    // Approach 2: DFS with recursion stack
    public static boolean hasCycleRecursionStack(Graph graph) {
        boolean[] visited = new boolean[graph.vertices];
        boolean[] recStack = new boolean[graph.vertices];
        
        for (int i = 0; i < graph.vertices; i++) {
            if (hasCycleRecStackHelper(graph, i, visited, recStack)) {
                return true;
            }
        }
        
        return false;
    }
    
    private static boolean hasCycleRecStackHelper(Graph graph, int vertex, 
                                                  boolean[] visited, 
                                                  boolean[] recStack) {
        if (recStack[vertex]) {
            return true;
        }
        
        if (visited[vertex]) {
            return false;
        }
        
        visited[vertex] = true;
        recStack[vertex] = true;
        
        for (int neighbor : graph.adjList.get(vertex)) {
            if (hasCycleRecStackHelper(graph, neighbor, visited, recStack)) {
                return true;
            }
        }
        
        recStack[vertex] = false;
        
        return false;
    }
    
    // Get cycle path
    public static java.util.List<Integer> getCyclePath(Graph graph) {
        int[] color = new int[graph.vertices];
        java.util.Arrays.fill(color, WHITE);
        int[] parent = new int[graph.vertices];
        java.util.Arrays.fill(parent, -1);
        
        for (int i = 0; i < graph.vertices; i++) {
            if (color[i] == WHITE) {
                java.util.List<Integer> cycle = new java.util.ArrayList<>();
                if (getCyclePathHelper(graph, i, color, parent, cycle)) {
                    return cycle;
                }
            }
        }
        
        return new java.util.ArrayList<>();
    }
    
    private static boolean getCyclePathHelper(Graph graph, int vertex, 
                                              int[] color,
                                              int[] parent,
                                              java.util.List<Integer> cycle) {
        color[vertex] = GRAY;
        
        for (int neighbor : graph.adjList.get(vertex)) {
            if (color[neighbor] == GRAY) {
                // Cycle found
                cycle.add(neighbor);
                int current = vertex;
                while (current != neighbor && current != -1) {
                    cycle.add(current);
                    current = parent[current];
                }
                cycle.add(neighbor);
                java.util.Collections.reverse(cycle);
                return true;
            }
            
            if (color[neighbor] == WHITE) {
                parent[neighbor] = vertex;
                if (getCyclePathHelper(graph, neighbor, color, parent, cycle)) {
                    return true;
                }
            }
        }
        
        color[vertex] = BLACK;
        
        return false;
    }
    
    public static void main(String[] args) {
        // Graph with cycle
        Graph graph1 = new Graph(4);
        graph1.addEdge(0, 1);
        graph1.addEdge(1, 2);
        graph1.addEdge(2, 3);
        graph1.addEdge(3, 1);
        
        System.out.println("Graph 1 has cycle (3-color DFS): " + hasCycleDFS(graph1));
        System.out.println("Graph 1 has cycle (Recursion Stack): " + 
                          hasCycleRecursionStack(graph1));
        System.out.println("Cycle path: " + getCyclePath(graph1));
        
        // Graph without cycle (DAG)
        Graph graph2 = new Graph(4);
        graph2.addEdge(0, 1);
        graph2.addEdge(0, 2);
        graph2.addEdge(1, 3);
        graph2.addEdge(2, 3);
        
        System.out.println("\nGraph 2 has cycle: " + hasCycleDFS(graph2));
    }
}
```

---

## 📝 Practice Problems (Part 1)

### Easy
| Problem | Pattern | Link |
|---------|---------|------|
| Number of Islands | DFS/BFS | LeetCode 200 |
| Flood Fill | DFS/BFS | LeetCode 733 |
| Find if Path Exists | BFS/DFS | LeetCode 1971 |

### Medium
| Problem | Pattern | Link |
|---------|---------|------|
| Course Schedule | Cycle Detection (DAG) | LeetCode 207 |
| Course Schedule II | Topological Sort | LeetCode 210 |
| Clone Graph | BFS/DFS | LeetCode 133 |
| Cheapest Flights Within K Stops | BFS/Dijkstra | LeetCode 787 |

### Hard
| Problem | Pattern | Link |
|---------|---------|------|
| Word Ladder | BFS | LeetCode 127 |
| Minimum Height Trees | BFS (Topological) | LeetCode 310 |

---

**Note:** Graphs ka content bahut bada hai, isliye **Part 2** mein continue karenge with:
- Topological Sort
- Dijkstra's Algorithm
- Bellman-Ford
- Floyd-Warshall
- Minimum Spanning Tree (Kruskal's & Prim's)
- More advanced problems

**Part 2 continue karein?** [1][2][6][14]