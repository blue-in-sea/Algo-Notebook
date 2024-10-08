/**
 * 140. Maximum Path Sum Binary Tree III (two node can be same node, and there only can be 1 path from Root to Leaf)
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
public class MaximumPathSumBinaryTreeIII {
    // Mathod 1: recursive
    // Time: O(H) where O(log N) for average case, O(N) worst case
    // Space: O(H) 
    public TreeNode insert(TreeNode root, int key) {
        if (root == null) {
            return new TreeNode(key);
        }
        if (key < root.key) {
            root.left = insert(root.left, key);
        } else if (key > root.key) {
            root.right = insert(root.right, key);
        }
        return root;
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
