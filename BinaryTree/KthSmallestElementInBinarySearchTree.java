package BinaryTree;

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class KthSmallestElementInBinarySearchTree {
    /**
     * @param root: the given BST
     * @param k: the given k
     * @return: the kth smallest element in BST
     */
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new LinkedList<>();
        pushLeft(root, stack);
        
        for (int i = 0; i < k - 1; i++) {
            // inOrder traversal iterative 
            TreeNode cur = stack.pollFirst();
            cur = cur.right;
            pushLeft(cur, stack);
        }
        
        return stack.peekFirst().val;
    }
    
    private void pushLeft(TreeNode cur, Deque<TreeNode> stack) {
        while (cur != null) {
            stack.offerFirst(cur);
            cur = cur.left;
        }
    }
}
