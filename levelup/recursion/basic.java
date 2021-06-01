import java.io.*;
import java.util.*;
public class basic {

    public static int floodfill(int sr, int sc, int er, int ec, int[][]dir, String[] sdir, boolean[][]vis, String psf){

        if(sr == er && sc == ec){
            System.out.println(psf);
            return 1;
        }

        int count = 0;
        vis[sr][sc] = true;
        for(int d = 0; d < dir.length; d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if(r >= 0 && c >= 0 && r <= er && c <= ec && !vis[r][c]){
                count += floodfill(r, c, er, ec, dir, sdir, vis, psf + sdir[d] + " ");
            }
        }
        vis[sr][sc] = false;
        return count;
    }

    public static class pathPair{
        int len = 0;
        String psf = "";
        int count = 0;

        pathPair(int len, String psf, int count){
            this.len = len;
            this.psf = psf;
            this.count = count;
        }
    }

    public static pathPair floodFill_longestPath(int sr, int sc, int er, int ec, int[][]dir, String[]sdir, boolean[][]vis){

        if(sr == er && sc == ec){
            return new pathPair(0, "", 1);
        }

        vis[sr][sc] = true;
        pathPair myAns = new pathPair(0, "", 0);
        for(int d = 0; d < dir.length; d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if(r >= 0 && c >= 0 && r <= er && c <= ec && !vis[r][c]){
                pathPair recAns = floodFill_longestPath(r, c, er, ec, dir, sdir, vis);
                if(recAns.len + 1 > myAns.len){
                    myAns.len = recAns.len + 1;
                    myAns.psf = sdir[d] + recAns.psf;
                }
                myAns.count += recAns.count;
            }
        }
        vis[sr][sc] = false;
        return myAns;
    }

    public static pathPair floodFill_shortestPath(int sr, int sc, int er, int ec, int[][]dir, String[]sdir, boolean[][]vis){

        if(sr == er && sc == ec){
            return new pathPair(0, "", 1);
        }

        vis[sr][sc] = true;
        pathPair myAns = new pathPair((int)1e8, "", 0);
        for(int d = 0; d < dir.length; d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if(r >= 0 && c >= 0 && r <= er && c <= ec && !vis[r][c]){
                pathPair recAns = floodFill_shortestPath(r, c, er, ec, dir, sdir, vis);
                if(recAns.len + 1 < myAns.len){
                    myAns.len = recAns.len + 1;
                    myAns.psf = sdir[d] + recAns.psf;
                }
                myAns.count += recAns.count;
            }
        }
        vis[sr][sc] = false;
        return myAns;
    }

    


    public static void main(String[] args) {
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
        String[] sdir = {"L", "R", "D", "U", "W", "S", "N", "E"};

        int n = 3, m = 3;
        boolean[][] vis = new boolean[n][m];
        // System.out.println(floodfill(0, 0, n - 1, m - 1, dir, sdir, vis, " "));
        pathPair ans = floodFill_shortestPath(0, 0, n - 1, m - 1, dir, sdir, vis);
        pathPair ans2 = floodFill_longestPath(0, 0, n - 1, m - 1, dir, sdir, vis);
        System.out.println("path: " + ans.psf + "\n" +  "Length: "  + ans.len +  "\n" + "Count: " + ans.count);
        System.out.println();
        System.out.println("path: " + ans2.psf + "\n" +  "Length: "  + ans2.len +  "\n" + "Count: " + ans2.count);

        
    }
}