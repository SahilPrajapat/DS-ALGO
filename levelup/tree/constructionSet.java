import java.util.LinkedList;

public class constructionSet {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }

    }

    public static TreeNode constructFromInorder(int[] inorder, int si, int ei){
        if(si > ei)
            return null;

        int mid = (si + ei)/2;
        TreeNode root = new TreeNode(inorder[mid]);

        root.left = constructFromInorder(inorder, si, mid - 1);
        root.right = constructFromInorder(inorder, mid + 1, ei);

        return root;
    }

    public static TreeNode constructFromInOrder(int[] inOrder) {
        int ei = inOrder.length - 1;
        return constructFromInorder(inOrder, 0, ei);
    }
    
    static int idx = 0;
    public static TreeNode bstFromPreOrder(int[] preOrder, int lr, int rr){
        if(idx == preOrder.length || preOrder[idx] < lr || preOrder[idx] > rr)
            return null;

        TreeNode root = new TreeNode(preOrder[idx++]);

        root.left = bstFromPreOrder(preOrder, lr, root.val);
        root.right = bstFromPreOrder(preOrder, root.val, rr);

        return root;
    }

    public static TreeNode bstFromPreOrder(int[] preOrder){
        idx = 0;
        return bstFromPreOrder(preOrder, -(int)1e9, (int)1e9);
    }

    public static TreeNode bstFromPostOrder(int[] postOrder, int lr, int rr){
        if(idx == -1 || postOrder[idx] < lr || postOrder[idx] > rr)
            return null;

        TreeNode root = new TreeNode(postOrder[idx--]);

        root.right = bstFromPostOrder(postOrder, root.val, rr);
        root.left = bstFromPostOrder(postOrder, lr, root.val);

        return root;
    }

    public static TreeNode bstFromPostOrder(int[] postOrder){
        idx = postOrder.length - 1;
        return bstFromPostOrder(postOrder, -(int)1e9, (int)1e9);
    }

    public static class bstLPair {
        TreeNode par = null;
        int lr = 0;
        int rr = 0;

        bstLPair(TreeNode par, int lr, int rr){
            this.par = par;
            this.lr = lr;
            this.rr = rr;
        }
    }

    public static TreeNode constructBSTfromLevelOrder(int[] levelOrder){
        if(levelOrder.length == 0)
            return null;

        LinkedList<bstLPair> que = new LinkedList<>();
        TreeNode root = new TreeNode(levelOrder[0]);
        int idx = 1;

        que.addLast(new bstLPair(root, -(int)1e9, root.val));
        que.addLast(new bstLPair(root, root.val, (int)1e9));

        while(idx < levelOrder.length){
            bstLPair rp = que.removeFirst();
            int lr = rp.lr, rr = rp.rr;
            if(levelOrder[idx] < lr || levelOrder[idx] > rr)
                continue;

            TreeNode node = new TreeNode(levelOrder[idx++]);
            if(node.val < rp.par.val)
                rp.par.left = node;
            else
                rp.par.right = node;

            que.addLast(new bstLPair(node, rp.lr, node.val));
            que.addLast(new bstLPair(node, node.val, rp.rr));
        }

        return root;
    }

    public static TreeNode preOrin(int[] pre, int psi, int pei, int[] in, int isi, int iei){
        if(isi > iei)
            return null;
        
        int idx = isi;
        while(in[idx] != pre[psi])
            idx++;

        int tel = idx - isi;

        TreeNode root = new TreeNode(pre[psi]);

        root.left = preOrin(pre, psi + 1, pei + tel, in, isi, idx - 1);
        root.right = preOrin(pre, psi + tel + 1, pei, in, idx + 1, iei);

        return root;
    }

    public static TreeNode postOrin(int[] post, int psi, int pei, int[] in, int isi, int iei){
        if(isi > iei)
            return null;

        int idx = isi;
        while(in[idx] != post[pei])
            idx++;

        int tel = idx - isi;

        TreeNode root = new TreeNode(post[pei]);

        root.left = postOrin(post, psi, psi + tel - 1, in, isi, idx - 1);
        root.right = postOrin(post, psi + tel, pei - 1, in, idx + 1, iei);
        
        return root;
    }
}