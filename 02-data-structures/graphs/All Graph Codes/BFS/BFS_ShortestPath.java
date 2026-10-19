import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS_ShortestPath {

    static class Graph{
        int vertices;
        List<List<Integer>> adjList;

        public Graph(int vertices){
            this.vertices = vertices;
            adjList = new ArrayList<>();

            for(int i = 0; i < vertices; i++){
                adjList.add(new ArrayList<>());
            }
        }


     public void addEdge(int u ,int v){
        if (!adjList.get(u).contains(v)) {
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
     }

     public int bfsSortestPath(Graph graph, int start, int end){

        if (start == end) {
            return 0;
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.vertices];
        int[] distence = new int[graph.vertices];

        Arrays.fill(distence, 0);

        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            for (int nei : adjList.get(vertex)) {
                
                if (!visited[nei]) {
                    visited[nei] = true;
                    distence[nei] = distence[vertex] + 1;

                    if (nei == end) {
                    return distence[nei];
                  }

                queue.offer(nei);

                }

                
            }
        }


        return  -1;
     }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0,3);
        graph.addEdge(1, 5);
        graph.addEdge(2, 4);
        graph.addEdge(3, 6);
        graph.addEdge(5, 7);
        graph.addEdge(4, 6);
        graph.addEdge(7, 6);

        int res = graph.bfsSortestPath(graph, 0, 6);

        System.out.println(res);
        
    }
}
