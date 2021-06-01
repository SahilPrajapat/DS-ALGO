package trees;

import java.util.*;
import java.io.*;

public class binaryTrss {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static class Pair {
        Node node;
        int state;

        Pair(Node node, int state){
            this.node = node;
            this.state = state;
        }
    }

    public static Node construction(Integer []arr){
        
        Node root = new Node(arr[0], null, null);
        Pair rtp = new Pair(root, 1);

        Stack<Pair> st = new Stack<>();
        st.push(rtp);

        int idx = 0;
        while(st.size() > 0){
            Pair top = st.peek();
            if(top.state == 1){

                idx++;
                if(arr[idx] != null){
                    top.node.left = new Node(arr[idx], null, null);
                    Pair lp = new Pair(top.node.left, 1);
                    st.push(lp);
                }else {
                    top.node.left = null;
                }

                top.state++;
            }else if(top.state == 2){
                if(arr[idx] != null){
                    top.node.right = new Node(arr[idx], null, null);
                    Pair rp = new Pair(top.node.right, 1);
                    st.push(rp);
                }else {
                    top.node.right = null;
                }

                top.state++;
            }else {
                st.pop();
            }
        }
        return root;

    }

    public static void display(Node node){
        if(node == null){
            return;
        }

        String str = "";
        str += node.left == null? "." : node.left.data + "";
        str += " <- " + node.data + " -> ";
        str += node.right == null? ".": node.right.data + "";
        System.out.println(str);

        display(node.left);
        display(node.right);
    }

    public static int size(Node node) {
        if(node == null){
            return 0;
        }
        int ls = size(node.left);
        int rs = size(node.right);
        int ts = ls + rs + 1;
        
        return ts;
      }
    
      public static int sum(Node node) {
        if(node == null){
            return 0;
        }
        int lsm = sum(node.left);
        int rsm = sum(node.right);
        int tsm = lsm + rsm + node.data;
        
        return tsm;
      }
    
      public static int max(Node node) {
        if(node == null){
            return Integer.MIN_VALUE;
        }
        
        int lm = max(node.left);
        int rm = max(node.right);
        int tm = Math.max(node.data ,Math.max(lm, rm));
        
        return tm;
      }
    
      public static int height(Node node) {
        if(node == null){
            return -1;    //-1 for edge, and 0 for node
        }
        int lh = height(node.left);
        int rh = height(node.right);
        int th = Math.max(lh, rh) + 1;
        
        return th;
      }

      public static void traversal(Node node){
          if(node == null){
              return;
          }

          System.out.println(node.data + "PreOrder");
          traversal(node.left);
          System.out.println(node.data + "InOrder");
          traversal(node.right);
          System.out.println(node.data + "PostOrder");
      }

      public static void levelOrder(Node node){
          Queue<Node> mq = new ArrayDeque<>();
          mq.add(node);

          while(mq.size() > 0){
              int count = mq.size();
              for(int i = 0; i < count; i++){
                  node = mq.remove();
                  System.out.print(node.data + " ");

                  if(node.left != null){
                      mq.add(node.left);
                  }
                  if(node.right != null){
                      mq.add(node.right);
                  }
              }
              System.out.println();
          }
      }

      public static void iterativePreInPost(Node node){
          Stack<Pair> st = new Stack<>();
          Pair rtp = new Pair(node, 1);
          st.push(rtp);
            String pre = "";
            String post = "";
            String in = "";
          while(st.size() > 0){
              Pair top = st.peek();
              if(top.state == 1){
                pre += top.node.data + " ";
                top.state++;
                if(top.node.left != null){
                    Pair lp = new Pair(top.node.left, 1);
                    st.push(lp);
                }
              }else if(top.state == 2){
                in += top.node.data + " ";
                top.state++;
                if(top.node.right != null){
                    Pair rp = new Pair(top.node.right, 1);
                    st.push(rp);
                }
              }else {
                post += top.node.data + " ";
                st.pop();
              }
          }
          System.out.println(pre);
          System.out.println(in);
          System.out.println(post);
      }

