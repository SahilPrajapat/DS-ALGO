import java.util.*;

public class bfs_question {

    public int orangesRotting(int[][] grid) {
        LinkedList<Integer> que = new LinkedList<>();
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        int freshOrange = 0, time = 0;

        int n = grid.length, m = grid[0].length;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1)
                    freshOrange++;
                else if (grid[i][j] == 2) {
                    que.addLast(i * m + j);
                    grid[i][j] = 2; // mark them visited
                }
            }
        }

        if (freshOrange == 0)
            return 0;

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int rmOrange = que.removeFirst();
                int sr = rmOrange / m, sc = rmOrange % m;
                for (int d = 0; d < 4; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                        if (--freshOrange == 0) {
                            return time + 1;
                        }
                        grid[r][c] = 2;
                        que.addLast(r * m + c);
                    }
                }
            }
            time++;
        }

        return -1;
    }

    //1091
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid.length == 0 && grid[0].length == 0)
            return 0;
        int n = grid.length, m = grid[0].length;

        if (grid[0][0] == 1 || grid[n - 1][m - 1] == 1)
            return -1;

        int shortestpath = 1;
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, 1 }, { 1, -1 }, { -1, -1 } };

        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(0);

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int idx = que.removeFirst();
                int sr = idx / m, sc = idx % m;

                if (sr == n - 1 && sc == m - 1)
                    return shortestpath;

                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 0) {
                        grid[r][c] = 1;
                        que.addLast(r * m + c);
                    }
                }
            }
            shortestpath++;
        }
        return -1;
    }


    //542
    public static int[][] updateMatrix(int[][] grid) {
        if(grid.length == 0 && grid[0].length == 0)
            return grid;

        int n = grid.length, m = grid[0].length;
        boolean[][] vis = new boolean[n][m];

        LinkedList<Integer> que = new LinkedList<>();
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 0){
                    vis[i][j] = true;
                    que.addLast(i * m + j);
                }
            }
        }

        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                int rm = que.removeFirst();
                int sr = rm / m, sc = rm % m;

                for(int d = 0; d < 4; d++){
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if(r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]){
                        vis[r][c] = true;
                        grid[r][c] = grid[sr][sc] + 1;
                        que.addLast(r * m + c);
                    }
                }
            }
        }

        return grid;
    }

    //886
    public boolean isBipartite(List<Integer>[] graph, int src, int[]vis) {
        LinkedList<Integer> que = new LinkedList<>();
        int color = 0;
        
        boolean isCyclic = false, isBipartite = true;
        que.addLast(src);
        
        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                int rm = que.removeFirst();
                if(vis[rm] != -1){
                    isCyclic = true;
                    if(vis[rm] != color){
                        isBipartite = false;
                        break;
                    }
                    continue;
                }
                
                vis[rm] = color;
                for(int e : graph[rm]){
                    if(vis[e] == -1){
                        que.addLast(e);
                    }
                }
            }
            color = (color + 1) % 2;
            if(!isBipartite) break;
        }
        return isBipartite;
    }
    
    
    public boolean possibleBipartition(int n, int[][] dislikes) {
        
        int[] vis = new int[n+1];
        Arrays.fill(vis, -1);
        
        List<Integer>[] graph = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
          }

          for (int i=0;i<dislikes.length;i++) {
            graph[dislikes[i][0]].add(dislikes[i][1]);
            graph[dislikes[i][1]].add(dislikes[i][0]);
          }

        boolean res = true;
        for(int i = 0; i < n; i++)
            if(vis[i] == -1)
                res = res && isBipartite(graph, i, vis);
        
        return res;
    }

    public void wallsAndGates(int[][] grid) {
        if(grid.length == 0 && grid[0].length == 0)
            return;

        int n = grid.length, m = grid[0].length;
        LinkedList<Integer> que = new LinkedList<>();
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 0){
                    que.addLast(i * m + j);
                }
            }
        }

        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                int rm = que.removeFirst();
                int sr = rm / m, sc = rm % m;
                for(int d = 0; d < 4; d++){
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 2147483647){
                        grid[r][c] = grid[sr][sc] + 1;
                        que.addLast(r * m + c);
                    }
                }
            }
        }
    }


    //207
    public boolean canFinish(int N, int[][] arr) {
        
        ArrayList<Integer>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();
        for (int[] a : arr) {
            graph[a[0]].add(a[1]);
        }
        
        LinkedList<Integer> que = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        int[] indegree = new int[N];

        for (int i = 0; i < N; i++) {
            for (Integer e : graph[i]) {
                indegree[e]++;
            }
        }

        for (int i = 0; i < N; i++)
            if (indegree[i] == 0)
                que.addLast(i);
        
        while (que.size() != 0) {
            int rvtx = que.removeFirst();
            ans.add(rvtx);

            for (Integer e : graph[rvtx]) {
                if (--indegree[e] == 0)
                    que.addLast(e);
            }
        }
        
        return ans.size() == N;
    }

    //210
    public int[] findOrder(int N, int[][] arr) {
        ArrayList<Integer>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();
        for (int[] a : arr) {
            graph[a[0]].add(a[1]);
        }
        
        LinkedList<Integer> que = new LinkedList<>();
        int[] ans = new int[N];
        int[] indegree = new int[N];
        int idx = N - 1;

        for (int i = 0; i < N; i++) {
            for (Integer e : graph[i]) {
                indegree[e]++;
            }
        }

        for (int i = 0; i < N; i++)
            if (indegree[i] == 0)
                que.addLast(i);
        
        while (que.size() != 0) {
            int rvtx = que.removeFirst();
            ans[idx--] = rvtx;

            for (Integer e : graph[rvtx]) {
                if (--indegree[e] == 0)
                    que.addLast(e);
            }
        }

        if (idx != -1)
            ans = new int[0];
        
        return ans;
    }


    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        LinkedList<Integer> que = new LinkedList<>();
        
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        
        int[][] indegre = new int[n][m];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++){
                for(int d = 0; d < 4; d++){
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];
                    
                    if(r >= 0 && c >= 0 && r < n && c < m && matrix[i][j] > matrix[r][c])
                        indegre[i][j]++;
                }
                
                if(indegre[i][j] == 0)
                    que.addLast(i * m + j);
            }
        
        int level = 0;
        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                int rvtx = que.removeFirst();
                int sr = rvtx/m, sc = rvtx%m;
                for(int d = 0; d < 4; d++){
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
                    
                    if(r >= 0 && c >= 0 && r < n && c < m && matrix[sr][sc] < matrix[r][c] && --indegre[r][c] == 0)
                        que.addLast(r * m + c);
                }
            }
            level++;
        }
        return level;
    }

    //815
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if(source == target)
            return 0;
            
        int N = routes.length;
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for(int bus = 0; bus < routes.length; bus++){
            for(int busStand : routes[bus]){
                map.putIfAbsent(busStand, new ArrayList<>());
                map.get(busStand).add(bus);
            }
        }
        
        HashSet<Integer> busStandVisited = new HashSet<>();
        boolean[] busVisited = new boolean[N];
        int interchange = 0;

        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(source);
        busStandVisited.add(source);

        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                int busStand = que.removeFirst();
                for(int bus : map.get(busStand)){

                    if(busVisited[bus])
                        continue;

                    for(int upcomingBusStand : routes[bus]){
                        if(!busStandVisited.contains(upcomingBusStand)){
                            busStandVisited.add(upcomingBusStand);
                            que.addLast(upcomingBusStand);
                            if(upcomingBusStand == target)
                                return interchange + 1;
                        }
                    }

                    busVisited[bus] = true;
                }
            }
            interchange++;
        }

        return -1;
    }

    //490
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        
        int n = maze.length, m = maze[0].length, sr = start[0], sc = start[1], er = destination[0], ec = destination[1];
        LinkedList<Integer> que = new LinkedList<>();
        boolean[][] vis = new boolean[n][m];
        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        
        que.add(sr * m + sc);
        vis[sr][sc] = true;
        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                int rm = que.removeFirst();
                int i = rm / m, j = rm % m;
                for(int[] d : dir){
                    int r = i, c = j;
                    while(r >= 0 && c >= 0 && r < n && c < m && maze[r][c] == 0){
                        r += d[0];
                        c += d[1];
                    }

                    r -= d[0];
                    c -= d[1];

                    if(vis[r][c])
                        continue;

                    vis[r][c] = true;
                    que.addLast(r * m + c);
                    if(r == er && c == ec)
                        return true;
                }
            }
        }

        return false;

    }

    //505
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int n = maze.length, m = maze[0].length, sr = start[0], sc = start[1], er = destination[0], ec = destination[1];

        class pair implements Comparable<pair>{
            int r, c, dis;

            public pair(int r, int c, int dis){
                this.r = r;
                this.c = c;
                this.dis = dis;
            }

            public int compareTo(pair o){
                return this.dis - o.dis;
            }
        }

        PriorityQueue<pair> que = new PriorityQueue<>();
        int[][] distance = new int[n][m];
        for(int[] d : distance){
            Arrays.fill(d, (int)1e8);
        }

        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

        pair root = new pair(sr, sc, 0);
        que.add(root);
        distance[sr][sc] = 0;

        while(que.size() != 0){
            pair p = que.remove();
            for(int[] d : dir){
                int r = p.r, c = p.c, l = p.dis;
                while(r >= 0 && c >= 0 && r < n && c < m && maze[r][c] == 0){
                    r += d[0];
                    c += d[1];
                    l++;
                }

                r -= d[0];
                c -= d[1];
                l--;

                if(l >= distance[r][c])
                    continue;

                que.add(new pair(r, c, l));
                distance[r][c] = l;
            }
        }

        return distance[er][ec] != (int)1e8 ? distance[er][ec] : -1;

    }

    //505
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int n = maze.length, m = maze[0].length, sr = ball[0], sc = ball[1], er = hole[0], ec = hole[1];

        class pair implements Comparable<pair>{
            int r, c, dis;
            String path;

            public pair(int r, int c, int dis, String path){
                this.r = r;
                this.c = c;
                this.dis = dis;
                this.path = path;
            }

            public int compareTo(pair o){
                return this.dis == o.dis ? this.path.compareTo(o.path) : this.dis - o.dis;
            }
        }

        boolean[][] vis = new boolean[n][m];
        PriorityQueue<pair> pq = new PriorityQueue<>();
        pq.add(new pair(sr, sc, 0, ""));

        char[] dstr = {'u', 'd', 'l', 'r'};
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while(pq.size() != 0){
            pair rem = pq.remove();
            if(rem.r == er && rem.c == ec)
                return rem.path;

            for(int i = 0; i < dir.length; i++){
                int r = rem.r, c = rem.c, l = rem.dis;
                String path = rem.path;

                while(r >= 0 && c >= 0 && r < n && c < m && maze[r][c] == 0 && (r != er || c != ec)){
                    r += dir[i][0];
                    c += dir[i][1];
                    l++;
                }

                if(r != er || c != ec){
                    r -= dir[i][0];
                    c -= dir[i][1];
                    l--;
                }

                if(!vis[r][c]){
                    vis[rem.r][rem.c] = true;
                    pq.add(new pair(r, c, l, path + dstr[i]));
                }
            }
        }

        return "impossible";
    }

}
