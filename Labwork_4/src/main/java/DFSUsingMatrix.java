import java.util.*;

class Graph {
    int V, E;
    public int[][] Adj;

    public Graph(int v, int e) {
        V = v;
        E = e;
        Adj = new int[V][V];
    }

    void addEdge(int u, int v) {
        Adj[u][v] = 1;
        Adj[v][u] = 1;
    }
}
class DFS {
    int[] vis = new int[100];
    void dfs(Graph G, int u) {
        vis[u] = 1;
        System.out.print(u + " ");
        for (int v = 0; v < G.V; v++) {
            if (vis[v] == 0 && G.Adj[u][v] == 1) {
                dfs(G, v);
            }
        }
    }

    void dfsTraversal(Graph G) {
        Arrays.fill(vis, 0);
        for (int i = 0; i < G.V; i++) {
            if (vis[i] == 0) {
                dfs(G, i);
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner ( System.in );
        System.out.print ( "Enter the number of vertices: " );
        int v = input.nextInt ();
        Graph G = new Graph ( v, v * (v - 1) / 2 );
        Random random = new Random ();
        for (int i = 0; i < v; i++) {
            for (int j = i + 1; j < v; j++) {
                int randNum = random.nextInt ( 2 );
                if (randNum == 1) {
                    G.addEdge ( i, j );
                }
            }
        }

        System.out.println("Adjacency matrix:");
        for (int i = 0; i < G.V; i++) {
            for (int j = 0; j < G.V; j++) {
                System.out.print(G.Adj[i][j] + ", ");
            }
            System.out.println();
        }
        System.out.println("\n");

        DFS dfs = new DFS ();
        System.out.print( "DFS traversal result: ");
        dfs.dfsTraversal ( G );
    }
}