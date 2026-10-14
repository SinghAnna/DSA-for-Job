import java.util.ArrayList;
import java.util.List;

public class GraphUndirected {



        private int vertices;
        private List<List<Integer>> adjList;

        public GraphUndirected(int vertices){
            this.vertices = vertices;
            adjList = new ArrayList<>();


            for(int i = 0; i < vertices; i++){
                adjList.add(new ArrayList<>());
            }
        }


    // add Undirected Edge
        public void addEdge(int u, int v){
  
            if (!adjList.get(u).contains(v)) {
                adjList.get(u).add(v);
                adjList.get(v).add(u);
            }
 
        }


        public void addEdgeWithWeight(int u, int v ,int weight){
            //Store as Pair u : [v,w]
            //Strore as Pair v : [u,w]
            System.out.println("Edge " + u + " - "+ v + " with weight "+ weight);
        }

        // Display Graph
        public void display(){
            for(int i = 0; i < vertices; i++){
                System.out.print(i + ": ");
                for(int neighbor : adjList.get(i)){
                    System.out.print(neighbor + " ");
                }
                System.out.println();
            }
        }

        // they give all connted notes to this node
        public  List<Integer> getNeighbors(int vertex){
            return adjList.get(vertex);
        }


    static void main(){
        System.out.println("Undirected Graph : ");

        GraphUndirected graphUndirected = new GraphUndirected(4);
        graphUndirected.addEdge(0, 1);
        graphUndirected.addEdge(0,2);
        graphUndirected.addEdge(1,2 );
        graphUndirected.addEdge(1, 3);
        graphUndirected.addEdge(3, 1);

        graphUndirected.display();

        List<Integer> conection = graphUndirected.getNeighbors(1);

        System.out.println(conection);
    }
}