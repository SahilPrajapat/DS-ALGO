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

}
