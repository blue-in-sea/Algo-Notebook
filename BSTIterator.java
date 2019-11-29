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
 * Example of iterate a tree:
 * BSTIterator iterator = new BSTIterator(root);
 * while (iterator.hasNext()) {
 *    TreeNode node = iterator.next();
 *    do something for node
 * } 
 */


public class BSTIterator {
    private Deque<TreeNode> stack = new LinkedList<>();
    
    /*
    * @param root: The root of binary tree.
    */public BSTIterator(TreeNode root) {
        while (root != null) {
            stack.offerFirst(root);
            root = root.left;
        }
    }

    /*
     * @return: True if there has next node, or false
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /*
     * @return: return next node
     */
    public TreeNode next() {
        if (stack.isEmpty()) {
            return null;
        }
        
        // inOrder traversal iterative 
        TreeNode node = stack.pollFirst();
        TreeNode cur = node.right;
        pushLeft(cur);   
        
        return node;
    }
    
    private void pushLeft(TreeNode cur) {
        while (cur != null) {
            stack.offerFirst(cur);
            cur = cur.left;
        }
    }
}
