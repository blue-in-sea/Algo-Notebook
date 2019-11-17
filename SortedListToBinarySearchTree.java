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
  /**
   * Method 1: Recursion 
   * Time: O(N logN) = (level) log N * (find middle) N/2
   * Space: O(logN) for stack calls 
    */
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
  
  /**
   * Method 2: Recursion + toArray
   * Time: O(N) 
   * Space: O(N) 
   */
  public TreeNode sortedListToBST(ListNode head) {
    int[] array = sortedListToArray(head);
    return helper(array, 0, array.length - 1);
  }

  private int[] sortedListToArray(ListNode head) {
    List<Integer> list = new ArrayList<>();
    while (head != null) {
      list.add(head.value);
      head = head.next;
    }
    
    int[] array = new int[list.size()];
    for (int i = 0; i < array.length; i++) {
      array[i] = list.get(i);
    }
    return array;
  }

  private TreeNode helper(int[] array, int left, int right) {
    // Invalid case
    if (left > right) {
      return null;
    }

    // Middle element forms the root.
    int mid = (int) Math.ceil((double)(left + right) / 2.0); // int-coversion!!!
    TreeNode node = new TreeNode(array[mid]);

    // Base case for when there is only one element left in the array
    if (left == right) {
      return node;
    }

    // Recursively form BST on the two halves
    node.left = helper(array, left, mid - 1);
    node.right = helper(array, mid + 1, right);
    return node;
  }
}
