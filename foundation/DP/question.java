import java.util.*;

public class question {

    //1035
    public int uncrossedLine(int[] A, int[] B, int N, int M, int[][]dp){
        for(int n = 0; n <= N; n++){
            for(int m = 0; m <= M; m++){
                if(n == 0 || m == 0){
                    dp[n][m] = 0;
                    continue;
                }

                if(A[n - 1] == B[m - 1]){
                    dp[n][m] = dp[n - 1][m - 1] + 1;
                }else 
                    dp[n][m] = Math.max(dp[n - 1][m], dp[n][m - 1]);
            }
        }
        return dp[N][M];
    }

    public static int maxUncrossedLine(int[] A, int[] B){
        int n = A.length;
        int m = B.length;
        int[][] dp = new int[n + 1][m + 1];

        return uncrossedLine(A, B, n, m, dp);
    }

    //72
    public static int minDistance(String s1, String s2, int n, int m, int[][]dp){
        if(n == 0 || m == 0)
            return dp[n][m] = (n == 0 ? m : n);
        
        if(dp[n][m] != -1)
            return dp[n][m];
        
        int insert = minDistance(s1, s2, n, m - 1, dp);
        int replace = minDistance(s1, s2, n - 1, m - 1, dp);
        int delete = minDistance(s1, s2, n - 1, m, dp);

        if(s1.charAt(n - 1) == s2.charAt(m - 1))
            return dp[n][m] = replace;
        else
            return dp[n][m] = Math.min(Math.min(insert, replace), delete) + 1;
    }

    public static int minDistance_DP(String s1, String s2, int N, int M, int[][]dp){
        for(int n = 0; n <= N; n++){
            for(int m = 0; m <= M; m++){
                if(n == 0 || m == 0){
                    dp[n][m] = (n == 0 ? m : n);
                    continue;
                }

                int insert = dp[n][m - 1];
                int replace = dp[n - 1][m - 1];
                int delete = dp[n - 1][m];

                if(s1.charAt(n - 1) == s2.charAt(m - 1))
                    dp[n][m] = replace;
                else
                    dp[n][m] = Math.min(Math.min(insert, replace), delete) + 1;
            }
        }
        return dp[N][M];
    }

    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][]dp = new int[n + 1][m + 1];

        for(int[] d: dp){
            Arrays.fill(d ,-1);
        }

        int ans = minDistance(word1, word2, n, m, dp);
        return ans;
    }

    // Cost of insert , replace and delete is {0,1,2};
    public static int minCost_DP(String s1, String s2, int N, int M, int[]cost, int[][]dp){
        for(int n = 0; n <= N; n++){
            for(int m = 0; m <= M; m++){
                if(n == 0 || m == 0){
                    dp[n][m] = (n == 0 ? m * cost[0] : n * cost[2]);
                    continue;
                }

                int insert = dp[n][m - 1];
                int replace = dp[n - 1][m - 1];
                int delete = dp[n - 1][m];

                if(s1.charAt(n - 1) == s2.charAt(m - 1))
                    dp[n][m] = replace;
                else
                    dp[n][m] = Math.min(Math.min(insert + cost[0], replace + cost[1]), delete + cost[2]);
            }
        }
        return dp[N][M];
    }

    public int minCost(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][]dp = new int[n + 1][m + 1];
        int []cost = new int[n];

        for(int[] d: dp){
            Arrays.fill(d ,-1);
        }

        int ans = minCost_DP(word1, word2, n, m, cost, dp);
        return ans;
    }

    
    // https://www.geeksforgeeks.org/edit-distance-and-lcs-longest-common-subsequence/?ref=rp
    // where we are allowed only two operations insert and delete, find edit
    // distance in this variation.

    public static int LCSS(String s1, String s2){
        int N = s1.length(), M = s2.length();
        int [][]dp = new int[N + 1][M  + 1];
        for(int n = 0; n <= N; n++){
            for(int m = 0; m <= M; m++){
                if(n == 0 || m == 0){
                    dp[n][m] = 0;
                    continue;
                }

                if(s1.charAt(n - 1) == s2.charAt(m - 1))
                    dp[n][m] = dp[n - 1][m - 1] + 1;
                else
                    dp[n][m] = Math.max(dp[n - 1][m], dp[n][m - 1]);
            }
        }
        return dp[N][M];
    }

    public static void editDistanceVariation(String s1, String s2){
        int n = s1.length(), m = s2.length();
        int LCSS = LCSS(s1, s2);

        int ans = (n - LCSS) + (m - LCSS);
        System.out.println(ans);
    }

    // https://practice.geeksforgeeks.org/problems/minimum-deletitions1648/1

    public static int LPSS(String str, int N, int M, int[][]dp){
        int n = str.length();
        for(int gap = 0; gap < n; gap++){
            for(int i = 0, j = gap; j < n; i++, j++){
                if(i >= j){
                    dp[i][j] = (i == j) ? 1 : 0;
                    continue;
                }

                if(str.charAt(i) == str.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2; //LPSS(str, i + 1, j - 1, dp)
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return dp[N][M];
    }

    public static int minimumNumberOfDeletions(String str){
        int n = str.length();
        int[][]dp = new int[n][n];

        int ans = n -LPSS(str, 0, n - 1, dp);
        return ans;
    }



    
    public static void main(String[] args) {
        
    }
}
