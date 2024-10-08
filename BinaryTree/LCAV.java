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
public class LCAV {
  public TreeNode lowestCommonAncestor(TreeNode root, List<TreeNode> nodes) {
    // Assumption: K >=2, No parent pointers given, and K nodes are 
    //             guaranteed to be in the binary tree
    Set<TreeNode> set = new HashSet<>(nodes);
    return lca(root, set);
  }
  private TreeNode lca(TreeNode root, Set<TreeNode> set) {
    // 1. What should you do on current layer?
    // base case: if root is null (or) root is one of the nodes in set 
    //            we can ignore the later recursion 
    if (root == null || set.contains(root)) {
      return root;
    }
    // 2. What do you expect from left or right child
    TreeNode left = lca(root.left, set);
    TreeNode right = lca(root.right, set);
    // 3. What should you report to the parent layer?
    if (left != null && right != null) {
      return root;
    }
    return left == null ? right : left;
  }
}

