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

}
