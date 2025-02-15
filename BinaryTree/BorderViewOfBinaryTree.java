/**
 * 545. Border View Of Binary Tree
 *
 *              1
 *       x            2
 *              3           4
 * Input: root = [1,null,2,3,4]
 * Output: [1,3,4,2]
 * Explanation:
 * - The left boundary is empty because the root does not have a left child.
 * - The right boundary follows the path starting from the root's right child 2 -> 4.
 *   4 is a leaf, so the right boundary is [2].
 * - The leaves from left to right are [3,4].
 * Concatenating everything results in [1] + [] + [3,4] + [2] = [1,3,4,2].
 */

/**
 * Root node: The topmost node of the tree.
 * Left boundary: The nodes along the leftmost path from the root to the leftmost leaf.
 * Leaf nodes: All the leaf nodes of the tree (from left to right).
 * Right boundary: The nodes along the rightmost path from the root to the rightmost leaf (in reverse order).
 *         1
 *        / \
 *       2   3
 *      /   /
 *     4   5
 *        /
 *       9
 *      /
 *     10
 *
 * root: 1
 * Left Boundary: 2 -> 4
 * Leaf Nodes: 4, 10
 * Right Boundary: 3 -> 5 -> 9 -> 10 (but added in reverse order: 10, 9, 5, 3)
 * output [1, 2, 4, 10, 9, 5, 3]
 */
public class BorderViewOfBinaryTree {
    // dfsLeft() + dfsLeaf + dfsRight()
    // preorder + preorder + postorder 
  
    // Time: O(3n) traverse all nodes -> O(n)
    // Space: O(3h) height of the tree stack calls -> O(h)  or O(n)
    List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        res.add(root.val);
        if (root.left == null && root.right == null) {
            return res;
        }

        dfsLeft(root.left, res);
        dfsLeaf(root, res);
        dfsRight(root.right, res);
        return res;
    }

    /**
     * Base Case: If the current node is null or a leaf node, return.
     * PreOrder: Add Node() Add the current node's value to the result list.
     *
     * If the left child exists, recursively traverse the left subtree.
     * If the left child does not exist, recursively traverse the right subtree.
     */
    private void dfsLeft(TreeNode root, List<Integer> res) {
        if (root == null || (root.left == null && root.right == null)) { // exlcude leaf-view
            return;
        }

        res.add(root.val);
        if (root.left != null) {
            dfsLeft(root.left, res);
        } else {
            dfsLeft(root.right, res);
        }
    }

   /**
     * Base Case: If the current node is null, return.
     * Leaf Node: If the current node is a leaf node (both left and right children are null),
     *            add its value to the result list.
     *
     * Recursive Traversal: Recursively traverse the left and right subtrees.
     */
    private void dfsLeaf(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            res.add(root.val);
            return;
        }
        dfsLeaf(root.left, res);
        dfsLeaf(root.right, res);
    }

    /**
     * Base Case: If the current node is null or a leaf node (both left and right children are null), return.
     * 
     * If the right child exists, recursively traverse the right subtree.
     * If the right child does not exist, recursively traverse the left subtree.
     *
     * PostOrder: After traversing the subtree, add the current node's value to the result list. 
     * This ensures that the right boundary nodes are added in reverse order.
     */
    private void dfsRight(TreeNode root, List<Integer> res) {
        if (root == null || (root.left == null && root.right == null)) { // exlcude leaf-view
            return;
        }

        if (root.right != null) {
            dfsRight(root.right, res);
        } else {
            dfsRight(root.left, res);
        }
        res.add(root.val);
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


