import java.util.*;

public class l006diaSet {
    public static class TreeNode{
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val){
            this.val = val;
        }
    }

    public static int size(TreeNode root){
        return root == null ? 0 : size(root.left) + size(root.right) + 1;
    }

    public static int height(TreeNode root){
        return root == null ? -1 : Math.max(height(root.left), height(root.right)) + 1;
    }

    public static int diameter_01(TreeNode root){
        if(root == null)
            return 0;

        int ld = diameter_01(root.left);
        int rd = diameter_01(root.right);

        int lh = height(root.left);
        int rh = height(root.right);

        return Math.max(Math.max(ld, rd), lh + rh + 2);
    }

    public static int[] diameter_02(TreeNode root){
        if(root == null)
            return new int[]{0, -1};

        int[] lp = diameter_02(root.left);
        int[] rp = diameter_02(root.right);

        int[] myAns = new int[2];

        myAns[0] = Math.max(Math.max(lp[0], rp[0]), lp[1] + rp[1] + 2);
        myAns[1] = Math.max(lp[1], rp[1]) + 1;

        return myAns;
    }

    //112
    public boolean hasPathSum(TreeNode root, int targetSum){
        if(root == null)
            return false;

        if(root.left == null && root.right == null)
            return targetSum - root.val == 0 ? true: false;

        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    //113
    public void path(TreeNode root, int targetSum, List<Integer>smallAns, List<List<Integer>>ans){
        if(root == null)
            return;
        
        if(root.left == null && root.right == null && targetSum - root.val == 0){
            List<Integer> base = new ArrayList<>(smallAns);
            base.add(root.val);
            ans.add(base);
            return;
        }
        
        smallAns.add(root.val);
        
        path(root.left, targetSum - root.val, smallAns, ans);
        path(root.right, targetSum - root.val, smallAns, ans);
        
        smallAns.remove(smallAns.size() - 1);
    }
    
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<Integer>smallAns = new ArrayList<>();
        List<List<Integer>>ans =  new ArrayList<>();
        
        path(root, targetSum, smallAns, ans);
        return ans;
    }

    public static class leaftoleafPair {
        int LTLMaxSum = -(int)1e9;
        int NTLMaxSum = -(int)1e9;
        
    }
    
    public static leaftoleafPair maxSum(TreeNode root){
        if(root == null)
            return new leaftoleafPair();
            
        if(root.left == null && root.right == null){
            leaftoleafPair base = new leaftoleafPair();
            base.NTLMaxSum = root.val;
            return base;
        }
        
        leaftoleafPair lRes = maxSum(root.left);
        leaftoleafPair rRes = maxSum(root.right);
        
        leaftoleafPair myRes = new leaftoleafPair();
        myRes.LTLMaxSum = Math.max(lRes.LTLMaxSum, rRes.LTLMaxSum);
        
        if(root.left != null && root.right != null){
            myRes.LTLMaxSum = Math.max(myRes.LTLMaxSum, lRes.NTLMaxSum + root.val + rRes.NTLMaxSum);
        }
        
        myRes.NTLMaxSum = Math.max(lRes.NTLMaxSum, rRes.NTLMaxSum) + root.val;
        
        return myRes;
        
    }
    
    
    int maxPathSum(TreeNode root){ 
        int ans = maxSum(root).LTLMaxSum;
        int ans2 = maxSum(root).NTLMaxSum;
        
        return ans != -(int)1e9 ? ans : Math.max(ans, ans2);
    } 


    //124
    public class NTNPair {
        int maxPossibleAns = -(int)1e9;
        int NTNMaxSum = 0;
    }
    
    public int getMax(int... arr){
        int maxEle = arr[0];
        for(int ele : arr){
            maxEle = Math.max(ele, maxEle);
        }
        
        return maxEle;
    }
    
    public NTNPair maxPath(TreeNode root){
        NTNPair myAns = new NTNPair();
        if(root == null){
            return myAns;
        }
        NTNPair left = maxPath(root.left);
        NTNPair right = maxPath(root.right);
        
        int OneSideMax = Math.max(left.NTNMaxSum, right.NTNMaxSum) + root.val;
        myAns.maxPossibleAns = getMax(left.maxPossibleAns, right.maxPossibleAns, root.val, OneSideMax, left.NTNMaxSum + right.NTNMaxSum + root.val);
        
        myAns.NTNMaxSum  = Math.max(OneSideMax, root.val);
        
        return myAns;
        
    }
    public int maxPathSum2(TreeNode root) {
        return maxPath(root).maxPossibleAns;
    }

    // -1 : camera required, 0 : already covered, 1 : i'm a camera
    public int minCameraCover_(TreeNode root, int[] camerCount){
        if(root == null)
            return 0;

        int lr = minCameraCover_(root.left, camerCount);
        int rr = minCameraCover_(root.right, camerCount);

        if(lr == -1 || rr == -1){
            camerCount[0]++;
            return 1;
        }

        if(lr == 1 || rr == 1)
            return 0;

        return -1;
    }

    public int minCameraCover(TreeNode root) {
        int[] cameraCount = new int[1];
        int ans = minCameraCover_(root, cameraCount);
        if(ans == -1){
            cameraCount[0]++;
        }
        return cameraCount[0];
    }

    // {with robbery, without robbery}
    public int[] rob_(TreeNode root) {
        if(root == null)
            return new int[2];

        int[] lr = rob_(root.left);
        int[] rr = rob_(root.right);

        int[] myAns = new int[2];
        myAns[0] = lr[1] + rr[1] + root.val;
        myAns[1] = Math.max(lr[0], lr[1]) + Math.max(rr[0], rr[1]);

        return myAns;
    }

    public int rob(TreeNode root) {
        int[] ans = rob_(root);
        return Math.max(ans[0], ans[1]);
    }


    // {forward, backward, maxZigZag Length}
    public int[] longestZigZag_(TreeNode root){
        if(root == null)
            return new int[]{-1, -1, -1};

        int[] lans = longestZigZag_(root.left);
        int[] rans = longestZigZag_(root.right);

        int[] myAns = new int[3];
        myAns[0] = lans[1] + 1;
        myAns[1] = rans[0] + 1;
        myAns[2] = Math.max(Math.max(lans[2], rans[2]), Math.max(myAns[0], myAns[1]));

        return myAns;

     }

    public int longestZigZag(TreeNode root) {
        int[] ans = longestZigZag_(root);
        return ans[2];
    }

}
