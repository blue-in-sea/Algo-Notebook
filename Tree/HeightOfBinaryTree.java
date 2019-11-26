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
public class HeightOfBinaryTree {
  // Time: O(N)
  // Time: O(H)
  public int findHeight(TreeNode root) {
    // base case
    if (root == null) {
      return 0;
    }

    // Step 1 - 问左边右边各要一个值
    int leftHeight = findHeight(root.left);
    int rightHeight = findHeight(root.right);

    // Step 2 - 当前层需要干点什么事儿？（左边右边谁高取谁）
    int MyHeight = Math.max(leftHeight, rightHeight) + 1;

    // Step 3 - 返回给 parent 什么值？
    return MyHeight;
  }
}

/*
  public int findHeight(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return Math.max(findHeight(root.left), findHeight(root.right)) + 1;
  }

*/
