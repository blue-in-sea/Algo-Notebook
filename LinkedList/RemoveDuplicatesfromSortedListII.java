/**
 * class ListNode {
 *   public int value;
 *   public ListNode next;
 *   public ListNode(int value) {
 *     this.value = value;
 *     next = null;
 *   }
 * }
 */
public class RemoveDuplicatesfromSortedListII {
  public ListNode removeDup(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode dummy = new ListNode(-1);
    dummy.next = head;

    ListNode pre = dummy;
    ListNode cur = head;
    ListNode first = dummy; // the first node in the new unduplicated(result) list.

    while (cur != null && cur.next != null) {
      // we found a unique node, we connect it at the tail of the unduplicated list, 
      // and update the first node.
      if (cur.value != pre.value && cur.value != cur.next.value) {
        first.next = cur;
        first = first.next;
      }
      pre = cur;
      cur = cur.next;
    }

    if (pre.value != cur.value) {  // the last node needs to be dealt with independently
      first.next = cur;
      first = first.next;
    }

    first.next = null;  // the subsequent list is duplicate.
    return dummy.next;
  }
}
