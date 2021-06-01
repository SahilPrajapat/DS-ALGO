package trees;

import java.util.*;
import java.io.*;

public class genericTrees {
    private static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();
    }

    public static void display(Node node){
        String str = node.data + " -> ";
        for(Node child : node.children){
            str += child.data + ", ";
        }
        str += ". ";
        System.out.println(str);

        for(Node child: node.children){
            display(child);
        }
    }

    public static Node construct(int[] arr){
        Node root = null;

        Stack<Node> st = new Stack<>();
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == -1){
                st.pop();
            }else {
                Node t = new Node();
                t.data = arr[i];

                if(st.size() > 0){
                    st.peek().children.add(t);
                }else{
                    root = t;
                }
                st.push(t);
            }
        }
        return root;
    }

    public static int size(Node node){
        int s = 0;
        for(Node child: node.children){
            int cs = size(child);
            s = s + cs;
        }
        s = s + 1;
        return s;
    }

    public static int max(Node node){
        int max = Integer.MIN_VALUE;
        for(Node child: node.children){
            int cm = max(child);
            max = Math.max(cm, max);
        }
        max = Math.max(node.data, max);

        return max;
    }

    public static int height(Node node){
        int ht = -1;              // use ht = 0 for node height
        for(Node child: node.children){
            int ch = height(child);
            ht = Math.max(ch, ht);
        }
        ht += 1;

        return ht;
    }

    public static void traversal(Node node){
      // euler's left, on the way deep in the recursion, node's pre
        System.out.println("Node Pre " + node.data);
        for(Node child: node.children){
            //edge's pre
            System.out.println("Edge Pre " + node.data + "--" + child.data);
            traversal(child);
            //edge's post
            System.out.println("Edge Post " + node.data + "--" + child.data);
        }
      // euler's right, on the way out in the  recursion, node's post
        System.out.println("Node Post " + node.data);
    }

    public static void levelOrder(Node node){
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);

        while(q.size() > 0){
            node = q.remove();
            System.out.println(node.data + " ");

            for(Node child: node.children){
                q.add(child);
            }
        }
        System.out.println(".");
    }

    public static void levelOrderLineWise(Node node){
        Queue<Node> mq = new ArrayDeque<>();
        mq.add(node);

        Queue<Node> cq = new ArrayDeque<>();
        while(mq.size() > 0){
            node = mq.remove();
            System.out.print(node.data);

            for(Node child: node.children){
                cq.add(child);
            }

            if (mq.size() == 0){
                mq = cq;
                cq = new ArrayDeque<>();
                System.out.println();
            }
        }
    }

    public static void levelOrderZigzag(Node node){
        Stack<Node> ms = new Stack<>();
        ms.push(node);

        Stack<Node> cs = new Stack<>();
        int level = 1;
        while(ms.size() > 0){
            node = ms.pop();
            System.out.println(node.data +" ");

            if(level % 2 == 1){
                for(int i = 0; i < node.children.size(); i++){
                    Node child = node.children.get(i);
                    cs.push(child);
                }
            }else {
                for(int i = node.children.size() - 1; i >=0; i--){
                    Node child = node.children.get(i);
                    cs.push(child);
                }
            }
            if(ms.size() == 0){
                ms = cs;
                cs = new Stack<>();
                level++;
                System.out.println();
            }
        }
    }

    public static void mirror(Node node){
        for(Node child: node.children){
            mirror(child);
        }
        Collections.reverse(node.children);
    }

    public static void removeLeave(Node node){
        for(int i = node.children.size() - 1; i >=0; i--){
            Node child = node.children.get(i);
            if(child.children.size() == 0){
                node.children.remove(child);
            }
        }
        for(Node child: node.children){
            removeLeave(child);
        }
    }

    public static void linearize(Node node){
        for(Node child: node.children){
            linearize(child);
        }

        while(node.children.size() > 1){
            Node lc = node.children.remove(node.children.size() - 1);
            Node sl = node.children.get(node.children.size() - 1);
            Node stl = getTail(sl);
            stl.children.add(lc);
        }
    }

    private static Node getTail(Node node){
        while(node.children.size() == 1){
            node = node.children.get(0);
        }
        return node;
    }

    public static Node linearize2(Node node){
        if(node.children.size() == 0){
            return node;
        }

        Node lkt = linearize2(node.children.get(node.children.size() - 1));
        while(node.children.size() > 1){
            Node last = node.children.remove(node.children.size() - 1);
            Node sl = node.children.get(node.children.size() - 1);
            Node slkt = linearize2(sl);
            slkt.children.add(last);
        }
        return lkt;
    }

    public static boolean findData(Node node, int data){
        if(node.data == data){
            return true;
        }

        for(Node child: node.children){
            boolean fic = findData(child, data);
            if(fic){
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Integer> nodeTorootPath(Node node, int data){
        if(node.data == data){
            ArrayList<Integer> list = new ArrayList<>();
            list.add(node.data);
            return list;
        }

        for(Node child: node.children){
            ArrayList<Integer> ptc = nodeTorootPath(child, data);
            if(ptc.size() > 0){
                ptc.add(node.data);
                return ptc;
            }
        }
        return new ArrayList<>();
    }

    public static int lca(Node node, int d1, int d2){
        ArrayList<Integer> p1 = nodeTorootPath(node, d1);
        ArrayList<Integer> p2 = nodeTorootPath(node, d2);

        int i = p1.size() - 1;
        int j = p2.size() - 1;

        while (i >= 0 && j >= 0 && p1.get(i) == p2.get(j)) {
            i--;
            j--;
        }
        i++;
        j++;

        return p1.get(i);
        //if wwe want to know the distance between two node then return i+j;
    }

    public static boolean areSimilar(Node n1, Node n2){
        if(n1.children.size() != n2.children.size()){
            return true;
        }

        for(int i = 0; i < n1.children.size(); i++){
            Node c1 = n1.children.get(i);
            Node c2 = n2.children.get(i);
            if(areSimilar(c1, c2) == false){
                return false;
            }
        }
        return true;
    }

    public static boolean areMirror(Node n1, Node n2){
        if(n1.children.size() != n2.children.size()){
            return false;
        }

        for(int i = 0; i < n1.children.size(); i++){
            int j = n1.children.size() - 1 - i;
            Node c1 = n1.children.get(i);
            Node c2 = n2.children.get(j);
            if(areMirror(c1, c2) == false){
                return false;
            }
        }
        return true;
    }

    public static boolean isSymetric(Node node){
        return areMirror(node, node);
    }

    static int sizee;
    static int mine;
    static int maxe;
    static int heighte;
    public static void multisolver(Node node, int depth){
        sizee++;
        mine = Math.min(mine, node.data);
        maxe = Math.max(maxe, node.data);
        heighte = Math.max(heighte, depth);

        for(Node child: node.children){
            multisolver(child, depth);
        }
    }

    static Node predecessor;
    static Node successor;
    static int state;
    public static void predecessorsuccessor(Node node, int data){
        if(state == 0){
            if(node.data == data){
                state = 1;
            }else{
                predecessor = node;
            }
        }else if(state == 1){
            successor = node;
            state = 2;
        }
        for(Node child: node.children){
            predecessorsuccessor(child, data);
        }
    }
    
    static int ceil;  //smallest among largest
    static int floor;  //largest among smallest
    public static void ceilAndFloor(Node node, int data){
        if(node.data > data){
            if(node.data < ceil){
                ceil = node.data;
            }
        }
        if(node.data < data){
            if(node.data > floor){
                floor = node.data;
            }
        }

        for(Node child: node.children){
            ceilAndFloor(child, data);
        }
    }

    public static int kthLargest(Node node, int k){
        floor = Integer.MIN_VALUE;
        int factor = Integer.MAX_VALUE;
        for(int i = 0; i < k; i++){
            ceilAndFloor(node, factor);
            factor = floor;
            floor = Integer.MIN_VALUE;
        }
        return factor;
    }

    static int msn = 0;
    static int ms = Integer.MIN_VALUE;
    public static int retSumAndCalculateMST(Node node){
        int sum = 0;

        for(Node child: node.children){
            int csum = retSumAndCalculateMST(child);
            sum += csum;
        }
        sum += node.data;

        if(ms < sum){
            msn = node.data;
            ms = sum;
        }
        return sum;
    }

    static int dia = 0;
    public static int calculateDiaReturnHeight(Node node){
        int dch = -1;
        int sdch = -1;

        for(Node child: node.children){
            int ch = calculateDiaReturnHeight(child);
            if(ch > dch){
                sdch = dch;
                dch = ch;
            }else if(ch > sdch){
                sdch = ch;
            }
        }

        int cnd = dch + sdch + 2;
        if(cnd > dia){
            dia = cnd;
        }
        dch += 1;
        return dch;
    }

    static class Pair{
        Node node;
        int state;

        Pair(Node node, int state){
            this.node = node;
            this.state = state;
        }
    }

    public static void IterativePreandPostOrder(Node node){
        Stack<Pair> st = new Stack<>();
        st.push(new Pair(node, -1));

        String pre = "";
        String post = "";
        while(st.size() > 0){
            Pair top = st.peek();
            if(top.state == -1){
                pre += top.node.data + " ";
                top.state++;
            }else if(top.state == top.node.children.size()){
                post += top.node.data + " ";
                st.pop();
            }else{
                Pair cp = new Pair(top.node.children.get(top.state), -1);
                st.push(cp);

                top.state++;
            }
        }
        
        System.out.println(pre);
        System.out.println(post);
    }



    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
          arr[i] = Integer.parseInt(values[i]);
        }
    
        Node root = construct(arr);
        // traversal(root);

        sizee = 0;
        mine = Integer.MAX_VALUE;
        maxe = Integer.MIN_VALUE;
        heighte = 0;
        multisolver(root, 0);
        System.out.println("Size = " + sizee);

        int data = Integer.parseInt(br.readLine());
        predecessor = null;
        successor = null;
        state = 0;
        predecessorsuccessor(root, data);
        if(predecessor == null){
          System.out.println("Predecessor = Not found");
        } else {
          System.out.println("Predecessor = " + predecessor.data);
        }
    
        if(successor == null){
          System.out.println("Successor = Not found");
        } else {
          System.out.println("Successor = " + successor.data);
        }

        ceil = Integer.MAX_VALUE;
        floor = Integer.MIN_VALUE;

        System.out.println(msn + "@" + ms);
    }
}
