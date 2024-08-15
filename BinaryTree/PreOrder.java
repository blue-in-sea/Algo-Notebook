/**
 * 655. Pre-order Traversal Of Binary Tree (recursive)
 *
 *         5
 *
 *       /    \
 *
 *     3        8
 *
 *   /   \        \
 *
 * 1      4        11
 *
 * Pre-order traversal is [5, 3, 1, 4, 8, 11]
 */
public class PreOrder {
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
        helper(root.right, res);
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
