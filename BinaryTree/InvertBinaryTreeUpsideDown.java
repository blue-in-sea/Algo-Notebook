/**
 * 178. Reverse Binary Tree Upside Down
 * Given a binary tree where all the right nodes are leaf nodes, flip it upside down and turn it into a tree with 
 * left leaf nodes as the root.
 *
 * Examples
 *
 *         1
 *       /    \
 *     2        5
 *   /   \
 * 3      4
 *
 * is reversed to
 *
 *         3
 *       /    \
 *     2        4
 *   /   \
 * 1      5
 */
public class InvertBinaryTreeUpsideDown {
  // Method 1: Recursion
  public TreeNode reverse(TreeNode root) {
    if (root == null || root.left == null) {
      return root;
    }

    TreeNode newRoot = reverse(root.left);
    root.left.right = root.right;
    root.left.left = root;

    root.left = null;
    root.right = null;

    return newRoot;
  }
  
  // Method 2: Iterative
  public TreeNode reverse(TreeNode root) {
    TreeNode prev = null;
    TreeNode prevRight = null;
    while (root != null) {
      TreeNode next = root.left;
      TreeNode curRight = root.right;
      root.right = prevRight;  
      root.left = prev;       
      prevRight = curRight;    
      prev = root;            
      root = next;             
    }
    return prev;
  }
}

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
