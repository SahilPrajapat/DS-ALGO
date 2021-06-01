package linkedList;

public class questions {
    public class Node{
        int data;
        Node next;
    }

    // -------------------------------------------------------------------------------------------------------------

    // Node head = null;
    // Node tail = null;

    // public int mid() {
    //     Node slow = head;
    //     Node fast = head;

    //     while(fast.next != null && fast.next.next != null){
    //         fast = fast.next.next;
    //         slow = slow.next;
    //     }
    //     return slow.data;
    // }

    // public void reversePI(){
    //     Node curr = head;
    //     Node prev = null;
    //     while(curr != null){
    //         Node forw = curr.next;

    //         curr.next = prev;

    //         prev = curr;
    //         curr = forw;
    //     }

    //     tail = head;
    //     head = prev;
    // }

    // public int length(){
    //     int len = 0;
    //     Node curr = head;
    //     while(curr != null){
    //         curr = curr.next;
    //         len++;
    //     }
    //     return len;
    // }

    // public void reverseDI(){
    //     int i = 0;
    //     int j = length() - 1;

    //     while (i < j) {
    //         Node in = getNodeAt(i);
    //         Node jn = getNodeAt(j);

    //         int temp = in.data;
    //         in.data = jn.data;
    //         jn.data = temp;

    //         i++;
    //         j--;
    //     }
    // }

    // private void reversePRHelper(Node node){
    //     if(node == null){
    //         return;
    //     }
    //     reversePRHelper(node.next);

    //     if(node == tail){

    //     }else {
    //         node.next.next = node;
    //     }
    // }
    // public void reversePR(){
    //     reversePRHelper(head);
    //     head.next = null;
    //     Node temp = head;
    //     head = tail;
    //     tail = temp;
    // }

    // public int KthFromLast(int k){
    //     Node slow = head;
    //     Node fast = head;

    //     for(int i = 0; i < k; i++){
    //         fast = fast.next;
    //     }
    //     while(fast != tail){
    //         fast = fast.next;
    //         slow = slow.next;
    //     }
    //     return slow.data;
    // }

    // public static LinkedList mergeTwoSortedList(LinkedList l1, LinkedList l2){
    //     Node c1 = l1.head;
    //     Node c2 = l2.head;

    //     LinkedList ans = new LinkedList();
    //     while(c1 != null && c2 != null){
    //         if(c1.data < c2.data){
    //             ans.addLast(c1.data);
    //             c1 = c1.next;
    //         }else {
    //             ans.addLast(c2.data);
    //             c2 = c2.next;
    //         }
    //     }
    //     while (c1 != null) {
    //         ans.addLast(c1.data);
    //         c1 = c1.next;
    //     }
    //     while (c2 != null) {
    //         ans.addLast(c2.data);
    //         c2 = c2.next;
    //     }
    //     return ans;
    // }

    // public static Node midNode(Node head){
    //     Node slow = head;
    //     Node fast = head;

    //     while (fast.next != null && fast.next.next != null) {
    //         fast = fast.next.next;
    //         slow = slow.next;
    //     }
    //     return slow;
    // }

    // public static LinkedList mergeSort(Node head, Node tail){
    //     if(head == tail){
    //         LinkedList br = new LinkedList();
    //         br.addLast(head.data);
    //         return br;
    //     }

    //     Node mid = midNode(head);

    //     Node head1 = head;
    //     Node tail1 = mid;
    //     Node head2 = mid.next;
    //     Node tail2 = tail;

    //     mid.next = null;

    //     LinkedList fsh = mergeSort(head1, tail1);
    //     LinkedList ssh = mergeSort(head2, tail2);

    //     mid.next = head2;

    //     return mergeTwoSortedList(ssh, fsh);
    // }

    // public static int addListHelper(Node one, int pv1, Node two, int pv2, LinkedList res){

    //     if(one == null && two == null){
    //         return 0;
    //     }

    //     int data = 0;
    //     if(pv1 > pv2){
    //         int oc = addListHelper(one.next, pv1 - 1, two, pv2, res);
    //         data = one.data + oc;
    //     }else if(pv1 < pv2){
    //         int oc = addListHelper(one, pv1, two.next, pv2 - 1, res);
    //         data = two.data + oc;
    //     }else {
    //         int oc = addListHelper(one.next, pv1 - 1, two.next, pv2 - 1, res);
    //         data = one.data + two.data + oc;
    //     }

    //     int nd = data % 10;
    //     int nc = data / 10;

    //     res.addFirst(nd);
    //     return nc;
    // }

    // public static LinkedList addTwoLists(LinkedList one, LinkedList two){
    //     LinkedList res = new LinkedList();

    //     int oc = addListHelper(one.head, one.size, two.head, two.size, res);
    //     if(oc > 0){
    //         res.addFirst(oc);
    //     }
    //     return res;
    // }

    // public static int findIntersection(LinkedList one, LinkedList two){

    //     Node t1 = one.head;
    //     Node t2 = two.head;

    //     int delta = Math.abs(one.size - two.size);
    //     if(one.size > two.size){
    //         for(int i = 0; i < delta; i++){
    //             t1 = t1.next;
    //         }
    //     }else {
    //             for(int i = 0 ; i < delta; i++){
    //                 t2 = t2.next;
    //             }
    //         }
    //         while(t1 != t2){
    //             t1 = t1.next;
    //             t2 = t2.next;
    //         }
    //     return t1.data;
    // }
}

