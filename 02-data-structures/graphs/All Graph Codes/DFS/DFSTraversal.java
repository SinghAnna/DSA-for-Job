import java.util.ArrayList;
import java.util.List;

class Graph{

    int V;
    List<List<Integer>> adjList;

    public Graph(int V){
        this.V = V;
        adjList = new ArrayList<>();

        for(int i = 0; i < V; i++){
            adjList.add(new ArrayList<>());
        }
    }

    void addEdge(int u, int v){

        if (!adjList.get(u).contains(v)) {
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
    }

    void dfs(Graph graph, int start){
        boolean[] visited = new boolean[graph.V];

        dfsHelper(graph, start,visited);


    }

    void dfsHelper(Graph graph, int start, boolean[] visited){

        visited[start] = true;
        System.out.print(start + " ");

        for(int neigh : graph.adjList.get(start)){
            if(!visited[neigh]){
                  dfsHelper(graph, neigh,visited);
            }
        } 
    }

}

public class DFSTraversal {

    

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        graph.dfs(graph, 0);
      }
    
}