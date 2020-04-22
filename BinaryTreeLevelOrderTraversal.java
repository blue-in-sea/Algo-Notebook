/**
 * Find the longest common substring of two given strings.
 * 
 * Assumptions
 * The two given strings are not null     
 *     
 * Examples       
 * S = “abcde”, T = “cdf”, the longest common substring of S and T is “cd” 
 * S = “student”, T = “sweden”, the longest common substring of S and T is “den”    
 */

public class BinaryTreeLevelOrderTraversal {
    /**
     * @param root: A Tree
     * @return: Level order a list of lists of integer
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        
        if (root == null) {
            return res;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                level.add(cur.val);
                
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            res.add(level);
        }
        
        return res;
    }
}
