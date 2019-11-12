/* *
 * Determine if a given binary tree is full (If the root is null, return false).
 *
 * A full binary tree is defined as a binary tree in which all nodes have either zero or two child nodes. 
 * Conversely, there is no node in a full binary tree, which has one child node.
 *
 * Recursion: 1) Termination condition: 终止条件
 *            2) Recursive calls: 调用单元做了什么
 *            3) Return value: 返回值
 */

public class TreeNode {
  int key;
  TreeNode left;
  TreeNode right;
  
  TreeNode(int key) {
    this.key = key;
  }
}

public class isFullBinaryTree {
  /**
   * Method: Recursion
   *
   * base case: if it is a leaf node: return true
   *
   * 1. What should we do at current layer? 
   *    if the node has both left and right child: 
   *        recursively check its left and right subtree validity  
   *    else 
   *        return false
   *
   * 2. What we expect from our left and right child?
   *    (valid full tree or invalid?)
   *
   * 3. What should we return to our parent layer?
   *    (same as processure 1)  
   */
  public boolean isFull(TreeNode root) {
    // corner case
    if (root == null) {
      return false;
    }
    return checkFull(root);
  }

  private boolean checkFull(TreeNode root) {
    // base case: leaf node
    if (root.left == null && root.right == null) {
      return true;
    }
    // recursive calls 
    if (root.left != null && root.right != null) {
      return (checkFull(root.left) && checkFull(root.right));
    } 
    return false;
  }
}
