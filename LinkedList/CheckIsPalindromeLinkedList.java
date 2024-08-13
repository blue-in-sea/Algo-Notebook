/**
 * 234. Palindrome Linked List
 * Given the head of a singly linked list, return true if it is a 
 * palindrome or false otherwise.
 *
 * Input: head = [1,2,2,1]
 * Output: true
 *
 *
 * Input: head = [1,2]
 * Output: false
 */
public class CheckIsPalindromeLinkedList {
  // Time: O(n), Space: O(1)
  public boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null) {
      return true;
    }
    ListNode middle = findMiddle(head);
    ListNode two = reverse(middle.next);
    while (two != null) {
      if (head.value != two.value) {
        return false;
      }
      head = head.next;
      two = two.next;
    }
    return true;
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

  private ListNode reverse(ListNode head) {
    ListNode prev = null;           // prev initialize as null
    while (head != null) {
      ListNode next = head.next;    // record the next node
      head.next = prev;             // reverse happens here
      prev = head;                  // prev move 1 step
      head = next;                  // head move 1 step
    }
    return prev;                    // prev is the new head,  
  }                                 //(exit while)head is null
}
/**
 * Definition for ListNode
 * public class ListNode {
 *     int key;
 *     ListNode next;
 *     ListNode(int key) {
 *         this.key = key;
 *         next = null;
 *     }
 * }
 */
