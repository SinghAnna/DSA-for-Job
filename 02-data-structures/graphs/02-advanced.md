# 🕸️ Graphs - Advanced Algorithms (Part 2)

## 📚 Theory

### Topological Sort

**Definition:** Linear ordering of vertices in a DAG such that for every edge (u, v), vertex u comes before v in the ordering.

# 🧭 Directed Acyclic Graph (DAG) & Topological Sorting

A **Directed Acyclic Graph (DAG)** is a directed graph with no directed cycles. In a DAG, it is impossible to start at any vertex $u$ and follow a sequence of directed edges that loops back to $u$.

---

## 📌 DAG Example

Consider the following 5-node directed graph:

```text
    0 --------> 1 --------> 3
    |                       |
    |                       |
    v                       v
    2 --------------------> 4

```

Topological Sorts:
-

-

-

etc.

- Applications:

- Task scheduling

- Course prerequisites

- Build systems

- Dependency resolution


# ⚡ Dijkstra's Algorithm — Step-by-Step Execution

Dijkstra's Algorithm finds the shortest path from a single source vertex to all other vertices in a weighted graph with non-negative edge weights.

---

## 📌 Graph Diagram

An undirected weighted graph with 4 vertices ($V = \{0, 1, 2, 3\}$):

```text
       0 ------------(5)------------ 1
       |                            /|
       |                          /  |
      (2)                       (1) (3)
       |                      /      |
       |                    /        |
       2 ------------(4)------------ 3
```


### Minimum Spanning Tree (MST)

**Definition:** Subset of edges that connects all vertices with minimum total weight and no cycles.


# 🌲 Kruskal's Algorithm — Minimum Spanning Tree (MST)

**Kruskal's Algorithm** is a greedy algorithm that finds a Minimum Spanning Tree for a connected, weighted, undirected graph. It selects edges in increasing order of weight, skipping any edge that forms a cycle (managed using a **Disjoint Set Union / DSU** data structure).

---


```text
       0 ------------(2)------------ 1
       |  \                          |
       |    \                        |
      (4)     (3)                   (1)
       |          \                  |
       |            \                |
       2 ------------(1)------------ 3

```


---

## 💻 Java Code Examples

### Problem 6: Topological Sort

**Question:** Given a DAG, return any valid topological ordering of vertices.

```java
/**
 * Problem: Topological Sort
 * LeetCode: 210 (Course Schedule II)
 * 
 * Visual:
 * 
 * Courses with prerequisites:
 *     0
 *    / \
 *   1   2
 *    \ /
 *     3
 * 
 * 0 → 1 (0 is prerequisite for 1)
 * 0 → 2
 * 1 → 3
 * 2 → 3
 * 
 * Topological Order:  or[0][1][2][3]
 * 
 * Approach 1: DFS with Stack
 * - Perform DFS
 * - Add vertex to stack after processing all neighbors
 * - Pop from stack for result
 * 
 * Approach 2: Kahn's Algorithm (BFS)
 * - Calculate in-degree of all vertices
 * - Add vertices with in-degree 0 to queue
 * - Process queue, reduce in-degree of neighbors
 * - Add neighbors with in-degree 0 to queue
 * 
 * Time Complexity: O(V + E)
 * Space Complexity: O(V + E)
 */
public class TopologicalSort {
    
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
    
    // Approach 1: DFS with Stack
    public static java.util.List<Integer> topologicalSortDFS(Graph graph) {
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        boolean[] visited = new boolean[graph.vertices];
        
        for (int i = 0; i < graph.vertices; i++) {
            if (!visited[i]) {
                topologicalSortDFSHelper(graph, i, visited, stack);
            }
        }
        
        java.util.List<Integer> result = new java.util.ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        
        return result;
    }
    
    private static void topologicalSortDFSHelper(Graph graph, int vertex, 
                                                 boolean[] visited, 
                                                 java.util.Stack<Integer> stack) {
        visited[vertex] = true;
        
        for (int neighbor : graph.adjList.get(vertex)) {
            if (!visited[neighbor]) {
                topologicalSortDFSHelper(graph, neighbor, visited, stack);
            }
        }
        
        // Add to stack after processing all neighbors
        stack.push(vertex);
    }
    
    // Approach 2: Kahn's Algorithm (BFS)
    public static java.util.List<Integer> topologicalSortKahn(Graph graph) {
        java.util.List<Integer> result = new java.util.ArrayList<>();
        
        // Calculate in-degree
        int[] inDegree = new int[graph.vertices];
        for (int u = 0; u < graph.vertices; u++) {
            for (int v : graph.adjList.get(u)) {
                inDegree[v]++;
            }
        }
        
        // Add vertices with in-degree 0 to queue
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        for (int i = 0; i < graph.vertices; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        
        // Process queue
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result.add(vertex);
            
            for (int neighbor : graph.adjList.get(vertex)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }
        
        // Check if all vertices processed (no cycle)
        if (result.size() != graph.vertices) {
            System.out.println("Graph has cycle, topological sort not possible");
            return new java.util.ArrayList<>();
        }
        
        return result;
    }
    
    // Check if topological sort is possible (no cycle)
    public static boolean canFinish(Graph graph) {
        int[] inDegree = new int[graph.vertices];
        
        for (int u = 0; u < graph.vertices; u++) {
            for (int v : graph.adjList.get(u)) {
                inDegree[v]++;
            }
        }
        
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        for (int i = 0; i < graph.vertices; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        
        int count = 0;
        
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            count++;
            
            for (int neighbor : graph.adjList.get(vertex)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }
        
        return count == graph.vertices;
    }
    
    public static void main(String[] args) {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        
        System.out.println("Topological Sort (DFS): " + topologicalSortDFS(graph));
        System.out.println("Topological Sort (Kahn): " + topologicalSortKahn(graph));
        System.out.println("Can finish: " + canFinish(graph));
        
        // Graph with cycle
        Graph graphCycle = new Graph(3);
        graphCycle.addEdge(0, 1);
        graphCycle.addEdge(1, 2);
        graphCycle.addEdge(2, 0);
        
        System.out.println("\nGraph with cycle can finish: " + canFinish(graphCycle));
    }
}
```

