import java.util.*;

public class question {
    // https://practice.geeksforgeeks.org/problems/special-matrix4201/1#
    public static int dfs(int sr, int sc, int er, int ec, boolean[][]block, int[][]dp){
        int mod = (int)1e9 + 7;
        if(sr == er && sc == ec)
            return dp[sr][sc] = 1;
    
        if(dp[sr][sc] != -1)
            return dp[sr][sc];
            
        int count = 0;
        if(sc + 1 <= ec && !block[sr][sc + 1])
            count = (count % mod + dfs(sr, sc + 1, er, ec, block, dp) % mod) % mod;
        
        if(sr + 1 <= er && !block[sr + 1][sc])
            count = (count % mod + dfs(sr + 1, sc, er, ec, block, dp) % mod) % mod;
        
        return dp[sr][sc] = count;
    }
    public int FindWays(int n, int m, int[][] blocked_cells)
    {
        n++;
        m++;
        boolean[][]block = new boolean[n][m];
        
        for(int[] b: blocked_cells)
            block[b[0]][b[1]]= true;
        
        if(block[1][1] || block[n - 1][m - 1])
            return 0;
            
        int [][]dp = new int[n][m];
        for(int[] d: dp)
            Arrays.fill(d, -1);
        
        return dfs(1,1,n-1, m-1,block,dp);
    }


    // https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1

    public static int floodfill(int sr, int sc, int er, int ec, int[][]dir, String[] sdir, int[][]vis, String psf, ArrayList<String>res){

        if(sr == er && sc == ec){
            res.add(psf);
            return 1;
        }

        int count = 0;
        vis[sr][sc] = 0;
        for(int d = 0; d < dir.length; d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if(r >= 0 && c >= 0 && r <= er && c <= ec && vis[r][c] == 1){
                count += floodfill(r, c, er, ec, dir, sdir, vis, psf + sdir[d] , res);
            }
        }
        vis[sr][sc] = 1;
        return count;
    }
    public static ArrayList<String> findPath(int[][] mat, int n) {
        int[][]dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        String[]sdir = {"L", "R", "U", "D"};
        ArrayList<String> res = new ArrayList<>();
        
        if(mat[0][0] == 0 || mat[n - 1][n - 1] == 0)
            return res;
        
        int ans = floodfill(0, 0, n - 1, n - 1, dir, sdir, mat, "", res);
        Collections.sort(res);
        
        return res;
        
    }

    //1219
    public int getMaximumGold(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int maxGold = 0;
        for (int r = 0; r < m; r++) {
          for (int c = 0; c < n; c++) {
            maxGold = Math.max(maxGold, findMaxGold(grid, m, n, r, c));
          }
        }
        return maxGold;
    }
        
    public static int findMaxGold(int[][] grid, int m, int n, int r, int c) {
        int[] DIR = new int[]{0, 1, 0, -1, 0};
          
        if (r < 0 || r == m || c < 0 || c == n || grid[r][c] == 0)    return 0;
            int origin = grid[r][c];
        grid[r][c] = 0;
        int maxGold = 0;
        for (int i = 0; i < 4; i++)
          maxGold = Math.max(maxGold, findMaxGold(grid, m, n, DIR[i] + r, DIR[i+1] + c));
        grid[r][c] = origin;
        return maxGold + origin;
    }

}
