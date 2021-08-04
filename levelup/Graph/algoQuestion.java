import java.util.*;

public class algoQuestion {

    static int[] low, disc;
    static boolean[] vis;
    static int time;

    public static void dfs_criticalConnection(ArrayList<Integer>[] graph, int src, int par, List<List<Integer>> ans) {
        disc[src] = low[src] = time++;
        vis[src] = true;
        for (Integer v : graph[src]) {
            if (!vis[v]) {
                dfs_criticalConnection(graph, v, src, ans);
                if (disc[src] < low[v]) {
                    List<Integer> edge = new ArrayList<>(Arrays.asList(src, v));
                    ans.add(edge);
                }

                low[src] = Math.min(low[src], low[v]);
            } else if (v != par) {
                low[src] = Math.min(low[src], disc[v]);
            }
        }
    }

    public List<List<Integer>> criticalConnections(int N, List<List<Integer>> connections) {
        ArrayList<Integer>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (List<Integer> ar : connections) {
            graph[ar.get(0)].add(ar.get(1));
            graph[ar.get(1)].add(ar.get(0));
        }

        low = new int[N];
        disc = new int[N];

        vis = new boolean[N];
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (!vis[i]) {
                dfs_criticalConnection(graph, i, -1, ans);
            }
        }

        return ans;
    }

    // 743
    public int networkDelayTime(int[][] times, int n, int k) {
        // {v, w}
        ArrayList<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] time : times) {
            int u = time[0], v = time[1], w = time[2];
            graph[u].add(new int[] { v, w });
        }

        int[] dis = new int[n + 1];
        Arrays.fill(dis, (int) 1e9);

        // {vtx, wsf}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        pq.add(new int[] { k, 0 });
        dis[k] = 0;

        while (pq.size() != 0) {
            int[] rp = pq.remove(); // remove pair
            int vtx = rp[0], wsf = rp[1];

            if (wsf > dis[vtx])
                continue;

            for (int[] e : graph[vtx]) {
                int v = e[0], w = e[1];
                if (wsf + w < dis[v]) {
                    dis[v] = wsf + w;
                    pq.add(new int[] { v, wsf + w });
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (dis[i] == (int) 1e9)
                return -1;
            max = Math.max(max, dis[i]);
        }

        return max;

    }

    // 787
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // {v, w}
        ArrayList<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] f : flights) {
            int u = f[0], v = f[1], w = f[2];
            graph[u].add(new int[] { v, w });
        }

        // {vtx, cost, edgeCount}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        pq.add(new int[] { src, 0, k + 1 });
        while (pq.size() != 0) {

            int[] p = pq.remove();
            int vtx = p[0], cost = p[1], edgeCount = p[2];

            if (edgeCount < 0)
                continue;

            if (vtx == dst)
                return cost;

            for (int[] e : graph[vtx]) {
                int v = e[0], w = e[1];
                pq.add(new int[] { v, cost + w, edgeCount - 1 });
            }
        }

        return -1;
    }

    // 787
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        int[] prev = new int[n];
        Arrays.fill(prev, (int) 1e9);
        prev[src] = 0;

        for (int i = 1; i <= k + 1; i++) {
            int[] curr = new int[n];
            for (int j = 0; j < n; j++)
                curr[j] = prev[j];

            boolean anyUpdate = false;
            for (int[] e : flights) {
                int u = e[0], v = e[1], w = e[2];
                if (prev[u] != (int) 1e9 && prev[u] + w < curr[v]) {
                    curr[v] = prev[u] + w;
                    anyUpdate = true;
                }
            }

            if (!anyUpdate)
                break;

            prev = curr;
        }

        return prev[dst] != (int) 1e9 ? prev[dst] : -1;
    }

}
