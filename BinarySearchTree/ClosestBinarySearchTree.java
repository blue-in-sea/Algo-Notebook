/**
 * 135. Closest Number In Binary Search Tree
 * In a binary search tree, find the node containing the closest number to the given target number.
 * 
 * The given root is not null.
 * There are no duplicate keys in the binary search tree.
 * Examples:
 *
 *     5
 *
 *   /    \
 *
 * 2      11
 *
 *      /    \
 *
 *     6     14
 *
 * closest number to 4 is 5
 * closest number to 10 is 11
 * closest number to 6 is 6
 */
class ClosestBinarySearchTree {
   // Method 1: Recursive Inorder + Linear search, O(n) time
   // Time: O(n) to build inorder traversal and then to perform linear search takes linear time
   // Space: O(n) for dfs stack calls 
   public int closestValue(TreeNode root, double target) {
        List<Integer> inorder = new ArrayList<>();
        dfs(root, inorder);

        int closest = inorder.get(0);
        for (int i = 1; i < inorder.size(); i++) {
            if (Math.abs(closest - target) > Math.abs(inorder.get(i) - target)) {
                closest = inorder.get(i);
            }
        }
        return closest; 
    }

    private void dfs(TreeNode root, List<Integer> inorder) {
        if (root == null) return;

        dfs(root.left, inorder);
        inorder.add(root.val);
        dfs(root.right, inorder);
    }

    // Method 2: Iterative Traverse of BST (Optimal)
    // Time: O(k) in the average case and O(H) in the worst case, where k is an index of the closest element.
    // Space: O(1)
    public int closestValue(TreeNode root, double target) {
        // Assume the binary tree is not null 
        int closest = root.val;

        while (root != null) {
            if (closest == target) {
                return closest;
            } else {
                if (Math.abs(root.val - target) < Math.abs(closest - target)) {
                    closest = root.val;
                } 
                root = root.val < target ? root.left : root.right;
            }
        }
        return closest;
    }
}
