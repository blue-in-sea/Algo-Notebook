/**
 * 143. Reorder List
 * You are given the head of a singly linked-list. The list can be represented as:
 *
 * L0 → L1 → … → Ln - 1 → Ln
 * Reorder the list to be on the following form:
 *
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 *
 * Input: head = [1,2,3,4]
 * Output: [1,4,2,3]
 *
 * Input: head = [1,2,3,4,5]
 * Output: [1,5,2,4,3]
 */
class Solution {
    // Time: O(n) There are three steps here. Each takes O(n)
    // Space: O(1)
    public ListNode reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 1. find the middle node - O(n)
        ListNode mid = findMiddle(head);
        ListNode one = head;
        ListNode two = mid.next;
        // de-link the second half from the list
        mid.next = null;
        // 2. reverse the second half - O(n/2)
        // 3. merge the two havles - O(n/2)
        return merge(one, reverse(two));
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        // p  h  n
        //    1->2->3->4->5->6 
        return prev;
    }

    // find the middle of linked list
    // in 1->2->3->4->5->6 find 3
    private ListNode merge(ListNode one, ListNode two) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (one != null && two != null) {
            cur.next = one;
            one = one.next;
            cur.next.next = two;
            two = two.next;
            cur = cur.next.next;
        }
        if (one != null) {
            cur.next = one;
        } else {
            cur.next = two;
        }
        return dummy.next;
    }


    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
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
