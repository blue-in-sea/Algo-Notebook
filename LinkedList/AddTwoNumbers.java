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
public class AddTwoNumbers {
  public ListNode addTwoNumbers(ListNode a, ListNode b) {
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;
    int val = 0;
    while (a != null || b != null || val != 0) {
      if (a != null) {
        val += a.value;
        a = a.next;
      }
      if (b != null) {
        val += b.value;
        b = b.next;
      }
      cur.next = new ListNode(val % 10);
      val = val / 10;
      cur = cur.next;
    }
    return dummy.next;
  }
}
