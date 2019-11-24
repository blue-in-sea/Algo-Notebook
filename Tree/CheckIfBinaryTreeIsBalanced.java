/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int key;
 *     public TreeNode left, right;
 *     public TreeNode(int key) {
 *         this.key = key;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class CheckIfBinaryTreeIsBalanced {
  // Method 1: use depth() only to indicate
  // Time: O(N)
  // Spce: O(H)
  public boolean isBalanced(TreeNode root) {
    if (root == null)  return true;  // corner case
    return depth(root) != -1;
  }
  // 从顶至底DFS，以每个节点为根节点，递归判断是否是平衡二叉树
  // 当我们发现有一例 左/右子树高度差 ＞ 1 的情况时，代表此树不是平衡树，返回-1；
  private int depth(TreeNode root) { 
    if (root == null) return 0;
    int left = depth(root.left);
    int right = depth(root.right);
    if (left == -1 || right == -1 || Math.abs(left - right) > 1)  return -1;
    return Math.max(left, right) + 1;
  }
  
  // Method 2: 从顶至底DFS，以每个节点为根节点，递归判断是否是平衡二叉树
  // Time: O(N^2) Not Recommended
  // Spce: O(H)
  public boolean isBalanced(TreeNode root) {
    if (root == null)  return true;
    return Math.abs(depth(root.left) - depth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
  }
  
  private int depth(TreeNode root) { 
    if (root == null) return 0;
    return Math.max(depth(root.left), depth(root.right)) + 1;
  }
}