---

### Problem 7: Dijkstra's Algorithm

**Question:** Find shortest path from source vertex to all other vertices in a weighted graph.

```java
/**
 * Problem: Dijkstra's Algorithm
 * LeetCode: 743 (Network Delay Time - similar)
 * 
 * Visual:
 * 
 * Weighted Graph:
 *       5       1
 *   0 ----- 1 ----- 3
 *   | \     |     /
 *   2   \   3   1
 *   |     \ | /
 *   2 ----- 2
 *       4
 * 
 * Edges:
 * 0-1: 5, 0-2: 2, 0-3: ∞
 * 1-2: 3, 1-3: 1
 * 2-3: 4
 * 
 * Dijkstra from 0:
 * 
 * Initial: dist=[0,∞,∞,∞], pq=[(0,0)]
 * 
 * Step 1: Pop (0,0)
 *         Update 1: dist=5, pq=[(5,1)][1]
 *         Update 2: dist=2, pq=[(2,2),(5,1)][2]
 * 
 * Step 2: Pop (2,2)
 *         Update 1: dist=min(5,2+3)=5, no change[1]
 *         Update 3: dist=2+4=6, pq=[(5,1),(6,3)][3]
 * 
 * Step 3: Pop (5,1)
 *         Update 3: dist=min(6,5+1)=6, pq=[(6,3)][3]
 * 
 * Step 4: Pop (6,3)
 *         No updates
 * 
 * Final: dist=[0][2][5][6]
 * 
 * Time Complexity: O((V + E) log V)
 * Space Complexity: O(V + E)
 */
public class DijkstraAlgorithm {
    
    static class Edge {
        int to;
        int weight;
        
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    
    static class Graph {
        private int vertices;
        private java.util.List<java.util.List<Edge>> adjList;
        
        public Graph(int vertices) {
            this.vertices = vertices;
            adjList = new java.util.ArrayList<>();
            
            for (int i = 0; i < vertices; i++) {
                adjList.add(new java.util.ArrayList<>());
            }
        }
        
        public void addEdge(int u, int v, int weight) {
            adjList.get(u).add(new Edge(v, weight));
            adjList.get(v).add(new Edge(u, weight)); // For undirected
        }
        
        public void addDirectedEdge(int u, int v, int weight) {
            adjList.get(u).add(new Edge(v, weight));
        }
    }
    
    static class Node implements Comparable<Node> {
        int vertex;
        int distance;
        
        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
        
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
    
    // Dijkstra's Algorithm
    public static int[] dijkstra(Graph graph, int source) {
        int[] dist = new int[graph.vertices];
        java.util.Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        
        java.util.PriorityQueue<Node> pq = new java.util.PriorityQueue<>();
        pq.add(new Node(source, 0));
        
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;
            
            // Skip if we found better path already
            if (current.distance > dist[u]) {
                continue;
            }
            
            // Update distances to neighbors
            for (Edge edge : graph.adjList.get(u)) {
                int v = edge.to;
                int newDist = dist[u] + edge.weight;
                
                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    pq.add(new Node(v, newDist));
                }
            }
        }
        
        return dist;
    }
    
    // Dijkstra with path reconstruction
    public static class DijkstraResult {
        int[] distances;
        int[] parents;
        
        DijkstraResult(int[] distances, int[] parents) {
            this.distances = distances;
            this.parents = parents;
        }
        
        public java.util.List<Integer> getPath(int target) {
            java.util.List<Integer> path = new java.util.ArrayList<>();
            
            if (distances[target] == Integer.MAX_VALUE) {
                return path; // Not reachable
            }
            
            int current = target;
            while (current != -1) {
                path.add(current);
                current = parents[current];
            }
            
            java.util.Collections.reverse(path);
            return path;
        }
    }
    
    public static DijkstraResult dijkstraWithPath(Graph graph, int source) {
        int[] dist = new int[graph.vertices];
        int[] parent = new int[graph.vertices];
        java.util.Arrays.fill(dist, Integer.MAX_VALUE);
        java.util.Arrays.fill(parent, -1);
        dist[source] = 0;
        
        java.util.PriorityQueue<Node> pq = new java.util.PriorityQueue<>();
        pq.add(new Node(source, 0));
        
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;
            
            if (current.distance > dist[u]) {
                continue;
            }
            
            for (Edge edge : graph.adjList.get(u)) {
                int v = edge.to;
                int newDist = dist[u] + edge.weight;
                
                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    parent[v] = u;
                    pq.add(new Node(v, newDist));
                }
            }
        }
        
        return new DijkstraResult(dist, parent);
    }
    
    // Network Delay Time (LeetCode 743)
    public static int networkDelayTime(int[][] times, int n, int k) {
        Graph graph = new Graph(n + 1); // 1-indexed
        
        for (int[] time : times) {
            graph.addDirectedEdge(time, time, time);[1][2]
        }
        
        int[] dist = dijkstra(graph, k);
        
        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1; // Not all reachable
            }
            maxTime = Math.max(maxTime, dist[i]);
        }
        
        return maxTime;
    }
    
    public static void main(String[] args) {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 5);
        graph.addEdge(0, 2, 2);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 3, 4);
        
        int source = 0;
        int[] distances = dijkstra(graph, source);
        
        System.out.println("Shortest distances from vertex " + source + ":");
        for (int i = 0; i < distances.length; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                System.out.println("To " + i + ": Not reachable");
            } else {
                System.out.println("To " + i + ": " + distances[i]);
            }
        }
        
        // With path
        DijkstraResult result = dijkstraWithPath(graph, source);
        System.out.println("\nPath to vertex 3: " + result.getPath(3));
        
        // Network delay time
        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
        int n = 4, k = 2;
        System.out.println("\nNetwork delay time: " + networkDelayTime(times, n, k));
    }
}
```

