import java.util.*;

public class l002BST {
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

    public static int maximum(TreeNode root){
        TreeNode curr = root;
        while(curr.right != null)
            curr = curr.right;

        return curr.val;
    }

    public static int minimum(TreeNode root){
        TreeNode curr = root;
        while(curr.left != null)
            curr = curr.left;

        return curr.val;
    }

    public static boolean find(TreeNode root, int data){
        TreeNode curr = root;
        while(curr != null){
            if(curr.val == data)
                return true;
            else if(curr.val < data)
                curr = curr.right;
            else
                curr = curr.left;
        }
        return false;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        TreeNode curr = root;
        while(curr != null){
            if(curr.val < p && curr.val < q)
                curr = curr.right;
            else if(curr.val > p && curr.val > q)
                curr = curr.left;
            else
                return curr;
        }
        return null;
    }

    class BSTIterator {

        private ArrayDeque<TreeNode> st = new ArrayDeque<>(); //addFirst and removeFirst

        public BSTIterator(TreeNode root) {
            addAllLeft(root);
        }

        private void addAllLeft(TreeNode node){
            while(node != null){
                this.st.addFirst(node);
                node = node.left;
            }
        }
        
        public int next() {
            TreeNode rnode = this.st.removeFirst();
            addAllLeft(rnode.right);

            return rnode.val;
        }
        
        public boolean hasNext() {
            return this.st.size() != 0;
        }
    }
}
