public class recursionTree {

    public static int permutationWithInficoin(int[] arr, int tar, String ans){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int ele: arr){
            if(tar - ele >= 0)
                count += permutationWithInficoin(arr, tar - ele, ans + ele);
        }
        return count;
    }

    public static int combinationwithInficoin(int[] arr, int tar, int idx, String ans){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = idx; i < arr.length; i++){
            if(tar - arr[i] >= 0){
                count += combinationwithInficoin(arr, tar - arr[i], i, ans + arr[i]);
            }
        }
        return count;
    }

    public static int combinationWithSingle(int[] arr, int tar, int idx, String ans){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }
        
        int count = 0;
        for(int i = idx; i < arr.length; i++){
            if(tar - arr[i] >= 0)
                count += combinationWithSingle(arr, tar - arr[i], i + 1, ans + arr[i]);
        }
        return count;
    }

    public static int permutationWithSingle(int [] arr, int tar, String ans){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > 0 && tar - arr[i] >= 0){
                int val = arr[i];
                arr[i] = -arr[i];
                count += permutationWithSingle(arr, tar - val, ans + val);
                arr[i] = -arr[i];
            }
        }
        return count;
    }

    public static int permutationWithSingle2(int[]arr, int tar, boolean[] vis, String ans){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = 0; i < arr.length; i++){
            if(!vis[i] && tar - arr[i] >= 0){
                vis[i] = true;
                count += permutationWithSingle2(arr, tar - arr[i], vis, ans + arr[i]);
                vis[i] = false;
            }
        }
        return count;
    }

    //SubSequence ===============================================================================================================================

    public static int permutationWithInficoin_subseq(int[] arr, int tar, int idx, String ans){
        if(tar == 0 || idx == arr.length){
            if(tar == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if(tar - arr[idx] >= 0){
            count += permutationWithInficoin_subseq(arr, tar - arr[idx], 0, ans + arr[idx]);
        }
        count += permutationWithInficoin_subseq(arr, tar, idx + 1, ans);

        return count;

    }

    public static int combinationwithInficoin_subseq(int[] arr, int tar, int idx, String ans){
        if(tar == 0 || idx == arr.length){
            if(tar == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if(tar - arr[idx] >= 0){
            count += combinationwithInficoin_subseq(arr, tar - arr[idx], idx, ans + arr[idx]);
        }
        count += combinationwithInficoin_subseq(arr, tar, idx + 1, ans);

        return count;
    }

    public static int combinationwithSinglecoin_subseq(int[] arr, int tar, int idx, String ans){
        if(tar == 0 || idx == arr.length){
            if(tar == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if(tar - arr[idx] >= 0){
            count += combinationwithSinglecoin_subseq(arr, tar - arr[idx], idx + 1, ans + arr[idx]);
        }
        count += combinationwithSinglecoin_subseq(arr, tar, idx + 1, ans);

        return count;
    }

    public static int permutationWithSinglecoin_subseq(int[] arr, int tar, int idx, String ans){
        if(tar == 0 || idx == arr.length){
            if(tar == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if(arr[idx] > 0 && tar - arr[idx] >= 0){
            int val = arr[idx];
            arr[idx] = -arr[idx];
            count += permutationWithSinglecoin_subseq(arr, tar - val, 0, ans + val);
            arr[idx] = -arr[idx];
        }
        count += permutationWithSinglecoin_subseq(arr, tar, idx + 1, ans);

        return count;
    }

    //1D - queen_set=========================================================================================================
    //tboxe = total boxes, tqn = total queen, qpsf = queen placed so far, bn = box no

    public static int queenCombination(int tboxe, int tqn, int qpsf, int bn, String ans){
        if(qpsf == tqn){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = bn; i < tboxe; i++){
            count += queenCombination(tboxe, tqn, qpsf + 1, i + 1, ans + "b" + i + "q" + qpsf + " ");
        }
        return count;
    }

    public static int queenPermutation(boolean[] tboxe, int tqn, int qpsf, int bn, String ans){
        if(qpsf == tqn){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = bn; i < tboxe.length; i++){
            if(!tboxe[i]){
                tboxe[i] = true;
                count += queenPermutation(tboxe, tqn, qpsf + 1, i + 1, ans + "b" + i + "q" + qpsf + " ");
                tboxe[i] = false;
            }
        }
        return count;
    }






    public static void coinchange() {
        int [] arr = {2, 3, 5, 7};
        int tar = 10;
        // System.out.println(combinationwithInficoin(arr, tar, 0, " "));
        // System.out.println(permutationWithInficoin(arr, tar, " "));
        // System.out.println(combinationWithSingle(arr, tar, 0, ""));
        // System.out.println(permutationWithSingle(arr, tar, ""));
        // System.out.println(permutationWithInficoin_subseq(arr, tar, 0, ""));
        // System.out.println(combinationwithInficoin_subseq(arr, tar, 0, ""));
        // System.out.println(permutationWithSinglecoin_subseq(arr, tar, 0, ""));
        // System.out.println(combinationwithSinglecoin_subseq(arr, tar, 0, ""));
    }

    public static void queen() {
        boolean[] boxes = new boolean[6];
        // System.out.println(queenCombination(16, 4, 0, 0, ""));
        System.out.println(queenPermutation(boxes, 4, 0, 0, ""));
    }



    public static void main(String[] args) {
        // coinchange();
        queen();
    }
    
}