---

### Problem 8: Bellman-Ford Algorithm

**Question:** Find shortest paths from source vertex in a weighted graph (handles negative weights).

```java
/**
 * Problem: Bellman-Ford Algorithm
 * 
 * Visual:
 * 
 * Graph with negative weight:
 *       4
 *   0 -----> 1
 *   | \      |
 *   |  \     | 1
 *   2   -1   ↓
 *   |        2
 *   |________|
 *      -2
 * 
 * Bellman-Ford handles negative weights!
 * 
 * Algorithm:
 * 1. Initialize distances: dist[source]=0, others=∞
 * 2. Relax all edges V-1 times
 * 3. Check for negative cycle (optional)
 * 
 * Relaxation:
 * if dist[u] + weight(u,v) < dist[v]:
 *     dist[v] = dist[u] + weight(u,v)
 * 
 * Time Complexity: O(V × E)
 * Space Complexity: O(V)
 */
public class BellmanFordAlgorithm {
    
    static class Edge {
        int from;
        int to;
        int weight;
        
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
    
    static class Graph {
        private int vertices;
        private java.util.List<Edge> edges;
        
        public Graph(int vertices) {
            this.vertices = vertices;
            edges = new java.util.ArrayList<>();
        }
        
        public void addEdge(int from, int to, int weight) {
            edges.add(new Edge(from, to, weight));
        }
    }
    
    // Bellman-Ford Algorithm
    public static int[] bellmanFord(Graph graph, int source) {
        int[] dist = new int[graph.vertices];
        java.util.Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        
        // Relax all edges V-1 times
        for (int i = 0; i < graph.vertices - 1; i++) {
            for (Edge edge : graph.edges) {
                if (dist[edge.from] != Integer.MAX_VALUE &&
                    dist[edge.from] + edge.weight < dist[edge.to]) {
                    dist[edge.to] = dist[edge.from] + edge.weight;
                }
            }
        }
        
        return dist;
    }
    
    // Bellman-Ford with negative cycle detection
    public static class BellmanFordResult {
        int[] distances;
        boolean hasNegativeCycle;
        
        BellmanFordResult(int[] distances, boolean hasNegativeCycle) {
            this.distances = distances;
            this.hasNegativeCycle = hasNegativeCycle;
        }
    }
    
    public static BellmanFordResult bellmanFordWithDetection(Graph graph, int source) {
        int[] dist = new int[graph.vertices];
        java.util.Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        
        // Relax V-1 times
        for (int i = 0; i < graph.vertices - 1; i++) {
            for (Edge edge : graph.edges) {
                if (dist[edge.from] != Integer.MAX_VALUE &&
                    dist[edge.from] + edge.weight < dist[edge.to]) {
                    dist[edge.to] = dist[edge.from] + edge.weight;
                }
            }
        }
        
        // Check for negative cycle
        boolean hasNegativeCycle = false;
        for (Edge edge : graph.edges) {
            if (dist[edge.from] != Integer.MAX_VALUE &&
                dist[edge.from] + edge.weight < dist[edge.to]) {
                hasNegativeCycle = true;
                break;
            }
        }
        
        return new BellmanFordResult(dist, hasNegativeCycle);
    }
    
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1, -1);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 4, 2);
        graph.addEdge(3, 2, 5);
        graph.addEdge(3, 1, 1);
        graph.addEdge(4, 3, -3);
        
        int source = 0;
        BellmanFordResult result = bellmanFordWithDetection(graph, source);
        
        if (result.hasNegativeCycle) {
            System.out.println("Graph contains negative weight cycle!");
        } else {
            System.out.println("Shortest distances from vertex " + source + ":");
            for (int i = 0; i < result.distances.length; i++) {
                if (result.distances[i] == Integer.MAX_VALUE) {
                    System.out.println("To " + i + ": Not reachable");
                } else {
                    System.out.println("To " + i + ": " + result.distances[i]);
                }
            }
        }
    }
}
```

