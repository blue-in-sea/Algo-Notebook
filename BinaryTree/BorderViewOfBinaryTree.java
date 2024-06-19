package BinaryTree; /**
 * 545. Border View Of Binary Tree
 *
 *              1
 *       x            2
 *              3           4
 * Input: root = [1,null,2,3,4]
 * Output: [1,3,4,2]
 * Explanation:
 * - The left boundary is empty because the root does not have a left child.
 * - The right boundary follows the path starting from the root's right child 2 -> 4.
 *   4 is a leaf, so the right boundary is [2].
 * - The leaves from left to right are [3,4].
 * Concatenating everything results in [1] + [] + [3,4] + [2] = [1,3,4,2].
 */

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
public class BorderViewOfBinaryTree {
  // dfsLeft()
  // dfsRight()
  // dfsLeaf()

  // Time: O(3n) traverse all nodes -> O(n)
  // Space: O(n) stack calls
  public List<Integer> borderView(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    if (root == null) {
      return res;
    }
    
    res.add(root.key);
    if (root.left == null && root.right == null) {
      return res;
    }

    dfsLeft(root.left, res);
    dfsLeaf(root, res);
    dfsRight(root.right, res);
    return res;
  }

  private void dfsLeft(TreeNode root, List<Integer> res) {
    if (root == null || (root.left == null && root.right == null)) { // exlcude leaf-view
      return;
    }

    res.add(root.key);
    if (root.left != null) {
      dfsLeft(root.left, res);
    } else {
      dfsLeft(root.right, res);
    }
  }

  private void dfsLeaf(TreeNode root, List<Integer> res) {
    if (root == null) {
      return;
    }
    if (root.left == null && root.right == null) {
      res.add(root.key);
      return;
    }
    dfsLeaf(root.left, res);
    dfsLeaf(root.right, res);
  }

  private void dfsRight(TreeNode root, List<Integer> res) {
    if (root == null || (root.left == null && root.right == null)) { // exlcude leaf-view
      return;
    }
  
    if (root.right != null) {
      dfsRight(root.right, res);
    } else {
      dfsRight(root.left, res);
    }
    res.add(root.key);
  }
}
