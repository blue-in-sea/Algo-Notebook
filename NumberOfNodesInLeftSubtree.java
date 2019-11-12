/* *
 * Given a binary tree, count the number of nodes in each node’s left subtree, 
 * and store it in the numNodesLeft field.
 *
 * Examples
 *                    1(6)
 * 
 *                /          \
 * 
 *            2(3)           3(0)
 * 
 *           /      \
 * 
 *       4(1)       5(0)
 * 
 *     /        \        \
 * 
 * 6(0)         7(0)      8(0)
 * 
 * The numNodesLeft is shown in parentheses.
 */
 
public class TreeNodeLeft {
  int key;
  TreeNodeLeft left;
  TreeNodeLeft right;
  int numNodesLeft;
  
  TreeNodeLeft(int key) {
    this.key = key;
  }
} 

public class NumberOfNodesInLeftSubtree {
  public void numNodesLeft(TreeNodeLeft root) {
    countNodes(root);
  }

  private int countNodes(TreeNodeLeft root) {
    // base case: leaf node
    if (root == null) {
      return 0;
    }
    // 1. What should we expect from left and right child
    int leftNum = countNodes(root.left);
    int rightNum = countNodes(root.right);
    // 2. What should we do in the current layer?
    root.numNodesLeft = leftNum;
    // 3. What shoudl we return to the parent layer?
    return leftNum + rightNum + 1;
  }
}
