import java.util.Arrays;

import javax.lang.model.util.ElementScanner14;

public class l001twoPointer {
    /*
     * 1. Faith 2. Recursive Tree 3. Recursion Code 4. Convert Recursion code into
     * memoization 5. Observation 6. Tabulation 7. Optimization
     */

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

    // F(n) = F(n - 1) + F(n - 2)
    public static int fibo_memo(int n, int[] dp) {
        if (n <= 1) {
            return dp[n] = 1;
        }

        if (dp[n] != 0)
            return dp[n];

        int ans = fibo_memo(n - 1, dp) + fibo_memo(n - 2, dp);

        return dp[n] = ans;
    }

    public static int fibo_tabu(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {

            if (n <= 1) {
                dp[n] = 1;
                continue;
            }

            int ans = dp[n - 1] + dp[n - 2];
            dp[n] = ans;
        }

        return dp[N];
    }

    public static int fibo_opti(int n) {
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            int sum = a + b;
            a = b;
            b = sum;
        }

        return a;
    }

    // total no of paths from all given direction
    public static int mazePath_memo(int sr, int sc, int er, int ec, int[][] dir, int[][] dp) {
        if (sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }

        if (dp[sr][sc] != 0)
            return dp[sr][sc] = 0;

        int count = 0;
        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];

            while (r >= 0 && c >= 0 && r <= er && c <= ec)
                count += mazePath_memo(r, c, er, ec, dir, dp);
        }

        return dp[sr][sc] = count;
    }

    public static int mazePath_tabu(int SR, int SC, int er, int ec, int[][] dir, int[][] dp) {
        for (int sr = er; sr <= SR; sr--) {
            for (int sc = ec; sc <= SC; sc--) {
                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }

                if (dp[sr][sc] != 0)
                    dp[sr][sc] = 0;

                int count = 0;
                for (int[] d : dir) {
                    int r = sr + d[0];
                    int c = sc + d[1];

                    while (r <= er && c <= ec)
                        count += dp[r][c]; // mazePath_memo(r, c, er, ec, dir, dp);
                }

                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];
    }

    public static int mazePathJump_tabu(int SR, int SC, int er, int ec, int[][] dir, int[][] dp) {
        for (int sr = er; sr <= SR; sr--) {
            for (int sc = ec; sc <= SC; sc--) {
                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                for (int[] d : dir) {
                    int r = sr + d[0];
                    int c = sc + d[1];

                    while (r <= er && c < ec) {
                        count += dp[r][c];
                        r += d[0];
                        c += d[1];
                    }
                }

                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];
    }

    public static void mazePath_Set() {
        int sr = 0, sc = 0, er = 2, ec = 2;
        int[][] dp = new int[er + 1][ec + 1];
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 1, 1 } };
        System.out.println(mazePath_memo(sr, sc, er, ec, dir, dp));
        // System.out.println(mazePath_tabu(sr, sc, er, ec, dir, dp));
        System.out.println(mazePathJump_tabu(sr, sc, er, ec, dir, dp));

        display2D(dp);
    }

    // Gold mine
    public static int goldMine_memo(int sr, int sc, int[][] mat, int[][] dir, int[][] dp) {
        if (sc == mat[0].length - 1)
            return dp[sr][sc] = mat[sr][sc];

        if (dp[sr][sc] != -1)
            return dp[sr][sc];

        int maxGold = 0;
        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];

            if (r >= 0 && c >= 0 && r < mat.length && c < mat[0].length) {
                maxGold = Math.max(maxGold, goldMine_memo(r, c, mat, dir, dp) + mat[sr][sc]);
            }
        }

        return dp[sr][sc] = maxGold;
    }

    public static int goldMine_Tabu(int SR, int SC, int[][] mat, int[][] dir, int[][] dp) {
        for (int sc = mat[0].length - 1; sc >= SC; sc--) {
            for (int sr = mat.length - 1; sr >= SR; sr--) {
                if (sc == mat[0].length - 1) {
                    dp[sr][sc] = mat[sr][sc];
                    continue;
                }

                int maxGold = 0;
                for (int[] d : dir) {
                    int r = sr + d[0];
                    int c = sc + d[1];

                    if (r >= 0 && c >= 0 && r < mat.length && c < mat[0].length)
                        maxGold = Math.max(maxGold, dp[r][c] + mat[sr][sc]);
                }

                dp[sr][sc] = maxGold;
            }
        }

        int maxGold = 0;
        for (int r = 0; r < mat.length; r++) {
            maxGold = Math.max(maxGold, dp[r][0]);
        }

        return maxGold;
    }

    public static int maxGold(int n, int m, int mat[][]) {
        int[][] dir = { { -1, 1 }, { 1, 1 }, { 0, 1 } };
        int[][] dp = new int[n][m];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int maxgold = 0;
        for (int r = 0; r < n; r++) {
            maxgold = Math.max(maxgold, goldMine_memo(r, 0, mat, dir, dp));
        }

        return maxgold;
    }

    // 70
    public int climbStairs(int n) {
        int a = 1, b = 1;
        for (int i = 0; i < n; i++) {
            int sum = a + b;
            a = b;
            b = sum;
        }

        return a;
    }

    // 746
    public static int minCost_memo(int n, int[] cost, int[] dp) {
        if (n <= 1) {
            return dp[n] = cost[n];
        }

        if (dp[n] != 0)
            return dp[n];

        int minCost = Math.min(minCost_memo(n - 1, cost, dp), minCost_memo(n - 2, cost, dp));

        return dp[n] = minCost + (n == cost.length ? 0 : cost[n]);
    }

    public static int minCost_tabu(int N, int[] cost, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = cost[n];
                continue;
            }
            int minCost;
            if (n < cost.length)
                minCost = Math.min(dp[n - 1], dp[n - 2]) + cost[n];
            else
                minCost = Math.min(dp[n - 1], dp[n - 2]);

            dp[n] = minCost;
        }

        return dp[N];
    }

    public int minCost_Opti(int[] cost) {
        int n = cost.length;
        int a = cost[0];
        int b = cost[1];

        for (int i = 2; i < n; i++) {
            int sum = Math.min(a, b) + cost[i];
            a = b;
            b = sum;
        }
        return Math.min(a, b);
    }

    // board path
    public static int boardPath_memo(int sp, int ep, int[] dp) {
        if (sp == ep) {
            return dp[sp] = 1;
        }

        if (dp[sp] != 0)
            return dp[sp];

        int count = 0;
        for (int dice = 1; dice <= 6 && sp + dice <= ep; dice++) {
            count += boardPath_memo(sp, ep, dp);
        }

        return dp[sp] = count;
    }

    public static int boardPath_Tabu(int SP, int EP, int[] dp) {
        for (int sp = EP; sp >= SP; sp--) {
            if (sp == EP) {
                dp[sp] = 1;
                continue;
            }

            int count = 0;
            for (int dice = 1; dice <= 6; dice++) {
                count += dp[sp + dice];
            }
            dp[sp] = count;
        }

        return dp[SP];
    }

    // =========================================================================================

    // 91
    public static int numDecodings_memo_last(String s, int n, int[] dp) {
        if (n == 0) {
            return dp[n] = 1;
        }

        if (dp[n - 1] != -1) {
            return dp[n];
        }

        int count = 0;
        if (s.charAt(n - 1) > '0')
            count += numDecodings_memo_last(s, n - 1, dp);

        if (n > 1) {
            int num = (s.charAt(n - 2) - '0') * 10 + (s.charAt(n - 1) - '0');
            if (num <= 26 && num >= 10)
                count += numDecodings_memo_last(s, n - 2, dp);
        }

        return dp[n] = count;
    }

    public static int numDecodings_memo_first(String s, int idx, int[] dp) {
        if (idx == s.length()) {
            return dp[idx] = 1;
        }

        if (dp[idx] != -1) {
            return dp[idx];
        }

        if (s.charAt(idx) == '0') {
            return dp[idx] = 0;
        }

        int count = 0;
        count += numDecodings_memo_first(s, idx + 1, dp);

        if (idx < s.length() - 1) {
            int num = (s.charAt(idx) - '0') * 10 + (s.charAt(idx + 1) - '0');
            if (num <= 26)
                count += numDecodings_memo_first(s, idx + 2, dp);
        }

        return dp[idx] = count;
    }

    public static int numDecodings_tabu(String s, int N, int[] dp) {
        for (int n = s.length(); n >= 0; n--) {
            if (n == s.length()) {
                dp[n] = 1;
                continue;
            }

            if (s.charAt(n) == '0') {
                dp[n] = 0;
                continue;
            }

            int count = 0;
            count += dp[n + 1];

            if (n < s.length() - 1) {
                int num = (s.charAt(n) - '0') * 10 + (s.charAt(n + 1) - '0');
                if (num <= 26)
                    count += dp[n + 2];
            }

            dp[n] = count;
        }

        return dp[N];
    }

    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        // int ans = numDecodings_memo_last(s, n, dp);
        int ans = numDecodings_memo_first(s, 0, dp);

        return ans;
    }

    // 639
    public static long numDecodingsStar_memo(String s, int idx, long[] dp) {
        if (idx == s.length()) {
            return dp[idx] = 1;
        }

        if (dp[idx] != -1)
            return dp[idx];

        if (s.charAt(idx) == '0') {
            return dp[idx] = 0;
        }

        long count = 0;
        int mod = (int) 1e9 + 7;
        char ch1 = s.charAt(idx);
        if (ch1 == '*') {
            count = (count + 9 * numDecodingsStar_memo(s, idx + 1, dp)) % mod;

            if (idx < s.length() - 1) {
                if (s.charAt(idx + 1) == '*')
                    count = (count + 15 * numDecodingsStar_memo(s, idx + 2, dp)) % mod;
                else if (s.charAt(idx + 1) >= '0' && s.charAt(idx + 1) <= '6')
                    count = (count + 2 * numDecodingsStar_memo(s, idx + 2, dp)) % mod;
                else if (s.charAt(idx + 1) >= '7')
                    count = (count + numDecodingsStar_memo(s, idx + 2, dp)) % mod;
            }
        } else {
            count = (count + numDecodingsStar_memo(s, idx + 1, dp)) % mod;

            if (idx < s.length() - 1) {
                if (s.charAt(idx + 1) != '*') {
                    int num = (s.charAt(idx) - '0') * 10 + (s.charAt(idx + 1) - '0');
                    if (num <= 26)
                        count = (count + 1 * numDecodingsStar_memo(s, idx + 2, dp)) % mod;
                } else {
                    if (ch1 == '1')
                        count = (count + 9 * numDecodingsStar_memo(s, idx + 2, dp)) % mod;
                    else if (ch1 == '2')
                        count = (count + 6 * numDecodingsStar_memo(s, idx + 2, dp)) % mod;
                }
            }
        }

        return dp[idx] = count;
    }

    public static long numDecodingsStar_tabu(String s, int IDX, long[] dp) {
        for (int idx = s.length(); idx >= 0; idx--) {
            if (idx == s.length()) {
                dp[idx] = 1;
                continue;
            }

            if (s.charAt(idx) == '0') {
                 dp[idx] = 0;
                 continue;
            }

            long count = 0;
            int mod = (int) 1e9 + 7;
            char ch1 = s.charAt(idx);
            if (ch1 == '*') {
                count = (count + 9 * dp[idx + 1]) % mod;

                if (idx < s.length() - 1) {
                    if (s.charAt(idx + 1) == '*')
                        count = (count + 15 * dp[idx + 2]) % mod;
                    else if (s.charAt(idx + 1) >= '0' && s.charAt(idx + 1) <= '6')
                        count = (count + 2 * dp[idx + 2]) % mod;
                    else if (s.charAt(idx + 1) >= '7')
                        count = (count + dp[idx + 2]) % mod;
                }
            } else {
                count = (count + dp[idx + 1]) % mod;

                if (idx < s.length() - 1) {
                    if (s.charAt(idx + 1) != '*') {
                        int num = (s.charAt(idx) - '0') * 10 + (s.charAt(idx + 1) - '0');
                        if (num <= 26)
                            count = (count + 1 * dp[idx + 2]) % mod;
                    } else {
                        if (ch1 == '1')
                            count = (count + 9 * dp[idx + 2]) % mod;
                        else if (ch1 == '2')
                            count = (count + 6 * dp[idx + 2]) % mod;
                    }
                }
            }

            dp[idx] = count;
        }

        return dp[IDX];
    }

    public int numDecodingsStar(String s) {
        int n = s.length();
        long[] dp = new long[n + 1];
        Arrays.fill(dp, -1);

        long ans = numDecodingsStar_memo(s, 0, dp);

        return (int) ans;
    }

    // https://practice.geeksforgeeks.org/problems/friends-pairing-problem5425/1
    public static long friendPairing_memo(int n, long[] dp) {
        if (n <= 1) {
            return dp[n] = 1;
        }

        if (dp[n] != 0) {
            return dp[n];
        }

        int mod = (int) 1e9 + 7;
        long single = friendPairing_memo(n - 1, dp);
        long pairing = friendPairing_memo(n - 2, dp) * (n - 1);

        return dp[n] = (single + pairing % mod) % mod;
    }

    public static long friendPairing_tabu(int N, long[] dp) {
        int mod = (int) 1e9 + 7;
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = 1;
                continue;
            }

            long single = dp[n - 1];
            long pairing = dp[n - 2] * (n - 1);

            dp[n] = (single + pairing % mod) % mod;
        }

        return dp[N];
    }

    public long countFriendsPairings(int n) {
        long dp[] = new long[n + 1];

        return friendPairing_memo(n, dp);
    }


    // https://www.geeksforgeeks.org/count-the-number-of-ways-to-divide-n-in-k-groups-incrementally/
    
    public static int divideInKGroup(int n, int k, int[][]dp){
        if(k == 1 || n == 1){
            return dp[n][k] = 1;
        }

        if(dp[n][k] == -1){
            return dp[n][k];
        }

        int selfSet = divideInKGroup(n - 1, k - 1, dp);
        int partOfAnotherSet = divideInKGroup(n - 1, k, dp) * k;

        return dp[n][k] = selfSet + partOfAnotherSet;
    }
    
    public static int divideInKGroup_tabu(int N, int K, int[][]dp){
        for(int n = 1; n <= N; n++){
            for(int k = 1; k <= K; k++){
                if(k > n)
                    break;

                if(k == 1 || n == 1){
                    dp[n][k] = 1;
                    continue;
                }

                int selfSet = dp[n - 1][k - 1];
                int partOfAnotherSet = dp[n - 1][k] * k;

                dp[n][k] = selfSet + partOfAnotherSet;
            }
        }

        return dp[N][K];
    }


    public static void divideInKGroup(){
        int n = 5, k = 3;
        int[][] dp = new int[n][k];
        for(int[] d : dp){
            Arrays.fill(d, -1);
        }

        System.out.println(divideInKGroup(n, k, dp));
    }



    public static void main(String[] args) {
        mazePath_Set();
    }

}
