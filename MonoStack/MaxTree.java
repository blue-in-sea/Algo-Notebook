/**
 * 654. Maximum Binary Tree
 * You are given an integer array nums with no duplicates. A maximum binary tree can be built recursively by following algorithm:
 * 1. Create a root node whose value is the maximum value in nums.
 * 2. Recursively build the left subtree on the subarray prefix to the left of the maximum value.
 * 3. Recursively build the right subtree on the subarray suffix to the right of the maximum value.
 * 4. Return the maximum binary tree built from nums.
 *
 * Example 1
 *
 *            6
 *       3        5
 *    x    2    0    x
 *        x 1
 *
 * Input: nums = [3,2,1,6,0,5]
 * Output: [6,3,5,null,2,0,null,null,1]
 * Explanation: The recursive calls are as follow:
 * - The largest value in [3,2,1,6,0,5] is 6. Left prefix is [3,2,1] and right suffix is [0,5].
 *     - The largest value in [3,2,1] is 3. Left prefix is [] and right suffix is [2,1].
 *         - Empty array, so no child.
 *         - The largest value in [2,1] is 2. Left prefix is [] and right suffix is [1].
 *             - Empty array, so no child.
 *             - Only one element, so child is a node with value 1.
 *     - The largest value in [0,5] is 5. Left prefix is [0] and right suffix is [].
 *         - Only one element, so child is a node with value 0.
 *         - Empty array, so no child.
 *
 * Example 2
 *
 *       3
 *     x    2
 *         x  1
 *
 * Input: nums = [3,2,1]
 * Output: [3,null,2,null,1]
 */
class MaxTree {
    /**
     * Algo: MonoStack 维护一个单调非递减序列
     * 当遇见了一个大于栈顶的元素，我们就可以构建一个subtree
     * 同时栈顶的元素可以划分一个subarray 
     *
     * peek[ 3 ]
     * peek[ 2 3 ]
     * peek[ 1 2 3 ]
     *      6
     *   3
     * x   2
     *   x   1
     *
     * peek[ 6 ]
     * peek[ 0 6 ]
     * peek[ 5 6 ]
     * peek[ 2147483647 ]
     *
     * for 每一次新的元素，建立一个 TreeNode，同时维护一个 stack
     * while 栈不为空
     *    if (新建节点的值比栈顶大)  cur > stack.peek() {
     *        临时保存栈顶节点并弹出  n = stack.pop()
     *        if (如果栈空) {
     *            临时保存的栈顶的节点是当前新建节点的左子树 cur.left = n
     *        } else 栈不为空 {
     *            if (当前栈顶的节点 left 大于新建节点) cur.left = n
     *            else 小于 left.right = n
     *        }
     *    } else {
     *        不处理 break;
     *    }
     *    新建节点入栈
     * }
     */
    public TreeNode constructMaximumBinaryTree(int[] A) {
        Deque<TreeNode> stack = new LinkedList<>();        
        TreeNode root = null;
        for (int i = 0; i <= A.length; i++) {
          
            //如果i==length,新建节点设置值为无穷大，否则值为A[i]
            TreeNode cur = i == A.length ? new TreeNode(Integer.MAX_VALUE) : new TreeNode(A[i]);
          
            while (!stack.isEmpty()) {                    
                if (cur.val > stack.peek().val) {         
                    TreeNode node = stack.pop();          
                    if (stack.isEmpty()) {                
                        cur.left = node;                  
                    } else {
                        TreeNode left = stack.peek();
                        if (left.val > cur.val) {
                            cur.left = node;              
                        } else {
                            left.right = node;            
                        }
                    }
                } else {
                    break;
                }
        
            }
            stack.push(cur);                              
        }
        return stack.peek().left;
    }
}
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
