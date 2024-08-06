/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class MergeTwoSortedLists {
    // Time: O(n + m) where n is the size of one, m is the size of two
    // Space: O(1)
    public ListNode mergeTwoLists(ListNode one, ListNode two) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (one != null && two != null) {
            if (one.val <= two.val) {
                cur.next = one;
                one = one.next;
            } else {
                cur.next = two;
                two = two.next;
            }
            cur = cur.next;

        }

        if (one != null) {
            cur.next = one;
        } else {
            cur.next = two;
        }

        return dummy.next;
    }
}
