
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class BinaryTreePaths {
  /**
   * @param root: the root of the binary tree
   * @return: all root-to-leaf paths
   */
  public List<String> binaryTreePaths(TreeNode root) {
    List<String> ans = new ArrayList<String> (); 
    String path = new String(); 

    dfs(root, path, ans); 
    return ans; 
  }

  private void dfs(TreeNode node, String path, List<String> ans) {
    if (node == null) {
      return;
    }

    if (node.left == null && node.right == null) {
      ans.add(path + node.val + ""); 
      return;
    }

    dfs(node.left, path + (node.val) + "->" , ans);
    dfs(node.right, path + (node.val) + "->", ans) ;
  }
}