      //node to root path;
      static ArrayList<Integer> path;
      public static boolean find(Node node, int data){
          if(node == null){
              return false;
          }
          if(node.data == data){
              path.add(node.data);
              return true;
          }

          boolean filc = find(node.left, data);
          if(filc){
              path.add(node.data);
              return true;
          }
          boolean firc = find(node.right, data);
          if(firc){
              path.add(node.data);
              return true;
          }
          return false;
      }

      public static void printKleveldown(Node node, int k){
          if(node == null){
              return;
          }
          if(k == 0){
              System.out.println(node.data);
          }
          printKleveldown(node.left, k - 1);
          printKleveldown(node.right, k - 1);
      }

    //   imp quetion 
      public static void printKNodesFar(Node node, int data, int k){
        path2 = new ArrayList<>();
        find2(node, data);
        for(int i = 0; i < path2.size(); i++){
            printKleveldown2(path2.get(i), k - i, i == 0? null: path2.get(i - 1));
        }
      }

      static ArrayList<Node> path2;
      public static boolean find2(Node node, int data){
          if(node == null){
              return false;
          }
          if(node.data == data){
              path2.add(node);
              return true;
          }

          boolean filc = find2(node.left, data);
          if(filc){
              path2.add(node);
              return true;
          }
          boolean firc = find2(node.right, data);
          if(firc){
              path2.add(node);
              return true;
          }
          return false;
      }
      public static void printKleveldown2(Node node, int k, Node blocker){
        if(node == null || k < 0 || node == blocker){
            return;
        }
        if(k == 0){
            System.out.println(node.data);
        }
        printKleveldown2(node.left, k - 1, blocker);
        printKleveldown2(node.right, k - 1, blocker);
    }


    public static void pathToLeafFromRoot(Node node, String path, int sum, int lo, int hi){
        if(node == null){
            return;
        }
        if(node.left == null && node.right == null){
            sum += node.data;
            if(sum >= lo && sum <= hi){
                System.out.println(path + node.data);
            }
            return;
        }
        
        pathToLeafFromRoot(node.left, path + node.data + " ", sum + node.data, lo, hi);
        pathToLeafFromRoot(node.right, path + node.data + " ", sum + node.data, lo, hi);
    }

    public static Node createLeftCloneTree(Node node){
        if(node == null){
            return null;
        }

        Node lcr = createLeftCloneTree(node.left);
        Node rcr = createLeftCloneTree(node.right);

        Node nn = new Node(node.data, lcr, null);
        node.left = nn;
        node.right = rcr;

        return node;
    }

    public static Node transBackFromLeftClonedTree(Node node){
        if(node == null){
            return null;
        }
        Node lnn = transBackFromLeftClonedTree(node.left.left);
        Node rnn = transBackFromLeftClonedTree(node.right);

        node.left = lnn;
        node.right = rnn;

        return node;
    }


    public static void printSingleChild(Node node, Node parent){
        if(node == null){
            return;
        }

        if(parent != null && parent.left == node && parent.right == null){
            System.out.println(node.data);
        }else if(parent != null &&  parent.right == node && parent.left == null){
            System.out.println(node.data);
        }

        printSingleChild(node.left, node);
        printSingleChild(node.right, node);
    }

    public static Node removeLeave(Node node){
        if(node == null){
            return null;
        }

        if(node.left == null && node.right == null){
            return null;
        }

        node.left = removeLeave(node.left);
        node.right = removeLeave(node.right);

        return node;
    }

    public static int diameter1(Node node){
        if(node == null){
            return 0;
        }

        int ld = diameter1(node.left);
        int rd = diameter1(node.right);

        int f = height(node.left) + height(node.right) + 2;

        int dia = Math.max(f, Math.max(ld, rd));

        return dia;
    }

