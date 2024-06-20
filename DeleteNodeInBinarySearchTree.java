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
class DeleteNodeInBinarySearchTree {
      public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key == root.val) {
            if (root.left == null && root.right == null) {      /* 当前 node no L, no R .   */
                return root.right;
            } else if (root.right == null) {                    /* 当前 node yes L, no R    */
                return root.left;
            } else if (root.right.left == null) {                /* 当前 node 的右子树 no L */
                root.right.left = root.left;                    
                return root.right;
            } else {                                             /* 其余   */
                TreeNode newRoot = deleteSmallest(root.right);   
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
