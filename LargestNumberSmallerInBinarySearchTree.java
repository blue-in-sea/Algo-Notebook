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
  /* *
   * Time: O(height) since cur either go left or go right along the tree 
   * Space: O(1)     no extra space used 
   */
  public int largestSmaller(TreeNode root, int target) {
    // assume tree is not null and no duplicate keys
    int result = Integer.MIN_VALUE;
    TreeNode cur = root;

    while (cur != null){
      if (cur.key < target) {
        result = cur.key;                // store cur 
        cur = cur.right;                 // ignore its all left-subtree
      } else {
        cur = cur.left;                  // ignore cur && its all right-subtree
      }
    }
    return result;
  }
}
