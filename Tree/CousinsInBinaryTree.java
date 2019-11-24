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
public class CousinsInBinaryTree {
  // Time: O(N)
  // Space: O(N)
  public boolean isCousin(TreeNode root, int a, int b) {
    Map<Integer, Integer> depth = new HashMap<>();
    Map<Integer, TreeNode> parent = new HashMap<>();
    dfs(depth, parent, root, null);
    return (depth.get(a) == depth.get(b) && parent.get(a) != parent.get(b));
  }
  
  private void dfs(Map<Integer, Integer> depth, Map<Integer, TreeNode> parent, TreeNode node, TreeNode par) {
    if (node != null) {
      depth.put(node.key, par != null ? 1 + depth.get(par.key) : 0);
      parent.put(node.key, par);
      dfs(depth, parent, node.left, node);
      dfs(depth, parent, node.right, node);
    }
  }
}
