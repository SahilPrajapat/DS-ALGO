import java.lang.reflect.Array;
import java.util.*;

public class l002directedgraph {
    public static class Edge {
        int v = 0, w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(v, w));
    }

    // O(2E)
    public static void display(ArrayList<Edge>[] graph) {
        int N = graph.length;
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + ", " + e.w + ") ");
            }
            System.out.println();
        }
    }

    public static void dfs_topo(ArrayList<Edge>[] graph, int src, boolean[] vis, ArrayList<Integer> ans) {
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                dfs_topo(graph, e.v, vis, ans);
        }

        ans.add(src);
    }

    public static void topologicalOrder(ArrayList<Edge>[] graph) {
        ArrayList<Integer> ans = new ArrayList<>();

        int N = graph.length;
        boolean[] vis = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (!vis[i])
                dfs_topo(graph, i, vis, ans);
        }
    }



    public static ArrayList<Integer> kahnsAlgo(ArrayList<Edge>[] graph){
        int N = graph.length;
        LinkedList<Integer> que = new LinkedList<>();
        ArrayList<Integer>ans = new ArrayList<>();
        int[] indegree = new int[N];

        for(int i = 0; i < N; i++){
            for(Edge e : graph[i])
                indegree[e.v]++;
        }

        for(int i = 0; i < N; i++){
            if(indegree[i] == 0)
                que.addLast(i);
        }

        while(que.size() != 0){
            int rvtx = que.removeFirst();
            ans.add(rvtx);

            for(Edge e : graph[rvtx]){
                if(--indegree[e.v] == 0)
                    que.addLast(e.v);
            }
        }

        if(ans.size() != N)
            ans.clear();;

        return ans;
    }


    // -1 : unvisited, 0 = currentPath, 1 = backtrack
    public static boolean dfs_topo_isCycle(ArrayList<Edge>[] graph, int src, int[] vis, ArrayList<Integer> ans){
        vis[src] = 0;
        boolean res = false;
        for(Edge e : graph[src]){
            if(vis[e.v] == -1){
                res = res || dfs_topo_isCycle(graph, e.v, vis, ans);
            }else if(vis[e.v] == 0) {
                res = true;
            }
        }

        vis[src] = 1;
        ans.add(src);
        return res;
    }


    public static ArrayList<Integer> dfs_topo_isCycle(ArrayList<Edge>[] graph) {
        int N = graph.length;
        int[] vis =  new int[N];
        Arrays.fill(vis, -1);

        boolean cycle = false;
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i < N; i++){
            if(vis[i] == -1){
                cycle = cycle || dfs_topo_isCycle(graph, i, vis, ans);
            }
        }

        if(cycle)
            ans.clear();

        return ans;
    }

    // course sehdule using dfs 207
     // -1 : unvisited, 0 = currentPath, 1 = backtrack
     public static boolean dfs_topo(ArrayList<Integer>[] graph, int src, int[] vis){
        vis[src] = 0;
        
        boolean res = false;
        for(Integer e : graph[src]){
            if(vis[e] == -1){
                res = res || dfs_topo(graph, e, vis);
            }else if(vis[e] == 0)
                res = true;
        }
        
        vis[src] = 1;
        return res;
    }
    
    public boolean canFinish(int N, int[][] arr) {
        ArrayList<Integer>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();
        for (int[] a : arr) {
            graph[a[0]].add(a[1]);
        }
        
        int[] vis = new int[N];
        Arrays.fill(vis, -1);
        
        boolean cycle = false;
        for(int i = 0; i < N; i++){
            if(vis[i] == -1)
                cycle = cycle || dfs_topo(graph, i, vis);
        }
        
        return !cycle;
    }


    // SSC
    public static void dfs_topo(int src, ArrayList<Edge>[] graph, boolean[] vis, ArrayList<Integer>ans){
        vis[src] = true;
        for(Edge e : graph[src]){
            if(!vis[e.v])
                dfs_topo(e.v, graph, vis, ans);
        }
        ans.add(src);
    }

    public static void dfs_SSC_Compo(int src, ArrayList<Edge>[] graph, boolean[] vis, ArrayList<Integer>component){
        vis[src] = true;
        component.add(src);
        for(Edge e : graph[src]){
            if(!vis[e.v])
                dfs_SSC_Compo(e.v, graph, vis, component);
        }
    }

    public static void kosaRaju(int N, ArrayList<Edge>[] graph){
        boolean[] vis = new boolean[N];
        ArrayList<Integer> order = new ArrayList<>();
        for(int i = 0; i < N; i++){
            if(!vis[i])
                dfs_topo(i, graph, vis, order);
        }

        // taking complement of graph
        ArrayList<Edge>[] ngraph = new ArrayList[N];
        for(int i = 0; i < N; i++)
            ngraph[i] = new ArrayList<>();

        for(int i = 0; i < N; i++){
            ArrayList<Edge> ar = graph[i];
            for(Edge e : ar){
                ngraph[e.v].add(new Edge(i, e.w));
            }
        }

        for(int i = 0; i < N; i++)
            vis[i] = false;

        ArrayList<Integer> components = new ArrayList<>();
        for(int i = order.size(); i >= 0; i--){
            int vtx = order.get(i);
            if(!vis[vtx]){
                dfs_SSC_Compo(vtx, ngraph, vis, components);
                System.out.println(components);
                components.clear();
            }
        }
    }


    // https://practice.geeksforgeeks.org/problems/mother-vertex/1
        public static void dfs_topo(int src, ArrayList<ArrayList<Integer>> graph, boolean[] vis){
            vis[src] = true;
            for(int e : graph.get(src)){
                if(!vis[e])
                    dfs_topo(e, graph, vis);
            }
    
        }
        
        public static int findMotherVertex(int V, ArrayList<ArrayList<Integer>>adj){
            boolean[] vis = new boolean[V];
            int ans = -1;
            for(int i = 0; i < V; i++){
                if(!vis[i]){
                    dfs_topo(i, adj, vis);
                    ans = i;
                }
            }
            
            boolean[] check = new boolean[V];
            dfs_topo(ans, adj, check);
            for(boolean val : check)
            {
                if (!val)
                {
                    return -1;
                }
            }
            return ans;
        }

    

    public static void constructGraph() {
        int N = 7;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 3, 0, 10);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 5, 6, 3);
        addEdge(graph, 6, 4, 8);

        display(graph);
    }

    public static void main(String[] args) {
        constructGraph();
    }


}
