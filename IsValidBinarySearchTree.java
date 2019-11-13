public class TreeNode {
  int key;
  TreeNode left;
  TreeNode right;
  
  TreeNode(int key) {
    this.key = key;
  }
}

public class IsValidBinarySearchTree {
  // Time: O(N)
  // Space: O(H)
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
    return checkBST(root.left, min, root.key - 1) && checkBST(root.right, root.key + 1, max);
  }
}
