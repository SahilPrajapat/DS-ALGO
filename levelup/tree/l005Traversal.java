import java.util.*;

public class l005Traversal {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val){
            this.val = val;
        }
    }

    public static TreeNode getRightMost(TreeNode node, TreeNode curr){
        while(node.right != null && node.right != curr)
            node = node.right;

        return node;
    }

    public static ArrayList<Integer> morinOrderTraversal(TreeNode root){
        TreeNode curr = root;
        ArrayList<Integer> ans = new ArrayList<>();
        while(curr != null){
            TreeNode left = curr.left;
            if(left == null){
                ans.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rightMost = getRightMost(left, curr);
                if(rightMost.right == null){          //thread creation area
                    rightMost.right = curr;           // thread created
                    curr = curr.left;
                }else {
                    rightMost.right = null;           // thread destroy
                    ans.add(curr.val);

                    curr = curr.right;
                }
            }
        }

        return ans;
    }

    public static ArrayList<Integer> morisPreOrderTraversal(TreeNode root){
        TreeNode curr = root;
        ArrayList<Integer> ans = new ArrayList<>();
        while(curr != null){
            TreeNode left = curr.left;
            if(left == null){
                ans.add(curr.val);
                curr = curr.right;
            }else {
                TreeNode rightMost = getRightMost(left, curr);
                if(rightMost.right == null) {
                    rightMost.right = curr;         //thread created
                    ans.add(curr.val);

                    curr = curr.left;
                } else {
                    rightMost.right = null;          //thread break
                    curr = curr.right;
                }
            }
        }
        return ans;
    }


    // validate bst =============================================================================================================

    public static boolean isValidBST(TreeNode root, TreeNode[]prev){
        if(root == null)
            return true;

        if(!isValidBST(root.left, prev))
            return false;

        if(prev[0] != null && prev[0].val >= root.val)
            return false;

        prev[0] = root;

        if(!isValidBST(root.right, prev))
            return false;

        return true;
    }

    public static boolean isValidBST_02(TreeNode root){
        long prev = -(long)1e13;
        TreeNode curr = root;
        while(curr != null){
            TreeNode left = curr.left;
            if(left == null){
                if(prev >= curr.val)
                    return false;

                prev = curr.val;
                curr = curr.right;
            } else {
                TreeNode rightMost = getRightMost(left, curr);
                if(rightMost.right == null) {
                    rightMost.right = curr;
                    curr = curr.left;
                } else  {
                    rightMost.right = null;
                    if(prev > curr.val)
                        return false;

                    prev = curr.val;
                    curr = curr.right;
                }
            }
        }
        return true;
    }

    public static int kthSmallest(TreeNode root, int k) {
        TreeNode curr = root;
        while(curr != null){
            TreeNode left = curr.left;
            if(left == null){
                if(--k == 0)
                    return curr.val;

                curr = curr.right;
            } else {
                TreeNode rightMost = getRightMost(left, curr);
                if(rightMost.right == null){
                    rightMost.right = curr;
                    curr = curr.left;
                } else {
                    rightMost.right = null;
                    if(--k == 0)
                        return curr.val;

                    curr = curr.right;
                }
            }
        }
        return -1;
    }

    public static TreeNode getLeftMost(TreeNode node, TreeNode curr){
        while(node.left != null && node.left != curr)
            node = node.left;

        return node;
    }

    public static int kthLargest(TreeNode root, int k){
        TreeNode curr = root;
        while(curr != null){
            TreeNode right = curr.right;
            if(right == null){
                if(--k == 0)
                    return curr.val;

                curr = curr.left;
            } else {
                TreeNode leftMost = getLeftMost(right, curr);
                if(leftMost.left == null) {
                    leftMost.left = curr;
                    curr = curr.right;
                } else {
                    leftMost.left = null;
                    if(--k == 0)
                        return curr.val;

                    curr = curr.left;
                }
            }
        }
        return -1;
    }

    public static TreeNode BSTtoDLL(TreeNode root){
        TreeNode dummy = new TreeNode(-1);
        TreeNode curr = root, prev = dummy;
        while(curr != null){
            TreeNode left = curr.left;
            if(left == null){
                
                prev.right = curr;
                curr.left = prev;
                prev = curr;

                curr = curr.right;
            }else {
                TreeNode rightMost = getRightMost(left, curr);
                if(rightMost.right == null){
                    rightMost.right = curr;
                    curr = curr.left;
                } else {
                    rightMost.right = null;
                    prev.right = curr;
                    curr.left = prev;
                    prev = curr;

                    curr = curr.right;
                }
            }
        }

        TreeNode head = dummy.right;
        dummy.right = head.left = null;

        head.left = prev;
        prev.right = head;

        return head;
    }


    class BSTIterator {
        TreeNode curr = null;
        
        private TreeNode getRightMost(TreeNode node, TreeNode curr){
            while(node.right != null && node.right != curr)
                node = node.right;
    
            return node;
        }
    
        public BSTIterator(TreeNode root) {
            curr = root;
        }
        
        public int next() {
            int rv = -1;
            while(curr != null){
                TreeNode left = this.curr.left;
                if(left == null){
                    rv = this.curr.val;
                    this.curr = this.curr.right;
                    break;
                }else {
                    TreeNode rightMost = getRightMost(left, curr);
                    if(rightMost.right == null){
                        rightMost.right = this.curr;
                        this.curr = this.curr.left;
                    }else {
                        rightMost.right = null;
                        rv = this.curr.val;
                        
                        curr = curr.right;
                        break;
                    }
                }
            }
            return rv;
        }
        
        public boolean hasNext() {
            return this.curr != null;
        }
    }
    
    //662 
    public int widthOfBinaryTree(TreeNode root) {
        Queue<Pair<TreeNode, Integer>> q = new ArrayDeque<>();
        q.add(new Pair(root, 0));
        
        int level = 0;
        while(q.size() != 0){
            int size = q.size();
            int p1 = q.peek().getValue();
            int p2 = p1;
            while(size-- > 0){
                Pair p = q.peek();
                p2 = (int)p.getValue();
                root = (TreeNode)p.getKey();
                q.remove();
                
                if(root.left != null)
                    q.add(new Pair(root.left, 2 * p2 + 1));
                if(root.right != null)
                    q.add(new Pair(root.right, 2* p2 + 2));
            }
            
            level = Math.max(level, p2 - p1 + 1);
        }
        return level;
    }

}