    static class DiaPair {
        int ht;
        int dia;
    }

    public static DiaPair diameter2(Node node){
        if(node == null){
            DiaPair bp = new DiaPair();
            bp.ht = -1;
            bp.dia = 0;
            return bp;
        }

        DiaPair lp = diameter2(node.left);
        DiaPair rp = diameter2(node.right);

        DiaPair mp = new DiaPair();
        mp.ht = Math.max(lp.ht, rp.ht) + 1;

        int fes = lp.ht + rp.ht + 2;
        mp.dia = Math.max(fes, Math.max(lp.dia, rp.dia));

        return mp;
    }


    static int tilt = 0;
    public static int tilt(Node node){
        if(node == null){
            return 0;
        }

        int ls = tilt(node.left);
        int rs = tilt(node.right);

        int ltitl = Math.abs(ls - rs);
        tilt += ltitl;

        int ts = ls + rs + node.data;

        return ts;
    }

    static boolean isBal = true;
    public static int isBalanced(Node node){

        int lh = isBalanced(node.left);
        int rh = isBalanced(node.right);

        int gap = Math.abs(lh - rh);
        if(gap > 1){
            isBal = false;
        }

        int th = Math.max(lh, rh) + 1;

        return th;
    }

    public static class BSTPair{
        boolean isBST;
        int min;
        int max;
    }

    public static BSTPair isBST(Node node){
        if(node == null){
            BSTPair bp = new BSTPair();
            bp.min = Integer.MAX_VALUE;
            bp.max = Integer.MIN_VALUE;
            bp.isBST = true;
            return bp;
        }


        BSTPair lp = isBST(node.left);
        BSTPair rp = isBST(node.right);

        BSTPair mp = new BSTPair();

        mp.isBST = lp.isBST && rp.isBST && (node.data >= lp.max && node.data <= rp.min);

        mp.max = Math.max(node.data, Math.max(lp.max, rp.max));
        mp.min = Math.min(node.data, Math.min(lp.min, rp.min));

        return mp;
    }

    public static class BSTPair2{
        boolean isBST2;
        int min;
        int max;
        int size;
        Node root;
    }
    public static BSTPair2 isBST2(Node node){
        if(node == null){
            BSTPair2 bp = new BSTPair2();
            bp.isBST2 = true;
            bp.min = Integer.MAX_VALUE;
            bp.max = Integer.MIN_VALUE;
            bp.size = 0;
            bp.root = null;
            return bp;
        }

        BSTPair2 lp = isBST2(node.left);
        BSTPair2 rp = isBST2(node.right);

        BSTPair2 mp = new BSTPair2();
        mp.isBST2 = lp.isBST2 && rp.isBST2 && (node.data >= lp.min && node.data <= rp.max);

        mp.min = Math.min(node.data, Math.min(lp.min, lp.max));
        mp.max = Math.max(node.data, Math.max(rp.min, rp.max));

        if(mp.isBST2){
            mp.root = node;
            mp.size = lp.size + rp.size + 1;
        }else if(lp.size > rp.size){
            mp.root = lp.root;
            mp.size = lp.size;
        }else{
            mp.root = rp.root;
            mp.size = rp.size;
        }

        return mp;
    }


 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Integer[] arr = new Integer[n];
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
          if (values[i].equals("n") == false) {
            arr[i] = Integer.parseInt(values[i]);
          } else {
            arr[i] = null;
          }
        }
        // int data = Integer.parseInt(br.readLine());
        // int k = Integer.parseInt(br.readLine());
        // Node root = construction(arr);
        // display(root);    
        // path = new ArrayList<>();
        // printKNodesFar(root, data, k);  
        
        // DiaPair p = diameter2(root);
        // System.out.println(p.dia);

        // BSTPair bp = isBST(root);
        // System.out.println(bp.isBST);

        // BSTPair2 bp = isBST2(root);
        // System.out.println(bp.root.data + "@" + bp.size);
    }
    
}
