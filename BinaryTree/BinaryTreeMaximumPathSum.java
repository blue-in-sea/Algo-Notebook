/**
 * 124. Binary Tree Maximum Path Sum (from any Node to any Node)
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge
 * connecting them. A node can only appear in the sequence at most once.
 *
 * Note that the path does not need to pass through the root.
 *
 *           1
 *     2            3
 *
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 *
 *
 *          -10
 *     9           20
 *           15          7
 *
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 */
class BinaryTreeMaximumPathSum {
    private int maxSum;
    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        gainFromSubtree(root);
        return maxSum;
    }

    // post order traversal of subtree rooted at `root`
    private int gainFromSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // add the path sum from left subtree. Note that if the path
        // sum is negative, we can ignore it, or count it as 0.
        // This is the reason we use `Math.max` here.
        int gainFromLeft = Math.max(gainFromSubtree(root.left), 0);

        // add the path sum from right subtree. 0 if negative
        int gainFromRight = Math.max(gainFromSubtree(root.right), 0);

        // if left or right path sum are negative, they are counted
        // as 0, so this statement takes care of all four scenarios
        maxSum = Math.max(maxSum, gainFromLeft + gainFromRight + root.val);

        // return the max sum for a path starting at the root of subtree
        return Math.max(gainFromLeft + root.val, gainFromRight + root.val);
    }



    // ****************************************************************

    // Laicode version: 139. Maximum Path Sum Binary Tree II
    
    public int maxPathSum(TreeNode root) {
        int[] max = new int[] { Integer.MIN_VALUE };
        dfs(root, max);
        return max[0];
    }

    // return 以 root 为顶点直上直下的 path 中最大的一条 path 的 path sum
    private int dfs(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }

        int left = dfs(root.left, max);
        int right = dfs(root.right, max);

        left = left < 0 ? 0 : left;
        right = right < 0 ? 0 : right;

        int cur = root.key + left + right;
        max[0] = Math.max(max[0], cur);
        return root.key + Math.max(left, right);
    }
}
