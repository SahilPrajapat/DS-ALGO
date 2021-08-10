import java.util.*;

public class dfs_question {

    public void dfs_numIsland(char[][] grid, int i, int j, int[][]dir){
        grid[i][j] = '2';
        int n = grid.length, m = grid[0].length;
        for(int d = 0; d < 4; d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == '1')
                dfs_numIsland(grid, r, c, dir);
        }
    }
    

    public int numIslands(char[][] grid) {
        int n = grid.length, m = grid[0].length, islandcount = 0; 

        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < m; j++){
                if(grid[n][m] == '1'){
                    dfs_numIsland(grid, i, j, dir);
                    islandcount++;
                }
            }
        }
        return islandcount;
    }

    //695
    public static int dfs_maxArea(int[][]grid, int i, int j, int[][]dir){
        grid[i][j] = 0;
        int n = grid.length, m = grid[0].length;

        int size = 0;
        for(int d = 0; d < 4; d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1){
                size += dfs_maxArea(grid, r, c, dir);
            }
        }
        return size + 1;
    }


    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length, m = grid[0].length, maxArea = 0;

        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1){
                    int s = dfs_maxArea(grid, i, j, dir);
                    maxArea = Math.max(maxArea, s);
                }
            }
        }

        return maxArea;
    }

    //463
    public int islandPerimeter(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] dir = { {1, 0}, {0 , 1}};
        
        int oneCount = 0, nbrCount = 0;
        for(int  i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1){
                    oneCount++;
                    for(int d = 0; d < 2; d++){
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];
                        

                        if(r < n && c < m && grid[r][c] == 1){
                            nbrCount++;
                        }
                    }
                }
            }
        }

        int parameter = 4 * oneCount - 2 * nbrCount;
        return parameter;
    }

    //130

    public static void surrounded_dfs(char[][] board, int i, int j, int[][] dir){
        int n = board.length, m = board[0].length;
        board[i][j] = '$';
        for(int d = 0; d < 4; d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            if(r >= 0 && c >= 0 && r < n && c < m && board[r][c] == 'O'){
                surrounded_dfs(board, r, c, dir);
            }
        }
    }


    public void solve(char[][] board) {
        int n = board.length, m = board[0].length;
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if((i == 0 || j == 0 || i == n - 1 || j == m - 1) && board[i][j] == 'O'){
                    surrounded_dfs(board, i, j, dir);
                }

            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(board[i][j] == '$')
                    board[i][j] = 'O';
                else 
                    board[i][j] = 'X';
            }
        }
    }


    int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    String[] dirS = { "D", "U", "R", "L" };
    StringBuilder sb;


    public void numberofDistinctIslands_dfs(int[][] grid, int i, int j) {
        grid[i][j] = 0;
        int n = grid.length, m = grid[0].length;
        for(int d = 0; d < 4; d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1){
                sb.append(dirS[d]);
                numberofDistinctIslands_dfs(grid, r, c);
                sb.append("b");
            }
        }
    }

    public int numberofDistinctIslands(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        HashSet<String> set = new HashSet<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1){
                    sb = new StringBuilder();
                    numberofDistinctIslands_dfs(grid, i, j);
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }

    //1905
    public boolean countSubIslands_dfs(int[][] grid1, int[][] grid2, int i, int j) {
        grid2[i][j] = 0;
        boolean res = true;
        int n = grid2.length, m = grid2[0].length;
        for(int d = 0; d < 4; d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            if(r >= 0 && c >= 00 && r < n && c < m && grid2[r][c] == 1){
                res = countSubIslands_dfs(grid1, grid2, r, c) && res;
            }
        }
        return res && grid1[i][j] == 1;
    }

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int n = grid2.length, m = grid2[0].length;
        int count = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid2[i][j] == 1){
                    count += countSubIslands_dfs(grid1, grid2, i, j) ? 1 : 0;
                }
            }
        }
        return count;
    }


    //1376

    public static int dfs_informTime(int src, ArrayList<Integer>[] graph, int[] informTime){
        int max = 0;
        for(int e : graph[src]){
            max = Math.max(max, dfs_informTime(e, graph, informTime));
        }

        return max + informTime[src];
    }

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        int src = 0;
        for(int i = 0; i < n; i++){
            if(manager[i] == -1){
                src = i;
            }else{
                graph[manager[i]].add(i);
            }
        }

        return dfs_informTime(src, graph, informTime);

    }

}
