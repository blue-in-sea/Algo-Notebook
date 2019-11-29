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
  /**
   * @param root: a TreeNode, the root of the binary tree
   * @return: root
   *
   * Mathod 1: Traversal (not recommend!)
   */
  private TreeNode lastNode = null; // declare a global var
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
  
  /**
   * @param root: a TreeNode, the root of the binary tree
   * @return: root
   *
   * Method 2: Divide and Conquer
   */
  public TreeNode flatten(TreeNode root) {
    helper(root);
    return root;
  }

  // flatten root and return the last node
  public TreeNode helper(TreeNode root) {
    if (root == null) {
      return null;
    }

    TreeNode leftLast = helper(root.left);
    TreeNode rightLast = helper(root.right);

    // connect leftLast to root.right
    if (leftLast != null) {
      leftLast.right = root.right;
      root.right = root.left;
      root.left = null;
    }

    if (rightLast != null) {
      return rightLast;
    }

    if (leftLast != null) {
      return leftLast;
    }

    return root;
  }
}
