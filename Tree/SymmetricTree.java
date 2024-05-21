/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     TreeNode left;
 *     TreeNode right;  
 *     int val;
 *     
 *     TreeNode(TreeNode left, TreeNode right, int val) {
 *         this.left = left;
 *         this.right = right;
 *         this.val = val;
 *     }
 * }
 * https://leetcode.com/problems/symmetric-tree
 */

class Solution {
    // Time: O(N) we traverse the entire input tree once
    // Space: O(N) number of recursive call is bounded by the height of the tree.
    //        In the worest case, the tree is linear & height is N 

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode one, TreeNode two) {
        // Two subtree are symmetric each other if 
        // 1. their roots have the same val
        // 2. right substree is a mirror reflection of the left substree

        if (one == null && two == null) {
            return true;
        } else if (one == null || two == null) {
            return false;
        } else if (one.val != two.val) {
            return false;
        }
        
        return isSymmetric(one.left, two.right) && isSymmetric(one.right, two.left);
    }
}
