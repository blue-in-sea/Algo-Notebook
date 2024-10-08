/**
 * 52. Search In Binary Search Tree
 * Find the target key K in the given binary search tree, return the node that contains the key if K is found,
 * otherwise return null.
 *
 * No duplicate keys in the binary search tree
 */

public class SearchInBinarySearchTree {
    public TreeNode search(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        TreeNode cur = root;
        while (cur != null && cur.key != key) {
            cur = key < cur.key ? cur.left : cur.right;
        }
        return cur;
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
