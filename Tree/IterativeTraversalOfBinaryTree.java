/**
 *        5
 *      /   \
 *     3      8
 *   /   \     \
 * 1      4     11
 *
 * Pre-order traversal is [5, 3, 1, 4, 8, 11]
 *  In-order traversal is [1, 3, 4, 5, 8, 11]
 * Postorder traversal is [1, 4, 3, 11, 8, 5]
 */

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
public class IterativeTraversalOfBinaryTree {
  
  /**
   * In-order Traversal Of Binary Tree (iterative)
   */
  public List<Integer> InOrder(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    
    Deque<Integer> stack = new LinkedList<>();
    pushLeft(root, stack);
    
    while (!stack.isEmpty()) {
      TreeNode cur = stack.offerFirst();
      res.add(cur);
      cur = cur.right;
      pushLeft(cur, stack);
    }
    
  }
  
  private void pushLeft(TreeNode cur, Deque<Integer> stack) {
    while (!cur.isEmpty()) {
      stack.offer(cur);
      cur = cur.left;
    } // cur = null : reach beyond leaf-node -> end while
  }
  
}
