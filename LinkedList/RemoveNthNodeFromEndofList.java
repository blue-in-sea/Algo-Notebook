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
public class Solution {
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode fast = head;
	  ListNode slow = head;

    while (n > 0 && fast != null) {
      fast = fast.next;
      n--;
    }
    // Check if n is greater than the size of linked List 
    if (n > 0) {
      return head;
    }

    // Check if the linked list has only one node
	  if (fast == null) {
      return head.next;
    }

	  while (fast.next != null) {
      fast = fast.next;
      slow = slow.next;
    }
	  
    // Remove slow.next which is the nth form the end
	  slow.next = slow.next.next;
	  return head;
  }
}

