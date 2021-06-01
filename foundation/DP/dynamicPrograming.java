// import java.io.*;
import java.util.*;

public class dynamicPrograming {
    public static Scanner scn = new Scanner(System.in);

    public static int fibn(int n){
        if(n == 0 || n == 1){
            return n;
        }

        int fibn1 = fibn(n - 1);
        int fibn2 = fibn(n - 2);
        int fib = fibn1 + fibn2;

        return fib;
    }
// this is the dynamic code it store the repetative value of the code
    public static int fibMemo(int n, int[]qb){
        if(n == 0 || n == 1){
            return n;
        }
        if(qb[n] != 0){
            return qb[n];
        }

        int fibn1 = fibMemo(n - 1, qb);
        int fibn2 = fibMemo(n - 2, qb);
        int fib = fibn1 + fibn2;

        return fib;
    }

    public static int countPath(int n, int[]qb){

        if(n == 0){
            return 1;
        }else if(n < 0){
            return 0;
        }

        if(qb[n] < 0){
            return qb[n];
        }
        int nm1 = countPath(n - 1, qb);
        int nm2 = countPath(n - 2, qb);
        int nm3 = countPath(n - 3, qb);

        int cp = nm1 + nm2 + nm3;
        qb[n] = cp;
        return qb[n];
    }

    public static int countPathTab(int n){
        int[]dp = new int[n + 1];

        dp[0] = 1;
        for(int i = 1; i <= n; i++){
            if(i == 1){
                dp[i] = dp[i - 1];
            }else if(i == 2){
                dp[i] = dp[i - 1] + dp[i - 2];
            }else{
                dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            }
        }
        return dp[n];
    }

    public static int climbStairsWithVariableJump(int n, int[]arr){
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for(int i = n - 1; i >=0; i--){
            for(int j = 1; j <= arr[i] && i + j < dp.length; j++){
                dp[i] += dp[i + j];
            }
        }
        return dp[0];
    }

    public static Integer climbStairsWithMinimumMove(int n, int[]arr){

        Integer[] dp = new Integer[n + 1];
        dp[n] = 0;

        for(int i = n - 1; i >= 0; i--){
            if(arr[i] > 0){
                int min = Integer.MAX_VALUE;

                for(int j = 1; j <= arr[i] && i + j < dp.length; j++){
                    if(dp[i + j] != null){
                        min = Math.min(min, dp[i + j]);
                    }
                }

                if (min != Integer.MAX_VALUE) {
                    dp[i] = min + 1;
                }
            }
        }

        return dp[0];
    }

    public static int minCost(int n, int m, int[][]arr){
        int[][]dp = new int[n][m];

        for(int i = dp.length - 1; i >= 0; i--){
            for(int j = dp[0].length - 1; j >= 0; j--){
                if(i == dp.length - 1 && j == dp[0].length - 1){
                    dp[i][j] = arr[i][j];
                }else if (i == dp.length - 1){
                    dp[i][j] = dp[i][j + 1] + arr[i][j];
                }else if (j == dp[0].length - 1){
                    dp[i][j] = dp[i + 1][j] + arr[i][j];
                }else{
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + arr[i][j];
                }
            }
        }
        return dp[0][0];
    }

    public static int goldMine(int n, int m, int[][]arr){
        int[][]dp = new int[n][m];
        for(int j = arr[0].length - 1; j >= 0; j--){
            for(int i = arr.length - 1; i >= 0; i--){
                if(j == arr[0].length - 1){
                    dp[i][j] = arr[i][j];
                } else if(i == 0){
                    dp[i][j] = arr[i][j] + Math.max(dp[i][j + 1], dp[i + 1][j + 1]);
                }else if(i == arr.length - 1){
                    dp[i][j] = arr[i][j] + Math.max(dp[i][j + 1], dp[i - 1][j + 1]);
                }else {
                    dp[i][j] = arr[i][j] + Math.max(dp[i][j + 1], Math.max(dp[i + 1][j + 1], dp[i - 1][j + 1]));
                }
            }
        }

        int max = dp[0][0];
        for(int i = 1; i < dp.length; i++){
            if(dp[i][0] > max){
                max = dp[i][0];
            }
        }
        return max;
    }

