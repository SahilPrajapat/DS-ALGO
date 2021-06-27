import java.util.*;

public class l001 {
    
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
        return root == null ? -(int)1e9 : Math.max(Math.max(height(root.left), height(root.right)), root.val);
    }

    public static boolean find(TreeNode root, int data){
        if(root == null)
            return false;

        if(root.val == data)
            return true;

        return find(root.left, data) || find(root.right, data);
    }

    public static ArrayList<TreeNode> NodetoRootPath(TreeNode root, int data){
        if(root == null)
            return new ArrayList<>();

        if(root.val == data){
            ArrayList<TreeNode> base = new ArrayList<>();
            base.add(root);
            return base;
        }

        ArrayList<TreeNode> left = NodetoRootPath(root.left, data);
            if(left.size() != 0){
                left.add(root);
                return left;
            }

        ArrayList<TreeNode> right = NodetoRootPath(root.right, data);
        if(right.size() != 0){
            right.add(root);
            return right;
        }
        return new ArrayList<>();
    }

    public static boolean NodeToRootPath(TreeNode root, int data, ArrayList<TreeNode> ans){
        if(root == null)
            return false;

        if(root.val == data){
            ans.add(root);
            return true;
        }

        boolean res = NodeToRootPath(root.left, data, ans) || NodeToRootPath(root.right, data, ans);
        if(res)
            ans.add(root);
        return res;
    }

    public static void rootToAllLeafPath(TreeNode root, ArrayList<Integer> smallAns, ArrayList<ArrayList<Integer>> ans){
        if(root == null)
            return;

        if(root.left == null && root.right == null){
            ArrayList<Integer> base = new ArrayList<>(smallAns);
            base.add(root.val);
            ans.add(base);
            return;
        }

        smallAns.add(root.val);

        rootToAllLeafPath(root.left, smallAns, ans);
        rootToAllLeafPath(root.right, smallAns, ans);

        smallAns.remove(smallAns.size() - 1);
    }

    public static ArrayList<ArrayList<Integer>> rootToAllLeafPath(TreeNode root) {
        ArrayList<Integer> smallAns = new ArrayList<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        rootToAllLeafPath(root, smallAns, ans);
        return ans;
    }

    public static void singleChildNode(TreeNode node, ArrayList<Integer> ans){
        if(node == null || (node.left == null && node.right == null))
            return;

        if(node.left == null || node.right == null){
            ans.add(node.val);
        }

        singleChildNode(node.left, ans);
        singleChildNode(node.right, ans);
    }

    public static int countExactlyOneChild(TreeNode node) {
        if(node == null || (node.left == null && node.right == null))
            return 0;

        int left = countExactlyOneChild(node.left);
        int right = countExactlyOneChild(node.right);

        int ans = left + right;

        if(node.left == null || node.right == null){
            ans++;
        }

        return ans;
    }

    //imp leetcode 863
    public void kDown(TreeNode root, TreeNode blockNode, int k, List<Integer>ans){
        if(root == null || root == blockNode || k < 0)
            return;

        if(k == 0){
            ans.add(root.val);
            return;
        }
        kDown(root.left, blockNode, k - 1, ans);
        kDown(root.right, blockNode, k - 1, ans);
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        ArrayList<TreeNode> path = new ArrayList<>();
        NodeToRootPath(root, target.val, path);

        List<Integer> ans = new ArrayList<>();
        TreeNode blockNode = null;
        for(int i = 0; i < path.size(); i ++){
            if(k - 1 < 0)
                break;

            kDown(path.get(i), blockNode, k - i, ans);
            blockNode = path.get(i);
        }
        return ans;
    }

    public int distanceK_01(TreeNode root, TreeNode target, int k, ArrayList<Integer>ans){
        if(root == null)
            return -1;

        if(root == target){
            kDown(root, null, k, ans);
            return 1;
        }

        int ld = distanceK_01(root.left, target, k, ans);
        if(ld != -1){
            kDown(root, root.left, k, ans);
            return ld + 1;
        }

        int rd = distanceK_01(root.right, target, k, ans);
        if(rd != -1){
            kDown(root, root.right, k, ans);
            return ld + 1;
        }

        return -1;
    }

    public static void kDown(TreeNode root, int time, TreeNode blockNode, ArrayList<ArrayList<Integer>>ans){
        if(root == null || root == blockNode)
            return;

        if(time == ans.size())
            ans.add(new ArrayList<>());
        ans.get(time).add(root.val);

        kDown(root.left, time + 1, blockNode, ans);
        kDown(root.right, time + 1, blockNode, ans);
    }

    public static int burningTree(TreeNode root, int target, ArrayList<ArrayList<Integer>> ans){
        if(root == null)
            return -1;

        if(root.val == target){
            kDown(root, 0, null, ans);
            return 1;
        }

        int ld = burningTree(root.left, target, ans);
        if(ld != -1){
            kDown(root, ld, root.left, ans);
            return ld + 1;
        }

        int rd = burningTree(root.right, target, ans);
        if(rd != -1){
            kDown(root, rd, root.right, ans);
            return rd + 1;
        }

        return -1;
    }

    public static int burningTree(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        burningTree(root, target, ans);
        return ans.size() - 1; // for the size of tree that how much tree is burn
    }


    //-1 : did we get the target node, -2 : fire will not reach that node, t > 0 :
    // fire will reach with time t.
    public static void KDown(TreeNode root, int time, TreeNode blockNode, ArrayList<ArrayList<Integer>>ans, HashSet<Integer>water){
        if(root == null || root == blockNode || water.contains(root.val))
            return;

        if(time == ans.size())
            ans.add(new ArrayList<>());
        ans.get(time).add(root.val);

        KDown(root.left, time, blockNode, ans, water);
        KDown(root.right, time, blockNode, ans, water);
    }

    public static int burningTreeWithWater(TreeNode root, int target, ArrayList<ArrayList<Integer>>ans, HashSet<Integer> water){
        if(root == null)
            return -1;

        if(root.val == target){
            if(!water.contains(root.val)){
                KDown(root, 0, null, ans, water);
                return 1;
            }else 
                return -2;
        }

        int ld = burningTreeWithWater(root.left, target, ans, water);
        if(ld > 0){
            if(!water.contains(root.val)){
                KDown(root, ld, root.left, ans, water);
                return ld + 1;
            }else 
                return -2;
        }
        if(ld == -2)
            return -2;

        int rd = burningTreeWithWater(root.right, target, ans, water);
        if(rd > 0){
            if(!water.contains(root.val)){
                KDown(root, rd, root.right, ans, water);
                return rd + 1;
            }else
                return -2;
        }
        if(rd == -2)
            return -2;

        return -1;
    }


    //LCA    ==========================================================================================================================================
    
    TreeNode LCA = null;
    public boolean lowestCommonAncestor_(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null)
            return false;
        
        boolean self = false;
        if(root == p || root == q)
            self = true;
        
        boolean left = lowestCommonAncestor_(root.left, p, q);
        boolean right = lowestCommonAncestor_(root.right, p, q);
        
        if((left && right) || (self && left) || (self && right))
            LCA = root;
        
        return self || left || right;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lowestCommonAncestor_(root, p, q);
        return LCA;
    }

    public static void levelOrder(TreeNode root){
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int level = 0;
        while(que.size() != 0){
            int size = que.size();
            ArrayList<Integer> smallAns = new ArrayList<>();
            System.out.println(smallAns);
            while(size-- > 0){
                TreeNode rnode = que.removeFirst();
                smallAns.add(rnode.val);

                if(rnode.left != null)
                    que.addLast(rnode.left);

                if(rnode.right != null)
                    que.addLast(rnode.right);
            }
            ans.add(smallAns);
            level++;
        }

        int count = 0;
        for(var list : ans){
            System.out.println(count++ + " -> " + list);
        }
    }

    public static List<Integer> leftView(TreeNode root){
        if(root == null)
            return new ArrayList<>();
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        List<Integer> ans = new ArrayList<>();
        while(que.size() != 0){

            int size = que.size();
            ans.add(que.getFirst().val);
            while(size-- > 0){
                TreeNode rnode = que.removeFirst();

                if(rnode.left != null)
                    que.addLast(rnode.left);

                if(rnode.right != null)
                    que.addLast(rnode.right);
            }
        }
        return ans;
    }

    public static List<Integer> rightView(TreeNode root){
        if(root == null)
            return new ArrayList<>();
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        List<Integer> ans = new ArrayList<>();
        while(que.size() != 0){

            int size = que.size();
            ans.add(que.getFirst().val);
            while(size-- > 0){
                TreeNode rnode = que.removeFirst();

                if(rnode.right != null)
                    que.addLast(rnode.right);

                if(rnode.left != null)
                    que.addLast(rnode.left);
            }
        }
        return ans;
    }

    public static void widthofShadow(TreeNode node, int vl, int[]minMax){
        if(node == null)
            return;

        minMax[0] = Math.min(minMax[0], vl);
        minMax[1] = Math.max(minMax[1], vl);

        widthofShadow(node.left, vl - 1, minMax);
        widthofShadow(node.right, vl + 1, minMax);
    }

    public static class vPair{
        TreeNode node = null;
        int vl = 0;
        int level = 0;

        vPair(TreeNode node, int vl){
            this(node, vl, 0);
        }

        vPair(TreeNode node, int vl, int level){
            this.node= node;
            this.vl = vl;
            this.level = level;
        }
    }

    public static ArrayList<ArrayList<Integer>> verticalOrder(TreeNode root){
        if(root == null)
            return new ArrayList<>();
        LinkedList<vPair> que = new LinkedList<>();
        int [] minMax = new int[2];
        widthofShadow(root, 0, minMax);

        int len = minMax[1] - minMax[0] + 1;
        que.addLast(new vPair(root, Math.abs(minMax[0])));

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < len; i++)
            ans.add(new ArrayList<>());

        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                vPair rp = que.removeFirst();
                int vl = rp.vl;
                TreeNode node = rp.node;

                ans.get(vl).add(node.val);

                if(node.left != null)
                    que.addLast(new vPair(node.left, vl - 1));

                if(node.right != null)
                    que.addLast(new vPair(node.right, vl + 1));
            }
        }
        return ans;
    }

    public static ArrayList<Integer> bottomView(TreeNode root){
        if(root == null)
            return new ArrayList<>();
        LinkedList<vPair> que = new LinkedList<>();
        int[] minMax = new int[2];
        widthofShadow(root, 0, minMax);
        int len = minMax[1] - minMax[0] + 1;

        que.addLast(new vPair(root, Math.abs(minMax[0])));

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < len; i++)
            ans.add(null);

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vPair rp = que.removeFirst();
                int vl = rp.vl;
                TreeNode node = rp.node;

                ans.set(vl, node.val);

                if (node.left != null)
                    que.addLast(new vPair(node.left, vl - 1));
                if (node.right != null)
                    que.addLast(new vPair(node.right, vl + 1));
            }
        }

        return ans;
    }

    public static ArrayList<Integer> topView(TreeNode root){
        if(root == null)
            return new ArrayList<>();
        LinkedList<vPair> que = new LinkedList<>();
        int[] minMax = new int[2];
        widthofShadow(root, 0, minMax);
        int len = minMax[1] - minMax[0] + 1;

        que.addLast(new vPair(root, Math.abs(minMax[0])));

        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i < len; i++){
            ans.add(null);
        }

        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                vPair rm = que.removeFirst();
                int vl = rm.vl;
                TreeNode node = rm.node;

                if(ans.get(vl) == null)
                    ans.set(vl, node.val);

                if(node.left != null)
                    que.addLast(new vPair(node.left, vl - 1));

                if(node.right != null)
                    que.addLast(new vPair(node.right, vl + 1));
            }
        }
        return ans;
    }

    public static ArrayList<ArrayList<Integer>> digonalOrder(TreeNode root){
        LinkedList<TreeNode> que = new LinkedList<>();

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        que.addFirst(root);
        while(que.size() != 0){

            ArrayList<Integer> smallAns = new ArrayList<>();
            int size = que.size();
            while(size-- > 0){
                TreeNode node = que.removeFirst();
                while(node != null){
                    smallAns.add(node.val);
                    if(node.left != null)
                        que.addLast(node.left);
                    node = node.right;        
                }
            }
            ans.add(smallAns);
        }
        return ans;
    }

    public static ArrayList<ArrayList<Integer>> antiDigonalOrder(TreeNode root){
        LinkedList<TreeNode> que = new LinkedList<>();

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        que.addLast(root);
        while(que.size() != 0){
            int size = que.size();
            ArrayList<Integer> smallAns = new ArrayList<>();
            while(size-- > 0){
                TreeNode node = que.removeFirst();
                while(node != null){
                    smallAns.add(node.val);
                    if(node.right != null)
                        que.addLast(node.right);
                    node = node.left;
                }
            }
            ans.add(smallAns);
        }
        return ans;
    }

    //do not use them
    public static ArrayList<Integer> verticalOrderSum(TreeNode root){
        LinkedList<vPair> que = new LinkedList<>();
        int[] minMax = new int[2];
        widthofShadow(root, 0, minMax);
        int len = minMax[1] - minMax[0] + 1;
        
        que.addLast(new vPair(root, Math.abs(minMax[0])));

        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i < len; i++)
            ans.add(0);

        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                vPair rm = que.removeFirst();
                int vl = rm.vl;
                TreeNode node = rm.node;

                ans.set(vl, ans.get(vl) + node.val);

                if(node.left != null)
                    que.addLast(new vPair(node.left, vl - 1));

                if(node.right != null)
                    que.addLast(new vPair(node.right, vl + 1));
            }
        }
        return ans;
    }

    public static ArrayList<Integer> digonalOrderSum(TreeNode root){
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        ArrayList<Integer> ans = new ArrayList<>();
        while(que.size() != 0){
            int size = que.size();
            int sum = 0;
            while(size-- > 0){
                TreeNode node = que.removeFirst();
                while(node != null){
                    sum += node.val;
                    if(node.left != null)
                        que.addLast(node.left);
                    node = node.right;
                }
            }
            ans.add(sum);
        }
        return ans;
    }

    // for interview purpose
    public static class ListNode {
        int data;
        ListNode prev = null;
        ListNode next = null;

        ListNode(int data){
            this.data = data;
        }
    }

    public static void vertivalOrderSum(TreeNode root, ListNode node){
        node.data += root.val;
        if(root.left != null){
            if(node.prev == null){
                ListNode nnode = new ListNode(0);
                nnode.next = node;
                node.prev = nnode;
            }
            vertivalOrderSum(root.left, node.prev);
        }

        if(root.right != null){
            if(node.next == null){
                ListNode nnode = new ListNode(0);
                nnode.prev = node;
                node.next = nnode;
            }
            vertivalOrderSum(root.right, node.next);
        }
    }

    public static void vertivalOrderSum(TreeNode root){
        ListNode curr = new ListNode(0);
        vertivalOrderSum(root, curr);
    }

    public static void digonalOrderSum(TreeNode root, ListNode node){
        node.data += root.val;
        if(root.left != null){
            if(node.prev == null){
                ListNode nnode = new ListNode(0);
                nnode.next = node;
                node.prev = nnode;
            }
            vertivalOrderSum(root.left, node.prev);
        }

        if(root.right != null)
            vertivalOrderSum(root.right, node);
    }

    public static void digonalOrderSum2(TreeNode root){
        ListNode curr = new ListNode(0);
        digonalOrderSum(root, curr);
    }


    public List<List<Integer>> verticalTraversal(TreeNode root){
        PriorityQueue<vPair> que = new PriorityQueue<>((a, b) ->{
            if(a.vl != b.vl)
                return a.vl - b.vl;

            return a.node.val - b.node.val;
        });

        PriorityQueue<vPair> childQue = new PriorityQueue<>((a, b) -> {
            if(a.vl != b.vl)
                return a.vl - b.vl;

            return a.node.val - b.node.val;
        });

        int[] minMax = new int[2];
        widthofShadow(root, 0, minMax);
        int len = minMax[1] - minMax[0] + 1;
        que.add(new vPair(root, Math.abs(minMax[0])));

        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < len; i++)
            ans.add(new ArrayList<>());

        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                vPair rp = que.remove();
                int vl = rp.vl;
                TreeNode node = rp.node;

                ans.get(vl).add(node.val);

                if(node.left != null)
                    childQue.add(new vPair(node.left,  vl - 1));

                if(node.right != null)
                    childQue.add(new vPair(node.right, vl + 1));
            }

            PriorityQueue<vPair> temp = que;
            que = childQue;
            childQue = temp;
        }
        return ans;

    }

    public List<List<Integer>> verticalTraversal_02(TreeNode root){
        PriorityQueue<vPair> que = new PriorityQueue<>((a, b) ->{
            if(a.level != b.level)
                return a.level - b.level;
            else if(a.vl != b.vl)
                return a.vl - b.vl;

            return a.node.val - b.node.val;
        });


        int[] minMax = new int[2];
        widthofShadow(root, 0, minMax);
        int len = minMax[1] - minMax[0] + 1;
        que.add(new vPair(root, Math.abs(minMax[0]), 0));

        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < len; i++)
            ans.add(new ArrayList<>());

        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                vPair rp = que.remove();
                int vl = rp.vl;
                int level = rp.level;
                TreeNode node = rp.node;

                ans.get(vl).add(node.val);

                if(node.left != null)
                    que.add(new vPair(node.left,  vl - 1, level + 1));

                if(node.right != null)
                    que.add(new vPair(node.right, vl + 1, level + 1));
            }
        }
        return ans;
    }

    public static class allSolution {
        TreeNode pred = null;
        TreeNode succ = null;

        int ceil = (int)1e9;
        int floor = -(int)1e9;

        TreeNode prev = null;
    }

    public static void allSolution(TreeNode node, int data, allSolution pair){
        if(node == null)
            return;

        if(node.val < data)
            pair.floor = Math.max(pair.floor, node.val);

        if(node.val > data)
            pair.ceil = Math.min(pair.ceil, node.val);

        allSolution(node.left, data, pair);
        if(node.val == data)
            pair.pred = pair.prev;

        if(pair.prev != null && pair.prev.val == data)
            pair.succ = node;

        pair.prev = node;
        allSolution(node.right, data, pair);

    }
}

