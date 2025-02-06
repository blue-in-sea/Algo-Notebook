import java.util.*;
/**
        1
       / \
      2   3
     / \ / \
    4  5 6  7

    in-order traversal: 4 2 5 1 6 3 7
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
public class ArrayToBinaryTreeBFS {
    // Time: O(n)
    // Space: O(m) maximum number of nodes at any level (the queue size)
    public static TreeNode arrayToBinaryTree(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }

        // Create the root node
        TreeNode root = new TreeNode(array[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root); // Add the root to the queue

        int index = 1; // Start from the second element in the array
        while (!queue.isEmpty() && index < array.length) {
            TreeNode current = queue.poll(); // Get the current node

            // Assign the left child
            if (index < array.length && array[index] != -1) { // -1 represents null
                current.left = new TreeNode(array[index]);
                queue.offer(current.left); // Add the left child to the queue
            }
            index++;

            // Assign the right child
            if (index < array.length && array[index] != -1) { // -1 represents null
                current.right = new TreeNode(array[index]);
                queue.offer(current.right); // Add the right child to the queue
            }
            index++;
        }

        return root;
    }
    
    // -------------------------------------------------------
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
