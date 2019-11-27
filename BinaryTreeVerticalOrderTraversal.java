/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class BinaryTreeVerticalOrderTraversal {
    /**
     * @param root: the root of tree
     * @return: the vertical order traversal
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        
        if (root == null) {
            return res;
        }
        
        Map<Integer, List<Integer>> map = new TreeMap<Integer, List<Integer>>();  // order map : bbst
        
        Queue<Integer> qCol = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        qCol.offer(0);
        
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            Integer col = qCol.poll();
            
            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<Integer>(Arrays.asList(cur.val)));
            } else {
                map.get(col).add(cur.val);
            }
            
            if (cur.left != null) {
                queue.offer(cur.left);
                qCol.offer(col - 1);
            }
            
            if (cur.right != null) {
                queue.offer(cur.right);
                qCol.offer(col + 1);
            }
        }
        
        for (int n : map.keySet()) {
            res.add(map.get(n));
        }
        
        return res;
    }
}
