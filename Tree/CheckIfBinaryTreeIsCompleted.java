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
    Queue<TreeNode> q = new LinkedList<>();
    boolean flag = false;
    q.offer(root);
    while (!q.isEmpty()) {
      TreeNode n = q.poll(); 
      
      if (n.left == null) {
        // if any of child is not present before, we set the flag to red (true)
        flag = true;
      } else if (flag) {
        // if flag is set (from previous node), we still see n has a left child 
        // we return flase 
        return false;
      } else {
        q.offer(n.left);
      }
      
      if (n.right == null) {
        flag = true;
      } else if (flag) {
        // if flag is set (from n's left node), we still see n has a right child
        // we return false
        return false;
      } else {
        q.offer(n.right);
      }
    }
    return true;
  }
}
