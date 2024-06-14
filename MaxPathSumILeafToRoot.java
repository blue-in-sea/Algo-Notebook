/**
 * Given a binary tree in which each node contains an integer number. Find the maximum possible path sum from a leaf 
 * to root.
 *          10
 *
 *        /      \
 *
 *     -2        7
 *
 *   /     \
 *
 * 8      -4
 *
 * The maximum path sum is 10 + 7 = 17.
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
public class MaxPathSumILeafToRoot {
  // Method 1: Top down - pass down the prefix sum 
  public int maxPathSumLeafToRoot(TreeNode root) {
    // Assume root is not null
    return maxPathSum(root, 0);
  }

  private int maxPathSum(TreeNode root, int sum) {
    sum += root.key; // calculte the prefix sum at the current level 
    
    if (root.left == null && root.right == null) {
      return sum;
    } else if (root.left == null) {
      return maxPathSum(root.right, sum); 
    } else if (root.right == null) {
      return maxPathSum(root.left, sum);
    }
    return Math.max(maxPathSum(root.left, sum), maxPathSum(root.right, sum));
  }
  
  // =======================================================================
  
  // Method 2: bottom up - return the max suffix sum (Optimal!!!)
  public int maxPathSumLeafToRoot(TreeNode root) {
    // Assume root is not null
    return maxPathSum(root);
  }

  private int maxPathSum(TreeNode root) {
    // base case
    if (root.left == null && root.right == null) {
      return root.key;
    }
    if (root.left == null) {
      return maxPathSum(root.right) + root.key;
    }
    if (root.right == null) {
      return maxPathSum(root.left) + root.key;
    }
    // current level
    return Math.max(maxPathSum(root.left), maxPathSum(root.right)) + root.key;
  }
  
  // =======================================================================
  
  // Method 3: Standard pre-order with a dynamic programming idea
  public int maxPathSumLeafToRoot(TreeNode root) {
    // Assume root is not null
    int pathPrefix = 0;
    int[] globalMax = { Integer.MIN_VALUE };
    preorder(root, pathPrefix, globalMax);
    return globalMax[0];
  }

  private void preorder(TreeNode root, int pathPrefix, int[] globalMax) {
    if (root == null) {
      return;
    }

    if (root.left == null && root.right == null) { // if we reach a leaf node 
      globalMax[0] = Math.max(globalMax[0], pathPrefix + root.key);
    }
      
    preorder(root.left, pathPrefix + root.key, globalMax);
    preorder(root.right, pathPrefix + root.key, globalMax);
  }
}
