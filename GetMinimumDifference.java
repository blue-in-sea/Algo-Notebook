/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int getMinimumDifference(TreeNode root) {
        int[] res = { Integer.MAX_VALUE };
        int[] pre = { -1 };
        dfs(root, res, pre);
        return res[0];
    }

    private void dfs(TreeNode root, int[] res, int[] pre) {
        if (root == null) return;
        dfs(root.left, res, pre);
        if (pre[0] == -1) {
            pre[0] = root.val;
        } else {
            res[0] = Math.min(res[0], root.val - pre[0]);
            pre[0] = root.val;
        }
        dfs(root.right, res, pre);
    }
}
