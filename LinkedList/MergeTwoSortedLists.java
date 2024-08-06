/**
 * 21. Merge Two Sorted Lists
 *
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 *
 * Input: list1 = [], list2 = []
 * Output: []
 *
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 */
class MergeTwoSortedLists {
    // Method 1: 谁小移谁
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
        
        // post-processing
        if (one != null) {
            cur.next = one;
        } else {
            cur.next = two;
        }
        return dummy.next;
    }

    // ==============================================================
    // Method 2: recursion
    // Time: O(n + m) where n is the size of one, m is the size of two
    // Space: O(n + m) stack calls 
    public ListNode mergeTwoLists(ListNode one, ListNode two) {
        if (one == null) {
            return two;
        } else if (two == null) {
            return one;
        } else if (one.val < two.val) {
            one.next = mergeTwoLists(one.next, two);
            return one;
        } else {
            two.next = mergeTwoLists(one, two.next);
            return two;
        }
    }
}
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
