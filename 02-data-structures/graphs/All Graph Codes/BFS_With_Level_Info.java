import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS_With_Level_Info {

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


        public void addEdge(int u, int v){

            if(!adjList.get(u).contains(v)){
                adjList.get(u).add(v);
                adjList.get(v).add(u);

            }
        }

         List<List<Integer>> bfsLevelInfo(Graph graph, int start){

        List<List<Integer>> result = new ArrayList<>();

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.vertices];
        

        visited[start] = true;
        queue.offer(start);

        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for(int i = 0; i < size; i++){
                int vertex = queue.poll();
                level.add(vertex);

                for (int neigh : adjList.get(vertex)) {
                    
                    if (!visited[neigh]) {
                        visited[neigh] = true;
                        queue.add(neigh);
                    }
                }
            }

            result.add(level);
       
        }


        return  result;

    }


    }

   
    public static void main(String[] args) {
        Graph graph = new Graph(4);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(3, 2);
         graph.addEdge(2, 3);

         List<List<Integer>> result = graph.bfsLevelInfo(graph, 0);

         System.out.println(result);
    }
}
