/**
 * 86. Partition List
 * Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes 
 * greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 *
 * Input: head = [2,1], x = 2
 * Output: [1,2]
 */
class Solution {
    // Time: O(n), Space: O(1)
    public ListNode partition(ListNode head, int x) {
        // corner case 
        if (head == null || head.next == null) {
            return head;
        }
        // partition
        ListNode dummySmallHead = new ListNode(0);
        ListNode dummyLargeHead = new ListNode(0);
        ListNode smallTail = dummySmallHead;
        ListNode largeTail = dummyLargeHead;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                smallTail.next = cur;
                smallTail = smallTail.next;
            } else {
                largeTail.next = cur;
                largeTail = largeTail.next;
            }
            cur = cur.next;
        }
        // terminate the largeList with NULL to avoid loop
        largeTail.next = null;
        // re-Link the two intervals 
        smallTail.next = dummyLargeHead.next;
        return dummySmallHead.next;
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
