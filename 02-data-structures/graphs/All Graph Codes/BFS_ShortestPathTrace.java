import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS_ShortestPathTrace {
    
    static class Graph{

        int vertices;
        List<List<Integer>> adjList;

        public  Graph(int vertices){
            this.vertices = vertices;
            adjList = new ArrayList<>();

            for(int i = 0; i < vertices; i++){
                adjList.add(new ArrayList<>());
            }
        }

        public void addEdge(int u, int v){
            if (!adjList.get(u).contains(v)) {
                adjList.get(u).add(v);
                adjList.get(v).add(u);
            }
        }

      public  List<Integer> TraceShortestPath(Graph graph, int start, int end){

            List<Integer> path = new ArrayList<>();

            if (start == end) {
                path.add(start);
                return path;
            }

          int[]  parent = new int[graph.vertices];
          boolean[] visited = new boolean[graph.vertices];
          int[] distance = new int[graph.vertices];

          Arrays.fill(distance, -1);
          Arrays.fill(parent, -1);
          
          Queue<Integer> queue = new LinkedList<>();


          visited[start] = true;
          distance[start] = 0;

          queue.offer(start);

          while (!queue.isEmpty()) {
            int vertex = queue.poll();

            for (int neig : adjList.get(vertex)) {
                
                if (!visited[neig]) {
                    visited[neig] = true;
                    distance[neig] = distance[vertex] + 1;

                    parent[neig] = vertex;

                    if (neig == end) {
                        int current = end;

                        while (current != -1) {
                            path.add(current);
                            current = parent[current];
                        }

                        Collections.reverse(path);
                    }
                    queue.offer(neig);
                }
            }
          }
          

            return  path;
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

        List<Integer> res = graph.TraceShortestPath(graph, 0, 6);

        System.out.println(res);
    }
}
