/**
 * LaiCode 39. Insert In Sorted Linked List
 * Insert a value in a sorted linked list.
 *
 * L = null, insert 1, return 1 -> null
 * L = 1 -> 3 -> 5 -> null, insert 2, return 1 -> 2 -> 3 -> 5 -> null
 * L = 1 -> 3 -> 5 -> null, insert 3, return 1 -> 3 -> 3 -> 5 -> null
 * L = 2 -> 3 -> null, insert 1, return 1 -> 2 -> 3 -> null
 */
public class InsertInSortedLinkedList {
    // Time: O(n), Space: O(1)
    public ListNode insert(ListNode head, int value) {
        // 1. determine if the insertion is before head
        ListNode newNode = new ListNode(value);
        if (head == null || value <= head.value) {
            newNode.next = head;
            return newNode;
        }
        // 2. insert the new node at the sequentially right position
        // using the previous node to traverse the Linked List 
        // the insertion should between prev and prev.next 
        ListNode prev = head;
        while (prev.next != null && prev.next.value < value) {
            prev = prev.next;
        }
        newNode.next = prev.next;
        prev.next = newNode;
        return head;
    }
}
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