---

### Problem 9: Floyd-Warshall Algorithm

**Question:** Find shortest paths between all pairs of vertices.

```java
/**
 * Problem: Floyd-Warshall Algorithm
 * 
 * Visual:
 * 
 * Graph:
 *       3
 *   0 -----> 1
 *   | \      |
 *   |  \     | 2
 *   6   4    ↓
 *   |        2
 *   |________|
 *      5
 * 
 * All pairs shortest paths
 * 
 * Algorithm:
 * For each intermediate vertex k:
 *   For each pair (i, j):
 *     dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])
 * 
 * Time Complexity: O(V³)
 * Space Complexity: O(V²)
 */
public class FloydWarshallAlgorithm {
    
    public static int[][] floydWarshall(int[][] graph) {
        int V = graph.length;
        int[][] dist = new int[V][V];
        
        // Initialize distance matrix
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else if (graph[i][j] == 0) {
                    dist[i][j] = Integer.MAX_VALUE;
                } else {
                    dist[i][j] = graph[i][j];
                }
            }
        }
        
        // Floyd-Warshall
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE &&
                        dist[k][j] != Integer.MAX_VALUE &&
                        dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        
        return dist;
    }
    
    // Print distance matrix
    public static void printMatrix(int[][] dist) {
        System.out.println("Shortest distances between all pairs:");
        int V = dist.length;
        
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == Integer.MAX_VALUE) {
                    System.out.print("∞  ");
                } else {
                    System.out.printf("%d  ", dist[i][j]);
                }
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        // Adjacency matrix (0 means no edge)
        int[][] graph = {
            {0, 3, 0, 6, 0},
            {3, 0, 2, 0, 0},
            {0, 0, 0, 4, 0},
            {0, 0, 0, 0, 1},
            {0, 0, 5, 1, 0}
        };
        
        int[][] dist = floydWarshall(graph);
        printMatrix(dist);
    }
}
```

