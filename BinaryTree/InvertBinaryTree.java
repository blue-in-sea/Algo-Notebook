package BinaryTree;

/**
 * 226. Invert Binary Tree
 * Given the root of a binary tree, invert the tree, and return its root.
 *
 *         4                            4
 *    2         7          =>      7        2
 *  1  3      6   9              3  1     9   6
 *
 * Input: root = [4,2,7,1,3,6,9]
 * Output: [4,7,2,9,6,3,1]
 *
 *        2                            2
 *   1          3         =>      1          3
 * Input: root = [2,1,3]
 * Output: [2,3,1]
 *
 * Input: root = []
 * Output: []
 */
public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        dfs(root);
        return root;
    }

    // preorder
    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        dfs(root.left);
        dfs(root.right);
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
