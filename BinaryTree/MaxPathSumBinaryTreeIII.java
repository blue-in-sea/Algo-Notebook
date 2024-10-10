/**
 * 140. Maximum Path Sum Binary Tree III (two node can be same node, and there only can be 1 path from Root to Leaf)
 *
 * Given a binary tree in which each node contains an integer number. Find the maximum possible sub-path sum(both the 
 * starting and ending node of the subpath should be on the same path from root to one of the leaf nodes, and the 
 * sub-path is allowed to contain only one node).
 *
 * The root of given binary tree is not null
 * Examples
 *
 *    -5
 *   /   \
 * 2     11
 *      /   \
 *     6    14
 *          /
 *        -3
 *
 * The maximum path sum is 11 + 14 = 25
 */
public class MaxPathSumBinaryTreeIII {
    // Mathod 1: recursive
    // Time: O(H) where O(log N) for average case, O(N) worst case
    // Space: O(H) 
    public boolean exist(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        List<TreeNode> path = new ArrayList<>();
        return dfs(root, path, sum);
    }

    private boolean dfs(TreeNode root, List<TreeNode> path, int sum) {
        path.add(root);

        int tmp = 0;
        for (int i = path.size() - 1; i >= 0; i--) {
            tmp += path.get(i).key;
            if (tmp == sum) {
                return true;
            }
        }

        if (root.left != null && dfs(root.left, path, sum)) {
            return true;
        }

        if (root.right != null && dfs(root.right, path, sum)) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }
}
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
