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
public class IterativeOrderTraversalOfBinaryTree2 {
  /**
   * Pre-order Traversal Of Binary Tree (iterative)
   * Time: O(N), Space: O(H)
   */
  public List<Integer> preOrder(TreeNode root) {
    List<Integer> preorder = new ArrayList<>();
    if (root == null) {
      return preorder;
    }
    Deque<TreeNode> stack = new LinkedList<>();
    stack.offerFirst(root);
    while (!stack.isEmpty()) {
      TreeNode cur = stack.pollFirst();
      // 保证打印 preorder 的顺序正确
      // the left subtree should be traversed before the right subtree
      // since the stack is LIFO, we push right into the stack first
      // so for the next step the top element of stack is the left subtree
      if (cur.right != null) {
        stack.offerFirst(cur.right);
      }
      if (cur.left != null) {
        stack.offerFirst(cur.left);
      }
      // print the elemet first before process it 
      // 在 root 弹出 stack 的时候 print 它
      preorder.add(cur.key);
    }
    return preorder;
  }
  
  /**
   * In-order Traversal Of Binary Tree (iterative)
   * Time: O(N), Space: O(H)
   */
  public List<Integer> inOrder(TreeNode root) {
    List<Integer> inorder = new ArrayList<>();
    if (root == null) {
      return inorder;
    }
    Deque<TreeNode> stack = new LinkedList<>();
    TreeNode cur = root;
    while (cur != null || !stack.isEmpty()) {
      // always try to go left side to see if there is any node
      // should be traversed before cur node, cur node is 
      // stored on stack since it has not been traversed yet
      if (cur != null) {
        stack.offerFirst(cur);
        cur = cur.left;
      } else {
        // if can not go left, meaning all nodes on the left side of
        // the top node on the stack have been traversed, the next 
        // traversed node should be on the top of stack 
        cur = stack.pollFirst();
        inorder.add(cur.key);
        cur = cur.right;
      }
    }
    return inorder;
  }
  
  /**
   * Post-order Traversal Of Binary Tree (iterative)
   * Time: O(N), Space: O(H)
   */
  public List<Integer> postOrder(TreeNode root) {
    List<Integer> postorder = new ArrayList<>();
    if (root == null) {
      return postorder;
    }
    Deque<TreeNode> stack = new LinkedList<TreeNode>();
    stack.offerFirst(root);
    while (!stack.isEmpty()) {
      TreeNode cur = stack.pollFirst();
      postorder.add(cur.key);
      // the right subtree should be traversed before the left subtree
      // since the stack is LIFO, we push left into the stack first
      // so for the next step the top element of stack is the right subtree
      if (cur.left != null) {
        stack.offerFirst(cur.left);
      }
      if (cur.right != null) {
        stack.offerFirst(cur.right);
      }
    }
    Collections.reverse(postorder);
    return postorder;
  }
}
