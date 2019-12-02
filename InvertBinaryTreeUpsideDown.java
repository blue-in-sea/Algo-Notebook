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
public class InvertBinaryTreeUpsideDown {
  // Method 1: Recursion
  public TreeNode reverse(TreeNode root) {
    if (root == null || root.left == null) {
      return root;
    }

    TreeNode newRoot = reverse(root.left);
    root.left.right = root.right;
    root.left.left = root;

    root.left = null;
    root.right = null;

    return newRoot;
  }
}
