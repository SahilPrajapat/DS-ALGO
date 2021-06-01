import java.io.*;
import java.util.*;

public class heap {

    public static void basic(int []ranks){
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); 
                                                    // if we pass Collection.reverseOrder() it will give the highest priorty first;
        // int[] ranks = {12,3,6,8,66,44,99,101};

        for(int val: ranks){
            pq.add(val);
        }

        while(pq.size() > 0){
            System.out.println(pq.peek());
            pq.remove();
        }
    }

    public static void kLargestEle(int[] arr, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < arr.length; i++){
            if(i < k){
                pq.add(arr[i]);
            }else {
                if(arr[i] > pq.peek()){
                    pq.remove();
                    pq.add(arr[i]);
                }
            }
        }
        while(pq.size() > 0){
            System.out.println(pq.remove());
        }
    }

    public static void kSmallestEle(int[]arr, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < arr.length; i++){
            if(i < k){
                pq.add(arr[i]);
            }else {
                if(arr[i] < pq.peek()){
                    pq.remove();
                    pq.add(arr[i]);
                }
            }
        }
        while (pq.size() > 0) {
            System.out.println(pq.remove());
        }
    }

    


    public static void sortKSorted(int[]arr, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i <= k; i++){
            pq.add(arr[i]);
        }

        for(int i = k + 1; i < arr.length; i++){
            System.out.println(pq.remove());
            pq.add(arr[i]);
        }

        while(pq.size() > 0){
            System.out.println(pq.remove());
        }
    }

    public static class MedianPriorityQueue {
        PriorityQueue<Integer> left;
        PriorityQueue<Integer> right;

        public MedianPriorityQueue(){
            left = new PriorityQueue<>(Collections.reverseOrder());
            right = new PriorityQueue<>();
        }

        public void add(int val){
            if(right.size() > 0 && val > right.peek()){
                right.add(val);
            }else {
                left.add(val);
            }

            if(left.size() - right.size() == 2){
                right.add(left.remove());
            }else if(right.size() - left.size() == 2){
                left.add(right.remove());
            }
        }

        public int remove(){
            if(this.size() == 0){
                System.out.println("UnderFlow");
                return -1;
            }else if(left.size() > right.size()){
                return left.remove();
            }else {
                return right.remove();
            }
        }

        public int peek() {
            if(this.size() == 0){
                System.out.println("UnderFlow");
                return -1;
            }else if(left.size() > right.size()){
                return left.peek();
            }else {
                return right.peek();
            }
        }

        public int size(){
            return left.size() + right.size();
        }
        

        public static class Pair implements Comparable<Pair> {
            int li;
            int di;
            int val;
            
            Pair(int li, int di, int val){
                this.li = li;
                this.di = di;
                this.val = val;
            }

            public int compareTo(Pair o){
                return this.val - o.val;
            }
        }

        public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>>lists){
            ArrayList<Integer> rv = new ArrayList<>();

            PriorityQueue<Pair> pq = new PriorityQueue<>();
            for(int i = 0; i < lists.size(); i++){
                Pair p = new Pair(i, 0, lists.get(i).get(0));
                pq.add(p);
            }

            while(pq.size() > 0){
                Pair p = pq.remove();
                rv.add(p.val);
                p.di++;

                if(p.di < lists.get(p.li).size()){
                    p.val = lists.get(p.li).get(p.di);
                    pq.add(p);
                }
            }
            return rv;
        }

    }

    public static ArrayList<Integer> mergeTwoList(ArrayList<Integer>list1, ArrayList<Integer>list2){
        ArrayList<Integer> ans = new ArrayList<>();
        int i = 0, n = list1.size();
        int j = 0, m = list2.size();

        while(i < n && j < m){
            if(list1.get(i) < list2.get(i))
                ans.add(list1.get(i++));
            else
                ans.add(list2.get(j++));
        }

        while(i < n){
            ans.add(list1.get(i++));
        }
        while(j < m){
            ans.add(list2.get(j++));
        }
        return ans;
    }

    public static ArrayList<Integer> mergeKSortedLists2(ArrayList<ArrayList<Integer>>lists, int si, int ei){
        if(si == ei)
            return lists.get(si);
        int mid = (si + ei)/2;
        ArrayList<Integer> list1 = mergeKSortedLists2(lists, si, mid);
        ArrayList<Integer> list2 = mergeKSortedLists2(lists, mid + 1, ei);
        
        return mergeTwoList(list1, list2);

    }


    //write priorityqueue using heap
    public static class PriorityQueue2{
        ArrayList<Integer> data;

        public PriorityQueue2() {
            data = new ArrayList<>();
        }

        public void add(int val){
            data.add(val);
            upheapify(data.size() - 1);
        }
        public void upheapify(int i){
            if(i == 0){
                return;
            }else {
                int pi = (i - 1) / 2;
                if(data.get(i) < data.get(pi)){
                    swap(i, pi);
                    upheapify(pi);
                }
            }
        }
        private void swap(int i, int j){
            int ith = data.get(i);
            int jth = data.get(j);
            data.set(i, jth);
            data.set(j, ith);
        }


        public int remove() {
            if(this.size() == 0){
                System.out.println("Underflow");
                return -1;
            }else {
                swap(0, data.size() - 1);
                int val = data.remove(data.size() - 1);
                downheapify(0);
                return val;
            }
        }
        private void downheapify(int pi){
            int mini = pi;

            int li = 2 * pi + 1;
            if(li < data.size() && data.get(li) < data.get(mini)){
                mini = li;
            }

            int ri = 2 * pi + 2;
            if(ri < data.size() && data.get(ri) < data.get(mini)){
                mini = ri;
            }

            if(mini != pi){
                swap(pi, mini);
                downheapify(mini);
            }
        }

        public int peek() {
            if(this.size() == 0){
                System.out.println("Underflow");
                return -1;
            }else {
                return data.get(0);
            }
        }

        public int size() {
            return data.size();
        }
    }



    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int []arr = new int[n];
        for(int i = 0; i < arr.length; i++){
            arr[i] = scn.nextInt();
        }
        basic(arr);

        // int k = scn.nextInt();

        // kLargestEle(arr, k);
        // kSmallestEle(arr, k);

        // sortKSorted(arr, k);

    }
}
