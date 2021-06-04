// import java.util.List;

public class question {
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val){
            this.val = val;
        }
    }

    public static ListNode midNode2(ListNode head){
        if(head == null || head.next == null) return head;

        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static ListNode midNode(ListNode head){
        if(head == null || head.next == null) return head;

        ListNode slow = head, fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }    

    public static ListNode reversNode(ListNode head){
        if(head == null || head.next == null) return head;

        ListNode curr = head, prev = null;
        while(curr != null){
            ListNode forw = curr.next;
            curr.next = prev;
            
            prev = curr;
            curr = forw;
        }
        return prev;
    }

    public static boolean isPalindrome(ListNode head){
        if(head == null || head.next == null) return true;

        ListNode mid = midNode(head);
        ListNode nhead = mid.next;
        mid.next = null;

        nhead = reversNode(nhead);

        ListNode c1 = head, c2 = nhead;

        boolean res = true;
        while(c2 != null){
            if(c1.val != c2.val){
                res = false;
                break;
            }

            c1 = c1.next;
            c2 = c2.next;

        }
        nhead = reversNode(nhead);
        mid.next = nhead;

        return res;
    }

    public static void fold(ListNode head){
        if(head == null || head.next == null) return;

        ListNode mid = midNode(head);
        ListNode nhead = mid.next;
        mid.next = null;

        nhead = reversNode(nhead);

        ListNode c1 = head, c2 = nhead;
        while(c2 != null){
            ListNode f1 = c1.next, f2 = c2.next;

            c1.next = c2;
            c2.next = f1;

            c1 = f1;
            c2 = f2;
        }
        
    }

    public static void unfold(ListNode head){
        if(head == null || head.next == null) return;

        ListNode l1 = new ListNode(-1);
        ListNode l2 = new ListNode(-1);
        ListNode p1 = l1, p2 = l2, c1 = head, c2 = head.next;

        while(c1 != null && c2 != null){
            p1.next = c1;
            p2.next = c2;

            p1 = p1.next;
            p2 = p2.next;

            if(c2 != null)
                c1 = c2.next;  // for odd
            if(c1 != null)
                c2 = c1.next; //for even
        }

        p1.next = null;
        p2 = reversNode(l2.next);
        p1.next = p2;
    }

    public static void unfold2(ListNode head){
        ListNode l1 = new ListNode(-1);
        ListNode l2 = new ListNode(-1);
        ListNode p1 = l1, p2 = l2;

        p1.next = head;
        p2.next = head.next;

        p1 = p1.next;
        p2 = p2.next;

        while(p2 != null && p2.next != null){
            ListNode f = p2.next;

            p1.next = f;
            p2.next = f.next;

            p1 = p1.next;
            p2 = p2.next;
        }
        p1.next = null;
        p2 = reversNode(l2.next);
        p1.next = p2;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2){
        if(l1 == null || l2 == null)
            return l1 != null ? l1 : l2;

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy, c1 = l1, c2 = l2;

        while(c1 != null && c2 != null){
            if(c1.val <= c2.val){
                prev.next = c1;
                c1 = c1.next;
            }else {
                prev.next = c2;
                c2 = c2.next;
            }
            prev = prev.next;
        }
        prev.next = c1 != null ? c1 : c2;
        return dummy.next;
    }

    public static ListNode mergeKLists(ListNode[] lists){
        if(lists.length == 0)
            return null;

        ListNode head = null;
        for(ListNode node: lists){
            head = mergeTwoLists(head, node);
        }
        return head;
    }

    // T : O(NlogkK), S : O(logK) -> N = k times of (avg length Of Linkedlist),
    // where k is length of lists.
    public static ListNode mergeList(ListNode[] lists, int si, int ei){
        if(si == ei)
            return lists[si];

        int mid = (si + ei)/2;
        ListNode leftMergeList = mergeList(lists, si, mid);
        ListNode rightMergeList = mergeList(lists, mid + 1, ei);

        return mergeTwoLists(leftMergeList, rightMergeList);
    }

    public static ListNode mergeKLists2(ListNode[] lists){
        if(lists.length == 0)
            return null;
        
        return mergeList(lists, 0, lists.length - 1);
    }

    public static ListNode mergeSort(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode mid = midNode(head);
        ListNode nhead = mid.next;
        mid.next = null;

        return mergeTwoLists(mergeSort(head), mergeSort(nhead));
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || n <= 0)
            return head;

        ListNode slow = head, fast = head;
        while(n-- > 0){
            fast = fast.next;
            if(fast == null && n > 0)
                return head;
        }

        if(fast == null){
            ListNode rmnode = slow;
            head = rmnode.next;
            rmnode.next = null;
            return head;
        }

        while(fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }

        ListNode rmnode = slow.next;
        slow.next = rmnode.next;
        rmnode.next = null;

        return head;
    }


    public static ListNode segregateEvenOdd(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode odd = new ListNode(-1);
        ListNode even = new ListNode(-1);
        ListNode op = odd, ep = even, curr = head;

        while(curr != null){
            if(curr.val % 2 != 0){
                op.next = curr;
                op = op.next;
            }else {
                ep.next = curr;
                ep = ep.next;
            }
            curr = curr.next;
        }

        ep.next = odd.next;
        op.next = null;
        head  = even.next;

        odd.next = even.next = null;

        return head;
    }


    public static ListNode segregate01(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode one = new ListNode(-1);
        ListNode zero = new ListNode(-1);
        ListNode op = one, zp = zero, curr = head;

        while(curr != null){
            if(curr.val != 0){
                op.next = curr;
                op = op.next;
            }else {
                zp.next = curr;
                zp = zp.next;
            }
            curr = curr.next;
        }

        zp.next = one.next;
        op.next = null;
        head = zero.next;

        zero.next = one.next = null;

        return head;
    }

    public static ListNode segregate012(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode zero = new ListNode(-1);
        ListNode one = new ListNode(-1);
        ListNode two = new ListNode(-1);
        ListNode zp = zero, op = one, tp = two, curr = head;

        while(curr != null){
            if(curr.val == 0){
                zp.next = curr;
                zp = zp.next;
            }else if(curr.val == 1){
                op.next = curr;
                op = op.next;
            }else{
                tp.next = curr;
                tp = tp.next;
            }
            curr = curr.next;
        }

        op.next = two.next;
        zp.next = one.next;
        tp.next = null;

        head = zero.next;
        zero.next = one.next = two.next = null;

        return head;
    }

    public static ListNode segregateOnLastIndex(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode small = new ListNode(-1);
        ListNode large = new ListNode(-1);
        ListNode sp = small, lp = large, curr = head;

        ListNode pivot = head;
        while(pivot.next != null)
            pivot = pivot.next;
        
        while(curr != null){
            if(curr.val <= pivot.val){
                sp.next = curr;
                sp = sp.next;
            }else {
                lp.next = curr;
                lp = lp.next;
            }
            curr = curr.next;
        }

        sp.next = large.next;
        lp.next = null;

        return sp;
    }

    public static ListNode segregate(ListNode head, int pivotIdx) {
        if(head == null || head.next == null)
            return head;

        ListNode small = new ListNode(-1);
        ListNode large = new ListNode(-1);
        ListNode sp = small, lp = large, curr = head;

        ListNode pivot = head;
        while(pivotIdx-- > 0)
            pivot = pivot.next;

        while(curr != null){
            if(curr == pivot){

            }else if(curr.val <= pivot.val){
                sp.next = curr;
                sp = sp.next;
            }else {
                lp.next = curr;
                lp = lp.next;
            }
            curr = curr.next;
        }

        sp.next = pivot;
        pivot.next = large.next;
        lp.next = null;
        head = small.next;

        return head;
    }

}
