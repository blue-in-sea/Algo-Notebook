/**
 * 450. Delete Node in a BST
 *
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
 *
 * Basically, the deletion can be divided into two stages:
 * Search for a node to remove.
 * If the node is found, delete the node.
 *
 *      5       delete            5
 *   3     6       [3]  =>    4       6
 * 2   4      7             2  x    x   7
 *
 *
 * Input: root = [5,3,6,2,4,null,7], key = 3
 * Output: [5,4,6,2,null,null,7]
 */

/**
 * Inorder traversal of BST is an array sorted in ascending order!!!
 *
 * 1. If key == root.val then the node to delete is right here
 *    1) If the node is a leaf                                    => set the root to be null
 *    2) If the node is not a leaf and has only the left child    => set the root to be its left child
 *    3) If the node is not a leaf and has the right child       => set the root.right to be its left child
 *       && if node.right has the left child
 *    4) others                                                   => find the replaced node & delete the node
 *       ...                                                   where the replaced should be the prev smallest
 *
 * 2. If key > root.val then 去左子树
 *
 * 3. If key > root.val then 去右子树
 */
public class DeleteInBinarySearchTree {
      // Time: O(logn) where search/delete/update in a balanced BST
      // Time: O(h) where search/delete/update in a skewed BST
      // Space: O(1)
      public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key == root.val) {
            if (root.left == null && root.right == null) {      /* 当前 node no L, no R .   */
                return null;
            } else if (root.right == null) {                    /* 当前 node yes L, no R    */
                return root.left;
            } else if (root.right.left == null) {                /* 当前 node 的右子树 no L */
                root.right.left = root.left;                    
                return root.right;
            } else {                                             /* 其余   */
                TreeNode newRoot = deleteSmallest(root.right);   /** delete root.right */
                newRoot.left = root.left;
                newRoot.right = root.right;
                return newRoot;
            }
        }

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        }

        return root;
    }
        
    private TreeNode deleteSmallest(TreeNode root) {
        while (root.left.left != null) {
            root = root.left;
        }
        TreeNode smallest = root.left;
        root.left = root.left.right;
        return smallest;
    }
}
/**
 * public class TreeNode {
 *   public int val;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int val) {
 *     this.val= val;
 *   }
 * }
 */