    public static boolean targetSumSubset(int n, int[]arr, int tar){

        boolean[][] dp = new boolean[n + 1][tar + 1];
        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j < dp[0].length; j++){
                if(i == 0 && j == 0){
                    dp[i][j] = true;
                }else if (i == 0){
                    dp[i][j] = false;
                }else if (j == 0){
                    dp[i][j] = true;
                }else {
                    if(dp[i - 1][j] == true){
                        dp[i][j] = true;
                    }else{
                        int val = arr[i - 1];
                        if(j >= val){
                            if(dp[i - 1][j - val] == true){
                                dp[i][j] = true;
                            }
                        }
                    }
                }
            }
        }
        return dp[arr.length][tar];
    }

    public static int coinCombination(int n, int[]arr, int amt){
        int[]dp = new int[amt + 1];

        dp[0] = 1;
        for(int i = 0; i < arr.length; i++){
            for(int j = arr[i]; j < dp.length; j++){
                dp[j] += dp[j - arr[i]];
            }
        }
        return dp[amt];
    }

    public static int coinPermutation(int n, int[]coins, int tar){
        int[]dp = new int[tar + 1];
        dp[0] = 1;

        for(int amt = 1; amt <= tar; amt++){
            for(int coin : coins){
                if(coin <= amt){
                    int ramt = amt - coin;
                    dp[amt] += dp[ramt];
                }
            }
        }
        return dp[tar];
    }

    public static int zeroOneKnapsack(int n, int[]vals, int[]wts, int cap){

        int[][] dp = new int[n + 1][cap + 1];
        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                if(j >= wts[i - 1]){
                    int rcap = j - wts[i - 1];

                    if(dp[i - 1][rcap] + vals[i - 1] > dp[i - 1][j]){
                        dp[i][j] = dp[i - 1][rcap] + vals[i - 1];
                    }else{
                        dp[i][j] = dp[i - 1][j]; //when i doesn't bat
                    }
                }else{
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][cap];
    }

    public static int unboundKnapsack(int n, int[]vals, int[]wts, int cap){
        
        int[]dp = new int[cap + 1];
        dp[0] = 1;

        for(int bagc = 0; bagc <= cap; bagc++){
            int max = 0;

            for(int i = 0; i < n; i++){
                if(wts[i] <= bagc){
                    int rbagc = bagc - wts[i];
                    int rbagv = dp[rbagc];
                    int tbagv = rbagv + vals[i];

                    if(tbagv > max){
                        max = tbagv;
                    }
                }
            }
            dp[bagc] = max;
        }
        return dp[cap];
    }

    public static void countBinaryString(int n){
        int[] dp0 = new int[n + 1];
        int[] dp1 = new int[n + 1];

        dp0[1] = 1;
        dp1[1] = 1;

        for(int i = 2; i <= n; i++){
            dp1[i] = dp1[i - 1] + dp0[i - 1];
            dp0[i] = dp1[i - 1];
        }

        System.out.println(dp1[n]+dp0[n]);
    }

    public static void arrangeBuildings(long n){
        long oczeros = 1;
        long ocones = 1;

        for(long i = 2; i <= n; i++){
            long nczeros = ocones;
            long ncones = ocones + oczeros;

            ocones = ncones;
            oczeros = nczeros;
        }

        long total = ocones + oczeros;
        total = total*total;
        System.out.println(total);
    }

    public static void countEncoding(String str){

        int[] dp = new int[str.length()];
        dp[0] = 1;

        for(int i = 1; i < dp.length; i++){
            if(str.charAt(i - 1) == '0' && str.charAt(i) == '0'){
                dp[i] = 0;
            } else if(str.charAt(i - 1) == '0' && str.charAt(i) != '0'){
                dp[i] = dp[i - 1];
            }else if(str.charAt(i - 1) != '0' && str.charAt(i) == '0'){
                if(str.charAt(i - 1) == '1' || str.charAt(i - 1) == '2'){
                    dp[i] = (i >= 2 ? dp[i - 2] : 1);
                }else{
                    dp[i] = 0;
                }
            }else {
                if(Integer.parseInt(str.substring(i - 1, i + 1)) <= 26){
                    dp[i] = dp[i - 1] + (i >= 2 ? dp[i - 2] : 1);
                }else{
                    dp[i] = dp[i - 1];
                }
            }
        }
        System.out.println(dp[str.length() - 1]);
    }

    public static void countabcSubsequence(String str){
        int a = 0;
        int ab = 0;
        int abc = 0;

        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);

            if(ch == 'a'){
                a = 2 * a + 1;
            }else if(ch == 'b'){
                ab = 2 * ab + a;
            }else if(ch == 'c'){
                abc = 2 * abc + ab;
            }
        }
        System.out.println(abc);
    }

    // Greedy question

    public static void maxSumofAdjacentElements(int n, int[]arr){
        int inc = arr[0];
        int exc = 0;

        for(int i = 1; i < arr.length; i++){
            int ninc = exc + arr[i];
            int nexc = Math.max(inc, exc);

            inc = ninc;
            exc = nexc;
        }
        int ans = Math.max(inc, exc);
        System.out.println(ans);
    }

    public static void paintHouse(int n, int[][]arr){

        long dp[][] = new long[n][3];

        dp[0][0] = arr[0][0];
        dp[0][1] = arr[0][1];
        dp[0][2] = arr[0][2];

        for(int i = 1; i < arr.length; i++){
            dp[i][0] = arr[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = arr[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = arr[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }

        long ans = Math.min(dp[n - 1][0], Math.min(dp[n - 1][1], dp[n - 1][2]));
        System.out.println(ans);
    }


    public static void paintHouseManyColor(int n, int m, int[][]arr){
        int[][] dp = new int[arr.length][arr[0].length];

        int least = Integer.MAX_VALUE;
        int sleast = Integer.MAX_VALUE;

        for(int j = 0; j < arr[0].length; j++){
            dp[0][j] = arr[0][j];

            if(arr[0][j] <= least){
                sleast = least;
                least = arr[0][j];
            }else if(arr[0][j] <= sleast){
                sleast = arr[0][j];
            }
        }

            for(int i = 1; i < dp.length; i++){
                int nleast = Integer.MAX_VALUE;
                int nsleast = Integer.MAX_VALUE;

                for(int j = 0; j < dp[0].length; j++){
                    if( least == dp[i - 1][j]){
                        dp[i][j] = sleast + arr[i][j];
                    }else {
                        dp[i][j] = least + arr[i][j];
                    }

                    if(dp[i][j] <= nleast){
                        nsleast = nleast;
                        nleast = dp[i][j];
                    } else if(dp[i][j] <= nsleast){
                        nsleast = dp[i][j];
                    }
                }

                least = nleast;
                sleast = nsleast;
            }   
        System.out.println(least);  
    }

    public static void paintFence(int n, int k){
        long same = k*1;
        long diff = k*(k-1);
        long total = same + diff;

        for(int i = 3; i <= n; i++){
            same = diff*1;
            diff = total*(k-1);
            total = same+diff;
        }
        System.out.println(total);
    }

    public static void tialing21(int n){
        int[]dp = new int[n + 1];

        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        System.out.println(dp[n]);
    }

    public static void tialingm1(int n, int m){
        int[]dp = new int[n + 1];

        for(int i = 1; i <= n; i++){
            if(i < m){
                dp[i] = 1;
            } else if(i == m){
                dp[i] = 2;
            }else {
                dp[i] = dp[i - 1] + dp[i - m];
            }
        }
        System.out.println(dp[n]);
    }

    public static void friendPairing(int n){

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2]*(i - 1);
        }

        System.out.println(dp[n]);
    }

    public static void partitionSubset(int n, int m){
        if(n == 0 || m == 0|| n < m){
            System.out.println(0);
            return ;
        }

        int[][] dp = new int[m + 1][n + 1];

        for(int t = 1; t <= m; t++){
            for(int p = 1; p <= n; p++){
                if(p < t){
                    dp[t][p] = 0;
                }else if(p == t){
                    dp[t][p] = 1;
                }else {
                    dp[t][p] = dp[t - 1][p - 1] + dp[t][p - 1]*t;
                }
            }
        }
        System.out.println(dp[m][n]);
    }

    public static void buyandSellinoneTrnas(int n, int[] prices){

        int lsf = Integer.MAX_VALUE;
        int op = 0;
        int pist = 0;

        for(int i = 0; i < prices.length; i++){
            if(prices[i] < lsf){
                lsf = prices[i];
            }
            pist = prices[i] - lsf;
            if(pist > op){
                op = pist;
            }
        }
        System.out.println(op);
    }

    public static void butAndsellmanytrans(int n, int[] prices){
        int bd = 0;
        int sd = 0;
        int profit = 0;

        for(int i = 1; i < prices.length; i++){
            if(prices[i] >= prices[i - 1]){
                sd++;
            }else{
                profit += prices[sd] - prices[bd];
                sd = bd = i;
            }
        }
        profit += prices[sd] - prices[bd];
        System.out.println(profit);
    }

    public static void buyandsellWithFee(int n, int[] prices, int fees){
        
        int obsp = -prices[0];
        int ossp = 0;

        for(int i = 1; i < prices.length; i++){
            int nbsp = 0;
            int nssp = 0;

            if(ossp - prices[i] > obsp){
                nbsp = ossp - prices[i];
            }else{
                nbsp = obsp;
            }

            if(obsp + prices[i] - fees > ossp){
                nssp = obsp + prices[i] - fees;
            }else{
                nssp = obsp;
            }

            obsp = nbsp;
            ossp = nssp;
        }
        System.out.println(ossp);
    }

    public static void buySellandCooldown(int n, int[]prices){
        
        int obsp = -prices[0];
        int ossp = 0;
        int ocsp = 0;

        for(int i = 1; i < prices.length; i++){
            int nbsp = 0;
            int nssp = 0;
            int ncsp = 0;

            if(ocsp - prices[i] > obsp){
                nbsp = ocsp - prices[i];
            }else {
                nbsp = obsp;
            }

            if(obsp + prices[i] > ossp){
                nssp = obsp + prices[i];
            }else {
                nssp = ossp;
            }

            if(ossp > ocsp){
                ncsp = ossp;
            }else{
                ncsp = ocsp;
            }

            obsp = nbsp;
            ossp = nssp;
            ocsp = ncsp;
        }

        System.out.println(ossp);
    }

    public static void buyandSellTwoTran(int n, int[]prices){
        int mpist = 0;
        int leastsf = prices[0];
        int[] dpl = new int[prices.length];

        for(int i = 1; i < prices.length; i++){
            if(prices[i] < leastsf){
                leastsf = prices[i];
            }

            mpist = prices[i] - leastsf;
            if(mpist > dpl[i - 1]){
                dpl[i] = mpist;
            }else {
                dpl[i] = dpl[i - 1];
            }
        }

        int mpibt = 0;
        int maxat = prices[prices.length - 1];
        int[] dpr = new int[prices.length];

        for(int i = prices.length - 2; i >= 0; i--){
            if(prices[i] > maxat){
                maxat = prices[i];
            }

            mpibt = maxat - prices[i];
            if(mpibt > dpr[i + 1]){
                dpr[i] = mpibt;
            }else{
                dpr[i] = dpr[i + 1];
            }
        }

        int op = 0;
        for(int i = 0; i < prices.length; i++){
            if(dpl[i] + dpr[i] > op){
                op = dpl[i] + dpr[i];
            }
        }
        System.out.println(op);
    }

    public static void buyandSellwithKtran(int n, int[] prices, int k){
        int[][] dp = new int[k + 1][n];

        for(int t = 1; t <= k; t++){
            int max = Integer.MIN_VALUE;

            for(int d = 1; d < prices.length; d++){
                if(dp[t - 1][d - 1] - prices[d - 1] > max){
                    max = dp[t - 1][d - 1] - prices[d - 1];
                }

                if(max + prices[d] > dp[t][d - 1]){
                    dp[t][d] = max + prices[d];
                }else{
                    dp[t][d] = dp[t][d - 1];
                }
            }
        }
        System.out.println(dp[k][n - 1]);
    }

    public static void main(String[] args) {
        // int n = scn.nextInt();
        // int fib = fibn(n);
        // int fib1 = fibMemo(n, new int [n + 1]);
        // System.out.println(fib1);

        // int cp = countPath(n, new int[n + 1]);
        // int cp = countPathTab(n);
        // System.out.println(cp);

        // int n = scn.nextInt();
        // int []arr = new int[n];
        // for(int i = 0; i < arr.length; i++){
        //     arr[i] = scn.nextInt();
        // }

        // int cwvj = climbStairsWithVariableJump(n, arr);
        // System.out.println(cwvj);

        // int csmm = climbStairsWithMinimumMove(n, arr);
        // System.out.println(csmm);

        
        //   int n = scn.nextInt();
        //   int m = scn.nextInt();

        //   int[][] arr = new int[n][m];
        //   for(int i = 0; i < arr.length; i++){
        //    for(int j = 0; j < arr[0].length; j++){
        //      arr[i][j] = scn.nextInt();
        //     }
        //   }
        //   int mincst = minCost(n, m, arr);
        //   System.out.println(mincst);

        // int gdmine = goldMine(n, m, arr);
        // System.out.println(gdmine);

        // int tar = scn.nextInt();
        // boolean tss = targetSumSubset(n, arr, tar);
        // System.out.println(tss);

        // int amt = scn.nextInt();
        // int ccc = coinCombination(n, arr, amt);
        // System.out.println(ccc);

        // int amt = scn.nextInt();
        // int ccp = coinPermutation(n, arr, amt);
        // System.out.println(ccp);

        // int n = scn.nextInt();
        // int[] vals = new int[n];
        // int[] wts = new int[n];

        // for(int i = 0; i < vals.length; i++){
        //     vals[i] = scn.nextInt();
        // }
        // for(int i = 0; i < wts.length; i++){
        //     wts[i] = scn.nextInt();
        // }

        // int cap = scn.nextInt();

        // int zok = zeroOneKnapsack(n, vals, wts, cap);
        // System.out.println(zok);

        // int ubk = unboundKnapsack(n, vals, wts, cap);
        // System.out.println(ubk);

        // countBinaryString(n);

        // long n = scn.nextInt();
        // arrangeBuildings(n);

        // String str = scn.next();
        // countEncoding(str);

        // countabcSubsequence(str);

        // int n = scn.nextInt();
        // int[]arr = new int[n];
        // for(int i = 0; i < arr.length; i++){
        //     arr[i] = scn.nextInt();
        // }
        // maxSumofAdjacentElements(n, arr);

        // int n = scn.nextInt();
        // int [][] arr = new int[n][3];
        // for(int i = 0; i < arr.length; i++){
        //     for(int j = 0; j < arr[0].length; j++){
        //         arr[i][j] = scn.nextInt();
        //     }
        // }
        // paintHouse(n, arr);

        // paintHouseManyColor(n, m, arr);

        // int k = scn.nextInt();
        // paintFence(n, k);

        // tialing21(n);
        // tialingm1(n,k);

        // friendPairing(n);
        // partitionSubset(n, k);



        int n = scn.nextInt();
        int[] prices = new int[n];
        for(int i = 0; i < prices.length; i++){
            prices[i] = scn.nextInt();
        }

        // buyandSellinoneTrnas(n, prices);

        // butAndsellmanytrans(n, prices);

        // int fees = scn.nextInt();

        //buyandsellWithFee(n, prices, fees);

        // buySellandCooldown(n, prices);

        // buyandSellTwoTran(n, prices);
        
        int k = scn.nextInt();
        buyandSellwithKtran(n, prices, k);

    }

}
