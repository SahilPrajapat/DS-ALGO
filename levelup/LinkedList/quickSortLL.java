import java.util.List;

public class quickSortLL {
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static int length(ListNode node){
        if(node == null)
            return 0;

        ListNode curr = node;
        int len = 0;
        while(curr != null){
            len++;
            curr = curr.next;
        }
        return len;
    }

    public static ListNode[] segrate(ListNode head, int pivotIdx){

        ListNode small = new ListNode(-1);
        ListNode large = new ListNode(-1);
        ListNode pivotNode = head, sp = small, lp = large, curr = head;

        while(pivotIdx-- > 0)
            pivotNode = pivotNode.next;

        while(curr != null){
            if(curr != pivotNode){
                if(curr.val <= pivotNode.val){
                    sp.next = curr;
                    sp = sp.next;
                }else {
                    lp.next = curr;
                    lp = lp.next;
                }
            }
            curr = curr.next;
        }

        pivotNode.next = null;
        sp.next = null;
        lp.next = null;

        return new ListNode[]{small.next, pivotNode, large.next};
    }

    public static ListNode[] mergeElement(ListNode[] left, ListNode pivotNode, ListNode[] right){
        ListNode head = null;
        ListNode tail = null;

        if(left[0] != null && right[0] != null){
            head = left[0];
            tail = right[1];
            left[1].next = pivotNode;
            pivotNode.next = right[0];
        }else if(left[0] != null){
            head = left[0];
            tail = pivotNode;
            left[1].next = pivotNode;
        }else if(right[0] != null){
            head = pivotNode;
            tail = right[1];
            pivotNode.next = right[0];
        }else{
            head = tail = pivotNode;
        }

        return new ListNode[]{head, tail};
    }


    public static ListNode[] quickSort(ListNode head){
        if(head == null || head.next == null)
            return new ListNode[]{head, head};
        
        int len = length(head);
        int pivotIdx = len/2;
        ListNode[] segrateElements = segrate(head, pivotIdx);

        ListNode[] left = quickSort(segrateElements[0]);
        ListNode[] right = quickSort(segrateElements[2]);

        return mergeElement(left, segrateElements[1], right);
    }

    public static ListNode Sort(ListNode head) {
        return quickSort(head)[0];
    }



    public static void main(String[] args) {
        
    }
}