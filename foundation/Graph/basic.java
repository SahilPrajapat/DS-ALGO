import java.io.*;
import java.util.*;

public class basic {

    public static class Edge {
        int v = 0;
        int w = 0;

        Edge( int v, int w){
            this.v = v;
            this.w = w;
        }

        public String toString() {
            return "(" + this.v + "," + this.w + ")";
        }
    }

    
    
    public static int N = 7;
    public static ArrayList<Edge>[] graph = new ArrayList[N];
    
    public static void addEdge (int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }
    
    public static void display() {
        for(int i = 0; i < N; i ++){
            System.out.print(i + "->");
            for(Edge e: graph[i]) {
                System.out.print(e);
            }
            System.out.println();
        }
    }
    
    public static int findEdge(int u, int v){
        for(int i = 0; i < graph[u].size(); i++){
            Edge e = graph[u].get(i);
            if(e.v == v)
            return i;
        }
        return -1;
        
    }
    
    public static void removeEdge(int u, int v){
        int idx1 = findEdge(u, v);
        int idx2 = findEdge(v, u);
        
        if(idx1 == -1 || idx2 == -1)
        return;
        
        graph[u].remove(idx1);
        graph[u].remove(idx2);
    }
    
    public static void removeVtx(int u){
        while(graph[u].size() != 0){
            int n = graph[u].size();
            Edge e = graph[u].get(n - 1);
            removeEdge(u, e.v);
        }
    }
    
    public static class Edge2 {
        int src;
        int nbr;
        int wt;

        Edge2(int src, int nbr, int wt){
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }

    }
    public static ArrayList<Edge2>[] graph2 = new ArrayList[N];

    public static boolean hasPath(ArrayList<Edge2>[] graph2, int src, int dst, boolean[] vis){
        if(src == dst){
            return true;
        }
        vis[src] = true;
        for(Edge2 e : graph2[src]){
            if(vis[e.nbr] == false){
                boolean nbrPath = hasPath(graph2, e.nbr, dst, vis);
                if(nbrPath == true){
                    return true;
                }
            }
        }
       
        return false;
    }

    public static void printAllPath(ArrayList<Edge2>[] graph2, int src, int dest, boolean[] vis, String psf){
        if(src == dest){
            System.out.println(psf);
            return;
        }

        vis[src] = true;
        for(Edge2 e: graph2[src]){
            if(vis[e.nbr] == false){
                printAllPath(graph2, e.nbr, dest, vis, psf + e.nbr);
            }
        }
        vis[src] = false;
    }

    public static class pair {
        int largestWeight = -(int)1e9;
        String largestPath = "";
        int smallestWeight = (int)1e9;
        String smallestPath = "";

        int ceil = (int)1e9;
        String ceilPath = "";
        int floor = -(int)1e9;
        String floorPath = "";

    }

    public static class pqPair {
        int wsf = 0;
        String psf = "";

        pqPair(int wsf, String psf){
            this.wsf = wsf;
            this.psf = psf;
        }
    }

    static PriorityQueue<pqPair> pq = new PriorityQueue<>((a, b) -> {
        return a.wsf - b.wsf;
    });

    public static void allSolution(int src, int dest, boolean[]vis, pair p, String psf, int wsf, int givenWeight, int k){
        if(src == dest){
            if(wsf > p.largestWeight){
                p.largestWeight = wsf;
                p.largestPath = psf + dest;
            }

            if(wsf < p.smallestWeight){
                p.smallestWeight = wsf;
                p.smallestPath = psf + dest;
            }

            if(wsf < givenWeight){
                if(wsf > p.floor){
                    p.floor = wsf;
                    p.floorPath = psf + dest;
                }
            }

            if(wsf > givenWeight){
                if(wsf < p.ceil){
                    p.ceil = wsf;
                    p.ceilPath = psf + dest;
                }
            }
            
            pq.add(new pqPair(wsf, psf + dest));
            if(pq.size() > k)
                pq.remove();
        
        }

        vis[src] = true;
        for(Edge e: graph[src]){
            if(!vis[e.v])
                allSolution(e.v, dest, vis, p, psf + src, wsf + e.w, givenWeight, k);
        }
        vis[src] = false;
    }

    public static void dfs (int src, boolean[] vis) {
        vis[src] = true;
        for(Edge e: graph[src])
            if(!vis[e.v])
                dfs(e.v, vis);
    }

    public static void gcc() {
        boolean[] vis = new boolean[N];
        int component = 0;
        for(int i = 0; i < N; i++){
            if(!vis[i])
                component++;
                dfs(i, vis);
        }
    }

    public static boolean isGraphConnected() {
        boolean vis[] = new boolean[N];
        int component = 0;
        for(int i = 0; i < N; i++){
            if(!vis[i]){
                component++;
                dfs(i, vis);
            }
        }

        return component == 1;
    }

    public static void dfs_island(int[][] mat, int[][]dir, int i, int j){
        mat[i][j] = 0;
        for(int d = 0; d < 4; d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if(r >= 0 && c >= 0 && r < mat.length && c < mat[0].length && mat[r][c] == 1){
                dfs_island(mat, dir, r, c);
            }
        }
    }

