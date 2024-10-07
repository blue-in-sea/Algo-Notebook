/**
 * public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
public class isBST {
  // Time: O(N)
  // Space: O(H)
  public boolean isBST(TreeNode root) {
    return checkBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }
  
  private boolean checkBST(TreeNode root, long min, long max) {
    if (root == null) {
      return true;
    }
    if (root.key < min || root.key > max) {
      return false;
    }
    return checkBST(root.left, min, root.key - 1) && checkBST(root.right, root.key + 1, max);
  }
}
