package LinkedList; /**
 * 148. Merge Sort On Linked List
 * Given the head of a linked list, return the list after sorting it in ascending order.
 *
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 *
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 *
 * Input: head = []
 * Output: []
 */

/**
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class MergeSortLinkList {
    // Merge Sort a Link List
    // Time: O(nlogn), Space: O(logn)
    public ListNode sortList(ListNode head) {
        // base case 
        if (head == null || head.next == null) return head;
        // 1. find the middle, then divide the list into halves
        ListNode mid = findMiddle(head);
        ListNode midNext = mid.next;
        mid.next = null; // 要断开！！
        // 2. recursion 
        ListNode left = sortList(head);
        ListNode right = sortList(midNext);
        return merge(left, right);
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

    private ListNode merge(ListNode one, ListNode two) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        while (one != null && two != null) {
            // 谁小移谁
            if (one.val < two.val) {
                curr.next = one;
                one = one.next;
            } else {
                curr.next = two;
                two = two.next;
            }
            curr = curr.next;
        }
        if (one != null) {
            curr.next = one;
        } else {
            curr.next = two;
        }
        return dummy.next;
    }
}
