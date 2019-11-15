/**
 * Determine if a given binary tree is binary search tree.There should no be duplicate keys in binary search tree.
 *
 * Assumptions: the keys stored in the binary search tree can not be Integer.MIN_VALUE or Integer.MAX_VALUE.
 * Corner Cases: What if the binary tree is null? Return true in this case.
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
public class IsValidBinarySearchTree {
  /**
   * @param root: The root of binary tree.
   * @return: True if the binary tree is BST, or false
   *
   * Time: O(N)
   * Space: O(H)
   */
  public boolean isBST(TreeNode root) {
    return checkBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }
  
  private boolean checkBST(TreeNode root, int min, int max) {
    if (root == null) {
      return true;
    }
    if (root.key < min || root.key > max) {
      return false;
    }
    return checkBST(root.left, min, root.key - 1) 
       && checkBST(root.right, root.key + 1, max);    // divide-and-conquer
  }
}
