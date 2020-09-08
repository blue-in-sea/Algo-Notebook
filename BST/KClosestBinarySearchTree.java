/**
 * In a binary search tree, find k nodes containing the closest numbers to the given target number. 
 * return them in sorted array (ascending)
 * Assmumption: root is not null, and no duplicate keys in the given binary search tree.
 */

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int key;
 *     public TreeNode left, right;
 *     public TreeNode(int key) {
 *         this.key = key;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class KClosestBinarySearchTree {
  /**
   * @param root: the given BST
   * @param target: the given target
   * @param k: the given k
   * @return: k values in the BST that are closest to the target
   * 
   * Method 1: inOrder Traversal convert BST into a sort array and then do k-closest in the given array
   * Time: O(n), Space: O(n) where n is the number of node in BST
   */
  public List<Integer> closestKValues(TreeNode root, double target, int k) {
    Deque<Integer> dq = new ArrayDeque<>();    
    inOrder(root, dq);
    
    while (dq.size() > k) {
      if (Math.abs(dq.peekFirst() - target) > Math.abs(dq.peekLast() - target)) {
        dq.pollFirst();
      } else {
        dq.pollLast();
      }
    }
    
    return new ArrayList<>(dq);
  }

  private void inOrder(TreeNode root, Deque<Integer> dq) {
    if (root == null) return;

    inOrder(root.left, dq);
    dq.add(root.key);
    inOrder(root.right, dq);
  }
  
 /**
   * Method 2: do k-closest in the input BST
   * Time: O(h + k), Space: O(h) where h is the height of node in BST
   * Time: O(logn + k), Space: O(logn) if assuem the BST is balanced 
   */
}
