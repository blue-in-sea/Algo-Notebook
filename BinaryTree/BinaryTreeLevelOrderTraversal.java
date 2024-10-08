/**
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
       public TreeNode right;
       
 *     public TreeNode(int val) {
 *         this.val = val;
 *         // this.left = this.right = null;
 *     }
 * }
 */

public class BinaryTreeLevelOrderTraversal {
    // Time: O(n)
    // Space: O(n) for size of queue/res list 
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        
        if (root == null) return res;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();

                level.add(curr.val);

                if (curr.left != null) {
                    queue.offer(curr.left);
                }

                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }

            res.add(level);
        }

        return res;
    }
}
