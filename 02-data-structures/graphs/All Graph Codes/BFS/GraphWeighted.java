import java.util.ArrayList;
import java.util.List;

public class GraphWeighted {

    static class Edge{
        int to;
        int weight;

        Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    private int vertices;
    private List<List<Edge>> adjList;


    public GraphWeighted(int vertices){
        this.vertices = vertices;
        adjList = new ArrayList<>();

        for(int i = 0; i < vertices; i++){
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v, int weight){
        adjList.get(u).add(new Edge(v, weight));
    }

    public void display(){
        for (int i = 0; i < vertices; i++) {
            System.out.print(i +": ");
            for (Edge edge : adjList.get(i)) {
                System.out.print("(" + edge.to + "," + edge.weight + ") ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        GraphWeighted graph = new GraphWeighted(4);
        
        graph.addEdge(0, 1, 7);
        graph.addEdge(0, 2, 14);
        graph.addEdge(0, 3, 17);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 19);
        graph.addEdge(3, 2, 21);
         graph.addEdge(2, 3, 11);

        graph.display();

    }
}
