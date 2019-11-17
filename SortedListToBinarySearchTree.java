/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * For testing purpose, please make sure for any node in the result, its left sub-tree should have equal 
 * or only one more node than its right sub-tree.
 */

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
/**
 * public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *     left = right = null;
 *   }
 * }
 */
public class SortedListToBinarySearchTree {
  public TreeNode sortedListToBST(ListNode head) {
    // corner case: if the list is empty, then the tree should be empty
    if (head == null) {
      return null;
    }
    return helper(head); 
  }
  
  private TreeNode helper(ListNode head) {
    // base case 1
    if (head == null) {
      return null;
    }
    ListNode mid = findMiddle(head);
    TreeNode root = new TreeNode(mid.value);
    // base case 2 
    if (head == mid) {
      return root;
    }    
    root.left = helper(head);
    root.right = helper(mid.next);
    return root;
  }
  
  private ListNode findMiddle(ListNode head) {
    ListNode prev = null;
    ListNode slow = head;
    ListNode fast = head;
    while (fast != null && fast.next != null) {
      prev = slow;
      slow = slow.next;
      fast = fast.next.next;
    }
    if (prev != null) {
      prev.next = null;
    }
    return slow;  // for even nodes, slow is the first node of the second half
  }
}
