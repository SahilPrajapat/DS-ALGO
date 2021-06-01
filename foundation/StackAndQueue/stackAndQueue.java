package StackAndQueue;

import java.util.*;

public class stackAndQueue {
    public static Scanner scn = new Scanner(System.in);

    public static void duplicateBracket(String str){
        Stack<Character> st = new Stack<>();
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            if(ch == ')'){
                if(st.peek() == '('){
                    System.out.println(true);
                    return;
                } else {
                    while(st.peek() != '('){
                        st.pop();
                    }
                    st.pop();
                }
            } else {
                st.push(ch);
            }
        }
        System.out.println(false);
    }

    public static void balancedBracket(String str){
        Stack <Character> st = new Stack<>();
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            if (ch == '(' || ch == '{' || ch == '['){
                st.push(ch);
            } else if (ch == ')'){
                boolean val = handleClosing(st, '(');
                if(val == false){
                    System.out.println(val);
                    return;
                }

            } else if (ch == '}'){
                boolean val = handleClosing(st, '{');
                if(val == false){
                    System.out.println(val);
                    return;
                }

            } else if (ch == ']'){
                boolean val = handleClosing(st, '[');
                if(val == false){
                    System.out.println(val);
                    return;
                }

            }
        }
        if(st.size() == 0){
            System.out.println(true);
        }else{
            System.out.println(false);
        }
    }

    public static boolean handleClosing(Stack <Character> st, char corresofch){
        if(st.size() == 0){
            return false;
        } else if(st.peek() != corresofch){
            return false;
        } else {
            st.pop();
            return true;
        }
    }

    public static int[] nextGreeaterEletoRight(int[] arr){
        int [] nge = new int[arr.length];                          // if we want arr the we have to use // code  or if we index then we have to remove arr
        Stack <Integer> st = new Stack<>();

        st.push(arr.length - 1);          // st.push(arr[arr.length - 1])
        nge[arr.length - 1] = -1;

        for(int i = arr.length - 2; i >= 0; i--){      

            while(st.size() > 0 && arr[i] >= arr[st.peek()]){         // st.peek()
                st.pop();
            }

            if(st.size() == 0){
                nge[i] = arr.length;
            }else{
                nge[i] = st.peek();
            }
            st.push(i);                         //arr[i];
        }

        return nge;
    }

    public static void display(int[] a){
        StringBuilder sb = new StringBuilder();
        System.out.println("ans------------");
        for(int val : a){
            sb.append(val + "\n");
        }
        System.out.println(sb);
    }

    public static int[] stockSpan(int[] arr){
        int[] span = new int[arr.length];

        Stack<Integer> st = new Stack<>();
        st.push(0);
        span[0] = 1;

        for(int i = 1; i < arr.length; i++){
            while (st.size() > 0 && arr[i] > arr[st.peek()]) {
                st.pop();
            }
            if(st.size() == 0){
                span[i] = i + 1;
            }else {
                span[i] = i - st.peek();
            }
            st.push(i);
        } 
        return span;
    }

    public static void longestAreaHistogram(int[] arr){

        // right boundary Next smallest index on the right
        int[] rb = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        st.push(arr.length - 1);
        rb[arr.length - 1] = arr.length;
        for(int i = arr.length - 2; i >= 0; i--){
            while(st.size() > 0 && arr[i] <= arr[st.peek()]){
                st.pop();
            }
            if(st.size() == 0){
                rb[i] = arr.length;
            }else{
                rb[i] = st.peek();
            }
            st.push(i);
        }

        // left boundary Next smallest index on the left
        int[] lb = new int[arr.length];
        st = new Stack<>();
        st.push(0);
        lb[0] = -1;
        for(int i = 0; i < arr.length; i++){
            while (st.size() > 0 && arr[i] <= arr[st.peek()]) {
                st.pop();
            }
            if (st.size() == 0) {
                lb[i] = -1;
            }else {
                lb[i] = st.peek();
            }
            st.push(i);
        }


        int maxArea = 0;
        for(int i = 0; i < arr.length; i++){
            int width = rb[i] - lb[i] -1;
            int area = arr[i] * width;
            if(area > maxArea){
                maxArea = area;
            }
        }
        System.out.println(maxArea);
    }

    public static void slidingWindowMax(int[] arr, int[] nge, int k){
        int j = 0;
        for(int i = 0; i <= arr.length - k ; i++){
            if(j < i){
                j = i;
            }
            while(nge[j] < i + k){
                j = nge[j];
            }
            System.out.println(arr[j]);
        }
    }

    public static int precedence(char optor){
        if(optor == '+'){
            return 1;
        }else if(optor == '-'){
            return 1;
        }else if(optor == '*'){
            return 2;
        }else {
            return 2;
        }
    }
    public static int operation(int v1, int v2, char optor){
        if(optor == '+'){
            return v1 + v2;
        }else if(optor == '-'){
            return v1 - v2;
        }else if(optor == '*'){
            return v1 * v2;
        }else {
            return v1 / v2;
        }
    }

    public static void infixEvaluation(String exp){
        Stack<Integer> opnds = new Stack<>();
        Stack<Character> optors = new Stack<>();

        for(int i = 0; i < exp.length(); i++){
            char ch = exp.charAt(i);

            if(ch == '('){
                optors.push(ch);
            }else if(Character.isDigit(ch)){
                opnds.push(ch - '0');
            }else if(ch == ')'){
                while (optors.peek() != '(') {
                    char optor = optors.pop();
                    int v2 = opnds.pop();
                    int v1 = opnds.pop();

                    int opv = operation(v1, v2, optor);
                    opnds.push(opv);
                }
                optors.pop();

            }else if(ch == '+' || ch == '-' || ch == '*' || ch == '/'){
                while(optors.size() > 0 && optors.peek() != '(' &&
                precedence(ch) <= precedence(optors.peek())){
                    //ch is wanting to solve higher priority first
                    char optor = optors.pop();
                    int v2 = opnds.pop();
                    int v1 = opnds.pop();

                    int opv = operation(v1, v2, optor);
                    opnds.push(opv);
                }
                // ch is pushing itself
                optors.push(ch);
            }
        }
        while (optors.size() != 0) {
            char optor = optors.pop();
            int v2 = opnds.pop();
            int v1 = opnds.pop();

            int opv = operation(v1, v2, optor);
            opnds.push(opv);
        }
        System.out.println(opnds.peek());
    }

    public static void infixCovulation(String exp){
        Stack <String> postfix = new Stack<>();
        Stack <String> prefix = new Stack<>();
        Stack <Character> ops = new Stack<>();

        for(int i = 0; i < exp.length(); i++){
            char ch = exp.charAt(i);

            if(ch == '('){
                ops.push(ch);

            }else if((ch >= '0' && ch <= '9') ||(ch >= 'a' && ch <= 'z')||(ch >= 'A' && ch <= 'Z')){
                postfix.push(ch + "");
                prefix.push(ch + "");

            }else if(ch == ')'){
                while (ops.peek() != '(') {
                    process(ops, postfix, prefix);
                }
                ops.pop(); // pooping the "(""

            }else if(ch == '+' || ch == '-' || ch == '*' || ch == '/'){
                while (ops.size() > 0 && ops.peek() != '(' && precedence(ch) <= precedence(ops.peek())) {
                    process(ops, postfix, prefix);
                }
                ops.push(ch); // pushing current operator
            }
        }
        while (ops.size() > 0) {
            process(ops, postfix, prefix);
        }
        System.out.println(postfix.pop());
        System.out.println(prefix.pop());
    }

    public static void process(Stack<Character> ops, Stack<String> postfix, Stack<String> prefix){
        char optor = ops.pop();

        String postv2 = postfix.pop();
        String postv1 = postfix.pop();
        String postv = postv1 + postv2 + optor;
        postfix.push(postv);

        String prev2 = prefix.pop();
        String prev1 = prefix.pop();
        String prev = optor + prev1 + prev2;
        prefix.push(prev);
    }


    public static void postfixEvaluationConversion(String exp){
        Stack <Integer> vs = new Stack<>();
        Stack <String> is = new Stack<>();
        Stack <String> ps = new Stack<>();

        for(int i = 0; i < exp.length(); i++){
            char ch = exp.charAt(i);

            if(ch == '+' || ch == '-' || ch == '*' || ch == '/'){
                int v2 = vs.pop();
                int v1 = vs.pop();
                int val = operation(v1, v2, ch);
                vs.push(val);

                String iv2 = is.pop();
                String iv1 = is.pop();
                String ivl = "(" + iv1 + ch + iv2 + ")";
                is.push(ivl);

                String pv2 = ps.pop();
                String pv1 = ps.pop();
                String pvl = ch + pv1 + pv2;
                ps.push(pvl);

            } else {
                vs.push(ch - '0');
                is.push(ch + "");
                ps.push(ch + "");
            }
        }
        System.out.println(vs.peek());
        System.out.println(is.peek());
        System.out.println(ps.peek());
    }

    public static void prefixEvaluationConversion(String exp){
        Stack <Integer> vs = new Stack<>();
        Stack <String> is = new Stack<>();
        Stack <String> pos = new Stack<>();

        for(int i = exp.length() - 1; i >= 0; i--){
            char ch = exp.charAt(i);

            if(ch == '+' || ch == '-' || ch == '*' || ch == '/'){
                int v1 = vs.pop();
                int v2 = vs.pop();
                int val = operation(v1, v2, ch);
                vs.push(val);

                String iv1 = is.pop();
                String iv2 = is.pop();
                String ivl = "(" + iv1 + ch + iv2 + ")";
                is.push(ivl);

                String pv1 = pos.pop();
                String pv2 = pos.pop();
                String pvl = pv1 + pv2 + ch;
                pos.push(pvl);

            } else {
                vs.push(ch - '0');
                is.push(ch + "");
                pos.push(ch + "");
            }
        }
        System.out.println(vs.peek());
        System.out.println(is.peek());
        System.out.println(pos.peek());
    }

    public static void findCelebrity(int[][] arr){
        Stack <Integer> st = new Stack<>();
        for(int i = 0; i < arr.length; i++){
            st.push(i);
        }
        while(st.size() > 0){
            int i = st.pop();
            int j = st.pop();

            if(arr[i][j] == 1){
                st.push(j);
            }else {
                st.push(i);
            }
        }
        int pot = st.pop();
        for(int i = 0; i < arr.length; i++){
            if(i != pot){
                if(arr[i][pot] == 0 || arr[pot][i] == 1){
                    System.out.println("none");
                    return;
                }
            }
        }
        System.out.println(pot);
    }

    public static void mergeOverlappingIntervals(int [][] arr){
        Pair[] pairs = new Pair[arr.length];
        for(int i = 0; i < arr.length; i++){
            pairs[i] = new Pair(arr[i][0] , arr[i][1]);
        }

        Arrays.sort(pairs);
        Stack<Pair> st = new Stack<>();
        for(int i = 0; i < pairs.length; i++){
            if(i == 0){
                st.push(pairs[i]);
            }else{
                Pair top = st.peek();

                if(pairs[i].st > top.et){
                    st.push(pairs[i]);
                }else{
                    top.et = Math.max(top.et, pairs[i].et);
                }
            }
        }

        Stack<Pair> rs = new Stack<>();
        while(st.size() > 0){
            rs.push(st.pop());
        }
        while(rs.size() >0){
            Pair p = rs.pop();
            System.out.println(p.st + " " + p.et);
        }
    }

    public static class Pair implements Comparable<Pair>{
        int st;
        int et;

        Pair(int st, int et){
            this.st = st;
            this.et = et;
        }
        // this > other return +ve;
        // this = other return 0;
        // this < other return -ve;
        public int compareTo(Pair other){
            if(this.st != other.st){
                return this.st - other.st;
            }else{
                return this.et - other.et;
            }
        }
    }

    public static void smallestNumberPattern(String exp){

        Stack<Integer> st = new Stack<>();
        int num = 1;
        for(int i = 0; i < exp.length(); i++){
            char ch = exp.charAt(i);
            if(ch == 'd'){
                st.push(num);
                num++;
            }else{
                st.push(num);
                num++;

                while (st.size() > 0) {
                   System.out.print(st.pop());
                }
            }
        }

        st.push(num);
        while (st.size() > 0) {
            System.out.print(st.pop());
        }
    }




    public static void main(String[] args) {
        // String str = scn.nextLine();
        // duplicateBracket(str);

        // balancedBracket(str);

        // int n = scn.nextInt();
        // int [] a = new int[n];
        // for(int i = 0; i < n; i++){
        //     a[i] = scn.nextInt();
        // }
        // int[] nge = nextGreeaterEletoRight(a);
        // int[] stk = stockSpan(a);
        // display(stk);

        // longestAreaHistogram(a);

        int k = scn.nextInt();
        // slidingWindowMax(a, nge, k);

        // String exp = scn.nextLine();
        // infixEvaluation(exp);
        // infixCovulation(exp);
        // postfixEvaluationConversion(exp);

        int[][] arr = new int[k][2];
        for(int j = 0; j < k; j++){
            String line = scn.nextLine();
            arr[j][0] = Integer.parseInt(line.split(" ")[0]);
            arr[j][1] = Integer.parseInt(line.split(" ")[1]);
        } 
        mergeOverlappingIntervals(arr);
    }
    
}
