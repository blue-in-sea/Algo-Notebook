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
 * 
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


/**
 * 该 Iterator 算法即 non-recursion 的 inorder traversal，不仅仅适用于 BST，任何 Binary Tree 都可以
 * • stack 中保存一路走到当前节点的所有节点
 * • stack的栈顶 一直存储 iterator 指向的当前节点
 * • hasNext() 只需要判断 stack 是否为空
 * • next() 只需要返回 stack 的栈顶值，并将 iterator 挪到下一个点，对 stack 进行相应的变化
 *  
 * 挪到下一个点的算法如下：
 * • 如果当前点存在右子树，那么就是右子树中“一路向左”最左边的那个点
 * • 如果当前点不存在右子树，则是走到当前点的路径中，第一个左拐的点
 */

