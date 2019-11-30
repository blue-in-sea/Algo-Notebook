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
public class InvertBinaryTree {
  public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }
    helper(root);
    return root;
  }
  /**
   * @param root: a TreeNode, the root of the binary tree
   * @return: nothing
   */
  private void helper(TreeNode root) {
    if (root == null) {
      return;
    }
    TreeNode tmp = root.left;
    root.left = root.right;
    root.right = tmp;

    helper(root.left);
    helper(root.right);
  }
  
 
  /**
   * @param root: a TreeNode, the root of the binary tree
   * @return: root  
   */
   public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }
    TreeNode tmp = root.left;
    root.left = root.right;
    root.right = tmp;

    invertTree(root.left);
    invertTree(root.right);
    return root;
  }
}
