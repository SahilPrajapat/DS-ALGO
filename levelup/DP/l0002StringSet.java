import java.util.*;

public class l0002StringSet {

    public static void display(int[] dp) {
        for (int ele : dp) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void display2D(int[][] dp) {
        for (int[] ar : dp) {
            display(ar);
        }
        System.out.println();
    }

    //longest palindromic subsequence
    public static int lpss(String s, int si, int ei, int[][]dp){
        if(si >= ei)
            return dp[si][ei] = (si == ei) ? 1 : 0;

        if(dp[si][ei] != 0)
            return dp[si][ei];

        if(s.charAt(si) != s.charAt(ei)){
            dp[si][ei] = Math.max(lpss(s, si + 1, ei, dp), lpss(s, si, ei - 1, dp));
        }else {
            dp[si][ei] = lpss(s, si + 1, ei - 1, dp) + 2;
        }

        return dp[si][ei];
    }
    
    public static int lpss_dp(String s, int SI, int EI, int[][]dp){
        int n = s.length();
        for(int gap = 0; gap < n; gap++){
            for(int si = 0, ei = gap; ei < n; si++, ei++){
                
                if(si >= ei){
                    dp[si][ei] = (si == ei) ? 1 : 0;
                    continue;
                }
                
                if(s.charAt(si) != s.charAt(ei))
                    dp[si][ei] = Math.max(dp[si + 1][ei], dp[si][ei - 1]);
                else
                    dp[si][ei] = dp[si + 1][ei - 1] + 2;
                
            }
        }
        
        return dp[SI][EI];
    }

    public static String lpss_ReverseEngi(String s, int si, int ei, int[][] dp){
        if(si >= ei)
            return si == ei ? s.charAt(si) + "" : "";

        if(si == ei)
            return s.charAt(si) + lpss_ReverseEngi(s, si + 1, ei - 1, dp) + s.charAt(si);
        else if(dp[si + 1][ei] > dp[si][ei - 1])
            return lpss_ReverseEngi(s, si + 1, ei, dp);
        else
            return lpss_ReverseEngi(s, si, ei - 1, dp);
    }

    public void longestPalinSubseq(String S){
        int n = S.length();
        int [][] dp = new int[n][n];

        // lpss(S, 0, n - 1, dp);
        System.out.println(lpss_ReverseEngi(S, 0, n - 1, dp));
    }

    //longest common subsequence
    //1143
    public static int LCSS(String str, int n, String str2, int m, int[][] dp){
        if(n == 0 || m == 0)
            return dp[n][m] = 0;

        if(dp[n][m] != -1)
            return dp[n][m];

        if(str.charAt(n - 1) == str2.charAt(m - 1))
            return dp[n][m] = LCSS(str, n - 1, str2, m -  1, dp) + 1;
        else
            return dp[n][m] = Math.max(LCSS(str, n - 1, str2, m, dp), LCSS(str, n, str2, m - 1, dp));
    }

    public static int LCSS_dp(String str, int N, String str2, int M, int[][]dp){
        for(int n = 0; n <= N; n++){
            for(int m = 0; m <= M; m++){
                
                if(n == 0 || m == 0){
                    dp[n][m] = 0;
                    continue;
                }

                if(str.charAt(n - 1) == str2.charAt(m - 1))
                    dp[n][m] = dp[n - 1][m - 1] + 1;
                else
                    dp[n][m] = Math.max(dp[n- 1][m], dp[n][m - 1]);
            }
        }

        return dp[N][M];
    }

    public int longestCommonSubsequence(String s1, String s2){
        int n = s1.length(), m = s2.length();
        int [][] dp = new int[n][m];

        for(int[] d: dp)
            Arrays.fill(d, -1);

        int ans = LCSS(s1, n, s2, m, dp);
        return ans;
    }


    //longest Palindromic substring
    public static String longestPalin(String S){
        int n = S.length();
        boolean[][] dp = new boolean[n][n];

        int len = 0, si = 0;
        for(int gap = 0; gap < n; gap++){
            for(int i = 0, j = gap; j < n; i++, j++){
                if(gap == 0)
                    dp[i][j] = true;
                else if(gap == 1 && S.charAt(i) == S.charAt(j))
                    dp[i][j] = true;
                else
                    dp[i][j] = S.charAt(i) == S.charAt(j) && dp[i + 1][j - 1];

                if(dp[i][j] && j - i + 1 > len){
                    len = j - i + 1;
                    si = i;
                }
            }
        }

        return S.substring(si, si + len);
    }

    // longest common substring
    public static int longestCommonSubstring(String str, String str2){
        int n = str.length(), m = str2.length();
        int[][] dp = new int[n + 1][m + 1];
		int ans = 0 ;
		for(int i = 1 ; i <= n; i++) {
			for(int j = 1 ; j <= m; j++) {
				if(str.charAt(i - 1) == str2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}
				ans = Math.max(ans, dp[i][j]);
			}
		}
		return ans;
    }
}
