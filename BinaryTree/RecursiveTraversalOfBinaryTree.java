/**
 * 654. Pre-order, In-order, Post-order Traversal Of Binary Tree (recursive)
 * Implement a recursive, in-order traversal of a given binary tree, return the list of keys of each node in the tree
 *         5
 *       /    \
 *     3        8
 *   /   \        \
 * 1      4        11
 *
 * Pre-order traversal is [5, 3, 1, 4, 8, 11]
 * In-order traversal is [1, 3, 4, 5, 8, 11]
 * Post-order traversal is [1, 4, 3, 11, 8, 5]
 */
public class RecursiveTraversalOfBinaryTree {
    public List<Integer> preOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }
    private void helper(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.key);         
        helper(root.left, res);
        // In-order
        helper(root.right, res);
        // Post-order
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