---

### Problem 10: Minimum Spanning Tree - Kruskal's Algorithm

**Question:** Find MST of a weighted undirected graph using Kruskal's algorithm.

```java
/**
 * Problem: Minimum Spanning Tree - Kruskal's Algorithm
 * LeetCode: 1135 (Connecting Cities - similar)
 * 
 * Visual:
 * 
 * Graph:
 *       4
 *   0 ----- 1
 *   | \     |
 *   2   \   | 3
 *   |     \ |
 *   2 ----- 3
 *       1
 * 
 * Edges sorted by weight:
 * (2,3): 1
 * (0,2): 2
 * (0,3): 4
 * (1,3): 3
 * (0,1): 4
 * 
 * Kruskal's:
 * 1. Add (2,3): weight=1
 * 2. Add (0,2): weight=2
 * 3. Skip (0,3): creates cycle
 * 4. Add (1,3): weight=3
 * 
 * MST weight: 1 + 2 + 3 = 6
 * 
 * Time Complexity: O(E log E) or O(E log V)
 * Space Complexity: O(V + E)
 */
public class KruskalsAlgorithm {
    
    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;
        
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }
    
    static class Graph {
        private int vertices;
        private java.util.List<Edge> edges;
        
        public Graph(int vertices) {
            this.vertices = vertices;
            edges = new java.util.ArrayList<>();
        }
        
        public void addEdge(int from, int to, int weight) {
            edges.add(new Edge(from, to, weight));
        }
    }
    
    // Union Find (Disjoint Set) with path compression
    static class UnionFind {
        private int[] parent;
        private int[] rank;
        
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }
        
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }
        
        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            if (rootX == rootY) {
                return false; // Already in same set
            }
            
            // Union by rank
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
    
    // Kruskal's Algorithm
    public static class MSTResult {
        java.util.List<Edge> mstEdges;
        int totalWeight;
        
        MSTResult(java.util.List<Edge> mstEdges, int totalWeight) {
            this.mstEdges = mstEdges;
            this.totalWeight = totalWeight;
        }
    }
    
    public static MSTResult kruskal(Graph graph) {
        java.util.List<Edge> mstEdges = new java.util.ArrayList<>();
        int totalWeight = 0;
        
        // Sort edges by weight
        java.util.Collections.sort(graph.edges);
        
        // Union Find for cycle detection
        UnionFind uf = new UnionFind(graph.vertices);
        
        // Process edges
        for (Edge edge : graph.edges) {
            if (uf.union(edge.from, edge.to)) {
                mstEdges.add(edge);
                totalWeight += edge.weight;
                
                // MST has V-1 edges
                if (mstEdges.size() == graph.vertices - 1) {
                    break;
                }
            }
        }
        
        return new MSTResult(mstEdges, totalWeight);
    }
    
    public static void main(String[] args) {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);
        
        MSTResult result = kruskal(graph);
        
        System.out.println("MST Edges:");
        for (Edge edge : result.mstEdges) {
            System.out.println(edge.from + " -- " + edge.to + " : " + edge.weight);
        }
        
        System.out.println("Total MST Weight: " + result.totalWeight);
    }
}
```

---

### Problem 11: Minimum Spanning Tree - Prim's Algorithm

**Question:** Find MST using Prim's algorithm.

