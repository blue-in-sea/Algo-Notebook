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
public class CheckIfBinaryTreeIsCompleted {
  public boolean isCompleted(TreeNode root) {
    if (root == null) {
      return true;
    } 
    Queue<TreeNode> q = new LinkedList<TreeNode>();
    boolean flag = false;
    q.offer(root);
    while (!q.isEmpty()) {
      TreeNode n = q.poll(); // n represnts curNode
      // if any of child is not present,
      // set the flag to TURE
      if (n.left == null) {
        flag = true;
      } else if (flag) {
        // if flag is set but we still see curNode has 
        // a left child we return FALSE
        return false;
      } else {
        q.offer(n.left);
      }
      if (n.right == null) {
        flag = true;
      } else if (flag) {
        // if flag is set but we still see curNode has 
        // a right child we return FALSE
        return false;
      } else {
        q.offer(n.right);
      }
    }
    return true;    
  }
}
