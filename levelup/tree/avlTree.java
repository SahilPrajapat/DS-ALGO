public class avlTree {

    public static class TreeNode{
        TreeNode left = null;
        TreeNode right = null;
        int val = 0;

        int bal = 0;
        int height = 0;

        TreeNode(int val){
            this.val = val;
            this.bal = 0;
            this.height = 0;
        }
    }

    // --------------------------------------------------------------------------------------------------------------------------------

    // O(1)
    public static void updateHeightBalance(TreeNode node){
        int lh = node.left != null ? node.left.height : -1;
        int rh = node.right != null ? node.right.height : -1;

        node.height = Math.max(lh, rh) + 1;
        node.bal = lh - rh;
    }

    // O(1)
    public static TreeNode rightRotation(TreeNode A){
        TreeNode B = A.right;
        TreeNode bkaRight = B.right;

        B.right = A;
        A.left = bkaRight;

        updateHeightBalance(A);
        updateHeightBalance(B);

        return B;
    }

    // O(1)
    public static TreeNode leftRotation(TreeNode A){
        TreeNode B = A.right;
        TreeNode bkaLeft = B.left;

        B.left = A;
        A.right = bkaLeft;

        updateHeightBalance(A);
        updateHeightBalance(B);

        return B;
    }

    // O(1)
    public static TreeNode getRotation(TreeNode root){
        updateHeightBalance(root);

        if(root.bal == 2){         // ll lr
            if(root.left.bal == 1){   //ll
                return rightRotation(root);
            }else {   //lr
                root.left = leftRotation(root.left);
                return rightRotation(root);
            }
        }else if(root.bal == -2){     //rr, rl
            if(root.right.bal == -1){   //rr
                return leftRotation(root);
            }else {    //rl
                root.right = rightRotation(root.right);
                return leftRotation(root);
            }
        }

        return root;
    }

    // --------------------------------------------------------------------------------------------------------------------------------

    // O (logN)
    public static int getMaximum(TreeNode root){
        while(root.right != null)
            root = root.right;

        return root.val;
    }

    // O (logN)
    public static TreeNode add(TreeNode root, int val) {
        if(root == null)
            return new TreeNode(val);

        if(root.val < val)
            root.right = add(root.right, val);
        else
            root.left = add(root.left, val);

        root = getRotation(root);
        return root;
    }

    // O (logN)
    public TreeNode remove(TreeNode root, int data) {
        if(root == null)
            return null;

        if(root.val < data){
            root.right = remove(root.right, data);
        }else if(root.val > data)
            root.left = remove(root.left, data);

        else{
            if(root.left == null || root.right == null)
                return root.left != null ? root.left : root.right;

            int maximum = getMaximum(root.left);
            root.val = maximum;

            root.left = remove(root.left, maximum);
        }

        root = getRotation(root);
        return root;
    }

    // O(n)
    public static void display(TreeNode node){
        if(node == null)
            return;

        StringBuilder sb = new StringBuilder();
        sb.append((node.left != null ? node.left.val : "."));
        sb.append(" -> " + node.val+ " <- ");
        sb.append((node.right != null ? node.right.val : "."));
        
        System.out.println(sb.toString());

        display(node.left);
        display(node.right);
    }


    

    public static void main(String[] args) {
        TreeNode root = null;
        for(int i = 0; i <= 100; i++){
            root = add(root, i*10);
        }
        display(root);
    }
    
}
