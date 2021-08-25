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

    public int maximum(int... arr){
        int max = arr[0];
        for(int ele: arr)
            max = Math.max(max, ele);

        return max;
    }

    //1458
    public int maxDotProduct_memo(int[] num1, int [] num2, int n, int m, int[][] dp){
        if(n == 0 || m ==0)
            return dp[n][m] = -(int)1e8;

        if(dp[n][m] != -(int)1e9)
            return dp[n][m];

        int val = num1[n - 1] * num2[m - 1];
        int acceptTwoNumer = maxDotProduct_memo(num1, num2, n - 1, m - 1, dp);
        int a = maxDotProduct_memo(num1, num2, n - 1, m, dp);
        int b = maxDotProduct_memo(num1, num2, n, m - 1, dp);

        return dp[n][m] = maximum(val, acceptTwoNumer, a, b);
    }

    public int maxDotProduct_tabu(int[] num1, int [] num2, int N, int M, int[][] dp){
        for(int n = 0; n <= N; n++){
            for(int m = 0; m <= M; m++){
                if(n == 0 || m ==0){
                    dp[n][m] = -(int)1e8;
                    continue;
                }
        
                int val = num1[n - 1] * num2[m - 1];
                int acceptTwoNumer =  dp[n - 1][m - 1] + val; 
                int a = dp[n - 1][m];
                int b = dp[n][m - 1]; 
        
                dp[n][m] = maximum(val, acceptTwoNumer, a, b);

            }
        }

        return dp[N][M];
    }

    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n + 1][m + 1];
        for(int[] d: dp){
            Arrays.fill(d, -(int)1e9);
        }

        return maxDotProduct_memo(nums1, nums2, n, m, dp);
    }

    //1035
    public int maxUncrossedLines_memo(int[] A, int[] B, int n, int m, int[][]dp){
        if(n == 0 || m == 0)
            return dp[n][m] = 0;

        if(dp[n][m] != -1)
            return dp[n][m];

        if(A[n - 1] == B[m - 1])
            return dp[n][m] = maxUncrossedLines_memo(A, B, n - 1, m - 1, dp) + 1;
        else
            return dp[n][m] = Math.max(maxUncrossedLines_memo(A, B, n - 1, m, dp), maxUncrossedLines_memo(A, B, n, m - 1, dp));
    }

    public int maxUncrossedLines_tau(int[] A, int[] B, int N, int M, int[][]dp){
        for(int n = 0; n <= N; n++){
            for(int m = 0; m <= M; m++){
                if(n == 0 || m == 0){
                    dp[n][m] = 0;
                    continue;
                }

                if(A[n - 1] == B[m - 1])
                    dp[n][m] = dp[n - 1][m - 1] + 1;
                else
                    dp[n][m] = Math.max(dp[n - 1][m], dp[n][m - 1]);
            }
        }

        return dp[N][M];
    }

    public int maxUncrossedLines(int[] A, int[] B){
        int n = A.length;
        int m = B.length;
        int [][] dp = new int[n + 1][m + 1];
        for(int[] d : dp)
            Arrays.fill(d, -1);
        return maxDotProduct_memo(A, B, n, m, dp);
    }

    //72
    public int minDistance_memo(String word1, String word2, int n, int m, int[][] dp){
        if(n == 0)
            dp[n][m] = m; //insert

        if(m == 0)
            dp[n][m] = n; //delete
        
        if(dp[n][m] != -1)
            return dp[n][m];

        int insert = minDistance_memo(word1, word2, n, m - 1, dp);
        int delete = minDistance_memo(word1, word2, n - 1, m, dp);
        int replace = minDistance_memo(word1, word2, n - 1, m - 1, dp);
        if(word1.charAt(n - 1) == word2.charAt(m - 1))
            return dp[n][m] = replace;
        else
            return dp[n][m] = Math.min(Math.min(insert, delete), replace) + 1;
    }

    public int minDistance_tabu(String word1, String word2, int N, int M, int[][] dp){
        for(int n = 0; n <= N; n++){
            for(int m = 0; m <= M; m++){
                if(n == 0 || m == 0){
                    dp[n][m] = (n == 0 ? m : n);
                    continue;
                }

                int insert = dp[n][m - 1];
                int delete = dp[n - 1][m];
                int replace = dp[n - 1][m - 1];

                if(word1.charAt(n - 1) == word2.charAt(m - 1))
                    dp[n][m] = replace;
                else
                    dp[n][m] = Math.min(Math.min(insert, delete), replace) + 1;
            }
        }

        return dp[N][M];
    }

    public int minDistance(String word1, String word2){
        int n = word1.length();
        int m = word2.length();
        int [][] dp = new int[n + 1][m + 1];
        for(int[] d: dp){
            Arrays.fill(d, -1);
        }

        return minDistance_memo(word1, word2, n, m, dp);
    }

    //583
    public int minDistance_memo2(String word1, String word2, int n, int m, int[][] dp){
        if(n == 0)
            dp[n][m] = m; //insert

        if(m == 0)
            dp[n][m] = n; //delete
        
        if(dp[n][m] != -1)
            return dp[n][m];

        int insert = minDistance_memo2(word1, word2, n, m - 1, dp);
        int delete = minDistance_memo2(word1, word2, n - 1, m, dp);
        int replace = minDistance_memo2(word1, word2, n - 1, m - 1, dp);
        if(word1.charAt(n - 1) == word2.charAt(m - 1))
            return dp[n][m] = replace;
        else
            return dp[n][m] = Math.min(insert, delete) + 1;
    }
    
    public int minDistance2(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int [][] dp = new int[n + 1][m + 1];
        for(int[] d: dp){
            Arrays.fill(d, -1);
        }
        
        return minDistance_memo2(word1, word2, n, m, dp);
    }

    //longest common subsequence method
    public static int LCSS_dp2(String str, int N, String str2, int M, int[][]dp){
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
    
    public int minDistance3(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int [][] dp = new int[n + 1][m + 1];
        for(int[] d: dp){
            Arrays.fill(d, -1);
        }
        
        return m + n - (2 * LCSS_dp2(word1, n, word2, m, dp));
    }

    //115
    public int numDistinct_memo(String s, String t, int n, int m, int[][] dp){
        if(m == 0 || n < m)
            return dp[n][m] = (m == 0 ? 1 : 0);

        if(dp[n][m] != -1)
            return dp[n][m];

        int a = numDistinct_memo(s, t, n - 1, m - 1, dp);
        int b = numDistinct_memo(s, t, n - 1, m, dp);

        if(s.charAt(n - 1) == t.charAt(m - 1))
            return dp[n][m] = a + b;
        else
            return dp[n][m] = b;
    }

    public int numDistinct_tabu(String s, String t, int N, int M, int[][] dp){
        for(int n = 0; n <= N; n++){
            for(int m = 0; m <= M; m++){
                if(m == 0 || n < m){
                    dp[n][m] = (m == 0 ? 1 : 0);
                    continue;
                }

                int a = dp[n - 1][m - 1];
                int b = dp[n - 1][m];

                if(s.charAt(n - 1) == t.charAt(m - 1))
                    dp[n][m] = a + b;
                else
                    dp[n][m] = b;
            }   
        }

        return dp[N][M];
    }

    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        int [][] dp = new int[n + 1][m + 1];
        for(int[] d : dp){
            Arrays.fill(d, -1);
        }

        return numDistinct_memo(s, t, n, m, dp); 
    }



    public static void main(String[] args) {
        
    }
}
