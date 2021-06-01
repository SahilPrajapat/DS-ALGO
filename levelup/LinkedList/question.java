import java.util.List;

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
}
