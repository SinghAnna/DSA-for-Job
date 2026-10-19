import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    void dfsIntra(Graph graph, int start){

        Stack<Integer> st = new Stack<>();
        boolean[] visited = new boolean[graph.V];

        visited[start] = true;
        st.push(start);

        while (!st.isEmpty()) {
            int vertex = st.pop();
            System.out.print(vertex + " ");

            for(int neigh : adjList.get(vertex)){

                if (!visited[neigh]) {
                    visited[neigh] = true;
                    st.push(neigh);
                }
            }
        }

    }

}
public class DFSItrative {
    public static void main(String[] args) {
          Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        graph.dfsIntra(graph, 0);
        
    }
}
