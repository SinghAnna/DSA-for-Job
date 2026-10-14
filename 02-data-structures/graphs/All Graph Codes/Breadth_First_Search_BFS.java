import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Breadth_First_Search_BFS {

    static  class Graph{
        int vertices;
        List<List<Integer>> adjList;

        public Graph(int vertices){
            this.vertices = vertices;
            adjList = new ArrayList<>();

            for(int i = 0; i < vertices; i++){
                adjList.add(new ArrayList<>());
            }
        }


        public void addEdge(int u, int v){
            
            if(!adjList.get(u).contains(u)){
                adjList.get(u).add(v);
                adjList.get(v).add(u);
            }

        }

        List<Integer> bfs(Graph graph,int start){

          List<Integer> result = new ArrayList<>();

          Queue<Integer> queue = new LinkedList<>();

          boolean[] visited = new boolean[graph.vertices];

          visited[start] = true;
          queue.offer(start);

          while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);

            for(int nei : adjList.get(node)){
               if(!visited[nei]) {
                visited[nei] = true;
                queue.add(nei);
               }
            }
          }


          return result; 
 
        }


    }

    public static void main(String[] args) {
        Graph graph = new Graph(4);

          graph.addEdge(0, 1);
        graph.addEdge(0,2);
        graph.addEdge(1,2 );
        graph.addEdge(1, 3);
        graph.addEdge(3, 1);

        List<Integer> result = graph.bfs(graph, 0);

        System.out.println(result);
    }
}
