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
  
  // Method 2: Iterative
  public TreeNode reverse(TreeNode root) {
    TreeNode prev = null;
    TreeNode prevRight = null;

    while (root != null) {
      TreeNode next = root.left;
      TreeNode curRight = root.right;

      root.right = prevRight;  // make new right-connection
      root.left = prev;        // make new left-connection

      prevRight = curRight;    // prevRight move one forward
      prev = root;             // prev move one forward

      root = next;             // root move one forward
    }

    return prev;
  }
}
