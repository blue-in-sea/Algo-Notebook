/**
 * In a binary search tree, find the node containing the largest number smaller than the given target number.
 * If there is no such number, return Integer.MIN_VALUE.
 */

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
public class LargestNumberSmallerInBinarySearchTree {
  /**
   * Time: O(H) where average case O(logn), worst case O(n)
   *       since cur either go left or go right along the tree 
   * Space: O(1) no extra space used 
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
