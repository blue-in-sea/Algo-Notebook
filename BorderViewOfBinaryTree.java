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