    public static int numberInsland(int[][] mat){
        int n = mat.length;
        int m = mat[0].length;

        int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

        int count = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(mat[i][j] == 1){
                    dfs_island(mat, dir, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    public static void hamintion_dfs(int src, int osrc, boolean[] vis, int NoOfEdges, String psf){
        if(NoOfEdges == N - 1){
            System.out.print(psf + src);
            int idx = findEdge(src, osrc);
            if(idx != -1)
                System.out.print("*");
            
            System.out.println();
            return;
        }

        vis[src] = true;
        for(Edge e: graph[src]){
            if(!vis[e.v])
            hamintion_dfs(e.v, osrc, vis, NoOfEdges + 1, psf + src);
        }
        vis[src] = false;
    }

    public static void hamintionPath() {
        boolean[] vis = new boolean[N];
        hamintion_dfs(0, 0, vis, 0, "");
    }

    //Moon problem

    public static int moonDFS(ArrayList<Integer>[] graph, int src, boolean[] vis){
        vis[src] = true;
        int size = 0;
        for(Integer e: graph[src]){
            if(!vis[e])
                size += moonDFS(graph, e, vis);
        }
        return size + 1;
    }

    public static long journeyToMoon(int n, int[][]astronaut){
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for(int[] a: astronaut){
            graph[a[0]].add(a[1]);
            graph[a[1]].add(a[0]);
        }

        ArrayList<Integer> sizeArray = new ArrayList<>();
        boolean[] vis = new boolean[n];
        for(int i = 0; i < n; i++){
            if(!vis[i])
                sizeArray.add(moonDFS(graph, i, vis));
        }

        long ssf = 0, res = 0;
        for(int ele : sizeArray){
            res += ele * ssf;
            ssf += ele;
        }

        return res;
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------------------


    public static void BFS(int src, boolean[]vis){
        int level = 0, cycleCount = 0;

        LinkedList<Integer> que = new LinkedList<>();
        que.add(src);

        while(que.size() != 0){
            int size = que.size();
            System.out.print(level + "->");

            while(size--> 0){
                int rVtx = que.removeFirst();
                if(vis[rVtx]) {
                    cycleCount++;
                    continue;
                }

                System.out.print(rVtx + "");

                vis[rVtx] = true;
                for(Edge e : graph[rVtx]){
                    if(!vis[e.v]){
                        que.addLast(e.v);
                    }
                }
            }
            System.out.println();
            level++;
        }
    }

    public static void BFS_02(int src, boolean[]vis){
        int level = 0, cycleCount = 0;

        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);
        vis[src] = true;
        while(que.size() != 0){
            int size = que.size();
            System.out.print(level + "->");

            while(size --> 0){
                int rVtx = que.removeFirst();

                System.out.print(rVtx + " ");
                for(Edge e: graph[rVtx]) {
                    if(!vis[e.v]){
                        que.addLast(e.v);
                        vis[e.v] = true;
                    }
                }
            }
            System.out.println();
            level++;
        }
    }


    public static class Pair2{
        int src;
        String psf;

        Pair2(int src, String psf){
            this.src = src;
            this.psf = psf;
        }
    }
    
    public static boolean IsCyclic(int src, boolean[]vis){
        ArrayDeque<Pair2> q = new ArrayDeque<>();
        q.add(new Pair2(src, src + ""));
        
        while(q.size() > 0){
            Pair2 rem = q.removeFirst();
            
            if(vis[rem.src]){
                return true;
            }
            vis[rem.src] = true;
            
            for(Edge2 e : graph2[rem.src]){
                if(!vis[e.nbr]){
                    q.add(new Pair2(e.nbr, rem.psf + e.nbr));
                }
            }
        }
        return false;
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


    public static boolean isTree(){
        // no cycle and 1 gcc count
        return true;
    }

    public static boolean isForest(){
        // no cycle and more than 1 gcc count
        return true;
    }

    public static boolean isBipartite(int src) {
        LinkedList<Integer> que = new LinkedList<>();
        int []vis = new int[N];
        Arrays.fill(vis, -1);

        que.addLast(src);
        int color = 0;

        //-1 -> undefine, 0 -> red, 1 -> green.
        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                int rVtx = que.removeFirst();
                if(vis[rVtx] != -1){
                    if(vis[rVtx] != color) // conflict
                        return false;

                    continue;
                }

                vis[rVtx] = color;
                for(Edge e: graph[rVtx]){
                    if(vis[e.v] == -1){
                        que.addLast(e.v);
                    }
                }
            }
            color = (color + 1) % 2;
        }
        return true; 
    }

    public static int spreadInfection(int src, int timeLimit){
        LinkedList<Integer> que = new LinkedList<>();
        boolean[] vis = new boolean[N];
        que.addLast(src);
        int time = 0, infectedCount = 1;
        vis[src] = true;

        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                int rvtx = que.removeFirst();

                for(Edge e: graph[rvtx]){
                    if(!vis[e.v]){
                        vis[e.v] = true;
                        que.addLast(e.v);
                        if(time + 1 < timeLimit)
                            infectedCount++;
                        else
                            return infectedCount;
                    }
                }
            }
            time++;
        }
        return infectedCount;
    }

