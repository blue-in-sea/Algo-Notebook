/**
 * Binary Tree
 *         3
 *       /   \
 *     9       20
 *           /  \
 *         15     7
 *
 * preorder: [3, 9, 20, null, null, 15, 7]
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
        // this.left = null;
        // this.right = null;
    }
}

public class ArrayToBinaryTreeDFS {
    // Helper function to build the binary tree recursively
    private static TreeNode buildTree(int[] array, int index) {
        if (index >= array.length || array[index] == -1) {
            return null; // -1 or out of bounds represents a null node
        }

        TreeNode root = new TreeNode(array[index]); // Create the root node
        root.left = buildTree(array, 2 * index + 1); // Recursively build left subtree
        root.right = buildTree(array, 2 * index + 2); // Recursively build right subtree

        return root;
    }

    // Main function to convert array to binary tree
    public static TreeNode arrayToBinaryTree(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return buildTree(array, 0); // Start building from the root (index 0)
    }

    // ----------------------------------------------------------------------

    // Helper function to print the tree (in-order traversal)
    public static void inOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left);
        System.out.print(root.val + " ");
        inOrderTraversal(root.right);
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7}; // Example array
        TreeNode root = arrayToBinaryTree(array);

        System.out.println("In-order traversal of the constructed binary tree:");
        inOrderTraversal(root);
    }
}
