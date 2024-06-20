package BinarySearchTree;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class MinimumDifferenceBST {
    // Algo: InOrder Traversal
    // Time: O(n)
    // Space: O(n)
    public int getMinimumDifference(TreeNode root) {
        int[] min = { Integer.MAX_VALUE };
        int[] pre = { -1 };
        dfs(root, min, pre);
        return min[0];
    }

    private void dfs(TreeNode root, int[] min, int[] pre) {
        if (root == null) return;
        dfs(root.left, min, pre);
        if (pre[0] == -1) {
            pre[0] = root.val;
        } else {
            min[0] = Math.min(min[0], root.val - pre[0]);
            pre[0] = root.val;
        }
        dfs(root.right, min, pre);
    }
}
