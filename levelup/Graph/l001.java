import java.util.*;

public class l001 {
    public static class Edge {
        int v = 0, w = 0;

        Edge(int v, int w){
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w){
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    //O(2E)
    public static void display(ArrayList<Edge>[] graph){
        int N = graph.length;
        for(int i = 0; i < N; i++){
            System.out.print(i + " - > ");
            for(Edge e : graph[i])
                System.out.print("( " + e.v + ", " + e.w + " )");

            System.out.println();
        }
    }

    //O(E)
    public static int findEdge(ArrayList<Edge>[] graph, int u, int v){
        ArrayList<Edge> list = graph[u];
        for(int i = 0; i < list.size(); i++){
            Edge e = list.get(i);
            if(e.v == v)
                return i;
        }
        return -1;
    }

    //O(E)
    public static void removeEdge(ArrayList<Edge>[] graph, int u, int v){
        int idx = findEdge(graph, u, v);
        graph[u].remove(idx);

        idx = findEdge(graph, v, u);
        graph[v].remove(idx);
    }

    //hasPath
    //O(E) where E is the no. edge in a perticular component
    public static boolean dfc_findPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis){
        if(src == dest)
            return true;

        vis[src] = true;
        boolean res = false;
        for(Edge e : graph[src]){
            if(!vis[e.v])
                res = res || dfc_findPath(graph, e.v, dest, vis);
        }
        return res;
    }

    public static int printAllPath(ArrayList<Edge>[] graph, int src, int dest, int psf, int wsf, boolean[]vis){
        if(src == dest){
            System.out.println(psf + dest + "@" + wsf);
            return 1;
        }

        vis[src] = true;
        int count = 0;
        for(Edge e : graph[src]){
            if(!vis[e.v])
                count += printAllPath(graph, e.v, dest, psf, wsf, vis);
        }
        vis[src] = false;

        return count;
    }


    public static void dfs_gcc(ArrayList<Edge>[] graph, int src, boolean[] vis){
        vis[src] = true;
        for(Edge e: graph[src]){
            if(!vis[e.v])
                dfs_gcc(graph, e.v, vis);
        }
    }

    //GCC
    //O(E + V)
    public static void getConnectedComponet(ArrayList<Edge>[] graph){
        int component = 0; 
        int N = graph.length;
        boolean [] vis = new boolean[N];
        for(int i = 0; i < N; i++){
            if(!vis[i]){
                component++;
                dfs_gcc(graph, i, vis);
            }
        }
        System.out.println(component);
    }

    //O(E)
    public static void BFS_withCycle(ArrayList<Edge>[] graph, int src, boolean[] vis){
        LinkedList<Integer> que = new LinkedList<>();
        int level = 0;
        boolean isCyclic = false;

        que.add(src);
        while(que.size() != 0){
            int size = que.size();
            System.out.print("Min no. of Edges" + level + " -> ");
            while(size-- > 0){
                int rvtx = que.removeFirst();

                if(vis[rvtx]){
                    isCyclic = true;
                    continue;
                }

                System.out.print(rvtx + ", ");
                vis[rvtx] = true;
                for(Edge e : graph[rvtx]){
                    if(!vis[e.v])
                        que.addLast(e.v);
                }
            }
            System.out.println();
            level++;
        }
    }


    //O(V)
    public static void BFS_withoutCycle(ArrayList<Edge>[] graph, int src, boolean[]vis){
        LinkedList<Integer> que = new LinkedList<>();
        int level = 0;
        
        que.addLast(src);
        vis[src] = true;
        while(que.size() != 0){
            int size = que.size();
            System.out.print("Min no. of Edge" + level + " -> ");
            while(size-- > 0){
                int rvtx = que.removeFirst();

                System.out.print(rvtx + ", ");
                vis[rvtx] = true;
                for(Edge e : graph[rvtx]){
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

    public static boolean isBipartite(ArrayList<Edge>[] graph, int src, int[]vis){
        LinkedList<Integer> que = new LinkedList<>();
        int color = 0; // 0 -> red, 1 -> green

        boolean isCycle = false, isBipartite = true;
        que.add(src);
        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                int rm = que.removeFirst();
                if(vis[rm] != -1){
                    isCycle = true;
                    if(vis[rm] != color){
                        isBipartite = false;
                        break;
                    }
                    continue;
                }

                vis[rm] = color;
                for(Edge e : graph[rm]){
                    if(vis[e.v] == -1){
                        que.addLast(e.v);
                    }
                }
            }
            color = (color + 1) % 2;
            if(!isBipartite) break;
        }

        if(isCycle){
            if(isBipartite){
                System.out.println("Graph is Bi-Partite it means it has even length cycle");
            }
            else
                System.out.println("Graph is non Bi-Partite it means it has odd length cycle");
        }else
            System.out.println("Graph is Bi-Partite");

        return isBipartite;
    }

    public static boolean isBipartite(ArrayList<Edge>[] graph){
        int N = graph.length;
        int[] vis = new int[N];
        Arrays.fill(vis, -1);

        boolean res = true;
        for(int i = 0; i < N; i++)
            if(vis[i] == -1)
                res = res && isBipartite(graph, i, vis);
        
        return res;
    }

}