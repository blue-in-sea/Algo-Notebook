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
public class FlattenBinaryTreeToLinkedList {
  // Mathod 1: Traversal (not recommend!)
  private TreeNode lastNode = null;
  public TreeNode flatten(TreeNode root) {
    if (root == null) {
      return null;
    }
  
    helper(root);
    return root;
  }
  private void helper(TreeNode root) {
    if (root == null) {
      return;
    }

    if (lastNode != null) {
      lastNode.left = null;
      lastNode.right = root;  // here we modify the right child
    }

    lastNode = root;
    TreeNode right = root.right; // right child's reference would be lost 
    // if we do not save it before modify the root.right
    helper(root.left);
    helper(right);
  }
}
