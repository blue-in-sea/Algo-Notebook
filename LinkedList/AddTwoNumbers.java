/**
 * 2. Add Two Numbers
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse 
 * order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807
 *
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 *
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 */
class Solution {
    public ListNode addTwoNumbers(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0); 
        ListNode cur = dummy;

        int sum = 0;

        // Traverse two lists and sum
        while (a != null || b != null || sum != 0) {
            if (a != null) {
                sum += a.val;
                a = a.next;
            }
            if (b != null) {
                sum += b.val;
                b = b.next;
            }

            // if sum is greate than 10
            ListNode node = new ListNode(sum % 10);
            sum = sum / 10;

            cur.next = node;
            cur = cur.next;
        }

        return dummy.next;
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
