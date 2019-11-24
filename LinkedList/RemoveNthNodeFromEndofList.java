public class RemoveNthNodeFromEndofList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode slow = dummy; // handle input = [1], n = 1 case
        ListNode fast = dummy;
        slow.next = head; 
        
        for (int i = 1; i <= n + 1; i++) {
            fast = fast.next;
        }
        
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        slow.next = slow.next.next; // slow points to the N-th node when exit while
        return dummy.next;
    }
}
