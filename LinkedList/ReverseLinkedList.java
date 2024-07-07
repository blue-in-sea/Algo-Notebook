/**
 * 206. Reverse Linked List
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 * Input: head = N1 -> N2 -> N3 -> N3 -> N5
 * Output: head = N5 <- N4 <- N3 <- N2 <- N1
 */

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
class ReverseLinkedList {
    // Iterative
    // Time: O(n) for traverse the list
    // Space: O(1) 
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
  
    // Recursive
    // Time: O(n) for traverse the list
    // Space: O(n) for stack call
    public ListNode reverseList(ListNode head) {
        // base case
        if (head == null || head.next == null) {
            return head;
        }
        
        // recursive rule
        ListNode p = reverseList(head.next);
        head.next.next = head;   
        head.next = null;        
        return p;  // p is the newHead 
    }
}

// Base Case: head.next == null
// reverseList() at the node_5: return head = node_5, N5 

// Recursive Rule
// reverseList() at the node_4: 
// p = node_5, head = node_4, head.next = node_5
// N5 <- N4, return p 


// reverseList() at the node_3: 
// p = node_5, head = node_3, head.next = node_4
// N5 <- N4 <- N3, return p 

// reverseList() at the node_2: 
// p = node_5, head = node_2, head.next = node_3
// N5 <- N4 <- N3 <- N2, return p 

// reverseList() at the node_1: 
// p = node_5, head = node_1, head.next = node_2
// N5 <- N4 <- N3 <- N2 <- N1, return p 

// recursion exit
