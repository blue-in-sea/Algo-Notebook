/**
 * LaiCode 126. Lowest Common Ancestor I
 * Given two nodes in a binary tree, find their lowest common ancestor.
 *
 * The given two nodes are guaranteed to be in the binary tree
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
 * The lowest common ancestor of 2 and 14 is 5
 * The lowest common ancestor of 2 and 9 is 9
 */
class LCAI {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null) return null;
        return LCA(root, a, b);
    }

    private TreeNode LCA(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null) {
            return null;
        }

        if (a == root || b == root) {
            return root;
        }

        TreeNode l = LCA(root.left, a, b);
        TreeNode r = LCA(root.right, a, b);

        if (l != null && r != null) {
            return root;
        }

        // return the non-null child
        return l == null ? r : l;
    }
}
