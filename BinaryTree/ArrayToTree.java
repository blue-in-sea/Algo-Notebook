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

public class ArrayToTree {
    static TreeNode arrToTree(Integer[] arr) {
        if (arr == null || arr.length < 0) return null;
        return dfs(arr, 0);
    }

    private static TreeNode dfs(Integer[] arr, int i) {
        if (i >= arr.length || arr[i] == null) {
            return null;
        }

        TreeNode root = new TreeNode(arr[i]);
        root.left = dfs(arr, i * 2 + 1);
        root.right = dfs(arr, i * 2 + 2);
        return root;
    }

    static void printTree(TreeNode root) {
        if (root == null) return;

        System.out.print(root.val + "  ");
        printTree(root.left);
        printTree(root.right);
    }

    public static void main(String[] args) {
        Integer arr[] = {3,9,20,null,null,15,7};
        System.out.print("preorder traversal: ");
        // preorder traversal: 3  9  20  15  7
        printTree(arrToTree(arr));
    }

    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
