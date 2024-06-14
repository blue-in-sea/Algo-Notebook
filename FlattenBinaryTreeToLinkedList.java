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
public class FlattenBinaryTreeToLinkedList {
    public void flatten(TreeNode root) {
        TreeNode[] prev = new TreeNode[1];
        dfs(root, prev);
    }

    private void dfs(TreeNode root, TreeNode[] prev) {
        if (root == null) {
            return;
        }
        dfs(root.right, prev);
        dfs(root.left, prev);
        root.right = prev[0];
        root.left = null;
        prev[0] = root;
    }
    /**
     * Method 1: Traversal (not recommend!)
     */
    private TreeNode lastNode = null; // declare a global var
    public TreeNode flatten(TreeNode root) {
        if (root == null) {
            return null;
        }

        helper(root);
        return root;
    }
    private void helper(TreeNode root) {
        if (root == null) {
            return;
        }

        if (lastNode != null) {
            lastNode.left = null;
            lastNode.right = root;  // here we modify the right child
        }

        lastNode = root;
        TreeNode right = root.right; // right child's reference would be lost 
        // if we do not save it before modify the root.right
        helper(root.left);
        helper(right);
    }

    /**
     * Method 2: Divide and Conquer
     */
    public TreeNode flatten(TreeNode root) {
        helper(root);
        return root;
    }

    // flatten root and return the last node
    public TreeNode helper(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode leftLast = helper(root.left);
        TreeNode rightLast = helper(root.right);

        // connect leftLast to root.right
        if (leftLast != null) {
            leftLast.right = root.right;
            root.right = root.left;
            root.left = null;
        }

        if (rightLast != null) {
            return rightLast;
        }

        if (leftLast != null) {
            return leftLast;
        }

        return root;
    }
}
