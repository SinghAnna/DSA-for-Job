import java.util.ArrayList;
import java.util.List;

public class GraphDirected {

    private Integer vertices;
    private List<List<Integer>> adjList;

    public GraphDirected(int vertices) {
        this.vertices = vertices;
        adjList = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }

    }

    public void addEdge(int u, int v) {
        if (!adjList.get(u).contains(v)) {
            adjList.get(u).add(v);
        }
    }

    public void display() {
        for (int i = 0; i < vertices; i++) {
            System.out.print(i + ": ");
            for (int neigh : adjList.get(i)) {
                System.out.print(neigh + " ");
            }
            System.out.println();
        }
    }

    public List<Integer> getConnted(int veritex) {
        return adjList.get(veritex);
    }

    public static void main(String[] args) {
        GraphDirected graph = new GraphDirected(4);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 1);
        graph.addEdge(1, 3);
        graph.addEdge(2, 1);
        graph.addEdge(3, 0);
        graph.addEdge(3, 3);
        graph.display();

        List<Integer> connceted = graph.getConnted(0);
        System.out.println(connceted); 
    }
}
