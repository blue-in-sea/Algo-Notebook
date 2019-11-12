/* *
 * In a binary search tree, find the node containing the largest number smaller than the given target number.
 * If there is no such number, return Integer.MIN_VALUE.
 */

public class TreeNode {
  int key;
  TreeNode left;
  TreeNode right;
  
  TreeNode(int key) {
    this.key = key;
  }
}

public class LargestNumberSmallerInBinarySearchTree {
  public int largestSmaller(TreeNode root, int target) {
    // assume tree is not null and no duplicate keys
    int result = Integer.MIN_VALUE;

    while (root != null){
      if (root.key < target) {
        result = root.key;
        root = root.right;
      } else {
        root = root.left;
      }
    }
    return result;
  }
}
