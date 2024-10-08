/**
 * 129. Lowest Common Ancestor IV
 * Given K nodes in a binary tree, find their lowest common ancestor.
 *
 * K >= 2
 * There is no parent pointer for the nodes in the binary tree
 * The given K nodes are guaranteed to be in the binary tree
 *
 * Examples
 *
 *         5
 *
 *       /   \
 *
 *      9     12
 *
 *    /  \      \
 *
 *   2    3      14
 *
 * The lowest common ancestor of 2, 3, 14 is 5
 * The lowest common ancestor of 2, 3, 9 is 9
 */
public class LCAIV {
    public TreeNode lowestCommonAncestor(TreeNode root, List<TreeNode> nodes) {
        Set<TreeNode> set = new HashSet<>(nodes);
        return LCA(root, set);
    }
    
    private TreeNode LCA(TreeNode root, Set<TreeNode> set) {
        // 1. What should you do on current layer?
        // base case: if root is null (or) root is one of the nodes in set 
        //            we can ignore the later recursion 
        if (root == null || set.contains(root)) {
            return root;
        }
        // 2. What do you expect from left or right child
        TreeNode l = LCA(root.left, set);
        TreeNode r = LCA(root.right, set);
        // 3. What should you report to the parent layer?
        if (l != null && r != null) {
            return root;
        }
        return l == null ? r : l;
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
