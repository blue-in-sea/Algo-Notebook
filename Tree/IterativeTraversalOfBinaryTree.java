/**
 *        5
 *      /   \
 *     3      8
 *   /   \     \
 * 1      4     11
 *
 * Pre-order traversal is [5, 3, 1, 4, 8, 11]
 *  In-order traversal is [1, 3, 4, 5, 8, 11]
 * Postorder traversal is [1, 4, 3, 11, 8, 5]   ==>   [5, 8, 11, 3, 4, 1]
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
   * Pre-order Traversal Of Binary Tree (iterative)
   * Time: O(N), Space: O(H)
   */
  public List<Integer> preOrder(TreeNode root) {
    // Time: O(N), Space: O(H)
    List<Integer> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    
    Deque<TreeNode> stack = new LinkedList<>();	
    pushLeft(root, stack, res);
	  while (!stack.isEmpty()) {
		  TreeNode cur = stack.pollFirst();
		  cur = cur.right;
		  pushLeft(cur, stack, res);
	  }
    return res;
  }

  private void pushLeft(TreeNode cur, Deque<TreeNode> stack, List<Integer> res) {
    while (cur != null) {
      res.add(cur.key);
      stack.offerFirst(cur);
      cur = cur.left;
    } // cur = null -> end while
  }
  
  /**
   * In-order Traversal Of Binary Tree (iterative)
   * Time: O(N), Space: O(H)
   */
  public List<Integer> inOrder(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    
    Deque<TreeNode> stack = new LinkedList<>();	
    pushLeft(root, stack);
	  while (!stack.isEmpty()) {
		  TreeNode cur = stack.pollFirst();
		  res.add(cur.key);
		  cur = cur.right;
		  pushLeft(cur, stack);
	  }
    return res;
  }

  private void pushLeft(TreeNode cur, Deque<TreeNode> stack) {
    while (cur != null) {
      stack.offerFirst(cur);
      cur = cur.left;
    } // cur = null -> end while
  }
  
  /**
   * Post-order Traversal Of Binary Tree (iterative)
   * Time: O(N), Space: O(H)
   */
  public List<Integer> PostOrder(TreeNode root) {
    
  }
  
 

}
