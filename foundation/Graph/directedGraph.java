import java.io.*;
import java.util.*;

public class directedGraph {

    public static class Edge{
        int v = 0;
        int w = 0;

        Edge(int v, int w){
            this.v = v;
            this.w = w;
        }

        public String toString(){
            return "(" + this.v + ", " + this.w + ")";
        }
    }

    public static int N = 0;
    public static ArrayList<Edge>[] graph = new ArrayList[N];

    public static void addEdge(int u, int v, int w){
        graph[u].add(new Edge(v, w));
    }

    public static void display() {
        for(int i = 0; i < N; i++){
            System.out.print(i + "->");
            for(Edge e: graph[i]){
                System.out.print(e);
            }
            System.out.println();
        }
    }

    public static void topo_dfs(int src, boolean[]vis, Stack<Integer> st){
        vis[src] = true;
        for(Edge e: graph[src]){
            if(!vis[e.v]){
                topo_dfs(e.v, vis, st);
            }
        }
        st.push(src);
    }

    public static void topologicalSort(boolean[]vis,int N){
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < N; i++){
            if(!vis[i]){
                topo_dfs(i, vis, st);
            }
        }
        while(st.size() > 0){
            System.out.println(st.pop());
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  
        int vtces = Integer.parseInt(br.readLine());
        N = vtces;
        graph = new ArrayList[vtces];
        for (int i = 0; i < vtces; i++) {
           graph[i] = new ArrayList<>();
        }
  
        int edges = Integer.parseInt(br.readLine());
        for (int i = 0; i < edges; i++) {
           String[] parts = br.readLine().split(" ");
           int v1 = Integer.parseInt(parts[0]);
           int v2 = Integer.parseInt(parts[1]);
           graph[v1].add(new Edge(v1, v2));
        }

        boolean[] vis = new boolean[N];
   
        // write your code here
        topologicalSort(vis, N);
     }
    
}
