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
 * https://leetcode.com/problems/find-leaves-of-binary-tree
 */

class FindLeavesOfBinaryTree {
    // Time: O(N) we traverse the entire input tree once
    // Space: O(N) number of recursive call is bounded by the height of the tree.
    //        In the worest case, the tree is linear & height is N 
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, res);
        return res;  
    }

    private int dfs(TreeNode root, List<List<Integer>> res) {
        if (root == null) {
            return -1;
        }

        int level = Math.max(dfs(root.left, res), dfs(root.right, res)) + 1;

        if (res.size() == level) {
            // go to the next level
            res.add(new ArrayList<>());
        }

        res.get(level).add(root.val);
        return level;
    }
}