    public static class Pair implements Comparable<Pair> {
        int src;
        String psf;
        int wsf;

        Pair(int src, String psf, int wsf){
            this.src = src;
            this.psf = psf;
            this.wsf = wsf;
        }
        public int compareTo(Pair o){
            return this.wsf - o.wsf;
        }
    }

    public static void shortestPathwithWeight(int src){
        boolean[] vis = new boolean[N];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, src + "", 0));

        while(pq.size() > 0){
            Pair rem = pq.remove();

            if(vis[rem.src]){
                continue;
            }

            vis[rem.src] = true;
            System.out.println(rem.src + " via " + rem.psf + " @ " + rem.wsf);

            for(Edge e: graph[rem.src]){         
                if(!vis[e.v]){
                    pq.add(new Pair(e.v, rem.psf + e.v, rem.wsf + e.w));
                }
            }
        }
    }

    public static class Pair3 implements Comparable<Pair3> {
        int v;
        int av;
        int wt;
        
        Pair3(int v, int av, int wt){
            this.v = v;
            this.av = av;
            this.wt = wt;
        }
        public int compareTo(Pair3 o) {
           return this.wt - o.wt;
        }
    }
    
    public static void connectAllPc(){
        PriorityQueue<Pair3> pq = new PriorityQueue<>();
        boolean[] vis = new boolean[N];
        pq.add(new Pair3(0, -1, 0));
        
        while(pq.size() > 0){
            Pair3 rem = pq.remove();
            
            if(vis[rem.v]){
                continue;
            }
            vis[rem.v] = true;
            
            if(rem.av != -1){
             System.out.println("[" + rem.v + "-" + rem.av + "@" + rem.wt + "]");
            }
             
            for(Edge2 e : graph2[rem.v]){
                if(!vis[e.nbr]){
                    pq.add(new Pair3(e.nbr, rem.v, e.wt));
                }
            }
        }
    }


    public static class Pair4{
        int v;
        String psf;
        
        Pair4(int v, String psf){
            this.v = v;
            this.psf = psf;
        }
    }
    
    public static void iterative_dfs(int src){
        boolean[] vis = new boolean[N];
        Stack<Pair4> st = new Stack<>();
        st.push(new Pair4(src, src + ""));
        
        while(st.size() > 0){
            Pair4 rem = st.pop();
            
            if(vis[rem.v]){
                continue;
            }
            vis[rem.v] = true;
            
            System.out.println(rem.v + "@" + rem.psf);
            
            for(Edge2 e : graph2[rem.v]){
                if(!vis[e.nbr]){
                    st.push(new Pair4(e.nbr, rem.psf + e.nbr));
                }
            }
        }
    }





    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        for(int i = 0; i < N; i++){
            graph[i] = new ArrayList<>();
        }
        // addEdge(0, 1, 10);
        // addEdge(0, 3, 40);
        // addEdge(1, 2, 10);
        // addEdge(2, 3, 10);
        // addEdge(3, 4, 2);
        // addEdge(4, 5, 3);
        // addEdge(4, 6, 8);
        // addEdge(5, 6, 3);
        // addEdge(2, 5, 5);
        // addEdge(0, 6, 16);
        addEdge(0, 1, 10);
        addEdge(1, 2, 10);
        addEdge(2, 3, 10);
        addEdge(3, 4, 10);
        addEdge(4, 5, 10);
        addEdge(5, 6, 10);

        // display();
        boolean[] vis = new boolean[N];
        // // System.out.println(hasPath(graph2, 0, 6, vis));
        // pair p = new pair();
        // allSolution(0, 6, vis, p, "", 0, 30, 4);
        // System.out.println("Smallest Path =" + p.smallestPath + "@" + p.smallestWeight);
        // System.out.println("Largest Path =" + p.largestPath + "@" + p.largestWeight);
        // System.out.println("Ceil of 30 =" + +p.ceil);
        // System.out.println("Floor of 30 =" + +p.floor);
        // System.out.println("Kth Largest Path =" + pq.peek().psf + "@" + pq.peek().wsf);

        // int n = scn.nextInt();
        // int m = scn.nextInt();
        // int [][]arr = new int[n][m];
        // for(int i = 0; i < arr.length; i++){
        //     for(int j = 0; j < arr[0].length; j++){
        //         arr[i][j] = scn.nextInt();
        //     }
        // }

        // System.out.println(numberInsland(arr));
        // BFS_02(2, vis);

        // hamintionPath();

        // boolean cycle = isCyclic(0, vis);
        // if(cycle){
        //     System.out.println(true);
        // }else {
        //     System.out.println(false);
        // }

        topologicalSort(vis, N);
    }
    
}
