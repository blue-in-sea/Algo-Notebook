/*
 * Reverse pairs of elements in a singly-linked list.
 *
 * Examples
 * input  N1 -> N2 -> N3 -> N4 -> NULL
 * return N2 -> N1 -> N4 -> N3 -> NULL
 *
 */
class ListNode {
  int value;
  int ListNode next;
  
  ListNode(int value) {
    this.value = value;
    next = null;
 }
 
 public class Solution {
  // Method1: Recursion
  public ListNode reverseInPairs(ListNode head) {
    // 1. Terminating condition for base case 
    // 返回值：head 为空指针或者 next 为空指针,
    // 也就是当前无节点或者只有一个节点, 无法进行交换
    if (head == null || head.next == null) {
      return head;
    }
    // 2. What should we do on the current level?
    // 调用单元：设需要交换的两个点为 head 和 next, head 连接后面
    // 交换完成的子链表，next 连接 head, 完成交换
    ListNode newHead = head.next;
    head.next = reverseInPairs(head.next.next);
    newHead.next = head;
    // 3. What should we return to the parent level?
    // 返回值：交换完成的子链表
    return newHead;
  }
  
  // Method2: Iterative
  public ListNode reverseInPairsIterative(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode cur = dummy;
    while (cur.next != null && cur.next.next != null) {
      ListNode next = cur.next.next;        // next = N2
      cur.next.next = cur.next.next.next;   // N2 -> N3
      next.next = cur.next;                 // N3 -> N1
      cur = cur.next.next;                  // cur move 2 steps along the list 
    }
    return dummy.next;
  }
}
