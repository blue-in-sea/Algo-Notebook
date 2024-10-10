/**
 * 675. Maximum Binary Tree
 * Given an integer array, build a binary tree following the rules below:
 *
 * 1. The root node is the maximum value in the array.
 * 2. The left sub-tree contains all values on the left of the root value in the given array.
 * 3. The right sub-tree contains all values on the right of the root value in the given array.
 * 4. All tree nodes follow the rules above.
 *
 * Input: [2,1,6,3]
 * Output: 
 *
 *           6
 *        /     \
 *      2        3
 *       \
 *        1
 */
public class MaxTree {
  public TreeNode constructMaximumBinaryTree(int[] A) {
    Deque<TreeNode> stack = new LinkedList<>(); //申请栈存放节点		
    TreeNode root = null;                            
    for (int i = 0; i <= A.length; i++) {
      //如果i==length,新建节点设置值为无穷大，否则值为A[i]
      TreeNode cur = i == A.length ? new TreeNode(Integer.MAX_VALUE) : new TreeNode(A[i]);
      while (!stack.isEmpty()) {                //如果栈不为空
        if (cur.key > stack.peek().key) {       //如果新建节点的值比栈顶大
          TreeNode node = stack.pop();          //临时保存栈顶节点并弹出
          if (stack.isEmpty()) {                //如果栈为空
            cur.left = node;                    //临时保存的栈顶的节点是当前新建节点的左子树
          } else {
            TreeNode left = stack.peek();
            if (left.key > cur.key) {            
              cur.left = node;                  //新建节点的左子树为临时保存节点
            } else {
              left.right = node;                //当前栈顶的节点的右子树为新建节点
            }
          }
        } else
          break;
      }
      stack.push(cur);                          //将新建节点压入栈中
    }
    return stack.peek().left;
  }
}
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
