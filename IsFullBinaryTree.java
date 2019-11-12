/* *
 * Determine if a given binary tree is full.
 * A full binary tree is defined as a binary tree in which all nodes have either zero or two child nodes. 
 * Conversely, there is no node in a full binary tree, which has one child node.
 * If the root is null, return false.
 *
 * Recursion: 1) Termination condition: 终止条件
 *            2) Return value: 返回值
 * [base case] if it is a leaf node: return true
 * [recursive rules]
 * 1. What should we do at current layer? 
 *    if the node has both left and right child: 
 *        recursively check its left and right subtree  
 *    else 
 *        return false
 * 2. What we expect from our left and right child?
 *    valid full tree or invalid?
 * 3. What should we return to our parent layer?
 *    (same as processure 1)     
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
