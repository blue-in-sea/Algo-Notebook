public class MaxPathSumILeafToRoot {


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
