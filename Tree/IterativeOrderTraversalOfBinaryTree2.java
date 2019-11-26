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
  public List<Integer> preOrder(TreeNode root) {
    List<Integer> preorder = new ArrayList<>();
    if (root == null) {
      return preorder;
    }
    Deque<TreeNode> stack = new LinkedList<>();
    stack.offerFirst(root);
    while (!stack.isEmpty()) {
      TreeNode cur = stack.pollFirst();
      // the left subtree should be traversed before the right subtree
      // since the stack is LIFO, we push right into the stack first
      // so for the next step the top element of stack is the left subtree
      if (cur.right != null) {
        stack.offerFirst(cur.right);
      }
      if (cur.left != null) {
        stack.offerFirst(cur.left);
      }
      preorder.add(cur.key);
    }
    return preorder;
  }
}