```java
/**
 * Problem: Minimum Spanning Tree - Prim's Algorithm
 * 
 * Visual:
 * 
 * Graph:
 *       4
 *   0 ----- 1
 *   | \     |
 *   2   \   | 3
 *   |     \ |
 *   2 ----- 3
 *       1
 * 
 * Prim's from vertex 0:
 * 
 * Step 0: MST={}, key=[0,∞,∞,∞], pq=[(0,0)]
 * 
 * Step 1: Extract 0
 *         MST={0}
 *         Update neighbors: key=[0][2][4][5]
 *         pq=[(2,2),(4,1),(5,3)]
 * 
 * Step 2: Extract 2
 *         MST={0,2}
 *         Update neighbors: key=[0][1][2][4]
 *         pq=[(1,3),(4,1)]
 * 
 * Step 3: Extract 3
 *         MST={0,2,3}
 *         Update neighbors: key=[0][1][2][3]
 *         pq=[(3,1)]
 * 
 * Step 4: Extract 1
 *         MST={0,2,3,1}
 * 
 * Total weight: 2 + 1 + 3 = 6
 * 
 * Time Complexity: O((V + E) log V)
 * Space Complexity: O(V + E)
 */
public class PrimsAlgorithm {
    
    static class Edge {
        int to;
        int weight;
        
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    
    static class Graph {
        private int vertices;
        private java.util.List<java.util.List<Edge>> adjList;
        
        public Graph(int vertices) {
            this.vertices = vertices;
            adjList = new java.util.ArrayList<>();
            
            for (int i = 0; i < vertices; i++) {
                adjList.add(new java.util.ArrayList<>());
            }
        }
        
        public void addEdge(int u, int v, int weight) {
            adjList.get(u).add(new Edge(v, weight));
            adjList.get(v).add(new Edge(u, weight));
        }
    }
    
    static class Node implements Comparable<Node> {
        int vertex;
        int key;
        
        Node(int vertex, int key) {
            this.vertex = vertex;
            this.key = key;
        }
        
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.key, other.key);
        }
    }
    
    public static class MSTResult {
        java.util.List<String> mstEdges;
        int totalWeight;
        
        MSTResult(java.util.List<String> mstEdges, int totalWeight) {
            this.mstEdges = mstEdges;
            this.totalWeight = totalWeight;
        }
    }
    
    public static MSTResult prim(Graph graph, int start) {
        java.util.List<String> mstEdges = new java.util.ArrayList<>();
        int totalWeight = 0;
        
        int[] key = new int[graph.vertices];
        int[] parent = new int[graph.vertices];
        boolean[] inMST = new boolean[graph.vertices];
        
        java.util.Arrays.fill(key, Integer.MAX_VALUE);
        java.util.Arrays.fill(parent, -1);
        
        key[start] = 0;
        
        java.util.PriorityQueue<Node> pq = new java.util.PriorityQueue<>();
        pq.add(new Node(start, 0));
        
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.vertex;
            
            if (inMST[u]) {
                continue;
            }
            
            inMST[u] = true;
            
            if (parent[u] != -1) {
                mstEdges.add(parent[u] + " -- " + u + " : " + key[u]);
                totalWeight += key[u];
            }
            
            for (Edge edge : graph.adjList.get(u)) {
                int v = edge.to;
                int weight = edge.weight;
                
                if (!inMST[v] && weight < key[v]) {
                    key[v] = weight;
                    parent[v] = u;
                    pq.add(new Node(v, key[v]));
                }
            }
        }
        
        return new MSTResult(mstEdges, totalWeight);
    }
    
    public static void main(String[] args) {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);
        
        MSTResult result = prim(graph, 0);
        
        System.out.println("MST Edges:");
        for (String edge : result.mstEdges) {
            System.out.println(edge);
        }
        
        System.out.println("Total MST Weight: " + result.totalWeight);
    }
}
```

---

## 📝 Practice Problems (Part 2)

### Medium
| Problem | Pattern | Link |
|---------|---------|------|
| Network Delay Time | Dijkstra | LeetCode 743 |
| Cheapest Flights Within K Stops | Bellman-Ford/BFS | LeetCode 787 |
| Evaluate Division | Graph BFS/DFS | LeetCode 399 |
| Reconstruct Itinerary | Eulerian Path | LeetCode 332 |

### Hard
| Problem | Pattern | Link |
|---------|---------|------|
| Word Ladder II | BFS + Backtracking | LeetCode 126 |
| Min Cost to Connect All Points | MST (Prim's) | LeetCode 1584 |
| Swim in Rising Water | Dijkstra/Binary Search | LeetCode 778 |
| Path With Maximum Probability | Dijkstra (Modified) | LeetCode 1514 |

---

## ✅ Key Takeaways

1. **BFS** - Shortest path in unweighted graphs
2. **DFS** - Cycle detection, topological sort, path finding
3. **Dijkstra** - Shortest path with non-negative weights
4. **Bellman-Ford** - Handles negative weights, detects negative cycles
5. **Floyd-Warshall** - All pairs shortest path
6. **Kruskal's** - MST using Union Find, sort edges
7. **Prim's** - MST using priority queue
8. **Topological Sort** - Only for DAGs, task scheduling

---

**Previous:** [Graphs Part 1](./01-theory.md)  
**Next:** [Dynamic Programming](../dynamic-programming/01-theory.md)