import java.util.ArrayList;
import java.util.List;

class MyGraph {
    int V;
    List<List<Integer>> adjList;
    private int timer;

    public MyGraph(int V) {
        this.V = V;
        adjList = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    void addEdge(int u, int v) {

        if (!adjList.get(u).contains(v)) {
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
    }

    void dfsWithTiming() {
        boolean[] visited = new boolean[V];
        int[] discoveryTime = new int[V];
        int[] finishTime = new int[V];
        timer = 0;

        for (int vertex = 0; vertex < V; vertex++) {
            if (!visited[vertex]) {
                dfs(vertex, visited, discoveryTime, finishTime);
            }
        }

        for (int vertex = 0; vertex < V; vertex++) {
            System.out.println(vertex + ": discovery=" + discoveryTime[vertex]
                    + ", finish=" + finishTime[vertex]);
        }
    }

    private void dfs(int vertex, boolean[] visited, int[] discoveryTime, int[] finishTime) {
        visited[vertex] = true;
        discoveryTime[vertex] = ++timer;

        for (int neighbor : adjList.get(vertex)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited, discoveryTime, finishTime);
            }
        }

        finishTime[vertex] = ++timer;
    }
}

public class DFSWithTiming {
    public static void main(String[] args) {
        MyGraph graph = new MyGraph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.dfsWithTiming();
    }
}
